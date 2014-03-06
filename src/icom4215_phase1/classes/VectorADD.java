/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 * Class that add tow vectors. TOP of stack of vector 0 is added with TOP of stack
 * of vector 1 and store the result in accumulator. Second of stack
 * of vector 0 is added with second of stack of vector 1 and store on
 * vector buffer. 
 */
public class VectorADD implements Instruction{
	private String accumTemp;
	private String bufferTemp;
	private String top1;
	private String top2;
	private String second1;
	private String second2;
	private String[] topCarryFlags = {"0","0","0","0","0","0","0","0","0"};
	private String[] secondCarryFlags = {"0","0","0","0","0","0","0","0","0"};
	
	/**
	 * Constructor
	 */
	public VectorADD() {
		top1 = RegisterManager.getVTOP1().toBinString();
		top2 = RegisterManager.getVTOP2().toBinString();
		second1 = RegisterManager.getVSEC1().toBinString();
		second2 = RegisterManager.getVSEC2().toBinString();
		accumTemp = "";
		bufferTemp = "";
	}

	@Override
	public void execute() {
		String overflowFlag = "0";
		String negativeFlag = "0";
		String zeroFlag = "0";
		
		
		//Sum top of stack
		for(int sumTopCounter = 7;sumTopCounter>=0;sumTopCounter--){
			String bit1 = ((Character)top1.charAt(sumTopCounter)).toString();
			String bit2 = ((Character)top2.charAt(sumTopCounter)).toString();
				
			accumTemp = sumTopBit(sumTopBit(bit1,topCarryFlags[sumTopCounter+1],sumTopCounter+1),bit2,sumTopCounter+1)+accumTemp;
			
		}
		
		if(Integer.parseInt(accumTemp,2)==0){
			zeroFlag = "1";
		}
		if(accumTemp.substring(0, 1).equals("1")){
			negativeFlag = "1";
		}
		if(top1.substring(0, 1).equals("0")&&top2.substring(0,1).equals("0")&&accumTemp.substring(0,1).equals("1")){
			overflowFlag = "1";
		}
		if(top1.substring(0, 1).equals("1")&&top2.substring(0,1).equals("1")&&accumTemp.substring(0,1).equals("0")){
			overflowFlag = "1";
		}
		
		RegisterManager.setSR("0000"+overflowFlag+negativeFlag+topCarryFlags[0]+zeroFlag);
		RegisterManager.setA(accumTemp);
		
		
		//Sum second of stack
		for(int sumSecondCounter = 7;sumSecondCounter>=0;sumSecondCounter--){
			String bit1 = ((Character)second1.charAt(sumSecondCounter)).toString();
			String bit2 = ((Character)second2.charAt(sumSecondCounter)).toString();
				
			bufferTemp = sumSecondBit(sumSecondBit(bit1,secondCarryFlags[sumSecondCounter+1],sumSecondCounter+1),bit2,sumSecondCounter+1)+bufferTemp;
			
		}
		
		RegisterManager.setVBUFF(bufferTemp);	
		
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
	private String sumTopBit(String string1, String string2, int radix){
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
			topCarryFlags[radix-1]="1";
			return "0";
		}
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
	private String sumSecondBit(String string1, String string2, int radix){
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
			secondCarryFlags[radix-1]="1";
			return "0";
		}
	}


}
