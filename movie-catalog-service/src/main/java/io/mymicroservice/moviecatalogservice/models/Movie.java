package io.mymicroservice.moviecatalogservice.models;

import java.io.Serializable;

public class Movie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6833439042742663154L;
	private String name;
	private String movieId;
	private String desc;
	public Movie(String id, String name, String desc) {
		super();
		this.name = name;
		this.movieId = id;
		this.desc = desc;
	}
	public Movie() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return movieId;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
