package game;

import city.soi.platform.*;
import city.soi.platform.Body.RotationType;
import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import org.jbox2d.common.Vec2;

/**
 * A very basic platform game.
 */
public class Game {

    /** The player (a specialised Actor). */
    private Player player;
    /** Game over flag. */
    private boolean isOver;
    /** The World in which the game bodies move and interact.*/
    private World world;
    /** A graphical display of the world (a specialised JPanel). */
    private WorldView view;
    /** A debug display. */
    private DebugViewer debugViewer;

    /** Initialise a new Game. */
    public Game() {
        isOver = false;

        // make the world
        world = new World();
        Body backgroundImage = new Body(world);
        backgroundImage.setImage(new BodyImage("images/Bored.png"));
        // make a player
        player = new Player(this);

        // make the ground
        Body ground = new Body(world, PolygonShape.makeBox(200, 15), Body.Type.STATIC);
        ground.setPosition(new Vec2(0, -230));

        // put the player on the ground
        player.putOn(ground);
        player.move(new Vec2(-90, 0));
       

        // make some platforms
        Body staticPlatform = new Body(world, PolygonShape.makeBox(50, 5), Body.Type.STATIC);
        staticPlatform.setPosition(new Vec2(0, 160));
        Body movingPlatform = new SlidingPlatform(world, PolygonShape.makeBox(50, 10), new Vec2(0, -140), 2.0f);
        movingPlatform.setPosition(new Vec2(-180, 140));
        Body movingPlatform2 = new SlidingPlatform(world, PolygonShape.makeBox(50, 10), new Vec2(0, 140), 2.0f);
        movingPlatform2.setPosition(new Vec2(180, 0));
       



        // make some slabs
        Body slab1 = new Body(world, PolygonShape.makeBox(10, 50));
        Body slab2 = new Body(world, PolygonShape.makeBox(10, 50));
        Body slab3 = new Body(world, PolygonShape.makeBox(50, 5));
        // arrange slabs into an arch
        slab1.putOn(ground);
        slab1.move(new Vec2(-40, 0));
        slab2.putOn(ground);
        slab2.move(new Vec2(40, 0));
        slab3.putOn(slab1);
        slab3.move(new Vec2(40, 0));

        // make some oranges
        Orange orange1 = new Orange(this);
        orange1.putOn(staticPlatform);
        orange1.move(new Vec2(-20, 20));
        Orange orange2 = new Orange(this);
        orange2.putOn(slab3);
        Orange orange3 = new Orange(this);
        orange3.putOn(ground);
        
        Diamonds diamond1 = new Diamonds(this);
        diamond1.putOn(movingPlatform2);
        diamond1.move(new Vec2(0, 140));

        // make a view
        view = new WorldView(world, 500, 500);
        //view.setDrawStats(true); // uncomment this line to show simulation stats in game display

        // display the view in a frame
        final JFrame frame = new JFrame("Game");

        // add some keyboard handling
        frame.addKeyListener(new java.awt.event.KeyAdapter() {

            /** Handle key press events for walking and jumping. */
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (isOver) {
                    return;
                }
                int code = e.getKeyCode();
                // SPACE = jump
                if (code == java.awt.event.KeyEvent.VK_SPACE) {
                    // only jump if player is not already jumping
                    if (!player.isJumping()) {
                        player.jump(270);
                    }
                    // LEFT ARROW = walk left
                } else if (code == java.awt.event.KeyEvent.VK_LEFT) {
                    player.walkLeft(90);
                    BodyImage left = new BodyImage("images/Supermanwalkleft.gif");
                    player.setImage(left);
                    // RIGHT ARROW = walk right
                } else if (code == java.awt.event.KeyEvent.VK_RIGHT) {
                    player.walkRight(90);
                    BodyImage right = new BodyImage("images/Supermanwalkright.gif");
                    player.setImage(right);
                    
                    // F1 key toggles display of debug view
                } else if (code == java.awt.event.KeyEvent.VK_F1) {
                    if (debugViewer == null) {
                        debugViewer = new DebugViewer(new DebugSettings(world));
                    }
                    if (debugViewer.isRunning()) {
                        debugViewer.stopViewer();
                    } else {
                        debugViewer.startViewer();
                    }
                }
            }

            /** Handle key release events (stop walking). */
            public void keyReleased(java.awt.event.KeyEvent e) {
                if (isOver) {
                    return;
                }
                int code = e.getKeyCode();
                if (code == java.awt.event.KeyEvent.VK_LEFT) {
                    player.stopWalking();
                    BodyImage left = new BodyImage("images/Supermanfaceleft.gif");
                    player.setImage(left);
                } else if (code == java.awt.event.KeyEvent.VK_RIGHT) {
                    player.stopWalking();
                    BodyImage right = new BodyImage("images/Supermanfaceright.gif");
                   player.setImage(right);
                }
            }
        });

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);

        // start!
        world.start();
    }

    /** Is the game over? */
    public boolean isOver() {
        return isOver;
    }

    /** End the game. */
    public void gameOver() {
        world.pause();
        isOver = true;
    }

    /** The world in which this game is played. */
    public World getWorld() {
        return world;
    }

    /** The world view. */
    public WorldView getView() {
        return view;
    }

    /** The player. */
    public Player getPlayer() {
        return player;
    }

    /** Play a game. */
    public static void main(String[] args) {
        new Game();
    }

    private RotationType setFillColor(Color BLACK) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
