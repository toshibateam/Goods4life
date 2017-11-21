package com.g4life.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.g4life.dto.AccountInfo;
import com.g4life.dto.ProductInfo;
import com.g4life.util.HibernateUtil;

public class SellerInfoAccess {
	public List<AccountInfo> getAllAccount() {
		try {
			// Open session
			Session session = HibernateUtil.getSessionFactory().openSession();

			Criteria criteria = session.createCriteria(ProductInfo.class).addOrder(Order.asc("accountID"));

			criteria.setCacheable(true);
			@SuppressWarnings("unchecked")
			List<AccountInfo> accountList = criteria.list();

			// Close session
			session.close();
			return accountList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
