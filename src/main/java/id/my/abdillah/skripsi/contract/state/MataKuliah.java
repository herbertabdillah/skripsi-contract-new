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
public class MataKuliah extends BaseState {
    @Property
    private String programStudiId;

    @Property
    private String nama;

    @Property
    private int jumlahSks;

    public static MataKuliah fromJSONString(byte[] bytes) {
        return fromJSONString(MataKuliah.class, bytes);
    }
    public static MataKuliah fromJSONString(String raw) {
        return fromJSONString(MataKuliah.class, raw);
    }
}