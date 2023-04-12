import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DailyTaskController {

    @FXML
    private Button startbtn;
    
    @FXML
    private Button startbtn1;

    @FXML
    private Button startbtn2;

    @FXML
    private Button quitbtn;
    
    @FXML
    void btnstartclicked(ActionEvent event) {
        // Load the Snake class
        try {
            Snake snake = new Snake();
            snake.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    @FXML
    public void btnstart1clicked() {
        loadNewScene("ScreenSaver.fxml");
    }
    @FXML
    public void btnstart2clicked() {
        loadNewScene("Taskscene.fxml");
    }

    @FXML
    public void btnquitclicked() {
        Stage stage = (Stage) quitbtn.getScene().getWindow();
        stage.close();
    }

    private void loadNewScene(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) startbtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
