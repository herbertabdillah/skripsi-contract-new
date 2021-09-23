package id.my.abdillah.skripsi.contract.state;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.json.JSONArray;

import java.util.ArrayList;

@DataType
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class IjazahAkses extends BaseState {
    @Property
    private ArrayList<String> kuliahId;

    @Property
    private int semester;

    @Property
    private int jumlahSks;

    @Property
    private boolean disetujuiDosenPa;

    @Property
    private String tanggalDiajukan;

    @Property
    private String tanggalDisetujui;

    @Property
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

    public static IjazahAkses fromJSONString(byte[] bytes) {
        return fromJSONString(IjazahAkses.class, bytes);
    }
    public static IjazahAkses fromJSONString(String raw) {
        return fromJSONString(IjazahAkses.class, raw);
    }
}

