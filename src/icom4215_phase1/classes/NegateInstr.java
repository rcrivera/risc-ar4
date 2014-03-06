/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 *Class that takes the two's complements to a number.
 */
public class NegateInstr implements Instruction{
	private String accumulator;
	private String temp;
	private String firstSubstring;
	private String lastSubstring;
	
	/**
	 * Constructor
	 */
	public NegateInstr(String accumulator) {
		this.accumulator = accumulator;
		temp = "";
		firstSubstring = "";
		lastSubstring = "";
	}

	@Override
	public void execute() {
		int rightCounter = accumulator.length()-1;
		Character currentCharacter = accumulator.charAt(accumulator.length()-1);
		
		while (!currentCharacter.equals('1')){
			lastSubstring = accumulator.substring(rightCounter,accumulator.length());
			rightCounter --;
			currentCharacter = accumulator.charAt(rightCounter);
		}
		
		lastSubstring = accumulator.substring(rightCounter,accumulator.length());
		for(int leftCounter=0; leftCounter < rightCounter; leftCounter++){
			currentCharacter = accumulator.charAt(leftCounter);
			if(currentCharacter.equals('0')){
				firstSubstring = firstSubstring.concat("1");
			}
			else{
				firstSubstring = firstSubstring.concat("0");
			}
		}
		
		temp = firstSubstring + lastSubstring;
		
		RegisterManager.setA(temp);
		Character signBIT = temp.charAt(0);
		
		if(signBIT.equals('1')){
			RegisterManager.setSR("00000100");
		}
		else if(temp.equals("00000000")){
			RegisterManager.setSR("00000001");
		}
		else{
			RegisterManager.setSR("00000000");
		}
		
	}

}
