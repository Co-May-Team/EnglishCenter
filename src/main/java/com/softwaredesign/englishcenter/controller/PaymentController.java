package com.softwaredesign.englishcenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softwaredesign.englishcenter.entity.Payment;
import com.softwaredesign.englishcenter.model.PaymentModel;
import com.softwaredesign.englishcenter.service.ClassService;
import com.softwaredesign.englishcenter.service.PaymentService;
import com.softwaredesign.englishcenter.service.StudentService;

@Controller
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	ClassService classService;
	
	@Autowired
	PaymentService paymentService;
	
    @GetMapping("")
    public String findAll(Model model){
    	List<PaymentModel> payments = paymentService.findAll();
    	model.addAttribute("payments", payments);
    	return "payment.html";
    }
    
    @GetMapping("/complete")
    @ResponseBody
    public String completePayment(Model model, @RequestParam String id){
    	Payment payment = paymentService.findById(Integer.valueOf(id));
    	payment.setStatus(true);
    	paymentService.update(payment);
    	return "successfully";
    }
}
