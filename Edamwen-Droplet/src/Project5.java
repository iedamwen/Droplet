import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.extra.entity.components.ExpireCleanComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.settings.GameSettings;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.geom.Point2D;
import java.util.Map;

import static com.almasb.fxgl.app.DSLKt.*;

/**
 *
 * Project5: Droplet
 *
 * Welcome to my first video game!
 *
 * The basis of this game is dodging all of the droplets with your fire elemental player
 *
 *
 * @author Emmanuel Edamwen
 * @version 1.0
 * @since 11/29/18
 *
 *
 * Current Bugs: Working on multiple player's spawning on screen to add difficulty, might be disabled for now
 *
 * Future Updates:
 * Droplet 2.0 - Testing Menu/PowerUps/Game difficulty option/High Score list/Multi-player (2 Player)/Sound
 *
 */




public class Project5 extends GameApplication{

    //Counts how many droplets are in the current game so they can all respawn
    private int dropletCount;


    /**
     *
     * @param settings We set all the basic info of our game here
     */
    @Override
    protected void initSettings(GameSettings settings)

    {
        settings.setTitle("Droplet");
        settings.setManualResizeEnabled(false);
        settings.setVersion("1.0");
        settings.setMenuEnabled(true);

        //settings.setSceneFactory(new MenuFactory());

    }


    /**
     *
     * @initInput - This is where we handle all the keyboard input from player
     */
    protected void initInput(){
        onKey(KeyCode.A, "Left", () -> {
            move(Direction.LEFT);
        });
        onKey(KeyCode.D, "Right", () -> {
            move(Direction.RIGHT);
        });
        onKey(KeyCode.W, "Up", () -> {
            move(Direction.UP);
        });
        onKey(KeyCode.S, "Down", () -> {
            move(Direction.DOWN);
        });

        //Alternative keys or input for 2 players

        /*
        onKey(KeyCode.LEFT, "Left2", () -> {
            move(Direction.LEFT);
        });
        onKey(KeyCode.RIGHT, "Right2", () -> {
            move(Direction.RIGHT);
        });
        onKey(KeyCode.UP, "Up2", () -> {
            move(Direction.UP);
        });
        onKey(KeyCode.DOWN, "Down2", () -> {
            move(Direction.DOWN);
        });
         */

    }

    /**
     *
     * @param direction We call the direction class to make a new direction then
     *                  call the fire component class to connect player to direction
     */

    private void move(Direction direction){
        for (Entity fire: getGameWorld().getEntitiesByType(EntityType.FIRE)){
            FireComponent comp = fire.getComponent(FireComponent.class);

            comp.move(direction);

            direction = direction.next();
        }
    }

    @Override
    protected void initGame(){


        //Adds listener to an integer, checking the score
        //Every few thousand scores it will spawn another droplet
        getGameState().<Integer>addListener("score", (prev, now) -> {
            if (now == 1000) {
                spawn("droplet", getWidth() / 2, 60);
               // inc("score", 1.2);
                dropletCount++;
                }
                else if(now == 2000){
                spawn("droplet", getWidth() / 2, 60);
               // inc("score", 1.4);
                dropletCount++;
            }
            else if(now == 3000){
                spawn("droplet", getWidth() / 2, 70);
                //inc("score", 1.6);
                dropletCount++;
            }
            else if(now == 4000){
               spawn("droplet", getWidth() / 2, 80);
                //inc("score", 1.8);
               dropletCount++;
            }
            else if(now == 7000){
                spawn("droplet", getWidth() / 2, 80);
               // inc("score", 2.5);
                dropletCount++;

                for(int i = 0; i < 1; i++){
                    double x = random(i*150, i*150 + 150);
                    double y = random(400, 600-60);

                    spawn("player", x, y);
                }

            }

            });

        //Listener to check lives, if 0 game ends and prompt for quit or play again
        //Not working right now, planning on adding severe punishment for losing lives, adding another player to game

        getGameState().<Integer>addListener("lives", (prev, now) -> {
                    if (now == 0) {
                        getDisplay().showConfirmationBox("Game Over, Continue?", yes -> {
                            if (yes) {
                                dropletCount = 0;
                                inc("score", 1);
                                startNewGame();
                            } else {
                                exit();
                            }
                        });
                    }
                });



        //Sets background

        getGameScene().setBackgroundRepeat("GameBG.png");

        //Have to call the factory to access entities
        getGameWorld().addEntityFactory(new P5Factory());

        //Adds screen bounds entity
        getGameWorld().addEntity(Entities.makeScreenBounds(40));

        //Calls respawnEntities method
        respawnEntities();
    }

    /**
     *
     * Respawn the entities, adds bounds to the game world and globally adds entities to them.
     * We start with 1 droplet each game and in version 1 of this game we only have 1 starting player
     */
    private void respawnEntities(){

        Entity bounds = Entities.makeScreenBounds(40);
        bounds.setType(EntityType.SCREEN);
        bounds.addComponent(new CollidableComponent(true));


        getGameWorld().addEntity(bounds);


        spawn("droplet", getWidth() / 2, 30);


        for(int i = 0; i < 1; i++){
            double x = random(i*150, i*150 + 150);
            double y = random(400, 600-60);

            spawn("player", x, y);
        }

    }

    /**
     *
     * @param vars - The game's variables, for now its lives & score
     */
    protected void initGameVars(Map<String, Object> vars){
        vars.put("lives", + 10);
        vars.put("score", + 0);
    }

    /**
     * In this method we handle all of the game's physics and collision detection
     */
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.FIRE, EntityType.DROPLET) {
            @Override
            protected void onCollisionBegin(Entity player, Entity droplet) {
                inc("lives", -1);

                getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);

                respawnEntities();

                for (int i = 0; i < dropletCount; i++) {
                    spawn("droplet", getWidth() / 2, 60 + i);
                }


            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.DROPLET, EntityType.SCREEN) {
            @Override
            protected void onCollisionBegin(Entity droplet, Entity screen) {
                ParticleEmitter emitter = ParticleEmitters.newSparkEmitter();

                Entity e = new Entity();
                e.setPosition(droplet.getCenter());
                e.addComponent(new ParticleComponent(emitter));
                e.addComponent(new ExpireCleanComponent(Duration.seconds(2)));

                getGameWorld().addEntity(e);
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.FIRE, EntityType.POWER) {
            @Override
            protected void onCollisionBegin(Entity fire, Entity powerup) {

                getGameWorld().removeEntities(powerup);
            }
        });
    }


    /**
     *
     * @initUI - Shows all of the text, numbers on screen by adding to game world. Stylizes them.
     *
     */

    protected void initUI(){
        Text textLives = addVarText(80, 20,"lives");
        textLives.setFont(getUIFactory().newFont(26));

        textLives.setFill(Color.BLACK);

        Text scoreText = new Text("Score: ");
        Text livesText = new Text("Lives: ");

        Text textScore = addVarText(720, 20, "score");
        textScore.setFont(getUIFactory().newFont(26));

        textScore.setFill(Color.BLACK);

        getGameScene().addUINode(scoreText);
        getGameScene().addUINode(livesText);
        scoreText.setLayoutX(640);
        scoreText.setLayoutY(20);

        scoreText.setFont(getUIFactory().newFont(26));
        livesText.setFont(getUIFactory().newFont(26));

        livesText.setLayoutY(20);
        livesText.setLayoutX(0);

    }


    /**
     *
     * Score is constantly increasing by 1
     */
    protected void onUpdate(double tpf){
        inc("score", +1);
    }

    }
