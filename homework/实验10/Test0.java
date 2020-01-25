package project;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class Test0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new MyFrame("MyFrame");
	}                                   

}
 class MyFrame extends Frame
 {
	 private Panel Card_Panel = null;
	 private Panel Control_Panel = null;
	 private CardLayout cardLayout = null; 
	 private FlowLayout flowLayout = null;
	 private Label l1,l2,l3,l4;
	 private TextField tf = null;
	 private Button b1,b2,b3,b4;
	 public MyFrame(String title)
	 {
		 super(title);
		 init();
		 Register();
	 }
	 void init()
	 {
		 Card_Panel = new Panel();
		 Control_Panel = new Panel();
		 cardLayout = new CardLayout();
		 flowLayout = new FlowLayout();
		 Card_Panel.setLayout(cardLayout);
		 Control_Panel.setLayout(flowLayout);
		 
		 l1 = new Label("第一页内容",Label.CENTER);
		 l2 = new Label("第二页内容",Label.CENTER);
		 tf = new TextField();
		 l3 = new Label("第四页内容",Label.CENTER);
		 l4 = new Label("第五页内容",Label.CENTER);
		 
		 Card_Panel.add(l1);
		 Card_Panel.add(l2);
		 Card_Panel.add(tf);
		 Card_Panel.add(l3);
		 Card_Panel.add(l4);
		 
		 b1 = new Button("第一张");
		 b2 = new Button("上一张");
		 b3 = new Button("下一张");
		 b4 = new Button("最后一张");
		 
		 Control_Panel.add(b1);
		 Control_Panel.add(b2);
		 Control_Panel.add(b3);
		 Control_Panel.add(b4);
		 
		 this.setBounds(0, 0, 666, 666);
		 this.add(Card_Panel,BorderLayout.CENTER);
		 this.add(Control_Panel,BorderLayout.SOUTH);
		 this.setVisible(true);
	 }
	 
	 void Register()
	 {
		 BtnListener btnListener = new BtnListener();
		 b1.addActionListener(btnListener);
		 b2.addActionListener(btnListener);
		 b3.addActionListener(btnListener);
		 b4.addActionListener(btnListener);
		 l1.addMouseMotionListener(new MouseMotionAdapter() {
			 @Override
			public void mouseMoved(MouseEvent e) {
				l1.setText(e.getX()+","+e.getY());
			}
		});
	 }
	 
	 class BtnListener implements ActionListener
	 {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			System.out.println(e.getActionCommand());
//			System.out.println(e.getSource());
			Object obj = e.getSource();
			if(obj.equals(b1))
			{
				cardLayout.first(Card_Panel);
			}
			else if(obj.equals(b2))
			{
				cardLayout.previous(Card_Panel);
			}
			else if(obj.equals(b3))
			{
				cardLayout.next(Card_Panel);
			}
			else if(obj.equals(b4))
			{
				cardLayout.last(Card_Panel);
			}
		}
		 
	 }
 }
