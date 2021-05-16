/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubricaberetta;

import it.edu.gastaldiabba.rubricaberetta.model.Cliente;
import it.edu.gastaldiabba.rubricaberetta.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @authors alecappe
 */
public class RubricaBeretta extends Application {
     
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/principale.fxml"));
       
        //Temporanea lettura da file cablata per sviluppare //////////////////
       String filename = "C:/Users/alecappe/Desktop/file_vari/BerettaClienti.xml";
//////////////////////////////////////////////////////////////////////
        
        ArrayList<Cliente> ClientiDaFile;//lista dei nomi delle societa
        ArrayList<String> listaSocieta; //lista dei nomi delle societa
        ClientiDaFile = Cliente.caricaArrayDaFileXML(filename);
        System.out.println();
    //FILTRO PER CITTA
        Cliente.filterForCity(ClientiDaFile, "Genova");
        System.out.println();
    //FILTRO SU BASE AFFIDABILITA >= x
        Cliente.filterForReliability(ClientiDaFile, 7);
        System.out.println();
    //FILTRO COMBINATO PRIMA PER CITTA e POI PER AFFIDABILITA
        listaSocieta = Cliente.getAllragSoc(Cliente.filterForReliability(Cliente.filterForCity(ClientiDaFile, "Torino"), 7));
        System.out.println(listaSocieta.toString());
        System.out.println();
    //ORDINAMNTO PER AFFIDABILITA DECRESCENTE
        Cliente.sortClienti(ClientiDaFile, "Aff");
        System.out.println("Ordinati");
        Cliente.printPersonaArray(ClientiDaFile);
    //ORDINAMENTO ALFABETICO PER RAGIONE SOCIALE 
        Cliente.sortClienti(ClientiDaFile, "RagSoc");
        System.out.println("Ordinati");
        Cliente.printPersonaArray(ClientiDaFile);
        System.out.println("selezione di Tonno");
    //SELEZIONE DALL'ARRAYLIST DELL'ELEMENTO SU BASE RAGIONE SOCIALE
        Cliente.getragSoc(ClientiDaFile, "Tonno");
        System.out.println("List RagSoc");
    

//CREAZIONE DELLA LISTA DELLE SOLE RAGIONI SOCIALI PER LA LISTA widget
    //filtri e ordinamento provenienti dai widget
    String filtrocitta = "";
    Integer filtroAffidabilita = 0;
    String ordinamento = "Aff";
    
        listaSocieta = Cliente.ragSocList(ClientiDaFile, filtrocitta, filtroAffidabilita, ordinamento);
    System.out.println("lista societa con filtro citta:" + filtrocitta +" filtro affidabilita:"+ filtroAffidabilita.toString()+" filtro ordinamento:"+ordinamento +":");
    System.out.println(listaSocieta.toString());
        
    filtrocitta = "";    
    ordinamento = "RagSoc";
    filtroAffidabilita = 0;
    
        listaSocieta = Cliente.ragSocList(ClientiDaFile, filtrocitta, filtroAffidabilita, ordinamento);
    System.out.println("lista societa con filtro citta:" + filtrocitta +" filtro affidabilita:"+ filtroAffidabilita.toString()+" filtro ordinamento:"+ordinamento +":");
    System.out.println(listaSocieta.toString());
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}