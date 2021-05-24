/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubricaberetta.controller;

import it.edu.gastaldiabba.rubricaberetta.model.Cliente;
import it.edu.gastaldiabba.rubricaberetta.model.ManageClienti;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private ListView<String> lista_RagSoc;// lista ragSoc
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

    private String filename = "resurces/BerettaClienti.xml";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.lista_RagSoc.getItems().addAll(CaricaLista());
    }    
    
    public ArrayList<String> CaricaLista(){
        int aff = 0;
        String citta = "*";
        String ordine = "Aff";
        
        ArrayList<Cliente> Clie = new ArrayList<Cliente>();
        ArrayList<String> Ls = new ArrayList<String>();
        Clie = ManageClienti.caricaArrayDaFileXML(this.filename);
        
        Ls = ManageClienti.ragSocList(Clie, citta, aff, ordine );//Lista ,filtro Citta , filtroAff , Ordinamento Aff o RagSoc
        return Ls;
    }
    
}
