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
public class Attendance extends BaseState {
    @Property
    private int studentId;

    @Property
    private int weekNumber;

    @Property
    private boolean attended;

    public static Attendance fromJSONString(byte[] bytes) {
        return fromJSONString(Attendance.class, bytes);
    }
    public static Attendance fromJSONString(String raw) {
        return fromJSONString(Attendance.class, raw);
    }
}

