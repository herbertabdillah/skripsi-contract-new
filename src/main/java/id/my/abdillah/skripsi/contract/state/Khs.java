package id.my.abdillah.skripsi.contract.state;


import id.my.abdillah.skripsi.contract.dto.HasilPerkuliahanDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.ArrayList;

@DataType
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Khs extends BaseState {
    @Property
    private ArrayList<HasilPerkuliahanDto> hasilPerkuliahan;

    @Property
    private double ip;

    @Property
    private String krsId;

    @Property
    private String mahasiswaId;

    public static Khs fromJSONString(byte[] bytes) {
        return fromJSONString(Khs.class, bytes);
    }
    public static Khs fromJSONString(String raw) {
        return fromJSONString(Khs.class, raw);
    }
}

