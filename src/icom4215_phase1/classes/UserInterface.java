package icom4215_phase1.classes;

import icom4215_phase1.exceptions.InstructionsOutOfBoundsException;
import icom4215_phase1.exceptions.NoStopFoundException;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Label;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.JLabel;

public class UserInterface {

	public static int ASCII_MemoryIndex = 252;
	public static JFrame frame;
	public static JTextField Keybd;
	private JLabel Display;
	private Label R7;
	private Label R6;
	private Label R5;
	private Label R4;
	private Label R3;
	private Label R2;
	private Label R1;
	private Label R0;
	private Label VBuff;
	private Label Acc;
	private Label PCdisp;
	private Label IR;
	private Label SR;
	private static String path;
	private static NoStopFoundException NoStopFoundException = new NoStopFoundException();
	private static boolean STEPMODE = false;
	private static List MemoryList;
	private static int StopIndex = 0;
	public static boolean KeyboardEnabled = false;
	static MemoryManager MM = MemoryManager.getMemManager();
	static RegisterManager RM = RegisterManager.getRegManager();
	private static Integer PC = 0;

	public static Integer getPC() {
		return PC;
	}

	public static void setPC(Integer pC) {
		PC = pC;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFileChooser loadfile = new JFileChooser();
					loadfile.setDialogTitle("Browse Instructions file...");
					loadfile.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
					loadfile.showOpenDialog(null);
					File file = loadfile.getSelectedFile();
					path = file.getAbsolutePath();

					try{
						new ReadInputFile(path);
						String current = ReadInputFile.readNextLine();
						int count = 0;
						while(true){
							Memory block1 = Memory.divideMem(current);
							Memory block2 = Memory.divideMem("");
							MM.instructionWrite(count, block1);
							count++;
							MM.instructionWrite(count, block2);
							count++;
							current = ReadInputFile.readNextLine();
							if(current.equals("F800")){
								Memory block1F = Memory.divideMem(current);
								Memory block2F = Memory.divideMem("");
								MM.instructionWrite(count, block1F);
								count++;
								MM.instructionWrite(count, block2F);
								break;
							}
						}
					}
					catch(IOException e){	
						// ERROR EN LEER EL FILE; PUEDE SER QUE STOP NO SE ENCONTRO
					}
					catch(InstructionsOutOfBoundsException e){
						String toprint = e.getMessage();
						//MENSAJE QUE TE INDICA QUE SUCEDIO; PQ LAS INSTRUCCIONES NO PUEDEN SER MAS DE 128B
					}

					UserInterface window = new UserInterface();
					fillMemoryDisplay();
					window.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR Reading the input file", "Error",JOptionPane.ERROR_MESSAGE);
					//e.printStackTrace();
				}
			}
		});
	}

	private static void fillMemoryDisplay(){
		for(int i=0;i<256;i+=2){
			String address = Integer.toHexString(i);
			String data;
			if(address.length()==1)
				address = "0"+address;
			if(!(MM.readFrom(i)==null)){
				String msb = MM.readFrom(i).getDatum();
				if(msb.length()==1)
					msb = "0"+msb;

				String lsb = MM.readFrom(i+1).getDatum();
				if(lsb.length()==1)
					lsb = "0"+lsb;
				data = msb+lsb;
			}
			else
				data="####";
			MemoryList.add(address+":"+data);
		}
	}


	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	private void executeInstruction(){

		while(MM.readFrom(PC)!=null){
			Memory ms = MM.readFrom(PC);
			Memory ls = MM.readFrom(PC+1);
			String bin = ms.toBinString()+ls.toBinString();
			RM.setIRMS(ms.toBinString());
			RM.setIRLS(ls.toBinString());

			InstructionExecuter iexec = new InstructionExecuter(bin);
			iexec.execute();
			PC+=2;
			SR.setText(RM.getSR().toBinString().substring(4));
			IR.setText(RM.getIRMS().toBinString()+RM.getIRLS().toBinString());

//			("Display");
			String ASCII ="";
			for(int i=252;i<=255;i++){
				try{
					String hex = MemoryManager.getMemManager().readFrom(i).getDatum();
					int decimalValue = Integer.parseInt(hex, 16);
					char c = (char)decimalValue;
					ASCII = ASCII + c;
				}
				catch(Exception e){
					ASCII = ASCII + " ";
				}
			}
			Display.setText(ASCII);
			PCdisp.setText(Integer.toBinaryString(PC));
			Acc.setText(RM.getA().toBinString());
			VBuff.setText(RM.getVBUFF().toBinString());
			R0.setText(RM.getR0().toBinString());
			R1.setText(RM.getR1().toBinString());
			R2.setText(RM.getR2().toBinString());
			R3.setText(RM.getR3().toBinString());
			R4.setText(RM.getR4().toBinString());
			R5.setText(RM.getR5().toBinString());
			R6.setText(RM.getR6().toBinString());
			R7.setText(RM.getR7().toBinString());

			if(STEPMODE)
				break;
		}
		if(MM.readFrom(PC)==null){
			JOptionPane.showMessageDialog(frame, "Execution Completed", "End of Instructions", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setBounds(100, 100, 407, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JPanel GP_Registers_Pane = new JPanel();

		JPanel panel_1 = new JPanel();

		JPanel panel = new JPanel();

		JButton Step = new JButton("Step");
		Step.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				STEPMODE = true; 
				executeInstruction();
			}
		});

		JButton Run = new JButton("Run");
		Run.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				STEPMODE = false;
				executeInstruction();
			}
		});

		JPanel IR_Pane = new JPanel();

		JPanel SR_Panel = new JPanel();

		JPanel panel_2 = new JPanel();


		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(25)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(GP_Registers_Pane, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addComponent(IR_Pane, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(SR_Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(20)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
												.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(Run)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(Step)))
																		.addGap(23))))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(36)
						.addComponent(IR_Pane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(SR_Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(GP_Registers_Pane, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
												.addGap(29)
												.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(Run)
														.addComponent(Step))))
														.addGap(64))
				);

		Label MemoryL = new Label("Memory");
		panel_2.add(MemoryL);

		MemoryList = new List();
		panel_2.add(MemoryList);

		Label SRL = new Label("SR");
		SR_Panel.add(SRL);

		SR = new Label("0000");
		SR.setBackground(Color.WHITE);
		SR_Panel.add(SR);

		Label IRL = new Label("IR");
		IR_Pane.add(IRL);

		IR = new Label("0000000000000000");
		IR.setBackground(Color.WHITE);
		IR_Pane.add(IR);

		Label KeybdL = new Label("Keybd");
		panel.add(KeybdL);

		Keybd = new JTextField("0",4);
		Keybd.setText("    ");
		Keybd.setEnabled(false);
		panel.add(Keybd);

		Label DisplayL = new Label("Display");
		panel.add(DisplayL);
		
		Display = new JLabel("    ");
		Display.setBackground(Color.WHITE);
		panel.add(Display);

		Label PCL = new Label("PC");
		panel_1.add(PCL);

		PCdisp = new Label("00000000");
		PCdisp.setBackground(Color.WHITE);
		panel_1.add(PCdisp);

		Label AccL = new Label("A");
		panel_1.add(AccL);

		Acc = new Label("00000000");
		Acc.setBackground(Color.WHITE);
		panel_1.add(Acc);

		Label VBuffL = new Label("VBuff");
		panel_1.add(VBuffL);

		VBuff = new Label("00000000");
		VBuff.setBackground(Color.WHITE);
		panel_1.add(VBuff);

		Label R0L = new Label("R0");
		GP_Registers_Pane.add(R0L);

		R0 = new Label("00000000");
		R0.setBackground(Color.WHITE);
		GP_Registers_Pane.add(R0);

		Label R1L = new Label("R1");
		GP_Registers_Pane.add(R1L);

		R1 = new Label("00000000");
		R1.setBackground(Color.WHITE);
		GP_Registers_Pane.add(R1);

		Label R2L = new Label("R2");
		GP_Registers_Pane.add(R2L);

		R2 = new Label("00000000");
		R2.setBackground(Color.WHITE);
		GP_Registers_Pane.add(R2);

		Label R3L = new Label("R3");
		GP_Registers_Pane.add(R3L);

		R3 = new Label("00000000");
		R3.setBackground(Color.WHITE);
		GP_Registers_Pane.add(R3);

		Label R4L = new Label("R4");
		GP_Registers_Pane.add(R4L);

		R4 = new Label("00000000");
		R4.setBackground(Color.WHITE);
		GP_Registers_Pane.add(R4);

		Label R5L = new Label("R5");
		GP_Registers_Pane.add(R5L);

		R5 = new Label("00000000");
		R5.setBackground(Color.WHITE);
		GP_Registers_Pane.add(R5);

		Label R6L = new Label("R6");
		GP_Registers_Pane.add(R6L);

		R6 = new Label("00000000");
		R6.setBackground(Color.WHITE);
		GP_Registers_Pane.add(R6);

		Label R7L = new Label("R7");
		GP_Registers_Pane.add(R7L);

		R7 = new Label("00000000");
		R7.setBackground(Color.WHITE);
		GP_Registers_Pane.add(R7);

		frame.getContentPane().setLayout(groupLayout);

	}

	public static List getMemoryList() {
		return MemoryList;
	}
}
