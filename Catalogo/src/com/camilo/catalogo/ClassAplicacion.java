package com.camilo.catalogo;

import java.util.ArrayList;

import com.camilo.catalogo.moduloaplicaciones.ClassAplicacionBO;
import com.camilo.catalogo.moduloaplicaciones.ClassCategoriaBO;

import android.app.Application;

public class ClassAplicacion extends Application{
	private ArrayList<ClassAplicacionBO> listadoAplicaciones;
	private ArrayList<ClassCategoriaBO> listadoCategorias;

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
