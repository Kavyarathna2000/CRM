package practice;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jdk.internal.org.jline.reader.Parser;

public class ReadDataFromJsonTest 
{
	public static void main(String[] args) throws IOException, ParseException 
	{
		//parse Json Physical file in to java Object using JsonParse class
		FileReader flir = new FileReader("./configAppData/JsonData.json");
		JSONParser parser=new JSONParser();
		Object obj = parser.parse(flir);
		
		//convert java object into json object using downcasting
		JSONObject map=(JSONObject)obj;
		//get the value from json file using key
		System.out.println(map.get("url"));
		System.out.println(map.get("browser"));
		System.out.println(map.get("un"));
		System.out.println(map.get("pwd"));
		System.out.println(map.get("timeout"));
		System.out.println(map.get("em"));
		
	}
}
