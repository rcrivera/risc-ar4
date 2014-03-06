/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 * Class that implements the logical NOT
 *
 */
public class LogicalNOTInstr implements Instruction{
	private String accumulator;
	private String temp;

	/**
	 * Constructor
	 */
	public LogicalNOTInstr(String acumulator) {
		this.accumulator = acumulator; 
		this.temp = "";
	}

	@Override
	public void execute() {
		int acumCounter = 0;
		Character negativeBit = '0';
		
		while(acumCounter < 8){
			Character acumBit = accumulator.charAt(acumCounter);
			
			if(acumBit.equals('0')){
				temp = temp.concat("1");
			}
			else{
				temp = temp.concat("0");
			}
			
			acumCounter++;
			
		}
		RegisterManager.setA(temp);
		negativeBit = temp.charAt(0);
		
		if(negativeBit.equals('1')){
			RegisterManager.setSR("00000100");
		}
		else if(Integer.parseInt(temp,2) == 0){
			RegisterManager.setSR("00000001");
		}
		else{
			RegisterManager.setSR("00000000");
		}
		
	}

}
