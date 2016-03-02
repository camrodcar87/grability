package com.camilo.catalogo.servicioweb;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import com.camilo.catalogo.moduloaplicaciones.ClassAplicacionBO;
import com.camilo.catalogo.moduloaplicaciones.ClassModuloAplicacionesBusinessWeb;
import com.camilo.catalogo.utilidades.ClassConstantes;
import com.camilo.catalogo.utilidades.ClassGenericos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class ClassFachadaSolicitudes extends AsyncTask<ClassFachadaSolicitudesBO, ClassFachadaSolicitudesBO, ClassFachadaSolicitudesBO> {
	private Activity activity;
	private ComunicacionFachadaCallback callback;
	private int tareaRealizar = 0;
	//private ProgressDialog dialogoProgreso;
	
	public ClassFachadaSolicitudes(Activity activity, int tareaRealizar,
			ComunicacionFachadaCallback callback) {
		this.activity = activity;
		this.tareaRealizar = tareaRealizar;
		this.callback = callback;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		if(!ClassGenericos.isRedDisponible(activity)){
			return;
		}
		
		/*dialogoProgreso = new ProgressDialog(activity);
		dialogoProgreso.setIndeterminate(false);
		dialogoProgreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialogoProgreso
				.setMessage("Comunicación en proceso...");
		dialogoProgreso.setCancelable(false);
		dialogoProgreso.setCanceledOnTouchOutside(false);
		dialogoProgreso.show();*/
	}
	
	@Override
	protected ClassFachadaSolicitudesBO doInBackground(
			ClassFachadaSolicitudesBO... params) {
		ClassFachadaSolicitudesBO fachadaSolicitudesBO = new ClassFachadaSolicitudesBO();
		
		if(!ClassGenericos.isRedDisponible(activity)){
			fachadaSolicitudesBO.setBanderaServicioWeb(false);
			return fachadaSolicitudesBO;
		}
		try {
			switch (this.tareaRealizar) {
			case ClassConstantes.CONSULTAR_APLICACIONES:
				JSONObject objetoRespuesta  = obtenerJSON(ClassConstantes.URL_SERVER);
				fachadaSolicitudesBO =(new ClassModuloAplicacionesBusinessWeb()).obtenerAplicaciones(objetoRespuesta);
				fachadaSolicitudesBO.setBanderaServicioWeb(true);
				fachadaSolicitudesBO.setObjetoRespuesta(objetoRespuesta);
				
				
				break;
	
			default:
				break;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fachadaSolicitudesBO;
	}
	
	
	
	private JSONObject obtenerJSON(String url) throws ClientProtocolException, JSONException, IOException{
		JSONObject json = new JSONObject();
		
		JSONObject jsonRespuesta = ClassGestorComunicacion.obtenerJSONDeURL(url, json);
		return jsonRespuesta;
	}
	
	@Override
	protected void onPostExecute(ClassFachadaSolicitudesBO result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		/*if (dialogoProgreso != null)
			dialogoProgreso.dismiss();*/
		callback.tareaFinalizada(result);
	}


}
