package icom4215_phase1.exceptions;

import java.io.IOException;

public class NoStopFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6373872853458114226L;
	private static String errormsg = "No Stop instruction was found.";
	
	public NoStopFoundException() {
		super(errormsg);
	}
}
