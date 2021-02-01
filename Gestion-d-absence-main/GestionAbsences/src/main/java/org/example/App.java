package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Promo"), 700, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch();

        //ApprenantDao apprenantDao = new ApprenantDaoImp();

        //Apprenant appr = apprenantDao.selectNonJustifie(("yassir@gmail.com"));


        //System.out.println(appr);
//        PromoDao promoDao = new PromoDaoImp();
//        Promo promo = new Promo(3,"Promo3","2019-2020");
//        Promo prom = (Promo) promoDao.getAll();
//        System.out.println(prom);

//        System.out.println("Bien");
//
//        PromoDaoImp promo = new PromoDaoImp();
//        System.out.println(promo.getAll());

        //salleDao.getAll().forEach(System.out::println);
        //Salle sal = salleDao.getById(2);
        //System.out.println(sal);

    }

}