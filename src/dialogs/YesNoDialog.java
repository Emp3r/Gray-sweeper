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

public class YesNoDialog extends JDialog {

	private static final long serialVersionUID = 3108333650033893578L;

	private JLabel lblReally;
	private JPanel buttonPanel;
	private JButton btnYes;
	private JButton btnNo;
	private boolean result = false;

	public YesNoDialog(JFrame parentFrame, String string) {
		super(parentFrame);
		setTitle("Really?");
		this.setResizable(false);
		this.getContentPane().setBackground(Appearance.color8);
		
		String text = "<html><center><br><b>Are you sure?</b><br> " + string + "<br><br></center></html>";
		lblReally = new JLabel(text, JLabel.CENTER);
		lblReally.setFont(Appearance.fontDialogs);
		lblReally.setForeground(Appearance.color10);
		
		GridLayout buttonLayout = new GridLayout(1, 2);
		buttonLayout.setHgap(8);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.setBackground(Appearance.color8);

		initializeNoButton();
		buttonPanel.add(btnNo);
		initializeYesButton();
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
	
	private void initializeNoButton() {
		btnNo = new JButton("<html><b>NO</b></html>");
		btnNo.setBackground(Appearance.color11);
		btnNo.setForeground(Color.BLACK);
		btnNo.setFocusPainted(false);
		btnNo.setOpaque(true);
		btnNo.setBorderPainted(false);
		btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				setVisible(false); 
				result = false; 
			}
		});
	}
	
	private void initializeYesButton() {
		btnYes = new JButton("<html><b>YES</b></html>");
		btnYes.setBackground(Appearance.color11);
		btnYes.setForeground(Color.BLACK);
		btnYes.setFocusPainted(false);
		btnYes.setOpaque(true);
		btnYes.setBorderPainted(false);
		btnYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				setVisible(false); 
				result = true; 
			}
		});
	}
}
