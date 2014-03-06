/**
 * 
 */
package icom4215_phase1.classes;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 * Class that push a new value for vector stack.
 */
public class PushVector implements Instruction{
	private String vectorValue1;
	private String vectorValue2;
	/**
	 * Constructor
	 */
	public PushVector(String vectorValue1,String vectorValue2) {
		this.vectorValue1 = vectorValue1;
		this.vectorValue2 = vectorValue2;
	}

	@Override
	public void execute() {
		String temp1 = RegisterManager.getVTOP1().toBinString();
		String temp2 = RegisterManager.getVTOP2().toBinString();
		
		RegisterManager.setVTOP1(vectorValue1);
		RegisterManager.setVTOP2(vectorValue2);
		RegisterManager.setVSEC1(temp1);
		RegisterManager.setVSEC2(temp2);
		
		String accumulator = RegisterManager.getA().toBinString();
		if(((Character)accumulator.charAt(0)).equals('1')){
			RegisterManager.setSR("00000100");
		}
		if(accumulator.equals("00000000")){
			RegisterManager.setSR("00000001");
		}
	}

}
