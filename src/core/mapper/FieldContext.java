package core.mapper;

import java.lang.reflect.Field;

public final class FieldContext {

    public final Field field;
    public final Class<?> cls;
    public final String name;

    public FieldContext(Field field) {
        this.field = field;
        this.cls = field.getType();
        this.name = field.getName();
        field.setAccessible(true);
    }
}