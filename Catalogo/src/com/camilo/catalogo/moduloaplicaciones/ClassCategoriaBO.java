package com.camilo.catalogo.moduloaplicaciones;

public class ClassCategoriaBO {
	private int idBaseDatos; 
	private String imId;
	private String term;
	private String scheme;
	private String label;
	public ClassCategoriaBO(int idBaseDatos, String imId, String term,
			String scheme, String label) {
		super();
		this.idBaseDatos = idBaseDatos;
		this.imId = imId;
		this.term = term;
		this.scheme = scheme;
		this.label = label;
	}
	public int getIdBaseDatos() {
		return idBaseDatos;
	}
	public void setIdBaseDatos(int idBaseDatos) {
		this.idBaseDatos = idBaseDatos;
	}
	public String getImId() {
		return imId;
	}
	public void setImId(String imId) {
		this.imId = imId;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
