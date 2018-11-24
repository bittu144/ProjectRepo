package com.shopware.Dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopware.request.WalletDetailsRequest;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class BalanceManagementDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {

		return sessionFactory.getCurrentSession();
	}

	public List<WalletDetailsRequest> userWalletDetails(long msisdn, String walletType) {
		Criteria query = getSession().createCriteria(WalletDetailsRequest.class);
		query.add(Restrictions.eq("msisdn", msisdn));
		query.add(Restrictions.eq("walletType", walletType));
		return query.list();
	}

	public int updateBalance(BigDecimal balance, long walletId) {
		Query query = getSession()
				.createQuery("UPDATE RegistrationRequest set BALANCE = :balance where WALLET_ID = :walletId");
		query.setParameter("balance", balance);
		query.setLong("walletId", walletId);
		return query.executeUpdate();
	}

}
