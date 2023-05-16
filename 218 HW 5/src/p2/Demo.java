package p2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class Demo {
	public static void lyricsToList(LinkedList<LinkedList<String>> ref, String[] words) {
		LinkedList<String> usedWords = new LinkedList<>();
		
		for(int i = 0; i < words.length - 1; i++){
			if(usedWords.contains(words[i])) {
				continue;
			}
			LinkedList<String> tempList = new LinkedList<String>();
			tempList.add(words[i]);
			ref.add(tempList);
			usedWords.add(words[i]);
		}
	}
	
	public static String fileLoader() {
		try {
		StringBuilder returnValue = new StringBuilder();
		File file = new File("src/blowingInTheWind.txt");
		FileReader re = new FileReader(file);
		BufferedReader br = new BufferedReader(re);
		int i;
		while(br.ready()) {
			returnValue.append(br.readLine() + " ");
		}   
		return returnValue.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "load error";
	}
	
	public static void main(String[] args) {
		LinkedList<LinkedList<String>> parentList = new LinkedList<LinkedList<String>>();
		String test = fileLoader();
		String[] testArray = test.trim().split("\\s+");
		lyricsToList(parentList, testArray);
		for(int i = 0; i < testArray.length - 1; i++) {
			for (int j = 0; j < parentList.size(); j++) {
				if(parentList.get(j).get(0).equals(testArray[i])) {
					parentList.get(j).add(testArray[i+1]);
					continue;
				}
			}
		}
		System.out.println(parentList.toString());
	}
	
	public static LinkedList<LinkedList<String>> getList(String dir){
		LinkedList<LinkedList<String>> parentList = new LinkedList<LinkedList<String>>();
		String test = fileLoader(dir);
		String[] testArray = test.trim().split("\\s+");
		lyricsToList(parentList, testArray);
		for(int w = 0; w < testArray.length - 1; w++) {
			for (int i = 0; i < parentList.size(); i++) {
				if(parentList.get(i).get(0).equals(testArray[w])) {
					parentList.get(i).add(testArray[w+1]);
					continue;
				}
			}
		}
		return parentList;
	}
	
	public static LinkedList<LinkedList<String>> getList(File dir){
		LinkedList<LinkedList<String>> parentList = new LinkedList<LinkedList<String>>();
		String test = fileLoader(dir);
		String[] testArray = test.trim().split("\\s+");
		lyricsToList(parentList, testArray);
		for(int i = 0; i < testArray.length - 1; i++) {
			for (int j = 0; j < parentList.size(); j++) {
				if(parentList.get(j).get(0).equals(testArray[i])) {
					parentList.get(j).add(testArray[i+1]);
					continue;
				}
			}
		}
		return parentList;
	}
	
	public static String fileLoader(File file) {
		try {
			StringBuilder returnValue = new StringBuilder();
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while(br.ready()) {
				returnValue.append(br.readLine() + " ");
			}   
			return returnValue.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "load error";
	}
	
	public static String fileLoader(String dir) {
		try {
			StringBuilder returnValue = new StringBuilder();
			File file = new File("src/blowingInTheWind.txt");
			FileReader re = new FileReader(file);
			BufferedReader br = new BufferedReader(re);
			while(br.ready()) {
				returnValue.append(br.readLine() + " ");
			}    
			return returnValue.toString();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return "load error";
	}
}