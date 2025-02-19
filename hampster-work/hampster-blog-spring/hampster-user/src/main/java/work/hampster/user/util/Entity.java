package work.hampster.user.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.mock.web.MockHttpServletRequest;

import java.lang.reflect.Method;

public class Entity {

    public static HttpServletRequest fromEntityToRq(Class clazz, Object entity) {
        MockHttpServletRequest rq = new MockHttpServletRequest();
        try {
            for (Method i : clazz.getDeclaredMethods()) {
                String parm = i.getName().substring(3);
                parm = Character.toLowerCase(parm.charAt(0)) + parm.substring(1);
                if (i.getName().startsWith("get") && i.invoke(entity) != null && (i.invoke(entity) != "")) {
                    rq.addParameter(parm, i.invoke(entity).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rq;
    }
}
