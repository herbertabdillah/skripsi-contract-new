package id.my.abdillah.skripsi.contract.state;

import lombok.*;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class Dosen extends BaseState {
    @Property
    private String nama;

    @Property
    private String nik;

    public static Dosen fromJSONString(byte[] bytes) {
        return fromJSONString(Dosen.class, bytes);
    }
    public static Dosen fromJSONString(String raw) {
        return fromJSONString(Dosen.class, raw);
    }
}