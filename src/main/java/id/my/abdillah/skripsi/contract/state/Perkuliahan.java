package id.my.abdillah.skripsi.contract.state;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Perkuliahan extends BaseState {
    @Property
    private String mataKuliahId;

    @Property
    private int tahun;

    @Property
    private int semester;

    @Property
    private String dosenId;

    public static Perkuliahan fromJSONString(byte[] bytes) {
        return fromJSONString(Perkuliahan.class, bytes);
    }
    public static Perkuliahan fromJSONString(String raw) {
        return fromJSONString(Perkuliahan.class, raw);
    }
}