package project.dtos;

public class LoginDTO {
	private String tenTK, maNV, matkhau, role;

	public LoginDTO() {
		super();
	}

	public LoginDTO(String tenTK, String maNV, String matkhau, String role) {
		super();
		this.tenTK = tenTK;
		this.maNV = maNV;
		this.matkhau = matkhau;
		this.role = role;
	}

	public LoginDTO(String tenTK, String matkhau) {
		super();
		this.tenTK = tenTK;
		this.matkhau = matkhau;
	}

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
