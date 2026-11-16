package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class KelasFrame extends JFrame {

    private JTextField txtIdKelas, txtNamaKelas, txtJadwal;
    private JTable table;
    private JButton btnSimpan, btnEdit, btnHapus, btnReset;

    // --- PALET WARNA PINK MODERN & ELEGAN (MENYESUAIKAN DASHBOARD) ---
    private static final Color COLOR_PRIMARY = new Color(131, 24, 67);      // Rose/Berry Pekat Elegan (#831843)
    private static final Color COLOR_BG = new Color(253, 242, 248);         // Soft Pink Background Sangat Bersih (#FDF2F8)
    private static final Color COLOR_TEXT_DARK = new Color(50, 20, 40);     // Teks gelap dengan sentuhan hangat
    private static final Color COLOR_MUTED_BG = new Color(252, 231, 243);   // Soft Pink untuk tombol sekunder

    public KelasFrame() {
        setTitle("Master Kelas - SIKURSUS Desktop");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 520);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(COLOR_BG);
        setContentPane(mainPanel);

        // Header Banner
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(COLOR_PRIMARY);
        headerPanel.setBorder(new EmptyBorder(18, 15, 18, 15));
        
        JLabel lblTitle = new JLabel("Form Master Kelas");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form Content Panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // ID Kelas
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblId = new JLabel("ID Kelas");
        lblId.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblId.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblId, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtIdKelas = new JTextField(20);
        txtIdKelas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        contentPanel.add(txtIdKelas, gbc);

        // Nama Kelas
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        JLabel lblNama = new JLabel("Nama Kelas");
        lblNama.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNama.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblNama, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNamaKelas = new JTextField(20);
        txtNamaKelas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        contentPanel.add(txtNamaKelas, gbc);

        // Jadwal
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblJadwal = new JLabel("Jadwal Pertemuan");
        lblJadwal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblJadwal.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblJadwal, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtJadwal = new JTextField(20);
        txtJadwal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        contentPanel.add(txtJadwal, gbc);

        // Tombol Aksi
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        btnPanel.setOpaque(false);
        
        btnSimpan = new JButton("SIMPAN");
        btnSimpan.setBackground(COLOR_PRIMARY);
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSimpan.setFocusPainted(false);
        btnSimpan.addActionListener(e -> JOptionPane.showMessageDialog(this, "Data Kelas berhasil disimpan."));

        btnEdit = new JButton("EDIT");
        btnEdit.setBackground(COLOR_MUTED_BG);
        btnEdit.setForeground(COLOR_PRIMARY);
        btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEdit.setFocusPainted(false);

        btnHapus = new JButton("HAPUS");
        btnHapus.setBackground(COLOR_MUTED_BG);
        btnHapus.setForeground(COLOR_PRIMARY);
        btnHapus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnHapus.setFocusPainted(false);

        btnReset = new JButton("RESET");
        btnReset.setBackground(COLOR_MUTED_BG);
        btnReset.setForeground(COLOR_PRIMARY);
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(e -> { txtIdKelas.setText(""); txtNamaKelas.setText(""); txtJadwal.setText(""); });

        btnPanel.add(btnSimpan);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        btnPanel.add(btnReset);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        contentPanel.add(btnPanel, gbc);

        // Tabel
        table = new JTable(new Object[][]{}, new String[]{"ID Kelas", "Nama Kelas", "Jadwal Pertemuan"});
        table.setRowHeight(24);
        table.getTableHeader().setBackground(COLOR_PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(650, 160));

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        contentPanel.add(scroll, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }
}