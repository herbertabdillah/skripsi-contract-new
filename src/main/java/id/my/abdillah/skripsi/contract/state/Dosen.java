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
public class Dosen extends BaseState {
    @Property
    private String name;

    @Property
    private String nik;

    public static Dosen fromJSONString(byte[] bytes) {
        return fromJSONString(Dosen.class, bytes);
    }
    public static Dosen fromJSONString(String raw) {
        return fromJSONString(Dosen.class, raw);
    }
}