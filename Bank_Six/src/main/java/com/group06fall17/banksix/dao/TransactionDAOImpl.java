package com.group06fall17.banksix.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group06fall17.banksix.interceptor.ILogs;
import com.group06fall17.banksix.model.BankAccount;
import com.group06fall17.banksix.model.Logs;
import com.group06fall17.banksix.model.Transaction;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
	private SessionFactory sessionFactory;

	@Autowired
	private LogsDAO logsDao;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	@Transactional
	public void add(Transaction transaction) {
		sessionFactory.getCurrentSession().save(transaction);
		logIt("add - ", transaction);
	}

	@Override
	@Transactional
	public void update(Transaction transaction) {
		sessionFactory.getCurrentSession().merge(transaction);
		logIt("update - ", transaction);
	}

	@Override
	@Transactional
	public void persist(Transaction transaction) {
		sessionFactory.getCurrentSession().persist(transaction);
		logIt("persist - ", transaction);
	}

	@Override
	@Transactional
	public void delete(Transaction transaction) {
		logIt("delete - ", transaction);
		Query query = sessionFactory.getCurrentSession().createQuery("delete Transaction where transid = :ID");
		query.setParameter("ID", transaction.getTransid());
		query.executeUpdate();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Transaction> findTransactionsOfAccount(String accountnumber) {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Transaction where fromacc = :accno1 or toacc = :accno2");
		query.setString("accno1", accountnumber);
		query.setString("accno2", accountnumber);
		@SuppressWarnings("unchecked")
		List<Transaction> list = query.list();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Transaction findTransactionById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction transaction = (Transaction) session.createQuery("from Transaction where transid = :id")
				.setInteger("id", id).uniqueResult();
		return transaction;
	}

	// Added by Saurabh, required by UserOperationsController
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Transaction> findTransactionsOfAccount(BankAccount bankaccount) {
		List<Transaction> transactionList = sessionFactory.getCurrentSession()
				.createQuery("from Transaction where fromacc = :fromBankaccount or toacc = :toBankaccount")
				.setParameter("fromBankaccount", bankaccount).setParameter("toBankaccount", bankaccount).list();

		return transactionList;
	}

	public void logIt(String action, ILogs ilogs) {
		Logs logs = new Logs();
		Date dateobj = new Date();
		logs.setLogentrydate(dateobj);
		logs.setLoginfo(action + ilogs.getLogDetail());

		logsDao.add(logs);
	}

}
