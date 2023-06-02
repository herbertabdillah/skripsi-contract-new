package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseTest;
import id.my.abdillah.skripsi.contract.state.Dosen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("unused")
public final class DosenContractTest extends BaseTest {
    private DosenContract contract;
    private String stateJson = readFile("/master/Dosen01.json");

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        contract = new DosenContract();
    }

    @Test
    public void insert() {
        verifyPutState("Dosen.1", stateJson);

        contract.insert(ctx, "1", "Ken Thompson", "1337");
    }

    @Test
    public void get() {
        whenGetState("Dosen.1").thenReturn(stateJson);
        Dosen dosen = Dosen.fromJSONString(stateJson);

        assertEquals(contract.get(ctx, "1"), dosen);
    }
}
