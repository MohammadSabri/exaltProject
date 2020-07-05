package com.exalt.petclinic.projection;

import java.util.Date;

public interface PetProjection {
	int getId();
	String getName();
	int getAge();
	String getSpecies();
	double getHeight();
	double getWeight();
	Date getCreationDate();
	
}
