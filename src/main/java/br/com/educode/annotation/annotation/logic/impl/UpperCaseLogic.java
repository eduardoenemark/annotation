/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.educode.annotation.annotation.logic.impl;

import br.com.educode.annotation.annotation.AnnotationException;
import br.com.educode.annotation.annotation.OperationEnum;
import br.com.educode.annotation.annotation.UpperCase;
import br.com.educode.annotation.annotation.logic.AnnotationLogic;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author u843433
 */
public class UpperCaseLogic implements AnnotationLogic<UpperCase>{

    @Override
    public void apply(UpperCase annotation, Object object, Field field, Method get, Method set, OperationEnum operacao) throws AnnotationException {
        try {
            String value = (String) get.invoke(object);
            set.invoke(object, upperCase(value));
        } catch (Exception ex) {
            Logger.getLogger(UpperCaseLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String upperCase(String value) {
        return (value != null) ? value.toUpperCase() : null;
    }
}
