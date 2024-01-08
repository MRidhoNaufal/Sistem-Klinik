import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

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
        JOptionPane.showMessageDialog(null, "Jadwal praktik Dokter Umum: " + getJadwalPraktik());
        AplikasiKlinik.addToHistory("Menampilkan Jadwal Praktik Dokter Umum");
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
            JOptionPane.showMessageDialog(null, "Rekam medis telah disimpan.");
            AplikasiKlinik.addToHistory("Simpan Rekam Medis untuk " + namaPasien);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class AplikasiKlinik {
    private static ArrayList<String> history = new ArrayList<>();

    public static void addToHistory(String operation) {
        history.add(operation);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplikasi Klinik");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(null);

        // Button untuk menampilkan jadwal praktik dokter
        JButton showScheduleButton = new JButton("Tampilkan Jadwal Praktik");
        showScheduleButton.setBounds(50, 50, 200, 30);
        frame.add(showScheduleButton);

        // Button untuk menyimpan rekam medis pasien
        JButton saveRecordButton = new JButton("Simpan Rekam Medis");
        saveRecordButton.setBounds(50, 100, 200, 30);
        frame.add(saveRecordButton);

        // Button untuk melihat history rekam medis
        JButton historyButton = new JButton("Lihat Riwayat Rekam Medis");
        historyButton.setBounds(50, 150, 200, 30);
        frame.add(historyButton);

        // Action listener untuk menampilkan jadwal praktik dokter
        showScheduleButton.addActionListener(e -> {
            // Input data dokter (bisa dimasukkan melalui dialog input juga)
            String namaDokter = JOptionPane.showInputDialog("Masukkan nama dokter:");
            String spesialisasiDokter = JOptionPane.showInputDialog("Masukkan spesialisasi dokter:");
            String jadwalPraktikDokter = JOptionPane.showInputDialog("Masukkan jadwal praktik dokter:");

            // Membuat objek dokter
            Dokter dokter1 = new DokterUmum(namaDokter, spesialisasiDokter, jadwalPraktikDokter);

            // Menampilkan informasi dokter
            JOptionPane.showMessageDialog(null,
                    "Informasi Dokter:\nNama: " + dokter1.getNama() +
                            "\nSpesialisasi: " + dokter1.getSpesialisasi());

            // Menampilkan jadwal praktik dokter
            dokter1.tampilkanJadwalPraktik();
        });

        // Action listener untuk menyimpan rekam medis pasien
        saveRecordButton.addActionListener(e -> {
            // Input data rekam medis pasien
            String namaPasien = JOptionPane.showInputDialog("Masukkan nama pasien:");
            String diagnosis = JOptionPane.showInputDialog("Masukkan diagnosis:");
            String catatan = JOptionPane.showInputDialog("Masukkan catatan:");

            // Proses rekam medis pasien dan menyimpannya ke file
            RekamMedis rekamMedis = new RekamMedis(namaPasien, diagnosis, catatan);
            rekamMedis.simpanRekamMedis();
        });

        // Action listener untuk melihat history rekam medis
        historyButton.addActionListener(e -> {
            StringBuilder historyMessage = new StringBuilder("Riwayat Operasi Rekam Medis:\n");
            for (String operation : history) {
                if (operation.startsWith("Simpan Rekam Medis")) {
                    historyMessage.append(operation).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, historyMessage.toString());
        });

        frame.setVisible(true);
    }
}
