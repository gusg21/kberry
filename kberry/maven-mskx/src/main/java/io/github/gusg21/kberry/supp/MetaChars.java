package io.github.gusg21.kberry.supp;

public class MetaChars {
	public static String escapeMetaCharacters(String inputString){
	    final String[] metaCharacters = {"\\","^","$","{","}","[","]","(",")",".","*","+","?","|","<",">","-","&"};
	    String outputString="";
	    for (int i = 1; i < metaCharacters.length ; i++){
	        if(inputString.contains(metaCharacters[i])){
	            outputString = inputString.replace(metaCharacters[i],"\\"+metaCharacters[i]);
	            inputString = outputString;
	        }
	    }
	    return outputString;
	}
}
