import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TasksController implements Initializable {
    @FXML
    private Button quitbtn;
    @FXML
    private Button btnwk;
    @FXML
    private Button btndn;

    @FXML
    private TextField workme;
    
    private final String[] WORDS = {"Sit UP", "Running", "Strength Training", "Yoga", "Swimming", "Cycling"};
    
    private Timeline timer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initialize timer
        timer = new Timeline(new KeyFrame(Duration.minutes(30), this::quitScene));
        timer.setCycleCount(1);
    }


    @FXML
    private void btndoneclicked(ActionEvent event) throws IOException {
        // load the new FXML scene file
        Parent root = FXMLLoader.load(getClass().getResource("ENDEDSCENE.fxml"));
        
        // create a new scene with the loaded FXML file
        Scene newScene = new Scene(root);
        
        // get the stage from the button event
        Stage stage = (Stage) btndn.getScene().getWindow();
        
        // set the new scene on the stage
        stage.setScene(newScene);
    }
    @FXML
    private void startworkout(ActionEvent event) {
        // start displaying random words and append to workme TextField
        timer.play(); // start timer
        new Thread(() -> {
            while (true) {
                String word = WORDS[(int) (Math.random() * WORDS.length)];
                appendText(word);
                try {
                    Thread.sleep(10000); // wait for 1 second
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }).start();
    }
    
    private void appendText(String word) {
        // append word to workme TextField
        String text = workme.getText();
        if (text.isEmpty()) {
            workme.setText(word);
        } else {
            workme.setText(text + " " + word);
        }
    }
    
    private void quitScene(ActionEvent event) {
        // quit scene and load new fxml scene
        // TODO: implement scene switching logic here
    }
    @FXML
    private void btnquitclicked(ActionEvent event) {
        // Exit the scene
        quitbtn.getScene().getWindow().hide();
    }
    
}