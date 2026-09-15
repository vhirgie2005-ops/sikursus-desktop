package latihan;

public class Latihan2 {

    public static void main(String[] args) {

        // =========================
        // DATA KURSUS
        // =========================
        String kode = "JAVA-BSC";
        String nama = "Java Desktop Fundamental";

        double biayaKursus = 2575000;
        double biayaRegistrasi = 500000;

        // =========================
        // 1. TAMBAHKAN BIAYA REGISTRASI
        // =========================
        double totalSebelumDiskon = biayaKursus + biayaRegistrasi;

        // =========================
        // 2. MENENTUKAN DISKON
        // =========================
        double diskon;

        if (totalSebelumDiskon >= 600000) {
            diskon = 0.10;
        } else {
            diskon = 0.05;
        }

        double potongan = totalSebelumDiskon * diskon;
        double totalBayar = totalSebelumDiskon - potongan;

        // =========================
        // 3. MENENTUKAN STATUS HARGA
        // =========================
        String status;

        if (totalSebelumDiskon >= 600000) {
            status = "MAHAL";
        } else {
            status = "TERJANGKAU";
        }

        // =========================
        // MENAMPILKAN DATA KURSUS
        // =========================
        System.out.println("==============================");
        System.out.println("       DATA KURSUS JAVA");
        System.out.println("==============================");

        System.out.println("Kode Kursus       : " + kode);
        System.out.println("Nama Kursus       : " + nama);
        System.out.printf("Biaya Kursus      : Rp %,.0f%n", biayaKursus);
        System.out.printf("Biaya Registrasi  : Rp %,.0f%n", biayaRegistrasi);

        System.out.println("------------------------------");

        System.out.printf("Total Sebelum Diskon : Rp %,.0f%n",
                totalSebelumDiskon);

        System.out.printf("Diskon               : %.0f%%%n",
                diskon * 100);

        System.out.printf("Potongan             : Rp %,.0f%n",
                potongan);

        System.out.printf("Total Bayar          : Rp %,.0f%n",
                totalBayar);

        System.out.println("Status Harga         : " + status);

        // =========================
        // 4. LATIHAN LUAS & KELILING
        // =========================

        // PERSEGI
        double sisi = 10;
        double luasPersegi = sisi * sisi;
        double kelilingPersegi = 4 * sisi;

        System.out.println("\n==============================");
        System.out.println("          PERSEGI");
        System.out.println("==============================");

        System.out.println("Sisi      : " + sisi + " cm");
        System.out.println("Luas      : " + luasPersegi + " cm2");
        System.out.println("Keliling  : " + kelilingPersegi + " cm");

        // PERSEGI PANJANG
        double panjang = 20;
        double lebar = 10;

        double luasPersegiPanjang = panjang * lebar;
        double kelilingPersegiPanjang = 2 * (panjang + lebar);

        System.out.println("\n==============================");
        System.out.println("       PERSEGI PANJANG");
        System.out.println("==============================");

        System.out.println("Panjang   : " + panjang + " cm");
        System.out.println("Lebar     : " + lebar + " cm");
        System.out.println("Luas      : " + luasPersegiPanjang + " cm2");
        System.out.println("Keliling  : " + kelilingPersegiPanjang + " cm");

        // LINGKARAN
        double jariJari = 7;
        double phi = 3.14;

        double luasLingkaran = phi * jariJari * jariJari;
        double kelilingLingkaran = 2 * phi * jariJari;

        System.out.println("\n==============================");
        System.out.println("          LINGKARAN");
        System.out.println("==============================");

        System.out.println("Jari-jari : " + jariJari + " cm");
        System.out.println("Luas      : " + luasLingkaran + " cm2");
        System.out.println("Keliling  : " + kelilingLingkaran + " cm");

        System.out.println("==============================");
    }
}