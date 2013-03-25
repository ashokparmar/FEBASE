package helper;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pojo.CustomerReviewSentences;
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
	private static boolean initialized = false;
	public static void main(String[] args) {
        init();
		getProductFeatures("Sony-Xperia-Tipo");
		//getCustomerReviews("Sony-Xperia-Tipo"); 
	}
	
	public static void init() {
		if (!initialized) {
			try {
				factory = new Configuration().configure().buildSessionFactory();
				initialized = true;
			} catch (Throwable ex) {
				System.err.println("Failed to create sessionFactory object.");
				ex.printStackTrace();
			}
		}
	}
		
	public static List<ProductFeatures> getProductFeatures(String productId) {
		init();
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
		init();
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
		init();
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
	
	public static List<CustomerReviewSentences> getAllCustomerReviewSentences() {
		init();
		List<CustomerReviewSentences> customerReviews = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM CustomerReviewSentences");
			customerReviews = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return customerReviews;
	}
	
	public static List<CustomerReviewSentences> getCustomerReviewSentences(String productId) {
		init();
		List<CustomerReviewSentences> customerReviewSentences = null;
		Session session = factory.openSession();
		String sql = "select sen.sentence_id, sen.review_id, sen.original_sentence, "
				+ "sen.status, sen.creation_date, sen.last_updated from "
				+ "CUSTOMER_REVIEW_SENTENCES sen inner join CUSTOMER_REVIEWS rev on sen.review_id = rev.review_id where "
				+ "rev.product_id = '" + productId + "'" ;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(CustomerReviewSentences.class);
			customerReviewSentences = sqlQuery.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return customerReviewSentences;
	}
	
	public static void saveCustomerReviewSentences(CustomerReviewSentences sentenceObj) {
		init();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			session.saveOrUpdate(sentenceObj);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) 
				tx.rollback();
			session.close();
		}
	}
	
	public static List<PosTaggedSentences> getPosTaggedSentences() {
		init();
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
		return posTaggedSentences;
	}
	
	public static List<PosTaggedSentences> getPosTaggedSentences(String productId) {
		List<PosTaggedSentences> posTaggedSentences = null;
		Session session = factory.openSession();
		String sql = "select pos.sentence_id, pos.pos_tagged_sentence, pos.nouns, pos.adjectives, " +
				"pos.adverbs, pos.semantic_score, pos.related_feature_name, pos.status, pos.creation_date, " +
				"pos.last_updated from POS_TAGGED_SENTENCES pos inner join CUSTOMER_REVIEW_SENTENCES sen on " +
				"pos.sentence_id = sen.sentence_id inner join CUSTOMER_REVIEWS cr on sen.review_id = cr.review_id " +
				"where cr.product_id = '" + productId + "'" ;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(PosTaggedSentences.class);
			posTaggedSentences = sqlQuery.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return posTaggedSentences;
	}
	
	public static void savePosTaggedSentences(PosTaggedSentences posRecord) {
		init();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			session.saveOrUpdate(posRecord);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) 
				tx.rollback();
			session.close();
		}
	}
	
	public static void savePosTaggedSentencesList(ArrayList<PosTaggedSentences> posRecords) {
		init();
		Session session = factory.openSession();
		Transaction tx = null;		
		try {
			for (PosTaggedSentences posRecord : posRecords) {
				tx = session.beginTransaction();
				tx.begin();
				session.saveOrUpdate(posRecord);
				tx.commit();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) 
				tx.rollback();
			session.close();
		}
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
