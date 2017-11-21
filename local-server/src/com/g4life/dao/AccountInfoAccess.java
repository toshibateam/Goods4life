package com.g4life.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.g4life.dto.AccountInfo;
import com.g4life.util.HibernateUtil;

public class AccountInfoAccess {
	public List<AccountInfo> getAllAccount() {
		try {
			// Open session
			Session session = HibernateUtil.getSessionFactory().openSession();

			Criteria criteria = session.createCriteria(AccountInfo.class).addOrder(Order.asc("accountID"));

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
	public void insertOrUpdate(AccountInfo data) {

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(data);
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	public AccountInfo getById(int id) {

		AccountInfo account = null;
		try {
			
			// Open session
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(AccountInfo.class).add(Restrictions.eq("accountID", id));
			criteria.setCacheable(true);
			@SuppressWarnings("unchecked")
			List<AccountInfo> result = criteria.list();
			// Close session
			session.close();
			
			if (result.size() == 1) {
				account = result.get(0);
			}

			return account;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public AccountInfo getByMail(String mail) {

		AccountInfo account = null;
		try {			
			// Open session
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(AccountInfo.class).add(Restrictions.eq("mail", mail));
			criteria.setCacheable(true);
			@SuppressWarnings("unchecked")
			List<AccountInfo> result = criteria.list();
			// Close session
			session.close();
			
			if (result.size() == 1) {
				account = result.get(0);
				return account;
			}else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public AccountInfo getByPhoneNumber(String phone) {

		AccountInfo account = null;
		try {
			
			// Open session
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(AccountInfo.class).add(Restrictions.eq("phoneNumber", phone));
			criteria.setCacheable(true);
			@SuppressWarnings("unchecked")
			List<AccountInfo> result = criteria.list();
			// Close session
			session.close();
			
			if (result.size() == 1) {
				account = result.get(0);
				return account;
			}else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public AccountInfo getByUserName(String userName) {

		AccountInfo account = null;
		try {
			
			// Open session
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(AccountInfo.class).add(Restrictions.eq("userName", userName));
			criteria.setCacheable(true);
			@SuppressWarnings("unchecked")
			List<AccountInfo> result = criteria.list();
			// Close session
			session.close();
			
			if (result.size() == 1) {
				account = result.get(0);
				return account;
			}else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void deleteAccount(AccountInfo data) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(data);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		AccountInfoAccess access = new AccountInfoAccess();
//		AccountInfo accountInfo = new AccountInfo(1,"duan","23071993","01656080521","","",ValueConstant.SING_IN_BY_ACCOUNT);
//		access.insertOrUpdate(accountInfo);
	}
}
