/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubricaberetta.controller;

import it.edu.gastaldiabba.rubricaberetta.model.Cliente;
import it.edu.gastaldiabba.rubricaberetta.model.ManageClienti;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author alecappe
 */
public class RubricaGuiController implements Initializable {

    @FXML
    private AnchorPane sfondo;
    @FXML
    private Button bottone_elimina;
    @FXML
    private Button bottone_modifica;
    @FXML
    private ComboBox<String> CittaBox;
    @FXML
    private ComboBox<Integer> AffBox;
    @FXML
    private ComboBox<String> OrdBocx;
    @FXML
    private Button bottone_aggiungi;
    @FXML
    private Button bottone_svuota;
    @FXML
    private Button bottone_salva;
    @FXML
    private ListView<String> lista;
    
    @FXML
    private TextField tRagSoc;

    @FXML
    private TextField tMail;

    @FXML
    private TextField tPec;

    @FXML
    private TextField tPhone;
    
    @FXML
    private TextField tIva;

    @FXML
    private TextField tAff;

    @FXML
    private TextField tAddress;

    @FXML
    private TextField tCity;
    
    @FXML
    private TextArea tNote;
        
    ObservableList<String> ObsList;
    
    @FXML
    void selectcity(ActionEvent e) {
            System.out.println(this.CittaBox.getValue());
    }
    
    @FXML
    void ClickElimina(ActionEvent event) {
         System.out.println(this.CittaBox.getValue());
    }
    private ArrayList<Cliente> ClientiView;
    
    private String filename = "resurces/BerettaClienti.xml";
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> obslista = FXCollections.observableArrayList();
        this.lista.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);//selezione singola
        
        this.ClientiView = CaricaClienti();
        //Gestione CittaBox: caricamento, setdefault, evento
        this.CittaBox.getItems().addAll(CaricaListaCity(this.ClientiView));
        this.CittaBox.setValue("*");
        this.CittaBox.setOnAction(eh -> { RefreshLista(); });
        //Gestione AffBox: caricamento, setdefault, evento
        this.AffBox.getItems().addAll(CaricaListaAff(this.ClientiView));
        this.AffBox.setValue(0);
        this.AffBox.setOnAction(eh -> { RefreshLista(); });
        //Gestione OrdBox: inizalizzazione, setdefault, evento
        this.OrdBocx.getItems().addAll("Ragione Sociale", "Affidabilita");
        this.OrdBocx.setValue("Ragione Sociale");
        this.OrdBocx.setOnAction(eh -> { RefreshLista(); });
        //Gestione lista: cariamento configurazione
        
        obslista = obsCaricaListaRag(this.ClientiView, 
                                                    this.CittaBox.getValue(),
                                                    this.AffBox.getValue(),
                                                    this.OrdBocx.getValue());
   
        this.lista.setItems(obslista);
        
        
        //CATTURA IL CAMBIO DI SELEZIONE DELLA LIST VIEW
        MultipleSelectionModel<String> listSelModel = lista.getSelectionModel();
        listSelModel.selectedItemProperty().
                addListener(new ChangeListener<String>(){
                    public void changed(ObservableValue<? extends String> changed,String oldVal, String newVal){
                        selectedRagSoc(newVal);
                    }
                });
    }
    public void ShowData(){
        
    }
    
    
    public void selectedRagSoc(String ragsoc) {
        Cliente cli = ManageClienti.getCliente(ClientiView, ragsoc);
        tRagSoc.setText(cli.getRagSoc());
        tMail.setText(cli.getMail());
        tPec.setText(cli.getPec());
        tPhone.setText(cli.getnTelefono());
        tIva.setText(cli.getpIva());
        tAff.setText(String.valueOf(cli.getAff()));
        tAddress.setText(cli.getIndirizzo());
        tCity.setText(cli.getCitta());
        tNote.setText(cli.getNc());
    }
    
    public void cleanTxtField() {
        tRagSoc.clear();
        tMail.clear();
        tPec.clear();
        tPhone.clear();
        tIva.clear();
        tAff.clear();
        tAddress.clear();
        tCity.clear();
        tNote.clear();
    }
    
    public void RefreshLista() {
        ObservableList<String> obslista = FXCollections.observableArrayList();
        obslista.addAll(obsCaricaListaRag(this.ClientiView, 
                                                    this.CittaBox.getValue(),
                                                    this.AffBox.getValue(),
                                                    this.OrdBocx.getValue()));
          
        this.lista.getItems().clear();
        cleanTxtField();
        this.lista.getItems().addAll(obslista);
    }
    
    public ArrayList<Cliente> CaricaClienti(){
        ArrayList<Cliente> Clie = new ArrayList<Cliente>();
        Clie = ManageClienti.caricaArrayDaFileXML(this.filename);
        return Clie;
    }
    //Rappresentazione lista ragioni sociali
   
    public ObservableList<String> obsCaricaListaRag(ArrayList<Cliente> Clie, String citta, Integer aff, String ordine){
        
        ObservableList<String> Ls = FXCollections.
                observableArrayList(ManageClienti.ragSocList(Clie, citta, aff, ordine ));             
        return Ls;
    }
    //Lista filtro città
    public ArrayList<String> CaricaListaCity(ArrayList<Cliente> Clie){
        
        ArrayList<String> Ls = new ArrayList<String>();
        Ls = ManageClienti.LoadCities(Clie);
        return Ls;
    }
    //Lista filtro Affidabilita
    public ArrayList<Integer> CaricaListaAff(ArrayList<Cliente> Clie){
        
        ArrayList<Integer> Ls = new ArrayList<Integer>();
        Ls = ManageClienti.LoadAff(Clie);
        return Ls;
    }
    
    
}
