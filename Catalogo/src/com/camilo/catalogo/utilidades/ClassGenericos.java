package com.camilo.catalogo.utilidades;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

public class ClassGenericos {

	public static Bitmap obtenerBitmapDeURL(String urlCadena){
		Bitmap bm = null;
		try{
			URL url = new URL(urlCadena);
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bm = BitmapFactory.decodeStream(bis);
			bis.close();
			is.close();
		}catch(IOException e){
			
		}
		return bm;
	}
	
	public static boolean isRedDisponible(Context context) {
		boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;

	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                haveConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	}
	
	public static boolean guardarImagen(Bitmap bitmap, String nombre){
		try {

			String baseDir = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/imagenesCatalogo";
			File direct = new File(baseDir);

			if (!direct.exists()) {
				File directorioImagenes = new File(baseDir);
				directorioImagenes.mkdirs();
			}

			String ruta = baseDir + File.separator + nombre
					+ ".png";

			FileOutputStream out = new FileOutputStream(ruta);

			bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static Bitmap obtenerImagen(String nombreImagen){
		String rutaImagen = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/imagenesCatalogo"+ File.separator + nombreImagen
				+ ".png";
		
		File imgFile = new  File(rutaImagen);

		if(imgFile.exists()){

		    Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

		    return bitmap;

		}
		return null;
	}
	
}
