package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RegisterBoard {

    private static Stage window;
    private static Scene registerScene;
    private static String username;
    private static String password;

    public static void displayRegister() throws FileNotFoundException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        //exit button
        FileInputStream inputBack = new FileInputStream("D:/CursuriFacultateAn2Sem2/PA/finalProjectPA/assets/arrow.png");
        Image image = new Image(inputBack);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        Button exit = new Button("Back", imageView);
        exit.setOnAction(e -> {
            window.close();
        });

        HBox layout = new HBox();
        layout.getChildren().add(exit);
        layout.setAlignment (Pos.TOP_LEFT);


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);

        Label confirmation = new Label("");

        Label usernameLabel = new Label("Username:");
        TextField userInput = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passInput = new PasswordField();

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            username = userInput.getText();
            password = passInput.getText();
            System.out.println("username: " + username + " pass: " + password);
            confirmation.setText("User created!");
        });

        ColumnConstraints column1 = new ColumnConstraints();column1.setPercentWidth(35);
        grid.getColumnConstraints().addAll(column1);

        grid.add(exit, 0, 0);
        grid.add(confirmation, 7, 0);
        grid.add(usernameLabel, 7, 7);
        grid.add(userInput, 8, 7);
        grid.add(passwordLabel, 7, 8);
        grid.add(passInput, 8, 8);
        grid.add(submitBtn, 7, 9, 9, 8);



        registerScene = new Scene( grid,800, 600);
        window.setScene(registerScene);
        window.show();
    }
}
