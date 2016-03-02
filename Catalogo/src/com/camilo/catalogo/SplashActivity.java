package com.camilo.catalogo;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.camilo.catalago.persistencia.ClassSharedPreferences;
import com.camilo.catalogo.moduloaplicaciones.ClassAplicacionBO;
import com.camilo.catalogo.moduloaplicaciones.ClassModuloAplicacionesBusinessWeb;
import com.camilo.catalogo.servicioweb.ClassFachadaSolicitudes;
import com.camilo.catalogo.servicioweb.ClassFachadaSolicitudesBO;
import com.camilo.catalogo.servicioweb.ComunicacionFachadaCallback;
import com.camilo.catalogo.utilidades.ClassConstantes;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SplashActivity extends Activity implements ComunicacionFachadaCallback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		llamadoServicio();
	}
	
	private void llamadoServicio(){
		ClassFachadaSolicitudes fachadaSolicitudes = new ClassFachadaSolicitudes(this, ClassConstantes.CONSULTAR_APLICACIONES, this);
		fachadaSolicitudes.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void tareaFinalizada(ClassFachadaSolicitudesBO mensaje) {
		ClassSharedPreferences sharedPreferences = new ClassSharedPreferences(this);
		ClassAplicacion aplicacion = (ClassAplicacion) getApplicationContext();
	
		if (mensaje.isBanderaServicioWeb()){
			
			aplicacion.setListadoAplicaciones(mensaje.getListadoAplicaciones());
			aplicacion.setListadoCategorias(mensaje.getListadoCategorias());
			JSONObject jsonRespuesta = mensaje.getObjetoRespuesta();
			sharedPreferences.setBanderaDatos(true);
			sharedPreferences.setDatosDePrueba("Un valor de prueba para comprobar que se guardan cosas");
			sharedPreferences.setInformacionJSON(jsonRespuesta);
			
			boolean banderaDatos = sharedPreferences.isBanderaDatos();
			String datosPrueba = sharedPreferences.getDatosDePrueba();
			JSONObject infoJSON = sharedPreferences.getInformacionJSON();
			
		}else{
			JSONObject objetoJSON =  sharedPreferences.getInformacionJSON();
			boolean banderaExistenDatos = sharedPreferences.isBanderaDatos();
			String datosPrueba = sharedPreferences.getDatosDePrueba();
			String c2 = datosPrueba;
			if (objetoJSON == null){
				Toast.makeText(this, "No hay información para mostrar", Toast.LENGTH_LONG).show();
				return;
			}
			try {
				ClassFachadaSolicitudesBO fachadaSolicitudesBO =  (new ClassModuloAplicacionesBusinessWeb()).obtenerAplicaciones(objetoJSON);
				
				aplicacion.setListadoAplicaciones(fachadaSolicitudesBO.getListadoAplicaciones());
				aplicacion.setListadoCategorias(fachadaSolicitudesBO.getListadoCategorias());
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		startActivity(new Intent().setComponent(new ComponentName(this, ListadoCategoriasActivity.class)));
	}
}
