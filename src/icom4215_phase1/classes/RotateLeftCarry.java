/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 *
 */
public class RotateLeftCarry implements Instruction{
	private String accumulator;
	private String temp;
	/**
	 * Constructor
	 */
	public RotateLeftCarry(String accumulator) {
		this.accumulator = accumulator;
		temp = "";
	}
	@Override
	public void execute() {
		int acumCounter = 1;
		
		while(acumCounter < 8){
			Character accumBit = accumulator.charAt(acumCounter);
			temp = temp.concat(Character.toString(accumBit));
			acumCounter++;	
		}
		
		temp = temp.concat(Character.toString(RegisterManager.getSR().toBinString().charAt(6)));
		
		Character signBIT = temp.charAt(0);
		if(signBIT.equals('1')){
			RegisterManager.setSR("000001"+Character.toString(accumulator.charAt(0))+"0");
		}
		else if(Integer.parseInt(temp, 2)== 0){
			RegisterManager.setSR("000000"+Character.toString(accumulator.charAt(0))+"1");
		}
		else{
			RegisterManager.setSR("000000"+Character.toString(accumulator.charAt(0))+"0");
		}
		
		RegisterManager.setA(temp);
		
		
	}

}
