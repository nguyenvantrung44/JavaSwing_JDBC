package xuly;

import java.sql.SQLException;

import project.db.MyConnection;
import project.views.Login_JFrame;
import project.views.FrmGioiThieu;
import java.awt.Toolkit;

public class CountDownThread extends Thread {

	public static void main(String[] args) {
		int count = 3;

		try {
			MyConnection.getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FrmGioiThieu frmgthLoading = new FrmGioiThieu();
		frmgthLoading.setIconImage(Toolkit.getDefaultToolkit().getImage("image/loading-icon.png"));
		frmgthLoading.setTitle("Loading...");
		frmgthLoading.setVisible(true);
		frmgthLoading.setLocationRelativeTo(null);
		for (int i = 0; i < count; i++) {
			try {
				Thread.sleep(1000);
				frmgthLoading.progressBar.setValue(33);
				if (i == 1)
					frmgthLoading.progressBar.setValue(66);
				if (i == 2)
					frmgthLoading.progressBar.setValue(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		frmgthLoading.setVisible(false);
		Login_JFrame login_JFrame = new Login_JFrame();
		login_JFrame.setVisible(true);
		login_JFrame.setLocationRelativeTo(null);
	}
}
