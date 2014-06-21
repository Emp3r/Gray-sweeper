package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import utils.Appearance;
import utils.Rules;

public class ScoreTableDialog extends JDialog {
	
	private static final long serialVersionUID = 143421226811L;

	private JLabel lblTop;
	private JPanel mainPanel;
	private JButton btnOk;

	public ScoreTableDialog(JFrame parentFrame, ScoreManager sm, int difficulty) {
		super(parentFrame);
		setTitle("Highscores");
		this.setResizable(false);
		this.getContentPane().setBackground(Appearance.color8);
		
		initializeTopLabel(difficulty);
		initializeOkButton();

		GridLayout panelLayout = new GridLayout(1, 3);
		panelLayout.setHgap(2);
		mainPanel = new JPanel();
		mainPanel.setLayout(panelLayout);
		mainPanel.setBackground(Appearance.color9);		
		
		String topSizeString = "Small";
		for (List<Record> list : sm.getLists(difficulty)) {
			initializeLabels(list, topSizeString);
			
			if (topSizeString.equals("Medium")) topSizeString = "Large";
			else topSizeString = "Medium";
		}
		
		getRootPane().setDefaultButton(btnOk);
		setPreferredSize(new Dimension(856, 300));
		getContentPane().setLayout(new BorderLayout(8, 8));
		getContentPane().add(lblTop, BorderLayout.NORTH);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(btnOk, BorderLayout.SOUTH);
		pack();
	}
	
	// top label lblTop - Easy/Medium/Hard
	private void initializeTopLabel(int difficulty) {
		if (difficulty == Rules.EASY)
			lblTop = new JLabel("Easy", JLabel.CENTER);
		else if (difficulty == Rules.MEDIUM)
			lblTop = new JLabel("Medium", JLabel.CENTER);
		else
			lblTop = new JLabel("Hard", JLabel.CENTER);
		lblTop.setForeground(Appearance.color0);
		lblTop.setFont(Appearance.fontComponent);
	}
	
	private void initializeLabels(List<Record> list, String topString) {
		GridLayout labelLayout = new GridLayout(11, 1);
		JPanel sizePanel = new JPanel();
		sizePanel.setLayout(labelLayout);
		sizePanel.setBackground(Appearance.color8);

		JLabel lblSize = new JLabel(topString, JLabel.CENTER);
		lblSize.setForeground(Appearance.color0);
		lblSize.setFont(Appearance.fontDialogTop);
		sizePanel.add(lblSize);
		
		int i = 1;
		for (Record r : list) {
			String text = String.format(" %2d.  ", i) + r.toString();
			JLabel lblRecord = new JLabel(text, JLabel.CENTER);
			lblRecord.setForeground(Appearance.color10);
			lblRecord.setFont(Appearance.fontRecords);
			sizePanel.add(lblRecord);
			i++;
		}
		mainPanel.add(sizePanel);
	}
	
	private void initializeOkButton() {
		btnOk = new JButton("<html><b>OK</b></html>");
		btnOk.setBackground(Appearance.color11);
		btnOk.setForeground(Color.BLACK);
		btnOk.setFocusPainted(false);
		btnOk.setOpaque(true);
		btnOk.setBorderPainted(false);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}
