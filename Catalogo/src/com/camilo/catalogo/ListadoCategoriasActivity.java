package com.camilo.catalogo;

import java.util.ArrayList;

import com.camilo.catalogo.moduloaplicaciones.ClassCategoriaBO;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class ListadoCategoriasActivity extends Activity implements OnItemClickListener {

	private int screenSize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_categorias);
		identificarVistas();
	}
	
	private void identificarVistas(){
		ClassAplicacion aplicacion = (ClassAplicacion) getApplicationContext();
		
		ArrayList<ClassCategoriaBO> categorias = aplicacion.getListadoCategorias();
		screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
		
		ListadoCategoriasAdapter adaptador; 
		
		switch(screenSize) {
	    	case Configuration.SCREENLAYOUT_SIZE_LARGE:
	    		GridView gridViewCategorias = (GridView)findViewById(R.id.listado_categorias_gv_listado);
	    		adaptador = new ListadoCategoriasAdapter(this, R.layout.fila_categoria_adaptador,R.id.listado_categorias_gv_listado , R.id.fila_adaptador_categoria_tv_nombre, categorias);
	    		gridViewCategorias.setAdapter(adaptador);
	    		gridViewCategorias.setOnItemClickListener(this);
	    		break;
	    	default:
	        // Set adapter for ListView
	    		ListView listViewAplicaciones = (ListView)findViewById(R.id.listado_categorias_lv_listado);
	    		adaptador= new ListadoCategoriasAdapter(this, R.layout.fila_categoria_adaptador,R.id.listado_categorias_lv_listado , R.id.fila_adaptador_categoria_tv_nombre, categorias);
	    		listViewAplicaciones.setAdapter(adaptador);
	    		listViewAplicaciones.setOnItemClickListener(this);
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listado_categorias, menu);
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
		Toast.makeText(this, ""+position, Toast.LENGTH_LONG).show();
		ListadoCategoriasAdapter categoriasAdapter =  (ListadoCategoriasAdapter) parent.getAdapter();
		ArrayList<ClassCategoriaBO> categorias = categoriasAdapter.getCategorias();
		
		Intent intent = new Intent();
		intent.putExtra(ListadoAplicacionesActivity.INDICE_CATEGORIA, position);
		
		startActivity(intent.setComponent(new ComponentName(this, ListadoAplicacionesActivity.class)));
	}
}
