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
import javax.swing.JTextField;

import utils.Appearance;

public class NewScoreDialog extends JDialog {
	
	private static final long serialVersionUID = 643421226831L;

	private JLabel lblGratz;
	private JLabel lblText;
	private JPanel labelPanel;
	private JTextField txtName;
	private JButton btnOk;
	private String name;
	
	public NewScoreDialog(JFrame parentFrame, String score) {
		super(parentFrame);
		setTitle("New Score");
		this.setResizable(false);
		this.getContentPane().setBackground(Appearance.color8);
		
		GridLayout labelLayout = new GridLayout(2, 1);
		labelPanel = new JPanel();
		labelPanel.setLayout(labelLayout);
		labelPanel.setBackground(Appearance.color8);

		lblGratz = new JLabel("Gratz, you won with time " + score, JLabel.CENTER);
		lblGratz.setFont(Appearance.fontDialogs);
		lblGratz.setForeground(Appearance.color10);
		lblText = new JLabel("Type your name for highscore table", JLabel.CENTER);
		lblText.setFont(Appearance.fontDialogs);
		lblText.setForeground(Appearance.color10);
		labelPanel.add(lblGratz);
		labelPanel.add(lblText);
		
		txtName = new JTextField();
		txtName.setFont(Appearance.fontDialogs);
		
		btnOk = new JButton("<html><b>OK</b></html>");
		btnOk.setBackground(Appearance.color11);
		btnOk.setForeground(Color.BLACK);
		btnOk.setFocusPainted(false);
		btnOk.setOpaque(true);
		btnOk.setBorderPainted(false);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name = txtName.getText();
				setVisible(false);
			}
		});

		getRootPane().setDefaultButton(btnOk);
		this.setPreferredSize(new Dimension(286, 136));
		getContentPane().setLayout(new BorderLayout(8, 8));
		getContentPane().add(labelPanel, BorderLayout.NORTH);
		getContentPane().add(txtName, BorderLayout.CENTER);
		getContentPane().add(btnOk, BorderLayout.SOUTH);
		pack();
	}
	
	public String getResult() {
		return name;
	}
}
