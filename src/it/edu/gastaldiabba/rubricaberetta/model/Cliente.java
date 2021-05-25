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
        return "Cliente{" + "Aff=" + Aff + ", ragSoc=" + ragSoc + ", indirizzo=" + indirizzo + ", pIva=" + pIva + ", citta=" + citta + ", nTelefono=" + nTelefono + ", mail=" + mail + ", pec=" + pec + ", Nc=" + Nc + '}';
    }
    
    
    //metodi per xml 
    /*
    public static File scegliFile() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            System.out.println("l'utente ha annullato o cancellato la finestra");
            return null;
        }
    }
    */

}
