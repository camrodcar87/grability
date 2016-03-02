package com.camilo.catalogo.moduloaplicaciones;


import java.util.ArrayList;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.camilo.catalogo.servicioweb.ClassFachadaSolicitudesBO;
import com.camilo.catalogo.utilidades.ClassConstantes;
import com.camilo.catalogo.utilidades.ClassGenericos;

import android.accounts.NetworkErrorException;
import android.graphics.Bitmap;

public class ClassModuloAplicacionesBusinessWeb {

	private ArrayList<ClassCategoriaBO> listadoCategorias = new ArrayList<ClassCategoriaBO>();
	
	public ClassModuloAplicacionesBusinessWeb() {
		
	}
		
	public ClassFachadaSolicitudesBO obtenerAplicaciones(JSONObject objetoJSON) throws JSONException{
		JSONObject feed= objetoJSON.getJSONObject(ClassConstantes.MODULO_APLICACIONES_FEED);
		JSONObject author = feed.getJSONObject(ClassConstantes.MODULO_APLICACIONES_AUTHOR);
		JSONArray entry = feed.getJSONArray(ClassConstantes.MODULO_APLICACIONES_ENTRY);
		ClassFachadaSolicitudesBO fachadaSolicitudesBO = new ClassFachadaSolicitudesBO();
				
		ArrayList<ClassAplicacionBO> aplicacionBOs = new ArrayList<ClassAplicacionBO>();
		
		for(int contador = 0; contador < entry.length() ; contador++){
			JSONObject objetoAplicacion = entry.getJSONObject(contador);
			ClassAplicacionBO aplicacionBO =  empaquetarObjetoAplicacion(objetoAplicacion);
			aplicacionBOs.add(aplicacionBO);
			
		}
		
		fachadaSolicitudesBO.setListadoAplicaciones(aplicacionBOs);
		fachadaSolicitudesBO.setListadoCategorias(listadoCategorias);
		return fachadaSolicitudesBO;
		
	}
	
	private ClassAplicacionBO empaquetarObjetoAplicacion(JSONObject objetoAplicacion) throws JSONException{
		JSONObject imName = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_IM_NAME);
		JSONArray imImage = objetoAplicacion.getJSONArray(ClassConstantes.MODULO_APLICACIONES_IM_IMAGE);
		JSONObject summary = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_SUMMARY);
		JSONObject imPrice = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_IM_PRICE);
		JSONObject imContentType = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_IM_CONTENT_TYPE);
		JSONObject rights = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_RIGHTS);
		JSONObject title = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_TITLE);
		JSONObject link = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_LINK);
		JSONObject id = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_ID);
		JSONObject imArtist = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_IM_ARTIST);
		JSONObject category = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_CATEGORY);
		JSONObject imReleaseDate = objetoAplicacion.getJSONObject(ClassConstantes.MODULO_APLICACIONES_IM_RELEASE_DATE);
		
		
		ArrayList<ClassElementoBO> imagenesBO = new ArrayList<ClassElementoBO>();
		for(int contadorImagenes = 0; contadorImagenes < imImage.length() ; contadorImagenes++){
			JSONObject objetoImagen = imImage.getJSONObject(contadorImagenes);
			ClassElementoBO imagenBO = new ClassElementoBO(objetoImagen.getString(ClassConstantes.MODULO_APLICACIONES_LABEL), objetoImagen.getJSONObject(ClassConstantes.MODULO_APLICACIONES_ATTRIBUTES));
			String urlImagen = imagenBO.getLabel();
			Bitmap bitmap;
			try{
				bitmap =  ClassGenericos.obtenerBitmapDeURL(urlImagen);
				boolean almacenado = ClassGenericos.guardarImagen(bitmap, imName.getString(ClassConstantes.MODULO_APLICACIONES_LABEL)+contadorImagenes);
			}catch(Exception e){
				bitmap = ClassGenericos.obtenerImagen(imName.getString(ClassConstantes.MODULO_APLICACIONES_LABEL)+contadorImagenes);
			}
			
			
			imagenBO.setImagen(bitmap);
			imagenesBO.add(imagenBO);
		}
		
		ClassAplicacionBO aplicacionBO = new ClassAplicacionBO(0,
				empaquetarObjetoElemento(imName), 
				imagenesBO, 
				empaquetarObjetoElemento(summary), 
				empaquetarObjetoElemento(imPrice), 
				empaquetarObjetoElemento(imContentType), 
				empaquetarObjetoElemento(rights), 
				empaquetarObjetoElemento(title), 
				empaquetarObjetoElemento(link), 
				empaquetarObjetoElemento(id), 
				empaquetarObjetoElemento(imArtist), 
				empaquetarObjetoElemento(category), 
				empaquetarObjetoElemento(imReleaseDate));
		
		agregarCategoria(category);
		
		return aplicacionBO;
	}
	
	private void agregarCategoria(JSONObject objetoCategoria){
		int cantidad = listadoCategorias.size();
		try {
			JSONObject attributes = objetoCategoria.getJSONObject(ClassConstantes.MODULO_APLICACIONES_ATTRIBUTES);
			ClassCategoriaBO categoriaBO = new ClassCategoriaBO(
					0, 
					attributes.getString(ClassConstantes.MODULO_APLICACIONES_IM_ID), 
					attributes.getString(ClassConstantes.MODULO_APLICACIONES_TERM), 
					attributes.getString(ClassConstantes.MODULO_APLICACIONES_SCHEME), 
					attributes.getString(ClassConstantes.MODULO_APLICACIONES_LABEL));
			if (cantidad == 0){
					listadoCategorias.add(categoriaBO);
				
			}else{
				boolean bandera = true;
				for (ClassCategoriaBO categoriaExistenteBO : listadoCategorias){
					if (categoriaExistenteBO.getImId().equals(categoriaBO.getImId()) ){
						bandera = false;
					}
				}
				
				if (bandera){
					listadoCategorias.add(categoriaBO);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private ClassElementoBO empaquetarObjetoElemento(JSONObject objetoElemento){
		String label = null;
		JSONObject attributes = null;
		try{
			label = objetoElemento.getString(ClassConstantes.MODULO_APLICACIONES_LABEL);
			
		}catch(JSONException e){
			
		}
		
		try{
			attributes = objetoElemento.getJSONObject(ClassConstantes.MODULO_APLICACIONES_ATTRIBUTES);
		}catch(JSONException e){
			
		}
		
		
		return new ClassElementoBO(label, attributes);
		
	}
	
}
