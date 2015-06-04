package pete.android.study.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import android.content.Context;
import android.os.AsyncTask;

public class RetrieveData extends AsyncTask<String, Void, String> {

	Context activityContext;
	
	public RetrieveData()
	{
		this.activityContext = null;
	}
	
	public RetrieveData(Context context)
	{
		this.activityContext = context;
	}
	
	protected String doInBackground(String... urls) 
    {		
		// Load CAs from an InputStream
		// (could be from a resource or ByteArrayInputStream or ...)
		try
		{
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			// From https://www.washington.edu/itconnect/security/ca/load-der.crt
			//InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
			
			//File sdcard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

			//Get the text file
			//File file = new File(sdcard,"ssl.crt");
			//InputStream caInput = new BufferedInputStream(new FileInputStream(file));
			InputStream caInput = new BufferedInputStream(activityContext.getAssets().open("ssl.crt"));
			
			Certificate ca;
			try {
			    ca = cf.generateCertificate(caInput);
			    //System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
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
	
			// Create an SSLContext that uses our TrustManager
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(null, tmf.getTrustManagers(), null);
	
			// Tell the URLConnection to use a SocketFactory from our SSLContext
			URL url = new URL(urls[0]);
			HttpsURLConnection urlConnection =
			    (HttpsURLConnection)url.openConnection();
			urlConnection.setSSLSocketFactory(context.getSocketFactory());
			InputStream in = urlConnection.getInputStream();
				
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String inputLine;
			StringBuilder sb = new StringBuilder();
			while ((inputLine = reader.readLine()) != null)
	        {
	          sb.append(inputLine);
	        }
			
			return sb.toString();
		}
		catch(Exception e)
		{
			return null;
		}
		
//		HttpParams httpParameters = new BasicHttpParams();
//	    HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
//	    HttpConnectionParams.setSoTimeout(httpParameters, 10000);
//	    HttpProtocolParams.setVersion(httpParameters, HttpVersion.HTTP_1_1);
//
//	    //Thread safe in case various AsyncTasks try to access it concurrently
//	    SchemeRegistry schemeRegistry = new SchemeRegistry();
//	    schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
//	    schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 8443));
//	    ClientConnectionManager cm = new ThreadSafeClientConnManager(httpParameters, schemeRegistry);
//
//	    DefaultHttpClient client = new DefaultHttpClient(cm, httpParameters);
//
//	    CookieStore cookieStore = client.getCookieStore();
//	    HttpContext localContext = new BasicHttpContext();
//	    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

		
//    	HttpClient client = new DefaultHttpClient();
//
//        // Prepare a request object
//    	//String url = "http://danny-n.webpark.cz/files/test.json";
//    	String url = urls[0];
//        HttpGet httpget = new HttpGet(url); 
//
//        // Execute the request
//        HttpResponse response;
//        try 
//        {
//            URL myurl = new URL(url);
//            HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
//            InputStream ins = con.getInputStream();
//            InputStreamReader isr = new InputStreamReader(ins);
//            BufferedReader in = new BufferedReader(isr);
//         
//            String inputLine;
//         
//            while ((inputLine = in.readLine()) != null)
//            {
//              System.out.println(inputLine);
//            }
//         
//            in.close();
//        	
//            response = client.execute(httpget);
//            // Examine the response status
//            int code = response.getStatusLine().getStatusCode();
//
//            // Get hold of the response entity
//            HttpEntity entity = response.getEntity();
//            // If the response does not enclose an entity, there is no need
//            // to worry about connection release
//            
//            if (entity != null) 
//            {
//                // A Simple JSON Response Read
//                InputStream instream = entity.getContent();
//                String result= convertStreamToString(instream);
//                // now you have the string representation of the HTML request
//                instream.close();
//                
//                return result;
//            }
//
//        } 
//        catch (Exception e) 
//        {
//        	String m = e.getMessage();
//        	
//        	return m;
//        }
//		return "SOMETHING";
    	
    }

    private static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
