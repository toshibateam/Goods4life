package com.g4life.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.NewsAddress;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.g4life.dto.ProductInfo;
import com.g4life.util.HibernateUtil;

public class ProductInfoAccess {
	
	public List<ProductInfo> getAllProduct() {
		try {
			// Open session
			Session session = HibernateUtil.getSessionFactory().openSession();

			Criteria criteria = session.createCriteria(ProductInfo.class).addOrder(Order.asc("productNo"));

			criteria.setCacheable(true);
			@SuppressWarnings("unchecked")
			List<ProductInfo> productList = criteria.list();

			// Close session
			session.close();

			return productList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void insertOrUpdate(ProductInfo data) {

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
	public List<ProductInfo> getByListAccountId(int accountId) {
		List<ProductInfo> systems = new ArrayList<>();

		try {
			// Open session
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(ProductInfo.class).add(Restrictions.eq("accountID", accountId));
			criteria.setCacheable(true);
			@SuppressWarnings("unchecked")
			List<ProductInfo> result = criteria.list();
			// Close session
			session.close();

			if (result.size() > 0) {
				systems = result;
			}

			return systems;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<ProductInfo> getByListProductNo(int productNo) {
		List<ProductInfo> systems = new ArrayList<>();

		try {
			
			// Open session
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(ProductInfo.class).add(Restrictions.eq("productNo", productNo));
			criteria.setCacheable(true);
			@SuppressWarnings("unchecked")
			List<ProductInfo> result = criteria.list();
			// Close session
			session.close();

			if (result.size() > 0) {
				systems = result;
			}

			return systems;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<ProductInfo> getByListAccoutnId(List<Integer> listAccountId) {
		List<ProductInfo> systems = new ArrayList<>();

		try {
			// Check list id empty
			if (listAccountId.size() == 0) {
				return systems;
			}
			// Open session
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(ProductInfo.class).add(Restrictions.in("accountID", listAccountId));
			criteria.setCacheable(true);
			@SuppressWarnings("unchecked")
			List<ProductInfo> result = criteria.list();
			// Close session
			session.close();

			if (result.size() > 0) {
				systems = result;
			}

			return systems;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void deleteProduct(ProductInfo data) {
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
		ProductInfoAccess newestProductDataAccess = new ProductInfoAccess();
		Date startDate = new Date();
		Date endDate = new Date();
		Boolean a = true;
		ProductInfo data = new ProductInfo(1,2,6,"hoa qua","15000VNĐ","Kg",25,startDate, endDate, 0, "true",50,"na_thai","", "" );
		newestProductDataAccess.insertOrUpdate(data);
		System.out.println("insert data success");
	}
	
}
