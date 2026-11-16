package view;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.User;
import util.UserSession;
import util.WindowManager;

public class DashboardFrame extends JFrame {

    private JLabel lblUser;
    private JLabel lblRole;

    private JMenuBar menuBar;
    private JMenu menuMaster, menuTransaksi, menuLaporan, menuKeluar;
    private JMenuItem miPeserta, miInstruktur, miKursus, miKelas;
    private JMenuItem miPendaftaran, miPembayaran;
    private JMenuItem miLaporanPeserta, miLaporanPendaftaran;
    private JMenuItem miLogout, miTutup;

    private JPopupMenu popupDashboard;
    private JMenuItem miTentang, miRefresh;

    // --- PALET WARNA PINK MODERN & ELEGAN ---
    private static final Color COLOR_PRIMARY = new Color(131, 24, 67);      // Rose/Berry Pekat Elegan (#831843)
    private static final Color COLOR_PRIMARY_DARK = new Color(157, 23, 77); // Varian lebih gelap untuk badge
    private static final Color COLOR_BG = new Color(253, 242, 248);         // Soft Pink Background Sangat Bersih (#FDF2F8)
    private static final Color COLOR_CARD_BG = Color.WHITE;
    private static final Color COLOR_TEXT_DARK = new Color(50, 20, 40);     // Teks gelap dengan sentuhan hangat
    private static final Color COLOR_TEXT_MUTED = new Color(113, 80, 95);   // Teks abu-pink lembut

    public DashboardFrame() {
        initComponents();
        setLocationRelativeTo(null);
        applySession();
    }

    private void initComponents() {
        setTitle("Dashboard - SIKURSUS Desktop Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 620);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(COLOR_BG);
        setContentPane(mainPanel);

        // --- HEADER / BANNER UTAMA ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COLOR_PRIMARY);
        headerPanel.setBorder(new EmptyBorder(25, 35, 25, 35));

        JPanel titleGroup = new JPanel();
        titleGroup.setLayout(new BoxLayout(titleGroup, BoxLayout.Y_AXIS));
        titleGroup.setOpaque(false);

        JLabel lblAppTitle = new JLabel("SIKURSUS Desktop");
        lblAppTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblAppTitle.setForeground(Color.WHITE);

        JLabel lblSubHeader = new JLabel("Pusat Kendali Sistem Informasi Manajemen Kursus & Pelatihan");
        lblSubHeader.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubHeader.setForeground(new Color(252, 231, 243)); // Pink muda lembut untuk subheader

        titleGroup.add(lblAppTitle);
        titleGroup.add(Box.createRigidArea(new Dimension(0, 4)));
        titleGroup.add(lblSubHeader);
        headerPanel.add(titleGroup, BorderLayout.WEST);

        // User & Role Badge di Header Kanan
        JPanel userBadgePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        userBadgePanel.setOpaque(false);

        JPanel badgeInner = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        badgeInner.setBackground(COLOR_PRIMARY_DARK);
        badgeInner.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        
        lblUser = new JLabel("User: -");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(Color.WHITE);

        JLabel sep = new JLabel("|");
        sep.setForeground(new Color(244, 114, 182));

        lblRole = new JLabel("Role: -");
        lblRole.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblRole.setForeground(new Color(254, 240, 138)); // Kuning pastel agar kontras dan manis

        badgeInner.add(lblUser);
        badgeInner.add(sep);
        badgeInner.add(lblRole);
        userBadgePanel.add(badgeInner);
        
        headerPanel.add(userBadgePanel, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // --- KONTEN TENGAH (KARTU SAMBUTAN) ---
        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setOpaque(false);

        JPanel welcomeCard = new JPanel();
        welcomeCard.setLayout(new BoxLayout(welcomeCard, BoxLayout.Y_AXIS));
        welcomeCard.setBackground(COLOR_CARD_BG);
        welcomeCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(252, 231, 243), 1, true),
            new EmptyBorder(45, 55, 45, 55)
        ));

        JLabel lblWelcomeTitle = new JLabel("Selamat Datang, Administrator!");
        lblWelcomeTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblWelcomeTitle.setForeground(COLOR_TEXT_DARK);
        lblWelcomeTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblWelcomeDesc = new JLabel("<html>Gunakan menu navigasi di atas untuk mengakses modul Master Data, memproses<br>pendaftaran kursus, serta melihat laporan rekapitulasi sistem secara real-time.</html>");
        lblWelcomeDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblWelcomeDesc.setForeground(COLOR_TEXT_MUTED);
        lblWelcomeDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        welcomeCard.add(lblWelcomeTitle);
        welcomeCard.add(Box.createRigidArea(new Dimension(0, 12)));
        welcomeCard.add(lblWelcomeDesc);

        centerContainer.add(welcomeCard);
        mainPanel.add(centerContainer, BorderLayout.CENTER);

        // --- MENU BAR ATAS ---
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(252, 231, 243)));

        // 1. Menu Master
        menuMaster = new JMenu("   Master   ");
        menuMaster.setFont(new Font("Segoe UI", Font.BOLD, 13));
        miPeserta = new JMenuItem("Master Peserta");
        miInstruktur = new JMenuItem("Master Instruktur");
        miKursus = new JMenuItem("Master Kursus");
        miKelas = new JMenuItem("Master Kelas");

        miPeserta.addActionListener(this::miPesertaActionPerformed);
        miInstruktur.addActionListener(this::miInstrukturActionPerformed);
        miKursus.addActionListener(this::miKursusActionPerformed);
        miKelas.addActionListener(this::miKelasActionPerformed);

        menuMaster.add(miPeserta);
        menuMaster.add(miInstruktur);
        menuMaster.add(miKursus);
        menuMaster.add(miKelas);
        menuBar.add(menuMaster);

        // 2. Menu Transaksi
        menuTransaksi = new JMenu("   Transaksi   ");
        menuTransaksi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        miPendaftaran = new JMenuItem("Pendaftaran");
        miPembayaran = new JMenuItem("Pembayaran");

        miPendaftaran.addActionListener(this::miPendaftaranActionPerformed);
        miPembayaran.addActionListener(this::miPembayaranActionPerformed);

        menuTransaksi.add(miPendaftaran);
        menuTransaksi.add(miPembayaran);
        menuBar.add(menuTransaksi);

        // 3. Menu Laporan
        menuLaporan = new JMenu("   Laporan   ");
        menuLaporan.setFont(new Font("Segoe UI", Font.BOLD, 13));
        miLaporanPeserta = new JMenuItem("Laporan Peserta");
        miLaporanPendaftaran = new JMenuItem("Laporan Pendaftaran");

        miLaporanPeserta.addActionListener(this::miLaporanPesertaActionPerformed);
        miLaporanPendaftaran.addActionListener(this::miLaporanPendaftaranActionPerformed);

        menuLaporan.add(miLaporanPeserta);
        menuLaporan.add(miLaporanPendaftaran);
        menuBar.add(menuLaporan);

        // 4. Menu Keluar
        menuKeluar = new JMenu("   Keluar   ");
        menuKeluar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        miLogout = new JMenuItem("Logout");
        miTutup = new JMenuItem("Tutup Aplikasi");

        miLogout.addActionListener(this::miLogoutActionPerformed);
        miTutup.addActionListener(this::miTutupActionPerformed);

        menuKeluar.add(miLogout);
        menuKeluar.addSeparator();
        menuKeluar.add(miTutup);
        menuBar.add(menuKeluar);

        setJMenuBar(menuBar);

        // Memuat Ikon Portable
        miPeserta.setIcon(loadIcon("/icons/peserta.png"));
        miInstruktur.setIcon(loadIcon("/icons/instruktur.png"));
        miKursus.setIcon(loadIcon("/icons/kursus.png"));
        miKelas.setIcon(loadIcon("/icons/kelas.png"));

        // --- POPUP MENU KLIK KANAN ---
        popupDashboard = new JPopupMenu();
        miTentang = new JMenuItem("Tentang Aplikasi");
        miRefresh = new JMenuItem("Refresh Tampilan");

        miTentang.addActionListener(this::miTentangActionPerformed);
        miRefresh.addActionListener(e -> JOptionPane.showMessageDialog(this, "Dashboard berhasil disegarkan."));

        popupDashboard.add(miTentang);
        popupDashboard.add(miRefresh);

        mainPanel.setComponentPopupMenu(popupDashboard);
        centerContainer.setComponentPopupMenu(popupDashboard);
    }

    private ImageIcon loadIcon(String path) {
        URL url = getClass().getResource(path);
        if (url == null) return null;
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void applySession() {
        User user = UserSession.getCurrentUser();
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Session tidak ditemukan. Silakan login ulang.");
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
            return;
        }

        lblUser.setText("User: " + user.getUsername());
        lblRole.setText("Role: " + user.getRole());

        boolean admin = user.isAdmin();
        miInstruktur.setEnabled(admin);
        miKursus.setEnabled(admin);
        miKelas.setEnabled(admin);
        miPeserta.setEnabled(true);
        menuTransaksi.setEnabled(true);
        menuLaporan.setEnabled(true);
    }

    // --- PENGARAHAN MENU ---
    private void miPesertaActionPerformed(ActionEvent evt) {
        WindowManager.open(MasterPeserta.class, MasterPeserta::new);
    }

    private void miInstrukturActionPerformed(ActionEvent evt) {
        WindowManager.open(InstrukturFrame.class, InstrukturFrame::new);
    }

    private void miKursusActionPerformed(ActionEvent evt) {
        WindowManager.open(FormPendaftaranKursus.class, FormPendaftaranKursus::new);
    }

    private void miKelasActionPerformed(ActionEvent evt) {
        WindowManager.open(FormPeserta.class, FormPeserta::new);
    }

    private void miPendaftaranActionPerformed(ActionEvent evt) {
        WindowManager.open(FormPendaftaranKursus.class, FormPendaftaranKursus::new);
    }

    private void miPembayaranActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Modul Pembayaran sedang dikembangkan pada tahap project berikutnya.");
    }

    private void miLaporanPesertaActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Modul laporan diaktifkan pada Pertemuan 12 dengan Jaspersoft Studio.");
    }

    private void miLaporanPendaftaranActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Modul laporan diaktifkan pada Pertemuan 12 dengan Jaspersoft Studio.");
    }

    private void miLogoutActionPerformed(ActionEvent evt) {
        int pilihan = JOptionPane.showConfirmDialog(this, "Logout dari aplikasi?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
        if (pilihan == JOptionPane.YES_OPTION) {
            WindowManager.closeAll();
            UserSession.clear();
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        }
    }

    private void miTutupActionPerformed(ActionEvent evt) {
        int pilihan = JOptionPane.showConfirmDialog(this, "Tutup aplikasi SIKURSUS?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (pilihan == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void miTentangActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "SIKURSUS Desktop Management\nProject PBO Program Studi Informatika");
    }
}