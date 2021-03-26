/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubricaberetta.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author alecappe
 */
public class BerettaGuiController implements Initializable {

    @FXML
    private Pane sfondo;
    @FXML
    private Button bottone_aggiungi;
    @FXML
    private Button bottone_elimina;
    @FXML
    private ChoiceBox<?> ordina_per;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
