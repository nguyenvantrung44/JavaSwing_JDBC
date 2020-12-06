package project.dtos;

public class TaiKhoanDTO {
	private String maTK;
	private String tenTK;
	private String matKhau;

	public TaiKhoanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaiKhoanDTO(String maTK, String tenTK, String matKhau) {
		super();
		this.maTK = maTK;
		this.tenTK = tenTK;
		this.matKhau = matKhau;
	}

	public String getMaTK() {
		return maTK;
	}

	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Override
	public String toString() {
		return "TaiKhoan [maTK=" + maTK + ", tenTK=" + tenTK + ", matKhau=" + matKhau + "]";
	}
}
