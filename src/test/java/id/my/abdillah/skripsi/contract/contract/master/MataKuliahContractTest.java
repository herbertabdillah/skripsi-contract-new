package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseTest;
import id.my.abdillah.skripsi.contract.state.MataKuliah;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public final class MataKuliahContractTest extends BaseTest {
    private MataKuliahContract mataKuliahContract;

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        mataKuliahContract = new MataKuliahContract();
    }

    @Test
    public void tambahMataKuliah() {
        String mataKuliahJson = MataKuliah.fromJSONString(readFile("/MataKuliah01.json")).toJsonString();

        verifyPutState(stub, "MataKuliah.1", mataKuliahJson);

        mataKuliahContract.tambahMataKuliah(ctx, "1", "2", "Dasar dasar pemrograman", 6);
    }

    @Test
    public void lihatMataKuliah() {
        when(stub.getState("MataKuliah.1")).thenReturn(readFileBytes("/MataKuliah01.json"));
        MataKuliah mataKuliah = MataKuliah.fromJSONString(readFile("/MataKuliah01.json"));

        assertEquals(mataKuliahContract.lihatMataKuliah(ctx, "1"), mataKuliah);
    }
}
