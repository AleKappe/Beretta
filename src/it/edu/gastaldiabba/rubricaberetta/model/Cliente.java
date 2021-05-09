package it.edu.gastaldiabba.rubricaberetta.model;

import java.io.File;
import java.io.IOException;
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
    
    private String ragSoc;
    private String indirizzo;
    private String pIva;
    private String citta;
    private String nTelefono;
    private String mail;
    private String pec;
    private Note Nc;

    public static File getXmlFile() {
        return xmlFile;
    }

    public static void setXmlFile(File xmlFile) {
        Cliente.xmlFile = xmlFile;
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

    //costruttore vuoto
    public Cliente() {
    }

    public Cliente(String ragSoc, String indirizzo, String pIva, String citta, String nTelefono, String mail, String pec) {
        this.ragSoc = ragSoc;
        this.indirizzo = indirizzo;
        this.pIva = pIva;
        this.citta = citta;
        this.nTelefono = nTelefono;
        this.mail = mail;
        this.pec = pec;
    }

    @Override
    public String toString() {
        return "Cliente{" + "ragSoc=" + ragSoc + ", indirizzo=" + indirizzo + ", pIva=" + pIva + ", citta=" + citta + ", nTelefono=" + nTelefono + ", mail=" + mail + ", pec=" + pec + ", Nc=" + Nc + '}';
    }
    
    private void ClienteArrayList(){
        
    }
    
    
    public static File scegliFile() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            System.out.println("l'utente ha annullato o cancellato la finestra");
            return null;
        }
    }
    public static void printPersonaArray(Cliente[] Clie) {

        for (Cliente Cln : Clie) {
            System.out.println(Cln.toString());
        }
    }
    public static Cliente[] caricaArrayDaFileXML(String pathname){

        try {
            File selectedFile = new File(pathname);
//creating a constructor of file class and parsing an XML file          
//an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(selectedFile);
            doc.getDocumentElement().normalize();
//            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("cliente");
//         System.out.println("\nCi sono " + Integer.toString(nodeList.getLength()) + " elementi di tipo  persona");
            Cliente[] clie = new Cliente[nodeList.getLength()];
// nodeList is not iterable, so we are using for loop
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
//                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    clie[itr] = new Cliente();
                    clie[itr].setRagSoc(eElement.getElementsByTagName("RagSoc").item(0).getTextContent());
                    clie[itr].setIndirizzo(eElement.getElementsByTagName("Indirizzo").item(0).getTextContent());
                    clie[itr].setpIva(eElement.getElementsByTagName("pIva").item(0).getTextContent());
                    clie[itr].setCitta(eElement.getElementsByTagName("citta").item(0).getTextContent());
                    clie[itr].setCitta(eElement.getElementsByTagName("numTel").item(0).getTextContent());
                    clie[itr].setMail(eElement.getElementsByTagName("mail").item(0).getTextContent());
                    clie[itr].setPec(eElement.getElementsByTagName("pec").item(0).getTextContent());
                }
            }
            printPersonaArray(clie);
            return clie;
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
