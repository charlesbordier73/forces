package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller providing helpful methods to sub controllers
 */
public class Controller {

    private static final String APP_NAME = "Réquisitions";
    private static final int APP_WIDTH = 800;
    private static final int APP_HEIGHT = 650;

    private static Controller instance;

    private static View currentView = View.REQUISITIONS;

    /**
     * The current stage of javafx
     */
    private Stage stage;

    public Controller() {
    }

    //Singleton
    public static Controller getInstance() {
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Set a new stage
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Go to a specific view
     * @param view
     */
    public void goTo(View view) {
        if (stage != null) {
            try {
                Parent root = FXMLLoader.load(Launcher.class.getResource( view.toString() + ".fxml"));
                stage.setTitle(APP_NAME);
                stage.setScene(new Scene(root, APP_WIDTH, APP_HEIGHT));
                stage.show();
            } catch (IOException e) {
                System.err.println("The view " + view.toString() + ".fxml" + " doesn't exist.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Go to a specific view and passing data
     * @param view
     * @param data
     */
    public void goTo(View view, Object data) {
        if (stage != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(view.toString() + ".fxml"));
                Parent root = loader.load();
                OnLoad controller = loader.getController();
                controller.onLoad(data);
                stage.setTitle(APP_NAME);
                stage.setScene(new Scene(root, APP_WIDTH, APP_HEIGHT));
                stage.show();
            } catch (IOException e) {
                System.err.println("The view " + view.toString() + ".fxml" + " doesn't exist.");
                e.printStackTrace();
            }
        }
    }

    public void dismiss() {
        goTo(this.getCurrentView());
    }

    public void setCurrentView(View view) {
        Controller.getInstance().currentView = view;
    }

    public View getCurrentView() {
        return this.currentView;
    }
}