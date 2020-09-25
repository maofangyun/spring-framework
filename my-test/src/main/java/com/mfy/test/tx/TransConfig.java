package com.mfy.test.tx;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.mfy.test.tx")
@EnableTransactionManagement
public class TransConfig {
}
