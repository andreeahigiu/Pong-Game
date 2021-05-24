package game;

import dataBase.entityClasses.Users;
import dataBase.repository.UsersRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RankingBoard {

    private static Stage window;
    private static Scene rankingScene;
    static TableView<Users> ranking;

    public static void displayRanking(){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ranking");
        ObservableList<Users> rankList = FXCollections.observableArrayList();
       
        for(Users user : UsersRepository.findTopUsers()){
            rankList.add(user);
        }

        //Username column
        TableColumn<Users, String> nameColumn = new TableColumn<>("Username");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        //Score column
        TableColumn<Users, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setMinWidth(100);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        ranking = new TableView<>();
        ranking.setItems(rankList);
        ranking.getColumns().addAll(nameColumn, scoreColumn);


        BorderPane border = new BorderPane();

        VBox tableBox = new VBox();
        tableBox.getChildren().addAll(ranking);


        //the back/exit window button
        Button exit = new Button("Back");
        exit.setOnAction(e -> {
            window.close();
        });
        StackPane btnLayout = new StackPane();
        btnLayout.getChildren().add(exit);
        btnLayout.setAlignment (Pos.TOP_LEFT);

        border.setTop(btnLayout);
        border.setCenter(new StackPane(tableBox));

        rankingScene = new Scene(border,800, 600);
        window.setScene(rankingScene);
        window.show();
    }
}
