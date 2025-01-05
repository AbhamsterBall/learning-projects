package work.hampster.util;

import java.util.Objects;

public class ObjectHandler {
    public static boolean isStrArrContains(String url, String[] EXCLUDE_URL) {
        boolean result = false;
        for (String excludeUrl : EXCLUDE_URL) {
            if (url.contains(excludeUrl)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean isInArr(Object url, Object[] EXCLUDE_URL) {
        boolean result = false;
        for (Object excludeUrl : EXCLUDE_URL) {
            if (Objects.equals(url, excludeUrl)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
