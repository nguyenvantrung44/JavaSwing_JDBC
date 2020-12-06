package project.views;

import javax.swing.SwingUtilities;

public class ChayLogin {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Login_JFrame gd = new Login_JFrame();
				gd.setVisible(true);
			}
		});
	}
}
