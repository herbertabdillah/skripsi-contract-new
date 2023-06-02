package id.my.abdillah.skripsi.contract.state;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BaseState {
    @JsonIgnore
    private String id;

    @JsonIgnore
    public static Charset CHARSET = StandardCharsets.UTF_8;

    //TODO: Refactor to Basemodel
    public String toJsonString() {
        ObjectMapper om = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static <T extends BaseState> T fromJSONString(Class<T> type, byte[] bytes) {
        return fromJSONString(type, new String(bytes, CHARSET));
    }

    public static <T extends BaseState> T fromJSONString(Class<T> type, String json) {
        ObjectMapper om = new ObjectMapper();
        T t = null;
        try {
            t = om.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONObject krsJo = new JSONObject(json);
        return t;
    }

    @JsonIgnore
    public byte[] getJsonStringBytes() {
        return toJsonString().getBytes(CHARSET);
    }
}
