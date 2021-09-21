package id.my.abdillah.skripsi.contract.contract;


import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import id.my.abdillah.skripsi.contract.state.BaseState;
import id.my.abdillah.skripsi.contract.state.Krs;
import id.my.abdillah.skripsi.contract.state.Perkuliahan;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public final class PerkuliahanContractTest {
    @Test
    public void tambahPerkuliahan() throws IOException {
        PerkuliahanContract perkuliahanContract = new PerkuliahanContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        String perkuliahanId = "uinjkt.fakultas.123qwe.programstudi.123qwe.perkuliahan.123qwe";
        String programStudiId = "uinjkt.fakultas.123qwe.programstudi.123qwe";
        String nama = "Dasar dasar pemrograman";
        String kode = "IF001";
        String dosenId = "uinjkt.dosen.123qwe";
        String tahunAjaran = "2021/2022";
        int semester = 1;
        int jumlahSks = 6;

        String perkuliahanJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/Perkuliahan01.json"), BaseState.CHARSET);
        String perkuliahanJson = Perkuliahan.fromJSONString(perkuliahanJsonRaw).toJsonString();

        perkuliahanContract.tambahPerkuliahan(ctx, perkuliahanId, nama, kode, programStudiId, dosenId, tahunAjaran, semester, jumlahSks);
        verify(stub).putState(perkuliahanId, perkuliahanJson.getBytes(UTF_8));
    }

    @Test
    public void ajukanKrs() throws IOException {
        PerkuliahanContract perkuliahanContract = new PerkuliahanContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        String mahasiswaId = "m1";
        String kuliahIdJson = "[\"k1\", \"k2\"]";
        int semester = 1;
        String krsId = "krs." + mahasiswaId + "." + Integer.toString(semester);

        String krsJson = IOUtils.toString(this.getClass().getResourceAsStream("/Krs02.json"), BaseState.CHARSET);

        String jo2 = Krs.fromJSONString(krsJson).toJsonString();
//        perkuliahanContract.ajukanKrs(ctx, mahasiswaId, kuliahIdJson);
        verify(stub).putState(krsId, jo2.getBytes(UTF_8));
    }

    @Test
    public void setujuiKrs() throws IOException {
        PerkuliahanContract perkuliahanContract = new PerkuliahanContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);
        String mahasiswaId = "m1";
        int semester = 1;
        String krsId = "krs." + mahasiswaId + "." + Integer.toString(semester);

        String krsJson = IOUtils.toString(this.getClass().getResourceAsStream("/Krs02.json"), BaseState.CHARSET);

        String krsJson2 = IOUtils.toString(this.getClass().getResourceAsStream("/Krs01.json"), BaseState.CHARSET);

        String jo2 = Krs.fromJSONString(krsJson2).toJsonString();
        when(stub.getState(krsId)).thenReturn(krsJson.getBytes(UTF_8));
        perkuliahanContract.setujuiKrs(ctx, krsId);
        verify(stub).putState(krsId, jo2.getBytes(UTF_8));
    }

    @Test
    public void lihatKrs() throws IOException {
        PerkuliahanContract perkuliahanContract = new PerkuliahanContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);
        String mahasiswaId = "m1";
        int semester = 1;
        String krsId = "krs." + mahasiswaId + "." + Integer.toString(semester);

        String krsJson = IOUtils.toString(this.getClass().getResourceAsStream("/Krs02.json"), BaseState.CHARSET);

        when(stub.getState(krsId)).thenReturn(krsJson.getBytes(UTF_8));
        Krs krs = Krs.fromJSONString(krsJson);
        assertEquals(perkuliahanContract.lihatKrs(ctx, krsId), krs);
    }
}

