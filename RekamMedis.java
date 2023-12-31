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
