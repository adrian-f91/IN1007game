package game;

import org.jbox2d.common.Vec2;

import city.soi.platform.*;

/**
 * Pick-ups in a game. When the player collides with an orange, the
 * player's orange count is increased and the orange is removed
 * from the world. 
 */
public class Diamonds extends Body {

    /** The game in which the player is playing. */
    private Game game;

    /**
     * Initialise a new orange.
     * @param g The game.
     */
    public Diamonds(Game game) {
        super(game.getWorld(), new PolygonShape(-7.0f,5.5f, -1.0f,5.5f, 2.0f,2.5f, -4.0f,-0.5f, -10.0f,2.5f, -7.0f,5.5f));
        this.game = game;
        setFillColor(java.awt.Color.WHITE);
        setLineColor(java.awt.Color.YELLOW);
        setImage(new BodyImage("images/diamond.png", new Vec2(10,10), 0.3f));
    }
}
 

