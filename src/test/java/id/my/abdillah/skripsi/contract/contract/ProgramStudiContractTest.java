package id.my.abdillah.skripsi.contract.contract;


import id.my.abdillah.skripsi.contract.state.BaseState;
import id.my.abdillah.skripsi.contract.state.ProgramStudi;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public final class ProgramStudiContractTest {
    @Test
    public void tambahProgramStudi() throws IOException {
        ProgramStudiContract programStudiContract = new ProgramStudiContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        String nama = "Teknik Informatika";
        String fakultas = "Sains dan Teknologi";
        String kepalaProgramStudiId = "uinjkt.dosen.1";
        String programStudiId = "uinjkt.prodi.1";

        String programStudiJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/ProgramStudi01.json"), BaseState.CHARSET);
        String programStudiJson = ProgramStudi.fromJSONString(programStudiJsonRaw).toJsonString();

        programStudiContract.tambahProgramStudi(ctx, programStudiId, nama, fakultas, kepalaProgramStudiId);
        verify(stub).putState(programStudiId, programStudiJson.getBytes(UTF_8));
    }

    @Test
    public void lihatProgramStudi() throws IOException {
        ProgramStudiContract programStudiContract = new ProgramStudiContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        String programStudiId = "uinjkt.prodi.1";
        String programStudiJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/ProgramStudi01.json"), BaseState.CHARSET);

        when(stub.getState(programStudiId)).thenReturn(programStudiJsonRaw.getBytes(UTF_8));
        ProgramStudi programStudi = ProgramStudi.fromJSONString(programStudiJsonRaw);

        assertEquals(programStudiContract.lihatProgramStudi(ctx, programStudiId), programStudi);
    }
}

