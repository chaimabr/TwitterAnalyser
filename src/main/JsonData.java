package main;

import java.io.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class JsonData {
	
	static String str;
	static JSONParser jsonParser = new JSONParser();
	
	//list count and text
	static List<String> listText = new ArrayList<String>();
	static List<Long> listRtCount = new LinkedList<Long>();
	static List<Long> listRtCountTrier = new LinkedList<Long>();
	static List<String> stopWords = Arrays.asList("i","a","its","so","about","than","other","more","an","and","are","as","at","be","by","com","de","en","for","from","how","in","is","it","la","of","on","or","that","the","this","to","was","what","when","where","who","will","with","und","the","www");  
	
	public static void main(String[] args) {
		
		try {
			// read the json file
			BufferedReader buffer = new BufferedReader(new FileReader("D:\\dataset\\Tweet_files\\5_5_2012_18_9.json"));
			
			FileWriter fileWrite = new FileWriter(new File("5_5_2012_18_9.txt"));
			BufferedWriter bufferWr = new BufferedWriter(fileWrite);
			
			while((str = buffer.readLine()) != null) 
			{				
			
				JSONObject jsonObject = (JSONObject) jsonParser.parse(str);
				
				// get text and store value
				listText.add((String)jsonObject.get("text"));
				//System.out.println("The text is: " + (String)jsonObject.get("text"));
				
				// get favorites count and store value
				
				listRtCount.add((Long)jsonObject.get("retweet_count"));
				listRtCountTrier.add((Long)jsonObject.get("retweet_count"));
				
			} 
			buffer.close(); 
			
			//sort the list
			Collections.sort(listRtCountTrier);
						
			//display top 2		
			
			for (int i=0;i<listRtCount.size();i++)
			{
				for (int j=listRtCountTrier.size()-1;j>listRtCountTrier.size()-20;j--)
				{
					if (listRtCount.get(i) == listRtCountTrier.get(j))
					{
						 String s= listText.get(i);
					     String[] words = s.split(" ");
					     words = s.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
					 	 ArrayList<String> keywords = new ArrayList<String>();

					     for(String word : words)
					     {
					         String wordCompare = word.toLowerCase();
					         if(!stopWords.contains(wordCompare) && !keywords.contains(wordCompare))
					         {
					             keywords.add(word);
					         }
					     }

					        for (String str : keywords){
					        	if (str.length()>3){
					        	bufferWr.write(str+" "); }
					        }
					        bufferWr.newLine();
					}
				}
			}
			bufferWr.close();
			fileWrite.close();
			File inputFile = new File("5_5_2012_18_9.txt");
			File tempFile = new File("myTempFile.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			int k=0;
			String previousline = reader.readLine();
			writer.write(previousline + System.getProperty("line.separator"));
			String currentLine;

			while(k==0 && (currentLine = reader.readLine()) != null) {
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(previousline)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			    k++;
			}
			writer.close(); 
			reader.close(); 
			boolean success = tempFile.renameTo(inputFile);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

}
