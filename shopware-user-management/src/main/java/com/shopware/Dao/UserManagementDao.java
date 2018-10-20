package com.shopware.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopware.request.FetchProfileRequest;
import com.shopware.request.Registration;
import com.shopware.response.PerAddressResponse;
import com.shopware.response.ProfileResponse;

@Repository
@Transactional
@SuppressWarnings("unchecked")
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

		/*
		 * for (int i = 0; i < registration.getRegistrationRequest().getInfo().size();
		 * i++) { registration.getRegistrationRequest().getInfo().get(i).setOtherInfo(
		 * registration.getRegistrationRequest()); }
		 */
		registration.setRegistrationRequest(registration.getRegistrationRequest());

		getSession().save(registration.getRegistrationRequest());

	}

	public List<ProfileResponse> getProfile(FetchProfileRequest profileRequest) {

		Criteria query = getSession().createCriteria(ProfileResponse.class);

		if (profileRequest.getUserId() != null)
			query.add(Restrictions.eq("msisdn", Long.parseLong(profileRequest.getUserId())));

		return query.list();
	}

	public List<PerAddressResponse> getAddress(String id) {

		Criteria query = getSession().createCriteria(PerAddressResponse.class);

		if (id != null)
			query.add(Restrictions.eq("msisdn", Long.parseLong(id)));

		return query.list();
	}

}
