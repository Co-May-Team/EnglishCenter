package com.softwaredesign.englishcenter.dao;

import java.util.List;

import com.softwaredesign.englishcenter.entity.Payment;
import com.softwaredesign.englishcenter.model.PaymentModel;

public interface IPaymentRepository {
	int save(Payment payment);
	int update(Payment payment);
	Payment findById(int id);
	List<PaymentModel> findAll();
}
