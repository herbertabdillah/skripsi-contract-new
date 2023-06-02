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
public class Krs extends BaseState {
    @Property
    private ArrayList<String> kuliahId;

    @Property
    private int semester;

    @Property
    private int tahun;

    @Property
    private String mahasiswaId;

    public static Krs fromJSONString(byte[] bytes) {
        return fromJSONString(Krs.class, bytes);
    }
    public static Krs fromJSONString(String raw) {
        return fromJSONString(Krs.class, raw);
    }
}

