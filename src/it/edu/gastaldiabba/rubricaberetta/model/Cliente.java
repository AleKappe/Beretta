package it.edu.gastaldiabba.rubricaberetta.model;

/**
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
    private int nTelefono;
    private String mail;
    private String pec;
    
    
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

    public int getnTelefono() {
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

    public void setnTelefono(int nTelefono) {
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

    public Cliente(String ragSoc, String indirizzo, String pIva, String citta, int nTelefono, String mail, String pec) {
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
        return "Cliente{" + "ragSoc=" + ragSoc +
                ", indirizzo=" + indirizzo +
                ", pIva=" + pIva + 
                ", citta=" + citta +
                ", nTelefono=" + nTelefono +
                ", mail=" + mail +
                ", pec=" + pec + '}';
    }

}
