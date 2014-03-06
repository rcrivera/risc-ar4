/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 * Class that load the accumulator from a register.
 */
public class LoadRegisterInstr implements Instruction{
	private String register;
	Character negativeBit = '0';

	/**
	 * Constructor
	 */
	public LoadRegisterInstr(String register) {
		this.register = register;
		negativeBit = register.charAt(0);
	}

	@Override
	public void execute() {
		RegisterManager.setA(register);
		
		if(Integer.parseInt(register, 2)== 0){
			RegisterManager.setSR("00000001");
		}
		else if(negativeBit.equals('1')){
			RegisterManager.setSR("00000100");
		}
		else{
			RegisterManager.setSR("00000000");
		}
		
	}

}
