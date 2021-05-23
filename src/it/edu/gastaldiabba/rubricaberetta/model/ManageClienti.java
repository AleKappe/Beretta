/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubricaberetta.model;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author alecappe
 */
public class ManageClienti {
    
    private ArrayList<Cliente> ArrayListCliente = new ArrayList<Cliente>();
    
        public static void printPersonaArray(ArrayList<Cliente> Clie) {

        for (Cliente Cln : Clie) {
            System.out.println(Cln.toString());
        }
    }
 
    /////////////////////////////////////////////////
    // GESTIONE DEGLI ORDINAMENTI E METODI COMPARE //
    /////////////////////////////////////////////////
    
    static int compareRagSoc(Cliente a, Cliente b) {
        return a.getRagSoc().compareToIgnoreCase(b.getRagSoc());
    } 
    
    static int compareAff(Cliente a, Cliente b) {
        return a.getAff() - b.getAff();
    } 
    public static ArrayList<Cliente> sortClienti(ArrayList<Cliente> Clie, String type) {
        //Al metodo sort dell'oggetto ArrayList passiamo come argomento 
        //una funzione da usare ossia il metodo compareRagSoc o compareAff
        //per fare cio' usiamo la sintassi classe::metodo e non la chiamata 
        //del metodo statico class.metodo(parametri)
        if (type.equals("RagSoc")) {
            Clie.sort(ManageClienti::compareRagSoc);
        }else if(type.equals("Aff"))  { 
            Clie.sort(ManageClienti::compareAff); 
        }
        return Clie;
    }
    ///////////////////////////////////////////////////
    
    ///////////////////////
    // FILTRI ARRAY LIST //
    ///////////////////////
    
    public static ArrayList<Cliente> filterForCity(ArrayList<Cliente> Clie, String city) {
        
        if (!city.isEmpty()) {
            ArrayList<Cliente> clieList = new ArrayList<Cliente>();
        
            for (Cliente Cln : Clie) {
               if (Cln.getCitta().equals(city)) {
                   //System.out.println(Cln.toString());
                   clieList.add(Cln);
                }   
           }
        return clieList;
        }
        return Clie;
    }

    public static ArrayList<Cliente> filterForReliability(ArrayList<Cliente> Clie, Integer aff) {
        //TO DO Aff deve essere un numero maggiore o uguale a 0
        if (aff>=0) {
            ArrayList<Cliente> clieList = new ArrayList<Cliente>();
            for (Cliente Cln : Clie) {
               if (Cln.getAff() >= aff) {
                  //System.out.println(Cln.toString());
                  clieList.add(Cln);
               }   
            }
            return clieList;
        }
        return Clie;
    }
    ////////////////////////////////////////////////////////////////////////
    
    public static Cliente getragSoc(ArrayList<Cliente> Clie, String ragSoc) {
        
        for (Cliente Cln : Clie) {
            if (Cln.getRagSoc().equals(ragSoc)) {
               //System.out.println(Cln.toString());
               return Cln;
            }   
        }
        return null;
    }
    
    public static ArrayList<String> getAllragSoc(ArrayList<Cliente> Clie) {
        
        ArrayList<String> RagSoc = new ArrayList<String>();
        for (Cliente Cln : Clie) {
          RagSoc.add(Cln.getRagSoc());
        }
        return RagSoc;
    }    
    
    
    //METODO PER PREPARARE LA LISTA DI RAGIONI SOCIALI
    //BASATA SU FILTRI SELEZIONATI E ORDINAMENTI
    public static ArrayList<String> ragSocList(ArrayList<Cliente> Clie, 
                                               String fCity,//filtro per citta se vuoto non filtra
                                               Integer fAff,//filtro per maggiore uguale affidabilita se null non filtra
                                               String ord) {//per ragione sociale o Affidabilita o niente
        ArrayList<String> RagSoc = new ArrayList<String>();
        if (fAff == null) {
            fAff = -1;
        }
        if (!Clie.isEmpty()) {
           ArrayList<Cliente> clieList = new ArrayList<Cliente>();
        
           clieList = ManageClienti.filterForCity(Clie, fCity);
           if (!clieList.isEmpty()) {
               clieList = ManageClienti.filterForReliability(clieList, fAff);
           }
           
           if (!clieList.isEmpty()) {
               clieList = ManageClienti.sortClienti(clieList, ord);
           }
           
           if (!clieList.isEmpty()) {
               RagSoc = ManageClienti.getAllragSoc(clieList); 
           }  
        }
        
        return RagSoc;
    }
    //Metodo per caricare il filtro
    //
    //
    public static ArrayList<String> LoadCities(ArrayList<Cliente> Clie){
        ArrayList<String> City = new ArrayList<String>();
        for (Cliente Cln : Clie) {
          City.add(Cln.getCitta());
        }
        return City;
    }
    //
    //
    public static ArrayList<Cliente> caricaArrayDaFileXML(String pathname){

        try {
            File selectedFile = new File(pathname);
            //creating a constructor of file class and parsing an XML file          
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(selectedFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("cliente");
            //System.out.println("\nCi sono " + Integer.toString(nodeList.getLength()) + " elementi di tipo  cliente");
            //Cliente[] clie = new Cliente[nodeList.getLength()];
            ArrayList<Cliente> clieList = new ArrayList<Cliente>();
            // nodeList is not iterable, so we are using for loop
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
            //System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    Cliente clie = new Cliente();
                    clie.setAff(Integer.parseInt(eElement.getElementsByTagName("Aff").item(0).getTextContent()));
                    clie.setRagSoc(eElement.getElementsByTagName("RagSoc").item(0).getTextContent());
                    clie.setIndirizzo(eElement.getElementsByTagName("Indirizzo").item(0).getTextContent());
                    clie.setpIva(eElement.getElementsByTagName("pIva").item(0).getTextContent());
                    clie.setCitta(eElement.getElementsByTagName("citta").item(0).getTextContent());
                    clie.setnTelefono(eElement.getElementsByTagName("numTel").item(0).getTextContent());
                    clie.setMail(eElement.getElementsByTagName("mail").item(0).getTextContent());
                    clie.setPec(eElement.getElementsByTagName("pec").item(0).getTextContent());
                    clie.setNc(eElement.getElementsByTagName("Note").item(0).getTextContent());
                    clieList.add(clie);
                }
            }
            printPersonaArray(clieList);
            return clieList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

