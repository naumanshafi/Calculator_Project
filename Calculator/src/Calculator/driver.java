package Calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class driver extends Application  {
    @Override
    public void start(Stage initialStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
        initialStage.setTitle("Calculator");
        initialStage.setScene(new Scene(root));
        initialStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
