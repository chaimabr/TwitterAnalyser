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
	static List<Long> listCount = new LinkedList<Long>();
	static List<Long> listCountTrier = new LinkedList<Long>();
	
	public static void main(String[] args) {
		
		try {
			// read the json file
			BufferedReader buffer = new BufferedReader(new FileReader("D:\\projeteurecom\\workspace\\TwitterAnalyser\\test1.json"));
			
			while((str = buffer.readLine()) != null) 
			{				
			
				JSONObject jsonObject = (JSONObject) jsonParser.parse(str);
				
				// get text and store value
				listText.add((String)jsonObject.get("text"));
				//System.out.println("The text is: " + (String)jsonObject.get("text"));
				
				// get favorites count and store value
				JSONObject user = (JSONObject)jsonObject.get("user");
				listCount.add((Long) user.get("favourites_count"));	
				listCountTrier.add((Long) user.get("favourites_count"));	
			} 
			buffer.close(); 
			
			//trier top 5
			Collections.sort(listCountTrier);
						
			//afficher les top 5		
			
			System.out.println("Top Five tweets according how much they were favorited are :");
					
			for (int i=0;i<listCount.size();i++)
			{
				for (int j=listCountTrier.size()-1;j>listCountTrier.size()-6;j--)
				{
					if (listCount.get(i) == listCountTrier.get(j))
					{
						System.out.println("Text du tweet " + (listCountTrier.size()-1-j) + " is : " + listText.get(i));
						System.out.println("number of favourites " + (listCountTrier.size()-1-j) + " is : " + listCount.get(i));
					}
				}
			}
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

}
