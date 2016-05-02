package videoplayer;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author mdfacihulazam
 */
public class VideoPlayer extends Application {

    Player player;
    FileChooser filechooser;

    @Override
    public void start(final Stage primaryStage) {

        String filename = "file:///Volumes/study/TUT/sig_innovation/video/tuesday/kampusareena.MP4";
        MenuItem open = new MenuItem("open");
        Menu file = new Menu("file");
        MenuBar menu = new MenuBar();

        file.getItems().add(open);
        menu.getMenus().add(file);

        filechooser = new FileChooser();

        open.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                player.player.pause();

                File file = filechooser.showOpenDialog(primaryStage);
                if (file != null) {
                    Scene scene = new Scene(player, 720, 535, Color.BLACK);
                    primaryStage.setScene(scene);
                 

                }
            }
        });

        player = new Player(filename);
        player.setTop(menu);

        Scene scene = new Scene(player, 720, 535, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
