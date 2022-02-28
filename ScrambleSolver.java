/*
 * ScrambleSolver.java
 * 
 * Copyright 2022 dfmaaa1 <dfmaaa1@dfmaaa1-dell>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class ScrambleSolver{
	static class Word{
	 String id=" ";
	 ArrayList<String> possible=new ArrayList<String>();
	 public Word(String word){
      this.id=word;
	 }
	}
	private static boolean checkForAnagram(String plainText, String wordToCheckFor){
	  if(plainText.length()!=wordToCheckFor.length()){
		 return false;
	  }
	  else{
	   boolean exists=false;
	   for(int x=0;x<plainText.length();x++){
		   for(int i=0;i<plainText.length();i++){
			 if(wordToCheckFor.charAt(x)==plainText.charAt(i)){
				 exists=true;
			 }
		   }
		 if(exists==false){
			 return false;
		 }
		 else{
		   exists=false;
		 }  
	   }
	  }
	  return true;
	}
	private static String[] read(File f) throws IOException{
        ArrayList<String> toBeConverted=new ArrayList<String>();
        try (FileReader fileStream = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileStream)) {
        String line = null;
        while ((line = bufferedReader.readLine())!= null) {
             toBeConverted.add(line);
           }
         }
         return convertArrayListToArray(toBeConverted);
        }
    private static String[] convertArrayListToArray(ArrayList<String> a){
	 String[] returnThis=new String[a.size()];
	 for(int x=0;x<returnThis.length;x++){
		 returnThis[x]=a.get(x);
	 }
	 return returnThis;
	}
	private static Word[] solve(String plainText, File a, int max) throws IOException{
	 System.out.println("Words: ");
	 for(String l : plainText.split(" ")){
		 System.out.print(l+" ");
	 }
	 System.out.println(" ");
	 Word[] returnThisObject=convertStringArrayToWordArray(plainText.split(" "));
	 FileReader rd=new FileReader(a);
	 BufferedReader br=new BufferedReader(rd);
	 String ln=null;
	 while((ln=br.readLine())!=null){
	  if(checkIfAllMaxTrue(returnThisObject,max)){
		  return returnThisObject;
	  }
	  else{
		for(Word oneWord : returnThisObject){
			if(oneWord.possible.size()<max){
			if(checkForAnagram(oneWord.id,ln)){
				oneWord.possible.add(ln);
			}
		  }
		}
	  }
	 }
	 return returnThisObject;
	}
	private static boolean checkIfAllMaxTrue(Word[] a, int max){
	 for(Word n : a){
		if(n.possible.size()!=max){
			return false;
		}
	 }
	 return true;
	}
	private static void display(Word[] a){
		for(Word n : a){
			System.out.println("Word: " + n.id);
			System.out.print("Possible words: ");
			for(int x=0;x<n.possible.size();x++){
				System.out.print(n.possible.get(x) + " ");
			}
			System.out.println(" ");
		}
	}
	private static Word[] convertStringArrayToWordArray(String[] words){
		Word[] a=new Word[words.length];
		for(int x=0;x<a.length;x++){
			a[x]=new Word(words[x]);
		}
		return a;
	}
	public static void main (String[] args) throws IOException{
	 System.out.println("Welcome to the Scramble Solver 1.0!");
	 System.out.println("Please enter an input file to read words from.");
	 System.out.println("The words in the file have to be real, not scrambled.");
	 Scanner input=new Scanner(System.in);
	 String inputFile=input.nextLine();
	 String[] words=read(new File(inputFile));
	 System.out.println("Enter max possible words for one scrambled word(this will affect the speed of the calculation)");
	 int max=Integer.parseInt(input.nextLine());
	 System.out.println("Enter the scrambled sentence/text");
	 String text=input.nextLine();
	 display(solve(text,new File(inputFile),max));
	}
}
