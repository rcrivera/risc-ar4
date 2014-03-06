package icom4215_phase1.exceptions;

public class InstructionsOutOfBoundsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -456836714433480295L;
	private static String errormsg = "Error: File must not surpass 128B. A file can not exceed 128 lines of instructions.";
	
	public InstructionsOutOfBoundsException() {
		super(errormsg);
	}
}
