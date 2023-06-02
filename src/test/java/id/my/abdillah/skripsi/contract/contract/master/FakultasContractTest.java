package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseTest;
import id.my.abdillah.skripsi.contract.state.Fakultas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("unused")
public final class FakultasContractTest extends BaseTest {
    private FakultasContract contract;
    private String stateJson = readFile("/master/Fakultas01.json");

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        contract = new FakultasContract();
    }

    @Test
    public void insert() {
        verifyPutState("Fakultas.1", stateJson);

        contract.insert(ctx, "1", "Sains dan Teknologi");
    }

    @Test
    public void get() {
        whenGetState("Fakultas.1").thenReturn(stateJson);
        Fakultas fakultas = Fakultas.fromJSONString(stateJson);

        assertEquals(contract.get(ctx, "1"), fakultas);
    }
}
