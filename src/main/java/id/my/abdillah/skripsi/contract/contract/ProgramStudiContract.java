package id.my.abdillah.skripsi.contract.contract;

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
                                   String programStudiId,
                                   String nama,
                                   String fakultas,
                                   String kepalaProgramStudiId
    ) {
        ProgramStudi programStudi = new ProgramStudi();
        programStudi.setKepalaProgramStudiId(kepalaProgramStudiId);
        programStudi.setFakultas(fakultas);
        programStudi.setNama(nama);

        ctx.getStub().putState(programStudiId, programStudi.getJsonStringBytes());
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public ProgramStudi lihatProgramStudi(Context ctx, String programStudiId) {
        ProgramStudi programStudi = ProgramStudi.fromJSONString(ctx.getStub().getState(programStudiId));

        return programStudi;
    }
}