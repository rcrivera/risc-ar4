/**
 * 
 */
package icom4215_phase1.classes;

import java.awt.List;

import icom4215_phase1.interfaces.Instruction;

/**
 * @author Eddrick
 * Class that executes Store Accumulator to Memory Location Address instruction.
 *
 */
public class StoreAddressInstr implements Instruction{
	List MemList = UserInterface.getMemoryList();
	private String address;

	/**
	 * Constructor
	 */
	public StoreAddressInstr(String address) {
		this.address = address;
	}

	@Override
	public void execute() {
		Integer decaddress = Integer.parseInt(address,2);
		String hexaddress = Integer.toHexString(decaddress);
		Memory memo = new Memory(RegisterManager.getA().toBinString(),0);
		MemoryManager.getMemManager().writeTo(decaddress, memo);
		String toprint = MemoryManager.getMemManager().readFrom(decaddress).getDatum();
		while(toprint.length()<4){
			toprint="0"+toprint;
		}
		MemList.replaceItem(hexaddress+":"+toprint, (decaddress)/2);
	}

}
