package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.state.Dosen;
import id.my.abdillah.skripsi.contract.state.ProgramStudi;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

@Contract(name = "ProgramStudiContract")
@Default
public class ProgramStudiContract implements ContractInterface{
    public ProgramStudiContract(){}
    @Transaction
    public void tambahProgramStudi(Context ctx,
                                   String id,
                                   String nama,
                                   String fakultasId
    ) {
        ProgramStudi programStudi = new ProgramStudi();
        programStudi.setNama(nama);
        programStudi.setFakultasId(fakultasId);

        ctx.getStub().putState(id, programStudi.getJsonStringBytes());
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public ProgramStudi lihatProgramStudi(Context ctx, String programStudiId) {
        ProgramStudi programStudi = ProgramStudi.fromJSONString(ctx.getStub().getState(programStudiId));

        return programStudi;
    }
}