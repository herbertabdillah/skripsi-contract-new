package id.my.abdillah.skripsi.contract.contract;

import id.my.abdillah.skripsi.contract.state.Krs;
import id.my.abdillah.skripsi.contract.state.Perkuliahan;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;

@Contract(name = "PerkuliahanContract")
@Default
public class PerkuliahanContract implements ContractInterface{
    public PerkuliahanContract(){}
    @Transaction
    public void tambahPerkuliahan(Context ctx,
                                  String perkuliahanId,
                                  String nama,
                                  String kode,
                                  String programStudiId,
                                  String dosenId,
                                  String tahunAjaran,
                                  int semester,
                                  int jumlahSks
    ) {

        Perkuliahan perkuliahan = new Perkuliahan();
        perkuliahan.setSemester(semester);
        perkuliahan.setDosenId(dosenId);
        perkuliahan.setJumlahSks(jumlahSks);
        perkuliahan.setProgramStudiId(programStudiId);
        perkuliahan.setKode(kode);
        perkuliahan.setNama(nama);
        perkuliahan.setTahunAjaran(tahunAjaran);

        ctx.getStub().putState(perkuliahanId, perkuliahan.getJsonStringBytes());
    }
    @Transaction
    public void ajukanKrs(Context ctx,
                          String krsId,
                          String mahasiswaId,
                          int semester,
                          String kuliahIdJson
    ) {

        Krs krs = new Krs();

        krs.setMahasiswaId(mahasiswaId);
        krs.parseKuliahId(kuliahIdJson);
        krs.setSemester(semester);
        krs.setDisetujuiDosenPa(false);
//        krs.setJumlahSks(jumlahSks);

        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
//        String date = "29/6/2021";

        krs.setTanggalDiajukan(date);
        krs.setTanggalDisetujui("");

        ctx.getStub().putState(krsId, krs.getJsonStringBytes());
    }

    @Transaction
    public void setujuiKrs(Context ctx,
                           String krsId) {
        Krs krs = Krs.fromJSONString(ctx.getStub().getState(krsId));
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
//        String date = "30/6/2021";
        krs.setTanggalDisetujui(date);
        krs.setDisetujuiDosenPa(true);
        ctx.getStub().putState(krsId, krs.getJsonStringBytes());
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Krs lihatKrs(Context ctx, String krsId) {
        Krs krs = Krs.fromJSONString(ctx.getStub().getState(krsId));

        return krs;
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Perkuliahan lihatPerkuliahan(Context ctx, String perkuliahanId) {
        Perkuliahan perkuliahan = Perkuliahan.fromJSONString(ctx.getStub().getState(perkuliahanId));

        return perkuliahan;
    }
}
