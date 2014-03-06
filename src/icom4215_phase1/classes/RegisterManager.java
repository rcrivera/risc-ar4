package icom4215_phase1.classes;

/**
 * @author Yoaquim Cintr—n, Roberto Rivera, Eddrick Berrios
 *
 *	Class that emulates a Register Manager.
 *	Contains Register R0-R7, Accumulator,
 *	Program Counter, Status Register, 
 *	Instruction Register.
 */

public class RegisterManager {
	
	private static  Memory R0 = new Memory("00", null);
	private static  Memory R1 = new Memory("00", null);
	private static  Memory R2 = new Memory("00", null);
	private static  Memory R3 = new Memory("00", null);
	private static  Memory R4 = new Memory("00", null);
	private static  Memory R5 = new Memory("00", null);
	private static  Memory R6 = new Memory("00", null);
	private static  Memory R7 = new Memory("00", null);
	private static  Memory A = new Memory("00", null);
	private static  Memory SR = new Memory("00", null);
	private static 	Memory VBUFF = new Memory("00", null);
	private static  Memory IRMS = new Memory("00", 0); 
	private static 	Memory IRLS = new Memory("00",1);
	private static  Memory VTOP1 = new Memory("00", 0); 
	private static  Memory VTOP2 = new Memory("00", 1); 
	private static  Memory VSEC1 = new Memory("00", 0); 
	private static  Memory VSEC2 = new Memory("00", 1); 
	private static	RegisterManager RM;
	
	
	/** Static method used to access registers.
	 * Singleton based; only one instance of a manager
	 * will ever be active. Registers accessed exclusively
	 * through this manager.
	 * @return A new Register Manager if
	 * 	RM is null, i.e., if there was none prior.
	 * Otherwise, returns the already existing 
	 * Register Manager, RM.
	 */
	public static RegisterManager getRegManager(){
		if(RM==null)
			return RM = new RegisterManager();
		else
			return RM;
	}
		
	public static Memory getR0() {
		return R0;
	}

	public static void setR0(String data) {
		R0.setDatum(data);
		R0.setPair(null);
	}

	public static Memory getR1() {
		return R1;
	}

	public static void setR1(String data) {
		R1.setDatum(data);
		R1.setPair(null);
	}

	public static Memory getR2() {
		return R2;
	}

	public static void setR2(String data) {
		R2.setDatum(data);
		R2.setPair(null);
	}

	public static Memory getR3() {
		return R3;
	}

	public static void setR3(String data) {
		R3.setDatum(data);
		R3.setPair(null);
	}

	public static Memory getR4() {
		return R4;
	}

	public static void setR4(String data) {
		R4.setDatum(data);
		R4.setPair(null);
	}

	public static Memory getR5() {
		return R5;
	}

	public static void setR5(String data) {
		R5.setDatum(data);
		R5.setPair(null);
	}

	public static Memory getR6() {
		return R6;
	}

	public static void setR6(String data) {
		R6.setDatum(data);
		R6.setPair(null);
	}

	public static Memory getR7() {
		return R7;
	}

	public static void setR7(String data) {
		R7.setDatum(data);
		R7.setPair(null);
	}

	public static Memory getA() {
		return A;
	}

	public static void setA(String a) {
		A.setDatum(a);
		A.setPair(null);
	}

	public static Memory getSR() {
		return SR;
	}

	public static void setSR(String sR) {
		SR.setDatum(sR);
		SR.setPair(null);
		
	}
	
	public static Memory getVBUFF() {
		return VBUFF;
	}

	public static void setVBUFF(String vBUFF) {
		VBUFF.setDatum(vBUFF);
	}

	public static Memory getIRMS() {
		return IRMS;
	}

	public static void setIRMS(String iR) {
		IRMS.setDatum(iR);
	}
	
	public static Memory getIRLS() {
		return IRLS;
	}

	public static void setIRLS(String iR) {
		IRLS.setDatum(iR);
	}
	
	public static Memory getVTOP1() {
		return VTOP1;
	}

	public static void setVTOP1(String vTOP1) {
		VTOP1.setDatum(vTOP1);
	}

	public static Memory getVTOP2() {
		return VTOP2;
	}

	public static void setVTOP2(String vTOP2) {
		VTOP2.setDatum(vTOP2);
	}

	public static Memory getVSEC1() {
		return VSEC1;
	}

	public static void setVSEC1(String vSEC1) {
		VSEC1.setDatum(vSEC1);
	}

	public static Memory getVSEC2() {
		return VSEC2;
	}

	public static void setVSEC2(String vSEC2) {
		VSEC2.setDatum(vSEC2);
	}
	
	/**Receives a number, 'regno', and fetches the corresponding
	 * Register that matches that number.
	 * @param regno Number of Register to fetch.
	 * @return Register matching 'regno'
	 */
	public static Memory fetchReg(Integer regno){
		switch(regno){
		case(0): return getR0(); 
		case(1): return getR1(); 
		case(2): return getR2(); 
		case(3): return getR3(); 
		case(4): return getR4(); 
		case(5): return getR5(); 
		case(6): return getR6();
		case(7): return getR7();
		default: break;
		}
		return null;
	}
	
	/**Takes the string parameter 'data' and writes
	 * it to the Register corresponding to 'regno'
	 * @param data	Data to write to Register.
	 * @param regno	Number of Register to write to.
	 */
	public static void scribeReg(Integer regno, String data){
		switch(regno){
		case(0): setR0(data); break;
		case(1): setR1(data); break;
		case(2): setR2(data); break;
		case(3): setR3(data); break;
		case(4): setR4(data); break;
		case(5): setR5(data); break;
		case(6): setR6(data); break;
		case(7): setR7(data); break;
		default: break;
		}
	}
}
