package com.camilo.catalogo.moduloaplicaciones;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;


public class ClassAplicacionBO {
	private int idBaseDatos; 
	
	private ClassElementoBO imName;
	private ArrayList<ClassElementoBO> imImage;
	private ClassElementoBO summary;
	private ClassElementoBO imPrice;
	private ClassElementoBO imContentType;
	private ClassElementoBO rights;
	private ClassElementoBO title;
	private ClassElementoBO link;
	private ClassElementoBO id;
	private ClassElementoBO imArtist;
	private ClassElementoBO category;
	private ClassElementoBO imReleaseDate;
	
	
	public ClassAplicacionBO(int idBaseDatos,ClassElementoBO imName,
			ArrayList<ClassElementoBO> imImage,
			ClassElementoBO summary,
			ClassElementoBO imPrice,
			ClassElementoBO imContentType,
			ClassElementoBO rights,
			ClassElementoBO title,
			ClassElementoBO link, ClassElementoBO id,
			ClassElementoBO imArtist,
			ClassElementoBO category,
			ClassElementoBO imReleaseDate) {
		super();
		this.idBaseDatos = idBaseDatos;
		this.imName = imName;
		this.imImage = imImage;
		this.summary = summary;
		this.imPrice = imPrice;
		this.imContentType = imContentType;
		this.rights = rights;
		this.title = title;
		this.link = link;
		this.id = id;
		this.imArtist = imArtist;
		this.category = category;
		this.imReleaseDate = imReleaseDate;
	}

	public ClassElementoBO getImName() {
		return imName;
	}

	public void setImName(ClassElementoBO imName) {
		this.imName = imName;
	}

	public ArrayList<ClassElementoBO> getImImage() {
		return imImage;
	}

	public void setImImage(ArrayList<ClassElementoBO> imImage) {
		this.imImage = imImage;
	}

	public ClassElementoBO getSummary() {
		return summary;
	}

	public void setSummary(ClassElementoBO summary) {
		this.summary = summary;
	}

	public ClassElementoBO getImPrice() {
		return imPrice;
	}

	public void setImPrice(ClassElementoBO imPrice) {
		this.imPrice = imPrice;
	}

	public ClassElementoBO getImContentType() {
		return imContentType;
	}

	public void setImContentType(ClassElementoBO imContentType) {
		this.imContentType = imContentType;
	}

	public ClassElementoBO getRights() {
		return rights;
	}

	public void setRights(ClassElementoBO rights) {
		this.rights = rights;
	}

	public ClassElementoBO getTitle() {
		return title;
	}

	public void setTitle(ClassElementoBO title) {
		this.title = title;
	}

	public ClassElementoBO getLink() {
		return link;
	}

	public void setLink(ClassElementoBO link) {
		this.link = link;
	}

	public ClassElementoBO getId() {
		return id;
	}

	public void setId(ClassElementoBO id) {
		this.id = id;
	}

	public ClassElementoBO getImArtist() {
		return imArtist;
	}

	public void setImArtist(ClassElementoBO imArtist) {
		this.imArtist = imArtist;
	}

	public ClassElementoBO getCategory() {
		return category;
	}

	public void setCategory(ClassElementoBO category) {
		this.category = category;
	}

	public ClassElementoBO getImReleaseDate() {
		return imReleaseDate;
	}

	public void setImReleaseDate(ClassElementoBO imReleaseDate) {
		this.imReleaseDate = imReleaseDate;
	}

	public int getIdBaseDatos() {
		return idBaseDatos;
	}

	public void setIdBaseDatos(int idBaseDatos) {
		this.idBaseDatos = idBaseDatos;
	}

	
	
}
