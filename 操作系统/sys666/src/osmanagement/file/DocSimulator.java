package osmanagement.file;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import osmanagement.main.FunctionItem;

public class DocSimulator implements FunctionItem {
	private static final int WID1 = 200; 
	private static final int WID2 = WID1 + 230;
	private static final int WID3 = WID2 + 230;
	private static final int WID_B = 100;
	private static final int WID_GAP = 10;
	private static final int WID_F = WID_B + WID_GAP;
	private static final int HEI1 = 300;
	private static final int HEI2 = HEI1 + 100;
	private static final int HEI3 = HEI2 + 100;
	private static final int HEI_B = 20;
	private static final int HEI_GAP = 5;
	private static final int HEI_F = HEI_B + HEI_GAP;
	private boolean flagIsShear;
	private boolean isHelpOpened = false;
	private boolean isSystemAttributesOpened = false;
	private DefaultMutableTreeNode shearPlate;
	private DefaultMutableTreeNode currentSelected;
	private JFrame frame;
	private JTree tree;
	private JScrollPane scrollTree;
	private JScrollPane []scrollTextPad = new JScrollPane[2];
	private DefaultMutableTreeNode root;
	private JTextArea []textPad = new JTextArea[2];
	private JTextField textName;
	private JTextField []output = new JTextField[2];
	private JLabel []label = new JLabel[1];
	private JButton buttonCreateFile;
	private JButton buttonCreateFolder;
	private JButton buttonRename;
	private JButton buttonShear;
	private JButton buttonPaste;
	private JButton buttonCopy;
	private JButton buttonOpenFile;
	private JButton buttonDelete;
	private JButton buttonSystemAttributes;
	private JButton buttonHelp;
	private JButton buttonFormat;
	
	public void init(){
		frame = new JFrame("osManagement_document");
		frame.setSize(WID3 + 20, HEI3 + 40);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);		
		
		root = FCB.loadFCB("blocks", FCB.getFCBPath());
		tree = new JTree(root);
		//frame.add(tree);
		tree.setVisible(true);
		//tree.setBounds(10, 10, 200, 200);
		
		scrollTree = new JScrollPane(tree);
		tree.setCellRenderer(new MyDefaultTreeCellRenderer());
		frame.add(scrollTree);
		scrollTree.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);    //ˮƽ������������Ҫ����ʾ
		scrollTree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);       //��ֱ������������Ҫ����ʾ
		scrollTree.setPreferredSize(new Dimension(300, 200));
		scrollTree.setBounds(0, 0, WID1, HEI3);
		
		tree.addTreeSelectionListener((TreeSelectionEvent e)-> {
            	tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);            	
                currentSelected = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                updateText(textPad[0], null);
                //System.out.println("��ѡ���ˣ�" + fcb.toString()); 
            }
        );
		
		for(int i = 0; i != 2; i++){
			textPad[i] = new JTextArea();
			scrollTextPad[i] = new JScrollPane(textPad[i]);
			frame.add(scrollTextPad[i]);
		}
		scrollTextPad[0].setBounds(WID2, 0, WID3 - WID2, HEI1);
		scrollTextPad[1].setBounds(WID2, HEI1, WID3 - WID2, HEI2 - HEI1);
		for(int i = 0; i != 2; i++){
			scrollTextPad[i].setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);       //��ֱ������������Ҫ����ʾ
			scrollTextPad[i].setPreferredSize(new Dimension(300, 200));
			textPad[i].setLineWrap(true);
			textPad[i].setEditable(false);
			textPad[i].setBackground(Color.lightGray);
		}
		
		for(int i = 0; i != 2; i++){
			output[i] = new JTextField();
			frame.add(output[i]);
			output[i].setEditable(false);
			output[i].setBackground(Color.lightGray);
		}
		output[0].setBounds(WID1 + WID_GAP, 0, WID_B * 2 + WID_GAP, HEI_B);
		output[1].setBounds(WID1 + WID_GAP, HEI_F * 5, WID_B * 2 + WID_GAP, HEI_B);
		output[1].setText("���а�������Ŀ");
		textName = new JTextField();
		frame.add(textName);
		textName.setBounds(WID1 + WID_GAP, HEI_F * 2, WID_B * 2 + WID_GAP, HEI_B);
		
		label[0] = new JLabel("�������������ļ����ļ�����:");
		frame.add(label[0]);
		label[0].setBounds(WID1 + WID_GAP, HEI_F, WID_B * 2 + WID_GAP, HEI_B);
		
		buttonCreateFile = new JButton("�½��ļ�");
		frame.add(buttonCreateFile);
		buttonCreateFile.setBounds(WID1 + WID_GAP, HEI_F * 3,WID_B, HEI_B);
		buttonCreateFile.addActionListener((ActionEvent e)->{
				String tempName = getLegalNameFromText(textName);
				if(tempName == null){
					return;
				}
				if(tempName.equals("")){
					tempName = "new";
				}
				DefaultMutableTreeNode node = FCB.createFCB(currentSelected, tempName, FCB.FILE);
				update();
				if(node == null){
					output[0].setText("�������ļ����½��ļ�");
				}else{
					output[0].setText("�ļ�"+ node +"�����ɹ�!");
				}
			});

		buttonCreateFolder = new JButton("�½��ļ���");
		frame.add(buttonCreateFolder);
		buttonCreateFolder.setBounds(WID1 + WID_GAP, HEI_F * 4, WID_B, HEI_B);
		buttonCreateFolder.addActionListener((ActionEvent e)->{
				String tempName = getLegalNameFromText(textName);
				if(tempName == null){
					return;
				}
				if(tempName.equals("")){
					tempName = "new";
				}
				DefaultMutableTreeNode node = FCB.createFCB(currentSelected, tempName, FCB.FOLDER);
				update();
				if(node == null){
					output[0].setText("�������ļ����½��ļ���");
				}else{
					output[0].setText("�ļ���"+ node +"�����ɹ�!");
				}
			});

		buttonRename = new JButton("������");
		frame.add(buttonRename);
		buttonRename.setBounds(WID1 + WID_GAP + WID_F, HEI_F * 3, WID_B, HEI_F + HEI_B);
		buttonRename.addActionListener((ActionEvent e)->{
				String tempName = getLegalNameFromText(textName);
				if(tempName == null){
					return;
				}
				if(tempName.equals("")){
					output[0].setText("�������޸ĺ������");
					return;
				}
				int exi = JOptionPane.showConfirmDialog (null, "��ȷ��Ҫ�����ļ�������Ϊ\"" + tempName + "\"?", "������ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (exi == JOptionPane.YES_OPTION){
    			((FCB)currentSelected.getUserObject()).name = FCB.getName((DefaultMutableTreeNode) currentSelected.getParent(), tempName);
    			update();
                }else{
                    return;
                }
			});
			
		buttonShear = new JButton("����");
		frame.add(buttonShear);
		buttonShear.setBounds(WID1 + WID_GAP, HEI_F * 6, WID_B, HEI_B);
		buttonShear.addActionListener((ActionEvent e)->{ 
				shearPlate = currentSelected;
				flagIsShear = true;
				output[1].setText("��ѡ��,���ƶ�: " + shearPlate);
			});
		
		buttonCopy = new JButton("����");
		frame.add(buttonCopy);
		buttonCopy.setBounds(WID1 + WID_GAP, HEI_F * 7, WID_B , HEI_B);
		buttonCopy.addActionListener((ActionEvent e)->{
				shearPlate = currentSelected;
				flagIsShear = false;
				output[1].setText("��ѡ��,�ɸ���: " + shearPlate);
			});
		
		buttonPaste = new JButton("ճ��");
		frame.add(buttonPaste);
		buttonPaste.setBounds(WID1 + WID_GAP + WID_F, HEI_F * 6, WID_B, HEI_F + HEI_B);
		buttonPaste.addActionListener((ActionEvent e)->{ 
				if(currentSelected == null){
					return;
				}
				if(shearPlate == null){
					output[0].setText("������������Ŀ");
					return;
				}
				if(flagIsShear){
					int flag = FCB.moveFCB(shearPlate, currentSelected);
					update();
					if(flag == -1){
						output[0].setText("����ʧ��,���ɽ��ļ��ƶ�������Ŀ¼");
					}else{
						output[0].setText("�ƶ��ɹ�");
					}
				}
				else{
					int flag = FCB.copyFile(shearPlate, currentSelected);
					update();
					if(flag == -1){
						output[0].setText("����ʧ��,���̿ռ�����");
					}else if(flag == -2){
						output[0].setText("����ʧ��,���ɽ��ļ����Ƶ�����Ŀ¼");
					}else{
						output[0].setText("���Ƴɹ�");
					}
				}
				output[1].setText("������������Ŀ");
				shearPlate = null;
			});
	
		buttonOpenFile = new JButton("���ļ�");
		frame.add(buttonOpenFile);
		buttonOpenFile.setBounds(WID1 + WID_GAP, HEI_F * 8, WID_B, HEI_B);
		buttonOpenFile.addActionListener((ActionEvent e)->{
				openFileInFrame(currentSelected);
			});
		
		buttonDelete = new JButton("ɾ��");
		frame.add(buttonDelete);
		buttonDelete.setBounds(WID1 + WID_GAP + WID_F, HEI_F * 8, WID_B, HEI_B);
		buttonDelete.addActionListener((ActionEvent e)->{
				if(currentSelected == root){
					output[0].setText("��Ŀ¼���������!");
					JOptionPane.showConfirmDialog (null, "��Ŀ¼���������!���б�Ҫ������ʽ��!", "������ʾ", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
					return;
				}
				int exi = JOptionPane.showConfirmDialog (null, "��ȷ��Ҫɾ������?", "������ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (exi == JOptionPane.YES_OPTION){
                	boolean flag = FCB.deleteFCB(currentSelected);
                	if(flag){
                    	currentSelected = null;
        				update();
                	}
                	else{
                		output[0].setText("���ļ��Ѵ�,���ȹر��ļ�");
                	}
                }else{
                    return;
                }				
			});
		
		buttonHelp = new JButton("����");
		frame.add(buttonHelp);
		buttonHelp.setBounds(WID1 + WID_GAP + WID_F, HEI2 + HEI_F * 2, WID_B, HEI_B);
		buttonHelp.addActionListener((ActionEvent e)->{
				openHelpFrame();
			});
		
		buttonSystemAttributes = new JButton("ϵͳ����");
		frame.add(buttonSystemAttributes);
		buttonSystemAttributes.setBounds(WID2 + WID_GAP, HEI2 + HEI_F * 2, WID_B, HEI_B);
		buttonSystemAttributes.addActionListener((ActionEvent e)->{
				openSystemAttributes();
			});
		
		buttonFormat = new JButton("��ʽ��");
		frame.add(buttonFormat);
		buttonFormat.setBounds(WID2 + WID_GAP + WID_F, HEI2 + HEI_F * 2, WID_B, HEI_B);
		buttonFormat.addActionListener((ActionEvent e)->{
				int exi = JOptionPane.showConfirmDialog (null, "��ȷ��Ҫ���и�ʽ�����������޷��ָ���", "������ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (exi == JOptionPane.YES_OPTION){
                	exi = JOptionPane.showConfirmDialog (null, "���ٴ�ȷ��!��ʽ�������޷��ָ�,���������!", "������ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (exi == JOptionPane.YES_OPTION){
        				FCB.format(root);
        				update();
                    }else{
                        return;
                    }
                }else{
                    return;
                }
			});
		
		update();
		//FCB.storeFCB(root, "FCB.txt");
	}
	
	private int updateText(JTextArea text1, JTextArea text2){
		if (text1 != null){
        	text1.setText("");
        	if(currentSelected != null){
    	        FCB fcb = (FCB)currentSelected.getUserObject();
    	        if(fcb.getType() == FCB.FOLDER){
    	        	text1.append("�ļ�����:\t" + fcb.name + "\n");
    	        }else{
    	        	text1.append("�ļ���:\t" + fcb.name + "\n");
    	        }
    	        text1.append("·��:\t" + FCB.getParentPath(currentSelected) + "\n");
    	        if(fcb.getType() == FCB.FOLDER){
    	            text1.append("����:\t" + "�ļ���" + "\n");
    	        }else{
    	            text1.append("����:\t" + "�ļ�" + "\n");
    	            if(fcb.isOpened){
        	            text1.append("��״̬:\t" + "�Ѵ�" + "\n");
    	            }else{
        	            text1.append("��״̬:\t" + "δ��" + "\n");
    	            }
    	            text1.append("�ļ���С:\t" + fcb.blocks().size() + "KB\n");
    	            text1.append("�洢λ��:\n");
    	            for(int i = 0; i != fcb.blocks().size(); i++){
    	            	text1.append(fcb.blocks().get(i) + ", ");
    	            }
    	            text1.append("\n");
    	        }
        	}
        }
		if(text2 != null){
			text2.setText("");
			text2.append("����ʹ����:\t" + (FCB.VOLUMN - FCB.countBlockFree) + "/" + FCB.VOLUMN + "\n");
		}
		return 1;
	}	
	private int update(){
		FCB.storeFCB(root, FCB.getFCBPath());
		tree.updateUI();
		updateText(textPad[0], textPad[1]);
		return 1;
	}
	private String getLegalNameFromText(JTextField text){
		String name = text.getText();
		text.setText("");
		return getLegalName(name);
	}
	private String getLegalName(String name){
		if(name.indexOf(' ') != -1 || name.indexOf('\t') != -1 || name.indexOf('\r') != -1 || name.indexOf('\n') != -1 
				|| name.indexOf('\\') != -1 || name.indexOf('/') != -1 || name.indexOf('~') != -1 || name.indexOf('!') != -1
				 || name.indexOf('@') != -1 || name.indexOf('#') != -1 || name.indexOf('$') != -1 || name.indexOf('%') != -1
				 || name.indexOf('^') != -1 || name.indexOf('&') != -1 || name.indexOf('?') != -1){
			output[0].setText("���������Ϸ�");
			return null;
		}
		return name;
	}
	private int openFileInFrame(DefaultMutableTreeNode node){
		if(node == null){
    		output[0].setText("��ѡ��Ҫ�������ļ�");
			return 0;
		}
		FCB fcb = (FCB)node.getUserObject();
    	if(fcb.getType() == FCB.FOLDER){
    		output[0].setText("ֻ�ɴ��ļ�");
    		return -1;
    	}
    	if(fcb.isOpened){
    		output[0].setText("�ļ�\"" + fcb + "\"�Ѵ�,��ֹ�ظ���");
    		return -2;
    	}
    	output[0].setText("�ɹ����ļ�\"" + fcb + "\"");
    	fcb.isOpened = true;
    	updateText(textPad[0], null);
		JFrame subFrame = new JFrame("�ļ��༭: " + fcb.name + " ·��:" + FCB.getParentPath(node));
		subFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		subFrame.setSize(500, 400);
		subFrame.setLocation(25, 25);
		subFrame.setLayout(null);
		subFrame.setVisible(true);	

		JTextArea text = new JTextArea();
		JScrollPane scrollText = new JScrollPane(text);
		subFrame.add(scrollText);
		scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);       //��ֱ������������Ҫ����ʾ
		scrollText.setPreferredSize(new Dimension(300, 200));
		scrollText.setBounds(10, 10, 460, 300);
		text.setLineWrap(true);
		
		JButton buttonStoreFileSub = new JButton("�����ļ�");
		subFrame.add(buttonStoreFileSub);
		buttonStoreFileSub.setBounds(20, 320, 100, 20);
		buttonStoreFileSub.addActionListener((ActionEvent e)->{
				if(FCB.storeFile(text.getText(), node) == -1){
					output[0].setText("���̿ռ䲻��");
					JOptionPane.showConfirmDialog (null, "���̿ռ䲻�㣬�޷�����!", "������ʾ", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
					return;
				}
				update();
			});
		
		subFrame.addWindowListener (new WindowAdapter ()
        {
            @Override
            public void windowClosing ( WindowEvent e )
            {
                int exi = JOptionPane.showConfirmDialog (null, "�Ƿ����˳�ʱ���棿��", "������ʾ", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (exi == JOptionPane.YES_OPTION){
    				if(FCB.storeFile(text.getText(), node) == -1){
    					output[0].setText("���̿ռ䲻��");
    					JOptionPane.showConfirmDialog (null, "���̿ռ䲻�㣬�޷�����!", "������ʾ", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
    					return;
    				}
    				fcb.isOpened = false;
    				update();
                    subFrame.dispose();
                }
                else if(exi == JOptionPane.NO_OPTION){
    				fcb.isOpened = false;
    				updateText(textPad[0], null);
                	subFrame.dispose();
                }
                else{
                    return;
                }
            }
        });
		text.setText(FCB.openFile(node));
		return 1;
	}
	private int openHelpFrame(){
		if(isHelpOpened){
			output[0].setText("���ڴ����Ѵ�");
			return -1;
		}
		isHelpOpened = true;
		JFrame helpFrame = new JFrame("����");
		helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		helpFrame.setSize(300, 400);
		helpFrame.setLayout(null);
		helpFrame.setVisible(true);	
		JTextArea text = new JTextArea();
		helpFrame.add(text);
		text.setBounds(10, 10, 260, 300);
		text.setBackground(Color.lightGray);
		text.setEditable(false);
		text.setText("������Ա:\t���ٷ�\nѧ��:\t1452692\n�γ�:\t����ϵͳ\n�꼶:\t���ѧԺ2014��\n�������:\tJAVA\n���뻷��:\teclipse-Mars\n\n\t��ӭʹ��!");
		helpFrame.addWindowListener (new WindowAdapter ()
        {
            @Override
            public void windowClosing ( WindowEvent e )
            {
            	isHelpOpened = false;
                helpFrame.dispose();
            }
        });
		return 1;
	}
	private int openSystemAttributes(){
		if(isSystemAttributesOpened){
			output[0].setText("ϵͳ���Դ����Ѵ�");
			return -1;
		}
		isSystemAttributesOpened = true;
		JFrame saFrame = new JFrame("ϵͳ����");
		saFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		saFrame.setSize(300, 400);
		saFrame.setLayout(null);
		saFrame.setVisible(true);	

		JTextArea text = new JTextArea();
		JScrollPane scrollText = new JScrollPane(text);
		saFrame.add(scrollText);
		scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollText.setPreferredSize(new Dimension(300, 200));
		scrollText.setBounds(10, 10, 260, 80);
		text.setBackground(Color.lightGray);
		text.setEditable(false);
		text.setLineWrap(true);
		updateText(null, text);
		text.append("������Ӳ�̾���ʹ�����:\n��ɫ����ҳ����У���ɫ����ҳ������");
		
		int size = 22;
		int border = size + 1;
		int width = 10;
		int height = 0;
		if(FCB.VOLUMN > 0){
			height = (FCB.VOLUMN - 1) / width + 1;
		}
		JPanel disk = new JPanel();
		
		JScrollPane scrollDisk = new JScrollPane(disk);
		saFrame.add(scrollDisk);
		scrollDisk.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollDisk.setPreferredSize(new Dimension(300, 200));
		scrollDisk.setBounds(10, 90, 260, 270);
		
		disk.setLayout(null);
		JPanel[] pages = new JPanel[FCB.VOLUMN];
		disk.setPreferredSize(new Dimension(border * width, border * height));
		disk.setVisible(true);
		for(int i = 0; i != height; i++){
			for(int j = 0; j != width; j++){
				if(i * width + j == FCB.VOLUMN){
					break;
				}
				pages[i * width + j] = new JPanel();
				disk.add(pages[i * width + j]);
				pages[i * width + j].setBounds(border * j, border * i, size, size);
				if(FCB.isBlockFree(i * width + j)){
					pages[i * width + j].setBackground(Color.cyan);
				}
				else{
					pages[i * width + j].setBackground(Color.orange);
				}
			}
		}		
		disk.revalidate();
		saFrame.addWindowListener (new WindowAdapter ()
        {
            @Override
            public void windowClosing ( WindowEvent e )
            {
            	isSystemAttributesOpened = false;
                saFrame.dispose();
            }
        });
		return 1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DocSimulator().excuteFuction();
	}

	@Override
	public void excuteFuction() {
		DocSimulator ds = new DocSimulator();
		ds.init();
	}
}
