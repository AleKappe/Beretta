/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubricaberetta.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
    static int compareCity(String a, String b) {
        return a.compareToIgnoreCase(b);
    }
        
    static int compareRagSoc(Cliente a, Cliente b) {
        return a.getRagSoc().compareToIgnoreCase(b.getRagSoc());
    } 
    
    static int compareAff(Cliente a, Cliente b) {
        return b.getAff() - a.getAff();
    } 
    static Integer compareInt(Integer a, Integer b) {
        return b - a;
    }
    public static ArrayList<Cliente> sortClienti(ArrayList<Cliente> Clie, String type) {
        //Al metodo sort dell'oggetto ArrayList passiamo come argomento 
        //una funzione da usare ossia il metodo compareRagSoc o compareAff
        //per fare cio' usiamo la sintassi classe::metodo e non la chiamata 
        //del metodo statico class.metodo(parametri)
        if (type.equals("Ragione Sociale")) {
            Clie.sort(ManageClienti::compareRagSoc);
        }else if(type.equals("Affidabilita"))  { 
            Clie.sort(ManageClienti::compareAff); 
        }
        return Clie;
    }
    ///////////////////////////////////////////////////
    
    ///////////////////////
    // FILTRI ARRAY LIST //
    ///////////////////////
    
    public static ArrayList<Cliente> filterForCity(ArrayList<Cliente> Clie, String city) {
        
        if (!(city.isEmpty() || city.equals("*"))) {
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
        //se 0 npn filtra
        if (aff>0) {
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
        if (fAff == null || fAff == 0) {
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
          String item = Cln.getCitta();
          if(!City.contains(item))
              City.add(item);
        }
        if(!City.contains("*"))
              City.add("*");
        City.sort(ManageClienti::compareCity);
        return City;
    }
    public static ArrayList<Integer> LoadAff(ArrayList<Cliente> Clie){
        ArrayList<Integer> Aff = new ArrayList<Integer>();
        for (Cliente Cln : Clie) {
          Integer item = Cln.getAff();
          if(!Aff.contains(item))
              Aff.add(item);
        }
        if(!Aff.contains(0))
              Aff.add(0);
        Aff.sort(ManageClienti::compareInt);
        return Aff;
    }
    //
    
    public static Cliente getCliente(ArrayList<Cliente> Clie, String ragsoc) {
        Cliente cliOut = new Cliente();
        for (Cliente Cln : Clie) {
            if(Cln.getRagSoc().equals(ragsoc)){
                cliOut = Cln;
                break;
            }
        }
        return cliOut;
    }
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
            //printPersonaArray(clieList);
            return clieList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
    *  
    */
    public static void salvaArraySuFileXML(ArrayList<Cliente> Clie, String pathname) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, IOException {

        try {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document = docBuilder.newDocument();

        // root element
        Element root = document.createElement("clienti");
        document.appendChild(root);
        
        for (Cliente Cln : Clie) { 
        // elemento cliente
            Element cliitem = document.createElement("cliente");
            root.appendChild(cliitem);
        
            Element aff = document.createElement("Aff");
            aff.appendChild(document.createTextNode(String.valueOf(Cln.getAff())));
            cliitem.appendChild(aff);

            Element ragsoc = document.createElement("RagSoc");
            ragsoc.appendChild(document.createTextNode(Cln.getRagSoc()));
            cliitem.appendChild(ragsoc);
            
            Element addr = document.createElement("Indirizzo");
            addr.appendChild(document.createTextNode(Cln.getIndirizzo()));
            cliitem.appendChild(addr);
            
            Element piva = document.createElement("pIva");
            piva.appendChild(document.createTextNode(Cln.getpIva()));
            cliitem.appendChild(piva);
            
            Element citta = document.createElement("citta");
            citta.appendChild(document.createTextNode(Cln.getCitta()));
            cliitem.appendChild(citta);
            
            Element tell = document.createElement("numTel");
            tell.appendChild(document.createTextNode(Cln.getnTelefono()));
            cliitem.appendChild(tell);
            
            Element mail = document.createElement("mail");
            mail.appendChild(document.createTextNode(Cln.getMail()));
            cliitem.appendChild(mail);
            
            Element pec = document.createElement("pec");
            pec.appendChild(document.createTextNode(Cln.getPec()));
            cliitem.appendChild(pec);
            
            Element Note = document.createElement("Note");
            Note.appendChild(document.createTextNode(Cln.getPec()));
            cliitem.appendChild(Note);
            
        }
     
        // create the xml file
        //transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //to write line break and indent
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //
        DOMSource domSource = new DOMSource(document);
        //StreamResult streamResult = new StreamResult(System.out);
        StreamResult streamResult = new StreamResult(new File(pathname));
 
            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
 
        transformer.transform(domSource, streamResult);
        
       
                   System.out.println("Done creating XML File");
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    
    }
    
    
}

