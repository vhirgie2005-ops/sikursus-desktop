package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class KursusFrame extends JFrame {

    private JTextField txtKodeKursus, txtNamaKursus, txtBiaya;
    private JComboBox<String> cmbLevel;
    private JTable table;
    private JButton btnSimpan, btnEdit, btnHapus, btnReset;

    // --- PALET WARNA PINK MODERN & ELEGAN (MENYESUAIKAN DASHBOARD) ---
    private static final Color COLOR_PRIMARY = new Color(131, 24, 67);      // Rose/Berry Pekat Elegan (#831843)
    private static final Color COLOR_BG = new Color(253, 242, 248);         // Soft Pink Background Sangat Bersih (#FDF2F8)
    private static final Color COLOR_TEXT_DARK = new Color(50, 20, 40);     // Teks gelap dengan sentuhan hangat
    private static final Color COLOR_MUTED_BG = new Color(252, 231, 243);   // Soft Pink untuk tombol sekunder

    public KursusFrame() {
        setTitle("Master Kursus - SIKURSUS Desktop");
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
        
        JLabel lblTitle = new JLabel("Form Master Kursus");
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

        // Kode Kursus
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblKode = new JLabel("Kode Kursus");
        lblKode.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblKode.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblKode, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtKodeKursus = new JTextField(20);
        txtKodeKursus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        contentPanel.add(txtKodeKursus, gbc);

        // Nama Kursus
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        JLabel lblNama = new JLabel("Nama Kursus");
        lblNama.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNama.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblNama, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNamaKursus = new JTextField(20);
        txtNamaKursus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        contentPanel.add(txtNamaKursus, gbc);

        // Level
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblLevel = new JLabel("Level");
        lblLevel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblLevel.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblLevel, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbLevel = new JComboBox<>(new String[]{"BEGINNER", "INTERMEDIATE", "ADVANCED"});
        cmbLevel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        contentPanel.add(cmbLevel, gbc);

        // Biaya
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lblBiaya = new JLabel("Biaya (Rp)");
        lblBiaya.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblBiaya.setForeground(COLOR_TEXT_DARK);
        contentPanel.add(lblBiaya, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtBiaya = new JTextField(20);
        txtBiaya.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        contentPanel.add(txtBiaya, gbc);

        // Tombol Aksi
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        btnPanel.setOpaque(false);
        
        btnSimpan = new JButton("SIMPAN");
        btnSimpan.setBackground(COLOR_PRIMARY);
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSimpan.setFocusPainted(false);
        btnSimpan.addActionListener(e -> JOptionPane.showMessageDialog(this, "Data Kursus berhasil disimpan."));

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
        btnReset.addActionListener(e -> { txtKodeKursus.setText(""); txtNamaKursus.setText(""); txtBiaya.setText(""); });

        btnPanel.add(btnSimpan);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        btnPanel.add(btnReset);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        contentPanel.add(btnPanel, gbc);

        // Tabel
        table = new JTable(new Object[][]{}, new String[]{"Kode Kursus", "Nama Kursus", "Level", "Biaya"});
        table.setRowHeight(24);
        table.getTableHeader().setBackground(COLOR_PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(650, 140));

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        contentPanel.add(scroll, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }
}