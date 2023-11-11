package com.rahul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.rahul.service.IPurchaseOrder;

@SpringBootApplication
public class SpringBootMailAppApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringBootMailAppApplication.class, args);

		IPurchaseOrder order = context.getBean(IPurchaseOrder.class);

		try {

			order.purchase(new String[] { "Fossil-Chronogramphy", "USPOLO-Tshirt", "LouisPhillipse-Shoes" },
					new Double[] { 12000.0, 34987.0, 84233.0 },
					new String[] { "rahulmore1113@gmail.com", "morerahul1113@outlook.com", "rsmore@flamencotech.com" });

			System.out.println(order);

		} catch (Exception e) {

			e.printStackTrace();

		}

		((ConfigurableApplicationContext) context).close();

	}

}
