package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Actor;
import model.Artist;
import model.Director;
import model.Genre;
import model.Movie;
import model.Rating;

public class DAOClass {

	private static EntityManagerFactory emf;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("cs544");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public void addMovie(){
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			Movie m1 = new Movie("pay it forward",2000,"Based on true story", Rating.Excellent, Genre.Drama);
			m1.addComments("Loved this movie");
			m1.addComments("Best movie i have ever seen");
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Artist actor1 = new Actor("Tony","Tom Hanks","Iowa",sdf.parse("10/20/1965"));
			Artist director1 = new Director("James Cameroon","Chicago",sdf.parse("11/15/1970"));
			m1.addArtists(actor1);
			m1.addArtists(director1);
			
			
			Movie m2 = new Movie("Beautiful mind",2001,"Story of Mathematician", Rating.Excellent, Genre.Drama);
			m2.addComments("Such an inspirational movie");
			
			Artist actor2 = new Actor("David","Russel Crow","NewYork",sdf.parse("10/01/1960"));
			Artist director2 = new Director("Cambridge","Louisianna",sdf.parse("11/23/1975"));
			m2.addArtists(actor2);
			m2.addArtists(director2);
			
			em.persist(m1);
			em.persist(m2);
			
			tx.commit();
		}catch (Throwable e) {
			System.out.println("in catch===="+e.getMessage());
			if ((tx != null) && (tx.isActive())) tx.rollback();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
	
	public void Search(){
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			//Selecting all movies;
			Query q = em.createQuery("from Movie");
			List<Movie> movies = q.getResultList();
			System.out.println("Id=======Movie_Title========Summary==============Year=======Genre=========Rating");
			for(Movie m:movies){
				System.out.println(m.getId()+"======="+m.getName()+"======="+m.getSummary()+"====="+m.getYear()+"===="+m.getGenre()+"===="+m.getRating());
			}
			
			//Selecting movie by name;
			q =em.createNamedQuery("searchMovieByName");
			q.setParameter("name", "pay it forward");
			Movie m =(Movie) q.getSingleResult();
			System.out.println();
			System.out.println("Searching movie by name result==>");
			System.out.println(m.getId()+"======="+m.getName()+"======="+m.getSummary()+"====="+m.getYear()+"===="+m.getGenre()+"===="+m.getRating());
			
			//Searching movie by Genre;
			q =em.createNamedQuery("searchMovieByGenre");
			q.setParameter("genre", Genre.Drama);
			List<Movie> mList = q.getResultList();
			System.out.println();
			System.out.println("Searching movie by genre result==>");
			System.out.println("Id=======Movie_Title========Summary==============Year=======Genre=========Rating");
			for(Movie mm:mList){
				System.out.println(mm.getId()+"======="+mm.getName()+"======="+mm.getSummary()+"====="+mm.getYear()+"===="+mm.getGenre()+"===="+mm.getRating());
			}
			
			//Searching movie by actor
			q = em.createQuery("select distinct M.name,M.year,A.name from Movie M join M.artists A where A.name=:name");
			q.setParameter("name", "Tom Hanks");
			List<Object[]> moviesByActor = q.getResultList();
			System.out.println();
			System.out.println("Searching movie by Artist name==>");
			System.out.println("Movie_Title========Movie_Year=======Artist_Name");
			for(Object[] obj:moviesByActor){
				System.out.print(obj[0]+"======="+obj[1]+"======="+obj[2]);
			}
	
			
			tx.commit();
		}catch (Throwable e) {
			System.out.println("in catch===="+e.getMessage());
			if ((tx != null) && (tx.isActive())) tx.rollback();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
		
		
	}
}
