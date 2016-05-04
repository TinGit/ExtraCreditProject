package control;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DAOClass;

public class MainApp {


	public static void main(String[] args) {
		DAOClass dao = new DAOClass();
	
		dao.addMovie();
		dao.Search(); //Searching query
	
	}

}
