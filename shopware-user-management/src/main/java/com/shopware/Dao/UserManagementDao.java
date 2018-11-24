package com.shopware.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopware.request.FetchProfileRequest;
import com.shopware.request.LoginRequest;
import com.shopware.request.Registration;
import com.shopware.request.WalletDetailsRequest;
import com.shopware.response.LoginResponse;
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
		registration.getRegistrationRequest().getSourceInfoRequest()
				.setSourceInfo(registration.getRegistrationRequest());

		registration.setRegistrationRequest(registration.getRegistrationRequest());

		getSession().save(registration.getRegistrationRequest());

	}

	public void walletCreation(List<WalletDetailsRequest> walletDetailsList) {
		getSession().save(walletDetailsList);
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

	public List<LoginResponse> login(LoginRequest loginRequest, String type) {
		Criteria query = getSession().createCriteria(LoginResponse.class);
		if (type.equalsIgnoreCase("email") || type.equalsIgnoreCase("user_Name")) {
			query.add(Restrictions.eq(type, loginRequest.getUserId()));
		} else {
			query.add(Restrictions.eq(type, Long.parseLong(loginRequest.getUserId())));
		}
		return query.list();
	}

	public List<LoginResponse> fetchMsisdn(String value, String type) {
		Criteria query = getSession().createCriteria(LoginResponse.class);
		query.add(Restrictions.eq(type, Long.parseLong(value)));
		return query.list();
	}

	public int updateBlockedTime(String blockedTime, long msisdn, int status) {
		System.out.println("blockedTime   " + blockedTime);
		Query query = getSession().createQuery(
				"UPDATE RegistrationRequest set blocked_time = :blockedTime, status = :status where MSISDN = :msisdn");
		query.setString("blockedTime", blockedTime);
		query.setLong("msisdn", msisdn);
		query.setParameter("status", status);
		return query.executeUpdate();
	}

	public int updateLoginFailedCountTime(long msisdn, int loginFailCount) {
		Query query = getSession().createQuery(
				"UPDATE RegistrationRequest set login_fail_count = :loginFailCount where MSISDN = :msisdn");
		query.setLong("msisdn", msisdn);
		query.setInteger("loginFailCount", loginFailCount);
		return query.executeUpdate();
	}

}
