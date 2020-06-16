package core.mapper;


import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import core.org.json.JSONArray;
import core.org.json.JSONObject;
import core.org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ObjectMapper {

    @Nullable
    public static <T> T decode(@NotNull Class<?> cls, @NotNull Object source) throws Exception {
        return decode(cls, source, null);
    }

    @Nullable
    public static <T> T decode(@NotNull Class<?> cls, @NotNull Object source, @Nullable ObjectSerializer serializer) throws Exception {
        Object json = validJSONObject(source);
        BeanContext context = BeanContext.get(cls);
        if (json instanceof JSONArray) {
            return decode(context, (JSONArray) json, serializer);
        } else if (json instanceof JSONObject) {
            return decode(context, (JSONObject) json, serializer);
        } else if (json instanceof List) {
            return decode(context, (List) json, serializer);
        } else if (json instanceof Map) {
            return decode(context, (Map) json, serializer);
        }
        return null;
    }

    private static <T> T decode(BeanContext context, JSONArray jsonArray, ObjectSerializer serializer) throws Exception {
        List results = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object object = decode(context.cls, jsonArray.getJSONObject(i), serializer);
            results.add(object);
        }
        return (T) results;
    }

    private static <T> T decode(BeanContext context, List list, ObjectSerializer serializer) throws Exception {
        List results = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object object = decode(context.cls, list.get(i), serializer);
            results.add(object);
        }
        return (T) results;
    }

    private static <T> T decode(BeanContext context, JSONObject jsonObject, ObjectSerializer serializer) throws Exception {
        Object bean = context.cls.newInstance();
        Map<String, String> codingkeys = null;
        if (serializer != null) {
            codingkeys = serializer.getDecodeCodingkeys();
        }
        for (FieldContext field : context.fields) {
            String key = field.name;
            if (codingkeys != null && codingkeys.containsKey(key)) {
                key = codingkeys.get(key);
            }
            if (!jsonObject.isNull(key)) {
                Object value = jsonObject.get(key);
                if (value != null) {
                    Property property = new Property(context, bean, serializer, field, key, value);
                    setObjectValue(property);
                }
            }
        }
        return (T) bean;
    }

    private static <T> T decode(BeanContext context, Map map, ObjectSerializer serializer) throws Exception {
        Object bean = context.cls.newInstance();
        Map<String, String> codingkeys = null;
        if (serializer != null) {
            codingkeys = serializer.getDecodeCodingkeys();
        }
        for (FieldContext field : context.fields) {
            String key = field.name;
            if (codingkeys != null && codingkeys.containsKey(key)) {
                key = codingkeys.get(key);
            }
            Object value = map.get(key);
            if (value != null) {
                Property property = new Property(context, bean, serializer, field, key, value);
                setObjectValue(property);
            }
        }
        return (T) bean;
    }

    private static Object validJSONObject(Object source) throws Exception {
        if (source instanceof String) {
            Object type = new JSONTokener(source.toString()).nextValue();
            if (type instanceof JSONObject) {
                return type;
            } else if (type instanceof JSONArray) {
                return type;
            }
        } else if (source instanceof JSONArray
                || source instanceof JSONObject
                || source instanceof List
                || source instanceof Map) {
            return source;
        }
        return null;
    }

    private static void setObjectValue(Property property) throws Exception {
        if (AnyObject.class.isAssignableFrom(property.fieldContext.cls)) {
            if (property.value instanceof JSONObject) {
                AnyObject object = new AnyObject(property.value, decode(property.beanContext, (JSONObject) property.value, property.serializer));
                property.fieldContext.field.set(property.bean, object);
            } else if (property.value instanceof Map) {
                AnyObject object = new AnyObject(property.value, decode(property.beanContext, (Map) property.value, property.serializer));
                property.fieldContext.field.set(property.bean, object);
            } else if (property.value instanceof JSONArray) {
                AnyObject object = new AnyObject(property.value, decode(property.beanContext, (JSONArray) property.value, property.serializer));
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
    }

    private static class Property {
        BeanContext beanContext;
        Object bean;
        ObjectSerializer serializer;
        FieldContext fieldContext;
        String key;
        Object value;

        public Property(BeanContext beanContext, Object bean, ObjectSerializer serializer, FieldContext fieldContext, String key, Object value) {
            this.beanContext = beanContext;
            this.bean = bean;
            this.serializer = serializer;
            this.fieldContext = fieldContext;
            this.key = key;
            this.value = value;
        }
    }
}
