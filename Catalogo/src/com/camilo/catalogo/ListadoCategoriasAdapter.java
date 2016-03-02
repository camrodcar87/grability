package com.camilo.catalogo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.camilo.catalogo.moduloaplicaciones.ClassCategoriaBO;
import com.camilo.catalogo.moduloaplicaciones.ClassElementoBO;

public class ListadoCategoriasAdapter extends ArrayAdapter<ClassCategoriaBO> {
	
	public ListadoCategoriasAdapter(Context context, int resource,int idListado, int textViewResourceId,
			List<ClassCategoriaBO> categorias) {
		super(context, resource, textViewResourceId, categorias);
		this.recurso = resource;
		this.categorias = (ArrayList<ClassCategoriaBO>) categorias;
		this.context = context;
		this.idListado = idListado;
		// TODO Auto-generated constructor stub
	}

	private Context context;
	private ArrayList<ClassCategoriaBO> categorias;
	private int recurso;

	private int idListado;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categorias.size();
	}

	@Override
	public ClassCategoriaBO getItem(int position) {
		// TODO Auto-generated method stub
		return categorias.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view;
		
		//if(convertView == null){
			view = new View(context);
			view = inflater.inflate(this.recurso, null);
			TextView nombreCategoriaTV = null;
			nombreCategoriaTV  = (TextView)view.findViewById(R.id.fila_adaptador_categoria_tv_nombre);
						ClassCategoriaBO categoriaBO = this.categorias.get(position);
			String nombreAplicacion = categoriaBO.getLabel();
			
			nombreCategoriaTV.setText(nombreAplicacion);
			//}else{
			//view = (View)convertView;
			
		//}
		
		return view;
	}

	public ArrayList<ClassCategoriaBO> getCategorias() {
		return categorias;
	}
	
	
	/*public MenuAdapter(Context context,int idListado ,int recurso, ArrayList<ClassAplicacionBO> aplicaciones) {
		
		
	}
	*/

}
