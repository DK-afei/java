package osmanagement.pagging;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import osmanagement.main.FunctionItem;

public class Paging implements FunctionItem {
	Page memory[] = new Page[PforMEM];
	Page disk[] = new Page[PforDISK];
	boolean memoryFull = false;
	int listFIFO[] = new int[PforMEM];
	int listLRU[] = new int[PforMEM];
	int pc = 0;
	int currentIad = 0;
	Instruction currentIns = null;
	int cycleTotal = 0;
	int cycleLack = 0;
	int fetchMethod = 0;
	
	final static int IperP = 10;
	private final static int PforMEM = 4;
	private final static int PforDISK = 32;
	private final static int TOTAL = IperP * PforDISK;
	protected static Random randNum = new Random();
	private boolean flagAutoOp = false;
	
	private final static int BORDER = 20;
	private final static int DISK_WIDTH = 50;
	private final static int DISK_HEIGHT = 18;
	private final static int DISK_GAP = 1;
	private final static int MEM_WIDTH = 120;
	private final static int MEM_HEIGHT = 220;
	private final static int GAP_WIDTH = 10;
	private final static int FIELD_HEIGHT = 20;
	private final static int FIELD_WIDTH = 120;
	private JFrame frame;
	private JLabel label[] = new JLabel[11];
	private JTextArea memBlock[] = new JTextArea[PforMEM];
	private JTextField diskBlock[] = new JTextField[PforDISK];
	private JTextField textCurrentOp = new JTextField();
	private JTextField textCurrentPC = new JTextField();
	private JTextField textCycleTotal = new JTextField();
	private JTextField textCycleLack = new JTextField();
	private JTextField textCycleLackRate = new JTextField();
	private JTextField textOutput = new JTextField();
	private JTextField textCurrentMethod = new JTextField();
	private JTextField textStartIad = new JTextField();
	private JButton buttonOp1 = new JButton("����ִ��");
	private JButton buttonOp10 = new JButton("ִ��10��");
	private JButton buttonOp50 = new JButton("ִ��50��");
	private JButton buttonOpEnd = new JButton("ִ�е���");
	private JButton buttonFIFO = new JButton("�Ƚ��ȳ�����");
	private JButton buttonLRU = new JButton("�������ʹ��");
	private JButton buttonAutoOp = new JButton("�Զ�ִ��");
	private JButton buttonAutoOpStop = new JButton("��ͣ�Զ�ִ��");
	private JButton buttonReStart = new JButton("�����ڴ�");
	private JButton buttonReProgram = new JButton("������±�д����");
	private Color colorOn = Color.green;
	private Color colorOff = Color.cyan;
	private static Color colorUnEditable = Color.lightGray;
	private DecimalFormat df = new DecimalFormat( "0.00000 ");
	
	private ArrayList<Instruction> insTemplates = new ArrayList<Instruction>();
	private Instruction createIns(int index) {
		if(index > -1 && index < insTemplates.size()) {
			return insTemplates.get(index).create();
		}
		return null;
	}
	
 	private boolean fetch(Instruction instruction){
		if(instruction == null) return false;
		currentIns = instruction;
		currentIns.opCurrentTimes++;
		cycleTotal++;
		return true;
	}
	private boolean fetchByList(int iad, int[] list){
		if(iad < 0 || iad >= TOTAL){
			textOutput.setText("ȫ��ִ����ϣ�");
			return false;
		}
		int pageNum = iad / IperP;
		int offset = iad % IperP;
		for(int i = 0; i != PforMEM; i++){
			if(memory[i] != null){
				if(memory[i].num == pageNum){
					for(int j = 0; j != PforMEM; j++){
						if(listLRU[j] > listLRU[i]){
							listLRU[j]--;
						}
					}
					listLRU[i] = PforMEM;
					textOutput.setText("�ɹ����ڴ��ҵ���ָ�λ�ã���" + i + "���ڴ�飬��" + offset + "��ָ��");
					fetch(memory[i].instruction[offset]);
					return true;
				}
			}
		}
		if(!memoryFull){
			for(int i = 0; i != PforMEM; i++){
				if(list[i] == 0){
					for(int j = 0; j != PforMEM; j++){
						if(list[j] > 0){
							listFIFO[j]--;
							listLRU[j]--;
						}
					}
					listFIFO[i] = PforMEM;
					listLRU[i] = PforMEM;
					memory[i] = disk[pageNum];
					textOutput.setText("δ���ڴ��ҵ���ָ������ڿ�����" + i + "���ڴ�飬��ָ���ڿ���λ��Ϊ" + offset);
					fetch(memory[i].instruction[offset]);
					cycleLack++;
					return true;
				}
			}
			memoryFull = true;
		}
		for(int i = 0; i != PforMEM; i++){
			if(list[i] == 1){
				for(int j = 0; j != PforMEM; j++){
						listFIFO[j]--;
						listLRU[j]--;
				}
				listFIFO[i] = PforMEM;
				listLRU[i] = PforMEM;
				memory[i] = disk[pageNum];
				fetch(memory[i].instruction[offset]);
				cycleLack++;
				return true;
			}
		}
		return true;
	}
	private boolean fetchFIFO(int iad){
		fetchByList(iad, listFIFO);
		return true;
	}
	private boolean fetchLRU(int iad){
		fetchByList(iad, listLRU);
		return true;
	}
	private boolean computePC(){
		pc += 1;
		if(currentIns == null){
			return false;
		}
		pc = currentIns.cComputePC(pc);
		if(pc < 0){
			pc = 0;
		}
		if(pc > TOTAL){
			pc = TOTAL;
		}
		return true;
	}
	private boolean operateOnce(){
		currentIad = pc;
		if(pc == TOTAL){
			textOutput.setText("ȫ��ִ����ϣ�");
			return false;
		}
		switch(fetchMethod){
		case 0:
			fetchFIFO(pc);
			break;
		case 1:
			fetchLRU(pc);
			break;
		default:
			break;	
		}
		computePC();
		return true;
	}
	private boolean autoOp(){
		flagAutoOp = true;
		for(;flagAutoOp && currentIad != TOTAL;){
			operateOnce();
			paint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flagAutoOp = false;
		return true;
	}
	
	private boolean setProgram(){
		for(int i = 0; i != PforDISK; i++){
			for(int j = 0; j != IperP; j++){
				int rNum = randNum.nextInt(5);
				if(rNum < 3){
					disk[i].instruction[j] = createIns(0);
				}
				else if(rNum < 4){
					disk[i].instruction[j] = createIns(1);
				}
				else{
					disk[i].instruction[j] = createIns(2);
				}
				//System.out.println(disk[i].instruction[j].op + "    " + disk[i].instruction[j].num);
			}
		}
		return true;
	}
	private boolean reStart(int startPoint){
		flagAutoOp = false;
		for(int i = 0; i != PforDISK; i++){
			for(int j = 0; j != IperP; j++){
				disk[i].instruction[j].opCurrentTimes = 0;
			}
		}
		for(int i = 0; i != PforMEM; i++){
			listFIFO[i] = 0;
			listLRU[i] = 0;
			memory[i] = null;
		}
		textOutput.setText("");
		textCurrentOp.setText("δ��ʼִ�в���");
		memoryFull = false;
		if(startPoint >= 0 && startPoint < TOTAL){
			pc = startPoint;
		}
		else{
			pc = 0;
		}
		currentIad = 0;
		currentIns = null;
		cycleTotal = 0;
		cycleLack = 0;
		paint();
		return true;
	}
	private boolean paint(){
		for(int i = 0; i != PforDISK; i++){
			diskBlock[i].setBackground(colorOff);
		}
		for(int i = 0; i != PforMEM; i++){
			if(memory[i] == null){
				memBlock[i].setText("��ҳΪ��");
			}
			else{
				diskBlock[memory[i].num].setBackground(colorOn);
				memBlock[i].setText("�滻���ȶ�:");
				switch(fetchMethod){
					case 0:
						memBlock[i].append("" + listFIFO[i]);
						break;
					case 1:
						memBlock[i].append("" + listLRU[i]);
						break;
					default:
				}
				memBlock[i].append("\n��" + memory[i].num + "ҳ:\n");
				for(int j = 0; j != IperP; j++){
					memBlock[i].append(j + "." + memory[i].instruction[j].getString() + "\n");
				}
			}
		}
		if(currentIad == TOTAL){
			textCurrentOp.setText(TOTAL + ". ��ֹ����");
		}
		else{
			textCurrentOp.setText(currentIad + ". " + currentGetString());
		}
		textCurrentPC.setText("" + pc);
		textCycleTotal.setText("" + cycleTotal);
		textCycleLack.setText("" + cycleLack);
		if(cycleTotal == 0){
			textCycleLackRate.setText("0");
		}
		else{
			textCycleLackRate.setText(df.format((double)cycleLack/cycleTotal));
		}
		switch(fetchMethod){
		case 0:
			textCurrentMethod.setText("��ǰ�㷨:�Ƚ��ȳ������㷨");
			break;
		case 1:
			textCurrentMethod.setText("��ǰ�㷨:�������ʹ�õ����㷨");
			break;
		default:
			textCurrentMethod.setText("");
		}
		return true;
	}
	public void init(){
		insTemplates.add(new InsNext());
		insTemplates.add(new InsJump());
		insTemplates.add(new InsBranch());
		
		for(int i = 0; i != PforDISK; i++){
			disk[i] = new Page(i);
		}
		setProgram();
		for(int i = 0; i != PforMEM; i++){
			listFIFO[i] = 0;
			listLRU[i] = 0;
		}
		frame = new JFrame("osManagement_paging");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(BORDER*2 + DISK_WIDTH + (GAP_WIDTH + MEM_WIDTH)*PforMEM + 20, BORDER*2 + (DISK_HEIGHT + DISK_GAP)*PforDISK + 40);
		frame.setVisible(true);
		frame.setLayout(null);
		//Disk Display
		for(int i = 0; i != PforDISK; i++){
			diskBlock[i] = new JTextField("No."+ i);
			frame.add(diskBlock[i]);
			diskBlock[i].setVisible(true);
			diskBlock[i].setBounds(BORDER, BORDER + (DISK_HEIGHT + DISK_GAP)*i, DISK_WIDTH, DISK_HEIGHT);
			diskBlock[i].setBackground(colorOff);
		}
		//Memory Display
		for(int i = 0; i != PforMEM; i++){
			memBlock[i] = new JTextArea(IperP + 1, MEM_WIDTH);
			frame.add(memBlock[i]);
			memBlock[i].setVisible(true);
			memBlock[i].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH + (GAP_WIDTH + MEM_WIDTH)*i, BORDER + FIELD_HEIGHT, MEM_WIDTH, MEM_HEIGHT);
			memBlock[i].setBackground(colorUnEditable);
			memBlock[i].setEditable(false);
		}
		//label
		for(int i = 0; i != 11; i++){
			label[i] = new JLabel();
			label[i].setVisible(true);
			frame.add(label[i]);
		}
		label[0].setText("�ڴ��ʹ����������ȼ�Ϊ 1 �����ȱ������ڴ棩��");
		label[0].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER, FIELD_WIDTH*3, FIELD_HEIGHT);
		label[1].setText("��ǰָ�");
		label[1].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT, FIELD_WIDTH, FIELD_HEIGHT);
		label[2].setText("����ָ��[PC]��");
		label[2].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*2, FIELD_WIDTH, FIELD_HEIGHT);
		label[3].setText("ִ��ָ�����");
		label[3].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH + FIELD_WIDTH*2, BORDER + MEM_HEIGHT + FIELD_HEIGHT*2, FIELD_WIDTH, FIELD_HEIGHT);
		label[4].setText("ȱҳ����");
		label[4].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH + FIELD_WIDTH*2, BORDER + MEM_HEIGHT + FIELD_HEIGHT*3, FIELD_WIDTH, FIELD_HEIGHT);
		label[5].setText("ȱҳ��");
		label[5].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*3, FIELD_WIDTH, FIELD_HEIGHT);
		label[6].setText("ѡ�������");
		label[6].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*5, FIELD_WIDTH, FIELD_HEIGHT);
		label[7].setText("ѡ������㷨��ͬʱ�����ڴ棩��");
		label[7].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*7, FIELD_WIDTH*2, FIELD_HEIGHT);
		label[8].setText("�Զ�������");
		label[8].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*10, FIELD_WIDTH, FIELD_HEIGHT);
		label[9].setText("�����ڴ�ͼĴ�����");
		label[9].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*12, FIELD_WIDTH, FIELD_HEIGHT);
		label[10].setText("����������");
		label[10].setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*14, FIELD_WIDTH, FIELD_HEIGHT);
		//textField
		frame.add(textCurrentOp);
		textCurrentOp.setEditable(false);
		textCurrentOp.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH + FIELD_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT, FIELD_WIDTH*3, FIELD_HEIGHT);
		textCurrentOp.setBackground(colorUnEditable);

		frame.add(textCurrentPC);
		textCurrentPC.setEditable(false);
		textCurrentPC.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH + FIELD_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*2, FIELD_WIDTH, FIELD_HEIGHT);
		textCurrentPC.setBackground(colorUnEditable);

		frame.add(textCycleTotal);
		textCycleTotal.setEditable(false);
		textCycleTotal.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH + FIELD_WIDTH*3, BORDER + MEM_HEIGHT + FIELD_HEIGHT*2, FIELD_WIDTH, FIELD_HEIGHT);
		textCycleTotal.setBackground(colorUnEditable);

		frame.add(textCycleLack);
		textCycleLack.setEditable(false);
		textCycleLack.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH + FIELD_WIDTH*3, BORDER + MEM_HEIGHT + FIELD_HEIGHT*3, FIELD_WIDTH, FIELD_HEIGHT);
		textCycleLack.setBackground(colorUnEditable);

		frame.add(textCycleLackRate);
		textCycleLackRate.setEditable(false);
		textCycleLackRate.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH + FIELD_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*3, FIELD_WIDTH, FIELD_HEIGHT);
		textCycleLackRate.setBackground(colorUnEditable);

		frame.add(textOutput);
		textOutput.setEditable(false);
		textOutput.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*4, FIELD_WIDTH*4, FIELD_HEIGHT);
		textOutput.setBackground(colorUnEditable);
		
		frame.add(textCurrentMethod);
		textCurrentMethod.setEditable(false);
		textCurrentMethod.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*8, FIELD_WIDTH*2, FIELD_HEIGHT);
		textCurrentMethod.setBackground(colorUnEditable);
		
		frame.add(textStartIad);
		textStartIad.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*13, FIELD_WIDTH/2, FIELD_HEIGHT);
		//button
		frame.add(buttonOp1);
		buttonOp1.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*6, FIELD_WIDTH, FIELD_HEIGHT);
		frame.add(buttonOp10);
		buttonOp10.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH*2 + FIELD_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*6, FIELD_WIDTH, FIELD_HEIGHT);
		frame.add(buttonOp50);
		buttonOp50.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH*3 + FIELD_WIDTH*2, BORDER + MEM_HEIGHT + FIELD_HEIGHT*6, FIELD_WIDTH, FIELD_HEIGHT);
		frame.add(buttonOpEnd);
		buttonOpEnd.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH*4 + FIELD_WIDTH*3, BORDER + MEM_HEIGHT + FIELD_HEIGHT*6, FIELD_WIDTH, FIELD_HEIGHT);
		frame.add(buttonFIFO);
		buttonFIFO.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*9, FIELD_WIDTH, FIELD_HEIGHT);
		frame.add(buttonLRU);
		buttonLRU.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH*2 + FIELD_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*9, FIELD_WIDTH, FIELD_HEIGHT);
		frame.add(buttonAutoOp);
		buttonAutoOp.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*11, FIELD_WIDTH, FIELD_HEIGHT);
		frame.add(buttonAutoOpStop);
		buttonAutoOpStop.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH*2 + FIELD_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*11, FIELD_WIDTH, FIELD_HEIGHT);
		frame.add(buttonReStart);
		buttonReStart.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH + FIELD_WIDTH/2, BORDER + MEM_HEIGHT + FIELD_HEIGHT*13, FIELD_WIDTH, FIELD_HEIGHT);
		frame.add(buttonReProgram);
		buttonReProgram.setBounds(BORDER + DISK_WIDTH + GAP_WIDTH, BORDER + MEM_HEIGHT + FIELD_HEIGHT*15, FIELD_WIDTH*3/2, FIELD_HEIGHT);
		
		buttonOp1.addActionListener((ActionEvent e)->{
				operateOnce();
				paint();
			});
		buttonOp10.addActionListener((ActionEvent e)->{
				for(int i = 0; i != 10; i++){
					operateOnce();
					paint();
				}
			});
		buttonOp50.addActionListener((ActionEvent e)->{
				for(int i = 0; i != 50; i++){
					operateOnce();
					paint();
				}
			});
		buttonOpEnd.addActionListener((ActionEvent e)->{
				for(; currentIad != TOTAL; ){
					operateOnce();
					paint();
				}
			});
		buttonFIFO.addActionListener((ActionEvent e)->{
				fetchMethod = 0;
				reStart(0);
			});
		buttonLRU.addActionListener((ActionEvent e)->{
				fetchMethod = 1;
				reStart(0);
				paint();
			});
		buttonAutoOp.addActionListener((ActionEvent e)->{
				Thread thread = new Thread(new Runnable(){
					public void run(){
						autoOp();
					}
				});
				thread.start();
			});
		buttonAutoOpStop.addActionListener((ActionEvent e)->{
				flagAutoOp = false;
			});
		buttonReStart.addActionListener((ActionEvent e)->{
				String s = textStartIad.getText();
				int startIad = 0;
				textStartIad.setText("");
				boolean b = s.matches("[0-9]*") && s.length()>0;
				if(b){
					startIad = Integer.parseInt(s);
				}
				reStart(startIad);
			});
		buttonReProgram.addActionListener((ActionEvent e)->{
				setProgram();
				reStart(0);
				textOutput.setText("�ɹ����±�д����");
			});
		reStart(0);
	}
	@Override
	public void excuteFuction() {
		Paging p = new Paging();
		p.init();
	}
	
//	int currentOp() {
//		if(currentIns != null) {
//			return currentIns.op;
//		}
//		return 0;
//	}
	String currentGetString() {
		if(currentIns != null) {
			return currentIns.getString();
		}
		return "NULL";
	}
	int currentNum() {
		if(currentIns != null) {
			return currentIns.num;
		}
		return 0;
	}
	int currentOpTimes() {
		if(currentIns != null) {
			return currentIns.opTimes;
		}
		return 0;
	}
	int currentOpCurrentTimes() {
		if(currentIns != null) {
			return currentIns.opCurrentTimes;
		}
		return 0;
	}
}

class Page{
	int num;
	Instruction instruction[] = new Instruction[Paging.IperP];
	Page(int n){
		num = n;
		for(int i = 0; i != Paging.IperP; i++){
			instruction[i] = new Instruction();
		}
	}
}

class Instruction{
	//int op = 0;
	int num = 0;
	int opTimes = 0;
	int opCurrentTimes = 0;
	protected static Random randNum = new Random();
	String cGetString(int n) {
		return null;
	}
	String getString(){
		return cGetString(num);
	}
	int cComputePC(int currentPc) {
		return -1;
	}
	Instruction create() {
		return null;
	}
}
