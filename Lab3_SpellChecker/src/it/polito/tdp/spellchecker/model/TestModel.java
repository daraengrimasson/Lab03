package it.polito.tdp.spellchecker.model;

public class TestModel {

	public static void main(String[] args) {
		Dictionary d=new Dictionary();
		d.loadDictionary("Italian");
		System.out.println("Primo indice dizionario italiano: "+d.firstIndexList("a"));
		System.out.println("Lunghezza dizionario italiano: "+d.listLenght());

	}

}
