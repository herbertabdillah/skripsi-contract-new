package id.my.abdillah.skripsi.contract.contract;


import id.my.abdillah.skripsi.contract.state.BaseState;
import id.my.abdillah.skripsi.contract.state.Dosen;
import id.my.abdillah.skripsi.contract.state.Mahasiswa;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public final class MahasiswaContractTest {
    @Test
    public void tambahMahasiswa() throws IOException {
        MahasiswaContract mahasiswaContract = new MahasiswaContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        String mahasiswaId = "uinjkt.mhs.1";
        String programStudiId = "uinjkt.prodi.1";
        String dosenPaId = "uinjkt.dosen.1";
        String status = "AKTIF";
        int tahunMasuk = 2017;

        String mahasiswaJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/Mahasiswa01.json"), BaseState.CHARSET);
        String mahasiswaJson = Mahasiswa.fromJSONString(mahasiswaJsonRaw).toJsonString();

        mahasiswaContract.tambahMahasiswa(ctx, mahasiswaId, programStudiId, dosenPaId, status, tahunMasuk);
        verify(stub).putState(mahasiswaId, mahasiswaJson.getBytes(UTF_8));
    }

    @Test
    public void lihatMahasiswa() throws IOException {
        MahasiswaContract mahasiswaContract = new MahasiswaContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        String mahasiswaId = "uinjkt.mahasiswa.123qwe";
        String mahasiswaJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/Mahasiswa01.json"), BaseState.CHARSET);

        when(stub.getState(mahasiswaId)).thenReturn(mahasiswaJsonRaw.getBytes(UTF_8));
        Mahasiswa mahasiswa = Mahasiswa.fromJSONString(mahasiswaJsonRaw);

        assertEquals(mahasiswaContract.lihatMahasiswa(ctx, mahasiswaId), mahasiswa);
    }
}

