package Controller;

import Application.Main;
import Model.HTMLtoPDF;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabPaneController implements Initializable{

    public static final String FXMLPATH = "/View/tabpane.fxml";


    @FXML
    private TabPane tabPane;
    @FXML
    private Button menuButton;

    private JFXPopup popup;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addNewTab(false);

        //
        tabPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(tabPane.getSelectionModel().isSelected(tabPane.getTabs().size()-1))
                {
                    onAddNewTabButtonClicked();
                }
            }
        });

        popup = new JFXPopup(menuButton);

        JFXButton History = new JFXButton("History");
        History.setOnMouseClicked(e->{
            addNewTab(true);
        });

        JFXButton Bookmarks = new JFXButton("Bookmarks");

        JFXButton toPDFButton = new JFXButton("");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(History, Bookmarks, toPDFButton);
        popup.setPopupContent(vBox);
        menuButton.setOnMouseClicked(this::OnMenuButtonClicked);

    }

    //add a new tab
    public Tab addNewTab(boolean addHistory)
    {
        try {
            //Create a new tab
            Tab tab = new Tab();

            //Set content to tabcontent.fxml
            //Note that in the tabcontent.fxml file, if you have maxHeight="-Infinity" and maxWidth="-Infinity",
            //it will prevent your tabcontent.fxml to fill the entire tab
            if(!addHistory)
            {
                tab.setContent(FXMLLoader.load(getClass().getResource(TabContentController.FXMLPATH)));
                //Google.com by default
                tab.setText("Google.com");
            }
            else
            {
                tab.setContent(FXMLLoader.load(getClass().getResource(HistoryController.FXMLPATH)));
                tab.setText("History");
            }

            tabPane.getTabs().add(tabPane.getTabs().size()-1,tab);
            tabPane.getSelectionModel().select(tab);



            return tab;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private void onAddNewTabButtonClicked()
    {
        addNewTab(false);
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    private void OnMenuButtonClicked(MouseEvent e) {
        double a = menuButton.getScene().getWidth();
        popup.show(menuButton, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, e.getX(), e.getY());
    }



}
