package core.extension;

import java.io.Serializable;
import java.util.Objects;

public class AnyObject implements Serializable{

    private final Object rawValue;
    private final Object anyValue;

    public AnyObject(Object rawValue, Object anyValue) {
        this.rawValue = rawValue;
        this.anyValue = anyValue;
    }

    public <T> T get(Class<T> cls) {
        if (anyValue.getClass().isAssignableFrom(cls)) {
            return (T) anyValue;
        }
        return null;
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
