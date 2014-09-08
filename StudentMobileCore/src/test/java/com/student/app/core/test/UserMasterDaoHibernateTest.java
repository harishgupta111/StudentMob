package com.student.app.core.test;

import org.junit.Assert;
import org.junit.Before;
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
import com.student.app.core.dao.IUserMasterDao;
import com.student.app.core.model.ClientMaster;
import com.student.app.core.model.UserMaster;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:/applicationContextCore-Test.xml" })
@Transactional(readOnly = true)
public class UserMasterDaoHibernateTest {

	// private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	@Autowired
	private IClientMasterDao iClientMasterDao;
	
	@Autowired
	private IUserMasterDao iUserMasterDao;
	private ClientMaster clientMaster;
	private UserMaster userMaster;
	
	@Before
	public void loadMasterObjects(){
		clientMaster = this.iClientMasterDao.getById(1L);
		userMaster = this.iUserMasterDao.getById(1L);
	}
	
	@Test
	public void test_getById_shouldGet() {
		Assert.assertNotNull(this.iUserMasterDao.getById(1L));
	}


}
