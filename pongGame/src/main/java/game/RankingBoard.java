package game;

import dataBase.entityClasses.Users;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RankingBoard {

    private static Stage window;
    private static Scene rankingScene;

    public static void displayRanking(){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        List<Users> rankList = new ArrayList();



    }
}
