package com.loungeboard.mobile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.os.AsyncTask;
import android.util.Log;

public class NetworkHelper {
		private final static String DEB_TAG = "NetworkHelper";
		private final static String host = "http://inteco.groups.si.umich.edu/LoungeBoardMobile";
		
		
		public NetworkHelper(){
			
			
		}
		
		public String register
		(String username, String lastname, String firstname, String email, String password, 
				String password2)
		{
			//LBAPIStatus status = new LBAPIStatus("OK");
			
			// Create a new Http Client and Post Header
			/* 
			 * Should Change to use Https in the future. 
			 *  
			 *  
			 */
			try{
				HttpClient httpclient = new DefaultHttpClient();
//				URI host = new URI("http://inteco.groups.si.umich.edu/LoungeBoardMobile/");
				HttpPost httppost = new HttpPost("http://inteco.groups.si.umich.edu/LoungeBoardMobile/");
				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("username", username));
				nameValuePairs.add(new BasicNameValuePair("lastname", lastname));
				nameValuePairs.add(new BasicNameValuePair("firstname", username));
				nameValuePairs.add(new BasicNameValuePair("email", lastname));
				nameValuePairs.add(new BasicNameValuePair("password", username));
				nameValuePairs.add(new BasicNameValuePair("password2", lastname));
				nameValuePairs.add(new BasicNameValuePair("BTID", "test"));
				
				// Execute HTTP Post Request
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
				HttpResponse response = httpclient.execute(httppost); 
				
				Log.d(DEB_TAG, "HTTP POST getStatusLine" + response.getStatusLine());
				
				
				int status = response.getStatusLine().getStatusCode();
				Log.i(DEB_TAG, "status = " + status);

				
				return "OK";
			} catch( ClientProtocolException e ){
				e.printStackTrace();
			} catch( IOException e){
				e.printStackTrace();
			} 
			
			Log.i(DEB_TAG, "Fail");
			
			return "Fail";
		}

		public String getHost()
		{
			return host;
		}
		
}
