package view;

import dao.PembayaranDAO;
import dao.PendaftaranDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Pembayaran;
import report.ReportService;
import util.LookupItem;
import util.Validation;

public class PembayaranFrame extends JFrame {
    private final JComboBox<LookupItem> pendaftaran = new JComboBox<>();
    private final JTextField tanggal = new JTextField(ViewUtil.today());
    private final JTextField nominal = new JTextField();
    private final JTextField search = new JTextField(18);
    private final JTextField awal = new JTextField(ViewUtil.today());
    private final JTextField akhir = new JTextField(ViewUtil.today());
    private final JComboBox<String> metode = new JComboBox<>(new String[]{"TUNAI", "TRANSFER", "QRIS"});
    private final JComboBox<String> status = new JComboBox<>(new String[]{"MENUNGGU", "LUNAS", "BATAL"});
    private final JTable table = new JTable(ViewUtil.tableModel("ID", "Pendaftaran", "Peserta", "Kelas", "Tanggal", "Nominal", "Metode", "Status"));
    private final PembayaranDAO dao = new PembayaranDAO();
    private int selected = -1;

    public PembayaranFrame() {
        super("Transaksi Pembayaran - SIKURSUS Desktop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ViewUtil.setupFrame(this, 1150, 740);
        build();
        loadLookups();
        load("");
    }

    private void build() {
        JPanel root = ViewUtil.root("Transaksi Pembayaran");
        JPanel body = ViewUtil.body();
        JPanel form = ViewUtil.formPanel();
        ViewUtil.addFormRow(form, 0, "Pendaftaran", pendaftaran);
        ViewUtil.addFormRow(form, 1, "Tanggal Bayar (yyyy-MM-dd)", tanggal);
        ViewUtil.addFormRow(form, 2, "Nominal", nominal);
        ViewUtil.addFormRow(form, 3, "Metode", metode);
        ViewUtil.addFormRow(form, 4, "Status", status);

        JButton simpan = ViewUtil.primaryButton("Simpan");
        JButton ubah = ViewUtil.updateButton("Ubah");
        JButton hapus = ViewUtil.dangerButton("Hapus");
        JButton reset = ViewUtil.neutralButton("Reset");
        JButton semua = ViewUtil.successButton("Cetak Semua");
        JPanel formBox = new JPanel(new BorderLayout());
        formBox.setBackground(ViewUtil.BG);
        formBox.add(form, BorderLayout.CENTER);
        formBox.add(ViewUtil.actionPanel(simpan, ubah, hapus, reset, semua), BorderLayout.SOUTH);

        JButton cari = ViewUtil.updateButton("Cari");
        JButton periode = ViewUtil.successButton("Cetak Periode");
        JPanel filter = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 7));
        filter.setBackground(ViewUtil.BG);
        filter.add(new JLabel("Cari"));
        ViewUtil.styleInput(search); search.setPreferredSize(new java.awt.Dimension(210, 34)); filter.add(search); filter.add(cari);
        filter.add(Box.createHorizontalStrut(12));
        filter.add(new JLabel("Awal")); ViewUtil.styleInput(awal); awal.setPreferredSize(new java.awt.Dimension(120, 34)); filter.add(awal);
        filter.add(new JLabel("Akhir")); ViewUtil.styleInput(akhir); akhir.setPreferredSize(new java.awt.Dimension(120, 34)); filter.add(akhir);
        filter.add(periode);

        JPanel tableBox = new JPanel(new BorderLayout(0, 8));
        tableBox.setBackground(ViewUtil.CARD);
        tableBox.add(filter, BorderLayout.NORTH);
        tableBox.add(ViewUtil.tableScroll(table), BorderLayout.CENTER);

        body.add(ViewUtil.section("Form Pembayaran", formBox), BorderLayout.NORTH);
        body.add(ViewUtil.section("Data Pembayaran", tableBox), BorderLayout.CENTER);
        root.add(body, BorderLayout.CENTER);
        setContentPane(root);

        simpan.addActionListener(e -> save());
        ubah.addActionListener(e -> update());
        hapus.addActionListener(e -> delete());
        reset.addActionListener(e -> clear());
        semua.addActionListener(e -> ReportService.showPembayaranSemua(this));
        periode.addActionListener(e -> period());
        cari.addActionListener(e -> load(search.getText()));
        search.addActionListener(e -> load(search.getText()));
        table.getSelectionModel().addListSelectionListener(e -> pick());
        getRootPane().setDefaultButton(simpan);
    }

    private void loadLookups() {
        try {
            pendaftaran.removeAllItems();
            for (LookupItem x : new PendaftaranDAO().lookup()) pendaftaran.addItem(x);
        } catch (SQLException ex) { ViewUtil.sqlError(this, "Memuat pilihan pembayaran", ex); }
    }

    private Pembayaran data() {
        LookupItem p = (LookupItem) pendaftaran.getSelectedItem();
        if (p == null) throw new IllegalArgumentException("Pendaftaran wajib tersedia");
        Pembayaran x = new Pembayaran();
        x.setIdPembayaran(Math.max(selected, 0));
        x.setIdPendaftaran(p.getId());
        x.setTanggalBayar(Validation.date(tanggal.getText(), "Tanggal bayar"));
        x.setNominal(Validation.positiveMoney(nominal.getText(), "Nominal"));
        x.setMetode(metode.getSelectedItem().toString());
        x.setStatus(status.getSelectedItem().toString());
        return x;
    }

    private void save() { try { dao.insert(data()); load(""); clear(); } catch (Exception ex) { handle(ex, "Menyimpan pembayaran"); } }
    private void update() { if (selected < 0) { JOptionPane.showMessageDialog(this, "Pilih pembayaran."); return; } try { dao.update(data()); load(""); clear(); } catch (Exception ex) { handle(ex, "Mengubah pembayaran"); } }
    private void delete() { if (selected < 0) return; if (JOptionPane.showConfirmDialog(this, "Hapus pembayaran?", "Konfirmasi", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return; try { dao.delete(selected); load(""); clear(); } catch (SQLException ex) { ViewUtil.sqlError(this, "Menghapus pembayaran", ex); } }

    private void load(String q) {
        try {
            List<Pembayaran> list = dao.search(q);
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            m.setRowCount(0);
            for (Pembayaran x : list) m.addRow(new Object[]{x.getIdPembayaran(), x.getIdPendaftaran(), x.getNamaPeserta(), x.getNamaKelas(), x.getTanggalBayar(), x.getNominal(), x.getMetode(), x.getStatus()});
        } catch (SQLException ex) { ViewUtil.sqlError(this, "Memuat pembayaran", ex); }
    }

    private void pick() {
        int r = table.getSelectedRow();
        if (r < 0) return;
        selected = Integer.parseInt(table.getValueAt(r, 0).toString());
        int pid = Integer.parseInt(table.getValueAt(r, 1).toString());
        ViewUtil.selectLookup(pendaftaran, pid);
        tanggal.setText(table.getValueAt(r, 4).toString());
        nominal.setText(table.getValueAt(r, 5).toString());
        metode.setSelectedItem(table.getValueAt(r, 6));
        status.setSelectedItem(table.getValueAt(r, 7));
    }

    private void period() {
        try {
            Date a = Validation.date(awal.getText(), "Tanggal awal");
            Date b = Validation.date(akhir.getText(), "Tanggal akhir");
            if (a.after(b)) throw new IllegalArgumentException("Tanggal awal tidak boleh setelah tanggal akhir");
            ReportService.showPembayaranPeriode(this, a, b);
        } catch (IllegalArgumentException ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }

    private void clear() {
        selected = -1;
        tanggal.setText(ViewUtil.today());
        nominal.setText("");
        metode.setSelectedIndex(0);
        status.setSelectedIndex(0);
        table.clearSelection();
        loadLookups();
        pendaftaran.requestFocus();
    }

    private void handle(Exception ex, String action) { if (ex instanceof SQLException) ViewUtil.sqlError(this, action, (SQLException) ex); else JOptionPane.showMessageDialog(this, ex.getMessage()); }
}
