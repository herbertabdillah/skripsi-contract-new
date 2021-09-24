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
public class IjazahLog extends BaseState {
    @Property
    private ArrayList<String> kuliahId;


    public static IjazahLog fromJSONString(byte[] bytes) {
        return fromJSONString(IjazahLog.class, bytes);
    }
    public static IjazahLog fromJSONString(String raw) {
        return fromJSONString(IjazahLog.class, raw);
    }
}

