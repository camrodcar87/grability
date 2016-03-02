package com.camilo.catalogo.moduloaplicaciones;

import java.util.ArrayList;

import org.json.JSONObject;

import android.graphics.Bitmap;

public class ClassElementoBO {
	private String label = "";
	private JSONObject attributes;
	private Bitmap imagen ;
	
	public ClassElementoBO(String label, JSONObject attributes) {
		super();
		this.label = label;
		this.attributes = attributes;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public JSONObject getAttributes() {
		return attributes;
	}
	public void setAttributes(JSONObject attributes) {
		this.attributes = attributes;
	}
	public Bitmap getImagen() {
		return imagen;
	}
	public void setImagen(Bitmap imagen) {
		this.imagen = imagen;
	}
	
	
}
