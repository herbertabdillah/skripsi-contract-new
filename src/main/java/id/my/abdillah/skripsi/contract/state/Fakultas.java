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
public class Fakultas extends BaseState {
    @Property
    private String nama;

    public static Fakultas fromJSONString(byte[] bytes) {
        return fromJSONString(Fakultas.class, bytes);
    }
    public static Fakultas fromJSONString(String raw) {
        return fromJSONString(Fakultas.class, raw);
    }
}
