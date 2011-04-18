package tw.elliot.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateAnnCfgTest {
	
	private static final Logger logger = LoggerFactory.getLogger(HibernateAnnCfgTest.class);

	@Test
	public void testLoadAnn() {
		SessionFactory sf = new Configuration().configure("hibernate_ann.cfg.xml").buildSessionFactory();
		Assert.assertNotNull(sf);
		
		Session session = sf.openSession();
		Query query = session.createQuery("FROM Person");
		List list = query.list();
		Assert.assertFalse(list.isEmpty());
	}
}
