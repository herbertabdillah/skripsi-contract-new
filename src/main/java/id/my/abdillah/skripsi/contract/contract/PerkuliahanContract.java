package id.my.abdillah.skripsi.contract.contract;

import id.my.abdillah.skripsi.contract.dto.HasilPerkuliahanDto;
import id.my.abdillah.skripsi.contract.state.*;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

@Contract(name = "PerkuliahanContract")
@Default
public class PerkuliahanContract implements ContractInterface{
    private final static Logger LOG = Logger.getLogger(PerkuliahanContract.class.getName());
    public PerkuliahanContract(){}
    @Transaction
    public void tambahPerkuliahan(Context ctx,
                                  String perkuliahanId,
                                  String nama,
                                  String kode,
                                  String programStudiId,
                                  String dosenId,
                                  String tahunAjaran,
                                  int semester,
                                  int jumlahSks
    ) {

        Perkuliahan perkuliahan = new Perkuliahan();
        perkuliahan.setSemester(semester);
        perkuliahan.setDosenId(dosenId);
        perkuliahan.setJumlahSks(jumlahSks);
        perkuliahan.setProgramStudiId(programStudiId);
        perkuliahan.setKode(kode);
        perkuliahan.setNama(nama);
        perkuliahan.setTahunAjaran(tahunAjaran);

        ctx.getStub().putState(perkuliahanId, perkuliahan.getJsonStringBytes());
    }
    @Transaction
    public void ajukanKrs(Context ctx,
                          String krsId,
                          String mahasiswaId,
                          int semester,
                          String kuliahIdJson
    ) {

        Krs krs = new Krs();

        krs.setMahasiswaId(mahasiswaId);
        krs.parseKuliahId(kuliahIdJson);
        krs.setSemester(semester);
        krs.setDisetujuiDosenPa(false);

        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
//        String date = "29/6/2021";

        krs.setTanggalDiajukan(date);
        krs.setTanggalDisetujui("");

        int sks = 0;
        for(String kuliahId : krs.getKuliahId()) {
            Perkuliahan perkuliahan = Perkuliahan.fromJSONString(ctx.getStub().getState(kuliahId));
            sks += perkuliahan.getJumlahSks();
        }

        krs.setJumlahSks(sks);

        ctx.getStub().putState(krsId, krs.getJsonStringBytes());
    }

    @Transaction
    public void setujuiKrs(Context ctx,
                           String krsId) {
        Krs krs = Krs.fromJSONString(ctx.getStub().getState(krsId));
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
//        String date = "30/6/2021";
        krs.setTanggalDisetujui(date);
        krs.setDisetujuiDosenPa(true);

        String[] split = krsId.split("\\.");
        String khsId = split[0] + "." + "khs" + "." + split[2];

        Khs khs = new Khs();
        khs.setKrsId(krsId);
        khs.setSemester(krs.getSemester());
        khs.setJumlahSks(krs.getJumlahSks());
        khs.setMahasiswaId(krs.getMahasiswaId());
        ArrayList<HasilPerkuliahanDto> hasilPerkuliahanDto = new ArrayList<>();
        for(String k : krs.getKuliahId()) {
            Perkuliahan p = Perkuliahan.fromJSONString(ctx.getStub().getState(k));
            HasilPerkuliahanDto h = new HasilPerkuliahanDto();
            h.setKuliahId(k);
            h.setNilai(0);
            h.setSks(p.getJumlahSks());
            hasilPerkuliahanDto.add(h);
        }
        khs.setHasilPerkuliahan(hasilPerkuliahanDto);


        LOG.info(krs.toJsonString());
        ctx.getStub().putState(krsId, krs.getJsonStringBytes());
        LOG.info(khs.toJsonString());
        ctx.getStub().putState(khsId, khs.getJsonStringBytes());
    }
    @Transaction
    public void nilaiKhs(Context ctx,
                         String khsId,
                         String kuliahId,
                         double nilai
    ) {
        Khs khs = Khs.fromJSONString(ctx.getStub().getState(khsId));
        double ip = 0;
        for(HasilPerkuliahanDto h : khs.getHasilPerkuliahan()) {
            if(kuliahId.equals(h.getKuliahId())) {
                h.setNilai(nilai);
            }
            ip += h.getNilai();
        }
        ip = ip / khs.getHasilPerkuliahan().size();
        khs.setIp(ip);

        LOG.info(khs.toJsonString());
        ctx.getStub().putState(khsId, khs.getJsonStringBytes());
    }
    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Krs lihatKrs(Context ctx, String krsId) {
        Krs krs = Krs.fromJSONString(ctx.getStub().getState(krsId));

        return krs;
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Khs lihatKhs(Context ctx, String khsId) {
        Khs khs = Khs.fromJSONString(ctx.getStub().getState(khsId));

        return khs;
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Perkuliahan lihatPerkuliahan(Context ctx, String perkuliahanId) {
        Perkuliahan perkuliahan = Perkuliahan.fromJSONString(ctx.getStub().getState(perkuliahanId));

        return perkuliahan;
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Attendance getAttendance(Context ctx, String attendanceId) {
        Attendance attendance = Attendance.fromJSONString(ctx.getStub().getState(attendanceId));

        return attendance;
    }
    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Grade getGrade(Context ctx, String gradeId) {
        Grade grade = Grade.fromJSONString(ctx.getStub().getState(gradeId));

        return grade;
    }

    @Transaction
    public void addAttendance(Context ctx,
                                  int studentId,
                                  boolean attended,
                                  int weekNumber
    ) {
        String attendanceId = "attendance." + Integer.toString(studentId) + "." + Integer.toString(weekNumber);

        Attendance attendance = new Attendance();
        attendance.setAttended(attended);
        attendance.setStudentId(studentId);
        attendance.setWeekNumber(weekNumber);

        ctx.getStub().putState(attendanceId, attendance.getJsonStringBytes());
    }

    @Transaction
    public void addGrade(Context ctx,
                              int studentId,
                              boolean graduated
    ) {
        String gradeId = "grade." + Integer.toString(studentId);

        // validation
        int notAttendedCount = 0;
        for(int i = 1; i <= 12; i++) {
            String attendanceId = "attendance." + Integer.toString(studentId) + "." + i;
            byte[] attendanceByte = ctx.getStub().getState(attendanceId);
            if(attendanceByte == null) {
                break;
            }
            String attendanceString = new String(attendanceByte, StandardCharsets.UTF_8);
            if(attendanceString == null || attendanceString.length() == 0) {
                break;
            }
            Attendance attendance = Attendance.fromJSONString(attendanceString);
            if(!attendance.isAttended()) notAttendedCount++;
            if(notAttendedCount == 3) {
                return;
            }

        }

        Grade grade = new Grade();
        grade.setStudentId(studentId);
        grade.setGraduated(graduated);

        ctx.getStub().putState(gradeId, grade.getJsonStringBytes());
    }
}
