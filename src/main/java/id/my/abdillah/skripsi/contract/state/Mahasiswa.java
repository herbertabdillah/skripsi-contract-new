package id.my.abdillah.skripsi.contract.state;

import id.my.abdillah.skripsi.contract.dto.StatusMahasiswa;
import lombok.*;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.ArrayList;

@DataType
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class Mahasiswa extends BaseState {
    @Property
    private String nama;

    @Property
    private String nim;

    @Property
    private String programStudiId;

    @Property
    private int tahunMasuk;

    @Property
    private StatusMahasiswa status;

    @Property
    private String dosenPembimbingId;

    public static Mahasiswa fromJSONString(byte[] bytes) {
        return fromJSONString(Mahasiswa.class, bytes);
    }
    public static Mahasiswa fromJSONString(String raw) {
        return fromJSONString(Mahasiswa.class, raw);
    }
}
