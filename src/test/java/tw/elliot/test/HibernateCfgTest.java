package tw.elliot.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.elliot.domain.Gender;
import tw.elliot.domain.Person;

public class HibernateCfgTest {
	
	private static final Logger logger = LoggerFactory.getLogger(HibernateCfgTest.class);

	@Test
	public void testLoadCfg() {
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Assert.assertNotNull(sf);
		
		Session session = sf.openSession();
		//HQL
		Query query = session.createQuery("FROM Person WHERE adult = ?");
		query.setBoolean(0, Boolean.FALSE);
		List list = query.list();
		logger.info("HQL Query FROM Persion's size:[{}]", list.size());
		Assert.assertFalse(list.isEmpty());
		
		//Criteria
		Criteria criteria = session.createCriteria(Person.class);
		criteria.add(Restrictions.eq("adult", Boolean.FALSE));
		list = criteria.list();
		logger.info("Criteria Query FROM Persion's size:[{}]", list.size());
		Assert.assertFalse(list.isEmpty());
		
		//Criteria with Example
		Person example = new Person();
		example.setAge(10);
		criteria = session.createCriteria(Person.class);
		criteria.add(Example.create(example));
		list = criteria.list();
		logger.info("Example Query FROM Persion's size:[{}]", list.size());
		Assert.assertFalse(list.isEmpty());
		
		//Create Entity
		Person person = new Person();
		person.setAdult(Boolean.FALSE);
		person.setAge(10);
		person.setBirthday(new Date());
		person.setGender(Gender.Female);
		person.setName("Kate");
		person.setRocId("B211");
		
		//Save
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(person);
		transaction.commit();
		
		logger.info("Check Created Person's ID:[{}]");
		
		//Update
		transaction = session.beginTransaction();
		
		
		session.flush();
		session.close();
	}
	
}
