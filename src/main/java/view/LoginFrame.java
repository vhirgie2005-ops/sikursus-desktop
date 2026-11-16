package view;

import dao.UserDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.User;
import util.UserSession;

public class LoginFrame extends JFrame {

    private final UserDAO userDAO = new UserDAO();

    // UI Components
    private JTextField txtUsername;
    private JPasswordField pwdPassword;
    private JButton btnLogin;
    private JButton btnKeluar;
    private JButton btnToggleShow;
    private JLabel lblTitle;
    private JLabel lblSubTitle;
    private JLabel lblUsername;
    private JLabel lblPassword;

    // --- PALET WARNA PINK MODERN & ELEGAN (MENYESUAIKAN DASHBOARD) ---
    private static final Color COLOR_PRIMARY = new Color(131, 24, 67);      // Rose/Berry Pekat Elegan (#831843)
    private static final Color COLOR_PRIMARY_DARK = new Color(157, 23, 77); // Varian lebih gelap untuk border card
    private static final Color COLOR_BG = new Color(253, 242, 248);         // Soft Pink Background Sangat Bersih (#FDF2F8)
    private static final Color COLOR_MUTED_BG = new Color(252, 231, 243);   // Soft Pink untuk border / latar sekunder
    private static final Color COLOR_WHITE = Color.WHITE;
    private static final Color COLOR_GRAY_BORDER = new Color(219, 189, 204);// Border Input Senada Rose

    public LoginFrame() {
        initComponents();
        setLocationRelativeTo(null); // Menampilkan form di tengah layar
    }

    private void initComponents() {
        setTitle("Login - SIKURSUS Desktop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 540);
        setResizable(false);

        // Fonts (Poppins / Segoe UI Fallback)
        Font fontTitle = new Font("Segoe UI", Font.BOLD, 22);
        Font fontSubtitle = new Font("Segoe UI", Font.PLAIN, 12);
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontInput = new Font("Segoe UI", Font.PLAIN, 13);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 13);

        // Main Background Panel (Soft Pink Lembut)
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(COLOR_BG);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(mainPanel);

        // Form Card Panel
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(COLOR_WHITE);
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_PRIMARY_DARK, 2, true),
                new EmptyBorder(30, 30, 30, 30)
        ));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(COLOR_WHITE);
        headerPanel.setLayout(new GridLayout(2, 1, 0, 5));

        lblTitle = new JLabel("SIKURSUS Desktop", SwingConstants.CENTER);
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(COLOR_PRIMARY);

        lblSubTitle = new JLabel("Sistem Informasi Manajemen Kursus & Pelatihan", SwingConstants.CENTER);
        lblSubTitle.setFont(fontSubtitle);
        lblSubTitle.setForeground(new Color(113, 80, 95));

        headerPanel.add(lblTitle);
        headerPanel.add(lblSubTitle);

        // Form Input Panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(COLOR_WHITE);
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.weightx = 1.0;

        // Username Label & Input
        lblUsername = new JLabel("Username");
        lblUsername.setFont(fontLabel);
        lblUsername.setForeground(COLOR_PRIMARY);
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblUsername, gbc);

        txtUsername = new JTextField();
        txtUsername.setFont(fontInput);
        txtUsername.setBackground(COLOR_WHITE);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_GRAY_BORDER, 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(txtUsername, gbc);

        // Password Label & Input
        lblPassword = new JLabel("Password");
        lblPassword.setFont(fontLabel);
        lblPassword.setForeground(COLOR_PRIMARY);
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(lblPassword, gbc);

        // Panel khusus untuk menggabungkan JPasswordField dan Tombol Lihat Password
        JPanel pwdPanel = new JPanel(new BorderLayout(5, 0));
        pwdPanel.setBackground(COLOR_WHITE);

        pwdPassword = new JPasswordField();
        pwdPassword.setFont(fontInput);
        pwdPassword.setBackground(COLOR_WHITE);
        pwdPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_GRAY_BORDER, 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        // Tombol Toggle Lihat/Sembunyikan Password
        btnToggleShow = new JButton("👁");
        btnToggleShow.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
        btnToggleShow.setBackground(COLOR_WHITE);
        btnToggleShow.setForeground(COLOR_PRIMARY);
        btnToggleShow.setFocusPainted(false);
        btnToggleShow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnToggleShow.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_GRAY_BORDER, 1, true),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));

        // Action Listener untuk menampilkan/menyembunyikan password
        btnToggleShow.addActionListener(e -> {
            if (pwdPassword.getEchoChar() != 0) {
                pwdPassword.setEchoChar((char) 0); // Tampilkan password
                btnToggleShow.setText("🔒");
            } else {
                pwdPassword.setEchoChar('•'); // Sembunyikan password kembali
                btnToggleShow.setText("👁");
            }
        });

        pwdPanel.add(pwdPassword, BorderLayout.CENTER);
        pwdPanel.add(btnToggleShow, BorderLayout.EAST);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(pwdPanel, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(COLOR_WHITE);
        buttonPanel.setLayout(new GridLayout(1, 2, 12, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        btnLogin = new JButton("Login");
        btnLogin.setFont(fontButton);
        btnLogin.setBackground(COLOR_PRIMARY);
        btnLogin.setForeground(COLOR_WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        btnKeluar = new JButton("Keluar");
        btnKeluar.setFont(fontButton);
        btnKeluar.setBackground(COLOR_MUTED_BG);
        btnKeluar.setForeground(COLOR_PRIMARY);
        btnKeluar.setFocusPainted(false);
        btnKeluar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnKeluar.setBorder(BorderFactory.createLineBorder(COLOR_PRIMARY, 1, true));
        btnKeluar.addActionListener(this::btnKeluarActionPerformed);

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnKeluar);

        cardPanel.add(headerPanel, BorderLayout.NORTH);
        cardPanel.add(formPanel, BorderLayout.CENTER);
        cardPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(cardPanel);

        getRootPane().setDefaultButton(btnLogin);
    }

    // Event Handler Tombol Login
    private void btnLoginActionPerformed(ActionEvent evt) {
        String username = txtUsername.getText().trim();
        char[] password = pwdPassword.getPassword();

        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Username dan password wajib diisi.",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        btnLogin.setEnabled(false);

        try {
            User user = userDAO.authenticate(username, password);
            if (user == null) {
                JOptionPane.showMessageDialog(this,
                        "Login gagal. Periksa username, password, atau status user.",
                        "Login Gagal",
                        JOptionPane.WARNING_MESSAGE);
                pwdPassword.setText("");
                pwdPassword.requestFocus();
                return;
            }

            UserSession.setCurrentUser(user);
            dispose();
            SwingUtilities.invokeLater(
                    () -> new DashboardFrame().setVisible(true));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Tidak dapat mengakses database: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Arrays.fill(password, '\0');
            btnLogin.setEnabled(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }

    // Event Handler Tombol Keluar
    private void btnKeluarActionPerformed(ActionEvent evt) {
        System.exit(0);
    }
}