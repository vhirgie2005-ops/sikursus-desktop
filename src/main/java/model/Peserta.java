package model;

public class Peserta extends Orang {
    private String nim;
    private String prodi;
    private String jenisKelamin; 
    private String email;        

    // =========================================================================
    // CONSTRUCTOR DEFAULT (Wajib Ada untuk PesertaDAO)
    // =========================================================================
    public Peserta() {
        super();
    }

    // =========================================================================
    // CONSTRUCTOR 1: Untuk UjiValidasi & DemoPolymorphism (Mendukung Kode Lama)
    // =========================================================================
    public Peserta(int id, String nama, String noHp, String nim, String prodi) {
        super(id, nama, noHp);
        this.nim = nim;
        this.prodi = prodi;
        this.jenisKelamin = "L"; 
        this.email = "";
    }

    // =========================================================================
    // CONSTRUCTOR 2: Untuk Form GUI & Simpan Database (Mendukung Kode Baru)
    // =========================================================================
    public Peserta(String nim, String nama, String jenisKelamin, String prodi, String noHp, String email) {
        super(0, nama, noHp); 
        this.nim = nim;
        this.prodi = prodi;
        this.jenisKelamin = jenisKelamin;
        this.email = email;
    }

    // =========================================================================
    // GETTER & SETTER
    // =========================================================================
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = (nim != null) ? nim.trim() : "";
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = (prodi != null) ? prodi.trim() : "";
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // =========================================================================
    // ALIAS METHOD UNTUK NOMOR HP (Mencegah Error di FormPeserta)
    // =========================================================================
    public String getNomorHp() {
        return getNoHp();
    }

    public void setNomorHp(String nomorHp) {
        setNoHp(nomorHp);
    }

    @Override
    public String getInfo() {
        return "PESERTA | " + super.getInfo()
                + " | NIM: " + getNim()
                + " | Prodi: " + getProdi()
                + " | JK: " + getJenisKelamin();
    }
}