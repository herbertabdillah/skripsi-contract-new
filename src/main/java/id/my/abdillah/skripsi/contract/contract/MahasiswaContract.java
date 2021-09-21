package id.my.abdillah.skripsi.contract.contract;

import id.my.abdillah.skripsi.contract.dto.StatusMahasiswa;
import id.my.abdillah.skripsi.contract.state.Dosen;
import id.my.abdillah.skripsi.contract.state.Krs;
import id.my.abdillah.skripsi.contract.state.Mahasiswa;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

@Contract(name = "MahasiswaContract")
@Default
public class MahasiswaContract implements ContractInterface{
    public MahasiswaContract(){}
    @Transaction
    public void tambahMahasiswa(Context ctx,
                                String mahasiswaId,
                                String dosenPaId,
                                String status,
                                int tahunMasuk
    ) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setDosenPaId(dosenPaId);
        mahasiswa.setStatus(StatusMahasiswa.valueOf(status));
        mahasiswa.setTahunMasuk(tahunMasuk);

        ctx.getStub().putState(mahasiswaId, mahasiswa.getJsonStringBytes());
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Mahasiswa lihatMahasiswa(Context ctx, String mahasiswaId) {
        Mahasiswa mahasiswa = Mahasiswa.fromJSONString(ctx.getStub().getState(mahasiswaId));

        return mahasiswa;
    }
}
