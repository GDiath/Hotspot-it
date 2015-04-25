import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;


public class Test {

	final static String apikey = "7762d5f003715e69354127a16746afd1"; //"3e4712535081394ccec11ce4533601c3";
	HashMap<String,PhotoEntry> myHashMap;

	public Test(){

		myHashMap = new HashMap<String, PhotoEntry>();
	}

	public static void main(String args[]) throws Exception{

		new Test().operate();
	}

	private void operate() throws IOException, ParseException, JSONException {

		File file = fileCreate("json1.txt");

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		for(int i=1; i<=16; i++){ // Uploaded from 1/1/14 to 4/7/14
			URL url= new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+apikey+"&min_upload_date=1%2F1%2F2014&max_upload_date=4%2F7%2F2014&bbox=23.557054%2C37.835065%2C23.891791%2C38.072234&content_type=1&extras=tags&per_page=250&page="+i+"&format=json&nojsoncallback=1");
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							yc.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			String jsonString = response.toString();

			Gson myGson = new Gson();
			Reply reply = myGson.fromJson(jsonString,Reply.class);
			for (Photo myPhoto: reply.photos.photo){
				myHashMap.put(myPhoto.id,new PhotoEntry());		//vazw ta id sto HashMap os Key
				myHashMap.get(myPhoto.id).setId(myPhoto.id);	//vazw ta id sto HashMap os Value
				if (myPhoto.tags != null){
					myHashMap.get(myPhoto.id).setKeywords(myPhoto.tags);
				}else {
					myHashMap.get(myPhoto.id).setKeywords("no tags");
				}
			}

			writeJSON(bw,jsonString);
			in.close();
		}
		bw.close();

		fw = new FileWriter(file.getAbsoluteFile(), true);
		bw = new BufferedWriter(fw);

		for(int i=1; i<=16; i++){ //Uploaded from 5/7/14 to 7/7/14
			URL url= new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+apikey+"&min_upload_date=5%2F7%2F2014&max_upload_date=7%2F7%2F2014&bbox=23.557054%2C37.835065%2C23.891791%2C38.072234&content_type=1&extras=tags&per_page=250&page="+i+"&format=json&nojsoncallback=1");
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							yc.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			String jsonString = response.toString();

			Gson myGson = new Gson();
			Reply reply = myGson.fromJson(jsonString,Reply.class);
			for (Photo myPhoto: reply.photos.photo){
				myHashMap.put(myPhoto.id,new PhotoEntry());
				myHashMap.get(myPhoto.id).setId(myPhoto.id);
				if (myPhoto.tags != null){
					myHashMap.get(myPhoto.id).setKeywords(myPhoto.tags);
				}else {
					myHashMap.get(myPhoto.id).setKeywords("no tags");
				}
			}

			writeJSON(bw,jsonString);
			in.close();
		}
		bw.close();

		File file2 = fileCreate("json2.txt");	// Dimiourgia 2ou file gia na grapsw to stringFormat pou tha valw stin Database.

		FileWriter fw2 = new FileWriter(file2.getAbsoluteFile());
		BufferedWriter bw2 = new BufferedWriter(fw2);

		for(String key: myHashMap.keySet()){
			URL url2= new URL("https://api.flickr.com/services/rest/?method=flickr.photos.geo.getLocation&api_key="+apikey+"&photo_id="+key+"&extras=tags&format=json&nojsoncallback=1");
			URLConnection yc2 = url2.openConnection();

			BufferedReader in2 = new BufferedReader(
					new InputStreamReader(
							yc2.getInputStream()));
			String inputLine2;
			StringBuffer response2 = new StringBuffer();

			while ((inputLine2 = in2.readLine()) != null) {
				response2.append(inputLine2);
			}
			String jsonString2 = response2.toString();

			Gson myGson2 = new Gson();
			Reply2 reply2 = myGson2.fromJson(jsonString2,Reply2.class);
			
			System.out.println(jsonString2);

				if (! reply2.stat.equals("ok")){
					in2.close();
					continue;
				}
					if (reply2.photo.location.latitude != null){
						myHashMap.get(key).setLat(reply2.photo.location.latitude);
					}else {
						myHashMap.get(key).setLat(0.0);
					}

					if (reply2.photo.location.longitude != null){
						myHashMap.get(key).setLon(reply2.photo.location.longitude);
					}else {
						myHashMap.get(key).setLon(0.0);
					}

					if (reply2.photo.location.neighbourhood != null){
						myHashMap.get(key).setNeighbourhood(reply2.photo.location.neighbourhood._content);
					}else {
						myHashMap.get(key).setNeighbourhood("Uknown Neighbourhood");
					}

					if (reply2.photo.location.locality != null){
						myHashMap.get(key).setLocality(reply2.photo.location.locality._content);	
					}else {
						myHashMap.get(key).setLocality("Uknown Locality");
					}
				
			
			in2.close();
		}

		String myFormat="";
		for (Map.Entry<String, PhotoEntry> myData : myHashMap.entrySet()){
			myFormat += myData.getValue();
		}

		writeJSON(bw2,myFormat);
		bw2.close();
	}


	private static File fileCreate(String s1) throws IOException {
		File file = new File("/Users/George/workspace/flickr/"+s1);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;	
	}


	public static void writeJSON(BufferedWriter bw, String jsonString) throws IOException{
		bw.write(jsonString+"\r\n"); 
	}


}
