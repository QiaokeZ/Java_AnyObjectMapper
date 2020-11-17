package core.mapper;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import core.org.json.JSONArray;
import core.org.json.JSONObject;
import core.org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class ObjectMapper {

    @Nullable
    public static <T> T decode(@NotNull Class<?> cls, @NotNull Object source) {
        return decode(cls, source, null);
    }

    @Nullable
    public static <T> T decode(@NotNull Class<?> cls, @NotNull Object source, @Nullable Serializer serializer) {
        Object json = validJSONObject(source);
        BeanContext context = BeanContext.get(cls);
        if (json instanceof List) {
            return decode(context, (List) json, serializer);
        } else if (json instanceof Map) {
            return decode(context, (Map) json, serializer);
        }
        return null;
    }

    @Nullable
    public static Map encode(@NotNull Object instance) {
        return encode(instance, null);
    }

    @Nullable
    public static Map encode(@NotNull Object instance, @Nullable Serializer serializer) {
        BeanContext context = BeanContext.get(instance.getClass());
        Map keyValues = new HashMap();
        Map<String, String> codingkeys = null;
        if (serializer != null) {
            codingkeys = serializer.getEncodeCodingkeys();
        }
        try {
            for (Map.Entry<String, FieldContext> entry : context.fields.entrySet()) {
                String key = entry.getKey();
                FieldContext fieldContext = entry.getValue();
                if (codingkeys != null && codingkeys.containsKey(key)) {
                    key = codingkeys.get(key);
                }
                Object value = fieldContext.field.get(instance);
                if (value != null) {
                    encode(keyValues, key, value, serializer);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return keyValues;
    }

    private static Map encode(Map<String, Object> map, Serializer serializer) {
        Map keyValues = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            encode(keyValues, key, value, serializer);
        }
        return keyValues;
    }

    private static Object encode(List list, Serializer serializer) {
        List values = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object value = list.get(i);
            if (value instanceof Number) {
                values.add(value);
            } else if (value instanceof String) {
                values.add(value);
            } else if (value instanceof Map) {
                values.add(encode((Map) value, serializer));
            } else if (value instanceof List) {
                values.add(encode((List) value, serializer));
            } else if (value instanceof AnyObject) {
                values.add(encode((AnyObject) value, serializer));
            } else if (value.getClass().getClassLoader() != null) {
                values.add(encode(value, serializer));
            }
        }
        return values;
    }

    private static Object encode(AnyObject anyObject, Serializer serializer) {
        Object value = anyObject.getRawValue();
        if (value != null) {
            if (value instanceof Number) {
                return value;
            } else if (value instanceof String) {
                return value;
            } else if (value instanceof Map) {
                return encode((Map) value, serializer);
            } else if (value instanceof List) {
                return encode((List) value, serializer);
            } else if (value instanceof AnyObject) {
                return encode((AnyObject) value, serializer);
            } else if (value.getClass().getClassLoader() != null) {
                return encode(value, serializer);
            }
        }
        return null;
    }

    private static void encode(Map container, String key, Object value, Serializer serializer) {
        if (value instanceof Number) {
            container.put(key, value);
        } else if (value instanceof String) {
            container.put(key, value);
        } else if (value instanceof Map) {
            container.put(key, encode((Map) value, serializer));
        } else if (value instanceof List) {
            container.put(key, encode((List) value, serializer));
        } else if (value instanceof AnyObject) {
            container.put(key, encode((AnyObject) value, serializer));
        } else if (value.getClass().getClassLoader() != null) {
            container.put(key, encode(value, serializer));
        }
    }

    private static <T> T decode(BeanContext context, List list, Serializer serializer) {
        List results = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object object = decode(context.cls, list.get(i), serializer);
            results.add(object);
        }
        return (T) results;
    }

    private static <T> T decode(BeanContext context, Map map, Serializer serializer) {
        try {
            Object bean = context.cls.newInstance();
            Map<String, String> codingkeys = null;
            if (serializer != null) {
                codingkeys = serializer.getDecodeCodingkeys();
            }
            for (Map.Entry<String, FieldContext> entry : context.fields.entrySet()) {
                String key = entry.getKey();
                FieldContext fieldContext = entry.getValue();
                if (codingkeys != null && codingkeys.containsKey(key)) {
                    key = codingkeys.get(key);
                }
                Object value = map.get(key);
                if (value != null) {
                    Wrapper property = new Wrapper(context, bean, serializer, fieldContext, key, value);
                    setObjectValue(property);
                }
            }
            return (T) bean;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object validJSONObject(Object source) {
        if (source instanceof String) {
            Object type = new JSONTokener(source.toString()).nextValue();
            if (type instanceof JSONObject) {
                return ((JSONObject) type).toMap();
            } else if (type instanceof JSONArray) {
                return ((JSONArray) type).toList();
            }
        } else if (source instanceof List || source instanceof Map) {
            return source;
        }
        return null;
    }

    private static void setObjectValue(Wrapper property) {
        try {
            if (AnyObject.class.isAssignableFrom(property.fieldContext.cls)) {
                if (property.value instanceof Map) {
                    AnyObject object = new AnyObject(property.value, decode(property.beanContext, (Map) property.value, property.serializer));
                    property.fieldContext.field.set(property.bean, object);
                } else if (property.value instanceof List) {
                    AnyObject object = new AnyObject(property.value, decode(property.beanContext, (List) property.value, property.serializer));
                    property.fieldContext.field.set(property.bean, object);
                } else {
                    AnyObject object = new AnyObject(property.value, property.value);
                    property.fieldContext.field.set(property.bean, object);
                }
            } else {
                if (property.value instanceof Number && Number.class.isAssignableFrom(property.fieldContext.cls)) {
                    property.fieldContext.field.set(property.bean, property.value);
                } else if (property.value instanceof String && String.class.isAssignableFrom(property.fieldContext.cls)) {
                    property.fieldContext.field.set(property.bean, property.value);
                } else if (property.value instanceof Map && Map.class.isAssignableFrom(property.fieldContext.cls)) {
                    property.fieldContext.field.set(property.bean, property.value);
                } else if (property.value instanceof JSONObject && Map.class.isAssignableFrom(property.fieldContext.cls)) {
                    property.fieldContext.field.set(property.bean, ((JSONObject) property.value).toMap());
                } else if (property.value instanceof Map && property.fieldContext.cls.getClassLoader() != null) {
                    property.fieldContext.field.set(property.bean, decode(property.fieldContext.cls, property.value, property.serializer));
                } else if (property.value instanceof JSONObject && property.fieldContext.cls.getClassLoader() != null) {
                    property.fieldContext.field.set(property.bean, decode(property.fieldContext.cls, property.value, property.serializer));
                } else if (property.value instanceof List && property.fieldContext.cls.isAssignableFrom(List.class)) {
                    Class<?> cls = null;
                    if (property.serializer != null && property.serializer.getDecodeClassInArrayKeys() != null) {
                        cls = property.serializer.getDecodeClassInArrayKeys().get(property.key);
                    }
                    if (cls != null) {
                        property.fieldContext.field.set(property.bean, decode(cls, property.value, property.serializer));
                    } else {
                        property.fieldContext.field.set(property.bean, property.value);
                    }
                } else if (property.value instanceof JSONArray && property.fieldContext.cls.isAssignableFrom(List.class)) {
                    Class<?> cls = null;
                    if (property.serializer != null && property.serializer.getDecodeClassInArrayKeys() != null) {
                        cls = property.serializer.getDecodeClassInArrayKeys().get(property.key);
                    }
                    if (cls != null) {
                        property.fieldContext.field.set(property.bean, decode(cls, property.value, property.serializer));
                    } else {
                        property.fieldContext.field.set(property.bean, ((JSONArray) property.value).toList());
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static class Wrapper {
        BeanContext beanContext;
        Object bean;
        Serializer serializer;
        FieldContext fieldContext;
        String key;
        Object value;

        public Wrapper(BeanContext beanContext, Object bean, Serializer serializer, FieldContext fieldContext, String key, Object value) {
            this.beanContext = beanContext;
            this.bean = bean;
            this.serializer = serializer;
            this.fieldContext = fieldContext;
            this.key = key;
            this.value = value;
        }
    }
}
