package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseTest;
import id.my.abdillah.skripsi.contract.state.Mahasiswa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("unused")
public final class MahasiswaContractTest extends BaseTest {
    private MahasiswaContract contract;
    private String stateJson = readFile("/master/Mahasiswa01.json");

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        contract = new MahasiswaContract();
    }

    @Test
    public void insert() {
        verifyPutState("Mahasiswa.1", stateJson);

        contract.insert(ctx, "1", "Herbert Abdillah", "11170910000046", "1",
                2017, "AKTIF", "1");
    }

    @Test
    public void get() {
        whenGetState("Mahasiswa.1").thenReturn(stateJson);
        Mahasiswa mahasiswa = Mahasiswa.fromJSONString(stateJson);

        assertEquals(contract.get(ctx, "1"), mahasiswa);
    }
}
