package icom4215_phase1.classes;



/**
 * @author Yoaquim Cintron, Roberto Rivera, Eddrick Berrios
 * 
 * Class that represents an instruction. An instruction object
 * has an instance field for the instruction representation, for 
 * the opcode and for the operands. 
 *
 */
public class InstructionExecuter {
	
	private String instruction;
	private Integer opcode;
	private String operands;
	private String accumulator;
	private String register;
	private String register2;
	private String address;
	private String immediate;
	
	
	/**
	 * Constructor
	 * @param instruction: a string representation of an instruction
	 */
	public InstructionExecuter(String instruction){
		this.instruction = instruction;
		opcode = Integer.parseInt(instruction.substring(0, 5), 2);
		operands = instruction.substring(5, 16);
		accumulator = RegisterManager.getA().toBinString();
		register = RegisterManager.fetchReg(Integer.parseInt(instruction.substring(5, 8),2)).toBinString();
		register2 = RegisterManager.fetchReg(Integer.parseInt(instruction.substring(8, 11),2)).toBinString();
		address = instruction.substring(8, 16);
		immediate = instruction.substring(8, 16);
	}
	
	public void execute(){
		switch(opcode){
		case 0:			//Logical AND instruction
			
			LogicalANDInstr logicalAND = new LogicalANDInstr(accumulator, register);
			logicalAND.execute();
			
			break;
		case 1:			//Logical OR instruction
			
			LogicalORInstr logicalOR = new LogicalORInstr(accumulator, register);
			logicalOR.execute();
			
			break;
		case 2:			//Logical XOR instruction
	
			LogicalXORInstr logicalXOR = new LogicalXORInstr(accumulator, register);
			logicalXOR.execute();
			break;
		case 3:			//Add with carry (ADDC rf)
			AddWithCarry addc = new AddWithCarry(accumulator,register);
			addc.execute();
			break;
		case 4:			//Subtraction of Accumulator minus a register. (SUB rf)
			Subtract sub = new Subtract(accumulator,register);
			sub.execute();
			break;
		case 5:			//ADD Vector instruction.
			VectorADD vAdd = new VectorADD();
			vAdd.execute();
			break;
		case 6:			//Two's complement (NEG)
			NegateInstr neg = new NegateInstr(accumulator);
			neg.execute();
			break;
		case 7:			//Logical NOT instruction  (NOT)
			LogicalNOTInstr logicalNOT = new LogicalNOTInstr(accumulator);
			logicalNOT.execute();
			break;
		case 8:			//Rotate left through carry (RLC)
			RotateLeftCarry rotateLC = new RotateLeftCarry(accumulator);
			rotateLC.execute();
			break;
		case 9:			//Rotate right through carry (RRC)
			RotateRightCarry rotateRC = new RotateRightCarry(accumulator);
			rotateRC.execute();
			break;
		case 10:		//Load Accumulator from register instruction (LDA rf)
			LoadRegisterInstr loadRegister = new LoadRegisterInstr(register);
			loadRegister.execute();
			break;
		case 11:		//Store accumulator to register (STA rf)
			StoreToRegister storeRegister = new StoreToRegister(Integer.parseInt(instruction.substring(5, 8),2));
			storeRegister.execute();
			break;
		case 12:		//Load Accumulator from memory location address (LDA addr)
			LoadAddressInstr loadAddress = new LoadAddressInstr(address);
			loadAddress.execute();
			break;
		case 13:		//Store accumulator to memory location address (STA addr)
			StoreAddressInstr storeAddress = new StoreAddressInstr(address);
			storeAddress.execute();
			break;
		case 14:		//Load accumulator with immediate (LDI immediate)
			LoadImmediate loadImmediate = new LoadImmediate(immediate);
			loadImmediate.execute();
			break;
		case 15:		//Push value for vector1 and vector2
			PushVector pushVector = new PushVector(register,register2);
			pushVector.execute();
			break;
		case 16:		//Branch if Zero (BRZ)
			BranchZero branchZ = new BranchZero();
			branchZ.execute();
			break;
		case 17:		//Branch if Carry (BRC)
			BranchCarry branchC = new BranchCarry();
			branchC.execute();
			break;
		case 18:		//Branch if Negative (BRN)
			BranchNegative branchN = new BranchNegative();
			branchN.execute();
			break;
		case 19:
			BranchOverflow branchO = new BranchOverflow();
			branchO.execute();
			break;
		case 20:		//STOP instruction is done on MAIN
			break;
		default:		//NOP instruction
			break;
		}
		
	}

	/**
	 * Getter for complete instruction
	 * @return the string representation for the instruction
	 */
	public String getInstruction() {
		return instruction;
	}

	/**
	 * Getter for the opcode.
	 * @return an integer representation for the
	 * instruction 
	 */
	public int getOpcode() {
		return opcode;
	}

	/**
	 * Getter for the instruction operands
	 * @return a string representing the instruction's operands 
	 */
	public String getOperands() {
		return operands;
	}

}
