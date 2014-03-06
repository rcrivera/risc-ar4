package icom4215_phase1.classes;

/**
 * @author Yoaquim Cintr—n, Roberto Rivera, Eddrick Berrios
 *
 *	Contains the instance fields, methods, properties and
 *characteristics that form a memory object.
 */

public class Memory {

	private String datum = null;
	private static Memory block1 = null;
	private static Memory block2 = null;
	private Integer pair = null;

	/**Constructor. Defines a memory object that contains the data
	 * and indicates if its the lower or upper end of a word (16 bits).
	 * @param datum The Data you want to store in memory; instruction.
	 * @param pair	Either 0 or 1: determines if its the lower block or upper block,
	 * 	respectively
	 */
	public Memory(String datum, Integer pair) {
		super();
		setDatum(datum);
		this.pair = pair;
	}

	public Memory(String datum){
		super();
		this.datum = datum;
	}
	/**Divides the hexadecimal string it receives into two memory blocks.
	 * The first block contains the highest ordered bits: 15-8; the 
	 * second block corresponds to the last 8 bits: 7-0.
	 * @param memory Hexadecimal string that represents the instruction
	 * @return 2 blocks of memory corresponding to most and least significant bits
	 */
	public static Memory divideMem(String memory){
		if(block1==null){
			String bin = Integer.toBinaryString(Integer.parseInt(memory, 16));
			while(bin.length()<16){
				bin = "0"+bin;
			}
			String ms = bin.substring(0,8);
			String ls = bin.substring(8);
			ms = Integer.toHexString(Integer.parseInt(ms, 2));
			ls = Integer.toHexString(Integer.parseInt(ls, 2));
			if(ms.length()==1)
				ms="0"+ms;
			if(ls.length()==1)
				ls="0"+ls;
			block1 = new Memory (ms);
			block2 = new Memory (ls);
			return block1;
		}
		else{
			block1 = null;
			Memory temp = block2;
			block2 = null;
			return temp;
		}
	}

	

	/**Returns the data contained within the memory space
	 * @return	Data within the memory space
	 */
	public String getDatum() {
		return datum;
	}

	/**Sets the value of the memory space to the 'datum' parameter received
	 * Accepts any string, be it hexadecimal or binary, but saves it as a
	 * hexadecimal string within memory.
	 * @param datum Value to save to memory space; can be hex or bin
	 */
	public void setDatum(String datum) {
		boolean bincheck = true;
		String[] st = datum.split("");
		for(int i=1; i<st.length;i++){
			if(!(st[i].equals("1"))&&!(st[i].equals("0")))
				bincheck = false;
		}
		if(bincheck){
			datum = Integer.toHexString(Integer.parseInt(datum, 2));
		}
		this.datum = datum;
	}

	public Integer getPair() {
		return pair;
	}

	public void setPair(Integer pair) {
		this.pair = pair;
	}


	/**Converts the data within the register into a binary string
	 * @return	Binary string that defines the data in memory
	 */
	public String toBinString(){
		String temp = Integer.toBinaryString(Integer.parseInt(this.datum, 16));
		while(temp.length()<8){
			temp = "0"+temp;
		}
		return temp;
	}

}
