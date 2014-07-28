
package com.otv.user.dao;

public class DaoException extends Exception {
	
	private static final long serialVersionUID = -33875169931292948L;
	
	public DaoException() {
		
	}
	
	public DaoException(String message) {
		super(message);
	}
	
	public DaoException(Throwable cause) {
		super(cause);	
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);	
	}

}
