package game;

import org.jbox2d.common.Vec2;

import city.soi.platform.*;

/**
 * Pick-ups in a game. When the player collides with an orange, the
 * player's oranges count is increased and the orange is removed
 * from the world. 
 */
public class Orange extends Body {

    /** The game in which the player is playing. */
    private Game game;

    /**
     * Initialise a new orange.
     * @param g The game.
     */
    public Orange(Game game) {
        super(game.getWorld(), new CircleShape(15));
        this.game = game;
        setFillColor(java.awt.Color.ORANGE);
        setLineColor(java.awt.Color.BLACK);
    }
}
