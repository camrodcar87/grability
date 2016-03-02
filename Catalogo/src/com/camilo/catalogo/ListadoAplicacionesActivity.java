package com.camilo.catalogo;

import java.util.ArrayList;

import org.json.JSONException;

import com.camilo.catalogo.moduloaplicaciones.ClassAplicacionBO;
import com.camilo.catalogo.moduloaplicaciones.ClassCategoriaBO;
import com.camilo.catalogo.utilidades.ClassConstantes;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

public class ListadoAplicacionesActivity extends Activity implements OnItemClickListener {
	private int posicionCategorias = -1;
	public static final String INDICE_CATEGORIA = "INDICE_CATEGORIA";
	private String labelCategoria = "";
	private ArrayList<ClassAplicacionBO> aplicacionesCategoria = new ArrayList<ClassAplicacionBO>();
	private ArrayList<Integer> idReales = new ArrayList<Integer>(); 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_aplicaciones);
		seleccionarAplicaciones();
		identificarVistas();
	}
	
	private void seleccionarAplicaciones(){
		this.posicionCategorias = getIntent().getExtras().getInt(INDICE_CATEGORIA);
		ClassAplicacion aplicacion = (ClassAplicacion) getApplicationContext();
		ArrayList<ClassCategoriaBO> categorias = aplicacion.getListadoCategorias();
		this.labelCategoria =  categorias.get(posicionCategorias).getLabel();
		ArrayList<ClassAplicacionBO> aplicaciones = aplicacion.getListadoAplicaciones();
		int contador = 0;
		for (ClassAplicacionBO aplicacionBO: aplicaciones){
			try {
				String labelVerificar = aplicacionBO.getCategory().getAttributes().getString(ClassConstantes.MODULO_APLICACIONES_LABEL);
				if (labelVerificar.equals(labelCategoria)){
					this.aplicacionesCategoria.add(aplicacionBO);
					idReales.add(contador);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contador++;
		}
	}
	
	private void identificarVistas(){
		
		int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
		
		ListadoAplicacionesAdapter adaptador;
    	
		switch(screenSize) {
		    case Configuration.SCREENLAYOUT_SIZE_LARGE:
		        // Set adapter for GridView
		    	
		    	GridView gridViewAplicaciones = (GridView)findViewById(R.id.listado_aplicaciones_gv_listado);
		    	adaptador = new ListadoAplicacionesAdapter(this, R.layout.fila_aplicacion_adaptador, R.id.listado_aplicaciones_gv_listado, R.id.fila_adaptador_large_tv_nombre_app, this.aplicacionesCategoria);
		    	gridViewAplicaciones.setAdapter(adaptador);
		    	gridViewAplicaciones.setOnItemClickListener(this);
		        break;
		    default:
		        // Set adapter for ListView
		    	ListView listViewAplicaciones = (ListView)findViewById(R.id.listado_aplicaciones_lv_listado);
		    	adaptador = new ListadoAplicacionesAdapter(this, R.layout.fila_aplicacion_adaptador, R.id.listado_aplicaciones_lv_listado, R.id.fila_adaptador_small_tv_nombre_app, this.aplicacionesCategoria);
		    	listViewAplicaciones.setAdapter(adaptador);
		    	listViewAplicaciones.setOnItemClickListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listado_aplicaciones, menu);
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.putExtra(DetalleAplicacionActivity.INDICE_APLICACION, idReales.get(position));
		
		startActivity(intent.setComponent(new ComponentName(this, DetalleAplicacionActivity.class)));
		
	}
}
