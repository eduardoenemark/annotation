/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.educode.annotation.service;

import br.com.educode.annotation.annotation.AnnotationException;
import br.com.educode.annotation.annotation.UpperCase;
import br.com.educode.annotation.annotation.logic.AnnotationLogic;
import br.com.educode.annotation.annotation.logic.impl.UpperCaseLogic;
import br.com.educode.annotation.model.Agendador;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author u843433
 */
public class AgendadorServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public AgendadorServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class AgendadorService.
     */
    @Test
    public void testInsertDataInvalida() throws Exception {
        thrown.expect(AnnotationException.class);

        Agendador agendador = new Agendador("task02", LocalDate.of(1980, 2, 1));
        AgendadorService agendadorService = new AgendadorService();
        agendadorService.insert(agendador);
          assertTrue(true);
    }

    /**
     * Test of insert method, of class AgendadorService.
     */
    @Test
    public void testDelete() throws Exception {
//        thrown.expect(AnnotationException.class);

//        Agendador agendador = new Agendador("task02", LocalDate.of(1980, 2, 1));
//        AgendadorService agendadorService = new AgendadorService();
//        agendadorService.insert(agendador);
        assertTrue(true);
    }

    @Test
    public void teste() throws Exception {
        AnnotationLogic uc = new UpperCaseLogic();
        Type[] types = uc.getClass().getGenericInterfaces();

        Class teste = getFirstAnnotationType(types);

        assertEquals(teste, UpperCase.class);
    }

    private static Class getFirstAnnotationType(Type[] types) {
        Class annotation;
        for (Type type : types) {
            annotation = getAnnotationType(type);
            if(annotation != null) {
                return annotation;
            }
        }
        return null;
    }

    private static Class getAnnotationType(Type type) {
        if (type instanceof Class) {
            Class classToCheck = (Class) type;
            List<Class> interfaces = Arrays.asList(classToCheck.getInterfaces());
            if (interfaces.contains(Annotation.class)) {
                return classToCheck;
            }
        } else if (type instanceof ParameterizedType) {
            Type[] aType = ((ParameterizedType) type).getActualTypeArguments();
            for (Type type1 : aType) {
                return getAnnotationType(type1);
            }
        } else if (type instanceof GenericArrayType) {
            Type genericComponentType =
                    ((GenericArrayType) type).getGenericComponentType();
            return getAnnotationType(genericComponentType);
        }
        return null;
    }

     private static void printType(Type type) {
        if (type instanceof Class) {
            System.out.println("type is Class");
            System.out.println("class name: " + ((Class) type).getSimpleName());
        } else if (type instanceof ParameterizedType) {
            System.out.println("type is ParameterizedType");
            Type[] aType = ((ParameterizedType) type).getActualTypeArguments();
            System.out.println("printing each one of ActualTypeArguments:");
            System.out.println("----");
            for (Type type1 : aType) {
                printType(type1);
            }
            System.out.println("----");
        } else if (type instanceof TypeVariable) {
            System.out.println("type is TypeVariable");
            String typeVariableName = ((TypeVariable) type).getName();
            System.out.println("typeVariableName: " + typeVariableName);

        } else if (type instanceof GenericArrayType) {
            System.out.println("type is GenericArrayType");
            Type genericComponentType =
                    ((GenericArrayType) type).getGenericComponentType();
            printType(genericComponentType);
        }
    }
}
