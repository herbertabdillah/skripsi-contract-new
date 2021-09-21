package id.my.abdillah.skripsi.contract.contract;

import id.my.abdillah.skripsi.contract.state.Dosen;
import id.my.abdillah.skripsi.contract.state.Krs;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

@Contract(name = "DosenContract")
@Default
public class DosenContract implements ContractInterface{
    public DosenContract(){}
    @Transaction
    public void tambahDosen(Context ctx,
                          String dosenId
    ) {
        Dosen dosen = new Dosen();
        dosen.setAktif(true);

        ctx.getStub().putState(dosenId, dosen.getJsonStringBytes());
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Dosen lihatDosen(Context ctx, String dosenId) {
        Dosen dosen = Dosen.fromJSONString(ctx.getStub().getState(dosenId));

        return dosen;
    }
}