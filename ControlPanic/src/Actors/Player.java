package Actors;

public class Player implements Moveable{
    private float x;
    private float y;
    private float dx;//derivation of x, aka speed
    private float dy;
    @Override
    public void move(float deltaTime) {
        x+=dx*deltaTime;
        y+=dy*deltaTime;
    }

}
