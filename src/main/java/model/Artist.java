package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Artist_Type",discriminatorType=DiscriminatorType.STRING)
@NamedQueries({
	@NamedQuery(name="searchArtistByName",query="select a from Artist a where a.name=:name"),
	@NamedQuery(name="searchArtistByCharName",query="select a from Artist a where a.characterName=:chName")
	
})
public abstract class Artist {

	@Id @GeneratedValue
	private long id;
	private String name;
	private String placeOfBirth;
	
	public Artist(){}
	
	public Artist(String name,String placeOfBirth,Date dob){
		this.name = name;
		this.placeOfBirth = placeOfBirth;
		this.dob=dob;
	}
	
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Lob
	private Byte[] picture;
	
	public void setPicture(Byte[] picture) {
		this.picture = picture;
	}
	
	
	@ManyToMany(mappedBy="artists")
	private List<Movie> movies = new ArrayList<Movie>();


	
	
}
