package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Movie {

	@Id @GeneratedValue
	private long id;
	private String name;
	private int year;
	private String summary;
	
	@Lob
	private Byte[] poster;
	
	@Enumerated(EnumType.STRING)
	private Rating rating;
	
	@Enumerated(EnumType.STRING)
	private Genre genre;
	
	@ManyToMany
	@JoinTable(name="Movie_Artist",
			joinColumns=@JoinColumn(name ="movie_Id"),
			inverseJoinColumns=@JoinColumn(name="artist_Id"))
	private List<Artist> artists=new ArrayList<Artist>();
	
	@ElementCollection
	private List<String> comments=new ArrayList<String>();
}
