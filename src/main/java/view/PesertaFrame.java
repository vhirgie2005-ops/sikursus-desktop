package view;

import dao.PesertaDAO;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Peserta;
import report.ReportService;

public class PesertaFrame extends JFrame {
    private final JTextField nim = new JTextField();
    private final JTextField nama = new JTextField();
    private final JTextField prodi = new JTextField();
    private final JTextField hp = new JTextField();
    private final JTextField email = new JTextField();
    private final JTextField search = new JTextField(18);
    private final JComboBox<String> jk = new JComboBox<>(new String[]{"L", "P"});
    private final JTable table = new JTable(ViewUtil.tableModel("ID", "NIM", "Nama", "JK", "Prodi", "No. HP", "Email"));
    private final PesertaDAO dao = new PesertaDAO();
    private int selected = -1;

    public PesertaFrame() {
        super("Master Peserta - SIKURSUS Desktop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ViewUtil.setupFrame(this, 1000, 720);
        build();
        load("");
    }

    private void build() {
        JPanel root = ViewUtil.root("Master Data Peserta");
        JPanel body = ViewUtil.body();

        JPanel form = ViewUtil.formPanel();
        ViewUtil.addFormRow(form, 0, "NIM", nim);
        ViewUtil.addFormRow(form, 1, "Nama Peserta", nama);
        ViewUtil.addFormRow(form, 2, "Jenis Kelamin", jk);
        ViewUtil.addFormRow(form, 3, "Program Studi", prodi);
        ViewUtil.addFormRow(form, 4, "Nomor HP", hp);
        ViewUtil.addFormRow(form, 5, "Email", email);

        JButton simpan = ViewUtil.primaryButton("Simpan");
        JButton ubah = ViewUtil.updateButton("Ubah");
        JButton hapus = ViewUtil.dangerButton("Hapus");
        JButton reset = ViewUtil.neutralButton("Reset");
        JButton cetak = ViewUtil.successButton("Cetak Semua");
        JPanel formBox = new JPanel(new BorderLayout());
        formBox.setBackground(ViewUtil.BG);
        formBox.add(form, BorderLayout.CENTER);
        formBox.add(ViewUtil.actionPanel(simpan, ubah, hapus, reset, cetak), BorderLayout.SOUTH);

        JButton cari = ViewUtil.updateButton("Cari");
        JPanel tableBox = new JPanel(new BorderLayout(0, 8));
        tableBox.setBackground(ViewUtil.CARD);
        tableBox.add(ViewUtil.searchPanel("Pencarian", search, cari), BorderLayout.NORTH);
        tableBox.add(ViewUtil.tableScroll(table), BorderLayout.CENTER);

        body.add(ViewUtil.section("Form Peserta", formBox), BorderLayout.NORTH);
        body.add(ViewUtil.section("Data Peserta", tableBox), BorderLayout.CENTER);
        root.add(body, BorderLayout.CENTER);
        setContentPane(root);

        simpan.addActionListener(e -> save());
        ubah.addActionListener(e -> update());
        hapus.addActionListener(e -> delete());
        reset.addActionListener(e -> clear());
        cari.addActionListener(e -> load(search.getText()));
        search.addActionListener(e -> load(search.getText()));
        cetak.addActionListener(e -> ReportService.showPeserta(this));
        table.getSelectionModel().addListSelectionListener(e -> pick());
        getRootPane().setDefaultButton(simpan);
    }

    private Peserta data() {
        Peserta p = new Peserta();
        p.setId(Math.max(selected, 0));
        p.setNim(nim.getText());
        p.setNama(nama.getText());
        p.setJenisKelamin(jk.getSelectedItem().toString());
        p.setProdi(prodi.getText());
        p.setNoHp(hp.getText());
        p.setEmail(email.getText());
        return p;
    }

    private void save() {
        try {
            dao.insert(data());
            load("");
            clear();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
            ViewUtil.sqlError(this, "Menyimpan peserta", ex);
        }
    }

    private void update() {
        if (selected < 0) {
            JOptionPane.showMessageDialog(this, "Pilih baris peserta.");
            return;
        }
        try {
            dao.update(data());
            load("");
            clear();
        } catch (Exception ex) {
            handle(ex, "Mengubah peserta");
        }
    }

    private void delete() {
        if (selected < 0) return;
        if (JOptionPane.showConfirmDialog(this, "Hapus peserta terpilih?", "Konfirmasi", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        try {
            dao.delete(selected);
            load("");
            clear();
        } catch (SQLException ex) {
            ViewUtil.sqlError(this, "Menghapus peserta", ex);
        }
    }

    private void load(String q) {
        try {
            List<Peserta> data = dao.search(q);
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            m.setRowCount(0);
            for (Peserta p : data) {
                m.addRow(new Object[]{p.getId(), p.getNim(), p.getNama(), p.getJenisKelamin(), p.getProdi(), p.getNoHp(), p.getEmail()});
            }
        } catch (SQLException ex) {
            ViewUtil.sqlError(this, "Memuat peserta", ex);
        }
    }

    private void pick() {
        int r = table.getSelectedRow();
        if (r < 0) return;
        selected = Integer.parseInt(table.getValueAt(r, 0).toString());
        nim.setText(table.getValueAt(r, 1).toString());
        nama.setText(table.getValueAt(r, 2).toString());
        jk.setSelectedItem(table.getValueAt(r, 3));
        prodi.setText(table.getValueAt(r, 4).toString());
        hp.setText(String.valueOf(table.getValueAt(r, 5)));
        email.setText(String.valueOf(table.getValueAt(r, 6)));
    }

    private void clear() {
        selected = -1;
        nim.setText("");
        nama.setText("");
        prodi.setText("");
        hp.setText("");
        email.setText("");
        jk.setSelectedIndex(0);
        table.clearSelection();
        nim.requestFocus();
    }

    private void handle(Exception ex, String action) {
        if (ex instanceof SQLException) ViewUtil.sqlError(this, action, (SQLException) ex);
        else JOptionPane.showMessageDialog(this, ex.getMessage());
    }
}
