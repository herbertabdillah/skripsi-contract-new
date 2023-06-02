package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseContract;
import id.my.abdillah.skripsi.contract.state.ProgramStudi;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

@Contract(name = "ProgramStudiContract")
@Default
public class ProgramStudiContract extends BaseContract implements ContractInterface{
    public ProgramStudiContract(){}
    @Transaction
    public void insert(Context ctx,
                                   String id,
                                   String nama,
                                   String fakultasId
    ) {
        ProgramStudi programStudi = ProgramStudi.builder().nama(nama).fakultasId(fakultasId).build();

        putState(ctx, id, programStudi);
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public ProgramStudi get(Context ctx, String id) {
        return getState(ctx, id, ProgramStudi.class);
    }
}