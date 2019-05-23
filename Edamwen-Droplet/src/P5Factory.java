import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.extra.entity.components.KeepOnScreenComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.texture.Texture;

import javafx.util.Duration;

import static com.almasb.fxgl.app.DSLKt.*;

/**
 *
 * P5Factory - Generates and creates all the data for entities, makes them able to be called/spawned in game
 *
 *
 * @author Emmanuel Edamwen
 * @version 1.0
 * @since 11/29/18
 *
 *
 */

public class P5Factory implements EntityFactory {

    @Spawns("droplet")
    public Entity newBall(SpawnData data)
    {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1.0f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(150, 150));

        return Entities.builder()
                .type(EntityType.DROPLET)
                .from(data)
                .viewFromNodeWithBBox(texture("droplet.png", 40, 40))
                .with(physics)
                .with(new DropletComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data)
    {
        Texture t = texture("fireanimation.png").multiplyColor(FXGLMath.randomColor());

        t.setFitWidth(50);
        t.setFitHeight(50);

        Entity player = Entities.builder()
                .type(EntityType.FIRE)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(70, 60)))
                .viewFromAnimatedTexture("fireanimation.png", 2, Duration.seconds(0.5))
                .with(new FireComponent())
                .with(new KeepOnScreenComponent(true, true))
                .with(new CollidableComponent(true))
                .build();
        player.getViewComponent().setAnimatedTexture(t.toAnimatedTexture(2, Duration.seconds(0.5)), true, false);

        return player;
    }

    @Spawns("power")
    public Entity newPowerUp(SpawnData data){

        return Entities.builder()
                .type(EntityType.POWER)
                .from(data)
                .viewFromNodeWithBBox(texture("powerup.png", 40, 40))
                .with(new DropletComponent())
                .with(new CollidableComponent(true))
                .build();

    }

}
