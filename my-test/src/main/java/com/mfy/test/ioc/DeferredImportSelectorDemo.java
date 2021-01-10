package com.mfy.test.ioc;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname DeferredImportSelectorDemo
 * @Description TODO
 * @Author Jack
 * Date 2020/12/29 14:06
 * Version 1.0
 */
public class DeferredImportSelectorDemo implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("=====DeferredImportSelectorDemo.selectImports");
        return new String[]{Student.class.getName()};
    }

    /**
     * 要返回一个实现了Group接口的类
    */
    @Override
    public Class<? extends Group> getImportGroup() {
        return DeferredImportSelectorGroupDemo.class;
    }

    private static class DeferredImportSelectorGroupDemo implements Group {

        List<Entry> list = new ArrayList<>();
        /**
            收集需要实例化的类
        */
        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            System.out.println("=====DeferredImportSelectorGroupDemo.process");
            String[] strings = selector.selectImports(metadata);
            for (String string : strings) {
                list.add(new Entry(metadata,string));
            }
        }

        @Override
        public Iterable<Entry> selectImports() {
            System.out.println("=====DeferredImportSelectorGroupDemo.selectImports");
            return list;
        }
    }
}
