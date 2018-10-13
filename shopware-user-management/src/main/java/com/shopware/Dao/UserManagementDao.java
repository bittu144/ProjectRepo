package com.shopware.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopware.request.Registration;

@Repository
@Transactional
public class UserManagementDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {

		return sessionFactory.getCurrentSession();
	}

	public void registrationDao(Registration registration) {
		registration.getRegistrationRequest().getTempAddressRequest()
				.setTempRequest(registration.getRegistrationRequest());
		registration.getRegistrationRequest().getPermanentAddressRequest()
				.setPerRequest(registration.getRegistrationRequest());

		registration.getRegistrationRequest().getTxnInfo().setTransactionInfo(registration.getRegistrationRequest());

		for (int i = 0; i < registration.getRegistrationRequest().getInfo().size(); i++) {
			registration.getRegistrationRequest().getInfo().get(i).setOtherInfo(registration.getRegistrationRequest());
		}

		registration.setRegistrationRequest(registration.getRegistrationRequest());

		getSession().save(registration.getRegistrationRequest());

	}

}
