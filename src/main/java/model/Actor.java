package model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Actor")
public class Actor extends Artist{

	private String characterName;
	
	public Actor(String characterName,String name,String placeOfBirth,Date dob){
		super(name,placeOfBirth,dob);
		this.characterName=characterName;	
	}
}
