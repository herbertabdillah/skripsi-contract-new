package id.my.abdillah.skripsi.contract.contract._old;


import id.my.abdillah.skripsi.contract.contract.master.DosenContract;
import id.my.abdillah.skripsi.contract.state.BaseState;
import id.my.abdillah.skripsi.contract.state.Dosen;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public final class DosenContractTest {
    @Test
    public void tambahDosen() throws IOException {
        DosenContract dosenContract = new DosenContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        String dosenId = "uinjkt.dosen.asdf123";

        String dosenJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/Dosen01.json"), BaseState.CHARSET);
        String dosenJson = Dosen.fromJSONString(dosenJsonRaw).toJsonString();

        dosenContract.tambahDosen(ctx, dosenId);
        verify(stub).putState(dosenId, dosenJson.getBytes(UTF_8));
    }

    @Test
    public void lihatDosen() throws IOException {
        DosenContract dosenContract = new DosenContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        String dosenId = "uinjkt.dosen.asdf123";
        String dosenJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/Dosen01.json"), BaseState.CHARSET);

        when(stub.getState(dosenId)).thenReturn(dosenJsonRaw.getBytes(UTF_8));
        Dosen dosen = Dosen.fromJSONString(dosenJsonRaw);

        assertEquals(dosenContract.lihatDosen(ctx, dosenId), dosen);
    }
}

