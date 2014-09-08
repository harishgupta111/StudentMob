package com.student.app.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.student.app.core.dao.IClientMasterDao;
import com.student.app.core.model.ClientMaster;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:/applicationContextCore-Test.xml" })
@Transactional(readOnly = true)
public class ClientMasterDaoHibernateTest {
	
	@Autowired
	private IClientMasterDao iClientMasterDao;

//	private Logger logger = Logger.getLogger(this.getClass());

	@Test
	public void test_getById_ShouldGet() {
		ClientMaster o = this.iClientMasterDao.getById(1L);
		org.junit.Assert.assertNotNull(o);
		org.junit.Assert.assertEquals("",
				o.getClientId().longValue(), 1L);
	}

	@Test
	public void test_findByClientCode_ShouldGet() {
		ClientMaster o = this.iClientMasterDao.findByClientCode("ABCD");
		org.junit.Assert.assertNotNull(o);
		org.junit.Assert.assertEquals("",
				o.getClientId().longValue(), 1L);
		org.junit.Assert.assertEquals("",
				o.getClientName(), "Test Client1");
	}


}
