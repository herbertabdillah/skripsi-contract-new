package id.my.abdillah.skripsi.contract.contract.master;

import id.my.abdillah.skripsi.contract.contract.BaseContract;
import id.my.abdillah.skripsi.contract.dto.StatusMahasiswa;
import id.my.abdillah.skripsi.contract.state.Mahasiswa;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

@Contract(name = "MahasiswaContract")
@Default
public class MahasiswaContract extends BaseContract implements ContractInterface{
    public MahasiswaContract(){}
    @Transaction
    public void insert(Context ctx,
                       String id,
                       String nama,
                       String nim,
                       String programStudiId,
                       int tahunMasuk,
                       String status,
                       String dosenPembimbingId
    ) {
        Mahasiswa mahasiswa = Mahasiswa.builder()
                        .nama(nama).nim(nim).programStudiId(programStudiId)
                        .tahunMasuk(tahunMasuk).dosenPembimbingId(dosenPembimbingId)
                        .status(StatusMahasiswa.valueOf(status))
                        .build();

        putState(ctx, id, mahasiswa);
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Mahasiswa get(Context ctx, String id) {
        return getState(ctx, id, Mahasiswa.class);
    }
}
