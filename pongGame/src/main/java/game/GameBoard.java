package game;

import dataBase.entityClasses.Users;
import dataBase.repository.UsersRepository;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Class GameBoard:
 * Contine metode pentru desfasurarea jocului pong
 *
 *
 * @author Andreea Higiu
 * @version 1.0
 */

public class GameBoard {

    //variables
    public static Button button;
    private static Stage window;
    private static Scene exportScene;

    private static final int width = 800;
    private static final  int height = 600;
    private static final int palletHeight = 100;
    private static final int palletWidth = 15;
    private static final double ballR = 15;
    private static int ballSpeedY = 1;
    private static int ballSpeedX = 1;

    private static double posYPlayer1 = height/2;
    private static double posYPlayer2 = height/2;
    private static int posXPlayer1 = 0;
    private static double posXPlayer2 = width - palletWidth;


    private static double ballPosX = width/2;
    private static double ballPosY = width/2;
    private static int scorePlayer1 = 0;
    private static int scorePlayer2 = 0;
    private static boolean gameStarted;
    private static String colorScheme;
    private static Color background;
    private static Color gamePieces;



    public static Scene getExportScene() {
        return exportScene;
    }

    public static void setExportScene(Scene exportScene) {
        GameBoard.exportScene = exportScene;
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        GameBoard.window = window;
    }

        public static void display() throws FileNotFoundException {
            window = new Stage();
           window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("PONG");

            //a new canvas for drawing
            Canvas canvas = new Canvas(width, height);
            GraphicsContext graphics = canvas.getGraphicsContext2D();

            //timeline for animations
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> run(graphics)));
            timeline.setCycleCount(Timeline.INDEFINITE);

            //The mouse controls
            canvas.setOnMouseMoved(e -> posYPlayer1 = e.getY());
            canvas.setOnMouseClicked(e ->  gameStarted = true);

            BorderPane border = new BorderPane();

            //exit button
            FileInputStream inputBack = new FileInputStream("D:/CursuriFacultateAn2Sem2/PA/finalProjectPA/assets/arrow.png");
            Image image = new Image(inputBack);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);

            Button exit = new Button(null, imageView);
            exit.setOnAction(e -> {
                timeline.stop();
                setScore();
                gameStarted = false;
                scorePlayer2 = 0;
                scorePlayer1 = 0;
                window.close();
            });

            StackPane layout = new StackPane();
            layout.getChildren().add(exit);
            layout.setAlignment (Pos.TOP_LEFT);
            border.setTop(layout);
            border.setCenter(new StackPane(canvas));


           // window.setScene(new Scene(new StackPane(canvas)));
            window.setScene(new Scene(border));
            window.show();
            timeline.play();
        }

    private static void run(GraphicsContext gc) {

        colorScheme = SettingsBoard.getColorScheme();
        background = Color.BLACK;
        gamePieces = Color.WHITE;
        if(colorScheme == null)
        {
            background = Color.BLACK;
            gamePieces = Color.WHITE;
        }else
            if(colorScheme.equals("pink")){
                background = Color.rgb(204, 0 ,129);
                gamePieces = Color.rgb(255, 198, 234);
            }else
            if(colorScheme.equals("orange")) {
                background = Color.rgb(255, 141, 29);
                gamePieces = Color.WHITE;
            }
        //setting the background
        gc.setFill(background);
        gc.fillRect(0, 0, width, height);

        //setting the text color
        gc.setFill(gamePieces);
        gc.setFont(Font.font(25));

        if(gameStarted) {
            //setting the ball movement
            ballPosX = ballPosX  +ballSpeedX ;
            ballPosY = ballPosY  +ballSpeedY ;

            //creating the opponent
            if(ballPosX < width - width/4){
                posYPlayer2 = ballPosY - palletHeight/2;
            }else{
                if(ballPosY > posYPlayer2 + palletHeight/2 )
                {
                    posYPlayer2 += 1;
                }else {
                    posYPlayer2 -= 1;
                }
            }

            //drawing the ball
            gc. fillOval(ballPosX, ballPosY, ballR, ballR );
        }else {
            //start text
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click to Start", width / 2, height / 2);

            //reset the starting point of the ball
            ballPosX = width / 2;
            ballPosY = height / 2;

            //reset the speed and the direction of the ball
            if(new Random().nextInt(2) == 0)
                ballSpeedX = 1;
            else
                ballSpeedX = -1;

            if(new Random().nextInt(2) == 0)
                ballSpeedY = 1;
            else
                ballSpeedY = -1;

        }

        //bounding the ball with the canvas
        if(ballPosY > height || ballPosY < 0)
            ballSpeedY = ballSpeedY * -1 ;

        //point calculator
        if(ballPosX < posXPlayer1 - palletWidth) {
            scorePlayer2++;
            gameStarted = false;
        }

        if(ballPosX > posXPlayer2 + palletWidth) {
            scorePlayer1++;
            gameStarted = false;
        }

        //increasing the speed of the ball after it hits a wall or the pallets
        if( ((ballPosX + ballR > posXPlayer2) && ballPosY >= posYPlayer2 && ballPosY <= posYPlayer2 + palletHeight) ||
                ((ballPosX < posXPlayer1 + palletWidth) && ballPosY >= posYPlayer1 && ballPosY <= posYPlayer1 + palletHeight)) {
            ballSpeedY += 1 * Math.signum(ballSpeedY);
            ballSpeedX += 1 * Math.signum(ballSpeedX);
            ballSpeedX *= -1;
            ballSpeedY *= -1;
        }

        //drawing the score
        gc.fillText(scorePlayer1 + "\t\t\t\t\t\t\t" + scorePlayer2, width / 2, 100);

        //drawing the pallets of the players
        gc.fillRect(posXPlayer1, posYPlayer1, palletWidth, palletHeight);
        gc.fillRect(posXPlayer2, posYPlayer2, palletWidth, palletHeight);

    }

    public static void setScore(){
        if(RegisterBoard.getLoggedIn() == true)
        {
            String username = RegisterBoard.getUsername();
            UsersRepository.updateUserScore(username, scorePlayer1);

            System.out.println("scorul- Player1: " + scorePlayer1 + " Player2: " + scorePlayer2);

        }
    }

}

