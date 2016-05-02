package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Actor")
public class Actor extends Artist{

	private String characterName;
}
