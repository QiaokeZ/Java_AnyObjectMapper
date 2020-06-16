package core.mapper;

import java.util.Map;

public class Serializer {

    private Map<String, String> decodeCodingkeys;
    private Map<String, Class> decodeClassInArrayKeys;

    public Map<String, String> getDecodeCodingkeys() {
        return decodeCodingkeys;
    }

    public void setDecodeCodingkeys(Map<String, String> decodeCodingkeys) {
        this.decodeCodingkeys = decodeCodingkeys;
    }

    public Map<String, Class> getDecodeClassInArrayKeys() {
        return decodeClassInArrayKeys;
    }

    public void setDecodeClassInArrayKeys(Map<String, Class> decodeClassInArrayKeys) {
        this.decodeClassInArrayKeys = decodeClassInArrayKeys;
    }
}


