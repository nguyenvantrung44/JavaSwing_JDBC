package baocao;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import project.daos.ThongKeDoanhThuTQDAO;
import project.daos.ThongKeNhanVienTheoNgay;
import project.daos.ThongKeTinhTrangLKDAO;
import project.dtos.ThongKeDoanhThuDTO;
import project.dtos.ThongKeHDNhanVien;
import project.dtos.ThongKeTinhTrangLK;

public class BaoCao {
	FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);
	private HSSFWorkbook workbook;

	private String getFile() {
		fd.setFile("untitled.xls");
		fd.setVisible(true);
		String url = fd.getDirectory() + fd.getFile();
		if (url.equals("nullnull")) {
			return null;
		}
		return url;
	}

	DecimalFormat df = new DecimalFormat("#,###.#");

	public void xuatFileExcelDoanhThu(int thang, int nam) {
		fd.setTitle("Xuất dữ liệu doanh thu ra excel");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Doanh Thu");

			ThongKeDoanhThuTQDAO dao = new ThongKeDoanhThuTQDAO();
			ArrayList<ThongKeDoanhThuDTO> list1;
			try {
				list1 = (ArrayList<ThongKeDoanhThuDTO>) dao.ThongKeDoanhThuTQ(thang, nam);
				int rownum = 0;
				Row row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1, CellType.STRING).setCellValue("Mã sản phẩm");
				row.createCell(2, CellType.STRING).setCellValue("Tên sản phẩm");
				row.createCell(3, CellType.STRING).setCellValue("Đơn giá bán");
				row.createCell(4, CellType.STRING).setCellValue("Đơn giá nhập");
				row.createCell(5, CellType.STRING).setCellValue("Số lượng bán");
				row.createCell(6, CellType.STRING).setCellValue("Tiền bán được");
				row.createCell(7, CellType.STRING).setCellValue("Lợi nhuận");
				row.createCell(8, CellType.STRING).setCellValue("Đơn vị tiền tệ");

				double lnc = 0;
				double ln = 0;
				double bd = 0;
				for (ThongKeDoanhThuDTO dt : list1) {

					bd = dt.getTienBanDuoc();
					ln = bd - (dt.getSoluongban() * dt.getGiaNhap());
					lnc += ln;
					rownum++;
					row = sheet.createRow(rownum);

					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(dt.getMaSP());
					row.createCell(2, CellType.STRING).setCellValue(dt.getTenSP());
					row.createCell(3, CellType.STRING).setCellValue(df.format(dt.getGiaBan()));
					row.createCell(4, CellType.STRING).setCellValue(df.format(dt.getGiaNhap()));
					row.createCell(5, CellType.STRING).setCellValue(dt.getSoluongban());
					row.createCell(6, CellType.STRING).setCellValue(df.format(dt.getTienBanDuoc()));
					row.createCell(7, CellType.STRING).setCellValue(df.format(ln));
					row.createCell(8, CellType.STRING).setCellValue("VND");

				}
				rownum++;
				row = sheet.createRow(rownum);
				row.createCell(0, CellType.STRING)
						.setCellValue("Tổng lợi nhuận" + String.valueOf(thang) + "-" + String.valueOf(nam) + ":");
				row.createCell(7, CellType.STRING).setCellValue(df.format(lnc));
				row.createCell(8, CellType.STRING).setCellValue("VND");
				for (int i = 0; i < rownum; i++) {
					sheet.autoSizeColumn(i);
				}

			} catch (ClassNotFoundException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);
			JOptionPane.showMessageDialog(null, "Xuất File thành công !!!\n"+"Đường dẫn: "+ file.getAbsolutePath(), "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));

		} catch (FileNotFoundException ex) {
			Logger.getLogger(BaoCao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

		} catch (IOException ex) {
			Logger.getLogger(BaoCao.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(BaoCao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public void xuatFileExcelThongKeNhanVienTheoNgay(String maNV, int ngay, int thang, int nam) {
		fd.setTitle("Xuất dữ liệu nhân viên theo ngày ra excel");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Thống kê nhân viên theo ngày");

//			ThongKeDoanhThuTQDAO dao = new ThongKeDoanhThuTQDAO();
//			ArrayList<ThongKeDoanhThuDTO> list1;

			ThongKeNhanVienTheoNgay dao = new ThongKeNhanVienTheoNgay();
			ArrayList<ThongKeHDNhanVien> list;
			try {
				list = (ArrayList<ThongKeHDNhanVien>) dao.ThongKeHDNhanVienTheoNgay(maNV, ngay, thang, nam);
				int rownum = 0;
				Row row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1, CellType.STRING).setCellValue("Mã Hoa Đơn");
				row.createCell(2, CellType.STRING).setCellValue("Mã nhân viên");
				row.createCell(3, CellType.STRING).setCellValue("Tên nhân viên");
				row.createCell(4, CellType.STRING).setCellValue("Ngày lập hóa đơn");
				row.createCell(5, CellType.STRING).setCellValue("Tiền hóa đơn");
				row.createCell(6, CellType.STRING).setCellValue("Số lượng sản phẩm");
				row.createCell(7, CellType.STRING).setCellValue("Tiền tệ");

				for (ThongKeHDNhanVien dt : list) {
					rownum++;
					row = sheet.createRow(rownum);

					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(dt.getMaHD());
					row.createCell(2, CellType.STRING).setCellValue(dt.getMaNV());
					row.createCell(3, CellType.STRING).setCellValue(dt.getTenNV());
					row.createCell(4, CellType.STRING).setCellValue(sdf.format(dt.getngayLap()));
					row.createCell(5, CellType.STRING).setCellValue(df.format(dt.getthanhTien()));
					row.createCell(6, CellType.STRING).setCellValue(dt.getSoLuong());
					row.createCell(7, CellType.STRING).setCellValue("VND");

				}
				for (int i = 0; i < rownum; i++) {
					sheet.autoSizeColumn(i);
				}

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);

			JOptionPane.showMessageDialog(null, "Xuất File thành công !!!\n"+"Đường dẫn: "+ file.getAbsolutePath(), "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(BaoCao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

		} catch (IOException ex) {
			Logger.getLogger(BaoCao.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(BaoCao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void xuatFileExcelThongKeLinhKienDaBan(int ngay, int thang, int nam) {
		fd.setTitle("Xuất dữ liệu linh kiện đã bán theo ngày ra excel");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Thống kê linh kiện đã bán theo ngày");
			ThongKeTinhTrangLKDAO dao = new ThongKeTinhTrangLKDAO();
			ArrayList<ThongKeTinhTrangLK> list;
			try {
				list = (ArrayList<ThongKeTinhTrangLK>) dao.ThongKeTTLK(ngay, thang, nam);

				int rownum = 0;
				Row row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1, CellType.STRING).setCellValue("Mã Sản Phẩm");
				row.createCell(2, CellType.STRING).setCellValue("Tên Sản Phẩm");
				row.createCell(3, CellType.STRING).setCellValue("Số lượng bán");
				row.createCell(4, CellType.STRING).setCellValue("Ngày lập");

				for (ThongKeTinhTrangLK dt : list) {
					rownum++;
					row = sheet.createRow(rownum);

					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(dt.getMaSP());
					row.createCell(2, CellType.STRING).setCellValue(dt.getTenSP());
					row.createCell(3, CellType.STRING).setCellValue(dt.getSoLuongBan());
					row.createCell(4, CellType.STRING).setCellValue(sdf.format(dt.getNgayLap()));

				}
				for (int i = 0; i < rownum; i++) {
					sheet.autoSizeColumn(i);
				}

			} catch (ClassNotFoundException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);

			JOptionPane.showMessageDialog(null, "Xuất File thành công !!!\n"+"Đường dẫn: "+ file.getAbsolutePath(), "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(BaoCao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

		} catch (IOException ex) {
			Logger.getLogger(BaoCao.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(BaoCao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
