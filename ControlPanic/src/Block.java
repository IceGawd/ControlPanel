import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Block extends Collideable{
	Image img;
	
	public Block(int x1, int y1, String s) {
		x = x1;
		y = y1;
		try {
			img = ImageIO.read(this.getClass().getClassLoader().getResource(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = img.getWidth(null);
		height = img.getHeight(null);
	}
	
	public void draw(Graphics g, int playerx, int playery) {
		g.drawImage(img, x - playerx, y - playery, null);
	}
}
