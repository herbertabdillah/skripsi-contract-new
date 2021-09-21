package id.my.abdillah.skripsi.contract.state;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Data
@NoArgsConstructor

public class Perkuliahan extends BaseState {
    @Property
    private String programStudiId;

    @Property
    private String kode;

    @Property
    private String nama;

    @Property
    private String dosenId;

    @Property
    private int semester;

    @Property
    private String tahunAjaran;

    @Property
    private int jumlahSks;

    public static Perkuliahan fromJSONString(byte[] bytes) {
        return fromJSONString(Perkuliahan.class, bytes);
    }
    public static Perkuliahan fromJSONString(String raw) {
        return fromJSONString(Perkuliahan.class, raw);
    }
}