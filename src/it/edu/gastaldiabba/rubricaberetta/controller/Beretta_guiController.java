/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubricaberetta.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author alecappe
 */
public class Beretta_guiController implements Initializable {

    @FXML
    private Pane sfondo;
    @FXML
    private AnchorPane background;
    @FXML
    private ListView<String> lista;
    @FXML
    private Button bottone_aggiungi;
    @FXML
    private Button bottone_elimina;
    @FXML
    private TitledPane ordina_per_aperto;
    @FXML
    private ToggleButton dark_mode;
    
    ObservableList<String> listview = FXCollections.observableArrayList("item 1","Item 2");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.lista.setItems(listview);
    }    
    
}
