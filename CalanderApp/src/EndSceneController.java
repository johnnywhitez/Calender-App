import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EndSceneController {
    
    @FXML
    private Button quitbtn;

    
    @FXML
    private void btnquitclicked(ActionEvent event) {
        // Exit the scene
        quitbtn.getScene().getWindow().hide();
    }
}
