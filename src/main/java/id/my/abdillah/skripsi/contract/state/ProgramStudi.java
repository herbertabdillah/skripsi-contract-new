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
public class ProgramStudi extends BaseState {
    @Property
    private String nama;

    @Property
    private String fakultasId;

    public static ProgramStudi fromJSONString(byte[] bytes) {
        return fromJSONString(ProgramStudi.class, bytes);
    }
    public static ProgramStudi fromJSONString(String raw) {
        return fromJSONString(ProgramStudi.class, raw);
    }
}

