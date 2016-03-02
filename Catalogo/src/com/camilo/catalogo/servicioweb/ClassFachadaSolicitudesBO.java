package com.camilo.catalogo.servicioweb;

import java.util.ArrayList;

import org.json.JSONObject;

import com.camilo.catalogo.moduloaplicaciones.ClassAplicacionBO;
import com.camilo.catalogo.moduloaplicaciones.ClassCategoriaBO;

public class ClassFachadaSolicitudesBO {
	private Boolean banderaServicioWeb = false;
	private JSONObject objetoRespuesta;
	ArrayList<ClassAplicacionBO> listadoAplicaciones;
	ArrayList<ClassCategoriaBO> listadoCategorias;
	
	public Boolean isBanderaServicioWeb() {
		return banderaServicioWeb;
	}
	public void setBanderaServicioWeb(Boolean banderaServicioWeb) {
		this.banderaServicioWeb = banderaServicioWeb;
	}
	public JSONObject getObjetoRespuesta() {
		return objetoRespuesta;
	}
	public void setObjetoRespuesta(JSONObject objetoRespuesta) {
		this.objetoRespuesta = objetoRespuesta;
	}
	public ArrayList<ClassAplicacionBO> getListadoAplicaciones() {
		return listadoAplicaciones;
	}
	public void setListadoAplicaciones(
			ArrayList<ClassAplicacionBO> listadoAplicaciones) {
		this.listadoAplicaciones = listadoAplicaciones;
	}
	public ArrayList<ClassCategoriaBO> getListadoCategorias() {
		return listadoCategorias;
	}
	public void setListadoCategorias(ArrayList<ClassCategoriaBO> listadoCategorias) {
		this.listadoCategorias = listadoCategorias;
	}
	
	
	
}
