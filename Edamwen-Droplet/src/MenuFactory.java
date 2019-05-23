import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.scene.FXGLMenu;
import com.almasb.fxgl.scene.SceneFactory;
import com.almasb.fxgl.scene.menu.FXGLDefaultMenu;
import com.almasb.fxgl.scene.menu.MenuType;
import com.almasb.fxgl.settings.GameSettings;
import org.jetbrains.annotations.NotNull;
import javafx.scene.Node;
import javafx.scene.text.Text;



public class MenuFactory extends SceneFactory {

    @NotNull
    @Override
    public FXGLMenu newMainMenu(@NotNull GameApplication app){
        return new FXGLDefaultMenu(app, MenuType.MAIN_MENU){

            @Override
            protected Node createBackground(double width, double height){
                return FXGL.getAssetLoader().loadTexture("GameBG.png", 800, 600);
            }

            @Override
            protected Node createTitleView(String title){
                return new Text("");
            }


        };

    }

    @NotNull
    @Override
    public FXGLMenu newGameMenu(@NotNull GameApplication app){
        return new FXGLDefaultMenu(app, MenuType.GAME_MENU){
            @Override
            protected Node createBackground(double width, double height){
                return FXGL.getAssetLoader().loadTexture("GameBG.png", 800, 600);
            }

            @Override
            protected Node createTitleView(String title){
                return new Text("");
            }


        };

    }





}
