package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FormPendaftaranKursus extends JFrame {

    // --- PALET WARNA PINK MODERN & ELEGAN (MENYESUAIKAN DASHBOARD) ---
    private static final Color COLOR_PRIMARY = new Color(131, 24, 67);      // Rose/Berry Pekat Elegan (#831843)
    private static final Color COLOR_BG = new Color(253, 242, 248);         // Soft Pink Background Sangat Bersih (#FDF2F8)
    private static final Color COLOR_TEXT_DARK = new Color(50, 20, 40);     // Teks gelap dengan sentuhan hangat
    private static final Color COLOR_MUTED_BG = new Color(252, 231, 243);   // Soft Pink untuk tombol sekunder

    // Komponen Form
    private JTextField txtNamaPeserta;
    private JPasswordField txtKodeAkses;
    private JRadioButton rbMahasiswa, rbUmum;
    private ButtonGroup bgJenisPeserta;
    private JComboBox<String> cmbPilihanKursus;
    private JCheckBox cbModulCetak, cbSertifikat, cbAksesRekaman;
    private JTextPane txtRingkasanPendaftaran;
    private JButton btnProsesPendaftaran, btnResetForm;

    public FormPendaftaranKursus() {
        setTitle("Form Pendaftaran Kursus - SIKURSUS Desktop");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        // Panel Utama dengan Background Soft Pink
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(COLOR_BG);
        setContentPane(mainPanel);

        // --- 1. HEADER BANNER ---
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(COLOR_PRIMARY);
        headerPanel.setBorder(new EmptyBorder(18, 15, 18, 15));

        JLabel lblHeader = new JLabel("Form Pendaftaran Kursus");
        lblHeader.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.add(lblHeader);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // --- 2. KONTEN FORM (GRIDBAGLAYOUT) ---
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Baris 0: Nama Peserta
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblNama = new JLabel("Nama Peserta");
        lblNama.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNama.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblNama, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtNamaPeserta = new JTextField();
        txtNamaPeserta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNamaPeserta.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(txtNamaPeserta, gbc);

        // Baris 1: Kode Akses
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        JLabel lblKode = new JLabel("Kode Akses");
        lblKode.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblKode.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblKode, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtKodeAkses = new JPasswordField();
        txtKodeAkses.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtKodeAkses.setForeground(COLOR_TEXT_DARK);
        txtKodeAkses.setToolTipText("Kode akses demo praktikum: PBO2026 atau PB02026");
        contentPanel.add(txtKodeAkses, gbc);

        // Baris 2: Jenis Peserta
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        JLabel lblJenis = new JLabel("Jenis Peserta");
        lblJenis.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblJenis.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblJenis, gbc);

        JPanel panelJenis = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelJenis.setOpaque(false);
        rbMahasiswa = new JRadioButton("Mahasiswa");
        rbUmum = new JRadioButton("Umum");
        
        rbMahasiswa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rbMahasiswa.setForeground(COLOR_TEXT_DARK);
        rbMahasiswa.setOpaque(false);
        rbMahasiswa.setFocusPainted(false);

        rbUmum.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rbUmum.setForeground(COLOR_TEXT_DARK);
        rbUmum.setOpaque(false);
        rbUmum.setFocusPainted(false);

        bgJenisPeserta = new ButtonGroup();
        bgJenisPeserta.add(rbMahasiswa);
        bgJenisPeserta.add(rbUmum);

        panelJenis.add(rbMahasiswa);
        panelJenis.add(rbUmum);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(panelJenis, gbc);

        // Baris 3: Kursus
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lblKursus = new JLabel("Kursus");
        lblKursus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblKursus.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblKursus, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbPilihanKursus = new JComboBox<>(new String[]{
            "--Pilih Kursus--", "UI/UX", "Pemrograman Java Desktop", 
            "Pemrograman Web", "Database MySQL", "Dasar-Dasar Pemrograman"
        });
        cmbPilihanKursus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbPilihanKursus.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(cmbPilihanKursus, gbc);

        // Baris 4: Fasilitas
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lblFasilitas = new JLabel("Fasilitas");
        lblFasilitas.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblFasilitas.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblFasilitas, gbc);

        JPanel panelFasilitas = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelFasilitas.setOpaque(false);
        cbModulCetak = new JCheckBox("Modul Cetak");
        cbSertifikat = new JCheckBox("Sertifikat");
        cbAksesRekaman = new JCheckBox("Akses Rekaman");

        JCheckBox[] cbs = {cbModulCetak, cbSertifikat, cbAksesRekaman};
        for (JCheckBox cb : cbs) {
            cb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            cb.setForeground(COLOR_TEXT_DARK);
            cb.setOpaque(false);
            cb.setFocusPainted(false);
            panelFasilitas.add(cb);
        }

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(panelFasilitas, gbc);

        // Baris 5: Ringkasan
        gbc.gridx = 0; gbc.gridy = 5; gbc.anchor = GridBagConstraints.NORTHWEST;
        JLabel lblRingkasan = new JLabel("Ringkasan");
        lblRingkasan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblRingkasan.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblRingkasan, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0;
        txtRingkasanPendaftaran = new JTextPane();
        txtRingkasanPendaftaran.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtRingkasanPendaftaran.setForeground(COLOR_TEXT_DARK);
        txtRingkasanPendaftaran.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtRingkasanPendaftaran);
        scrollPane.setPreferredSize(new Dimension(380, 180));
        contentPanel.add(scrollPane, gbc);

        // Baris 6: Tombol Aksi (Proses & Reset)
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.weighty = 0.0; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.CENTER;
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBtn.setOpaque(false);

        btnProsesPendaftaran = new JButton("Proses");
        btnProsesPendaftaran.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnProsesPendaftaran.setBackground(COLOR_PRIMARY);
        btnProsesPendaftaran.setForeground(Color.WHITE);
        btnProsesPendaftaran.setFocusPainted(false);
        btnProsesPendaftaran.setPreferredSize(new Dimension(100, 30));
        btnProsesPendaftaran.addActionListener(this::btnProsesPendaftaranActionPerformed);

        btnResetForm = new JButton("Reset");
        btnResetForm.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnResetForm.setBackground(COLOR_MUTED_BG);
        btnResetForm.setForeground(COLOR_PRIMARY);
        btnResetForm.setFocusPainted(false);
        btnResetForm.setPreferredSize(new Dimension(100, 30));
        btnResetForm.addActionListener(e -> resetForm());

        panelBtn.add(btnProsesPendaftaran);
        panelBtn.add(btnResetForm);

        contentPanel.add(panelBtn, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    private void btnProsesPendaftaranActionPerformed(ActionEvent evt) {
        String nama = txtNamaPeserta.getText().trim();
        String kode = new String(txtKodeAkses.getPassword()).trim().toUpperCase(Locale.ROOT);

        // Validasi nama
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama peserta wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            txtNamaPeserta.requestFocus();
            return;
        }

        // Validasi kode akses
        if (!kode.equals("PBO2026") && !kode.equals("PB02026")) {
            JOptionPane.showMessageDialog(this, "Kode akses salah!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            txtKodeAkses.requestFocus();
            return;
        }

        // Validasi jenis peserta
        String jenis = getJenisPeserta();
        if (jenis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan pilih jenis peserta!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validasi kursus
        if (cmbPilihanKursus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih kursus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String kursus = cmbPilihanKursus.getSelectedItem().toString();

        // Menghitung biaya
        double biayaKursus = hitungBiayaDasar(kursus);
        double biayaFasilitas = hitungBiayaFasilitas();
        double totalBiaya = biayaKursus + biayaFasilitas;

        // Format Rupiah
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        rupiah.setMaximumFractionDigits(0);
        rupiah.setMinimumFractionDigits(0);

        // Membuat ringkasan otomatis
        String ringkasan =
                "        RINGKASAN PENDAFTARAN KURSUS\n"
                + "====================================\n\n"
                + "Nama Peserta : " + nama + "\n"
                + "Kode Akses   : Valid\n"
                + "Jenis Peserta: " + jenis + "\n"
                + "Kursus       : " + kursus + "\n\n"
                + "FASILITAS TAMBAHAN:\n"
                + buatRingkasanFasilitas()
                + "\n"
                + "Biaya Kursus    : " + rupiah.format(biayaKursus) + "\n"
                + "Biaya Fasilitas : " + rupiah.format(biayaFasilitas) + "\n"
                + "====================================\n"
                + "TOTAL BIAYA     : " + rupiah.format(totalBiaya);

        txtRingkasanPendaftaran.setText(ringkasan);
        txtRingkasanPendaftaran.setCaretPosition(0);

        JOptionPane.showMessageDialog(this, "Pendaftaran berhasil diproses!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
    }

    private String getJenisPeserta() {
        if (rbMahasiswa.isSelected()) return "Mahasiswa";
        if (rbUmum.isSelected()) return "Umum";
        return "";
    }

    private double hitungBiayaDasar(String kursus) {
        switch (kursus) {
            case "Pemrograman Java Desktop": return 500_000;
            case "Data Science": return 650_000;
            case "Pemrograman Web": return 450_000;
            case "UI/UX": return 400_000;
            case "Database MySQL": return 450_000;
            case "Dasar-Dasar Pemrograman": return 350_000;
            default: return 0;
        }
    }

    private double hitungBiayaFasilitas() {
        double total = 0;
        if (cbModulCetak.isSelected()) total += 50_000;
        if (cbSertifikat.isSelected()) total += 75_000;
        if (cbAksesRekaman.isSelected()) total += 40_000;
        return total;
    }

    private String buatRingkasanFasilitas() {
        StringBuilder hasil = new StringBuilder();
        int jumlah = 0;

        if (cbModulCetak.isSelected()) {
            hasil.append("- Modul Cetak\n");
            jumlah++;
        }
        if (cbSertifikat.isSelected()) {
            hasil.append("- Sertifikat\n");
            jumlah++;
        }
        if (cbAksesRekaman.isSelected()) {
            hasil.append("- Akses Rekaman\n");
            jumlah++;
        }

        if (jumlah == 0) {
            return "Tidak ada fasilitas tambahan\n";
        }
        return hasil.toString();
    }

    private void resetForm() {
        txtNamaPeserta.setText("");
        txtKodeAkses.setText("");
        bgJenisPeserta.clearSelection();
        cmbPilihanKursus.setSelectedIndex(0);
        cbModulCetak.setSelected(false);
        cbSertifikat.setSelected(false);
        cbAksesRekaman.setSelected(false);
        txtRingkasanPendaftaran.setText("");
        txtNamaPeserta.requestFocus();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new FormPendaftaranKursus().setVisible(true));
    }
}