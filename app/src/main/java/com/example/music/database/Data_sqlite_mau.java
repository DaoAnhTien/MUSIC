package com.example.music.database;

public class Data_sqlite_mau {
    public static final String INSERT_NGUOIDUNG ="Insert into NguoiDung(maND,hoTen,matKhau)  values" +
            "('admin','daoanhtien','123')," +
            "('tiendt','nguyenvantien','123')";
    public static final String INSERT_THELOAI ="Insert into TheLoai(tenTL)  values " +
            "            ('nhạc vàng'), " +
            "            ('nhạc Trữ tình'), " +
            "            ('nhạc Đỏ'), " +
            "            ('nhạc Trẻ'), " +
            "            ('nhạc remix'), " +
            "            ('cải lương ')," +
            "            ('Vọng cổ'), " +
            "            ('hat chèo'),             " +
            "            ('nhạc cách mạng'), " +
            "            ('nhạc nhạc sống '),  " +
            "            ('nhac trẻ');";
    public static final String INSERT_CASI="Insert into CaSi(hoTen,ngaySinh,gioiTinh)  values  " +
            "            ('Đàm Vĩnh Hưng','1977/2/30','nam'),  " +
            "            ('Hoài Lâm','1990/8/24','nam'),  " +
            "            ('Lệ Quyên','1987/9/16','nữ'),  " +
            "            ('Đan nguyên','1970/9/29','nam'),  " +
            "            ('Phi Nhung','1980/10/14','nữ'),  " +
            "            ('Thùy Tiên','1987/5/21','nữ');";
    public static final String INSERT_NHAC=" Insert into Nhac(MaTL,MaCS,tenBH,linkBH,namRD)  values " +
            "            ('1','1','Phượng Buồn','https://zingmp3.vn/bai-hat/Phuong-Buon-Cam-Ly-Dam-Vinh-Hung/ZWZCW7AB.html','1999')," +
            "                    ('2','2','Như Những phút Ban đầu','https://zingmp3.vn/bai-hat/Nhu-Nhung-Phut-Ban-Dau-Hoai-Lam/ZW6D0IBA.html','2018');";
}
