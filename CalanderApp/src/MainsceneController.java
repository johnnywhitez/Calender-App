import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainsceneController {

    @FXML
    private TextField textField;

    @FXML
    private Button btn1;

    @FXML
    private Button Todobtn;

    @FXML
    private Button dailybtn;

    @FXML
    private Button quitbtn;

    @FXML
    public void btndailyclicked() {
        loadNewScene("DailyEventScene.fxml");
    }

    @FXML
    public void btntodoclicked() {
        loadNewScene("TodoScene.fxml");
    }

    @FXML
    public void btnquitclicked() {
        Stage stage = (Stage) quitbtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleBtn1() {
        String text = textField.getText();
        System.out.println("Text entered: " + text);
    }

    private void loadNewScene(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) dailybtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
