package it.edu.gastaldiabba.rubricaberetta.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

/*
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.jdom.*;
import org.jdom.output.*;
import java.io.*;
*/
 
/**
 *
 * @author lukef alesc marcb lorc (Salamini Beretta)
 */


public class Cliente{
    
    private int Aff;
    private String ragSoc;
    private String indirizzo;
    private String pIva;
    private String citta;
    private String nTelefono;
    private String mail;
    private String pec;
    private String Nc;

    public int getAff() {
        return Aff;
    }

    public void setAff(int Aff) {
        this.Aff = Aff;
    }

    public String getNc() {
        return Nc;
    }

    public void setNc(String Nc) {
        this.Nc = Nc;
    }

    private static File xmlFile = new File("todo.xml");
    
    
    public String getRagSoc() {
        return ragSoc;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getpIva() {
        return pIva;
    }

    public String getCitta() {
        return citta;
    }

    public String getnTelefono() {
        return nTelefono;
    }

    public String getMail() {
        return mail;
    }

    public String getPec() {
        return pec;
    }
    
    public void setRagSoc(String ragSoc) {
        this.ragSoc = ragSoc;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setnTelefono(String nTelefono) {
        this.nTelefono = nTelefono;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    
    public Cliente() {
    }

    public Cliente(int aff, String ragSoc, String indirizzo, String pIva, String citta, String nTelefono, String mail, String pec, String node) {
        this.Aff = aff;
        this.ragSoc = ragSoc;
        this.indirizzo = indirizzo;
        this.pIva = pIva;
        this.citta = citta;
        this.nTelefono = nTelefono;
        this.mail = mail;
        this.pec = pec;
        this.Nc = node;
    }

    @Override
    public String toString() {
        return "Cliente{" +"AffidabilitÃ ="+ Aff + ", ragSoc=" + ragSoc + ", indirizzo=" + indirizzo + ", pIva=" + pIva + ", citta=" + citta + ", nTelefono=" + nTelefono + ", mail=" + mail + ", pec=" + pec + ", Nc=" + Nc + '}';
    }
    
    
    
    //
    public static void ClienteArrayList(Cliente[] cln){
        ArrayList<Cliente> ClList = new ArrayList<Cliente>();
        
        
        System.out.println(ClList.toString());
    }
    

    //metodi per xml 
    
    public static File scegliFile() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            System.out.println("l'utente ha annullato o cancellato la finestra");
            return null;
        }
    }
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
            Clie.sort(Cliente::compareRagSoc);
        }else if(type.equals("Aff"))  { 
            Clie.sort(Cliente::compareAff); 
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
        
           clieList = Cliente.filterForCity(Clie, fCity);
           if (!clieList.isEmpty()) {
               clieList = Cliente.filterForReliability(clieList, fAff);
           }
           
           if (!clieList.isEmpty()) {
               clieList = Cliente.sortClienti(clieList, ord);
           }
           
           if (!clieList.isEmpty()) {
               RagSoc = Cliente.getAllragSoc(clieList); 
           }  
        }
        
        return RagSoc;
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
            printPersonaArray(clieList);
            return clieList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    * Creare un file xml - codice da mettere apposto 
    */
    public static void createXml() throws ParserConfigurationException, TransformerConfigurationException, TransformerException, IOException {

        try {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element todos = doc.createElement("persona");
        doc.appendChild(todos);

        Element todo = doc.createElement("nome");
        todo.appendChild(doc.createTextNode(" Luigi "));
        todos.appendChild(todo);
        
        Element todo2 = doc.createElement("cognome");
        todo2.appendChild(doc.createTextNode(" Verdi "));
        todos.appendChild(todo2);
        
        Element todo3 = doc.createElement("eta");
        todo2.appendChild(doc.createTextNode(" 40 "));
        todos.appendChild(todo3);
        
        Element todo4 = doc.createElement("dataDiNascita");
        todo2.appendChild(doc.createTextNode("03-03-1980"));
        todos.appendChild(todo4);
        
        Element todo5 = doc.createElement("lingue");
        todo2.appendChild(doc.createTextNode(" Italiano, Inglese, Francese "));
        todos.appendChild(todo5);
        
        Element todo6 = doc.createElement("corsiStudio");
        todo2.appendChild(doc.createTextNode(" licenza elementare, licenza media, diploma informatica, laurea magistrale informatica "));
        todos.appendChild(todo6);
        

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        if (!xmlFile.exists()) {
            xmlFile.createNewFile();
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        }
        } catch (ParserConfigurationException | TransformerException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
