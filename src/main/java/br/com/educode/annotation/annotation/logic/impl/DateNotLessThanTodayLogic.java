/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.educode.annotation.annotation.logic.impl;

import br.com.educode.annotation.annotation.AnnotationException;
import br.com.educode.annotation.annotation.DateNotLessThanToday;
import br.com.educode.annotation.annotation.OperationEnum;
import br.com.educode.annotation.annotation.logic.AnnotationLogic;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;

/**
 *
 * @author u843433
 */
public class DateNotLessThanTodayLogic implements AnnotationLogic{

    @Override
    public void validate(Annotation annotation, Object object, Field field, Method get, Method set, OperationEnum operation) throws AnnotationException {
        DateNotLessThanToday dt = (DateNotLessThanToday) annotation;
        try {
            LocalDate now = LocalDate.now();
            LocalDate value = (LocalDate) get.invoke(object);
            if (now.compareTo(value) > 0) {
                throw new Exception(dt.exceptionMessage());
            }
        } catch (Exception ex) {
            if (dt.throwException()) {
                throw new AnnotationException(dt.exceptionMessage());
            }
        }
    }
}
