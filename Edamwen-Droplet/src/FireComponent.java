import com.almasb.fxgl.entity.component.Component;

/**
 *
 * FireComponent - Helper class for the fire entity/object, fire is our player
 *
 * @author Emmanuel Edamwen
 * @version 1.0
 * @since 11/29/18
 *
 *
 */

public class FireComponent extends Component {

    //We create a move method that uses the direction class and this decides how fast the player moves

    public void move(Direction direction){
        entity.translate(direction.vector.multiply(5));
    }
}
