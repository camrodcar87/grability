package com.camilo.catalogo.servicioweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassGestorComunicacion {
public static JSONObject obtenerJSONDeURL(String url,JSONObject json) throws JSONException, ClientProtocolException, IOException{
		
		String result="";
		JSONObject jObjectResponse= null;
		String contentType="application/json";
		HttpParams params= new BasicHttpParams();
		DefaultHttpClient httpClient= new DefaultHttpClient(params);
		BasicCookieStore cookieStore= new BasicCookieStore();
		HttpPost post= new HttpPost(url);
		//HttpGet post = new HttpGet(url);
		
		StringEntity se = new StringEntity( json.toString(),HTTP.UTF_8);  
        se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
			
		post.setHeader("Content-Type",contentType);
		post.setHeader("Accept",contentType);
		
		
		
		httpClient.setCookieStore(cookieStore);
		HttpResponse httpResponse= httpClient.execute(post); 
		HttpEntity httpEntity =httpResponse.getEntity();
		if(httpEntity != null){
			InputStream is= httpEntity.getContent();
			BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb= new StringBuilder();
			String line= null;
			while((line= reader.readLine())!= null){
				sb.append(line + "\n");	
			}
			is.close();
			result=sb.toString();
		}
	
		
		
		jObjectResponse=new JSONObject(result);
		
		
		return jObjectResponse;
	}
}
