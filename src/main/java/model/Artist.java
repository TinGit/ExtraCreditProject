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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Artist_Type",discriminatorType=DiscriminatorType.STRING)
public abstract class Artist {

	@Id @GeneratedValue
	private long id;
	private String name;
	private String placeOfBirth;
	
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Lob
	private Byte[] picture;
	
	
	private List<Movie> movies = new ArrayList<Movie>();
	
}
