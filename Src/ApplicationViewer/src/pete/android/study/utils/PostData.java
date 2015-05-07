package pete.android.study.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

public class PostData extends AsyncTask<String, Void, String> {

	Context activityContext;
	
	public PostData(){		
	}
	
	public PostData(Context context){
		activityContext = context;
	}
	
    @Override
    protected String doInBackground(String... params) {    	
    	try
		{
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			InputStream caInput = new BufferedInputStream(activityContext.getAssets().open("ssl.crt"));
			
			Certificate ca;
			try {
			    ca = cf.generateCertificate(caInput);
			} finally {
			    caInput.close();
			}
	
			// Create a KeyStore containing our trusted CAs
			String keyStoreType = KeyStore.getDefaultType();
			KeyStore keyStore = KeyStore.getInstance(keyStoreType);
			keyStore.load(null, null);
			keyStore.setCertificateEntry("ca", ca);
	
			// Create a TrustManager that trusts the CAs in our KeyStore
			String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
			tmf.init(keyStore);
	
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(null, tmf.getTrustManagers(), null);
	
			URL url = new URL(params[0]);
			
			HttpsURLConnection urlConnection =
			    (HttpsURLConnection)url.openConnection();
			urlConnection.setSSLSocketFactory(context.getSocketFactory());
									
			JSONObject jo = new JSONObject();
        	jo.put("name", params[1]);
        	jo.put("package", params[2]);
        	jo.put("version", params[3]);
        	jo.put("fingerprint", params[4]);
        	jo.put("description", params[5]);
        	
        	
        	String input = jo.toString();
			
			urlConnection.setDoOutput( true );
			urlConnection.setDoInput ( true );
			urlConnection.setInstanceFollowRedirects( false );
			urlConnection.setRequestMethod( "POST" );
			urlConnection.setRequestProperty( "Content-Type", "application/json"); 
			urlConnection.setRequestProperty( "charset", "utf-8");
			int dataLength = input.getBytes().length;
			urlConnection.setRequestProperty( "Content-Length", Integer.toString(dataLength));
			urlConnection.setUseCaches( false );
			DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
			
			try
			{ 				 						
			   wr.write( input.getBytes(Charset.forName( "UTF-8" )));
			   wr.flush();
			}
			catch(Exception ex)
			{
				return "ERROR";
			}
			finally
			{
				wr.close();
			}
			String response = urlConnection.getResponseMessage();
			int responseCode = urlConnection.getResponseCode();
			
			return "DONE";
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
    	
//    	try {
//        	URL url = new URL("http://thanos.feidroid.mobi:8080/FEIDroid-0.0.1-SNAPSHOT/api/application");
//        	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        	conn.setDoOutput(true);
//        	conn.setRequestMethod("POST");
//        	conn.setRequestProperty("Content-Type", "application/json");
//        	
//        	JSONObject jo = new JSONObject();
//        	jo.put("name", params[0]);
//        	jo.put("version", params[1]);
//        	
//        	String input = jo.toString();
//
//        	OutputStream os = conn.getOutputStream();
//        	os.write(input.getBytes());
//        	os.flush();
//
//        	if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//        		throw new RuntimeException("Failed : HTTP error code : "
//        				+ conn.getResponseCode());
//        	}
//
//        	BufferedReader br = new BufferedReader(new InputStreamReader(
//        			(conn.getInputStream())));
//
//        	String output;
//        	System.out.println("Output from Server .... \n");
//        	
//        	while ((output = br.readLine()) != null) {
//        		System.out.println(output);
//        	}
//
//        		conn.disconnect();
//
//        	} catch (MalformedURLException e) {
//
//        		e.printStackTrace();
//
//        	} catch (IOException e) {
//
//        		e.printStackTrace();
//
//        	} catch (JSONException e) {
//				e.printStackTrace();
//			}  
//        return null;
    }

    @Override
    protected void onPostExecute(String result) {
    }

}