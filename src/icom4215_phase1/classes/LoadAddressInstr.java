/**
 * 
 */
package icom4215_phase1.classes;

import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import icom4215_phase1.classes.UserInterface;
import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 *Class that define the Load Accumulator From Memory Location instruction
 */
public class LoadAddressInstr implements Instruction{
	private String address;
	/**
	 * Constructor
	 */
	public LoadAddressInstr(String address) {
		this.address = address;
	}

	@Override
	public void execute() {
		Integer decaddress = Integer.parseInt(address,2);
		String tomem = null;
		try{
			if(address.equals("11111010")||address.equals("11111011")){
				String input = JOptionPane.showInputDialog ("Type one Character"); 
				while(input.length()==0 || input.length()>=2){
					input = JOptionPane.showInputDialog ("Type one Character"); 
				}
				
				UserInterface.Keybd.setText(input);
				tomem = Integer.toHexString(input.charAt(0));
				RegisterManager.setA(tomem);
			}
			else{
				tomem = MemoryManager.getMemManager().readFrom(decaddress).getDatum();
				Character negativeBit = tomem.charAt(0);
				RegisterManager.setA(tomem);
				if(decaddress == 0){
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
		catch(NullPointerException e){
			RegisterManager.setA("00");
		}

	}

}
