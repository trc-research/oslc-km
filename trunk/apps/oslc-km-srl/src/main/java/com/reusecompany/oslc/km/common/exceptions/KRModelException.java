package com.reusecompany.oslc.km.common.exceptions;

import java.io.FileNotFoundException;

/**
 *
 * This class models a generic runtime exception.
 */
public class KRModelException extends RuntimeException{
	  
	public KRModelException(Exception e) {
	        super(e);
	    }

	public KRModelException(String string) {
	        super(string);
	    }
	  
    public KRModelException(Exception e, String string) {
        super(string,e);
    }

    public KRModelException(String string, Exception e) {
        super(string,e);
    }
    
    public KRModelException(FileNotFoundException e, String string) {
        super(string, e);
    }

}
