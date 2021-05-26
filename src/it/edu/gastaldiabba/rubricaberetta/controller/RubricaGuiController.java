/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubricaberetta.controller;

import it.edu.gastaldiabba.rubricaberetta.model.Cliente;
import it.edu.gastaldiabba.rubricaberetta.model.ManageClienti;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

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
        this.bottone_salva.setOnMouseClicked(eh -> { saveOnFile(); });
        this.bottone_svuota.setOnMouseClicked(eh -> { RefreshLista(); });
        this.bottone_aggiungi.setOnMouseClicked(eh -> { 
            addNewCliente();
            RefreshLista();});
        this.bottone_elimina.setOnMouseClicked(eh -> { 
            deleteCliente(); 
            RefreshLista();});
        this.bottone_modifica.setOnMouseClicked(eh -> { 
            deleteCliente(); 
            addNewCliente();
            RefreshLista();});
        
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
    
   
    
    public void addNewCliente(){
        Cliente cli = new Cliente();
        
        cli.setAff(Integer.parseInt(tAff.getText()));
        cli.setRagSoc(tRagSoc.getText());
        cli.setMail(tMail.getText());
        cli.setPec(tPec.getText());
        cli.setnTelefono(tPhone.getText());
        cli.setpIva(tIva.getText());
        cli.setIndirizzo(tAddress.getText());
        cli.setCitta(tCity.getText());
        cli.setNc(tNote.getText());
       
        ClientiView.add(cli);
       
    }
    
    public void deleteCliente(){
        //Estraggo la ragione sociale attualmente selezionata nella listview
        String tobedelete = this.lista.getSelectionModel().getSelectedItem();
        //estraggo il cliente con la ragione sociale selezionata dall'arraylist clienti       
        Cliente clitoberemove = ManageClienti.getragSoc(ClientiView, tobedelete);
        //rimuovo il cliente dalla arraylist
        ClientiView.remove(clitoberemove);
        //ricarico la listview
       
    }
    
    
    public void saveOnFile(){
        
        String patholdfile = filename.replace("xml", "old");
        File fxml = new File(this.filename);//newfile .xml
        File oldxml = new File(patholdfile);//oldfile .old
        
        if(fxml.exists() && !fxml.isDirectory()) {
            //Se c'e' il file xml
            if(oldxml.exists() && !oldxml.isDirectory()) { 
                //se c'e' il file old lo cancello
                oldxml.delete();
            }
            //rename del file .xml in file .old
            Path path = Paths.get(filename);                         // /resources/ e BerettaClienti.xml
            // call getFileName() and get FileName path object
            Path fName = path.getFileName();                         //BerettaClienti.xml
            String oldfile = fName.toString().replace("xml", "old"); // BerettaClienti.old
            Path source = Paths.get(filename);                       //path /resources/
            try {
                Files.move(source, source.resolveSibling(oldfile)); //rimonina 
            } catch (IOException ex) {
                Logger.getLogger(RubricaGuiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            //scrittura del nuovo .xml
            ManageClienti.salvaArraySuFileXML(ClientiView, filename);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(RubricaGuiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(RubricaGuiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RubricaGuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
