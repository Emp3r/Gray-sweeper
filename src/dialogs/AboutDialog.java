package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Appearance;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 7571189229681651679L;

	private JPanel labelPanel;
	private JButton btnOk;
	
	public AboutDialog(JFrame parentFrame) {
		super(parentFrame);
		setTitle("About");
		this.setResizable(false);
		this.getContentPane().setBackground(Appearance.color8);

		String text = "<html><center><br><b>Gray Sweeper</b> is a Java implementation<br>of the well known puzzle game Minesweeper</center></html>";
		JLabel lblGray = new JLabel(text, JLabel.CENTER);
		JLabel lblName = new JLabel("by Josef Podstata", JLabel.CENTER);
		JLabel lblMail = new JLabel("emp3rr@gmail.com", JLabel.CENTER);
		JLabel lblInfo = new JLabel("Written in Eclipse 4.3.2", JLabel.CENTER);
		lblGray.setFont(Appearance.fontDialogs);
		lblGray.setForeground(Appearance.color10);
		lblName.setFont(Appearance.fontDialogs);
		lblName.setForeground(Appearance.color10);
		lblMail.setFont(Appearance.fontDialogs);
		lblMail.setForeground(Appearance.color10);
		lblInfo.setFont(Appearance.fontDialogs);
		lblInfo.setForeground(Appearance.color10);
		
		GridLayout labelLayout = new GridLayout(5, 1);
		labelPanel = new JPanel();
		labelPanel.setLayout(labelLayout);
		labelPanel.setBackground(Appearance.color8);

		labelPanel.add(lblName);
		labelPanel.add(lblMail);
		labelPanel.add(new JLabel());
		labelPanel.add(lblInfo);

		btnOk = new JButton("<html><b>OK</b></html>");
		btnOk.setBackground(Appearance.color11);
		btnOk.setForeground(Color.BLACK);
		btnOk.setFocusPainted(false);
		btnOk.setOpaque(true);
		btnOk.setBorderPainted(false);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { setVisible(false); }
		});
		
		getRootPane().setDefaultButton(btnOk);
		this.setPreferredSize(new Dimension(338, 208));
		getContentPane().setLayout(new BorderLayout(8, 8));
		getContentPane().add(lblGray, BorderLayout.NORTH);
		getContentPane().add(labelPanel, BorderLayout.CENTER);
		getContentPane().add(btnOk, BorderLayout.SOUTH);
		pack();
	}
}

