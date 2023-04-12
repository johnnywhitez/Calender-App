import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class ScreensaverController {
    @FXML
    private Button btnimage;
    @FXML
    private Button btnChange;
    @FXML
    private Button quitbtn;
    @FXML
    private ImageView image;

    private Timer timer;

    public void initialize() {
        // Load an image file and set it to ImageView "image"
        Image img = new Image("image1.jpg");
        image.setImage(img);
    }

    public void btnimageClicked() {
        // Start displaying new images every 2 seconds
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Load a new image file and set it to ImageView "image"
                Image img = new Image("image2.jpg");
                image.setImage(img);
            }
        }, 2000, 2000);


                // Start displaying new images every 2 seconds
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // Load a new image file and set it to ImageView "image"
                        Image img = new Image("image.jpg");
                        image.setImage(img);
                    }
                }, 2200, 2200);
    }

    public void btnquitclicked() {
        // Stop displaying new images and exit the scene
        timer.cancel();
        Stage stage = (Stage) quitbtn.getScene().getWindow();
        stage.close();
    }
}

