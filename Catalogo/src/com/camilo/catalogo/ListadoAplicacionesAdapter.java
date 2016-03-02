package com.camilo.catalogo;

import java.util.ArrayList;
import java.util.List;

import com.camilo.catalogo.moduloaplicaciones.ClassAplicacionBO;
import com.camilo.catalogo.moduloaplicaciones.ClassElementoBO;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.text.Layout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListadoAplicacionesAdapter extends ArrayAdapter<ClassAplicacionBO> {
	
	public ListadoAplicacionesAdapter(Context context, int resource,int idListado, int textViewResourceId,
			List<ClassAplicacionBO> aplicaciones) {
		super(context, resource, textViewResourceId, aplicaciones);
		this.recurso = resource;
		this.aplicaciones = (ArrayList<ClassAplicacionBO>) aplicaciones;
		this.context = context;
		this.idListado = idListado;
		// TODO Auto-generated constructor stub
	}

	private Context context;
	private ArrayList<ClassAplicacionBO> aplicaciones;
	private int recurso;

	private int idListado;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return aplicaciones.size();
	}

	@Override
	public ClassAplicacionBO getItem(int position) {
		// TODO Auto-generated method stub
		return aplicaciones.get(position);
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
			TextView nombreAplicacionTV = null;
			ImageView imagenAplicacionIV = null;
			switch (idListado) {
			case R.id.listado_aplicaciones_gv_listado:
				nombreAplicacionTV  = (TextView)view.findViewById(R.id.fila_adaptador_large_tv_nombre_app);
				imagenAplicacionIV = (ImageView)view.findViewById(R.id.fila_adaptador_large_iv_imagen);
				break;
			case R.id.listado_aplicaciones_lv_listado:
				nombreAplicacionTV  = (TextView)view.findViewById(R.id.fila_adaptador_small_tv_nombre_app);
				imagenAplicacionIV = (ImageView)view.findViewById(R.id.fila_adaptador_small_iv_imagen);
			default:
				break;
			}
			
			ClassAplicacionBO aplicacionBO = this.aplicaciones.get(position);
			String nombreAplicacion = aplicacionBO.getImName().getLabel();
			ArrayList<ClassElementoBO> imagenesAplicacion = aplicacionBO.getImImage();
			nombreAplicacionTV.setText(nombreAplicacion);
			Resources r = context.getResources();
			float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, r.getDisplayMetrics());
			imagenAplicacionIV.setImageBitmap(Bitmap.createScaledBitmap(imagenesAplicacion.get(0).getImagen(),(int)px, (int)px, false));
		//}else{
			//view = (View)convertView;
			
		//}
		
		return view;
	}

	public ArrayList<ClassAplicacionBO> getAplicaciones() {
		return aplicaciones;
	}

	

	
	
}
