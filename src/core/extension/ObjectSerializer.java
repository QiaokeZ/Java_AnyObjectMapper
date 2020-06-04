package core.extension;

public class ObjectSerializer extends ObjectSerializer_Private {

    public static final int DecodeCodingkeys = 0;
    public static final int DecodeClassInArrayKeys = 1;

    public Promise<ObjectSerializer> set(int key, Object value) {
        switch (key) {
            case DecodeCodingkeys:
                return setDecodeCodingkeys(value);
            case DecodeClassInArrayKeys:
                return setDecodeClassInArrayKeys(value);
        }
        return null;
    }

    public <T> T get(int key) {
        switch (key) {
            case DecodeCodingkeys:
                return (T) getDecodeCodingkeys();
            case DecodeClassInArrayKeys:
                return (T) getDecodeClassInArrayKeys();
        }
        return null;
    }
}


