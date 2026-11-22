package view;

import dao.PesertaDAO;
import model.Peserta;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class FormPeserta extends javax.swing.JFrame {
    private static final Color BG_MAIN = new Color(253, 242, 246);
    private static final Color PRIMARY_PINK = new Color(176, 75, 118);
    private static final Color TEXT_DARK_PINK = new Color(105, 42, 70);
    private static final Color SOFT_PINK_BTN = new Color(249, 224, 234);

    private JPanel jPanel4, jPanel5;
    private JLabel jLabel19, jLabel20, jLabel21, jLabel22, jLabel23, jLabel24, jLabel25, jLabel26, jLabel27, jLabel28, jLabel29;
    private JTextPane txtNim, txtNama, txtProdi, txtNoHp, txtEmail, txtRingkasanPendaftaran;
    private JPasswordField txtKodeAkses;
    private JRadioButton rbLaki, rbPerempuan;
    private ButtonGroup bgJk;
    private JComboBox<String> cmbPilihanKursus;
    private JCheckBox cbModulCetak, cbSertifikat, cbAksesRekaman;
    private JButton btnProsesPendaftaran, btnResetForm;
    private PesertaDAO pesertaDAO;

    public FormPeserta() {
        pesertaDAO = new PesertaDAO();
        initComponents();
        setTitle("Form Pendaftaran Kursus - SIKURSUS Desktop");
        setSize(820, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        jPanel4 = new JPanel();
        jPanel4.setBackground(BG_MAIN);
        jPanel4.setLayout(null);
        jPanel4.setPreferredSize(new Dimension(780, 850));

        // Header Panel
        jPanel5 = new JPanel();
        jPanel5.setBackground(PRIMARY_PINK);
        jPanel5.setBounds(0, 0, 820, 65);
        jPanel5.setLayout(null);

        jLabel19 = new JLabel("Form Pendaftaran Kelas", JLabel.CENTER);
        jLabel19.setForeground(Color.WHITE);
        jLabel19.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jLabel19.setBounds(0, 15, 820, 35);
        jPanel5.add(jLabel19);
        jPanel4.add(jPanel5);

        int startY = 85;
        int gap = 45;

        // NIM
        jLabel20 = new JLabel("Nim");
        jLabel20.setBounds(50, startY, 120, 25);
        jLabel20.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel20.setForeground(TEXT_DARK_PINK);
        jPanel4.add(jLabel20);
        txtNim = new JTextPane();
        JScrollPane spNim = new JScrollPane(txtNim);
        spNim.setBounds(180, startY, 540, 30);
        jPanel4.add(spNim);

        // Nama Peserta
        startY += gap;
        jLabel21 = new JLabel("Nama Peserta");
        jLabel21.setBounds(50, startY, 120, 25);
        jLabel21.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel21.setForeground(TEXT_DARK_PINK);
        jPanel4.add(jLabel21);
        txtNama = new JTextPane();
        JScrollPane spNama = new JScrollPane(txtNama);
        spNama.setBounds(180, startY, 540, 30);
        jPanel4.add(spNama);

        // Jenis Kelamin
        startY += gap;
        jLabel22 = new JLabel("Jenis Kelamin");
        jLabel22.setBounds(50, startY, 120, 25);
        jLabel22.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel22.setForeground(TEXT_DARK_PINK);
        jPanel4.add(jLabel22);

        rbLaki = new JRadioButton("Laki-Laki");
        rbLaki.setBounds(180, startY, 100, 25);
        rbLaki.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rbLaki.setForeground(TEXT_DARK_PINK);
        rbLaki.setOpaque(false);

        rbPerempuan = new JRadioButton("Perempuan");
        rbPerempuan.setBounds(380, startY, 110, 25);
        rbPerempuan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rbPerempuan.setForeground(TEXT_DARK_PINK);
        rbPerempuan.setOpaque(false);

        bgJk = new ButtonGroup();
        bgJk.add(rbLaki);
        bgJk.add(rbPerempuan);
        jPanel4.add(rbLaki);
        jPanel4.add(rbPerempuan);

        // Program Studi
        startY += gap;
        jLabel23 = new JLabel("Program Studi");
        jLabel23.setBounds(50, startY, 120, 25);
        jLabel23.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel23.setForeground(TEXT_DARK_PINK);
        jPanel4.add(jLabel23);
        txtProdi = new JTextPane();
        JScrollPane spProdi = new JScrollPane(txtProdi);
        spProdi.setBounds(180, startY, 540, 30);
        jPanel4.add(spProdi);

        // Nomor Hp
        startY += gap;
        jLabel24 = new JLabel("Nomor Hp");
        jLabel24.setBounds(50, startY, 120, 25);
        jLabel24.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel24.setForeground(TEXT_DARK_PINK);
        jPanel4.add(jLabel24);
        txtNoHp = new JTextPane();
        JScrollPane spNoHp = new JScrollPane(txtNoHp);
        spNoHp.setBounds(180, startY, 540, 30);
        jPanel4.add(spNoHp);

        // Email
        startY += gap;
        jLabel25 = new JLabel("Email");
        jLabel25.setBounds(50, startY, 120, 25);
        jLabel25.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel25.setForeground(TEXT_DARK_PINK);
        jPanel4.add(jLabel25);
        txtEmail = new JTextPane();
        JScrollPane spEmail = new JScrollPane(txtEmail);
        spEmail.setBounds(180, startY, 540, 30);
        jPanel4.add(spEmail);

        // Kode Akses
        startY += gap;
        jLabel26 = new JLabel("Kode Akses");
        jLabel26.setBounds(50, startY, 120, 25);
        jLabel26.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel26.setForeground(TEXT_DARK_PINK);
        jPanel4.add(jLabel26);
        txtKodeAkses = new JPasswordField();
        txtKodeAkses.setBounds(180, startY, 540, 30);
        jPanel4.add(txtKodeAkses);

        // Pilihan Kursus
        startY += gap;
        jLabel28 = new JLabel("kursus");
        jLabel28.setBounds(50, startY, 120, 25);
        jLabel28.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel28.setForeground(TEXT_DARK_PINK);
        jPanel4.add(jLabel28);
        cmbPilihanKursus = new JComboBox<>(new String[] { "-Pilih Kursus-", "UI/UX", "Pemrograman Java Desktop", "Pemrograman Web", "Database MySQL", "Dasar-Dasar Pemrograman" });
        cmbPilihanKursus.setBounds(180, startY, 540, 30);
        jPanel4.add(cmbPilihanKursus);

        // Fasilitas
        startY += gap;
        jLabel27 = new JLabel("Fasilitas");
        jLabel27.setBounds(50, startY, 120, 25);
        jLabel27.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel27.setForeground(TEXT_DARK_PINK);
        jPanel4.add(jLabel27);

        cbModulCetak = new JCheckBox("Modul Cetak");
        cbModulCetak.setBounds(180, startY, 115, 25);
        cbModulCetak.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cbModulCetak.setForeground(TEXT_DARK_PINK);
        cbModulCetak.setOpaque(false);

        cbSertifikat = new JCheckBox("Sertifikat");
        cbSertifikat.setBounds(330, startY, 90, 25);
        cbSertifikat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cbSertifikat.setForeground(TEXT_DARK_PINK);
        cbSertifikat.setOpaque(false);

        cbAksesRekaman = new JCheckBox("Akses Rekaman");
        cbAksesRekaman.setBounds(500, startY, 130, 25);
        cbAksesRekaman.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cbAksesRekaman.setForeground(TEXT_DARK_PINK);
        cbAksesRekaman.setOpaque(false);

        jPanel4.add(cbModulCetak);
        jPanel4.add(cbSertifikat);
        jPanel4.add(cbAksesRekaman);

        // Ringkasan
        startY += gap;
        jLabel29 = new JLabel("Ringkasan");
        jLabel29.setBounds(50, startY, 120, 25);
        jLabel29.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel29.setForeground(TEXT_DARK_PINK);
        jPanel4.add(jLabel29);

        txtRingkasanPendaftaran = new JTextPane();
        txtRingkasanPendaftaran.setEditable(false);
        JScrollPane spRingkasan = new JScrollPane(txtRingkasanPendaftaran);
        spRingkasan.setBounds(180, startY, 540, 120);
        jPanel4.add(spRingkasan);

        // Tombol Proses & Reset
        startY += 140;
        btnProsesPendaftaran = new JButton("PROSES");
        btnProsesPendaftaran.setBounds(180, startY, 110, 35);
        btnProsesPendaftaran.setBackground(PRIMARY_PINK);
        btnProsesPendaftaran.setForeground(Color.WHITE);
        btnProsesPendaftaran.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnProsesPendaftaran.addActionListener(e -> btnProsesPendaftaranActionPerformed());
        jPanel4.add(btnProsesPendaftaran);

        btnResetForm = new JButton("RESET");
        btnResetForm.setBounds(305, startY, 110, 35);
        btnResetForm.setBackground(SOFT_PINK_BTN);
        btnResetForm.setForeground(TEXT_DARK_PINK);
        btnResetForm.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnResetForm.addActionListener(e -> btnResetFormActionPerformed());
        jPanel4.add(btnResetForm);

        // Bungkus dengan JScrollPane utama
        JScrollPane mainScrollPane = new JScrollPane(jPanel4);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setContentPane(mainScrollPane);
    }

    private void resetForm() {
        txtNim.setText("");
        txtNama.setText("");
        bgJk.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtKodeAkses.setText("");
        cmbPilihanKursus.setSelectedIndex(0);
        cbModulCetak.setSelected(false);
        cbSertifikat.setSelected(false);
        cbAksesRekaman.setSelected(false);
        txtRingkasanPendaftaran.setText("");
        txtNim.requestFocus();
    }

    private void btnResetFormActionPerformed() {
        int jawab = JOptionPane.showConfirmDialog(this, "Kosongkan seluruh input?", "Konfirmasi Reset", JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            resetForm();
        }
    }

    private void btnProsesPendaftaranActionPerformed() {
        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String jenisKelamin = rbLaki.isSelected() ? "L" : (rbPerempuan.isSelected() ? "P" : "");
        String prodi = txtProdi.getText().trim();
        String noHp = txtNoHp.getText().trim();
        String email = txtEmail.getText().trim();
        String kursus = cmbPilihanKursus.getSelectedItem().toString();

        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || jenisKelamin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIM, Nama, Prodi, dan Jenis Kelamin wajib diisi!", "Peringatan Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double biayaDasar = 0;
        if (kursus.contains("Java") || kursus.equalsIgnoreCase("Pemrograman Java Desktop")) {
            biayaDasar = 500000;
        } else if (kursus.contains("UI/UX")) {
            biayaDasar = 400000;
        } else if (kursus.contains("Web")) {
            biayaDasar = 600000;
        } else if (kursus.contains("Database")) {
            biayaDasar = 450000;
        } else {
            biayaDasar = 350000;
        }

        double biayaFasilitas = 0;
        StringBuilder rincianFasilitas = new StringBuilder();

        if (cbModulCetak.isSelected()) {
            biayaFasilitas += 75000;
            rincianFasilitas.append("  - Modul Cetak\n");
        }
        if (cbSertifikat.isSelected()) {
            biayaFasilitas += 50000;
            rincianFasilitas.append("  - Sertifikat\n");
        }
        if (cbAksesRekaman.isSelected()) {
            biayaFasilitas += 100000;
            rincianFasilitas.append("  - Akses Rekaman\n");
        }

        if (rincianFasilitas.length() == 0) {
            rincianFasilitas.append("  - Tidak Ada\n");
        }

        double totalBiaya = biayaDasar + biayaFasilitas;

        java.text.NumberFormat nf = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("id", "ID"));
        nf.setMaximumFractionDigits(0);

        StringBuilder sb = new StringBuilder();
        sb.append("PENDAFTARAN & PENYIMPANAN DATA\n");
        sb.append("====================================\n");
        sb.append(String.format("%-15s : %s\n", "NIM", nim));
        sb.append(String.format("%-15s : %s\n", "Nama", nama));
        sb.append(String.format("%-15s : %s\n", "Gender", jenisKelamin.equals("L") ? "Laki-laki" : "Perempuan"));
        sb.append(String.format("%-15s : %s\n", "Prodi", prodi));
        sb.append(String.format("%-15s : %s\n", "No HP", noHp));
        sb.append(String.format("%-15s : %s\n", "Email", email));
        sb.append(String.format("%-15s : %s\n", "Kursus", kursus));
        sb.append(String.format("%-15s : %s\n", "Biaya Dasar", nf.format(biayaDasar)));
        sb.append("Fasilitas:\n");
        sb.append(rincianFasilitas.toString());
        sb.append("====================================\n");
        sb.append(String.format("%-15s : %s\n", "TOTAL BIAYA", nf.format(totalBiaya)));

        txtRingkasanPendaftaran.setText(sb.toString());

        try {
            Peserta peserta = new Peserta();
            peserta.setNim(nim);
            peserta.setNama(nama);
            peserta.setJenisKelamin(jenisKelamin);
            peserta.setProdi(prodi);
            peserta.setNoHp(noHp);
            peserta.setEmail(email);

            boolean sukses = pesertaDAO.insert(peserta);
            if (sukses) {
                JOptionPane.showMessageDialog(this, "Data peserta berhasil disimpan ke database XAMPP!", "Informasi Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan ke database:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        EventQueue.invokeLater(() -> new FormPeserta().setVisible(true));
    }
}