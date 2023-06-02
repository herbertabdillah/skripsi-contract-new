package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseContract;
import id.my.abdillah.skripsi.contract.state.Fakultas;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

import java.util.logging.Logger;

@Contract(name = "PerkuliahanContract")
@Default
public class FakultasContract extends BaseContract implements ContractInterface{
    private final static Logger LOG = Logger.getLogger(FakultasContract.class.getName());
    public FakultasContract(){}
    @Transaction
    public void insert(Context ctx,
                       String id,
                       String nama
    ) {
        Fakultas fakultas = Fakultas.builder()
                .nama(nama)
                .build();

        putState(ctx, id, fakultas);
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Fakultas get(Context ctx, String id) {
        return getState(ctx, id, Fakultas.class);
    }
}
