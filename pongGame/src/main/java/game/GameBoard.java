package game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameBoard {

        public static Button button;
        private static Stage window;
        private static Scene exportScene;

    public static Scene getExportScene() {
        return exportScene;
    }

    public static void setExportScene(Scene exportScene) {
        GameBoard.exportScene = exportScene;
    }

//    public static void main(String[] args) {
//            launch(args);
//        }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        GameBoard.window = window;
    }

        public static void display() throws FileNotFoundException {
            window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("It works");

            //exit button
            FileInputStream inputBack = new FileInputStream("D:/CursuriFacultateAn2Sem2/PA/finalProjectPA/assets/arrow.png");
            Image image = new Image(inputBack);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            Button ranking = new Button ("Ranking", imageView);
            ranking.setMaxSize(100, 150);

            Button exit = new Button(null, imageView);
            exit.setOnAction(e -> {
                window.close();
            });

            StackPane layout = new StackPane();
            layout.getChildren().add(exit);
            layout.setAlignment (Pos.TOP_LEFT);

            //PONG Game



            Scene scene = new Scene(layout, 700, 600);
            exportScene = scene;
            scene.setFill(Color.RED);
            window.setScene(scene);
            window.showAndWait();
        }

    }

