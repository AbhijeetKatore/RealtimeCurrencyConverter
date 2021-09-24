package CurrencyConverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import java.lang.NumberFormatException;
import java.net.MalformedURLException;
import java.io.IOException;

public class converterMain  {
	converterMain obj;
	JSONObject jobj ;
	JSONObject Curr ;
	protected String name[];
	protected String baseSelected="";
	protected String toConSelect="";
	protected float amount;
	JLabel welcome ;
	JTextField base;
	JLabel toConvert;
	JButton convert;
	JComboBox baseDown;
	JComboBox toConvertDown; 
	HttpURLConnection con;

	protected void getJsonData() throws Exception {
		 try {
			 String url = "https://api.exchangerate-api.com/v4/latest/USD";
			 URL obj = new URL(url);
			 con = (HttpURLConnection) obj.openConnection();
			 con.connect();
	    	    
			 BufferedReader in = new BufferedReader(
	    	    new InputStreamReader(con.getInputStream()));
	    	    String inputLine;
	    	    
	    	    StringBuffer response = new StringBuffer();
	    	    while ((inputLine = in.readLine()) != null) {
	    	    	response.append(inputLine);
	    	    }
	    	    in.close();
	    	    
	    	    jobj = new JSONObject(response.toString());
	    	    Curr = new JSONObject(jobj.get("rates").toString());
	    	    name = JSONObject.getNames(Curr);
	    	    Arrays.sort(name);
	         	
	      } catch (MalformedURLException e) {
	    	  	JFrame opF = new JFrame("No Internet");
	    	  	JOptionPane.showMessageDialog(opF, "Internet is not Avaialable");
	      }
		 	catch (IOException e) {
		 		JFrame opF = new JFrame("No Internet");
	    	  	JOptionPane.showMessageDialog(opF, "Internet is not Avaialable");
	    	  	
	    	  	
		 	}

	}

	

	
	
	protected void converter()  {
		
		baseSelected = (String) baseDown.getSelectedItem();
	    toConSelect = (String) toConvertDown.getSelectedItem();
	    int ct=0;
	    
	    try {
	    	String am = base.getText();
	    	
	    	try {
	    	 Float.parseFloat(am);
	    	
	    	}
	    	catch(NumberFormatException e)
	    	{
	    		toConvert.setText("Enter Amount in Numerics ");
	    		ct+=1;
	    	}
	    	
	    	
	    	if (am.isEmpty() || am.contains(" ") || ct>0){
				toConvert.setText("Enter Numerics Value");
				}
	    	
	    	else {
			String bv = Curr.get(baseSelected).toString();
			String cv = Curr.get(toConSelect).toString();
			
			
			float baseValue = Float.valueOf(bv);
			float conValue = Float.valueOf(cv);
			float reqAmount = Float.valueOf(am);			
			
			if (baseSelected != "USD"){
				amount = reqAmount / baseValue ;
				}

			amount = amount * conValue;
			toConvert.setText(String.valueOf(amount));
			}
			
			

		}catch (JSONException e) {
			
			e.printStackTrace();
		}
	}
}