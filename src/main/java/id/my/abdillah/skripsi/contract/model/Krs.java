package id.my.abdillah.skripsi.contract.model;


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

    public void setKuliahId(String kuliahIdJson) {
        JSONArray ja = new JSONArray(kuliahIdJson);
        this.setKuliahId(ja);
    }
    public void setKuliahId(JSONArray ja) {

        ArrayList<String> kuliahId = new ArrayList<>();
//        JSONArray ja = new JSONArray(kuliahIdJson);
        int jumlahKuliah = ja.length();
        int jumlahSks = jumlahKuliah * 3;
        for(int i = 0; i < jumlahKuliah; i++) {
            kuliahId.add(ja.getString(i));
        }
        this.kuliahId = kuliahId;
        this.setJumlahSks(jumlahSks);

    }

    public String toJsonString() {
        JSONObject jo = new JSONObject(this);
//        if(this.tanggalDisetujui == null) {
//            jo.put("tanggalDisetujui", JSONObject.NULL);
//        }
        String jsonString = jo.toString();
        return jsonString;
    }

    public static Krs fromJSONString(String json) {
        JSONObject krsJo = new JSONObject(json);
        Krs krs = new Krs();
        krs.setDisetujuiDosenPa(krsJo.getBoolean("disetujuiDosenPa"));
        krs.setSemester(krsJo.getInt("semester"));
        krs.setMahasiswaId(krsJo.getString("mahasiswaId"));
        krs.setDosenPaId(krsJo.getString("dosenPaId"));
        krs.setKuliahId(krsJo.getJSONArray("kuliahId"));
        if(!krsJo.isNull("tanggalDisetujui")) {
            krs.setTanggalDisetujui(krsJo.getString("tanggalDisetujui"));
        }
        krs.setTanggalDiajukan(krsJo.getString("tanggalDiajukan"));

        return krs;
    }
}

