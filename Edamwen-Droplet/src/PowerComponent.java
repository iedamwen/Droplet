import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

import java.awt.*;

/**
 *
 * PowerComponent - Creates a helper class for dealing with power ups
 *
 * Droplet 1.0 - Unused
 *
 *
 * @author Emmanuel Edamwen
 * @version 1.0
 * @since 11/29/18
 *
 *
 */

public class PowerComponent extends Component {
    private LocalTimer timer = FXGL.newLocalTimer();


    public void onUpdate(double tpf){
        if(timer.elapsed(Duration.seconds(30))){
           FXGL.getGameWorld().spawn("power", 300, 300);
        }
    }
}
