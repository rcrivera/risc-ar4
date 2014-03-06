/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 *Class that load a given value to accumulator.
 */
public class LoadImmediate implements Instruction{
	private String immediate;
	Character negativeBit;
	/**
	 * Constructor
	 */
	public LoadImmediate(String immediate) {
		this.immediate = immediate;
		negativeBit = immediate.charAt(0);
	}
	@Override
	public void execute() {
		RegisterManager.setA(immediate);
		
		if(Integer.parseInt(immediate,2)==0){
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
