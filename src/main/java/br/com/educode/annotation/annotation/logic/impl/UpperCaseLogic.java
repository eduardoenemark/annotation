/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.educode.annotation.annotation.logic.impl;

import br.com.educode.annotation.annotation.AnnotationException;
import static br.com.educode.annotation.annotation.AnnotationProcessor.upperCase;
import br.com.educode.annotation.annotation.OperationEnum;
import br.com.educode.annotation.annotation.UpperCase;
import br.com.educode.annotation.annotation.logic.AnnotationLogic;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author u843433
 */
public class UpperCaseLogic implements AnnotationLogic{

    @Override
    public void validate(Annotation annotation, Object object, Field field, Method get, Method set, OperationEnum operacao) throws AnnotationException {
        UpperCase uc = (UpperCase) annotation;
        String value = (String) get.invoke(object);
        set.invoke(object, upperCase(value));
    }

    public static String upperCase(String value) {
        return (value != null) ? value.toUpperCase() : null;
    }
}
