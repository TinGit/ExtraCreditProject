package model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Director")
public class Director extends Artist{

	public Director(String name,String placeOfBirth,Date dob){
		super(name,placeOfBirth,dob);
	}
}
