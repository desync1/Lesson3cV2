import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Serializer {
    public static Map<String, Object> serialize(Object obj) throws IllegalAccessException {
        Map<String, Object> result = new HashMap<>();
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                if (Modifier.isPrivate(field.getModifiers())) {
                    field.setAccessible(true);
                }
                result.put(field.getName(), field.get(obj));
            }
        }
        return result;
    }

    public static <T> T deserialize(Map<String, Object> map, Class<T> cls) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        T result = (T) cls.newInstance();
        Set<String> set = map.keySet();
        for (String names : set) {
            Field field = cls.getDeclaredField(names);
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }
            if (field.isAnnotationPresent(Save.class)) {
                field.set(result, map.get(names));
            }
        }
        return result;
    }
}
