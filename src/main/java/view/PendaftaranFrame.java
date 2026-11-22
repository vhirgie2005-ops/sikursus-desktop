package view;

import dao.KelasDAO;
import dao.PendaftaranDAO;
import dao.PesertaDAO;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Pendaftaran;
import report.ReportService;
import util.LookupItem;
import util.Validation;

public class PendaftaranFrame extends JFrame {
    private final JComboBox<LookupItem> peserta = new JComboBox<>();
    private final JComboBox<LookupItem> kelas = new JComboBox<>();
    private final JTextField tanggal = new JTextField(ViewUtil.today());
    private final JTextField search = new JTextField(18);
    private final JComboBox<String> status = new JComboBox<>(new String[]{"DAFTAR", "AKTIF", "SELESAI", "BATAL"});
    private final JTable table = new JTable(ViewUtil.tableModel("ID", "NIM", "Peserta", "Kelas", "Kursus", "Tanggal", "Status"));
    private final PendaftaranDAO dao = new PendaftaranDAO();
    private int selected = -1;

    public PendaftaranFrame() {
        super("Transaksi Pendaftaran - SIKURSUS Desktop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ViewUtil.setupFrame(this, 1080, 700);
        build();
        loadLookups();
        load("");
    }

    private void build() {
        JPanel root = ViewUtil.root("Transaksi Pendaftaran Kursus");
        JPanel body = ViewUtil.body();
        JPanel form = ViewUtil.formPanel();
        ViewUtil.addFormRow(form, 0, "Peserta", peserta);
        ViewUtil.addFormRow(form, 1, "Kelas", kelas);
        ViewUtil.addFormRow(form, 2, "Tanggal Daftar (yyyy-MM-dd)", tanggal);
        ViewUtil.addFormRow(form, 3, "Status", status);

        JButton simpan = ViewUtil.primaryButton("Simpan");
        JButton ubah = ViewUtil.updateButton("Ubah");
        JButton hapus = ViewUtil.dangerButton("Hapus");
        JButton reset = ViewUtil.neutralButton("Reset");
        JButton semua = ViewUtil.successButton("Cetak Semua");
        JButton terpilih = ViewUtil.successButton("Cetak Terpilih");
        JPanel formBox = new JPanel(new BorderLayout());
        formBox.setBackground(ViewUtil.BG);
        formBox.add(form, BorderLayout.CENTER);
        formBox.add(ViewUtil.actionPanel(simpan, ubah, hapus, reset, semua, terpilih), BorderLayout.SOUTH);

        JButton cari = ViewUtil.updateButton("Cari");
        JPanel tableBox = new JPanel(new BorderLayout(0, 8));
        tableBox.setBackground(ViewUtil.CARD);
        tableBox.add(ViewUtil.searchPanel("Pencarian", search, cari), BorderLayout.NORTH);
        tableBox.add(ViewUtil.tableScroll(table), BorderLayout.CENTER);

        body.add(ViewUtil.section("Form Pendaftaran", formBox), BorderLayout.NORTH);
        body.add(ViewUtil.section("Data Pendaftaran", tableBox), BorderLayout.CENTER);
        root.add(body, BorderLayout.CENTER);
        setContentPane(root);

        simpan.addActionListener(e -> save());
        ubah.addActionListener(e -> update());
        hapus.addActionListener(e -> delete());
        reset.addActionListener(e -> clear());
        semua.addActionListener(e -> ReportService.showPendaftaranSemua(this));
        terpilih.addActionListener(e -> {
            if (selected < 0) JOptionPane.showMessageDialog(this, "Pilih baris pendaftaran.");
            else ReportService.showPendaftaranById(this, selected);
        });
        cari.addActionListener(e -> load(search.getText()));
        search.addActionListener(e -> load(search.getText()));
        table.getSelectionModel().addListSelectionListener(e -> pick());
        getRootPane().setDefaultButton(simpan);
    }

    private void loadLookups() {
        try {
            peserta.removeAllItems();
            for (LookupItem x : new PesertaDAO().lookup()) peserta.addItem(x);
            kelas.removeAllItems();
            for (LookupItem x : new KelasDAO().lookup()) kelas.addItem(x);
        } catch (SQLException ex) { ViewUtil.sqlError(this, "Memuat pilihan pendaftaran", ex); }
    }

    private Pendaftaran data() {
        LookupItem p = (LookupItem) peserta.getSelectedItem();
        LookupItem k = (LookupItem) kelas.getSelectedItem();
        if (p == null || k == null) throw new IllegalArgumentException("Peserta dan kelas wajib tersedia");
        Pendaftaran x = new Pendaftaran();
        x.setIdPendaftaran(Math.max(selected, 0));
        x.setIdPeserta(p.getId());
        x.setIdKelas(k.getId());
        x.setTanggalDaftar(Validation.date(tanggal.getText(), "Tanggal daftar"));
        x.setStatus(status.getSelectedItem().toString());
        return x;
    }

    private void save() { try { dao.insert(data()); load(""); clear(); } catch (Exception ex) { handle(ex, "Menyimpan pendaftaran"); } }
    private void update() { if (selected < 0) { JOptionPane.showMessageDialog(this, "Pilih pendaftaran."); return; } try { dao.update(data()); load(""); clear(); } catch (Exception ex) { handle(ex, "Mengubah pendaftaran"); } }
    private void delete() { if (selected < 0) return; if (JOptionPane.showConfirmDialog(this, "Hapus pendaftaran? Pembayaran terkait harus dihapus lebih dahulu.", "Konfirmasi", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return; try { dao.delete(selected); load(""); clear(); } catch (SQLException ex) { ViewUtil.sqlError(this, "Menghapus pendaftaran", ex); } }

    private void load(String q) {
        try {
            List<Pendaftaran> list = dao.search(q);
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            m.setRowCount(0);
            for (Pendaftaran x : list) m.addRow(new Object[]{x.getIdPendaftaran(), x.getNim(), x.getNamaPeserta(), x.getNamaKelas(), x.getNamaKursus(), x.getTanggalDaftar(), x.getStatus()});
        } catch (SQLException ex) { ViewUtil.sqlError(this, "Memuat pendaftaran", ex); }
    }

    private void pick() {
        int r = table.getSelectedRow();
        if (r < 0) return;
        selected = Integer.parseInt(table.getValueAt(r, 0).toString());
        try {
            for (Pendaftaran x : dao.findAll()) {
                if (x.getIdPendaftaran() == selected) {
                    ViewUtil.selectLookup(peserta, x.getIdPeserta());
                    ViewUtil.selectLookup(kelas, x.getIdKelas());
                    tanggal.setText(x.getTanggalDaftar().toString());
                    status.setSelectedItem(x.getStatus());
                    break;
                }
            }
        } catch (SQLException ex) { ViewUtil.sqlError(this, "Membaca pendaftaran", ex); }
    }

    private void clear() {
        selected = -1;
        tanggal.setText(ViewUtil.today());
        status.setSelectedIndex(0);
        table.clearSelection();
        loadLookups();
        peserta.requestFocus();
    }

    private void handle(Exception ex, String action) { if (ex instanceof SQLException) ViewUtil.sqlError(this, action, (SQLException) ex); else JOptionPane.showMessageDialog(this, ex.getMessage()); }
}
