package game;

import dataBase.entityClasses.Users;
import dataBase.entityManager.EntityManagement;
import dataBase.repository.UsersRepository;
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

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class RegisterBoard:
 * Contine metodele pentru inregistrarea si logarea userilor. Comunica cu baza de date, adauga useri noi sau
 * verifica existenta acestora si seteaza statusul utilizatorilor logat/nelogat
 *
 * @author Andreea Higiu
 * @version 1.0
 */

public class RegisterBoard {

    private static Stage window;
    private static Scene registerScene;
    private static String username;
    private static String password;
    private static boolean loggedIn = false;
    private static boolean userExists;

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static boolean getLoggedIn() {
        return loggedIn;
    }

    public static void displayRegister() throws FileNotFoundException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Register");

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
        confirmation.setWrapText(true);

        Label usernameLabel = new Label("Username:");
        TextField userInput = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passInput = new PasswordField();

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            username = userInput.getText();
            password = passInput.getText();
            boolean ok = isAllowed(username, password);

            System.out.println("username: " + username + " pass: " + password);
            //registering
            if(WelcomeBoard.registering) {
                if (ok) {
                    if (!checkExistance(username)) {
                        createUser(username, password);
                        confirmation.setText("User created!");
                    } else {
                        confirmation.setText("User already exists!");
                    }
                } else {
                    confirmation.setText("Password must contain at least 8 characters and the username 2");
                }
            }else{
                //login
                if(checkExistance(username)){
                    loginUser(username, password);
                    System.out.println("loggedIn: "+ loggedIn);
                    if(loggedIn)
                        confirmation.setText("Welcome, " + username);
                    else
                        confirmation.setText("Incorrect password, try again");
                }else
                    confirmation.setText("User doesn't exist, please register.");


            }

        });

        ColumnConstraints column1 = new ColumnConstraints();column1.setPercentWidth(35);
        grid.getColumnConstraints().addAll(column1);

        grid.add(exit, 0, 0);
        grid.add(confirmation, 7, 0, 3, 1);
        grid.add(usernameLabel, 7, 7);
        grid.add(userInput, 8, 7);
        grid.add(passwordLabel, 7, 8);
        grid.add(passInput, 8, 8);
        grid.add(submitBtn, 7, 9, 1, 1);

        registerScene = new Scene( grid,800, 600);
        window.setScene(registerScene);
        window.show();
    }

    //verifica daca numele de utilizator si parola sunt valide
    private static boolean isAllowed(String username, String password){

        if(username.length() >= 2 && password.length() >= 8)
            return true;
        else
            return false;
    }

    //verifica existenta userului in bd
    private static boolean checkExistance(String username){
        try{
            UsersRepository.findUserByName(username);
        }catch(NoResultException e){
            return false;
        }
        return true;
    }

    //adauga noul utilizator in baza de date
    private static void createUser(String username, String password){

        EntityManager em = EntityManagement.getInstance().getEntityManagerFactory().createEntityManager();
        Users newUser = new Users(username, password);
        newUser.setScore(0);
        UsersRepository.create(newUser);
    }

    private static void addUser(String username, String password){
        try{
            UsersRepository.findUserByName(username);
        }catch(NoResultException e){
            userExists=false;
        }

        }

        //logheaza utilizatorul
    private static void loginUser(String username, String password){
        Users user = UsersRepository.findUserByName(username);
        System.out.println("check pass" + user.getPassword());
        if(user.getPassword().equals(password)){
            loggedIn = true;
        }
    }
}
