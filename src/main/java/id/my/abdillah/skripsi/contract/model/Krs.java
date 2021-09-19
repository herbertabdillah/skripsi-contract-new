package id.my.abdillah.skripsi.contract.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@DataType()
@Data()
@NoArgsConstructor()
// TODO: 6/30/2021 pake jackson atau gson untuk serialize json otomatis

public class Krs {
    @Property()
    private String dosenPaId;

    @Property()
    private ArrayList<String> kuliahId;

    @Property()
    private int semester;

    @Property()
    private int jumlahSks;

    @Property()
    private boolean disetujuiDosenPa;

    @Property()
    private String tanggalDiajukan;

    @Property()
    private String tanggalDisetujui;

    @Property()
    private String mahasiswaId;

    public void parseKuliahId(String kuliahIdJson) {
        JSONArray ja = new JSONArray(kuliahIdJson);
        this.parseKuliahId(ja);
    }
    public void parseKuliahId(JSONArray ja) {

        ArrayList<String> kuliahId = new ArrayList<>();
        int jumlahKuliah = ja.length();
        int jumlahSks = jumlahKuliah * 3;
        for(int i = 0; i < jumlahKuliah; i++) {
            kuliahId.add(ja.getString(i));
        }
        this.kuliahId = kuliahId;
        this.setJumlahSks(jumlahSks);

    }

    public String toJsonString() {
        ObjectMapper om = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        JSONObject jo = new JSONObject(this);
//        if(this.tanggalDisetujui == null) {
//            jo.put("tanggalDisetujui", JSONObject.NULL);
//        }
//        String jsonString = jo.toString();
        return jsonString;
    }

    public static Krs fromJSONString(String json) {
        ObjectMapper om = new ObjectMapper();
        Krs krs = null;
        try {
            krs = om.readValue(json, Krs.class);
//            krs = om.readValue(json, krs.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONObject krsJo = new JSONObject(json);
//        Krs krs = new Krs();
//        krs.setDisetujuiDosenPa(krsJo.getBoolean("disetujuiDosenPa"));
//        krs.setSemester(krsJo.getInt("semester"));
//        krs.setMahasiswaId(krsJo.getString("mahasiswaId"));
//        krs.setDosenPaId(krsJo.getString("dosenPaId"));
//        krs.setKuliahId(krsJo.getJSONArray("kuliahId"));
//        if(!krsJo.isNull("tanggalDisetujui")) {
//            krs.setTanggalDisetujui(krsJo.getString("tanggalDisetujui"));
//        }
//        krs.setTanggalDiajukan(krsJo.getString("tanggalDiajukan"));

        return krs;
    }
}

