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
public class Grade extends BaseState {
    @Property
    private int studentId;

    @Property
    private boolean graduated;

    public static Grade fromJSONString(byte[] bytes) {
        return fromJSONString(Grade.class, bytes);
    }
    public static Grade fromJSONString(String raw) {
        return fromJSONString(Grade.class, raw);
    }
}

