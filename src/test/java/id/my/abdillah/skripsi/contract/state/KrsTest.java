package id.my.abdillah.skripsi.contract.state;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class KrsTest {
    @Test
    public void serialization() throws IOException {
        String krsJson = IOUtils.toString(this.getClass().getResourceAsStream("/Krs01.json"), BaseState.CHARSET);
        Krs krs = Krs.fromJSONString(krsJson);

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
