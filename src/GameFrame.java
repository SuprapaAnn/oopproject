import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
//======== Audio Player ======
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.imageio.ImageIO;


import java.net.URL;

/**
 * GUI class for the game interface.
 * Responsible for displaying buttons and input fields 
 * to allow users to control program flow in order to play games, 
 * look at help pages and exit the program.
 * @author Jeremy Ma
 */
public class GameFrame {

	private InstructionFrame instructions;
	private JPanel optionPanel;
	private JFrame frame;
	
	//Frame components
	private JButton playButton;
	private JButton howButton;
	private JButton exitButton;


	
	
	/** Generates the introductory window of the Game
	 * @param game The game object that contains the details of in game screens and the maze.
	 * @param width Width of the window.
	 * @param height Height of the window.
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	public GameFrame(Game game, int width, int height) {
		
		// play audio 
	    /*
		File audioFile = new File("starlog.wav");
		 
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
		
		AudioFormat format = audioStream.getFormat();
		Clip clip = AudioSystem.getClip(); 
		

		clip.open(audioStream);
		clip.start();
		
		
		
		Clip music = ImageLoader.LoadSound("/src/starlog.wav"); 
		music.loop(Clip.LOOP_CONTINUOUSLY);
		
		*/
		frame = new JFrame();
		
		//Set Minimum size
		Dimension minSize = new Dimension(600, 600);
		frame.setMinimumSize(minSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Test Game");
		final Game g = game;
		
		this.instructions = new InstructionFrame();
		this.optionPanel = new OptionPanel(g);
		
		//Set user size
		frame.setSize(width, height);
		
		//Make size fixed
		frame.setResizable(false);
		
		//Set layout
		frame.setLayout(new GridBagLayout());
		
		//Set background colour
		frame.getContentPane().setBackground(Color.BLACK); // Background Color
		
		//Make the title page
		
		//get images
		ImageIcon link = new ImageIcon(GameFrame.this.getClass().getResource("/sprites/linkImage.gif")); // Title Mascot
		JLabel linkImage = new JLabel(link);
		
		ImageIcon title = new ImageIcon(GameFrame.this.getClass().getResource("/sprites/wtp.png")); // Title LOgo
		JLabel titleImage = new JLabel(title);
		
	    GridBagConstraints c = new GridBagConstraints();
	    
	    c.gridheight = 15;
	    c.gridwidth = 10;
	    c.gridy = 0;
	    c.gridx = 0;
	    frame.add(titleImage,c);
	    
		//add image of link
		c.gridwidth = 4;
		c.gridheight = 10;
		c.gridy = 15;
		c.gridx =3 ;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,250,0,0);
	    frame.add(linkImage, c);
	    
		c.gridwidth = 1;
		c.gridheight = 1;
		//Add play button
	    c.gridy = 26;
	    c.gridx = 3;
	    c.insets = new Insets(0,235,0,0);
	    playButton = new JButton("Play Game!");
		this.playButton.setBackground(Color.BLACK);
		this.playButton.setForeground(Color.PINK);
		
		frame.add(playButton,c); 
		this.playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null,optionPanel,"Choose Name & Character ",
						JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					g.getPlayer().setName(optionPanel.getName()); // Testsadsadkmsadlmsadl;msaddsaml;sadml;dadas
					g.createMaze(); //based on user options
					g.setIsInGame(true);
					g.setIsGameOver(false);
					frame.setVisible(false);
				}
			}
		});
		c.insets = new Insets(0,0,0,0);
		//Add how to play button
		c.gridy = 26;
	    c.gridx = 4;
	    howButton = new JButton("How to Play");
		this.howButton.setBackground(Color.BLACK);
		this.howButton.setForeground(Color.pink);
		this.howButton.setEnabled(true);
		frame.add(howButton,c);
		this.howButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instructions.setVisible(true);
			}
		});

		//Add exit button
		c.gridy =26;
	    c.gridx = 5;
	    exitButton = new JButton("EXIT");
		this.exitButton.setBackground(Color.BLACK);
		
		this.exitButton.setForeground(Color.PINK);
		
		frame.add(exitButton,c);		
		this.exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		
		//Pack
		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	
	
	
	/**
	 * Gets the frame of the introductory game window (game frame).
	 * @return the frame of the introductory game window (game frame).
	 */
	public JFrame getFrame() {
		return frame;
	}
}
