package it.edu.gastaldiabba.rubricaberetta.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author farina.luca
 */
import java.util.ArrayList;

/*public class Note extends Cliente{
    ArrayList<String> note = new ArrayList<String>(); 
    
    note.add("adfaf");
    note.add("Volvo");
}
*/
public class Note{
  public static void LisNote() {
    ArrayList<String> note = new ArrayList<String>();
    note.add("Volvo");
    note.add("BMW");
    note.add("Ford");
    note.add("Mazda");
    System.out.println(note);
  }
}