package br.com.educode.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author eduardo
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UpperCase {

    public boolean free() default false;
    
    public boolean insert() default true;

    public boolean update() default true;

    public boolean delete() default true;
}
