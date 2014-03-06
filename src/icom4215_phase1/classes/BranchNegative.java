/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 *
 */
public class BranchNegative implements Instruction{

	/**
	 * Constructor
	 */
	public BranchNegative() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		String statusRegister = RegisterManager.getSR().toBinString();
		if(((Character)statusRegister.charAt(5)).equals('1')){
			int PCValue = Integer.parseInt(RegisterManager.getR7().getDatum(),16)-2;
			UserInterface.setPC(PCValue);
		}
		
	}

}
