/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.scope;

import java.lang.reflect.Modifier;

import org.springframework.aop.framework.AopInfrastructureBean;
import org.springframework.aop.framework.ProxyConfig;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.aop.target.SimpleBeanTargetSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.FactoryBeanNotInitializedException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * Convenient proxy factory bean for scoped objects.
 *
 * <p>Proxies created using this factory bean are thread-safe singletons
 * and may be injected into shared objects, with transparent scoping behavior.
 *
 * <p>Proxies returned by this class implement the {@link ScopedObject} interface.
 * This presently allows for removing the corresponding object from the scope,
 * seamlessly creating a new instance in the scope on next access.
 *
 * <p>Please note that the proxies created by this factory are
 * <i>class-based</i> proxies by default. This can be customized
 * through switching the "proxyTargetClass" property to "false".
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 2.0
 * @see #setProxyTargetClass
 */
@SuppressWarnings("serial")
public class ScopedProxyFactoryBean extends ProxyConfig
		implements FactoryBean<Object>, BeanFactoryAware, AopInfrastructureBean {

	/** The TargetSource that manages scoping. */
	private final SimpleBeanTargetSource scopedTargetSource = new SimpleBeanTargetSource();

	/** The name of the target bean. */
	@Nullable
	private String targetBeanName;

	/** The cached singleton proxy. */
	@Nullable
	private Object proxy;


	/**
	 * Create a new ScopedProxyFactoryBean instance.
	 */
	public ScopedProxyFactoryBean() {
		setProxyTargetClass(true);
	}


	/**
	 * Set the name of the bean that is to be scoped.
	 */
	public void setTargetBeanName(String targetBeanName) {
		this.targetBeanName = targetBeanName;
		// 设置beanName,在SimpleBeanTargetSource.getTarget()时,通过targetBeanName从beanFactory中获取原型实例对象
		this.scopedTargetSource.setTargetBeanName(targetBeanName);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		if (!(beanFactory instanceof ConfigurableBeanFactory)) {
			throw new IllegalStateException("Not running in a ConfigurableBeanFactory: " + beanFactory);
		}
		ConfigurableBeanFactory cbf = (ConfigurableBeanFactory) beanFactory;

		this.scopedTargetSource.setBeanFactory(beanFactory);
		// 由于实现了BeanFactoryAware接口,在实例化bean的初始化阶段,会调用此方法
		ProxyFactory pf = new ProxyFactory();
		pf.copyFrom(this);
		// 给ProxyFactory添加targetSource,后续AOP调用,通过targetSource获取被代理的对象
		pf.setTargetSource(this.scopedTargetSource);

		Assert.notNull(this.targetBeanName, "Property 'targetBeanName' is required");
		Class<?> beanType = beanFactory.getType(this.targetBeanName);
		if (beanType == null) {
			throw new IllegalStateException("Cannot create scoped proxy for bean '" + this.targetBeanName +
					"': Target type could not be determined at the time of proxy creation.");
		}
		if (!isProxyTargetClass() || beanType.isInterface() || Modifier.isPrivate(beanType.getModifiers())) {
			pf.setInterfaces(ClassUtils.getAllInterfacesForClass(beanType, cbf.getBeanClassLoader()));
		}

		// Add an introduction that implements only the methods on ScopedObject.
		ScopedObject scopedObject = new DefaultScopedObject(cbf, this.scopedTargetSource.getTargetBeanName());
		// 添加DelegatingIntroductionInterceptor的通知,这个通知其实啥都没有做,就是传递了一下火炬,
		// @Autowired+原型的实现:主要是AOP调用前,获取实例对象时,通过SimpleBeanTargetSource.getTarget()做到的
		pf.addAdvice(new DelegatingIntroductionInterceptor(scopedObject));

		// Add the AopInfrastructureBean marker to indicate that the scoped proxy
		// itself is not subject to auto-proxying! Only its target bean is.
		pf.addInterface(AopInfrastructureBean.class);

		this.proxy = pf.getProxy(cbf.getBeanClassLoader());
	}


	@Override
	public Object getObject() {
		if (this.proxy == null) {
			throw new FactoryBeanNotInitializedException();
		}
		return this.proxy;
	}

	@Override
	public Class<?> getObjectType() {
		if (this.proxy != null) {
			return this.proxy.getClass();
		}
		return this.scopedTargetSource.getTargetClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
