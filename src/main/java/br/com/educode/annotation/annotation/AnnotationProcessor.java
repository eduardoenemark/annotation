package br.com.educode.annotation.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Random;
import static br.com.educode.annotation.annotation.OperationEnum.DELETE;
import static br.com.educode.annotation.annotation.OperationEnum.INSERT;
import static br.com.educode.annotation.annotation.OperationEnum.UPDATE;
import br.com.educode.annotation.annotation.logic.AnnotationLogic;
import java.util.Map;

/**
 *
 * @author eduardo
 */
public class AnnotationProcessor {
    private Map<Class<? extends Annotation>, AnnotationLogic> annotationLogics;

    public AnnotationProcessor(Map<Class<? extends Annotation>, AnnotationLogic> annotationLogics) {
        this.annotationLogics = annotationLogics;
    }

    public Object processor(Object object) throws Exception {
        return processor(object, OperationEnum.FREE);
    }

    public Object processor(Object object, OperationEnum operationEnum) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                Method free, insert, update, delete;
                try {
                    free = getMethod(annotation.getClass(), "free");
                    insert = getMethod(annotation.getClass(), "insert");
                    update = getMethod(annotation.getClass(), "update");
                    delete = getMethod(annotation.getClass(), "delete");
                } catch (Exception exception) {
                    exception.printStackTrace();
                    continue;
                }
                AnnotationLogic annotationLogic = annotationLogics.get(annotation.getClass());
                if (annotationLogic == null) {
                    continue;
                }
                Method set, get;
                Boolean isFree, isInsert, isUpdate, isDelete;
                try {
                    get = getMethod(object.getClass(), "get".concat(field.getName()));
                    set = getMethod(object.getClass(), "set".concat(field.getName()));
                    isFree = (Boolean) free.invoke(annotation);
                    isInsert = (Boolean) insert.invoke(annotation);
                    isUpdate = (Boolean) update.invoke(annotation);
                    isDelete = (Boolean) delete.invoke(annotation);

                } catch (Exception exception) {
                    exception.printStackTrace();
                    continue;
                }
                if (applyProcessorOnField(operationEnum, isFree, isInsert, isUpdate, isDelete)) {
                    annotationLogic.validate(annotation, object, field, get, set, operationEnum);
                }
            }
            if (annotations.length > 0) {
                for (Annotation annotation : annotations) {
                    Method set, get;
                    try {
                        get = getMethod(object.getClass(), "get".concat(field.getName()));
                        set = getMethod(object.getClass(), "set".concat(field.getName()));

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        continue;
                    }
//                    if (annotation instanceof UpperCase) {
//                        UpperCase uc = (UpperCase) annotation;
//                        if (applyProcessorOnField(operationEnum, uc.free(), uc.insert(), uc.update(), uc.delete())) {
//                            String value = (String) get.invoke(object);
//                            set.invoke(object, upperCase(value));
//                        }
//                    } else if (annotation instanceof DateNotLessThanToday) {
//                        DateNotLessThanToday dt = (DateNotLessThanToday) annotation;
//                        if (applyProcessorOnField(operationEnum, dt.free(), dt.insert(), dt.update(), dt.delete())) {
//                            LocalDate now = LocalDate.now();
//                            LocalDate value = (LocalDate) get.invoke(object);
//                            if (now.compareTo(value) > 0) {
//                                if (dt.throwException()) {
//                                    throw new AnnotationException(dt.exceptionMessage());
//                                }
//                            }
//                        }
//                    } else if (annotation instanceof RandomNumber) {
//                        RandomNumber rn = (RandomNumber) annotation;
//                        if (applyProcessorOnField(operationEnum, rn.free(), rn.insert(), rn.update(), rn.delete())) {
//                            set.invoke(object, randomNumber(rn.bound()));
//                        }
//                    }
                }
            }
        }
        return null;
    }

    public static boolean applyProcessorOnField(
            OperationEnum operationEnum,
            boolean free,
            boolean insert,
            boolean update,
            boolean delete) {
        boolean applyProcessorOnField = true;
        switch (operationEnum) {
            case INSERT:
                applyProcessorOnField = insert;
                break;
            case UPDATE:
                applyProcessorOnField = update;
                break;
            case DELETE:
                applyProcessorOnField = delete;
                break;
            case FREE:
                applyProcessorOnField = free;
        }
        return applyProcessorOnField;
    }

    public static Method getMethod(Class clazz, String pattern) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().toUpperCase().matches(pattern.toUpperCase())) {
                return method;
            }
        }
        return null;
    }

//    public static String upperCase(String value) {
//        return (value != null) ? value.toUpperCase() : null;
//    }

//    public static long randomNumber(int bound) {
//        return new Random().nextInt(bound);
//    }

}
