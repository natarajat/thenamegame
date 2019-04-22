/**
 * 
 */
package com.mypocs.thenamegame.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Raj Thuppanna
 *
 */
@Entity
@Table(name = "headshot")
public class Headshot {

	@Id
	private String id;
	private String type;
	private String mimeType;
	private String url;
	private String alt;
	private int height;
	private int width;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	@Override
	public String toString() {
		return "Headshot [id=" + id + ", type=" + type + ", mimeType=" + mimeType + ", url=" + url + ", alt=" + alt
				+ ", height=" + height + ", width=" + width + "]";
	}
	
	
}
