package main;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.*;
import org.json.simple.parser.*;

public class JsonData {
	
	static String str;
	static JSONParser jsonParser = new JSONParser();
	
	//list count and text
	static List<String> listText = new ArrayList<String>();
	static List<Long> listRtCount = new LinkedList<Long>();
	static List<Long> listRtCountTrier = new LinkedList<Long>();
	static List<String> stopWords ;
	
	
	public static void main(String[] args) {
		
		try {
			 BufferedReader sw1 = new BufferedReader(new FileReader(""+"stopwords.txt"));
		        while((str = sw1.readLine()) != null) 
				{	
					stopWords.add(str);
				}
			// read the json file
			BufferedReader buffer = new BufferedReader(new FileReader("D:\\dataset\\Tweet_files\\5_5_2012_16_16.json"));
			
			FileWriter fileWrite = new FileWriter(new File("5_5_2012_16_16.txt"));
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
			int l=0;
			for (int i=0;i<listRtCount.size();i++)
			{
				for (int j=listRtCountTrier.size()-1;j>listRtCountTrier.size()-20;j--)
				{
					if (listRtCount.get(i) == listRtCountTrier.get(j))
					{
						l++;
						 String s= listText.get(i);
						 String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
	                     Pattern pa = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
	                     Matcher m = pa.matcher(s);
	                     int f = 0;
	                     while (m.find()) {
	                         s = s.replaceAll(m.group(f),"").trim();
	                         f++;
	                     }
					     String[] words = s.split(" ");
					     words = s.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
					 	 ArrayList<String> keywords = new ArrayList<String>();

					     for(String word : words)
					     {
					         if((!(word.charAt(0)=='@')) && ((word.charAt(0)>='A' && word.charAt(0)<='Z') || (!stopWords.contains(word.toLowerCase())) && !keywords.contains(word.toLowerCase())))
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
			File inputFile = new File("5_5_2012_16_16.txt");
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
