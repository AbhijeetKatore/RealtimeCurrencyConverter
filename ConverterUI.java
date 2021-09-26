package CurrencyConverter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class ConverterUI extends converterMain  {

	protected void mainUI() {
		
		try {
		
		JFrame jf = new JFrame("Currency Converter");
		jf.setSize(600,250);
		
		
		welcome = new JLabel("Welcome to Real Time Currency Converter");
		base = new JTextField();
		toConvert = new JLabel();
		convert = new JButton("Convert");
		baseDown = new JComboBox(name);
		toConvertDown = new JComboBox(name);
		
		jf.setLayout(null);
		
		welcome.setBounds(170,20,300,20);
		base.setBounds(100, 140, 100, 20);
		toConvert.setBounds(380, 140, 300, 20);
		convert.setBounds(245, 140, 100, 20);;
		baseDown.setBounds(100, 80, 100, 20);
		toConvertDown.setBounds(380, 80, 100, 20);
		
		
		
		jf.add(welcome);
		jf.add(base);
		jf.add(toConvert);
		jf.add(convert);
		jf.add(baseDown);
		jf.add(toConvertDown);
		
	    convert.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    	            converter();
	    	        }  
	    	    });  
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}
		catch(NullPointerException e) {
			System.exit(0);
			
		}
		
	}
	

	public static void main(String[] args) {
		
		
		ConverterUI obj = new ConverterUI();
	
	 try {
	    	obj.getJsonData();
	    	
	       } catch (Exception e) {
	        e.printStackTrace();
	      }
	 		
	 obj.mainUI();
	}
}
