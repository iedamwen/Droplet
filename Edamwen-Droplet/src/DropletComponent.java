
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.extra.entity.components.ExpireCleanComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

/**
 *
 * DropletComponent - This class is for the droplet/ball movement, we use physics to decide when and how it randomly moves
 *
 * @author Emmanuel Edamwen
 * @version 1.0
 * @since 11/29/18
 *
 *
 */

public class DropletComponent extends Component {

    //Timer to add delay for when it changes direction, it might change randomly to catch player's off guard
    private LocalTimer timer = FXGL.newLocalTimer();

    private PhysicsComponent physics;

    @Override
    public void onUpdate(double tpf){

        if(timer.elapsed(Duration.seconds(2))){
            physics.setLinearVelocity(FXGLMath.randomPoint2D().multiply(350));

            timer.capture();
        }
    }
}
