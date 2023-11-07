package com.bus.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies_details")
public class MovieDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_id")
	private long movieId;
	
	@Column(nullable = false)
	private String movieName;
	
	@Column(nullable = false)
	private String image;
	
	@Column(name = "movie_details", nullable = false)
	private String Description; 
	
	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}


	public MovieDetails(long movieId, String movieName, String image, String description) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.image = image;
		this.Description = description;
		
	}

	public MovieDetails(String movieName, String image, String description) {
		super();
		this.movieName = movieName;
		this.image = image;
		this.Description = description;
		
	}

	public MovieDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MovieDetails [movieId=" + movieId + ", movieName=" + movieName + ", image=" + image + ", Description="
				+ Description + "]";
	}
	
	
	


}