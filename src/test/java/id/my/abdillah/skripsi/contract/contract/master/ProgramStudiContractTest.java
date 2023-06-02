package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseTest;
import id.my.abdillah.skripsi.contract.state.Fakultas;
import id.my.abdillah.skripsi.contract.state.ProgramStudi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("unused")
public final class ProgramStudiContractTest extends BaseTest {
    private ProgramStudiContract contract;
    private String stateJson = readFile("/master/ProgramStudi01.json");

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        contract = new ProgramStudiContract();
    }

    @Test
    public void insert() {
        verifyPutState("ProgramStudi.1", stateJson);

        contract.insert(ctx, "1", "Teknik Informatika", "1");
    }

    @Test
    public void get() {
        whenGetState("ProgramStudi.1").thenReturn(stateJson);
        ProgramStudi programStudi = ProgramStudi.fromJSONString(stateJson);

        assertEquals(contract.get(ctx, "1"), programStudi);
    }
}
