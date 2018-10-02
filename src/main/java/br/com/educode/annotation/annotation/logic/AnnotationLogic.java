/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.educode.annotation.annotation.logic;

import br.com.educode.annotation.annotation.AnnotationException;
import br.com.educode.annotation.annotation.OperationEnum;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

/**
 *
 * @author u843433
 * @param <T>
 */
public interface AnnotationLogic <T extends Annotation> {
    public void validate(T annotation, Object object, Field field, Method get, Method set, OperationEnum operation) throws AnnotationException;
}
