package com.camilo.catalogo;

import java.util.ArrayList;

import org.json.JSONException;

import com.camilo.catalogo.moduloaplicaciones.ClassAplicacionBO;
import com.camilo.catalogo.moduloaplicaciones.ClassCategoriaBO;
import com.camilo.catalogo.moduloaplicaciones.ClassElementoBO;
import com.camilo.catalogo.utilidades.ClassConstantes;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleAplicacionActivity extends Activity {

	private int posicionAplicacion = -1;
	public static final String INDICE_APLICACION = "INDICE_APLICACION";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_aplicacion);
		identificarVistas();
	}

	private void identificarVistas() {
		this.posicionAplicacion = getIntent().getExtras().getInt(
				INDICE_APLICACION);
		ClassAplicacion aplicacion = (ClassAplicacion) getApplicationContext();
		ArrayList<ClassAplicacionBO> aplicaciones = aplicacion
				.getListadoAplicaciones();
		ClassAplicacionBO aplicacionBO = aplicaciones.get(posicionAplicacion);

		TextView detalleTV = (TextView) findViewById(R.id.detalle_tv_detalle);
		ImageView imagenIV = (ImageView) findViewById(R.id.detalle_iv_imagen);

		try {
			String texto = "Im Name: "+aplicacionBO.getImName().getLabel()+"\n"+
					"Summary: "+aplicacionBO.getSummary().getLabel()+"\n"+
					"Category:"+aplicacionBO.getCategory().getAttributes()
					.getString(ClassConstantes.MODULO_APLICACIONES_LABEL);
			detalleTV.setText(texto);
			
			Resources r = getResources();
			
			ArrayList<ClassElementoBO> imagenesAplicacion = aplicacionBO.getImImage();
			float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, r.getDisplayMetrics());
			imagenIV.setImageBitmap(Bitmap.createScaledBitmap(imagenesAplicacion.get(0).getImagen(),(int)px, (int)px, false));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_aplicacion, menu);
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
}
