/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.daljine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


/**
 *
 * @author gigabyte
 */
public class CalculatorDaljine {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		CalculatorDaljine http = new CalculatorDaljine();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
		
		//System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();

	}

	// HTTP GET request
	private void sendGet() throws Exception {

                String prviGrad = "New York, NY";
                String drugiGrad = "Montgomery, OH";
                
                //http://www.travelmath.com/drive-distance/from/Chicago,+IL/to/Miami,+FL
                
                prviGrad = URLEncoder.encode(prviGrad, "UTF-8");
                drugiGrad = URLEncoder.encode(drugiGrad, "UTF-8");
		String url = "http://www.travelmath.com/drive-distance/from/"+ prviGrad + "/to/"+ drugiGrad;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		String rezultat =response.toString();
                  // List<String> ret = new ArrayList<String>((text.length() + size - 1) / size);

                 String[] niz = rezultat.split("drivedist");
                 
                 int daljina = 0;
                 
                  for (int s = 0; s < niz.length; s++) {
                    String deo = niz[s].substring(0, 10);
                    
                    char[] slova = deo.toCharArray();
                    String brojevi = "";
                    
                      for (int i = 0; i < slova.length; i++) {
                          if (Character.isDigit(slova[i])) {
                            brojevi+= Character.toString(slova[i]);  
                          }
                      }
                    
                      try {
                          
                          daljina = Integer.parseInt(brojevi);
                          
                      } catch (Exception e) {
                          System.out.print("greska ");
                      }
                      System.out.println(String.valueOf(daljina));
                      
                      
                    }

	}
	
	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());

	}

}