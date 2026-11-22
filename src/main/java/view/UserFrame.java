package view;

import dao.UserDAO;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.User;
import util.UserSession;

public class UserFrame extends JFrame {
    private final JTextField txtUsername = new JTextField();
    private final JPasswordField txtPassword = new JPasswordField();
    private final JComboBox<String> cmbRole = new JComboBox<>(new String[]{"ADMIN", "OPERATOR"});
    private final JCheckBox chkAktif = new JCheckBox("Aktif", true);
    private final JTable table = new JTable(ViewUtil.tableModel("ID", "Username", "Role", "Aktif"));
    private final UserDAO dao = new UserDAO();
    private int selected = -1;

    public UserFrame() {
        super("Master Users - SIKURSUS Desktop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ViewUtil.setupFrame(this, 860, 650);
        build();
        load();
    }

    private void build() {
        JPanel root = ViewUtil.root("Manajemen User & Role");
        JPanel body = ViewUtil.body();
        JPanel form = ViewUtil.formPanel();
        ViewUtil.addFormRow(form, 0, "Username", txtUsername);
        ViewUtil.addFormRow(form, 1, "Password / Password Baru", txtPassword);
        ViewUtil.addFormRow(form, 2, "Role", cmbRole);
        ViewUtil.addFormRow(form, 3, "Status Akun", chkAktif);

        JButton tambah = ViewUtil.primaryButton("Tambah");
        JButton update = ViewUtil.updateButton("Ubah Role/Status");
        JButton resetPass = ViewUtil.successButton("Reset Password");
        JButton hapus = ViewUtil.dangerButton("Hapus");
        JButton clear = ViewUtil.neutralButton("Reset Form");
        JPanel formBox = new JPanel(new BorderLayout());
        formBox.setBackground(ViewUtil.BG);
        formBox.add(form, BorderLayout.CENTER);
        formBox.add(ViewUtil.actionPanel(tambah, update, resetPass, hapus, clear), BorderLayout.SOUTH);

        JPanel tableBox = new JPanel(new BorderLayout());
        tableBox.setBackground(ViewUtil.CARD);
        tableBox.add(ViewUtil.tableScroll(table), BorderLayout.CENTER);

        body.add(ViewUtil.section("Form User", formBox), BorderLayout.NORTH);
        body.add(ViewUtil.section("Daftar User", tableBox), BorderLayout.CENTER);
        root.add(body, BorderLayout.CENTER);
        setContentPane(root);

        tambah.addActionListener(e -> create());
        update.addActionListener(e -> update());
        resetPass.addActionListener(e -> resetPassword());
        hapus.addActionListener(e -> delete());
        clear.addActionListener(e -> clear());
        table.getSelectionModel().addListSelectionListener(e -> pick());
        getRootPane().setDefaultButton(tambah);
    }

    private void load() {
        try {
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            m.setRowCount(0);
            for (User u : dao.findAll()) m.addRow(new Object[]{u.getIdUser(), u.getUsername(), u.getRole(), u.isAktif()});
        } catch (SQLException ex) { ViewUtil.sqlError(this, "Memuat users", ex); }
    }

    private void pick() {
        int r = table.getSelectedRow();
        if (r < 0) return;
        selected = (Integer) table.getValueAt(r, 0);
        txtUsername.setText(table.getValueAt(r, 1).toString());
        txtUsername.setEditable(false);
        cmbRole.setSelectedItem(table.getValueAt(r, 2));
        chkAktif.setSelected((Boolean) table.getValueAt(r, 3));
    }

    private void create() {
        char[] p = txtPassword.getPassword();
        try {
            if (txtUsername.getText().trim().isEmpty()) throw new IllegalArgumentException("Username wajib diisi");
            dao.create(txtUsername.getText().trim(), p, cmbRole.getSelectedItem().toString(), chkAktif.isSelected());
            load();
            clear();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
            ViewUtil.sqlError(this, "Menambah user", ex);
        } finally { Arrays.fill(p, '\0'); }
    }

    private void update() {
        if (selected < 0) { JOptionPane.showMessageDialog(this, "Pilih user."); return; }
        try {
            dao.updateRoleStatus(selected, cmbRole.getSelectedItem().toString(), chkAktif.isSelected());
            load();
        } catch (SQLException ex) { ViewUtil.sqlError(this, "Mengubah user", ex); }
    }

    private void resetPassword() {
        if (selected < 0) { JOptionPane.showMessageDialog(this, "Pilih user."); return; }
        char[] p = txtPassword.getPassword();
        try {
            dao.resetPassword(selected, p);
            JOptionPane.showMessageDialog(this, "Password direset.");
            txtPassword.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally { Arrays.fill(p, '\0'); }
    }

    private void delete() {
        if (selected < 0) return;
        if (UserSession.getCurrentUser() != null && selected == UserSession.getCurrentUser().getIdUser()) {
            JOptionPane.showMessageDialog(this, "Akun yang sedang dipakai tidak boleh dihapus.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Hapus user terpilih?", "Konfirmasi", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        try { dao.delete(selected); load(); clear(); }
        catch (SQLException ex) { ViewUtil.sqlError(this, "Menghapus user", ex); }
    }

    private void clear() {
        selected = -1;
        txtUsername.setText("");
        txtUsername.setEditable(true);
        txtPassword.setText("");
        cmbRole.setSelectedIndex(1);
        chkAktif.setSelected(true);
        table.clearSelection();
        txtUsername.requestFocus();
    }
}
