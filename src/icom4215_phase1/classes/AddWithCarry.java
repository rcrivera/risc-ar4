/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 *Class that add the accumulator content with a register content and the carry bit.
 */
public class AddWithCarry implements Instruction{
	private String accumulator;
	private String register;
	private String temp;
	private String[] carryFlags = {"0","0","0","0","0","0","0","0","0"};

	/**
	 * Constructor
	 */
	public AddWithCarry(String accumulator, String register) {
		this.accumulator = accumulator;
		this.register = register;
		temp = "";
		carryFlags[8]=((Character)RegisterManager.getSR().toBinString().charAt(6)).toString();
	}

	@Override
	public void execute() {
		
		String overflowFlag = "0";
		String negativeFlag = "0";
		String zeroFlag = "0";
		
		
		for(int sumCounter = 7;sumCounter>=0;sumCounter--){
			String bit1 = ((Character)accumulator.charAt(sumCounter)).toString();
			String bit2 = ((Character)register.charAt(sumCounter)).toString();
				
			temp = sumBit(sumBit(bit1,carryFlags[sumCounter+1],sumCounter+1),bit2,sumCounter+1)+temp;
			
		}
		
		if(Integer.parseInt(temp,2)==0){
			zeroFlag = "1";
		}
		if(temp.substring(0, 1).equals("1")){
			negativeFlag = "1";
		}
		if(accumulator.substring(0, 1).equals("0")&&register.substring(0,1).equals("0")&&temp.substring(0,1).equals("1")){
			overflowFlag = "1";
		}
		if(accumulator.substring(0, 1).equals("1")&&register.substring(0,1).equals("1")&&temp.substring(0,1).equals("0")){
			overflowFlag = "1";
		}
		
		RegisterManager.setSR("0000"+overflowFlag+negativeFlag+carryFlags[0]+zeroFlag);
		RegisterManager.setA(temp);
		
	}
	
	/**
	 * Private method that helps to perform a bit sum. Sum one bit another bit.
	 * if 0 + 0 then 0
	 * if 0 + 1 then 1
	 * if 1 + 0 then 1
	 * if 1 + 1 then 10 
	 * @param string1 bit 1
	 * @param string2 bit 2
	 * @return sum result
	 */
	private String sumBit(String string1, String string2, int radix){
		if(string1.equals("0")&&string2.equals("0")){
			return "0";
		}
		else if(string1.equals("0")&&string2.equals("1")){
			return "1";
		}
		else if(string1.equals("1")&&string2.equals("0")){
			return "1";
		}
		else{
			carryFlags[radix-1]="1";
			return "0";
		}
	}

}
