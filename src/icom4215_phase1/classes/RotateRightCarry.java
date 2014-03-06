/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 *Class that rotate right through carry.
 */
public class RotateRightCarry implements Instruction{
	private String accumulator;
	private String temp;

	/**
	 * Constructor
	 */
	public RotateRightCarry(String accumulator) {
		this.accumulator = accumulator;
		temp = "";
	}

	@Override
	public void execute() {
		int acumCounter = 0;
		temp = temp.concat(Character.toString(RegisterManager.getSR().toBinString().charAt(6)));
		
		while(acumCounter < 7){
			Character accumBit = accumulator.charAt(acumCounter);
			temp = temp.concat(Character.toString(accumBit));
			acumCounter++;	
		}
		
		Character signBIT = temp.charAt(0);
		if(signBIT.equals('1')){
			RegisterManager.setSR("000001"+Character.toString(accumulator.charAt(7))+"0");
		}
		else if(Integer.parseInt(temp, 2)== 0){
			RegisterManager.setSR("000000"+Character.toString(accumulator.charAt(7))+"1");
		}
		else{
			RegisterManager.setSR("000000"+Character.toString(accumulator.charAt(7))+"0");
		}
		
		RegisterManager.setA(temp);
		
	}

}
