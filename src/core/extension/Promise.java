package core.extension;

public class Promise<T> {

    public T self;
    public Object value;

    public Promise(T self, Object value) {
        this.self = self;
        this.value = value;
    }
}
