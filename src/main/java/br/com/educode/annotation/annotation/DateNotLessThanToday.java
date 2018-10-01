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
public @interface DateNotLessThanToday {

    public boolean throwException() default false;

    public String exceptionMessage() default "Date not can lesser than today!";

    public boolean free() default false;

    public boolean insert() default true;

    public boolean update() default true;

    public boolean delete() default true;
}
