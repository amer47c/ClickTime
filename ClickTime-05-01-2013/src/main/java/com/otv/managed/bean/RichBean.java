package com.otv.managed.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class RichBean implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	private String text; // getter and setter
	private Integer count;  // getter and setter
	 
	   
		public RichBean(){
			text = "Manjeet";
			
	}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Integer getCount() {	
			countAction();
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}
		
		private void countAction() {
			count = text.length();
		   }
		
	
}