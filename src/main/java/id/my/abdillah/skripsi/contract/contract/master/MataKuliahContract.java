package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseContract;
import id.my.abdillah.skripsi.contract.dto.HasilPerkuliahanDto;
import id.my.abdillah.skripsi.contract.state.Khs;
import id.my.abdillah.skripsi.contract.state.Krs;
import id.my.abdillah.skripsi.contract.state.MataKuliah;
import id.my.abdillah.skripsi.contract.state.Perkuliahan;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Contract(name = "PerkuliahanContract")
@Default
public class MataKuliahContract extends BaseContract implements ContractInterface{
    private final static Logger LOG = Logger.getLogger(MataKuliahContract.class.getName());
    public MataKuliahContract(){}
    @Transaction
    public void tambahMataKuliah(Context ctx,
                                 String id,
                                 String programStudiId,
                                 String nama,
                                 int jumlahSks
    ) {
        MataKuliah mataKuliah = new MataKuliah();
        mataKuliah.setJumlahSks(jumlahSks);
        mataKuliah.setProgramStudiId(programStudiId);
        mataKuliah.setNama(nama);

//        ctx.getStub().putState(id, mataKuliah.getJsonStringBytes());
        putState(ctx, id, mataKuliah);
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public MataKuliah lihatMataKuliah(Context ctx, String id) {
//        Krs krs = Krs.fromJSONString(ctx.getStub().getState(krsId));

//        return krs;
        return getState(ctx, id, MataKuliah.class);
    }
}
