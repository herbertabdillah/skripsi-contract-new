package id.my.abdillah.skripsi.contract.contract;


import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import id.my.abdillah.skripsi.contract.dto.HasilPerkuliahanDto;
import id.my.abdillah.skripsi.contract.state.BaseState;
import id.my.abdillah.skripsi.contract.state.Khs;
import id.my.abdillah.skripsi.contract.state.Krs;
import id.my.abdillah.skripsi.contract.state.Perkuliahan;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public final class PerkuliahanContractTest {
//    @Test
//    public void tambahPerkuliahan() throws IOException {
//        PerkuliahanContract perkuliahanContract = new PerkuliahanContract();
//        Context ctx = mock(Context.class);
//        ChaincodeStub stub = mock(ChaincodeStub.class);
//        when(ctx.getStub()).thenReturn(stub);
//
//        String perkuliahanId = "uinjkt.kuliah.1";
//        String programStudiId = "uinjkt.prodi.1";
//        String nama = "Dasar dasar pemrograman";
//        String kode = "IF001";
//        String dosenId = "uinjkt.dosen.1";
//        String tahunAjaran = "2021/2022";
//        int semester = 1;
//        int jumlahSks = 6;
//
//        String perkuliahanJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/Perkuliahan01.json"), BaseState.CHARSET);
//        String perkuliahanJson = Perkuliahan.fromJSONString(perkuliahanJsonRaw).toJsonString();
//
//        perkuliahanContract.tambahPerkuliahan(ctx, perkuliahanId, nama, kode, programStudiId, dosenId, tahunAjaran, semester, jumlahSks);
//        verify(stub).putState(perkuliahanId, perkuliahanJson.getBytes(UTF_8));
//    }
//
//    @Test
//    public void ajukanKrs() throws IOException {
//        PerkuliahanContract perkuliahanContract = new PerkuliahanContract();
//        Context ctx = mock(Context.class);
//        ChaincodeStub stub = mock(ChaincodeStub.class);
//        when(ctx.getStub()).thenReturn(stub);
//
//        String kuliahId = "uinjkt.kuliah.1";
//        Perkuliahan perkuliahan = Perkuliahan.fromJSONString(IOUtils.toString(this.getClass().getResourceAsStream("/Perkuliahan01.json"), BaseState.CHARSET));
//        when(ctx.getStub().getState(kuliahId)).thenReturn(perkuliahan.getJsonStringBytes());
//
//        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
//        int semester = 1;
//        String mahasiswaId = "uinjkt.mhs.1";
//        String kuliahIdJson = "[\"uinjkt.kuliah.1\"]";
//        String krsId = "uinjkt.krs.1";
//
//        String krsRawJson = IOUtils.toString(this.getClass().getResourceAsStream("/Krs02.json"), BaseState.CHARSET);
//        Krs krs = Krs.fromJSONString(krsRawJson);
//        krs.setTanggalDiajukan(date);
//        String krsJson = krs.toJsonString();
//
//        perkuliahanContract.ajukanKrs(ctx, krsId, mahasiswaId, semester, kuliahIdJson);
//        verify(stub).putState(krsId, krsJson.getBytes(UTF_8));
//    }
//
//    @Test
//    public void setujuiKrs() throws IOException {
//        PerkuliahanContract perkuliahanContract = new PerkuliahanContract();
//        Context ctx = mock(Context.class);
//        ChaincodeStub stub = mock(ChaincodeStub.class);
//        when(ctx.getStub()).thenReturn(stub);
//        String mahasiswaId = "uinjkt.mhs.1";
//        int semester = 1;
//        String krsId = "uinjkt.krs.1";
//        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
//
//        String krsJsonRawBelumSetuju = IOUtils.toString(this.getClass().getResourceAsStream("/Krs02.json"), BaseState.CHARSET);
//        String krsJsonRawSetuju = IOUtils.toString(this.getClass().getResourceAsStream("/Krs01.json"), BaseState.CHARSET);
//
//        Krs krsSetuju = Krs.fromJSONString(krsJsonRawSetuju);
//        krsSetuju.setTanggalDisetujui(date);
//        String krsJsonSetuju = krsSetuju.toJsonString();
//        Krs krsBelumSetuju = Krs.fromJSONString(krsJsonRawBelumSetuju);
//        String krsJsonBelumSetuju = krsBelumSetuju.toJsonString();
//
////        System.out.println(krsJsonSetuju);
//
//        when(stub.getState(krsId)).thenReturn(krsJsonBelumSetuju.getBytes(UTF_8));
//        String kuliahId = "uinjkt.kuliah.1";
//        String kuliahJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/Perkuliahan01.json"), BaseState.CHARSET);
//        Perkuliahan perkuliahan = Perkuliahan.fromJSONString(kuliahJsonRaw);
//        when(stub.getState(kuliahId)).thenReturn(perkuliahan.getJsonStringBytes());
//        perkuliahanContract.setujuiKrs(ctx, krsId);
//
//        ArrayList<HasilPerkuliahanDto> hasilPerkuliahanDto = new ArrayList<>();
//        for(String k : krsSetuju.getKuliahId()) {
//            Perkuliahan p = Perkuliahan.fromJSONString(ctx.getStub().getState(k));
//            HasilPerkuliahanDto h = new HasilPerkuliahanDto();
//            h.setKuliahId(k);
//            h.setNilai(0);
//            h.setSks(p.getJumlahSks());
//        }
//        Khs khs = new Khs();
//        String khsId = "uinjkt.khs.1";
//        khs.setKrsId(krsId);
//        khs.setSemester(krsSetuju.getSemester());
//        khs.setJumlahSks(krsSetuju.getJumlahSks());
//        khs.setMahasiswaId(krsSetuju.getMahasiswaId());
//
//        String khsJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/Khs02.json"), BaseState.CHARSET);
//        String khsJson = Khs.fromJSONString(khsJsonRaw).toJsonString();
//        verify(stub).putState(krsId, krsJsonSetuju.getBytes(UTF_8));
//        verify(stub).putState(khsId, khsJson.getBytes(UTF_8));
//    }
//
//    @Test
//    public void nilaiKhs() throws IOException {
//        Context ctx = mock(Context.class);
//        ChaincodeStub stub = mock(ChaincodeStub.class);
//        when(ctx.getStub()).thenReturn(stub);
//
//        String khsId = "uinjkt.khs.1";
//
//        String khsJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/Khs02.json"), BaseState.CHARSET);
//        Khs khsSebelumNilai = Khs.fromJSONString(khsJsonRaw);
//
//        when(stub.getState(khsId)).thenReturn(khsSebelumNilai.getJsonStringBytes());
//
//        Khs khs = new Khs();
////        String khsId = "uinjkt.khs.1";
//        String kuliahId = "uinjkt.kuliah.1";
//        double nilai = 3.6;
//
//        PerkuliahanContract  perkuliahanContract = new PerkuliahanContract();
//        perkuliahanContract.nilaiKhs(ctx, khsId, kuliahId, nilai);
//
//        String khsNilaiJsonRaw = IOUtils.toString(this.getClass().getResourceAsStream("/Khs01.json"), BaseState.CHARSET);
//        String khsNilaiJson = Khs.fromJSONString(khsNilaiJsonRaw).toJsonString();
//
//        verify(stub).putState(khsId, khsNilaiJson.getBytes(UTF_8));
//    }
//    @Test
//    public void lihatKrs() throws IOException {
//        PerkuliahanContract perkuliahanContract = new PerkuliahanContract();
//        Context ctx = mock(Context.class);
//        ChaincodeStub stub = mock(ChaincodeStub.class);
//        when(ctx.getStub()).thenReturn(stub);
//        String mahasiswaId = "uinjkt.mhs.1";
//        int semester = 1;
//        String krsId = "uinjkt.krs.1";
//
//        String krsJson = IOUtils.toString(this.getClass().getResourceAsStream("/Krs02.json"), BaseState.CHARSET);
//
//        when(stub.getState(krsId)).thenReturn(krsJson.getBytes(UTF_8));
//        Krs krs = Krs.fromJSONString(krsJson);
//        assertEquals(perkuliahanContract.lihatKrs(ctx, krsId), krs);
//    }
}

