package sk.stuba.fei.feidroid.feidroidbeta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.Editable;
import android.util.Xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xmlFile extends Activity{

	public void writeData(Context context){
	
		final String xmlFile="userData";
		try {
		FileOutputStream fileos=context.openFileOutput(xmlFile, 0);
		XmlSerializer xmlSerializer = Xml.newSerializer();              
		StringWriter writer = new StringWriter();
		xmlSerializer.setOutput(writer);
		xmlSerializer.startDocument("UTF-8",true);
		xmlSerializer.startTag(null, "FEIdroid");
		
		xmlSerializer.startTag(null, "group");
		xmlSerializer.attribute(null, "name", "Hry");					
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.VIBRATE");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.FLASHLIGHT");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.SET_ORIENTATION");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.PLAY_AUDIO");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.BLUETOOTH");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.INTERNET");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.WAKE_LOCK");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "blackList");			
				xmlSerializer.text("android.permission.POST_NOTIFICATION");
			xmlSerializer.endTag(null,"blackList");
			xmlSerializer.startTag(null, "blackList");			
				xmlSerializer.text("android.permission.CAMERA");
			xmlSerializer.endTag(null,"blackList");
			xmlSerializer.startTag(null, "blackList");			
				xmlSerializer.text("android.permission.WRITE_EXTERNAL_STORAGE");
			xmlSerializer.endTag(null,"blackList");
		xmlSerializer.endTag(null,"group");		
		
		xmlSerializer.startTag(null, "group");
		xmlSerializer.attribute(null, "name", "Multimédia");					
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.CAMERA");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.MEDIA_CONTENT_CONTROL");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.SET_WALLPAPPER");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.RECORD_AUDIO");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.PLAY_AUDIO");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.WAKE_LOCK");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "blackList");			
				xmlSerializer.text("android.permission.SEND_SMS");
			xmlSerializer.endTag(null,"blackList");
			xmlSerializer.startTag(null, "blackList");			
				xmlSerializer.text("android.permission. POST_NOTIFICATION");
			xmlSerializer.endTag(null,"blackList");
			xmlSerializer.startTag(null, "blackList");			
				xmlSerializer.text("android.permission.WRITE_EXTERNAL_STORAGE");
			xmlSerializer.endTag(null,"blackList");
			xmlSerializer.startTag(null, "blackList");			
				xmlSerializer.text("android.permission.READ_EXTERNAL_STORAGE");
			xmlSerializer.endTag(null,"blackList");
		xmlSerializer.endTag(null,"group");	
		
		xmlSerializer.startTag(null, "group");
		xmlSerializer.attribute(null, "name", "Sociálne siete");					
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.INTERNET");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.VIBRATE");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.POST_NOTIFICATION");
			xmlSerializer.endTag(null,"whiteList");			
			xmlSerializer.startTag(null, "blackList");			
				xmlSerializer.text("android.permission.WRITE_EXTERNAL_STORAGE");
			xmlSerializer.endTag(null,"blackList");
			xmlSerializer.startTag(null, "blackList");			
				xmlSerializer.text("android.permission.READ_EXTERNAL_STORAGE");
			xmlSerializer.endTag(null,"blackList");
		xmlSerializer.endTag(null,"group");	
		
		xmlSerializer.startTag(null, "group");
		xmlSerializer.attribute(null, "name", "Bankové operácie");					
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.INTERNET");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.CAMERA");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.SEND_SMS");
			xmlSerializer.endTag(null,"whiteList");		
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.READ_SMS");
			xmlSerializer.endTag(null,"whiteList");	
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.WAKE_LOCK");
			xmlSerializer.endTag(null,"whiteList");	
			xmlSerializer.startTag(null, "whiteList");			
				xmlSerializer.text("android.permission.CALL_PHONE");
			xmlSerializer.endTag(null,"whiteList");	
		xmlSerializer.endTag(null,"group");	
		
		xmlSerializer.startTag(null, "group");
		xmlSerializer.attribute(null, "name", "Systém");			
		xmlSerializer.endTag(null,"group");	
		/*
			xmlSerializer.startTag(null, "group");
			xmlSerializer.attribute(null, "name", "média");	
				xmlSerializer.startTag(null, "appName");			
					xmlSerializer.text("com.example.camera");
				xmlSerializer.endTag(null,"appName");		
				xmlSerializer.startTag(null, "whiteList");			
					xmlSerializer.text("android.permission.CAMERA");
				xmlSerializer.endTag(null,"whiteList");
				xmlSerializer.startTag(null, "whiteList");			
					xmlSerializer.text("android.permission.INTERNET");
			xmlSerializer.endTag(null,"whiteList");
			xmlSerializer.endTag(null,"group");		
			
			xmlSerializer.startTag(null, "group");
			xmlSerializer.attribute(null, "name", "systém");	
				xmlSerializer.startTag(null, "appName");			
					xmlSerializer.text("com.endomondo.android");
				xmlSerializer.endTag(null,"appName");			
				xmlSerializer.startTag(null, "whiteList");			
					xmlSerializer.text("android.permission.INTERNET");
				xmlSerializer.endTag(null,"whiteList");
				xmlSerializer.startTag(null, "whiteList");			
					xmlSerializer.text("android.permission.READ_EXTERNAL_STORAGE");
				xmlSerializer.endTag(null,"whiteList");
				xmlSerializer.startTag(null, "blackList");			
					xmlSerializer.text("android.permission.WRITE_EXTERNAL_STORAGE");
				xmlSerializer.endTag(null,"blackList");
			xmlSerializer.endTag(null,"group");				
		*/
			xmlSerializer.startTag(null, "group");
			xmlSerializer.attribute(null, "name", "ostatné");						
			xmlSerializer.endTag(null,"group");		
		
		xmlSerializer.endTag(null, "FEIdroid");
		xmlSerializer.endDocument();
		xmlSerializer.flush();
		String dataWrite=writer.toString();
		fileos.write(dataWrite.getBytes());
		fileos.close();
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}
	
	public void addXMLTag(String value,String type, String tag, Context context) throws IllegalArgumentException, IllegalStateException, IOException{
		final String filepath="userData";	
		
		XmlSerializer xmlSerializer = Xml.newSerializer();              
		StringWriter writer = new StringWriter();
		xmlSerializer.setOutput(writer);
		
		 String data = null;
			try {
             FileInputStream fis = context.openFileInput(filepath);
             InputStreamReader isr = new InputStreamReader(fis);
             char[] inputBuffer = new char[fis.available()];
             isr.read(inputBuffer);
             data = new String(inputBuffer);
             isr.close();
             fis.close();
             } catch (FileNotFoundException e3) {
             // TODO Auto-generated catch block
                 e3.printStackTrace();
             } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
         XmlPullParserFactory factory = null;
         try {
             factory = XmlPullParserFactory.newInstance();
             } catch (XmlPullParserException e2) {
             // TODO Auto-generated catch block
             e2.printStackTrace();
             }
         factory.setNamespaceAware(true);
         XmlPullParser xpp = null;
         try {
             xpp = factory.newPullParser();
             } catch (XmlPullParserException e2) {
             // TODO Auto-generated catch block
             e2.printStackTrace();
             }
         try{
             xpp.setInput( new StringReader (data) );
             } catch (XmlPullParserException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
             }
          int eventType = 0;
          try{
              eventType = xpp.getEventType();
             } catch (XmlPullParserException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
             }
          int i;
          while (eventType != XmlPullParser.END_DOCUMENT){
              if(eventType == XmlPullParser.START_DOCUMENT) {
            	 
              }else if(eventType == XmlPullParser.START_TAG) {
            	  xmlSerializer.startTag(null, xpp.getName());
            	  
            	  if(type.compareTo("newGroup")==0)
	            	  if(xpp.getName().compareTo("FEIdroid")==0){
	            		xmlSerializer.startTag(null, "group");
	          			xmlSerializer.attribute(null, "name", value);						
	          			xmlSerializer.endTag(null,"group");	            		  
	            	  }  	  
            	  if(xpp.getAttributeCount()>0){
            		for(i=0; i<xpp.getAttributeCount();i++){
            			xmlSerializer.attribute(null,xpp.getAttributeName(i), xpp.getAttributeValue(i));	            		
            		}            	
            	  }
            	  if(type.compareTo("App")==0)
	            	  if(xpp.getName().compareTo("group")==0){
	            		  if(xpp.getAttributeValue(0).compareTo(tag)==0){
	            			  xmlSerializer.startTag(null, "appName");			
	            			  	xmlSerializer.text(value);
	            			  xmlSerializer.endTag(null,"appName");            			  
	            		  }           		  
	            	  }
            	  if(type.compareTo("AddWL")==0)
	            	  if(xpp.getName().compareTo("group")==0){
	            		  if(xpp.getAttributeValue(0).compareTo(tag)==0){
	            			  xmlSerializer.startTag(null, "whiteList");			
	            			  	xmlSerializer.text(value);
	            			  xmlSerializer.endTag(null,"whiteList");            			  
	            		  }           		  
	            	  }
            	  if(type.compareTo("AddBL")==0)
	            	  if(xpp.getName().compareTo("group")==0){
	            		  if(xpp.getAttributeValue(0).compareTo(tag)==0){
	            			  xmlSerializer.startTag(null, "blackList");			
	            			  	xmlSerializer.text(value);
	            			  xmlSerializer.endTag(null,"blackList");            			  
	            		  }           		  
	            	  }
               
              }else if(eventType == XmlPullParser.END_TAG) {
            	  xmlSerializer.endTag(null, xpp.getName());
              }else if(eventType == XmlPullParser.TEXT) {
            	  
            	  xmlSerializer.text(xpp.getText());   
                 
              }
              try{
                  eventType = xpp.next();
             }catch (XmlPullParserException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             }
          }		             

		xmlSerializer.endDocument();
		xmlSerializer.flush();
		String dataWrite=writer.toString();
		FileOutputStream fileos=context.openFileOutput(filepath, 0);
		fileos.write(dataWrite.getBytes());
		fileos.close();
		
	
	}
	
	public void delXMLTag(String value,String type, String tag, Context context) throws IllegalArgumentException, IllegalStateException, IOException{
		final String filepath="userData";	
		
		int delete = 0;
		
		XmlSerializer xmlSerializer = Xml.newSerializer();              
		StringWriter writer = new StringWriter();
		xmlSerializer.setOutput(writer);
		
		 String data = null;
			try {
             FileInputStream fis = context.openFileInput(filepath);
             InputStreamReader isr = new InputStreamReader(fis);
             char[] inputBuffer = new char[fis.available()];
             isr.read(inputBuffer);
             data = new String(inputBuffer);
             isr.close();
             fis.close();
             } catch (FileNotFoundException e3) {
             // TODO Auto-generated catch block
                 e3.printStackTrace();
             } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
         XmlPullParserFactory factory = null;
         try {
             factory = XmlPullParserFactory.newInstance();
             } catch (XmlPullParserException e2) {
             // TODO Auto-generated catch block
             e2.printStackTrace();
             }
         factory.setNamespaceAware(true);
         XmlPullParser xpp = null;
         try {
             xpp = factory.newPullParser();
             } catch (XmlPullParserException e2) {
             // TODO Auto-generated catch block
             e2.printStackTrace();
             }
         try{
             xpp.setInput( new StringReader (data) );
             } catch (XmlPullParserException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
             }
          int eventType = 0;
          try{
              eventType = xpp.getEventType();
             } catch (XmlPullParserException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
             }
          int i;
          while (eventType != XmlPullParser.END_DOCUMENT){
              if(eventType == XmlPullParser.START_DOCUMENT) {
            	 
              }else if(eventType == XmlPullParser.START_TAG) {            	  
            	  
            	  if(type.compareTo("delGroup")==0){
            		  if(xpp.getAttributeCount()>0)
                  		for(i=0; i<xpp.getAttributeCount();i++){
                  			if(xpp.getAttributeName(i).compareTo("name")==0 && xpp.getAttributeValue(i).compareTo(value)==0){
    	            		    delete = 1;    
    	            		    break;
    	            	  }            	  	            		
                  		}
	            	  

            	 }
            	  if(delete!=1)
            			  xmlSerializer.startTag(null, xpp.getName());
            	  
            	  if(delete!=1)
	            	  if(xpp.getAttributeCount()>0){
	            		for(i=0; i<xpp.getAttributeCount();i++){
	            			xmlSerializer.attribute(null,xpp.getAttributeName(i), xpp.getAttributeValue(i));	            		
	            		}
            	  }
               
              }else if(eventType == XmlPullParser.END_TAG) { 	  
            	  if(delete!=1)
            		  xmlSerializer.endTag(null, xpp.getName());
            	  if(xpp.getName().compareTo("group")==0 && delete==1)
            		  delete = 0; 
              }else if(eventType == XmlPullParser.TEXT) {    
            	  if(delete!=1)            		  
            		  if(type.compareTo("delApp")==0){
                		 if(xpp.getText().compareTo(value)==0){        	            		    
        	            }   
                		else
                		 xmlSerializer.text(xpp.getText());
            		  }
            		  else if(type.compareTo("delWL")==0){
                    		 if(xpp.getText().compareTo(value)==0){        	            		    
             	            }  
                    		 else
                        		 xmlSerializer.text(xpp.getText());
            		  }
                    else if(type.compareTo("delBL")==0){
                        	if(xpp.getText().compareTo(value)==0){        	            		    
                 	       } 
                        	else
                       		 xmlSerializer.text(xpp.getText());
                    }
                	else
                		xmlSerializer.text(xpp.getText());
                         	  
              }
              try{
                  eventType = xpp.next();
             }catch (XmlPullParserException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             }
          }		             

		xmlSerializer.endDocument();
		xmlSerializer.flush();
		String dataWrite=writer.toString();
		FileOutputStream fileos=context.openFileOutput(filepath, 0);
		fileos.write(dataWrite.getBytes());
		fileos.close();
		
		
		
	
	}
	
	public List<String> readGroup(Context context, boolean del){
		final String xmlFile="userData";
		ArrayList<String> userData = new ArrayList<String>();
		            String data = null;
					try {
		                FileInputStream fis = context.openFileInput(xmlFile);
		                InputStreamReader isr = new InputStreamReader(fis);
		                char[] inputBuffer = new char[fis.available()];
		                isr.read(inputBuffer);
		                data = new String(inputBuffer);
		                isr.close();
		                fis.close();
		                } catch (FileNotFoundException e3) {
		                // TODO Auto-generated catch block
		                    e3.printStackTrace();
		                } catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            XmlPullParserFactory factory = null;
		            try {
		                factory = XmlPullParserFactory.newInstance();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            factory.setNamespaceAware(true);
		            XmlPullParser xpp = null;
		            try {
		                xpp = factory.newPullParser();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            try{
		                xpp.setInput( new StringReader (data) );
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             int eventType = 0;
		             try{
		                 eventType = xpp.getEventType();
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             while (eventType != XmlPullParser.END_DOCUMENT){
		                 if(eventType == XmlPullParser.START_DOCUMENT) {
		                    
		                 }else if(eventType == XmlPullParser.START_TAG) {
		                	if(xpp.getAttributeCount()!=0)	
		                		if(xpp.getAttributeName(0).compareTo("name")==0)
		                			if(!(del && xpp.getAttributeValue(0).compareTo("ostatné")==0))
		                				userData.add(xpp.getAttributeValue(0));
		                  
		                 }else if(eventType == XmlPullParser.END_TAG) {
		                    
		                 }else if(eventType == XmlPullParser.TEXT) {                   
		                	// userData.add(xpp.getText());   
		                    
		                 }
		                 try{
		                     eventType = xpp.next();
		                }catch (XmlPullParserException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		                }
		             }	
		             
		        Collections.sort(userData);
		        return userData;		
	}
	
	public List<String> readApp(Context context, String app){
		final String xmlFile="userData";
		boolean getDataApp2=false;
		boolean getDataApp=false;
		ArrayList<String> userData = new ArrayList<String>();
		            String data = null;
					try {
		                FileInputStream fis = context.openFileInput(xmlFile);
		                InputStreamReader isr = new InputStreamReader(fis);
		                char[] inputBuffer = new char[fis.available()];
		                isr.read(inputBuffer);
		                data = new String(inputBuffer);
		                isr.close();
		                fis.close();
		                } catch (FileNotFoundException e3) {
		                // TODO Auto-generated catch block
		                    e3.printStackTrace();
		                } catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            XmlPullParserFactory factory = null;
		            try {
		                factory = XmlPullParserFactory.newInstance();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            factory.setNamespaceAware(true);
		            XmlPullParser xpp = null;
		            try {
		                xpp = factory.newPullParser();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            try{
		                xpp.setInput( new StringReader (data) );
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             int eventType = 0;
		             try{
		                 eventType = xpp.getEventType();
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             while (eventType != XmlPullParser.END_DOCUMENT){
		                 if(eventType == XmlPullParser.START_DOCUMENT) {
		                    
		                 }else if(eventType == XmlPullParser.START_TAG) {
		                	if(xpp.getName().compareTo("group")==0){
		                		if(xpp.getAttributeCount()==1)
		                			if(xpp.getAttributeName(0).compareTo("name")==0)
		                				if(xpp.getAttributeValue(0).compareTo(app)==0)
		                					getDataApp = true;
		                	}
		                	else{
		                		if(xpp.getName().compareTo("appName")==0)
		                			getDataApp2 = true;
		                	}
		                	
		                 }else if(eventType == XmlPullParser.END_TAG) {		                	 
		                	 if(xpp.getName().compareTo("group")==0 && getDataApp) {
		                		 getDataApp =false;
		                		 break;
		                	 }
		                	 else{
			                	if(xpp.getName().compareTo("appName")==0)
			                		getDataApp2 = false;
			                }
		                 }else if(eventType == XmlPullParser.TEXT) {                   
		                	if(getDataApp && getDataApp2){		                		
		                		
		                		final PackageManager pm = context.getPackageManager();
		                		ApplicationInfo ai;
		                		try {
		                		    ai = pm.getApplicationInfo(xpp.getText(), 0);
		                		} catch (final NameNotFoundException e) {
		                		    ai = null;
		                		}
		                		final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) :"neznámy");
		                				                	
		                		 userData.add(applicationName+System.getProperty("line.separator")+xpp.getText()); 		
		                	}
		                 }
		                 try{
		                     eventType = xpp.next();
		                }catch (XmlPullParserException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		                }
		             }		    

		        Collections.sort(userData);
		        return userData;		
	}
	
	public int isInGroup(Context context, String app){
		final String xmlFile="userData";
		boolean getDataApp=false;		
	
		
		            String data = null;
					try {
		                FileInputStream fis = context.openFileInput(xmlFile);
		                InputStreamReader isr = new InputStreamReader(fis);
		                char[] inputBuffer = new char[fis.available()];
		                isr.read(inputBuffer);
		                data = new String(inputBuffer);
		                isr.close();
		                fis.close();
		                } catch (FileNotFoundException e3) {
		                // TODO Auto-generated catch block
		                    e3.printStackTrace();
		                } catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            XmlPullParserFactory factory = null;
		            try {
		                factory = XmlPullParserFactory.newInstance();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            factory.setNamespaceAware(true);
		            XmlPullParser xpp = null;
		            try {
		                xpp = factory.newPullParser();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            try{
		                xpp.setInput( new StringReader (data) );
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             int eventType = 0;
		             try{
		                 eventType = xpp.getEventType();
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             while (eventType != XmlPullParser.END_DOCUMENT){
		                 if(eventType == XmlPullParser.START_DOCUMENT) {
		                    
		                 }else if(eventType == XmlPullParser.START_TAG) {		                	
		                		if(xpp.getName().compareTo("appName")==0)
		                			getDataApp = true;
		                		
		                 }else if(eventType == XmlPullParser.END_TAG) {		                	 
		                	 if(xpp.getName().compareTo("appName")==0 && getDataApp) {
		                		 getDataApp =false;
		                	 }
		                	
		                 }else if(eventType == XmlPullParser.TEXT) {                   
		                	if(getDataApp)
		                		 if(app.compareTo(xpp.getText())==0)
		                			 return 1;
		                 }
		                 try{
		                     eventType = xpp.next();
		                }catch (XmlPullParserException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		                }
		             }		    

		        return 0;		
	}
	
	public int getList(Context context, String perm, String group){
		final String xmlFile="userData";
		boolean getDataApp=false;		
		boolean white = false;
		boolean black = false;
		
		if(group.compareTo("ostatné")==0)
			return 0;		

		            String data = null;
					try {
		                FileInputStream fis = context.openFileInput(xmlFile);
		                InputStreamReader isr = new InputStreamReader(fis);
		                char[] inputBuffer = new char[fis.available()];
		                isr.read(inputBuffer);
		                data = new String(inputBuffer);
		                isr.close();
		                fis.close();
		                } catch (FileNotFoundException e3) {
		                // TODO Auto-generated catch block
		                    e3.printStackTrace();
		                } catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            XmlPullParserFactory factory = null;
		            try {
		                factory = XmlPullParserFactory.newInstance();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            factory.setNamespaceAware(true);
		            XmlPullParser xpp = null;
		            try {
		                xpp = factory.newPullParser();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            try{
		                xpp.setInput( new StringReader (data) );
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             int eventType = 0;
		             try{
		                 eventType = xpp.getEventType();
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             while (eventType != XmlPullParser.END_DOCUMENT){
		                 if(eventType == XmlPullParser.START_DOCUMENT) {
		                    
		                 }else if(eventType == XmlPullParser.START_TAG) {		                	
		                		if(xpp.getName().compareTo("group")==0)
		                			if(xpp.getAttributeValue(0).compareTo(group)==0){		                				
		                				getDataApp = true;		                				
		                			}
		                		
		                		if(xpp.getName().compareTo("whiteList")==0 && getDataApp){		                			
		                				white = true;		      
		                		}
		                		
		                		if(xpp.getName().compareTo("blackList")==0 && getDataApp)		                			
	                				black = true;
		                		
		                 }else if(eventType == XmlPullParser.END_TAG) {		                	 
		                	 if(xpp.getName().compareTo("group")==0 && getDataApp){
		                				getDataApp =false;
		                	 }
		                	 
		                	 if(xpp.getName().compareTo("whiteList")==0 && getDataApp)		                			
	                				white = false;
	                		
	                		if(xpp.getName().compareTo("blackList")==0 && getDataApp)		                			
	                				black = false;
		                	
		                 }else if(eventType == XmlPullParser.TEXT) { 		                
		                	 	if(perm.compareTo(xpp.getText())==0 && white)
		                	 		return 1;
		                	 	if(perm.compareTo(xpp.getText())==0 && black)
		                	 		return 2;
		                 }
		                 try{
		                     eventType = xpp.next();
		                }catch (XmlPullParserException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		                }
		             }		    

		        return 5;		
	}
	
	public List<String> readAppOther(Context context){
	
		ArrayList<String> userData = new ArrayList<String>();
		PackageManager packageManager = context.getPackageManager();
		
	    List<PackageInfo> packs = packageManager.getInstalledPackages(0); //PackageManager.GET_META_DATA 
	    
	    for(int i=0; i < packs.size(); i++) {
	       PackageInfo p = packs.get(i);
	       ApplicationInfo a = p.applicationInfo;
	       // skip system apps if they shall not be included
	       if ((!false) && ((a.flags & ApplicationInfo.FLAG_SYSTEM) == 1)) {
	          continue;
	       }
	       if(true){
	    	   if(isInGroup(context,p.packageName)!=1){
	    		   final PackageManager pm = context.getPackageManager();
           			ApplicationInfo ai;
           			try {
           				ai = pm.getApplicationInfo(p.packageName, 0);
           			} catch (final NameNotFoundException e) {
           				ai = null;
           			}
           			final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) :"neznámy");
	    		  /* PackageManager packageManager1 = context.getPackageManager();
	    		   ApplicationInfo applicationInfo = null;
	    		  
	    		       try {
						applicationInfo = packageManager1.getApplicationInfo(p.packageName, 0);
					} catch (NameNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		  
	    		   final String title = (String)((applicationInfo != null) ? packageManager1.getApplicationLabel(applicationInfo) : "");*/
	    		   userData.add(applicationName+System.getProperty("line.separator")+p.packageName);
	    		   
	    	   }
	       
	       }
	    }
	       
	       return userData;	
	}
	
	public List<String> readWL(Context context, String app)
	{//app==group
		final String xmlFile="userData";
		boolean getDataApp2=false;
		boolean getDataApp=false;
		ArrayList<String> userData = new ArrayList<String>();
		            String data = null;
					try {
		                FileInputStream fis = context.openFileInput(xmlFile);
		                InputStreamReader isr = new InputStreamReader(fis);
		                char[] inputBuffer = new char[fis.available()];
		                isr.read(inputBuffer);
		                data = new String(inputBuffer);
		                isr.close();
		                fis.close();
		                } catch (FileNotFoundException e3) {
		                // TODO Auto-generated catch block
		                    e3.printStackTrace();
		                } catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            XmlPullParserFactory factory = null;
		            try {
		                factory = XmlPullParserFactory.newInstance();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            factory.setNamespaceAware(true);
		            XmlPullParser xpp = null;
		            try {
		                xpp = factory.newPullParser();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            try{
		                xpp.setInput( new StringReader (data) );
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             int eventType = 0;
		             try{
		                 eventType = xpp.getEventType();
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             while (eventType != XmlPullParser.END_DOCUMENT){
		                 if(eventType == XmlPullParser.START_DOCUMENT) {
		                    
		                 }else if(eventType == XmlPullParser.START_TAG) {
		                	if(xpp.getName().compareTo("group")==0){
		                		if(xpp.getAttributeCount()==1)
		                			if(xpp.getAttributeName(0).compareTo("name")==0)
		                				if(xpp.getAttributeValue(0).compareTo(app)==0)
		                					getDataApp = true;
		                	}
		                	else{
		                		if(xpp.getName().compareTo("whiteList")==0)
		                			getDataApp2 = true;
		                	}
		                	
		                 }else if(eventType == XmlPullParser.END_TAG) {		                	 
		                	 if(xpp.getName().compareTo("group")==0 && getDataApp) {
		                		 getDataApp =false;
		                		 break;
		                	 }
		                	 else{
			                	if(xpp.getName().compareTo("whiteList")==0)
			                		getDataApp2 = false;
			                }
		                 }else if(eventType == XmlPullParser.TEXT) {                   
		                	if(getDataApp && getDataApp2)
		                		 userData.add(xpp.getText().substring(19)); 		                    
		                 }
		                 try{
		                     eventType = xpp.next();
		                }catch (XmlPullParserException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		                }
		             }		    

		        Collections.sort(userData);
		        return userData;		
	}

	public List<String> readBL(Context context, String app){//app==group
		final String xmlFile="userData";
		boolean getDataApp2=false;
		boolean getDataApp=false;
		ArrayList<String> userData = new ArrayList<String>();
		            String data = null;
					try {
		                FileInputStream fis = context.openFileInput(xmlFile);
		                InputStreamReader isr = new InputStreamReader(fis);
		                char[] inputBuffer = new char[fis.available()];
		                isr.read(inputBuffer);
		                data = new String(inputBuffer);
		                isr.close();
		                fis.close();
		                } catch (FileNotFoundException e3) {
		                // TODO Auto-generated catch block
		                    e3.printStackTrace();
		                } catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            XmlPullParserFactory factory = null;
		            try {
		                factory = XmlPullParserFactory.newInstance();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            factory.setNamespaceAware(true);
		            XmlPullParser xpp = null;
		            try {
		                xpp = factory.newPullParser();
		                } catch (XmlPullParserException e2) {
		                // TODO Auto-generated catch block
		                e2.printStackTrace();
		                }
		            try{
		                xpp.setInput( new StringReader (data) );
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             int eventType = 0;
		             try{
		                 eventType = xpp.getEventType();
		                } catch (XmlPullParserException e1) {
		                // TODO Auto-generated catch block
		                e1.printStackTrace();
		                }
		             while (eventType != XmlPullParser.END_DOCUMENT){
		                 if(eventType == XmlPullParser.START_DOCUMENT) {
		                    
		                 }else if(eventType == XmlPullParser.START_TAG) {
		                	if(xpp.getName().compareTo("group")==0){
		                		if(xpp.getAttributeCount()==1)
		                			if(xpp.getAttributeName(0).compareTo("name")==0)
		                				if(xpp.getAttributeValue(0).compareTo(app)==0)
		                					getDataApp = true;
		                	}
		                	else{
		                		if(xpp.getName().compareTo("blackList")==0)
		                			getDataApp2 = true;
		                	}
		                	
		                 }else if(eventType == XmlPullParser.END_TAG) {		                	 
		                	 if(xpp.getName().compareTo("group")==0 && getDataApp) {
		                		 getDataApp =false;
		                		 break;
		                	 }
		                	 else{
			                	if(xpp.getName().compareTo("blackList")==0)
			                		getDataApp2 = false;
			                }
		                 }else if(eventType == XmlPullParser.TEXT) {                   
		                	if(getDataApp && getDataApp2)
		                		 userData.add(xpp.getText().substring(19)); 		                    
		                 }
		                 try{
		                     eventType = xpp.next();
		                }catch (XmlPullParserException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		                }
		             }		    

		        Collections.sort(userData);
		        return userData;		
	}
	
}
