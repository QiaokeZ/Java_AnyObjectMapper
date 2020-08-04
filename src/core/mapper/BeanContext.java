package core.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BeanContext {

    public final Class<?> cls;
    public Map<String, FieldContext> fields;
    public static Map<Class, BeanContext> classCache = new HashMap();

    private BeanContext(Class<?> cls) {
        this.cls = cls;
    }

    public static BeanContext get(Class<?> cls) {
        synchronized (cls) {
            BeanContext context = classCache.get(cls);
            if (context == null) {
                context = new BeanContext(cls);
                context.fields = findFields(cls);
                classCache.put(cls, context);
            }
            return context;
        }
    }

    private static Map findFields(Class<?> cls) {
        Map fields = new HashMap();
        while (cls != null) {
            for (Field field : cls.getDeclaredFields()) {
                FieldContext context = new FieldContext(field);
                fields.put(context.name, context);
            }
            cls = cls.getSuperclass();
        }
        return fields;
    }
}
