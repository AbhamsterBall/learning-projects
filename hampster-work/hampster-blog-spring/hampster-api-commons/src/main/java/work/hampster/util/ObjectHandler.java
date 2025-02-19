package work.hampster.util;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import work.hampster.model.User;

import java.lang.reflect.Field;
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

    public static void convert(Object ori, Object des) throws IllegalAccessException {
        if (ObjectUtils.isNotNull(ori)) {
            for (Field field : User.class.getDeclaredFields()) {
                if (field.getName().equals("serialVersionUID") || field.getName().equals("merchantCode")) continue;
                field.setAccessible(true);
                if (ObjectUtils.isNotNull(field.get(ori))) {
                    field.set(des, field.get(ori));
                }
            }
        }
    }
}
