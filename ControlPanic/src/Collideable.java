
public class Collideable {
	int x = 0;
	int y = 0;
	int height = 0;
	int width = 0;

	public int collide(Collideable object) {
		// 1 = Object 1 collided on the right of Object 2
		// 2 = Object 1 collided on the left of Object 2
		// 3 = Object 1 collided on bottom of Object 2
		// 4 = Object 1 collided on top of Object 2
		if (x > object.x + object.width)
			return 1;
		if (x + width < object.x)
			return 2;
		if (y > object.y + object.height)
			return 3;
		if (y + height < object.y) {
			return 4;
		}
		return 0;
	}
}
