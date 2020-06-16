package core.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BeanContext {

    public final Class<?> cls;
    public List<FieldContext> fields;
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

    private static List findFields(Class<?> cls) {
        List fields = new ArrayList();
        while (cls != null) {
            for (Field field : cls.getDeclaredFields()) {
                FieldContext context = new FieldContext(field);
                fields.add(context);
            }
            cls = cls.getSuperclass();
        }
        return fields;
    }
}
