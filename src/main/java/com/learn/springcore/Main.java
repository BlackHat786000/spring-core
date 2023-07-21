package com.learn.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		
//		Doctor doctor = context.getBean(Doctor.class);
//		Doctor doctor = (Doctor) context.getBean("doctor");
//		Nurse nurse = (Nurse) context.getBean("nurse");
		
		// Singleton / Prototype
//		Doctor doctor = context.getBean(Doctor.class);
//		doctor.setQualification("MBBS");
//		System.out.println(doctor);
//		Doctor doctor1 = context.getBean(Doctor.class);
//		doctor1.setQualification("MD");
//		System.out.println(doctor1);
		
		Staff staff = context.getBean(Doctor.class);
		staff.assist();

	}

}
