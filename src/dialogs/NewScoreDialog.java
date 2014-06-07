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
import javax.swing.JTextField;

public class NewScoreDialog extends JDialog {
	
	private static final long serialVersionUID = 643421226831L;

	private JLabel lblGratz;
	private JLabel lblText;
	private JPanel labelPanel;
	private JTextField txtName;
	private JButton btnOk;
	private String name;
	private Color lightGray = new Color (235, 235, 235);
	private Font font = new Font("Monospaced", Font.PLAIN, 14);
	
	public NewScoreDialog(JFrame parentFrame, String score) {
		super(parentFrame);
		setTitle("New Score");
		this.setResizable(false);
		this.getContentPane().setBackground(Color.black);
		
		GridLayout labelLayout = new GridLayout(2, 1);
		labelPanel = new JPanel();
		labelPanel.setLayout(labelLayout);
		labelPanel.setBackground(Color.black);

		lblGratz = new JLabel("Gratz, you won with time " + score, JLabel.CENTER);
		lblGratz.setFont(font);
		lblGratz.setForeground(lightGray);
		lblText = new JLabel("Type your name for highscore table", JLabel.CENTER);
		lblText.setFont(font);
		lblText.setForeground(lightGray);
		labelPanel.add(lblGratz);
		labelPanel.add(lblText);
		
		txtName = new JTextField();
		txtName.setFont(font);
		
		btnOk = new JButton("<html><b>OK</b></html>");
		btnOk.setBackground(Color.gray);
		btnOk.setForeground(Color.black);
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
