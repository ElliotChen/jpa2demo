package tw.elliot.test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tw.elliot.domain.Gender;
import tw.elliot.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:jpaApplicationContext.xml" })
@Transactional
public class SpringJpaTest {
	@Autowired
	private EntityManager entityManager;
	
	@Test
	public void testLoad() {
		Assert.assertNotNull(entityManager);
	}
	
	@Test
	@Rollback(false)
	public void test() {
		Query query = entityManager.createQuery("FROM Person");
		List list = query.getResultList();
		Assert.assertFalse(list.isEmpty());
		
		//Create Entity
		Person person = new Person();
		person.setAdult(Boolean.FALSE);
		person.setAge(10);
		person.setBirthday(new Date());
		person.setGender(Gender.Female);
		person.setName("Jpa");
		person.setRocId("B211");
		
		entityManager.persist(person);
	}
}
