/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubricaberetta.controller;

import it.edu.gastaldiabba.rubricaberetta.model.Cliente;
import it.edu.gastaldiabba.rubricaberetta.model.ManageClienti;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author alecappe
 */
public class PrincipaleController implements Initializable {

    @FXML
    private Pane sfondo;
    @FXML
    private AnchorPane background;
    @FXML
    private ListView<Cliente> lista_RagSoc;// lista ragSoc
    @FXML
    private Button bottone_aggiungi;
    @FXML
    private Button bottone_elimina;
    @FXML
    private TitledPane ordina_per_aperto;
    @FXML
    private ListView<Cliente> Note;// lista note
    @FXML
    private TextArea arae_bella; //text area per mostrare i dettagli

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String filename = "C:/Users/alecappe/Desktop/file_vari/BerettaClienti.xml";
        this.lista_RagSoc.getItems().addAll(ManageClienti.caricaArrayDaFileXML(filename));
    }    
    
}
