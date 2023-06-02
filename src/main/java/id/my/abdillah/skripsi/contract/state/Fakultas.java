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
