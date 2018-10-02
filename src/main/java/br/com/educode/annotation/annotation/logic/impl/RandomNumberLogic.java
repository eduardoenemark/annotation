/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.educode.annotation.annotation.logic.impl;

import br.com.educode.annotation.annotation.AnnotationException;
import br.com.educode.annotation.annotation.OperationEnum;
import br.com.educode.annotation.annotation.RandomNumber;
import br.com.educode.annotation.annotation.logic.AnnotationLogic;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 *
 * @author u843433
 */
public class RandomNumberLogic implements AnnotationLogic<RandomNumber>{

    @Override
    public void apply(RandomNumber annotation, Object object, Field field, Method get, Method set, OperationEnum operacao) throws AnnotationException {
        try {
            set.invoke(object, randomNumber(annotation.bound()));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new AnnotationException(ex.getMessage());
        }
    }

    public static long randomNumber(int bound) {
        return new Random().nextInt(bound);
    }
}
