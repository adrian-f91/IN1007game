package game;

import city.soi.platform.*;

/** Basic players in a game. */
public class Player extends Actor {

    /** The number of oranges the player currently has. */
    private int oranges;
    
    private int diamond;
    
    /** The game. */
    private Game game;

    /**
     * Initialise a new player.
     * @param game  The game in which the player will be playing.
     */
    public Player(Game game) {
        super(game.getWorld(), new PolygonShape(0.0f,32.5f, 26.0f,31.5f, 29.0f,-43.5f, -6.0f,-43.5f, 0.0f,32.5f));
        this.game = game;
        oranges = 0;
        setFillColor(java.awt.Color.MAGENTA);
        setLineColor(java.awt.Color.BLACK);
        setImage(new BodyImage("images/Supermanfaceright.gif"));
    }

    /** Increase the orange count. */
    public void incrementOrangeCount() {
        oranges++;
    }

    /** Decrease the orange count. */
    public void decrementOrangeCount() {
        oranges--;
    }

    /** The number of oranges the player currently has. */
    public int getOranges() {
        return oranges;
    }
    
    public int getDiamonds() {
        return diamond;
    }
    
    public void incrementDiamonds() {
        diamond++;
    }
    
    public void decrementDiamonds() {
        diamond--;
    }
    
}
