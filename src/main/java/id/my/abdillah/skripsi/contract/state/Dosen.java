package id.my.abdillah.skripsi.contract.state;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.json.JSONArray;

import java.util.ArrayList;

@DataType
@Data
@NoArgsConstructor

public class Dosen extends BaseState {
    @Property
    private Boolean aktif;

    public static Dosen fromJSONString(byte[] bytes) {
        return fromJSONString(Dosen.class, bytes);
    }
    public static Dosen fromJSONString(String raw) {
        return fromJSONString(Dosen.class, raw);
    }
}