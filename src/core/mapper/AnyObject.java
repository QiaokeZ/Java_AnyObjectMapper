package core.mapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class AnyObject implements Serializable {

    private final Object rawValue;
    private final Object anyValue;

    public AnyObject(Object rawValue) {
        this(rawValue, null);
    }

    public AnyObject(Object rawValue, Object anyValue) {
        this.rawValue = rawValue;
        this.anyValue = anyValue;
    }

    public <T> T get(Class<T> cls) {
        return get(cls, null);
    }

    public <T> T get(Class<T> cls, Object def) {
        if (anyValue.getClass().isAssignableFrom(cls)) {
            return (T) anyValue;
        } else if (def != null) {
            return (T) def;
        }
        return null;
    }

    public String stringValue() {
        return stringValue(null);
    }

    public String stringValue(String def) {
        return get(String.class, def);
    }

    public Integer intValue() {
        return intValue(null);
    }

    public Integer intValue(Integer def) {
        return get(Integer.class, def);
    }

    public Double doubleValue() {
        return doubleValue(0D);
    }

    public Double doubleValue(Double def) {
        if (anyValue.getClass().isAssignableFrom(Double.class)) {
            return (Double) anyValue;
        } else if (anyValue.getClass() == BigDecimal.class) {
            return ((BigDecimal) anyValue).doubleValue();
        } else {
            return def;
        }
    }

    public Float floatValue() {
        return floatValue(0F);
    }

    public Float floatValue(Float def) {
        if (anyValue.getClass().isAssignableFrom(Float.class)) {
            return (Float) anyValue;
        } else if (anyValue.getClass() == BigDecimal.class) {
            return ((BigDecimal) anyValue).floatValue();
        } else {
            return def;
        }
    }

    public Object getRawValue() {
        return rawValue;
    }

    public Object getAnyValue() {
        return anyValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        AnyObject anyObject = (AnyObject) obj;
        if (Objects.equals(rawValue, anyObject.rawValue) && Objects.equals(anyValue, anyObject.anyValue)) {
            return true;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "AnyObject{" +
                "rawValue=" + rawValue +
                ", anyValue=" + anyValue +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawValue, anyValue);
    }
}
