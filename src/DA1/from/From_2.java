/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DA1.from;

import DA1.model.HoaDon;
import DA1.model.HoaDonChiTiet;
import DA1.model.TimeLine;
import DA1.service.HDCTService;
import DA1.service.HoaDonService;
import DA1.service.QR_CODE1;
import com.google.zxing.qrcode.encoder.QRCode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class From_2 extends javax.swing.JPanel {

    public static String mahd;
    private DefaultTableModel modelCT1 = new DefaultTableModel();
    private DefaultTableModel model1 = new DefaultTableModel();
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel modelCT = new DefaultTableModel();
    private final HoaDonService hs = new HoaDonService();
    private final HDCTService hdcts = new HDCTService();
    private ArrayList<HoaDon> list = new ArrayList<>();
    private ArrayList<HoaDonChiTiet> listCT = new ArrayList<>();
    private ArrayList<TimeLine> listTL = new ArrayList<>();

    double bHeight = 0.0;
    ArrayList<String> tenSP = new ArrayList<>();
    ArrayList<String> soluong = new ArrayList<>();
    ArrayList<String> gia = new ArrayList<>();
    ArrayList<String> tong = new ArrayList<>();

    String tongtien;
    String nhanvien;

    public From_2() {
        initComponents();
        cboHT.removeAllItems();
        cboHT.addItem("COD");
        cboHT.addItem("Chuyển khoản");
        cboTT.removeAllItems();
        cboTT.addItem("Chưa thanh toán");
        cboTT.addItem("Đã thanh toán");
        model = (DefaultTableModel) tblHD.getModel();
        modelCT = (DefaultTableModel) tblHDCT.getModel();
        modelCT1 = (DefaultTableModel) tblCT1.getModel();
        loadToDaTa();

    }

    public void loadToDaTa() {
        int i = 1;
        model.setRowCount(0);
        list = hs.getAll();
        for (HoaDon hd : list) {
            model.addRow(new Object[]{i++, hd.getId(), hd.getNt(), hd.getNgd(), hd.getIdnv(), hd.getTenkh(), hd.getSdt(), hd.getTongTien(), hd.getPttt(), hd.getTt()});
        }
    }

    public void getText1(int id) {
        HoaDon hd = hs.findById(id);
        txtHD.setText(String.valueOf(hd.getId()));
        txtNT.setText(hd.getNt());
        txtNGD.setText(hd.getNgd());
        txtNV.setText(String.valueOf(hd.getIdnv()));
        txtTNV.setText(hd.getTennv());
        txtTKH.setText(hd.getTenkh());
        txtSDT.setText(hd.getSdt());
        txtTong.setText(String.valueOf(hd.getTongTien()));
        txtPTTT.setText(hd.getPttt());
        txtTT.setText(hd.getTt());

    }

    public void filldate() {
        int index = tblHD.getSelectedRow();
        System.out.println("ROW1: " + index);
        String id = tblHD.getValueAt(index, 1).toString();
        int i = 1;
        modelCT.setRowCount(0);
        listCT = hs.lienKetHoaDon(Integer.parseInt(id));
        for (HoaDonChiTiet hdct : listCT) {
            modelCT.addRow(new Object[]{i++, hdct.getTensp(), hdct.getHang(), hdct.getKd(), hdct.getXx(), hdct.getSl(), hdct.getGia()});
        }
        getText1(Integer.parseInt(id));
//        loadToDaTa();
        modelCT1.setRowCount(0);
        listTL = hs.lienKetTimeLine(Integer.parseInt(id));
        System.out.println(listTL);
        for (TimeLine tl : listTL) {
            modelCT1.addRow(new Object[]{tl.getId(), tl.getIdhd(), tl.getTg(), tl.getNd()});
        }
    }

    public void themSPHD() {
        int index = tblHD.getSelectedRow();
        System.out.println("ROW2: " + index);
        String id = tblHD.getValueAt(index, 1).toString();

        nhanvien = "Tạ Xuân An";

        tongtien = tblHD.getValueAt(index, 7).toString();
        listCT = hdcts.selectAllByMaHoaDon(Integer.parseInt(id));
        for (HoaDonChiTiet ct : listCT) {
            tenSP.add(ct.getTensp());
            soluong.add(String.valueOf(ct.getSl()));
            gia.add(String.valueOf(ct.getGia()));
            tong.add(String.valueOf(ct.getSl() * ct.getGia()));
        }
    }

    public PageFormat getPage(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper p = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeigth = 5.0;
        double footerHeigth = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeigth + bodyHeight + footerHeigth);
        p.setSize(width, height);
        p.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(p);
        return pf;
    }

    public static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    public static double toPPI(double inch) {
        return inch * 72d;
    }

    public class Bill implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            int r = tenSP.size();
            ImageIcon icon = new ImageIcon("D:\\Zalo Received Files\\IMG_5169.JPG");
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {
                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    ImageObserver rootPane = null;
                    g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);
                    y += yShift + 30;
                    g2d.drawString("------------------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("         Cửa hàng guitar classic M4L            ", 12, y);
                    y += yShift;
                    g2d.drawString("Địa chỉ: Đường Trịnh Văn Bô, Phương Canh,", 12, y);
                    y += yShift;
                    g2d.drawString("         Nam Từ Liêm, Hà Nội                      ", 12, y);
                    y += yShift;
                    g2d.drawString("------------------------------------------------", 12, y);
                    y += headerRectHeight;

                    g2d.drawString("  Tên sản phẩm               Giá", 10, y);
                    y += yShift;
                    g2d.drawString("------------------------------------------------", 10, y);
                    y += headerRectHeight;
                    for (int i = 0; i < r; i++) {
                        g2d.drawString(" " + tenSP.get(i) + "                     ", 10, y);
                        y += yShift;
                        g2d.drawString("  " + soluong.get(i) + " * " + gia.get(i), 10, y);
                        g2d.drawString(tong.get(i), 160, y);
                        y += yShift;
                    }
                    g2d.drawString("-------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Tổng:                     " + tongtien + "", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------------------", 10, y);
                    y += yShift;
                    y += yShift;
                    g2d.drawString("             CẢM ƠN VÀ HẸN GẶP LẠI               ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("               Người lập đơn          ", 10, y);
                    y += yShift;
                    g2d.drawString("               " + nhanvien + "", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------------------", 10, y);
                    y += yShift;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    public void inHD() {
        themSPHD();
        bHeight = Double.valueOf(tenSP.size());

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new Bill(), getPage(pj));
        try {
            pj.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboTT = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboHT = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        btnLoc = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        btnXuat = new javax.swing.JButton();
        btnIn = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtNGD = new javax.swing.JTextField();
        txtNT = new javax.swing.JTextField();
        txtHD = new javax.swing.JTextField();
        txtPTTT = new javax.swing.JTextField();
        txtTT = new javax.swing.JTextField();
        txtTong = new javax.swing.JTextField();
        txtTKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtTNV = new javax.swing.JTextField();
        txtNV = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCT1 = new javax.swing.JTable();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm:");

        jLabel2.setText("Trạng thái hóa đơn:");

        jLabel3.setText("Hình thức thanh toán:");

        jLabel4.setText("Giá từ:");

        jLabel5.setText("Đến:");

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Ngày tạo", "Ngày GD", "Mã NV", "Tên KH", "SDT", "Tổng tiền", "PTTT", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHD);
        if (tblHD.getColumnModel().getColumnCount() > 0) {
            tblHD.getColumnModel().getColumn(0).setResizable(false);
            tblHD.getColumnModel().getColumn(1).setResizable(false);
            tblHD.getColumnModel().getColumn(2).setResizable(false);
            tblHD.getColumnModel().getColumn(3).setResizable(false);
            tblHD.getColumnModel().getColumn(4).setResizable(false);
            tblHD.getColumnModel().getColumn(5).setResizable(false);
            tblHD.getColumnModel().getColumn(6).setResizable(false);
            tblHD.getColumnModel().getColumn(7).setResizable(false);
            tblHD.getColumnModel().getColumn(8).setResizable(false);
            tblHD.getColumnModel().getColumn(9).setResizable(false);
        }

        btnXuat.setText("Xuất danh sách");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        btnIn.setText("In hóa đơn");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnLoc)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIn)
                .addGap(30, 30, 30)
                .addComponent(btnXuat)
                .addGap(10, 10, 10))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cboHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuat)
                    .addComponent(btnIn))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên SP", "Hãng", "Kiểu dáng", "Xuất xứ", "Số lượng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHDCT);
        if (tblHDCT.getColumnModel().getColumnCount() > 0) {
            tblHDCT.getColumnModel().getColumn(0).setResizable(false);
            tblHDCT.getColumnModel().getColumn(1).setResizable(false);
            tblHDCT.getColumnModel().getColumn(2).setResizable(false);
            tblHDCT.getColumnModel().getColumn(3).setResizable(false);
            tblHDCT.getColumnModel().getColumn(4).setResizable(false);
            tblHDCT.getColumnModel().getColumn(5).setResizable(false);
            tblHDCT.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa đơn", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel7.setText("Mã HD:");

        jLabel8.setText("Ngày tạo:");

        jLabel9.setText("Ngày giao dịch:");

        jLabel10.setText("Số điện thoại:");

        jLabel11.setText("Mã NV:");

        jLabel13.setText("Tên nhân viên:");

        jLabel14.setText("Tên khách hàng:");

        jLabel15.setText("PTTT:");

        jLabel16.setText("Trạng thái:");

        jLabel17.setText("Tổng tiền:");

        txtNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPTTT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(txtNGD, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNT, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHD, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTT))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel10)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(txtTKH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTNV, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNV, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTong))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(txtHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13)
                    .addComponent(txtNT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNGD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        tblCT1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã HD", "Thời gian", "Nội dung"
            }
        ));
        jScrollPane4.setViewportView(tblCT1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lịch sử HD", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNVActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        try {

            int i = 1;
            int id = Integer.valueOf(txtTim.getText());
            if (id > 0) {
                model.setRowCount(0);
                HoaDon hd = hs.findById(id);
                if (hd.getId() == id) {
                    model.addRow(new Object[]{i++, hd.getId(), hd.getNt(), hd.getNgd(), hd.getIdnv(), hd.getTenkh(), hd.getSdt(), hd.getTongTien(), hd.getPttt(), hd.getTt()});
                }
            }
            modelCT.setRowCount(0);
            listCT = hs.lienKetHoaDon(id);
            for (HoaDonChiTiet hdct : listCT) {
                modelCT.addRow(new Object[]{i++, hdct.getTensp(), hdct.getHang(), hdct.getKd(), hdct.getXx(), hdct.getSl(), hdct.getGia()});
            }
            getText1(id);

            modelCT1.setRowCount(0);
            listTL = hs.lienKetTimeLine(id);
            System.out.println(listTL);
            for (TimeLine tl : listTL) {
                modelCT1.addRow(new Object[]{tl.getId(), tl.getIdhd(), tl.getTg(), tl.getNd()});
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        String pttt = (String) cboHT.getSelectedItem();
        String tt = (String) cboTT.getSelectedItem();
        if (!(pttt.isEmpty() && tt.isEmpty())) {
            model.setRowCount(0);
            list = hs.locHoaDon(pttt, tt);
            for (HoaDon hd : list) {
                model.addRow(new Object[]{hd.getId(), hd.getNt(), hd.getNgd(), hd.getIdnv(), hd.getTenkh(), hd.getSdt(), hd.getTongTien(), hd.getPttt(), hd.getTt()});
            }
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        // TODO add your handling code here:
        filldate();
    }//GEN-LAST:event_tblHDMouseClicked

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        inHD();
    }//GEN-LAST:event_btnInActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:                                             
        try {
            XSSFWorkbook wordkbook = new XSSFWorkbook();
            XSSFSheet sheet = wordkbook.createSheet("HoaDon");
            XSSFRow row_sheet = null;

            row_sheet = sheet.createRow(3);
            XSSFCell cell = row_sheet.createCell(0, CellType.STRING);
            cell.setCellValue("ID");

            cell = row_sheet.createCell(1, CellType.STRING);
            cell.setCellValue("NgayTao");

            cell = row_sheet.createCell(2, CellType.STRING);
            cell.setCellValue("NgayGiaoDich");

            cell = row_sheet.createCell(3, CellType.STRING);
            cell.setCellValue("MaNV");
            cell = row_sheet.createCell(4, CellType.STRING);
            cell.setCellValue("TenNV");
            cell = row_sheet.createCell(5, CellType.STRING);
            cell.setCellValue("TenKH");
            cell = row_sheet.createCell(6, CellType.STRING);
            cell.setCellValue("SDT");
            cell = row_sheet.createCell(7, CellType.STRING);
            cell.setCellValue("TongTien");
            cell = row_sheet.createCell(8, CellType.STRING);
            cell.setCellValue("PTTT");
            cell = row_sheet.createCell(9, CellType.STRING);
            cell.setCellValue("PTTT");

            for (int i = 0; i < list.size(); i++) {
                //Modelbook book =arr.get(i);
                row_sheet = sheet.createRow(5 + i);
                cell = row_sheet.createCell(0, CellType.NUMERIC);
                cell.setCellValue(list.get(i).getId());

                cell = row_sheet.createCell(1, CellType.STRING);
                cell.setCellValue(list.get(i).getNt());

                cell = row_sheet.createCell(2, CellType.STRING);
                cell.setCellValue(list.get(i).getNgd());

                cell = row_sheet.createCell(3, CellType.STRING);
                cell.setCellValue(list.get(i).getIdnv());

                cell = row_sheet.createCell(4, CellType.STRING);
                cell.setCellValue(list.get(i).getTennv());

                cell = row_sheet.createCell(5, CellType.STRING);
                cell.setCellValue(list.get(i).getTenkh());

                cell = row_sheet.createCell(6, CellType.STRING);
                cell.setCellValue(list.get(i).getSdt());

                cell = row_sheet.createCell(7, CellType.STRING);
                cell.setCellValue(list.get(i).getTongTien());

                cell = row_sheet.createCell(8, CellType.STRING);
                cell.setCellValue(list.get(i).getPttt());

                cell = row_sheet.createCell(9, CellType.STRING);
                cell.setCellValue(list.get(i).getTt());
            }

            File f = new File("D:\\Nhom4BanNhacCu\\Danhsachhoadon.xlsx");
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
    }//GEN-LAST:event_btnXuatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        loadToDaTa();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHDCTMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXuat;
    private javax.swing.JComboBox<String> cboHT;
    private javax.swing.JComboBox<String> cboTT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tblCT1;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTextField txtHD;
    private javax.swing.JTextField txtNGD;
    private javax.swing.JTextField txtNT;
    private javax.swing.JTextField txtNV;
    private javax.swing.JTextField txtPTTT;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTKH;
    private javax.swing.JTextField txtTNV;
    private javax.swing.JTextField txtTT;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
