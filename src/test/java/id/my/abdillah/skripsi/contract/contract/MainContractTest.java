package id.my.abdillah.skripsi.contract.contract;


import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.Filter;
import id.my.abdillah.skripsi.contract.contract.MainContract;
import id.my.abdillah.skripsi.contract.model.Krs;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.json.JSONObject;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public final class MainContractTest {
    @Test
    public void ajukanKrs() {
        MainContract mainContract = new MainContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        String mahasiswaId = "m1";
        String dosenPaId = "d1";
        String kuliahIdJson = "[\"k1\", \"k2\"]";
        int semester = 1;
        String krsId = "krs." + mahasiswaId + "." + Integer.toString(semester);

        String krsJson = "{" +
                "\"jumlahSks\":6," +
                "\"dosenPaId\":\"d1\"," +
                "\"semester\":1," +
                "\"disetujuiDosenPa\":false," +
                "\"mahasiswaId\":\"m1\"," +
                "\"tanggalDiajukan\":\"29/6/2021\"," +
                "\"kuliahId\":[\"k1\", \"k2\"]," +
                "\"tanggalDisetujui\":\"\"" +
                "}";
//        JSONObject jo = new JSONObject(krsJson);
        String jo2 = Krs.fromJSONString(krsJson).toJsonString();
//        System.out.println(jo2);
//        System.out.println(jo2.getBytes(UTF_8));
        mainContract.ajukanKrs(ctx, mahasiswaId, dosenPaId, kuliahIdJson);
        verify(stub).putState(krsId, jo2.getBytes(UTF_8));
    }

    @Test
    public void setujuiKrs() {
        MainContract mainContract = new MainContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);
        String mahasiswaId = "m1";
        String dosenPaId = "d1";
        String kuliahIdJson = "[\"k1\", \"k2\"]";
        int semester = 1;
        String krsId = "krs." + mahasiswaId + "." + Integer.toString(semester);

        String krsJson = "{" +
                "\"dosenPaId\":\"d1\"," +
                "\"kuliahId\":[\"k1\", \"k2\"]," +
                "\"semester\":1," +
                "\"jumlahSks\":6," +
                "\"disetujuiDosenPa\":false," +
                "\"tanggalDiajukan\":\"29/6/2021\"," +
                "\"tanggalDisetujui\":\"\"," +
                "\"mahasiswaId\":\"m1\"" +
                "}";
        String krsJson2 = "{" +
                "\"dosenPaId\":\"d1\"," +
                "\"kuliahId\":[\"k1\", \"k2\"]," +
                "\"semester\":1," +
                "\"jumlahSks\":6," +
                "\"disetujuiDosenPa\":true," +
                "\"tanggalDiajukan\":\"29/6/2021\"," +
                "\"tanggalDisetujui\":\"30/6/2021\"," +
                "\"mahasiswaId\":\"m1\"" +
                "}";
//        JSONObject jo = new JSONObject(krsJson2);
//        String jo2 = jo.toString();
        String jo2 = Krs.fromJSONString(krsJson2).toJsonString();
        when(stub.getState(krsId)).thenReturn(krsJson.getBytes(UTF_8));
        mainContract.setujuiKrs(ctx, krsId);
        verify(stub).putState(krsId, jo2.getBytes(UTF_8));
    }

    @Test
    public void lihatKrs() {
        MainContract mainContract = new MainContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);
        String mahasiswaId = "m1";
        String dosenPaId = "d1";
        String kuliahIdJson = "[\"k1\", \"k2\"]";
        int semester = 1;
        String krsId = "krs." + mahasiswaId + "." + Integer.toString(semester);

        String krsJson = "{" +
                "\"dosenPaId\":\"d1\"," +
                "\"kuliahId\":[\"k1\", \"k2\"]," +
                "\"semester\":1," +
                "\"jumlahSks\":6," +
                "\"disetujuiDosenPa\":false," +
                "\"tanggalDiajukan\":\"29/6/2021\"," +
                "\"tanggalDisetujui\":\"30/6/2021\"," +
                "\"mahasiswaId\":\"m1\"" +
                "}";
//        JSONObject jo = new JSONObject(krsJson);
//        String jo2 = jo.toString();
        when(stub.getState(krsId)).thenReturn(krsJson.getBytes(UTF_8));
        Krs krs = Krs.fromJSONString(krsJson);
        assertEquals(mainContract.lihatKrs(ctx, krsId), krs);
    }
}

