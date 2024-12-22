package work.hampster.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
    public static Gson getGson() {
        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        Gson g = gb.create();
        return g;
    }

    public static String toJson(Object o) {
        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        Gson g = gb.create();
        return g.toJson(o);
    }

}
