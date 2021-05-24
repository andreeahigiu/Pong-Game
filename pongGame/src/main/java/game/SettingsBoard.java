package game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SettingsBoard {
    private static Stage window;
    private static Scene settingsScene;
    static Button setButton;
    private static String colorScheme;

    public static String getColorScheme() {
        return colorScheme;
    }

    public static void displaySettings() throws FileNotFoundException {

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Settings");

        final ToggleGroup selectGroup = new ToggleGroup();

        Label option1 = new Label("Choose the color scheme: ");

        RadioButton box1 = new RadioButton("Black");
        box1.setToggleGroup(selectGroup);
        RadioButton box2 = new RadioButton("Orange");
        box2.setToggleGroup(selectGroup);
        RadioButton box3 = new RadioButton("Pink");
        box3.setToggleGroup(selectGroup);

        //The Set button
        setButton = new Button("Set");
        setButton.setOnAction ( e-> setOptions(box1, box2, box3));
        setButton.setMaxSize(100,200);

        //Back button
        FileInputStream inputBack = new FileInputStream("D:/CursuriFacultateAn2Sem2/PA/finalProjectPA/assets/arrow.png");
        javafx.scene.image.Image image = new Image(inputBack);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        Button exit = new Button(null, imageView);
        exit.setOnAction(e -> {
            window.close();
        });

        //back button layout
        StackPane layoutBack = new StackPane();
        layoutBack.getChildren().add(exit);
        layoutBack.setAlignment (Pos.TOP_LEFT);

        //options layout
        VBox options = new VBox(20);
        options.getChildren().addAll(option1, box1, box2, box3, setButton);
        options.setAlignment(Pos.CENTER);

        BorderPane border = new BorderPane();

        border.setTop(layoutBack);
        border.setCenter(options);

        settingsScene = new Scene(border,800, 600);
        window.setScene(settingsScene);
        window.show();
    }

    private static void setOptions(RadioButton box1, RadioButton box2, RadioButton box3) {
        if(box1.isSelected()){
            colorScheme = "black";
        }
        if(box2.isSelected()){
            colorScheme = "orange";
        }
        if(box3.isSelected()){
            colorScheme = "pink";
        }
        System.out.println("color selected: "+ colorScheme);
    }




}
