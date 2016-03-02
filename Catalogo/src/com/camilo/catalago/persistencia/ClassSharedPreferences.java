package com.camilo.catalago.persistencia;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ClassSharedPreferences {
	public static final String SHARED_PREFERENCES_ARCHIVO = "DatosCatalogo";
	public static final String INFORMACION_JSON = "InormacionJSON";
	public static final String DATOS_DE_PRUEBA = "Datosdeprueba";
	public static final String BANDERA_EXISTEN_DATOS = "banderaExistenDatos";
	
	public static final String CLAVE_NO_EXISTE = "nada";
	private Context context;
	SharedPreferences sharedPreferences;
	
	public ClassSharedPreferences(Context context) {
		this.context = context;
		this.sharedPreferences = this.context.getSharedPreferences(SHARED_PREFERENCES_ARCHIVO, Context.MODE_PRIVATE);
	}
	

	
	private boolean editarString(String clave, String valor){
		Editor editor = this.sharedPreferences.edit();
		editor.putString(clave, valor);
		boolean insercion = editor.commit();
		return insercion;
	}
	
	public void eliminarDatos(){
		Editor editor = this.sharedPreferences.edit();
		editor.clear();
		editor.commit();
	}
	
	private String consultarString(String clave){
		Map<String, ?> todos = sharedPreferences.getAll();
		if(sharedPreferences.contains(clave))
			return sharedPreferences.getString(clave,CLAVE_NO_EXISTE);
		else
			return null;
				
	}
	
	
	private Boolean isBandera(String clave){
		if(sharedPreferences.contains(clave))
			return sharedPreferences.getBoolean(clave, false);
		else
			return false;
	}
	
	private void setBandera(String clave, Boolean valor){
		Editor editor = this.sharedPreferences.edit();
		editor.putBoolean(clave, valor);
		editor.commit();
	}
	
	public void setInformacionJSON(JSONObject objetoJSON){
		String jsonString = objetoJSON.toString();
		editarString(INFORMACION_JSON, jsonString);
	}
	
	public JSONObject getInformacionJSON(){
		String jsonString = consultarString(INFORMACION_JSON);
		if (jsonString == null){
			return null;
		}
		JSONObject jsonObjeto = null;
		try {
			jsonObjeto = new JSONObject(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObjeto;
	}
	
	public void setBanderaDatos(boolean valor){
		setBandera(BANDERA_EXISTEN_DATOS, valor);
	}
	
	public boolean isBanderaDatos(){
		return isBandera(BANDERA_EXISTEN_DATOS);
	}
	
	public void setDatosDePrueba(String valor){
		editarString(DATOS_DE_PRUEBA, valor);
		
	}
	
	public String getDatosDePrueba(){
		return consultarString(DATOS_DE_PRUEBA);
	}
}
