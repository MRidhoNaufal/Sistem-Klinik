import java.io.*;

// Interface untuk penyimpanan data medis
interface DataMedis {
    void simpanRekamMedis();
    // Metode terkait data medis lainnya
    // ...
}

// Kelas Abstrak untuk Dokter
abstract class Dokter {
    private String nama;
    private String spesialisasi;
    private String jadwalPraktik;

    public Dokter(String nama, String spesialisasi, String jadwalPraktik) {
        this.nama = nama;
        this.spesialisasi = spesialisasi;
        this.jadwalPraktik = jadwalPraktik;
    }

    public abstract void tampilkanJadwalPraktik();

    public String getNama() {
        return nama;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public String getJadwalPraktik() {
        return jadwalPraktik;
    }
}

// Kelas turunan dari Dokter
class DokterUmum extends Dokter {
    public DokterUmum(String nama, String spesialisasi, String jadwalPraktik) {
        super(nama, spesialisasi, jadwalPraktik);
    }

    @Override
    public void tampilkanJadwalPraktik() {
        System.out.println("Jadwal praktik Dokter Umum: " + getJadwalPraktik());
    }
}

// Kelas untuk Rekam Medis Pasien
class RekamMedis implements DataMedis {
    private String namaPasien;
    private String diagnosis;
    private String catatan;

    public RekamMedis(String namaPasien, String diagnosis, String catatan) {
        this.namaPasien = namaPasien;
        this.diagnosis = diagnosis;
        this.catatan = catatan;
    }

    @Override
    public void simpanRekamMedis() {
        try (FileWriter fileWriter = new FileWriter("rekam_medis_pasien.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("Rekam Medis: " + namaPasien + ", " + diagnosis + ", " + catatan);
            System.out.println("Rekam medis telah disimpan.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class AplikasiKlinik {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Input data dokter
            System.out.println("Masukkan nama dokter:");
            String namaDokter = reader.readLine();

            System.out.println("Masukkan spesialisasi dokter:");
            String spesialisasiDokter = reader.readLine();

            System.out.println("Masukkan jadwal praktik dokter:");
            String jadwalPraktikDokter = reader.readLine();

            // Membuat objek dokter
            Dokter dokter1 = new DokterUmum(namaDokter, spesialisasiDokter, jadwalPraktikDokter);

            // Menampilkan informasi dokter
            System.out.println("\nInformasi Dokter:");
            System.out.println("Nama: " + dokter1.getNama());
            System.out.println("Spesialisasi: " + dokter1.getSpesialisasi());

            // Menampilkan jadwal praktik dokter
            dokter1.tampilkanJadwalPraktik();

            // Input data rekam medis pasien
            System.out.println("\nInput Rekam Medis Pasien");
            System.out.println("Masukkan nama pasien:");
            String namaPasien = reader.readLine();

            System.out.println("Masukkan diagnosis:");
            String diagnosis = reader.readLine();

            System.out.println("Masukkan catatan:");
            String catatan = reader.readLine();

            // Proses rekam medis pasien dan menyimpannya ke file
            RekamMedis rekamMedis = new RekamMedis(namaPasien, diagnosis, catatan);
            rekamMedis.simpanRekamMedis();

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
