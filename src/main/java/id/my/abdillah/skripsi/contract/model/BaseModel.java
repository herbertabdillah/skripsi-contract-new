package id.my.abdillah.skripsi.contract.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class BaseModel {

    public static <T extends BaseModel> T fromJsonString(Class<T> type, String raw) {
        ObjectMapper om = new ObjectMapper();
        T t = null;

        try {
            t = om.readValue(raw, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }
}
