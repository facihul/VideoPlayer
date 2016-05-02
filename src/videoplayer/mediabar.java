package videoplayer;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 *
 * @author mdfacihulazam
 */
public class mediabar extends HBox {

    Slider time = new Slider();
    Slider vol = new Slider();
    
    Button playbtn = new Button("||");
    Label volume = new Label("Volume: ");
    MediaPlayer player;
    
    public mediabar(MediaPlayer play) {
        
        player = play;
        
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));
        
        vol.prefWidth(70);
        vol.minWidth(30);
        vol.setValue(100);
        HBox.setHgrow(time, Priority.ALWAYS);
        
        playbtn.setPrefWidth(30);
        getChildren().add(playbtn);
        getChildren().add(time);
        getChildren().add(volume);
        getChildren().add(vol);
        
        playbtn.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent e) {
                Status status = player.getStatus();
                
                if (status == Status.PLAYING) {
                    if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {
                        player.seek(player.getStartTime());
                        player.play();
                    } else {
                        player.pause();
                        playbtn.setText(">");
                        
                    }
                }
                if (status == Status.PAUSED || status == Status.HALTED || status == Status.STOPPED) {
                    
                    player.play();
                    playbtn.setText("||");
                }
                
            }
        });
        
        player.currentTimeProperty().addListener(new InvalidationListener() {
            
          
            public void invalidated(Observable observable) {
                updateValues();
            }
            
        });
        time.valueProperty().addListener(new InvalidationListener() {
            
            public void invalidated(Observable observable) {
                if (time.isPressed()) {
                    player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
                }
            }
            
        });
        
        vol.valueProperty().addListener(new InvalidationListener(){
          
            public void invalidated(Observable observable) {
                if (vol.isPressed()) {
                    player.setVolume(vol.getValue()/100);
                }
                
            }
        
        
        });
        
        
    }
    
    protected void updateValues() {
        Platform.runLater(new Runnable() {
            public void run() {
                time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
            }
        });
        
    }
    
}
