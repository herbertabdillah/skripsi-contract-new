package id.my.abdillah.skripsi.contract.state;

import id.my.abdillah.skripsi.contract.dto.StatusMahasiswa;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Data
@NoArgsConstructor

public class Mahasiswa extends BaseState {
    @Property
    private String dosenPaId;

    @Property
    private String programStudiId;

    @Property
    private StatusMahasiswa status;

    @Property
    private int tahunMasuk;

    public static Mahasiswa fromJSONString(byte[] bytes) {
        return fromJSONString(Mahasiswa.class, bytes);
    }
    public static Mahasiswa fromJSONString(String raw) {
        return fromJSONString(Mahasiswa.class, raw);
    }
}

