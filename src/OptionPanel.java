import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Class containing JPanel for options for Character, Difficulty selection and setting Name
 * @author Jeremy Ma
 */
public class OptionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton setRosalyn;	//buttons for selecting characters
	private JButton setMiton;
	private JButton setNoah;
	private JTextField nameField;	//place where user enters name
	
	private JRadioButton easyDifficulty;	//buttons for choosing difficulty
	private JRadioButton mediumDifficulty;
	private JRadioButton hardDifficulty;

	/**
	 * Construct the JPanel containing elements for the option panel.
	 * @param g the Game object describing the game state
	 */
	public OptionPanel(Game g){
		final Game game = g;
		this.setLayout(new GridBagLayout());		
		GridBagConstraints gbc = new GridBagConstraints();
		
		Sprite RosaltnPanel = new Sprite(MazeFrame.RosaltnSprite, 48, 48);
		Sprite MitonPanel = new Sprite(MazeFrame.MitonSprite, 48, 48);
		Sprite NoahPanel = new Sprite(MazeFrame.NoahSprite, 48, 48);
		
		setRosalyn = new JButton("Rosalyn", RosaltnPanel.getSprite() );
		setMiton = new JButton("Miton", MitonPanel.getSprite());
		setNoah = new JButton("Noah", NoahPanel.getSprite());
		
		//set up selecting character when user clicks the corresponding button
		setRosalyn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				game.getPlayer().setCharacter(MazeFrame.RosaltnSprite);
			}
		});
		setMiton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				game.getPlayer().setCharacter(MazeFrame.MitonSprite);
			}
		});
		setNoah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				game.getPlayer().setCharacter(MazeFrame.NoahSprite);
			}
		});;
		this.add(setRosalyn);	//add choose character buttons to panel
		this.add(setMiton);
		this.add(setNoah);
		
		nameField = new JTextField("Enter Name");
		nameField.setPreferredSize(new Dimension(200,20));
		
		// Move to next line
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.insets = new Insets(20,20,0,0);
		
		JLabel nameText = new JLabel("Introduce yourself: ");
		this.add(nameText, gbc);
		
		gbc.gridx = 1;
		this.add(nameField, gbc);

		// Move to next line
		gbc.gridy = 2;
		gbc.gridx = 0;
		JLabel difficultyText = new JLabel("Choose difficulty:");
		this.add(difficultyText, gbc);
		
		//Add setting difficulty option
		ButtonGroup difficultyButtons = new ButtonGroup();
		easyDifficulty = new JRadioButton("Easy");
		mediumDifficulty = new JRadioButton("Medium", true);
		hardDifficulty = new JRadioButton("Hard");
		difficultyButtons.add(easyDifficulty);
		difficultyButtons.add(mediumDifficulty);
		difficultyButtons.add(hardDifficulty);
		
		//Move to next line, and add buttons
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.insets = new Insets(10,10,0,0);
		this.add(easyDifficulty,gbc);
		
		gbc.gridx = 1;
		this.add(mediumDifficulty,gbc);
		
		gbc.gridx = 2;
		this.add(hardDifficulty,gbc);
		
		//set up changing game difficulty when radio button is selected
		easyDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				game.setDifficulty(Game.EASY);
			}
		});
		mediumDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				game.setDifficulty(Game.MEDIUM);
			}
		});
		hardDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				game.setDifficulty(Game.HARD);
			}
		});
	}
	
	/**
	 * Gets the name user entered in the text field
	 * @return the name user entered in the text field
	 */
	public String getName(){
		return nameField.getText();
	}
}
