package it.polito.tdp.spellchecker.model;

import java.util.*;
import java.lang.Math;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
	private List<String> dizionario=new ArrayList<String>();
	//private List<RichWord> dizionario=new ArrayList<RichWord>();
	
	
	
	public void loadDictionary(String language){
		try { 
			 FileReader fr = new FileReader("rsc/"+language+".txt");
			 BufferedReader br = new BufferedReader(fr);
			 String word;
			 while ((word = br.readLine()) != null) {  
				 /*Aggiungere parola alla struttura dati dizionario*/
				 /*true perchè la parola sarà sempre corretta*/
				 //RichWord temp=new RichWord(word.toLowerCase(),true);
				 //dizionario.add(temp);
				 dizionario.add(word.toLowerCase());
			 }
		 br.close(); 			 
		 } catch (IOException e){ 
			 System.out.println("Errore nella lettura del file");  
		 } 		 
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> result= new LinkedList<RichWord>();
		for(String s:inputTextList){
			/*RichWord temp= new RichWord(s,true);
			for(RichWord r: dizionario){
				if(r.equals(temp)){
					temp.setCorretto(true);
					result.add(temp);
					flag=true;
				}
			}
			if(flag==false){
				temp.setCorretto(false);
				result.add(temp);
			}*/
			
			
			/*for(String r: dizionario){
				if(r.compareTo(s)==0){
					result.add(new RichWord(s,true));
					flag=true;
				}
			}
			if(flag==false){
				result.add(new RichWord(s,false));
			}*/
			
			boolean flag=false;
			int low=0;
			int high=dizionario.size()-1;
			int mid;
			
			while(low<high || low==high){
				mid=(int) Math.floor((low+high)/2);
				if(dizionario.get(mid).compareTo(s)==0){
					/*parola trovata e devo uscire subito dal ciclo*/
					/*per non andare in overloop*/
					flag=true;
					break;
				}else if(dizionario.get(mid).compareTo(s)<0){
					/*s segue alfabeticamente la parola con indice mid*/
					/*quindi scarto la parte di dizionario che precede il mid*/
					low=mid+1;
				}else if(dizionario.get(mid).compareTo(s)>0){
					/*s precede alfabeticamente la parola con indice mid*/
					/*quindi scarto la parte di dizionario che segue il mid*/
					high=mid-1;
				}
			}
			if(flag==false){
			/*parola mai trovata facendo scorrere il dizionario e quindi sbagliata*/
				if(dizionario.indexOf(s)==-1){
					result.add(new RichWord(s,false));
				}
			}else{
				result.add(new RichWord(s,true));
			}
		}
		return result;
	}
	
	
	
	/**
	 * Resettare il dizionario serve perchè se non lo facessi 
	 * la seconda volta che lancio il programma avrei due dizionari caricati
	 */
	public void clear() {
		dizionario.clear();
	}
	
	public int firstIndexList(String parola){
		return dizionario.indexOf(parola);
	}
	
	public int listLenght(){
		return dizionario.size();
	}
	
}
