package form;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import data.ScoreManager;
import dialogs.*;

public class Form extends JFrame {

	private static final long serialVersionUID = 845222177524994234L;
	private static final int EASY = 13;
	private static final int MEDIUM = 8;
	private static final int HARD = 5;
	
	private JGame game;
	private ScoreManager scoreManager;
	private JMenuBar mainMenu;
	private JMenu menuGame;
	private JMenu menuSize;
	private JMenu menuDifficulty;
	private JMenu menuScore;
	private int heightMenuDifference = 0;
	
	public Form() {
		this.setDefaultCloseOperation(Form.EXIT_ON_CLOSE);
		this.setTitle("Gray Sweeper");
		
		scoreManager = new ScoreManager();
		game = new JGame(16, 16, MEDIUM, this);
		this.getContentPane().add(game);

		if (System.getProperty("os.name").equals("Mac OS X")) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			heightMenuDifference = 22;
		}
		initializeMenu();
		
		this.setJMenuBar(mainMenu);
		this.setPreferredSize(new Dimension(482, 556 - heightMenuDifference));
		this.setLocation(300, 160);
		this.setResizable(false);
		pack();
	}

	// win dialog
	public void win(int dif) {
		NewScoreDialog dialog = new NewScoreDialog(this, game.getScore());
		dialog.setModal(true);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
		
		if (dialog.getResult() != null && !dialog.getResult().equals("")) { 
			int score = (game.getMinutes() * 100) + game.getSeconds();
			scoreManager.writeScore(score, dialog.getResult(), dif);
		}
	}
	
	// highscore tables
	public void scoreDialog(int dif) {
		ScoreTableDialog dialog = new ScoreTableDialog(this, scoreManager, dif);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}
	
	// about dialog
	public void aboutDialog() {
		AboutDialog dialog = new AboutDialog(this);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}
	
	// yes/no dialog
	public boolean yesNoDialog() {
		YesNoDialog dialog = new YesNoDialog(this);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
		return dialog.getResult();
	}
	
	// menu init
	private void initializeMenu() {
		mainMenu = new JMenuBar();
		menuGame = new JMenu("Game");
		menuDifficulty = new JMenu("Difficulty");
		menuSize = new JMenu("Size");
		menuScore = new JMenu("Highscores");

		// game
		JMenuItem menuItemNew = new JMenuItem("New game");
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { game.newGame(); }
		});
		JMenuItem menuItemAbout = new JMenuItem("About");
		menuItemAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { aboutDialog(); }
		});
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menuItemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { setVisible(false); dispose(); System.exit(0); }
		});
		menuGame.add(menuItemNew);
		menuGame.add(menuItemAbout);
		menuGame.add(menuItemExit);
		mainMenu.add(menuGame);
		
		// difficulty
		final JCheckBoxMenuItem menuItemEasy = new JCheckBoxMenuItem("Easy");
		final JCheckBoxMenuItem menuItemMedium = new JCheckBoxMenuItem("Medium");
		final JCheckBoxMenuItem menuItemHard = new JCheckBoxMenuItem("Hard");
		menuItemEasy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
		menuItemEasy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { game.setDifficulty(EASY); game.newGame(); 
				menuItemEasy.setSelected(true); menuItemMedium.setSelected(false); menuItemHard.setSelected(false);}
		});
		menuItemMedium.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
		menuItemMedium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { game.setDifficulty(MEDIUM); game.newGame(); 
				menuItemMedium.setSelected(true); menuItemEasy.setSelected(false); menuItemHard.setSelected(false);}
		});
		menuItemHard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
		menuItemHard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { game.setDifficulty(HARD); game.newGame(); 
				menuItemHard.setSelected(true); menuItemEasy.setSelected(false); menuItemMedium.setSelected(false);}
		});
		menuItemMedium.setSelected(true);
		menuDifficulty.add(menuItemEasy);
		menuDifficulty.add(menuItemMedium);
		menuDifficulty.add(menuItemHard);
		mainMenu.add(menuDifficulty);
		
		final JCheckBoxMenuItem menuItemSizeSmall = new JCheckBoxMenuItem("Small");
		final JCheckBoxMenuItem menuItemSizeMedium = new JCheckBoxMenuItem("Medium");
		final JCheckBoxMenuItem menuItemSizeLarge = new JCheckBoxMenuItem("Large");
		menuItemSizeSmall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItemSizeSmall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { game.newGame(16, 8); setSize(new Dimension(482, 316 - heightMenuDifference));
				menuItemSizeSmall.setSelected(true); menuItemSizeMedium.setSelected(false); menuItemSizeLarge.setSelected(false);}
		});
		menuItemSizeMedium.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItemSizeMedium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { game.newGame(16, 16); setSize(new Dimension(482, 556 - heightMenuDifference));
				menuItemSizeMedium.setSelected(true); menuItemSizeSmall.setSelected(false); menuItemSizeLarge.setSelected(false);}
		});
		menuItemSizeLarge.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItemSizeLarge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { game.newGame(32, 16); setSize(new Dimension(962, 556 - heightMenuDifference));
				menuItemSizeLarge.setSelected(true); menuItemSizeSmall.setSelected(false); menuItemSizeMedium.setSelected(false);}
		});
		menuItemSizeMedium.setSelected(true);
		menuSize.add(menuItemSizeSmall);
		menuSize.add(menuItemSizeMedium);
		menuSize.add(menuItemSizeLarge);
		mainMenu.add(menuSize);
		
		// score
		JMenuItem menuItemScoreEasy = new JMenuItem("Easy");
		JMenuItem menuItemScoreMedium = new JMenuItem("Medium");
		JMenuItem menuItemScoreHard = new JMenuItem("Hard");
		JMenuItem menuItemScoreReset = new JMenuItem("Reset tables"); 
		menuItemScoreEasy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { scoreDialog(EASY); }
		});
		menuItemScoreMedium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { scoreDialog(MEDIUM); }
		});
		menuItemScoreHard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { scoreDialog(HARD); }
		});
		menuItemScoreReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				if (yesNoDialog())
					scoreManager.fillTable(); 
			}
		});
		menuScore.add(menuItemScoreEasy);
		menuScore.add(menuItemScoreMedium);
		menuScore.add(menuItemScoreHard);
		menuScore.add(menuItemScoreReset);
		mainMenu.add(menuScore); 
	}
	
	// MAIN
	public static void main(String[] args) {

		Form form = new Form();
		form.setVisible(true);
	}
}
