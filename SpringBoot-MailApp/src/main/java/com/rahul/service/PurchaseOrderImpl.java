package com.rahul.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class PurchaseOrderImpl implements IPurchaseOrder {

	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String fromEmails;

	@Override
	public String purchase(String[] items, Double[] prices, String[] toEmails) throws Exception {

		Double amt = 0.0;

		for (Double price : prices)
			amt = amt + price;

		String msg = Arrays.toString(items) + " with price :: " + Arrays.toString(prices)
				+ " are purchased with billAmount :: " + amt;

		String status = sendMail(msg, toEmails);

		return msg + " ---> " + status;

	}

	private String sendMail(String msg, String[] toEmails) throws MessagingException {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(fromEmails);
		helper.setCc(toEmails);
		helper.setSubject("Open in to know it");
		helper.setSentDate(new Date());
		helper.setText(msg);
//		helper.addAttachment("amazon", new ClassPathResource("D:\\amazon.jpg"));
		sender.send(message);

		return "mail-sent";

	}

}
