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
