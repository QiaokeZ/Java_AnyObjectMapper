package core.extension;

import java.util.Map;

class ObjectSerializer_Private {

    protected Map<String, String> decodeCodingkeys;
    protected Map<String, Class> decodeClassInArrayKeys;

    protected Promise setDecodeCodingkeys(Object value) {
        if (value instanceof Map) {
            decodeCodingkeys = (Map) value;
            return new Promise(this, decodeCodingkeys);
        }
        return null;
    }

    protected Promise setDecodeClassInArrayKeys(Object value) {
        if (value instanceof Map) {
            decodeClassInArrayKeys = (Map) value;
            return new Promise(this, decodeClassInArrayKeys);
        }
        return null;
    }

    protected Map<String, String> getDecodeCodingkeys() {
        return decodeCodingkeys;
    }

    protected Map<String, Class> getDecodeClassInArrayKeys() {
        return decodeClassInArrayKeys;
    }
}
