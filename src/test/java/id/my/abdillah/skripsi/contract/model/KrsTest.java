package id.my.abdillah.skripsi.contract.model;


import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;

import id.my.abdillah.skripsi.contract.model.Krs;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public final class KrsTest {
    @Test
    public void serialization() {
        String krsJson = "{" +
                "\"dosenPaId\":\"d1\"," +
                "\"kuliahId\":[\"k1\", \"k2\"]," +
                "\"semester\":1," +
                "\"jumlahSks\":6," +
                "\"disetujuiDosenPa\":true," +
                "\"tanggalDiajukan\":\"29/6/2021\"," +
                "\"tanggalDisetujui\":\"30/6/2021\"," +
                "\"mahasiswaId\":\"m1\"" +
                "}";
        Krs krs = Krs.fromJSONString(krsJson);
        assertEquals(krs.getDosenPaId(), "d1");
        assertEquals(krs.getSemester(), 1);
        assertEquals(krs.getJumlahSks(), 6);
        assertTrue(krs.isDisetujuiDosenPa());
        assertEquals(krs.getTanggalDiajukan(), "29/6/2021");
        assertEquals(krs.getTanggalDisetujui(), "30/6/2021");
        assertEquals(krs.getMahasiswaId(), "m1");
        assertEquals(krs.getKuliahId().get(0), "k1");
        assertEquals(krs.getKuliahId().get(1), "k2");
    }
}
