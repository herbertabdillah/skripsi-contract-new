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
    private String akunId;

    @Property
    private String mahasiswaId;

    public static IjazahAkses fromJSONString(byte[] bytes) {
        return fromJSONString(IjazahAkses.class, bytes);
    }
    public static IjazahAkses fromJSONString(String raw) {
        return fromJSONString(IjazahAkses.class, raw);
    }
}

