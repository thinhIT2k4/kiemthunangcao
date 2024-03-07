/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DA1.from;

import DA1.model.DangDan;
import JForm.JFAddXuatXu;
import JForm.JFAddMauSac;
import JForm.JFKichCo;
import JForm.JFNSX;
import DA1.model.KichThuoc;
import DA1.model.MauSac;
import DA1.model.NhaSanXuat;
import DA1.model.SanPham;
import DA1.model.SanPhamChiTiet;
import DA1.model.XuatXu;
import DA1.service.DangDanService;
import DA1.service.KichThuocServiec;
import DA1.service.MauSacService;
import DA1.service.NSXService;
import DA1.service.SPCTService;
import DA1.service.SanPhamService;
import DA1.service.XuatXuService;
import JForm.JFDangDan;
import JForm.JFSanPhamChiTiet;
import JForm.JFThungRacSPCT;
import JForm.JFThungRacSanPham;
import JForm.JFrameThemNhanh;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Welcome
 */
public final class From_3 extends javax.swing.JPanel {

    List<SanPham> listSanPham = new ArrayList<>();
    ArrayList<SanPhamChiTiet> listSanPhamCT = new ArrayList<>();
    HashMap<Integer, Integer> clickCounts = new HashMap<>();
    SanPhamService SanPhamService = new SanPhamService();
    XuatXuService xuatXuService = new XuatXuService();
    private int indexRowChoice;
    int i;
    DefaultComboBoxModel cbb = new DefaultComboBoxModel<>();
    DefaultTableModel model = new DefaultTableModel();

    // Tạo một TableRowSorter
    public From_3() {
        initComponents();
        fillToTableSanPham();
        fillToComboBox();
        fillToTableSanPhamChiTiet();// đẩy dữ liệu sang bảng sản phẩm chi tiết
        listSanPhamCT = (ArrayList<SanPhamChiTiet>) SPCTService.selectAll();
        LocByCombobox();

    }

    // clear form
    public void ClearFormSPCT() {
        txtSoLuong.setText("");
        txtGiaBan.setText("");
        ta_moTa.setText("");
        cbbNSX.setSelectedIndex(0);
        cbbXuatXu.setSelectedIndex(0);
        cbbKichThuoc.setSelectedIndex(0);
        cbbMauSac.setSelectedIndex(0);
        cbbDangDan.setSelectedIndex(0);
        cbbSanPham.setSelectedIndex(0);
        fillToComboBox();
    }

    // cler form sản phẩm 
    public void clearFormSP() {
        txtID_sanPham.setText("");
        txtTenSanPham.setText("");
        tblSanPham.clearSelection();
    }

//// đẩy lên combobox
    private void fillToCBB(JComboBox comboBox, List<?> list) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        // Thêm đối tượng "Tất cả" vào model
        model.addElement("Tất cả");

        for (Object item : list) {
            model.addElement(item);
            System.out.println(item);
        }
        comboBox.setModel(model);
    }

    private void fillToCBBLoc(JComboBox comboBox, List<?> list) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        // Thêm đối tượng "Tất cả" vào model
        model.addElement("Thuộc tính");

        for (Object item : list) {
            model.addElement(item);
            System.out.println(item);
        }
        comboBox.setModel(model);
    }

// Đẩy lên các bombobox
    public void fillToComboBox() {

        fillToCBB(cbbSanPham, SanPhamService.selectAll());
        fillToCBB(cbbNSX, NSXService.selectTblThuocTinh());
        fillToCBB(cbbXuatXu, XuatXuService.selectTblThuocTinh());
        fillToCBB(cbbKichThuoc, KichThuocServiec.selectTblThuoTinh());
        fillToCBB(cbbMauSac, MauSacService.selectTblThuoTinh());
        fillToCBB(cbbDangDan, DangDanService.selectTblThuoTinh());
        // đẩy lên cho cbb lọc

        fillToCBBLoc(cbbLocDangDan, DangDanService.selectTblThuoTinh());

    }

    public void LocByCombobox() {
        cbbLocGiaBan.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (cbbLocGiaBan.getSelectedItem().equals("Lựa chọn")) {

                } else {
                    if (cbbLocGiaBan.getSelectedItem().equals("Tăng dần")) {
                        fillToTable(tblSanPhamChiTiet, SPCTService.selectByIdSPTang(idsp), (s, i) -> new Object[]{i, s.getID(), s.getTenSanPham(), s.getNXS(), s.getXuatXu(), s.getKichCo(), s.getMauSac(), s.getDangDan(), s.getGiaBan(), s.getSoLuong()});
                    } else {
                        fillToTable(tblSanPhamChiTiet, SPCTService.selectByIdSPGiam(idsp), (s, i) -> new Object[]{i, s.getID(), s.getTenSanPham(), s.getNXS(), s.getXuatXu(), s.getKichCo(), s.getMauSac(), s.getDangDan(), s.getGiaBan(), s.getSoLuong()});
                    }
                }
            }
        });
    }

    public boolean check() {
        if (txtID_nhanVien.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "không được để trống mã nhân viên");
            return false;
        }
        if (txtID_sanPham.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "không được để trống mã sản phẩm");
            return false;
        }
        if (txtTenSanPham.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "không được để trông tên sản phẩm");
            return false;
        }
        return true;
    }

    // đẩy lên table
    public void fillToTableSanPham() {
        fillToTable(tblSanPham, SanPhamService.selectAll(), (s, i) -> new Object[]{i, s.getID(), s.getTenSP(), s.getId_NhanVien()});
    }

    public void fillToTableSanPhamChiTiet() {
        fillToTable(tblSanPhamChiTiet, SPCTService.selectAll(), (s, i) -> new Object[]{i, s.getID(), s.getTenSanPham(), s.getNXS(), s.getXuatXu(), s.getKichCo(), s.getMauSac(), s.getDangDan(), s.getGiaBan(), s.getSoLuong()});
    }

    public void fillToTableSanPhamChiTietWhereID(int id) {
        fillToTable(tblSanPhamChiTiet, SPCTService.selectByIdSPCT(id), (s, i) -> new Object[]{i, s.getID(), s.getTenSanPham(), s.getNXS(), s.getXuatXu(), s.getKichCo(), s.getMauSac(), s.getDangDan(), s.getGiaBan(), s.getSoLuong()});
    }

    private <T> void fillToTable(JTable table, List<T> list, BiFunction<T, Integer, Object[]> rowMapper) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        int i = 1;
        for (T item : list) {
            model.addRow(rowMapper.apply(item, i++));
        }
        table.setModel(model);
    }

    public SanPham getObj() {
        String ten = txtTenSanPham.getText().trim();
        int Id = Integer.parseInt(txtID_nhanVien.getText());
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm");
            return null;
        } else {
            if (checkTrungTen(ten)) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại");
                return null;
            } else {
                return new SanPham(null, ten, Id);
            }
        }
    }
    // Hàm kiểm tra trùng tên sản phẩm

    public boolean checkTrungTen(String ten) {
        List<SanPham> listSanPham = SanPhamService.selectAll();
        return listSanPham.stream().anyMatch(s -> s.getTenSP().trim().equalsIgnoreCase(ten.trim()));
    }

   public SanPhamChiTiet getObjSPCT() {
    Object selectedItem = cbbSanPham.getSelectedItem();
    if (selectedItem instanceof SanPham) {
        SanPham selectedSanPham = (SanPham) selectedItem;
        int ID_SanPham = Integer.parseInt(selectedSanPham.getID());

        Object nsxItem = cbbNSX.getSelectedItem();
        if (nsxItem == null || !(nsxItem instanceof NhaSanXuat)) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống hoặc chọn một nhà sản xuất");
            return null;
        }
        NhaSanXuat selectedThuongHieu = (NhaSanXuat) nsxItem;
        int ID_ThuongHieu = (selectedThuongHieu.getID());

        Object ktItem = cbbKichThuoc.getSelectedItem();
        if (ktItem == null || !(ktItem instanceof KichThuoc)) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống hoặc chọn một kích thước");
            return null;
        }
        KichThuoc selectedKichThuoc = (KichThuoc) ktItem;
        int ID_KICHTHUOC = (selectedKichThuoc.getID());

        Object msItem = cbbMauSac.getSelectedItem();
        if (msItem == null || !(msItem instanceof MauSac)) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống hoặc chọn một màu sắc");
            return null;
        }
        MauSac selectedMauSac = (MauSac) msItem;
        int ID_MauSac = (selectedMauSac.getId());

        Object xxItem = cbbXuatXu.getSelectedItem();
        if (xxItem == null || !(xxItem instanceof XuatXu)) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống hoặc chọn một xuất xứ");
            return null;
        }
        XuatXu selectedXuatXu = (XuatXu) xxItem;
        int ID_XuatXu = (selectedXuatXu.getId());

        Object ddItem = cbbDangDan.getSelectedItem();
        if (ddItem == null || !(ddItem instanceof DangDan)) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống hoặc chọn một dạng dán");
            return null;
        }
        DangDan selectedDangDan = (DangDan) ddItem;
        int ID_DangDan = (selectedDangDan.getID());

        Long GiaBan = Long.parseLong(txtGiaBan.getText());
        int SoLuong = Integer.parseInt(txtSoLuong.getText());
        int temporaryId = -1;

        String moTa = ta_moTa.getText();
        return new SanPhamChiTiet(temporaryId, ID_SanPham, ID_ThuongHieu, ID_KICHTHUOC, ID_MauSac, ID_XuatXu, ID_DangDan, null, null, null, null, GiaBan, null, null, null, SoLuong, moTa);
    } else {
        // Xử lý trường hợp mà mục đã chọn không phải là một đối tượng SanPham
        JOptionPane.showMessageDialog(this, "Vui lòng không để trống");

        return null;
    }
}


    public void fillToFormSanPham(int row) {
        txtID_sanPham.setText(tblSanPham.getValueAt(row, 1).toString());
        txtTenSanPham.setText(tblSanPham.getValueAt(row, 2).toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        TPSanPham = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtsearchSP = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        btnThemSanPham = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnCapNhatSanPham = new javax.swing.JButton();
        BtnClear = new javax.swing.JButton();
        txtID_sanPham = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID_nhanVien = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbbKichCo = new javax.swing.JLabel();
        cbbSanPham = new javax.swing.JComboBox<>();
        cbbKichCo1 = new javax.swing.JLabel();
        btnMauSac = new javax.swing.JButton();
        btnAddXuatXu = new javax.swing.JButton();
        btnAddKichCo = new javax.swing.JButton();
        BtnThemSoLuong = new javax.swing.JButton();
        btnBotSoLuong = new javax.swing.JButton();
        cbbNSX = new javax.swing.JComboBox<>();
        cbbXuatXu = new javax.swing.JComboBox<>();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        btnAddDangDan = new javax.swing.JButton();
        cbbKichCo2 = new javax.swing.JLabel();
        cbbDangDan = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_moTa = new javax.swing.JTextArea();
        btnAddTenSPCT = new javax.swing.JButton();
        btnAddNSX = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        BtnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        XuatFileExcel = new javax.swing.JButton();
        btnThungRac = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamChiTiet = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        cbbLocDangDan = new javax.swing.JComboBox<>();
        cbbLocGiaBan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtThuocTinhSanPham = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("SẢN PHẨM");

        jPanel2.setPreferredSize(new java.awt.Dimension(750, 481));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN SẢN PHẨM"));

        jLabel5.setText("Tìm kiếm");

        txtsearchSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchSPKeyReleased(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TT", "Mã", "Tên sản phẩm", "Số lượng"
            }
        )

        {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        }
    );
    tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblSanPhamMouseClicked(evt);
        }
    });
    jScrollPane3.setViewportView(tblSanPham);

    jButton4.setText("THÙNG RÁC");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtsearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(40, 40, 40)
                    .addComponent(jButton4))
                .addComponent(jScrollPane3))
            .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtsearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5)
                .addComponent(jButton4))
            .addGap(18, 18, 18)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(193, 193, 193))
    );

    jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("SẢN PHẨM"));

    jLabel3.setText("TÊN SẢN PHẨM");

    jLabel4.setText("MÃ SẢN PHẨM");

    txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtTenSanPhamActionPerformed(evt);
        }
    });

    btnThemSanPham.setText("THÊM MỚI");
    btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnThemSanPhamActionPerformed(evt);
        }
    });

    btnXoaSanPham.setText("XÓA");
    btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnXoaSanPhamActionPerformed(evt);
        }
    });

    btnCapNhatSanPham.setText("CẬP NHẬT");
    btnCapNhatSanPham.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCapNhatSanPhamActionPerformed(evt);
        }
    });

    BtnClear.setText("LÀM MỚI");
    BtnClear.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BtnClearActionPerformed(evt);
        }
    });

    txtID_sanPham.setText("ID");

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
            .addGap(42, 42, 42)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtID_sanPham))
            .addGap(150, 150, 150)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnXoaSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(70, 70, 70)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(BtnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapNhatSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
            .addContainerGap(219, Short.MAX_VALUE))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtID_sanPham)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemSanPham)
                        .addComponent(BtnClear))))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(39, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoaSanPham)
                        .addComponent(btnCapNhatSanPham))
                    .addContainerGap(46, Short.MAX_VALUE))))
    );

    jLabel2.setText("ID nhân viên:  ");

    txtID_nhanVien.setText("5");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2)
            .addGap(18, 18, 18)
            .addComponent(txtID_nhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(136, 136, 136))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(txtID_nhanVien))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(29, 29, 29)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(21, 21, 21))
    );

    TPSanPham.addTab("Sản phẩm", jPanel2);

    jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

    jLabel10.setText("Tên sản phẩm");

    jLabel11.setText("Giá");

    jLabel12.setText("Số lượng");

    jLabel13.setText("Nhà Sản xuất");

    jLabel14.setText("Xuất xứ");

    cbbKichCo.setText("Kích cỡ");

    cbbSanPham.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbbSanPhamActionPerformed(evt);
        }
    });

    cbbKichCo1.setText("Màu sắc");

    btnMauSac.setText("+");
    btnMauSac.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnMauSacActionPerformed(evt);
        }
    });

    btnAddXuatXu.setText("+");
    btnAddXuatXu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddXuatXuActionPerformed(evt);
        }
    });

    btnAddKichCo.setText("+");
    btnAddKichCo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddKichCoActionPerformed(evt);
        }
    });

    BtnThemSoLuong.setText("+");
    BtnThemSoLuong.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BtnThemSoLuongActionPerformed(evt);
        }
    });

    btnBotSoLuong.setText("-");
    btnBotSoLuong.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBotSoLuongActionPerformed(evt);
        }
    });

    cbbNSX.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbbNSXActionPerformed(evt);
        }
    });

    cbbKichThuoc.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbbKichThuocActionPerformed(evt);
        }
    });

    btnAddDangDan.setText("+");
    btnAddDangDan.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddDangDanActionPerformed(evt);
        }
    });

    cbbKichCo2.setText("Dáng đàn");

    jLabel15.setText("Mô tả");

    ta_moTa.setColumns(20);
    ta_moTa.setRows(5);
    jScrollPane2.setViewportView(ta_moTa);

    btnAddTenSPCT.setText("+");
    btnAddTenSPCT.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddTenSPCTActionPerformed(evt);
        }
    });

    btnAddNSX.setText("+");
    btnAddNSX.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddNSXActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel11)
                .addComponent(jLabel10)
                .addComponent(jLabel12)
                .addComponent(jLabel15))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cbbSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtGiaBan)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addComponent(txtSoLuong)
                    .addGap(18, 18, 18)
                    .addComponent(BtnThemSoLuong))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnBotSoLuong)
                                .addComponent(btnAddTenSPCT))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbKichCo)
                                    .addComponent(jLabel14)))
                            .addGap(12, 12, 12))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(cbbKichCo1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbbKichThuoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbNSX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbXuatXu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(cbbMauSac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbKichCo2)
                    .addGap(18, 18, 18)
                    .addComponent(cbbDangDan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAddXuatXu)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMauSac)
                    .addComponent(btnAddKichCo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addComponent(btnAddNSX)
                .addComponent(btnAddDangDan))
            .addGap(11, 11, 11))
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddNSX))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(btnAddXuatXu)))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(20, 20, 20)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddKichCo))
                        .addComponent(cbbKichCo))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMauSac)
                                .addComponent(cbbKichCo1))
                            .addGap(21, 21, 21))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAddDangDan)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbDangDan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbKichCo2))))
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddTenSPCT))
                        .addComponent(jLabel10))
                    .addGap(20, 20, 20)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(21, 21, 21)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(BtnThemSoLuong)
                        .addComponent(btnBotSoLuong))
                    .addGap(20, 20, 20)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(43, 43, 43))
    );

    jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    btnThem.setText("Thêm");
    btnThem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnThemActionPerformed(evt);
        }
    });

    btnSua.setText("Sửa");
    btnSua.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSuaActionPerformed(evt);
        }
    });

    BtnXoa.setText("Xóa");
    BtnXoa.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BtnXoaActionPerformed(evt);
        }
    });

    btnLamMoi.setText("Làm mới");
    btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnLamMoiActionPerformed(evt);
        }
    });

    XuatFileExcel.setText("Xuất excel");
    XuatFileExcel.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            XuatFileExcelActionPerformed(evt);
        }
    });

    btnThungRac.setText("Thùng rác");
    btnThungRac.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnThungRacActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addGap(32, 32, 32)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(XuatFileExcel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addComponent(BtnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThungRac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(24, Short.MAX_VALUE))
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addGap(14, 14, 14)
            .addComponent(btnThem)
            .addGap(20, 20, 20)
            .addComponent(btnSua)
            .addGap(20, 20, 20)
            .addComponent(BtnXoa)
            .addGap(20, 20, 20)
            .addComponent(btnLamMoi)
            .addGap(20, 20, 20)
            .addComponent(XuatFileExcel)
            .addGap(20, 20, 20)
            .addComponent(btnThungRac)
            .addContainerGap(26, Short.MAX_VALUE))
    );

    jLabel21.setText("Thuộc tính");

    tblSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "STT", "ID", "Tên sản phẩm", "Nhà xản xuất", "xuất xứ", "Kích cơ", "Màu sắc", "Dáng đàn", "Giá bán", "Số lượng"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    tblSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblSanPhamChiTietMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblSanPhamChiTiet);
    if (tblSanPhamChiTiet.getColumnModel().getColumnCount() > 0) {
        tblSanPhamChiTiet.getColumnModel().getColumn(0).setResizable(false);
        tblSanPhamChiTiet.getColumnModel().getColumn(1).setResizable(false);
        tblSanPhamChiTiet.getColumnModel().getColumn(3).setResizable(false);
        tblSanPhamChiTiet.getColumnModel().getColumn(4).setResizable(false);
        tblSanPhamChiTiet.getColumnModel().getColumn(5).setResizable(false);
        tblSanPhamChiTiet.getColumnModel().getColumn(6).setResizable(false);
        tblSanPhamChiTiet.getColumnModel().getColumn(7).setResizable(false);
        tblSanPhamChiTiet.getColumnModel().getColumn(8).setResizable(false);
        tblSanPhamChiTiet.getColumnModel().getColumn(9).setResizable(false);
    }

    cbbLocDangDan.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbbLocDangDanActionPerformed(evt);
        }
    });

    cbbLocGiaBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lựa chọn", "Tăng dần", "Giảm dần" }));
    cbbLocGiaBan.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            cbbLocGiaBanMouseClicked(evt);
        }
    });

    jLabel6.setText("Lọc giá");

    txtThuocTinhSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtThuocTinhSanPhamKeyReleased(evt);
        }
    });

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(txtThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(258, 258, 258)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(29, 29, 29)
                    .addComponent(cbbLocGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(224, 224, 224)
            .addComponent(cbbLocDangDan, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(9, 9, 9)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel21)
                .addComponent(jLabel20)
                .addComponent(cbbLocDangDan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cbbLocGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6)
                .addComponent(txtThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(19, 19, 19)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(187, Short.MAX_VALUE))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    TPSanPham.addTab("Sản phẩm chi tiết", jPanel3);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TPSanPham)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel9)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(TPSanPham)
            .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(151, 151, 151)
            .addComponent(jLabel1)
            .addContainerGap(1003, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(113, 113, 113)
            .addComponent(jLabel1)
            .addContainerGap(517, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()))
    );
    }// </editor-fold>//GEN-END:initComponents

// Xuất Excel
    private void XuatFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XuatFileExcelActionPerformed
        try {
            XSSFWorkbook wordkbook = new XSSFWorkbook();
            XSSFSheet sheet = wordkbook.createSheet("SANPHAM");
            XSSFRow row_sheet = null;

            row_sheet = sheet.createRow(3);
            XSSFCell cell = row_sheet.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row_sheet.createCell(1, CellType.STRING);
            cell.setCellValue("ID");

            cell = row_sheet.createCell(2, CellType.STRING);
            cell.setCellValue("TenSanPham");

            cell = row_sheet.createCell(3, CellType.STRING);
            cell.setCellValue("NSX");
            cell = row_sheet.createCell(4, CellType.STRING);
            cell.setCellValue("XuatXu");
            cell = row_sheet.createCell(5, CellType.STRING);
            cell.setCellValue("GiaBan");
            cell = row_sheet.createCell(6, CellType.STRING);
            cell.setCellValue("KichCo");
            cell = row_sheet.createCell(7, CellType.STRING);
            cell.setCellValue("MauSac");
            cell = row_sheet.createCell(8, CellType.STRING);
            cell.setCellValue("SoLuong");

            for (int i = 0; i < listSanPhamCT.size(); i++) {
                //                Modelbook book =arr.get(i);
                row_sheet = sheet.createRow(5 + i);
                cell = row_sheet.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row_sheet.createCell(1, CellType.STRING);
                cell.setCellValue(listSanPhamCT.get(i).getID());

                cell = row_sheet.createCell(2, CellType.STRING);
                cell.setCellValue(listSanPhamCT.get(i).getTenSanPham());

                cell = row_sheet.createCell(3, CellType.STRING);
                cell.setCellValue(listSanPhamCT.get(i).getNXS());

                cell = row_sheet.createCell(4, CellType.STRING);
                cell.setCellValue(listSanPhamCT.get(i).getXuatXu());

                cell = row_sheet.createCell(5, CellType.STRING);
                cell.setCellValue(listSanPhamCT.get(i).getGiaBan());

                cell = row_sheet.createCell(6, CellType.STRING);
                cell.setCellValue(listSanPhamCT.get(i).getKichCo());

                cell = row_sheet.createCell(7, CellType.STRING);
                cell.setCellValue(listSanPhamCT.get(i).getMauSac());

                cell = row_sheet.createCell(8, CellType.STRING);
                cell.setCellValue(listSanPhamCT.get(i).getSoLuong());

            }

            File f = new File("D:\\DUAN1\\FILE buil\\excel\\BanNhacCu.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);
                wordkbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(this, "in thanh cong");

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Loi mo file");
        }
    }//GEN-LAST:event_XuatFileExcelActionPerformed

    private void BtnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnXoaActionPerformed
        int indexRowChoice = tblSanPhamChiTiet.getSelectedRow();
        if (indexRowChoice == -1) {
            // Không có dòng nào được chọn
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng cần xóa!");
        } else {
            int id = (int) tblSanPhamChiTiet.getValueAt(indexRowChoice, 1);
            if (id != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String result = SPCTService.delete(id);
                    if (result.equals("Xóa Thành Công")) {
                        JOptionPane.showMessageDialog(this, result);
                        fillToTable(tblSanPhamChiTiet, SPCTService.selectByIdSP(idsp), (s, i) -> new Object[]{i, s.getID(), s.getTenSanPham(), s.getNXS(), s.getXuatXu(), s.getKichCo(), s.getMauSac(), s.getDangDan(), s.getGiaBan(), s.getSoLuong()});
                    } else {
                        // Xử lý lỗi ở đây, ví dụ:
                        JOptionPane.showMessageDialog(this, "Không thể xóa sản phẩm: " + result);
                    }
                }
            }
        }


    }//GEN-LAST:event_BtnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa không", "", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            Object selectedItem = cbbSanPham.getSelectedItem();
            if (!(selectedItem instanceof SanPham)) {
                JOptionPane.showMessageDialog(this, "ComboBox SanPham chưa chọn đối tượng");
                return;
            }
            SanPham selectedSanPham = (SanPham) selectedItem;
            int ID_SanPham = Integer.parseInt(selectedSanPham.getID());

            selectedItem = cbbNSX.getSelectedItem();
            if (!(selectedItem instanceof NhaSanXuat)) {
                JOptionPane.showMessageDialog(this, "ComboBox NSX chưa chọn đối tượng");
                return;
            }
            NhaSanXuat selectedThuongHieu = (NhaSanXuat) selectedItem;
            int ID_ThuongHieu = (selectedThuongHieu.getID());

            selectedItem = cbbKichThuoc.getSelectedItem();
            if (!(selectedItem instanceof KichThuoc)) {
                JOptionPane.showMessageDialog(this, "ComboBox KichThuoc chưa chọn đối tượng");
                return;
            }
            KichThuoc selectedKichThuoc = (KichThuoc) selectedItem;
            int ID_KICHTHUOC = (selectedKichThuoc.getID());

            selectedItem = cbbMauSac.getSelectedItem();
            if (!(selectedItem instanceof MauSac)) {
                JOptionPane.showMessageDialog(this, "ComboBox MauSac chưa chọn đối tượng");
                return;
            }
            MauSac selectedMauSac = (MauSac) selectedItem;
            int ID_MauSac = (selectedMauSac.getId());

            selectedItem = cbbXuatXu.getSelectedItem();
            if (!(selectedItem instanceof XuatXu)) {
                JOptionPane.showMessageDialog(this, "ComboBox XuatXu chưa chọn đối tượng");
                return;
            }
            XuatXu selectedXuatXu = (XuatXu) selectedItem;
            int ID_XuatXu = (selectedXuatXu.getId());

            selectedItem = cbbDangDan.getSelectedItem();
            if (!(selectedItem instanceof DangDan)) {
                JOptionPane.showMessageDialog(this, "ComboBox DangDan chưa chọn đối tượng");
                return;
            }
            DangDan selectedDangDan = (DangDan) selectedItem;
            int ID_DangDan = (selectedDangDan.getID());

            long GiaBan = Long.parseLong(txtGiaBan.getText());
            int SoLuong = Integer.parseInt(txtSoLuong.getText());
            String mota = ta_moTa.getText();
            int roww = tblSanPhamChiTiet.getSelectedRow();
            System.out.println("DÒng được chọn là " + roww);
            int id_SPCT = Integer.parseInt(tblSanPhamChiTiet.getValueAt(roww, 1).toString());

            SPCTService.updateSanPhamChiTiet(id_SPCT, ID_SanPham, ID_ThuongHieu, ID_MauSac, ID_KICHTHUOC, ID_XuatXu, ID_DangDan, GiaBan, SoLuong, mota);
            JOptionPane.showMessageDialog(this, "Sửa thành công");

            fillToTable(tblSanPhamChiTiet, SPCTService.selectByIdSP(idsp), (s, i) -> new Object[]{i, s.getID(), s.getTenSanPham(), s.getNXS(), s.getXuatXu(), s.getKichCo(), s.getMauSac(), s.getDangDan(), s.getGiaBan(), s.getSoLuong()});
        }


    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (check()) {
            SanPhamChiTiet SanPham = getObjSPCT();
            if (SanPham != null) {
                JOptionPane.showMessageDialog(this, SPCTService.add(SanPham));
                fillToTable(tblSanPhamChiTiet, SPCTService.selectByIdSP(idsp), (s, i) -> new Object[]{i, s.getID(), s.getTenSanPham(), s.getNXS(), s.getXuatXu(), s.getKichCo(), s.getMauSac(), s.getDangDan(), s.getGiaBan(), s.getSoLuong()});
                fillToTableSanPham();
                fillToComboBox();
                ClearFormSPCT();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnBotSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBotSoLuongActionPerformed
        String quantityText = txtSoLuong.getText();
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng mong muốn");
        } else {
            int currentQuantity = Integer.parseInt(quantityText);
            if (currentQuantity > 0) {
                txtSoLuong.setText(String.valueOf(currentQuantity - 1));
            } else {
                JOptionPane.showMessageDialog(null, "Số lượng không thể là số âm");
            }
        }
    }//GEN-LAST:event_btnBotSoLuongActionPerformed

    private void BtnThemSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnThemSoLuongActionPerformed
        String quantityText = txtSoLuong.getText();
        int currentQuantity = quantityText.isEmpty() ? 0 : Integer.parseInt(quantityText);
        txtSoLuong.setText(String.valueOf(currentQuantity + 1));
    }//GEN-LAST:event_BtnThemSoLuongActionPerformed

    private void btnAddKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKichCoActionPerformed
        JFKichCo JFKC = new JFKichCo(this);
        JFKC.setVisible(true);
    }//GEN-LAST:event_btnAddKichCoActionPerformed

    private void btnAddNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNSXActionPerformed
        JFNSX JFNSX = new JFNSX(this);
        JFNSX.setVisible(true);
    }//GEN-LAST:event_btnAddNSXActionPerformed

    private void btnAddXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddXuatXuActionPerformed
        JFAddXuatXu JFXX = new JFAddXuatXu(this);
        JFXX.setVisible(true);
    }//GEN-LAST:event_btnAddXuatXuActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        JFAddMauSac JFMS = new JFAddMauSac(this);
        JFMS.setVisible(true);
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void BtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClearActionPerformed
        clearFormSP();
        fillToTableSanPham();
    }//GEN-LAST:event_BtnClearActionPerformed

    private void btnCapNhatSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSanPhamActionPerformed
        String IDSanPham = txtID_sanPham.getText();
        String tenSanPham = txtTenSanPham.getText();
        String id_nhanVien_str = txtID_nhanVien.getText();

        if (IDSanPham.isEmpty() || tenSanPham.isEmpty() || id_nhanVien_str.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để cập nhật");
            return;
        }

        int id_nhanVien = Integer.parseInt(id_nhanVien_str);

        if (checkTrungTen(tenSanPham)) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại");
            return;
        }

        SanPham sanPham = new SanPham();
        sanPham.setID(IDSanPham);
        sanPham.setTenSP(tenSanPham);
        sanPham.setId_NhanVien(id_nhanVien);
        JOptionPane.showMessageDialog(this, SanPhamService.update(sanPham));
        fillToTableSanPham();
        fillToTableSanPhamChiTiet();
        fillToComboBox();
        clearFormSP();

    }//GEN-LAST:event_btnCapNhatSanPhamActionPerformed

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        int row = tblSanPham.getSelectedRow();
        if (row != -1) {
            String id = tblSanPham.getValueAt(row, 1).toString();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để xóa");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, SanPhamService.GetToGaber(Integer.parseInt(id)));
                fillToTableSanPham();
                fillToTableSanPhamChiTiet();
                fillToComboBox();
                clearFormSP();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để xóa");
        }


    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        SanPham SanPham = getObj();
        if (SanPham != null) {
            JOptionPane.showMessageDialog(this, SanPhamService.add(SanPham));
            fillToTableSanPham();
            fillToComboBox();
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        model = (DefaultTableModel) tblSanPham.getModel();

    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    int previousRow = -1; // Biến để lưu dòng được chọn trước đó
    int clickCount = 0; // Biến để theo dõi số lần nhấp vào dòng hiện tại
    int idsp;
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = tblSanPham.getSelectedRow();
        txtID_sanPham.setText((String) tblSanPham.getValueAt(row, 1));
        txtTenSanPham.setText((String) tblSanPham.getValueAt(row, 2));

        if (row != -1) {
            // Nếu chọn sang sản phẩm khác từ bảng thì lần ấn vào dòng trước đó sẽ sang là 0
            if (previousRow != -1 && previousRow != row) {
                clickCount = 0;
            }
            // Tăng số lần dòng được chọn
            clickCount++;
            System.out.println("dòng " + row + " Được ấn: " + clickCount + " lần");

            // Nếu ấn 2 lần liên tiếp thì sẽ lấy id từ bảng sau đó gọi tới phương thức chuyển trang
            if (clickCount == 2) {

                idsp = Integer.parseInt(tblSanPham.getValueAt(row, 1).toString());
                fillToTable(tblSanPhamChiTiet, SPCTService.selectByIdSP(idsp), (s, i) -> new Object[]{i, s.getID(), s.getTenSanPham(), s.getNXS(), s.getXuatXu(), s.getKichCo(), s.getMauSac(), s.getDangDan(), s.getGiaBan(), s.getSoLuong()});

                // Gọi phương thức chuyển trang với ID
                TPSanPham.setSelectedIndex(1);
                row = 1;
            }
            // Cập nhật dòng được chọn trước đó
            previousRow = row;
        }

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtsearchSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchSPKeyReleased
        String name = txtsearchSP.getText();
        search(name);
    }//GEN-LAST:event_txtsearchSPKeyReleased

    private void btnAddDangDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDangDanActionPerformed
        JFDangDan JFDD = new JFDangDan(this);
        JFDD.setVisible(true);
    }//GEN-LAST:event_btnAddDangDanActionPerformed

    int previousRowSPCT = -1; // Biến để lưu dòng được chọn trước đó
    int clickCountSPCT = 0; // Biến để theo dõi số lần nhấp vào dòng hiện tại
    private void tblSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChiTietMouseClicked
        int row = tblSanPhamChiTiet.getSelectedRow();
        if (row != -1) {

            // Lấy chỉ số dòng đã lọc từ chỉ số dòng đã chọn
            fillToFormSPCT(row);
            int id_spa = Integer.parseInt(tblSanPhamChiTiet.getModel().getValueAt(row, 1).toString());
            SanPhamChiTiet s = SPCTService.selectByIdSPCT(id_spa).get(0);
            ta_moTa.setText(s.getGhiChu());

            // Nếu chọn sang sản phẩm khác từ bảng thì lần ấn vào dòng trước đó sẽ sang là 0
            if (previousRowSPCT != -1 && previousRowSPCT != row) {
                clickCountSPCT = 0;
            }

            // Tăng số lần dòng được chọn
            clickCountSPCT++;
            System.out.println("Dòng " + row + " Được ấn: " + clickCountSPCT + " lần");

            // Nếu ấn 2 lần liên tiếp thì sẽ lấy id từ bảng sau đó gọi tới phương thức chuyển trang
            if (clickCountSPCT == 2) {
                Integer id = Integer.parseInt(tblSanPhamChiTiet.getModel().getValueAt(row, 1).toString()); // Lấy ID từ cột đầu tiên của dòng được chọn
                System.out.println("ID_spct in ra là: " + id);
                JFSanPhamChiTiet JFSPCT = new JFSanPhamChiTiet(id);
                JFSPCT.setVisible(true);
                row = 1;
                // Reset số lần ấn về 0 sau khi xử lý
                clickCountSPCT = 0;
            }

            // Cập nhật dòng được chọn trước đó
            previousRowSPCT = row;
        }


    }//GEN-LAST:event_tblSanPhamChiTietMouseClicked

    public void fillToFormSPCT(int row) {

        DefaultTableModel model = (DefaultTableModel) tblSanPhamChiTiet.getModel();
        Object sp = model.getValueAt(row, 2);

        String SoSP = String.valueOf(tblSanPhamChiTiet.getValueAt(row, 9));
        String GiaBan = String.valueOf(tblSanPhamChiTiet.getValueAt(row, 8));
        fillToFormSanPhamChiTiet(sp.toString(), GiaBan, SoSP);

    }

    // đẩy dữ liệu lên form sản phẩm chi tiết và form sp
    public void fillToFormSanPhamChiTiet(String selectedItem1, String GiaBan, String SoSP) {
        for (int i = 0; i < cbbSanPham.getItemCount(); i++) {
            if (selectedItem1.equals(cbbSanPham.getItemAt(i).toString())) {
                cbbSanPham.setSelectedIndex(i);
                System.out.println("i được chọn CBB sanPham " + i + " teenSSP chọn" + selectedItem1);
            }
        }

        txtGiaBan.setText(GiaBan);
        txtSoLuong.setText(SoSP);

    }


    private void cbbLocDangDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocDangDanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLocDangDanActionPerformed

    private void cbbKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKichThuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbKichThuocActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        ClearFormSPCT();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFThungRacSanPham ftp = new JFThungRacSanPham(this);
        ftp.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnThungRacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThungRacActionPerformed
        JFThungRacSPCT JfTRSPCT = new JFThungRacSPCT(this);
        JfTRSPCT.setVisible(true);
    }//GEN-LAST:event_btnThungRacActionPerformed

    private void btnAddTenSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTenSPCTActionPerformed
        JFrameThemNhanh jftn = new JFrameThemNhanh(this);
        jftn.setVisible(true);

    }//GEN-LAST:event_btnAddTenSPCTActionPerformed

    private void cbbNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNSXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNSXActionPerformed

    private void cbbSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSanPhamActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbbSanPhamActionPerformed

    private void cbbLocGiaBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbLocGiaBanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLocGiaBanMouseClicked

    private void txtThuocTinhSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThuocTinhSanPhamKeyReleased
        String TTSP = txtThuocTinhSanPham.getText();
        searchSPCT(TTSP);
    }//GEN-LAST:event_txtThuocTinhSanPhamKeyReleased

    public void search(String str) {
        // Lấy DefaultTableModel từ JTable
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();

        // Tạo một TableRowSorter mới và liên kết nó với DefaultTableModel
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);

        // Gán TableRowSorter vừa tạo cho JTable
        tblSanPham.setRowSorter(trs);

        // Chuyển đổi chuỗi tìm kiếm thành chữ thường
        str = str.toLowerCase();

        // Đặt bộ lọc cho TableRowSorter để thực hiện tìm kiếm dựa trên biểu thức chính quy
        // Sử dụng toLowerCase() trong biểu thức chính quy để không phân biệt chữ hoa chữ thường
        trs.setRowFilter(RowFilter.regexFilter("(?i)" + str));
    }

    public void searchSPCT(String str) {
        // Lấy DefaultTableModel từ JTable
        DefaultTableModel model = (DefaultTableModel) tblSanPhamChiTiet.getModel();

        // Tạo một TableRowSorter mới và liên kết nó với DefaultTableModel
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);

        // Gán TableRowSorter vừa tạo cho JTable
        tblSanPhamChiTiet.setRowSorter(trs);

        // Chuyển đổi chuỗi tìm kiếm thành chữ thường
        str = str.toLowerCase();

        // Đặt bộ lọc cho TableRowSorter để thực hiện tìm kiếm dựa trên biểu thức chính quy
        // Sử dụng toLowerCase() trong biểu thức chính quy để không phân biệt chữ hoa chữ thường
        trs.setRowFilter(RowFilter.regexFilter("(?i)" + str));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnClear;
    private javax.swing.JButton BtnThemSoLuong;
    private javax.swing.JButton BtnXoa;
    private javax.swing.JTabbedPane TPSanPham;
    private javax.swing.JButton XuatFileExcel;
    private javax.swing.JButton btnAddDangDan;
    private javax.swing.JButton btnAddKichCo;
    private javax.swing.JButton btnAddNSX;
    private javax.swing.JButton btnAddTenSPCT;
    private javax.swing.JButton btnAddXuatXu;
    private javax.swing.JButton btnBotSoLuong;
    private javax.swing.JButton btnCapNhatSanPham;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnThungRac;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<Object> cbbDangDan;
    private javax.swing.JLabel cbbKichCo;
    private javax.swing.JLabel cbbKichCo1;
    private javax.swing.JLabel cbbKichCo2;
    private javax.swing.JComboBox<Object> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbLocDangDan;
    private javax.swing.JComboBox<String> cbbLocGiaBan;
    private javax.swing.JComboBox<Object> cbbMauSac;
    private javax.swing.JComboBox<Object> cbbNSX;
    private javax.swing.JComboBox<Object> cbbSanPham;
    private javax.swing.JComboBox<Object> cbbXuatXu;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea ta_moTa;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamChiTiet;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JLabel txtID_nhanVien;
    private javax.swing.JLabel txtID_sanPham;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtThuocTinhSanPham;
    private javax.swing.JTextField txtsearchSP;
    // End of variables declaration//GEN-END:variables
}
