
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.lang.String;
import javafx.scene.layout.VBox;



public class ScoreBoard extends Application {

    //Creating window, scenes, table
    Stage window;
    Scene scene;
    TableView<Scores> table;

    public void start(Stage primaryStage) {
        //Window and title
        primaryStage.setTitle("Scoreboard");
        window = primaryStage;

        //Creating tables columns

        TableColumn nameCol = new TableColumn<>("Name");
        nameCol.setMaxWidth(110);
        nameCol.setCellValueFactory(new PropertyValueFactory<Scores, String>("name"));

        TableColumn scoreCol = new TableColumn<>("Score");
        scoreCol.setMaxWidth(110);
        scoreCol.setCellValueFactory(new PropertyValueFactory<Scores, Integer>("score"));

        //Creating table, storing inside VBox
        table = new TableView<>();
        table.getColumns().addAll(nameCol, scoreCol);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        scene = new Scene(vBox, 160, 400);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

}