package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.sql.Date;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import model.User;
import report.ReportService;
import util.UserSession;
import util.WindowManager;

public class DashboardFrame extends JFrame {
    // Palet warna pink yang selaras dengan SIKURSUS Desktop
    private static final Color PINK_DARK = new Color(176, 75, 118);   // #B04B76 (Header)
    private static final Color PINK_LIGHT = new Color(249, 224, 234); // #F9E0EA (Menu Bar)
    private static final Color PINK_BG = new Color(253, 242, 246);    // #FDF2F6 (Background)

    private final JLabel lblUser = new JLabel();
    private final JLabel lblRole = new JLabel();
    private final JMenuItem miUsers = new JMenuItem("Users");
    private final JMenuItem miPeserta = new JMenuItem("Peserta");
    private final JMenuItem miInstruktur = new JMenuItem("Instruktur");
    private final JMenuItem miKursus = new JMenuItem("Kursus");
    private final JMenuItem miKelas = new JMenuItem("Kelas");

    public DashboardFrame() {
        super("Dashboard - SIKURSUS Desktop");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ViewUtil.setupFrame(this, 1040, 650);
        buildMenu();
        buildBody();
        applySession();
    }

    private void buildBody() {
        JPanel root = ViewUtil.root("SIKURSUS Desktop - Dashboard");
        root.setBackground(PINK_BG);

        // Mengubah warna banner header bawaan ViewUtil menjadi pink tua (#B04B76)
        if (root.getLayout() instanceof BorderLayout) {
            Component north = ((BorderLayout) root.getLayout()).getLayoutComponent(BorderLayout.NORTH);
            if (north instanceof JComponent) {
                north.setBackground(PINK_DARK);
            }
        }

        JPanel body = ViewUtil.body();
        body.setBackground(PINK_BG);

        JPanel session = new JPanel(new GridLayout(2, 1, 2, 2));
        session.setBackground(ViewUtil.CARD);
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUser.setForeground(PINK_DARK);
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblRole.setForeground(ViewUtil.MUTED);
        session.add(lblUser);
        session.add(lblRole);
        body.add(ViewUtil.section("Sesi Pengguna", session), BorderLayout.NORTH);

        JTextArea info = new JTextArea(
                "FINAL PROJECT PBO - Pertemuan 9-14\n\n"
                + "Gunakan menu di bagian atas untuk mengelola master, transaksi, dan laporan.\n\n"
                + "Alur utama:\n"
                + "Master Data -> Kelas -> Pendaftaran -> Pembayaran -> Laporan\n\n"
                + "Database : db_sikursus\n"
                + "Arsitektur: View -> DAO -> JDBC -> MySQL\n\n"
                + "Tips: selesaikan master terlebih dahulu sebelum menguji transaksi."
        );
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        info.setForeground(ViewUtil.TEXT);
        info.setBackground(ViewUtil.CARD);
        info.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        body.add(ViewUtil.section("Ringkasan Sistem", info), BorderLayout.CENTER);

        root.add(body, BorderLayout.CENTER);
        setContentPane(root);
    }

    private void buildMenu() {
        JMenuBar bar = new JMenuBar();
        bar.setBackground(PINK_LIGHT);

        JMenu master = new JMenu("Master");
        master.add(miUsers);
        master.add(miPeserta);
        master.add(miInstruktur);
        master.add(miKursus);
        master.add(miKelas);

        JMenu trx = new JMenu("Transaksi");
        JMenuItem reg = new JMenuItem("Pendaftaran");
        JMenuItem pay = new JMenuItem("Pembayaran");
        trx.add(reg);
        trx.add(pay);

        JMenu rep = new JMenu("Laporan");
        JMenuItem rp = new JMenuItem("Peserta - Semua");
        JMenuItem rr = new JMenuItem("Pendaftaran - Semua");
        JMenuItem rt = new JMenuItem("Pembayaran - Semua");
        JMenuItem rtp = new JMenuItem("Pembayaran - Rentang Tanggal");
        rep.add(rp);
        rep.add(rr);
        rep.add(rt);
        rep.add(rtp);

        JMenu out = new JMenu("Keluar");
        JMenuItem logout = new JMenuItem("Logout");
        JMenuItem exit = new JMenuItem("Tutup Aplikasi");
        out.add(logout);
        out.addSeparator();
        out.add(exit);

        bar.add(master);
        bar.add(trx);
        bar.add(rep);
        bar.add(out);
        setJMenuBar(bar);

        miUsers.addActionListener(e -> WindowManager.open(UserFrame.class, UserFrame::new));
        miPeserta.addActionListener(e -> WindowManager.open(PesertaFrame.class, PesertaFrame::new));
        miInstruktur.addActionListener(e -> WindowManager.open(InstrukturFrame.class, InstrukturFrame::new));
        miKursus.addActionListener(e -> WindowManager.open(KursusFrame.class, KursusFrame::new));
        miKelas.addActionListener(e -> WindowManager.open(KelasFrame.class, KelasFrame::new));
        reg.addActionListener(e -> WindowManager.open(PendaftaranFrame.class, PendaftaranFrame::new));
        pay.addActionListener(e -> WindowManager.open(PembayaranFrame.class, PembayaranFrame::new));
        rp.addActionListener(e -> ReportService.showPeserta(this));
        rr.addActionListener(e -> ReportService.showPendaftaranSemua(this));
        rt.addActionListener(e -> ReportService.showPembayaranSemua(this));
        rtp.addActionListener(e -> showPaymentPeriod());
        logout.addActionListener(e -> logout());
        exit.addActionListener(e -> System.exit(0));

        miPeserta.setIcon(loadIcon("/icons/peserta.png"));
        miInstruktur.setIcon(loadIcon("/icons/instruktur.png"));
        miKursus.setIcon(loadIcon("/icons/kursus.png"));
        miKelas.setIcon(loadIcon("/icons/kelas.png"));
    }

    private ImageIcon loadIcon(String path) {
        URL u = getClass().getResource(path);
        if (u == null) return null;
        Image img = new ImageIcon(u).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void applySession() {
        User u = UserSession.getCurrentUser();
        if (u == null) {
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
            return;
        }
        lblUser.setText("Pengguna: " + u.getUsername());
        lblRole.setText("Role: " + u.getRole());
        boolean admin = u.isAdmin();
        miUsers.setEnabled(admin);
        miInstruktur.setEnabled(admin);
        miKursus.setEnabled(admin);
        miKelas.setEnabled(admin);
        miPeserta.setEnabled(true);
    }

    private void showPaymentPeriod() {
        String a = JOptionPane.showInputDialog(this, "Tanggal awal (yyyy-MM-dd):", ViewUtil.today());
        if (a == null) return;
        String b = JOptionPane.showInputDialog(this, "Tanggal akhir (yyyy-MM-dd):", ViewUtil.today());
        if (b == null) return;
        try {
            ReportService.showPembayaranPeriode(this, Date.valueOf(a.trim()), Date.valueOf(b.trim()));
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Format tanggal harus yyyy-MM-dd.");
        }
    }

    private void logout() {
        WindowManager.closeAll();
        UserSession.clear();
        dispose();
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}