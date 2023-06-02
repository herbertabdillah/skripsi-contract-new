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
public class Ijazah extends BaseState {
    @Property
    private String mahasiswaId;

    @Property
    private double ipk;

    @Property
    private int tahun;

    public static Ijazah fromJSONString(byte[] bytes) {
        return fromJSONString(Ijazah.class, bytes);
    }
    public static Ijazah fromJSONString(String raw) {
        return fromJSONString(Ijazah.class, raw);
    }
}

