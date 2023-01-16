package com.ntt.godzilla.annotation;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "gozTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public @interface GozTransaction {
}
