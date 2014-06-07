package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Record;
import data.ScoreManager;

public class ScoreTableDialog extends JDialog {
	
	private static final long serialVersionUID = 143421226811L;

	private JLabel lblTop;
	private JPanel labelPanel;
	private JButton btnOk;
	private Color lightGray = new Color (235, 235, 235);

	public ScoreTableDialog(JFrame parentFrame, ScoreManager sm, int difficulty) {
		super(parentFrame);
		setTitle("Highscores");
		this.setResizable(false);
		this.getContentPane().setBackground(Color.black);
		
		// labels
		GridLayout labelLayout = new GridLayout(10, 1);
		labelPanel = new JPanel();
		labelPanel.setLayout(labelLayout);
		labelPanel.setBackground(Color.black);
		
		List<Record> list;
		if (difficulty == 13) {
			list = sm.getListEasy();
			lblTop = new JLabel("Easy", JLabel.CENTER);
		} else if (difficulty == 8) {
			list = sm.getListMedium();
			lblTop = new JLabel("Medium", JLabel.CENTER);
		} else {
			list = sm.getListHard();
			lblTop = new JLabel("Hard", JLabel.CENTER);
		}
		lblTop.setForeground(Color.white);
		lblTop.setFont(new Font("Monospaced", Font.BOLD, 16));

		int i = 1;
		for (Record r : list) {
			String text = String.format(" %2d.  ", i) + r.toString();
			JLabel lblRecord = new JLabel(text);
			lblRecord.setForeground(lightGray);
			lblRecord.setFont(new Font("Monospaced", Font.PLAIN, 14));
			labelPanel.add(lblRecord);
			i++;
		}
		
		btnOk = new JButton("<html><b>OK</b></html>");
		btnOk.setBackground(Color.gray);
		btnOk.setForeground(Color.black);
		btnOk.setFocusPainted(false);
		btnOk.setOpaque(true);
		btnOk.setBorderPainted(false);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { setVisible(false); }
		});
		
		getRootPane().setDefaultButton(btnOk);
		setPreferredSize(new Dimension(344, 300));
		getContentPane().setLayout(new BorderLayout(8, 8));
		getContentPane().add(lblTop, BorderLayout.NORTH);
		getContentPane().add(labelPanel, BorderLayout.CENTER);
		getContentPane().add(btnOk, BorderLayout.SOUTH);
		pack();
	}
	
}

