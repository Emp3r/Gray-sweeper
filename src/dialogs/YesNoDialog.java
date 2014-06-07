package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class YesNoDialog extends JDialog {

	private static final long serialVersionUID = 3108333650033893578L;

	private JLabel lblReally;
	private JPanel buttonPanel;
	private JButton btnYes;
	private JButton btnNo;
	private boolean result = false;
	private Color lightGray = new Color (235, 235, 235);
	private Font font = new Font("Monospaced", Font.PLAIN, 14);

	public YesNoDialog(JFrame parentFrame) {
		super(parentFrame);
		setTitle("Really?");
		this.setResizable(false);
		this.getContentPane().setBackground(Color.black);
		
		String text = "<html><center><br><b>Are you sure?</b><br> It will delete all records in every category.<br><br></center></html>";
		lblReally = new JLabel(text, JLabel.CENTER);
		lblReally.setFont(font);
		lblReally.setForeground(lightGray);
		
		GridLayout buttonLayout = new GridLayout(1, 2);
		buttonLayout.setHgap(8);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.setBackground(Color.black);
		
		btnYes = new JButton("<html><b>YES</b></html>");
		btnYes.setBackground(Color.gray);
		btnYes.setForeground(Color.black);
		btnYes.setFocusPainted(false);
		btnYes.setOpaque(true);
		btnYes.setBorderPainted(false);
		btnYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { setVisible(false); result = true; }
		});
		
		btnNo = new JButton("<html><b>NO</b></html>");
		btnNo.setBackground(Color.gray);
		btnNo.setForeground(Color.black);
		btnNo.setFocusPainted(false);
		btnNo.setOpaque(true);
		btnNo.setBorderPainted(false);
		btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { setVisible(false); result = false; }
		});

		buttonPanel.add(btnNo);
		buttonPanel.add(btnYes);
		
		this.setPreferredSize(new Dimension(380, 120));
		getContentPane().setLayout(new BorderLayout(8, 8));
		getContentPane().add(lblReally, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	

	public boolean getResult() {
		return result;
	}
}
