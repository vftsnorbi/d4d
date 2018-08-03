package hu.krisztn.entity.util;

        import java.util.HashMap;
        import java.util.Map;

public class ClassCache {

    private static final Map<Class<?>, String> CACHE = new HashMap<>();

    public static String getSimpleName(final Class<?> aClass) {
        return CACHE.computeIfAbsent(aClass, c -> aClass.getSimpleName());
    }

}
