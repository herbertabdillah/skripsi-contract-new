package id.my.abdillah.skripsi.contract.contract;

import id.my.abdillah.skripsi.contract.state.Krs;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

@Contract(name = "PerkuliahanContract")
@Default
public class IjazahContract implements ContractInterface{
    public IjazahContract(){}
    @Transaction
    public void ajukanKrs(Context ctx,
                          String mahasiswaId,
//                          String dosenPaId,
                          String kuliahIdJson
    ) {

        int semester = 1;
        String krsId = "krs." + mahasiswaId + "." + Integer.toString(semester);
        Krs krs = new Krs();

//        krs.setDosenPaId(dosenPaId);
        krs.setMahasiswaId(mahasiswaId);
        krs.parseKuliahId(kuliahIdJson);
        krs.setSemester(semester);
        krs.setDisetujuiDosenPa(false);

//        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String date = "29/6/2021";

        krs.setTanggalDiajukan(date);
        krs.setTanggalDisetujui("");

        ctx.getStub().putState(krsId, krs.getJsonStringBytes());
    }

    @Transaction
    public void setujuiKrs(Context ctx,
                           String krsId) {
        Krs krs = Krs.fromJSONString(ctx.getStub().getState(krsId));
//        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String date = "30/6/2021";
        krs.setTanggalDisetujui(date);
        krs.setDisetujuiDosenPa(true);
        ctx.getStub().putState(krsId, krs.getJsonStringBytes());
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Krs lihatKrs(Context ctx, String krsId) {
        Krs krs = Krs.fromJSONString(ctx.getStub().getState(krsId));

        return krs;
    }
}
