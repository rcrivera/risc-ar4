/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 *Class that store the accumulator contents to a register.
 */
public class StoreToRegister implements Instruction{
	private int register;
	/**
	 * Constructor
	 */
	public StoreToRegister(int register) {
		this.register = register; 
	}
	@Override
	public void execute() {
		RegisterManager.fetchReg(register).setDatum(RegisterManager.getA().getDatum());
		
	}

}
