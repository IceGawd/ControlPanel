import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Runner {
	public JFrame frame = new JFrame("ControlPanic");
	public JPanel panel;
	public static Font main;
	public static Font second;
	private Keyboard key = new Keyboard();
	public static int WINX = 500;
	public static int WINY = 500;
	public ArrayList<Clip> sounds = new ArrayList<Clip>();
	private static final int delay = 17;
	private Timer repaint = new Timer(delay, new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	
	public static void main(String[] args) {
		new Runner().start();
	}

	public void start() {
		makeFrame();
		repaint.start();
	}
	
	public void draw(Graphics g) {
		
	}
	
	public void makeFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel  = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				draw(g);
			}
		};
		panel.addMouseListener(new MouseAdapter() {
			// Thats STILL future me's problem ;)
		});
		frame.add(panel);
		panel.setPreferredSize(new Dimension(WINX, WINY));
		panel.addKeyListener(key);
		panel.setFocusable(true);
		panel.setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.pack();
		frame.setVisible(true);
		
		try {
			InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("zephyrea.ttf");
			main = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);
			stream = ClassLoader.getSystemClassLoader().getResourceAsStream("zephyrea.ttf");
			second = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(95f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public Clip findSound() {
		for (int x = 0; x < sounds.size(); x++) {
			if (!sounds.get(x).isActive()) {
				return sounds.get(x);
			}
		}
		
		try {
			sounds.add(AudioSystem.getClip());
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return sounds.get(sounds.size() - 1);
	}
	
	public void playSound(String s, Clip c) {
		if (c != null) {
			try {
				c.stop();
				c.close();
				AudioInputStream a = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(s));
				c.open(a);
				c.start();
			}
			catch (Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}
		}
	}
}
