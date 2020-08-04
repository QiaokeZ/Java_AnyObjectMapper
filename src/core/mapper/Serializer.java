package core.mapper;

import java.util.Map;

public class Serializer {

    private Map<String, String> decodeCodingkeys;
    private Map<String, Class> decodeClassInArrayKeys;
    private Map<String, String> encodeCodingkeys;

    public void setDecodeCodingkeys(Map<String, String> decodeCodingkeys) {
        this.decodeCodingkeys = decodeCodingkeys;
    }

    public Map<String, String> getDecodeCodingkeys() {
        return decodeCodingkeys;
    }


    public void setEncodeCodingkeys(Map<String, String> encodeCodingkeys) {
        this.encodeCodingkeys = encodeCodingkeys;
    }

    public Map<String, String> getEncodeCodingkeys() {
        return encodeCodingkeys;
    }

    public void setDecodeClassInArrayKeys(Map<String, Class> decodeClassInArrayKeys) {
        this.decodeClassInArrayKeys = decodeClassInArrayKeys;
    }

    public Map<String, Class> getDecodeClassInArrayKeys() {
        return decodeClassInArrayKeys;
    }

}


