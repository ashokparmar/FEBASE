package helper;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pojo.CustomerReviews;
import pojo.PosTaggedSentences;
import pojo.ProductFeatures;


public class DBHelper {
	
	public static final String url = "jdbc:mysql://localhost:3306/";
	public static final String dbName = "FEBASE";
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String userName = "root";
	public static final String password = "root";
	private static SessionFactory factory;
	
	public static void main(String[] args) {
        init();
		getProductFeatures("Sony-Xperia-Tipo");
		getCustomerReviews("Sony-Xperia-Tipo"); 
	}
	
	public static void init() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object.");
			ex.printStackTrace();
		}
	}
		
	public static List<ProductFeatures> getProductFeatures(String productId) {
		List<ProductFeatures> productFeatures = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM ProductFeatures where productFeaturesPK.productId = :productId");
			query.setParameter("productId", productId);
			productFeatures = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return productFeatures;
	}
	
	public static void storeProductFeatures(ProductFeatures record) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			session.saveOrUpdate(record);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) 
				tx.rollback();
			session.close();
		}
	}
	
	public static List<CustomerReviews> getCustomerReviews(String productId) {
		List<CustomerReviews> customerReviews = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM CustomerReviews where productId = :productId");
			query.setParameter("productId", productId);
			customerReviews = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return customerReviews;
	}
	
	public static List<PosTaggedSentences> getPosTaggedSentences() {
		List<PosTaggedSentences> posTaggedSentences = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM PosTaggedSentences");
			posTaggedSentences = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	/*
	 * public static void initializeConnection() {
		System.out.println("MySQL Connect Example.");
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "FEBASE";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "root";
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    conn = DriverManager.getConnection(url,userName,password);
		    
		    System.out.println("Connected to the database");
		    conn.close();
		    System.out.println("Disconnected from database");
		} catch (Exception e) {
		        e.printStackTrace();
		}
	}
	
	public static Connection getConnection () {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "FEBASE";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "root";
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    conn = DriverManager.getConnection(url,userName,password);
		    
		    System.out.println("Connected to the database");
		    conn.close();
		    System.out.println("Disconnected from database");
		} catch (Exception e) {
		        e.printStackTrace();
		}
		return null;
	}
	
	public static List<Object> executeSelect(String sqlQuery) {
		Connection conn;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName, userName, password);
		    Statement stmt = conn.createStatement();
		    ResultSet result = stmt.executeQuery(sqlQuery);
		    if (result != null) {
		    	while (result.next()) {
		    		
		    		System.out.println(result.getString(1));
		    	}
		    }
		    conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		return null;
	}
	 */
}
