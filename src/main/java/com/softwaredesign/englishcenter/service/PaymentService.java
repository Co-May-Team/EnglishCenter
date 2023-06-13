package com.softwaredesign.englishcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwaredesign.englishcenter.dao.IPaymentRepository;
import com.softwaredesign.englishcenter.entity.Payment;
import com.softwaredesign.englishcenter.model.PaymentModel;

@Service
public class PaymentService {
	
	@Autowired
	IPaymentRepository paymentRepository;
	
	public List<PaymentModel> findAll(){
		return paymentRepository.findAll();
	}
	
	public boolean save(Payment payment) {
		if(paymentRepository.save(payment) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean update(Payment payment) {
		if(paymentRepository.update(payment) == 1) {
			return true;
		} else {
			return false;
		}
	}
	public Payment findById(int id) {
		return paymentRepository.findById(id);
	}
}
