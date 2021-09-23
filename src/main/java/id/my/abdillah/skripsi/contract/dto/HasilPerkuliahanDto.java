package id.my.abdillah.skripsi.contract.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HasilPerkuliahanDto {
    private String kuliahId;
    private double nilai;
    private int sks;

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

    public static List<HasilPerkuliahanDto> fromJSONString(String json) {
        ObjectMapper om = new ObjectMapper();
        ArrayList<HasilPerkuliahanDto> t = new ArrayList<>();
        try {
            t = om.readValue(json, t.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }
}
