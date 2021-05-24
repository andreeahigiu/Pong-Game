package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WelcomeBoard extends Application {

 private static Stage window;
 Scene initialOptions, board;
 public static boolean registering;
//
//    public static Stage getStage() {
//        return window;
//    }

 public static void main (String[] args){
     launch(args);
 }

 @Override
    public void start(Stage mainStage) throws FileNotFoundException {
     window = mainStage;

     //Options menu
     Label greeting = new Label("Welcome to PONG Game!");
     Font font = Font.font ("Courier New", FontWeight.BOLD, 25);
     greeting.setFont(font);

//     Label options = new Label("Options:");
//     options.setFont(new Font("Arial", 20));

     //login button
     FileInputStream inputLogin = new FileInputStream("D:/CursuriFacultateAn2Sem2/PA/finalProjectPA/assets/user.png");
     Image image = new Image(inputLogin);
     ImageView imageView = new ImageView(image);
     imageView.setFitHeight(30);
     imageView.setFitWidth(25);
     Button login = new Button("Login", imageView);
     login.setMaxSize(100, 150);
     login.setOnAction( e -> {
      registering = false;
      try {
       RegisterBoard.displayRegister();
      } catch (FileNotFoundException fileNotFoundException) {
       fileNotFoundException.printStackTrace();
      }
     });


  //register button
  FileInputStream inputRegister = new FileInputStream("D:/CursuriFacultateAn2Sem2/PA/finalProjectPA/assets/edit.png");
  Image image1 = new Image(inputRegister);
  ImageView imageView1 = new ImageView(image1);
  imageView1.setFitHeight(30);
  imageView1.setFitWidth(29);
  Button register = new Button("Register", imageView1);
  register.setMaxSize(100, 150);
  register.setOnAction( e -> {
   registering=true;
   try {
    RegisterBoard.displayRegister();
   } catch (FileNotFoundException fileNotFoundException) {
    fileNotFoundException.printStackTrace();
   }
  });


     //play button
     FileInputStream inputPlay = new FileInputStream("D:/CursuriFacultateAn2Sem2/PA/finalProjectPA/assets/play.png");
     Image image2 = new Image(inputPlay);
     ImageView imageView2 = new ImageView(image2);
     imageView2.setFitHeight(30);
     imageView2.setFitWidth(25);
     Button play = new Button("Play", imageView2);
     play.setMaxSize(100, 150);
     play.setOnAction(e ->
     {
      try {
       GameBoard.display();

      } catch (FileNotFoundException fileNotFoundException) {
       fileNotFoundException.printStackTrace();
      }
      //this.window.setScene(GameBoard.getExportScene());
     });


     //settings button
     FileInputStream inputSettings = new FileInputStream("D:/CursuriFacultateAn2Sem2/PA/finalProjectPA/assets/settings.png");
     Image image3 = new Image(inputSettings);
     ImageView imageView3 = new ImageView(image3);
     imageView3.setFitHeight(30);
     imageView3.setFitWidth(28);
     Button gameSettings = new Button("Settings", imageView3);
     gameSettings.setMaxSize(100, 150);

     //ranking button
     FileInputStream inputRank = new FileInputStream("D:/CursuriFacultateAn2Sem2/PA/finalProjectPA/assets/ranking.png");
     Image image4 = new Image(inputRank);
     ImageView imageView4 = new ImageView(image4);
     imageView4.setFitHeight(30);
     imageView4.setFitWidth(25);
     Button ranking = new Button ("Ranking", imageView4);
     ranking.setMaxSize(100, 150);



     //Layout1
     VBox menuLayout = new VBox(20);
     menuLayout.getChildren().addAll(greeting, login, register, play, gameSettings, ranking);
     initialOptions = new Scene(menuLayout, 800, 600);
     menuLayout.setAlignment(Pos.CENTER);


     //First display the options

     window.setScene(initialOptions);
     window.setTitle("PONG");
     window.show();
 }

}
