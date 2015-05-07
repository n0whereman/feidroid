package sk.stuba.fei.feidroid.feidroidbeta;



import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import sk.stuba.fei.feidroid.feidroidbeta.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	Context context;
	ListView listView;

	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		context = getApplicationContext();
		xmlFile xml = new xmlFile();
	
		File file = getApplicationContext().getFileStreamPath("userData");
		if(!file.exists())
			xml.writeData(getApplicationContext());
		//naplnenie zoznamu
		 listView=(ListView)findViewById(R.id.listViewZoznamGroup);
		
		 listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_group, xml.readGroup(getApplicationContext(),false)));		
		 
		 listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {			    
				    setAppList(((TextView) view).getText());				    
				}
			});
		 
		 
	}
	
	public void setAppList(CharSequence title){		
//		Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
//		myIntent.putExtra("title",  title);	
//		startActivity(myIntent);
		
		Intent intent = new Intent();	
		intent.setClass(getApplicationContext(), SecondActivity.class);
		Bundle b = new Bundle();
		b.putString("title",  title.toString());
		intent.putExtras(b);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}	

	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {		
		 
		 final xmlFile xml = new xmlFile();	
	        if(item.getItemId() == R.id.item1){
	        	AlertDialog.Builder alert = new AlertDialog.Builder(this);

	        	alert.setTitle("Prida� kateg�riu");
	        	alert.setMessage("N�zov novej kateg�rie:");

	        	// Set an EditText view to get user input 
	        	final EditText input = new EditText(this);
	        	alert.setView(input);

	        	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        	  Editable value = input.getText();
	        	  
	        	  if(value.length()>0){	 	        		         		         		
					
	        		  try {
						 xml.addXMLTag(value.toString(), "newGroup", null,context);	        			
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
					
				
		              listView=(ListView)findViewById(R.id.listViewZoznamGroup);
		        	  listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group, xml.readGroup(context,false)));	
	        	  }
	        	}
	        	});

	        	alert.setNegativeButton("Zru�i�", new DialogInterface.OnClickListener() {
	        	  public void onClick(DialogInterface dialog, int whichButton) {
	        	    // Canceled.
	        	  }
	        	});

	        	alert.show();
   
        	        
	        }
	        else if(item.getItemId() == R.id.item2){
	        	
	        	 
	        	List<String> kat = xml.readGroup(context,true);
	        	final CharSequence[] choise = kat.toArray(new CharSequence[kat.size()]);	        	
	        
	        	    AlertDialog.Builder alert = new AlertDialog.Builder(this);
	        	    alert.setTitle("Kateg�ria pre zmazanie:");
	        	    alert.setSingleChoiceItems(choise,-1, new DialogInterface.OnClickListener()
	        	    {
	        	        @Override
	        	        public void onClick(DialogInterface dialog, int which) 
	        	        {
	        	           if(choise[which].toString().compareTo("ostatn�")==0){
	        	        	   	
	        	           }
	        	           else{
	        	            	try {
	        						xml.delXMLTag(choise[which].toString(), "delGroup", null,context);					
	        						 listView=(ListView)findViewById(R.id.listViewZoznamGroup);
	        			        	 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group, xml.readGroup(context,false)));
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
	        	            dialog.dismiss();
	        	        }
	        	    });
	        	    alert.show();
	        	
	        }
	        else if(item.getItemId() == R.id.item3){
	        	
	        	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    			alertDialogBuilder.setTitle("Obnovenie kateg�ri�");
	    			alertDialogBuilder
	    				.setMessage("Obnovi� p�vodn� kateg�rie?")
	    				.setCancelable(false)
	    				.setNegativeButton("�no",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						xml.writeData(getApplicationContext());
	    			        	 listView=(ListView)findViewById(R.id.listViewZoznamGroup);
	    			        	 listView.setAdapter(new ArrayAdapter<String>(context, R.layout.list_group, xml.readGroup(context,false)));
	    						
	    						dialog.cancel();
	    					}
	    				  })
	    				.setPositiveButton("Nie",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						dialog.cancel();
	    					}
	    				});
	    				AlertDialog alertDialog = alertDialogBuilder.create();
	    				alertDialog.show(); 			
	        }
	        else{
	                // if a the new item is clicked show "Toast" message.
	        }
	 
	        return super.onOptionsItemSelected(item);
	    }
	 
	 
}
