package com.softwaredesign.englishcenter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softwaredesign.englishcenter.dao.IPaymentRepository;
import com.softwaredesign.englishcenter.entity.Payment;
import com.softwaredesign.englishcenter.model.ClassModel;
import com.softwaredesign.englishcenter.model.PaymentModel;
import com.softwaredesign.englishcenter.model.StudentModel;

import jakarta.persistence.TypedQuery;

@Repository
@Transactional
public class PaymentRepositoryImpl implements IPaymentRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRepositoryImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int save(Payment payment) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.persist(payment);
			return 1;
		} catch (Exception e) {
			LOGGER.error("Error has occured at addPayment() ", e);
		}
		return -1;
	}

	@Override
	public List<PaymentModel> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Payment> payments = null;
		List<PaymentModel> paymentModels = null;
		try {
			StringBuilder hql = new StringBuilder("FROM payment");
	        TypedQuery<Payment> query = session.createQuery(hql.toString(), Payment.class);
	        payments = query.getResultList();
	        paymentModels = new ArrayList<>();
	        for(Payment payment : payments) {
	        	PaymentModel paymentModel = new PaymentModel();
	        	paymentModel.setPaymentId(payment.getPaymentId());
	        	paymentModel.setAmount(payment.getAmount());
	        	paymentModel.setPaymentDate(payment.getPaymentDate());
	        	
	        	StudentModel studentModel = new StudentModel();
	        	StringBuilder fullname = new StringBuilder(payment.getStudent().getLastName() + " ");
	        	fullname.append(payment.getStudent().getMidleName() + " ");
	        	fullname.append(payment.getStudent().getFirstName());
	        	studentModel.setFullname(fullname.toString());
	        	studentModel.setId(payment.getStudent().getStudentId());
	        	studentModel.setCitizenId(payment.getStudent().getCitizenId());
	        	studentModel.setAddress(payment.getStudent().getAddress());
	        	studentModel.setPhoneNumber(payment.getStudent().getPhoneNumber());
	        	paymentModel.setStudentModel(studentModel);
	        	
	        	ClassModel classModel = new ClassModel();
	        	classModel.setClassId(payment.getClassObject().getClassId());
	        	classModel.setName(payment.getClassObject().getName());
	        	classModel.setTuitionFee(payment.getClassObject().getTuitionFee().doubleValue());
	        	paymentModel.setClassModel(classModel);
	        	
	        	if(!payment.isStatus()) {
	        		paymentModel.setStatus("Chưa thanh toán");
	        	} else {
	        		paymentModel.setStatus("Đã thanh toán");
	        	}
	        	
	        	paymentModels.add(paymentModel);
	        	
	        }
		} catch (Exception e) {
			LOGGER.error("Error has occured at findAllPayment() ", e);
		}
		return paymentModels;
	}

	@Override
	public int update(Payment payment) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.merge(payment);
			return 1;
		} catch (Exception e) {
			LOGGER.error("Error has occured at addPayment() ", e);
		}
		return -1;
	}

	@Override
	public Payment findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Payment payment = null;
		try {
			StringBuilder hql = new StringBuilder("FROM payment WHERE paymentId =" + id);
	        TypedQuery<Payment> query = session.createQuery(hql.toString(), Payment.class);
	        payment = query.getSingleResult();

		} catch (Exception e) {
			LOGGER.error("Error has occured at findAllPayment() ", e);
		}
		return payment;
	}

}
