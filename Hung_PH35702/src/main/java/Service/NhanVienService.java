package Service;

import com.example.asm2_test.NhanVien;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NhanVienService {
    private List<NhanVien> nv = new ArrayList<>();

    public void addNV(NhanVien nhanvien) {
        String tenNV = nhanvien.getTen();
        String sdt = nhanvien.getSdt();
        String email = nhanvien.getEmail();
        LocalDate ngaySinh = nhanvien.getNgaySinh();
        String cccd = nhanvien.getCccd();

        // Validate string lengths first
        if (tenNV.length() < 2 || tenNV.length() > 25) {
            throw new IllegalArgumentException("Độ dài tên từ 1-25");
        }
        if (sdt.length() != 10) {
            throw new IllegalArgumentException("Sđt phải có 10 số");
        }
        if (cccd.length() != 12) { // Assuming CCCD has 12 digits
            throw new IllegalArgumentException("CCCD phải có 12 số");
        }

        // Validate string contents
        if (tenNV.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Tên không được chứa số");
        }
        if (sdt.matches(".*[^0-9].*")) {
            throw new IllegalArgumentException("Số điện thoại không được chứa chữ hoặc ký tự đặc biệt.");
        }

        // Validate email format
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }

        // Add NhanVien to the list if all validations pass
        nv.add(nhanvien);
    }
    public void deleteNV(int maNV) {
        boolean found = false;
        for (int i = 0; i < nv.size(); i++) {
            if (nv.get(i).getMaNV() == maNV) {
                nv.remove(i);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Mã nhân viên không tồn tại");
        }

    }

    public void updateNV( int maNV, String tenNV, String sdt, String email, LocalDate ngaySinh, String cccd) {
        boolean found = false;
        for (int i = 0; i < nv.size(); i++) {
            if (nv.get(i).getMaNV() == maNV) {
                nv.set(i, new NhanVien(maNV, tenNV, sdt, email, ngaySinh, cccd));
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Mã nhân viên không tồn tại");
        }
        if (tenNV.length() < 2 || tenNV.length() > 25) {
            throw new IllegalArgumentException("Độ dài tên từ 1-25");
        }
        if (sdt.length() != 10) {
            throw new IllegalArgumentException("Sđt phải có 10 số");
        }
        if (tenNV.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Tên không được chứa số");
        }
    }

    private boolean isValidEmail(String email) {
        // Implement email validation logic here
        // You can use regular expressions or other validation methods
        return true; // Replace with your actual validation logic
    }

    public List<NhanVien> getNV() {
        return nv;
    }


}
