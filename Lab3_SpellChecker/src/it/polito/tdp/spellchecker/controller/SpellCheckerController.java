package it.polito.tdp.spellchecker.controller;


import java.net.URL;
import java.util.*;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBoxLanguage;
    /*String perchè metterò una stringa nella cmbBox*/

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtWrongWords;

    @FXML
    private Label txtErrors;

    @FXML
    private Button btnClearText;

    @FXML
    private Label txtTime;
    Dictionary model;

	public void setModel(Dictionary model) {
		this.model=model;
	}

    @FXML
    void doClearText(ActionEvent event) {
    	txtWrongWords.clear();
    	txtInput.clear();
    	txtErrors.setText("");
    	txtTime.setText("");
    }
    
    
    /**
     * Trasforma il testo in input in una lista (paroleInput) e la passa a Dictionary che ne controlla la correttezza
     * @param event
     */
    @FXML
    void doSpellCheck(ActionEvent event) {
    	double t1=System.nanoTime();
    	/*Faccio caricare il dizionario in base alla scelta*/
    	model.loadDictionary(comboBoxLanguage.getValue());
    	/*Modifiche della forma dell'input*/
    	String[] input=txtInput.getText().toLowerCase().split(" ");
    	
    	List<String> paroleInput=new ArrayList<String>();
    	for(int i=0; i<input.length; i++){
    			paroleInput.add(input[i].replaceAll("[\\p{Punct}]", ""));
    	}
    	List<RichWord> output= new ArrayList<RichWord>(model.spellCheckText(paroleInput));
    	/*Conta #parole sbagliate ciclando sulla Lista passata da spellCheckText()*/
    	int cont=0;
    	for(RichWord r: output){
    		if(!r.isCorretto()){
    			cont++;
    			txtWrongWords.appendText(r.getTextInput()+"\n");
    		}
    	}
    	double t2=System.nanoTime();
    	txtTime.setText("Spell check completed in "+(t2-t1)/1e9+" seconds");
    	txtErrors.setText("The text contains "+cont+" errors");
    	/*Resetta il dizionario*/
    	model.clear();
    	
    }

    @FXML
    void initialize() {
        assert comboBoxLanguage != null : "fx:id=\"comboBoxLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrongWords != null : "fx:id=\"txtWrongWords\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        comboBoxLanguage.getItems().addAll("English","Italian");
        /*if per mettere subito la lingua English visibile nella scelta*/
        if(comboBoxLanguage.getItems().size()>0){
        	comboBoxLanguage.setValue(comboBoxLanguage.getItems().get(0));
        }
    }
    
    
}
