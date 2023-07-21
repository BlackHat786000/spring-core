package com.learn.springcore;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class Doctor implements Staff, BeanNameAware {
	
	private String qualification;
//	
//	public Doctor(String qualification) {
//		this.qualification = qualification;
//	}
//
	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	@Override
	public String toString() {
		return "Doctor [qualification=" + qualification + "]";
	}

	public void assist() {
		System.out.println("doctor is assisting");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("set bean name method is called");
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("post construct method is called");
	}

}
