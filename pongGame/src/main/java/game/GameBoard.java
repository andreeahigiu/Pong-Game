package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;

public class GameBoard extends Application {
 Stage window;
 Scene initialOptions, board;

 public static void main (String[] args){
     launch(args);
 }

 @Override
    public void start(Stage mainStage){
     window = mainStage;

     //Options menu
     Label greeting = new Label("Welcome to PONG Game!");
     Label options = new Label("Options:");
     Button login = new Button("Login");
     Button play = new Button("Play");
     play.setOnAction(e -> window.setScene(board));
    // login.setTextFill (Color.RED);

     Button gameSettings = new Button("Settings");
     Button ranking = new Button ("Ranking");

      Font font = Font.font ("Courier New", FontWeight.BOLD, 25);
       greeting.setFont(font);

     //Layout1
     VBox menuLayout = new VBox(20);
     menuLayout.getChildren().addAll(greeting, options, login, play, gameSettings, ranking);
     initialOptions = new Scene(menuLayout, 400, 400);
  initialOptions.setFill(Color.RED);

     menuLayout.setAlignment(Pos.BASELINE_CENTER);

     //Game Board
     Button back = new Button("back");
     back.setOnAction(e -> window.setScene(initialOptions));

     Button ball = new Button("ball");

     //Layout2

     VBox layout2 = new VBox();
     layout2.getChildren().addAll(back);
     board = new Scene(layout2, 400, 400);
     board.setFill(Color.RED);

     layout2.setAlignment(Pos.TOP_LEFT);

     //First display the options
     window.setScene(initialOptions);
     window.setTitle("PONG");
     window.show();
 }

}
