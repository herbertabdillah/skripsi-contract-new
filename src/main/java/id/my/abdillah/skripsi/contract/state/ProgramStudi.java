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

