class DokterUmum extends Dokter {
    public DokterUmum(String nama, String spesialisasi, String jadwalPraktik) {
        super(nama, spesialisasi, jadwalPraktik);
    }

    @Override
    public void tampilkanJadwalPraktik() {
        System.out.println("Jadwal praktik Dokter Umum: " + getJadwalPraktik());
    }
}
