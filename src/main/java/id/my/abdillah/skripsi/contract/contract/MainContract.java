package id.my.abdillah.skripsi.contract.contract;

import id.my.abdillah.skripsi.contract.model.Krs;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

import static java.nio.charset.StandardCharsets.UTF_8;

@Contract(name = "MainContract")
@Default
public class MainContract implements ContractInterface{
    public MainContract(){}
    @Transaction
    public void ajukanKrs(Context ctx,
                          String mahasiswaId,
                          String dosenPaId,
                          String kuliahIdJson
    ) {

        int semester = 1;
        String krsId = "krs." + mahasiswaId + "." + Integer.toString(semester);
        Krs krs = new Krs();

        krs.setDosenPaId(dosenPaId);
        krs.setMahasiswaId(mahasiswaId);
        krs.parseKuliahId(kuliahIdJson);
        krs.setSemester(semester);
        krs.setDisetujuiDosenPa(false);

//        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String date = "29/6/2021";

        krs.setTanggalDiajukan(date);
        krs.setTanggalDisetujui("");

        String jsonString = krs.toJsonString();
//        System.out.println(jsonString);
        ctx.getStub().putState(krsId, jsonString.getBytes(UTF_8));
    }

    @Transaction
    public void setujuiKrs(Context ctx,
                           String krsId) {
        Krs krs = Krs.fromJSONString(new String(ctx.getStub().getState(krsId), UTF_8));
//        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String date = "30/6/2021";
        krs.setTanggalDisetujui(date);
        krs.setDisetujuiDosenPa(true);
        String jsonString = krs.toJsonString();
        byte[] jsonByte = jsonString.getBytes(UTF_8);
        ctx.getStub().putState(krsId, jsonByte);
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Krs lihatKrs(Context ctx, String krsId) {
        Krs krs = Krs.fromJSONString(new String(ctx.getStub().getState(krsId), UTF_8));

        return krs;
    }
}
