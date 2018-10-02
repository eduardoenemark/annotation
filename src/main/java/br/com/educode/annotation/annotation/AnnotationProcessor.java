package br.com.educode.annotation.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static br.com.educode.annotation.annotation.OperationEnum.DELETE;
import static br.com.educode.annotation.annotation.OperationEnum.INSERT;
import static br.com.educode.annotation.annotation.OperationEnum.UPDATE;
import br.com.educode.annotation.annotation.logic.AnnotationLogic;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eduardo
 */
public class AnnotationProcessor {
    private Map<Class<? extends Annotation>, AnnotationLogic> annotationLogics;

    private AnnotationProcessor() {
        super();
    }

    public AnnotationProcessor(AnnotationLogic[] annotationLogics) {
        this(Arrays.asList(annotationLogics));
    }

    public AnnotationProcessor(List<AnnotationLogic> annotationLogics) {
        this.annotationLogics = new HashMap<>();
        annotationLogics.forEach((annotationLogic) -> {
            Class annotation = getFirstAnnotationType(annotationLogic.getClass().getGenericInterfaces());
            if (annotation == null) {
                throw new IllegalArgumentException("Each annotation logic must specify an annotation as generic type.");
            }
            this.annotationLogics.put(getFirstAnnotationType(annotationLogic.getClass().getGenericInterfaces()), annotationLogic);
        });
    }

    public Object processor(Object object) throws Exception {
        return processor(object, OperationEnum.FREE);
    }

    public Object processor(Object object, OperationEnum operationEnum) throws AnnotationException {
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
                AnnotationLogic annotationLogic = annotationLogics.get(annotation.annotationType());
                if (annotationLogic == null) {
                    continue;
                }
                Method set, get;
                Boolean isFree, isInsert, isUpdate, isDelete;
                try {
                    String strGet = (field.getType() == Boolean.TYPE || field.getType() == boolean.class) ? "is" : "get";
                    get = getMethod(object.getClass(), strGet.concat(field.getName()));
                    set = getMethod(object.getClass(), "set".concat(field.getName()));
                    isFree = (Boolean) free.invoke(annotation);
                    isInsert = (Boolean) insert.invoke(annotation);
                    isUpdate = (Boolean) update.invoke(annotation);
                    isDelete = (Boolean) delete.invoke(annotation);

                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                    exception.printStackTrace();
                    continue;
                }
                if (applyProcessorOnField(operationEnum, isFree, isInsert, isUpdate, isDelete)) {
                    annotationLogic.validate(annotation, object, field, get, set, operationEnum);
                }
            }
        }
        return null;
    }

    public boolean applyProcessorOnField(
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

    private static Class getFirstAnnotationType(Type[] types) {
        Class annotation;
        for (Type type : types) {
            annotation = getFirstAnnotationType(type);
            if(annotation != null) {
                return annotation;
            }
        }
        return null;
    }

    private static Class getFirstAnnotationType(Type type) {
        if (type instanceof Class) {
            Class classToCheck = (Class) type;
            List<Class> interfaces = Arrays.asList(classToCheck.getInterfaces());
            if (interfaces.contains(Annotation.class)) {
                return classToCheck;
            }
        } else if (type instanceof ParameterizedType) {
            Type[] aType = ((ParameterizedType) type).getActualTypeArguments();
            for (Type type1 : aType) {
                return getFirstAnnotationType(type1);
            }
        } else if (type instanceof GenericArrayType) {
            Type genericComponentType =
                    ((GenericArrayType) type).getGenericComponentType();
            return getFirstAnnotationType(genericComponentType);
        }
        return null;
    }
}
