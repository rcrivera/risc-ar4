package icom4215_phase1.classes;

import icom4215_phase1.exceptions.InstructionsOutOfBoundsException;

/**
 * @author Yoaquim Cintr—n, Roberto Rivera, Eddrick Berrios
 * 
 *Emulates memory within a Microprocessor.
 *Gives access to internal data contained within
 *memory and allows user to read and write from 
 *said memory space.
 */

public class MemoryManager {

	private static final int INSTLIM = 127;
	private static final int MEMSTART = 128;
	private static final int MEMEND = 249;
	private static final int SPECSTART = 250;
	private static final int SPECEND = 255;
	private static final InstructionsOutOfBoundsException IndexOutOfBounds = new InstructionsOutOfBoundsException();
	private static Memory[] MEMORY = new Memory[256] ;
	private static MemoryManager MM;
	
	
	/**Static method used to access memory.
	 * Singleton based; only one instance of a manager
	 * will ever be active. Memory accessed exclusively
	 * through this manager.
	 * @return A new Memory Manager if
	 * 	MM is null, i.e., if there was none prior.
	 * Otherwise, returns the already existing 
	 * Memory Manager, MM.
	 */
	public static MemoryManager getMemManager(){
		if (MM==null)
			return MM = new MemoryManager();
		else
			return MM;
	}
	
	/** Allows you to read data from memory space indicated.
	 * @param memSpace Address of memory you want to read from.
	 * @return Data contained within address 'memspace'
	 */
	public Memory readFrom(int memSpace){
		return MEMORY[memSpace];
	}
	
	
	
	/**Allows you to write data to memory space indicated.
	 * @param memSpace Address of memory you want to write to.
	 * @param data	Data you want to write to memory.
	 */
	public void writeTo(int memSpace, Memory data){
		MEMORY[memSpace] = data;
	}
	
	/** Allows you to write instructions to memory space indicated.
	 * Can not exceed address #127.
	 * @param memSpace Address of memory you want to write instruction to.
	 * @param data Instruction you want to write to memory.
	 * @throws Exception 
	 */
	public void instructionWrite(int memSpace, Memory data) throws Exception{
		if(memSpace>INSTLIM)
			throw IndexOutOfBounds;
		else{
			writeTo(memSpace, data);
		}
	}
}
