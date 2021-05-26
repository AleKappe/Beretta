/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubricaberetta;

import it.edu.gastaldiabba.rubricaberetta.model.Cliente;
import it.edu.gastaldiabba.rubricaberetta.model.ManageClienti;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.event.EventType;

/**
 *
 * @authors alecappe
 */
public class RubricaBeretta extends Application {
     
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/RubricaGui.fxml"));
 /*     
        //Temporanea lettura da file cablata per sviluppare //////////////////
       String filename = "resurces/BerettaClienti.xml";
       String filenamesave = "resurces/BerettaClienti_saved.xml";
//////////////////////////////////////////////////////////////////////
       
        ArrayList<Cliente> ClientiDaFile;//lista dei nomi delle societa
        ArrayList<String> listaSocieta; //lista dei nomi delle societa
        ClientiDaFile = ManageClienti.caricaArrayDaFileXML(filename);
        ManageClienti.salvaArraySuFileXML(ClientiDaFile, filenamesave);
        System.out.println();
 /*
    //FILTRO PER CITTA
        ManageClienti.filterForCity(ClientiDaFile, "Genova");
        System.out.println();
    //FILTRO SU BASE AFFIDABILITA >= x
        ManageClienti.filterForReliability(ClientiDaFile, 7);
        System.out.println();
    //FILTRO COMBINATO PRIMA PER CITTA e POI PER AFFIDABILITA
        listaSocieta = ManageClienti.getAllragSoc(ManageClienti.filterForReliability(ManageClienti.filterForCity(ClientiDaFile, "Torino"), 7));
        System.out.println(listaSocieta.toString());
        System.out.println();
    //ORDINAMNTO PER AFFIDABILITA DECRESCENTE
        ManageClienti.sortClienti(ClientiDaFile, "Aff");
        System.out.println("Ordinati");
        ManageClienti.printPersonaArray(ClientiDaFile);
    //ORDINAMENTO ALFABETICO PER RAGIONE SOCIALE 
        ManageClienti.sortClienti(ClientiDaFile, "RagSoc");
        System.out.println("Ordinati");
        ManageClienti.printPersonaArray(ClientiDaFile);
        System.out.println("selezione di Tonno");
    //SELEZIONE DALL'ARRAYLIST DELL'ELEMENTO SU BASE RAGIONE SOCIALE
        ManageClienti.getragSoc(ClientiDaFile, "Tonno");
        System.out.println("List RagSoc");
    

//CREAZIONE DELLA LISTA DELLE SOLE RAGIONI SOCIALI PER LA LISTA widget
    //filtri e ordinamento provenienti dai widget
    String filtrocitta = "*";
    Integer filtroAffidabilita = 0;
    String ordinamento = "Aff";
    
        listaSocieta = ManageClienti.ragSocList(ClientiDaFile, filtrocitta, filtroAffidabilita, ordinamento);
    System.out.println("lista societa con filtro citta:" + filtrocitta +" filtro affidabilita:"+ filtroAffidabilita.toString()+" filtro ordinamento:"+ordinamento +":");
    System.out.println(listaSocieta.toString());
        
    filtrocitta = "*";    
    ordinamento = "RagSoc";
    filtroAffidabilita = 5;
    
        listaSocieta = ManageClienti.ragSocList(ClientiDaFile, filtrocitta, filtroAffidabilita, ordinamento);
    System.out.println("lista societa con filtro citta:" + filtrocitta +" filtro affidabilita:"+ filtroAffidabilita.toString()+" filtro ordinamento:"+ordinamento +":");
    System.out.println(listaSocieta.toString());
    
    ArrayList<String> ListaCitta = new ArrayList<String>();
    ListaCitta = ManageClienti.LoadCities(ClientiDaFile);
    System.out.println("Lista città univoca " + ListaCitta.toString());
    
    ArrayList<Integer> ListaAff = new ArrayList<Integer>();
    ListaAff = ManageClienti.LoadAff(ClientiDaFile);
    System.out.println("Lista Aff univoca " + ListaAff.toString());
        */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("B"+ "\u03A3"+"reTTa");
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}