package work.hampster.user.util;

import work.hampster.model.User;

import java.lang.reflect.Method;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

public class UserJson {
//    public static Gson getGson() {
//        GsonBuilder gb = new GsonBuilder();
//        gb.serializeNulls();
//        Gson g = gb.create();
//        return g;
//    }

    public static Object fromJsonToObject(Class z, LinkedHashMap json) {
        Object temp = null;
        try {
            temp = z.newInstance();
            for (Method i : User.class.getDeclaredMethods()) {
                String parm = i.getName().substring(3);
                parm = Character.toLowerCase(parm.charAt(0)) + parm.substring(1);
                if (i.getName().startsWith("set") && json.get(parm) != null && (json.get(parm) != ""))
                    i.invoke(temp, trueType(json.get(parm).toString(), i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    private static Object trueType(String value, Method i) {
        Object o = value;
        Class c = i.getReturnType() == void.class ? i.getParameterTypes()[0] : i.getReturnType();
        if (value == "") o = null;
        else {
            if (c == Long.class || c == long.class) o = Long.parseLong(value);
            else if (c == Integer.class || c == int.class) o = Integer.parseInt(value);
            else if (c == Date.class) {
                System.out.println("in");
                try {
                    o = new Date(Integer.parseInt(value));
                } catch (Exception e) {
                    SimpleDateFormat simple = new SimpleDateFormat("MMM dd, yyyy");
                    try {
                        o = new Date(simple.parse(value).getTime());
                    } catch (ParseException ex) {
                        o = new Date(0);
                    }
                }
                System.out.println(o);
            }
        }
        return o;
    }

}
