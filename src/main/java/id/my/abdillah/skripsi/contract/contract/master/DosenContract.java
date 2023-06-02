package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseContract;
import id.my.abdillah.skripsi.contract.state.Dosen;
import id.my.abdillah.skripsi.contract.state.Krs;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

@Contract(name = "DosenContract")
@Default
public class DosenContract extends BaseContract implements ContractInterface {
    public DosenContract(){}
    @Transaction
    public void insert(Context ctx, String id, String nama, String nik) {
        Dosen dosen = Dosen.builder().nama(nama).nik(nik).build();

        putState(ctx, id, dosen);
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Dosen get(Context ctx, String id) {
        return getState(ctx, id, Dosen.class);
    }
}