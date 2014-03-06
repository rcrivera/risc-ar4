package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;


public class LogicalANDInstr implements Instruction{
	
	private String accumulator;
	private String register;
	private String temp;
	
	public LogicalANDInstr(String acumulator, String register){
		this.accumulator = acumulator;
		this.register = register;
		temp = "";
	}

	@Override
	public void execute() {
		
		int acumCounter = 0;
		int registerCounter = 0;
		Character negativeBit = '0';
		
		while(acumCounter < 8 && registerCounter < 8){
			Character acumBit = accumulator.charAt(acumCounter);
			Character registerBit = register.charAt(registerCounter);
			
			if(acumBit.equals('0') || registerBit.equals('0')){
				temp = temp.concat("0");
			}
			else{
				temp = temp.concat("1");
			}
			
			acumCounter++;
			registerCounter++;
			
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
