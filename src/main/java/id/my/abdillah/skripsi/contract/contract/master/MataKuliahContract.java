package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseContract;
import id.my.abdillah.skripsi.contract.state.MataKuliah;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

import java.util.logging.Logger;

@Contract(name = "PerkuliahanContract")
@Default
public class MataKuliahContract extends BaseContract implements ContractInterface{
    private final static Logger LOG = Logger.getLogger(MataKuliahContract.class.getName());
    public MataKuliahContract(){}
    @Transaction
    public void insert(Context ctx,
                       String id,
                       String programStudiId,
                       String nama,
                       int jumlahSks
    ) {
        MataKuliah mataKuliah = MataKuliah.builder()
                .jumlahSks(jumlahSks)
                .programStudiId(programStudiId)
                .nama(nama)
                .build();

        putState(ctx, id, mataKuliah);
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public MataKuliah get(Context ctx, String id) {
        return getState(ctx, id, MataKuliah.class);
    }
}
