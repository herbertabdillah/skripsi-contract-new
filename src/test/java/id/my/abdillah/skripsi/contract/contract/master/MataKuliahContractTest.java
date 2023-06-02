package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseTest;
import id.my.abdillah.skripsi.contract.state.MataKuliah;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("unused")
public final class MataKuliahContractTest extends BaseTest {
    private MataKuliahContract contract;
    private String stateJson = readFile("/master/MataKuliah01.json");

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        contract = new MataKuliahContract();
    }

    @Test
    public void insert() {
        verifyPutState("MataKuliah.1", stateJson);

        contract.insert(ctx, "1", "2", "Dasar dasar pemrograman", 6);
    }

    @Test
    public void get() {
        whenGetState("MataKuliah.1").thenReturn(stateJson);
        MataKuliah mataKuliah = MataKuliah.fromJSONString(stateJson);

        assertEquals(contract.get(ctx, "1"), mataKuliah);
    }
}
