package project;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.Maze.Point;
public class ShowMaze extends Application{
	private Rectangle r;
	ArrayList<Rectangle> ar = new ArrayList();
	private Button b1,b2,b3,b4;
	private Pane pane;
	private Maze m;
	final int size = 22;
	final int realSize = size * 2 + 1;
	private static int n = 0;
	private Point[][] maze,maze0;
	private Point enterPoint;
	private Point exitPoint;
	private Point now_point; // ��ǰλ��,�ٴ˳���ʹ��һ��Point��������������ʾ��Point��ϵͳ���õ��ࡣ���а�����x��y��������ֵ��
	private Point start_point;	// ���λ�á�
	private Point final_point;	// �յ�λ�á�
	private MazeStack<Point> finalPath; // ��¼�����ҵ���һ����ȷ��·���е�����λ�á�
	private ArrayList<Point> allPath;  // ��¼�����߹���·���е�����λ�á���
	private Timeline animation;
	private boolean isStarting = false;
	private boolean isReset = false;
	public static final int PASS_BY = 3;
	private Label l1 =  new Label("EnterX:");
	private Label l2 =  new Label("EnterY:");
	private Label l3 =  new Label("ExitX:");
	private Label l4 =  new Label("ExitY:");
	private TextField t1 = new TextField();
	private TextField t2 = new TextField();
	private TextField t3 = new TextField();
	private TextField t4 = new TextField();
	private Pane showNodes;
	private Label label;
	private VBox vv;
	Point[] pp ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}
	@Override
	public void start(Stage ps) throws Exception {
		// TODO Auto-generated method stub
		m = new Maze();
		ps.setTitle("ShowMaze");
		HBox h = new HBox();
		b1 = new Button("oneKey");
		b2 = new Button("onestep");
		b3 = new Button("reset");
		b4 = new Button("setStart");
		h.getChildren().addAll(b1,b2,b3);
		h.setAlignment(Pos.CENTER);
		h.setSpacing(5);
		VBox v = new  VBox();
		v.getChildren().addAll(l1,t1,l2,t2,l3,t3,l4,t4,b4);
		pane = new Pane();
		create();
		show();
		b1.setOnAction(e->{if(isReset)show();oneKey();isReset=false;});
		b2.setOnMouseClicked(e->{oneStep();n++;});
		b3.setOnAction(e->{isReset = true;pane.getChildren().clear();reset();});
		b4.setOnAction(e->setStart());
		BorderPane b = new BorderPane();
		showNodes = new Pane();
		vv = new VBox();
		showNodes.getChildren().add(new ScrollPane(vv));
		vv.setPrefWidth(70);;
		b.setLeft(new ScrollPane(showNodes));
		b.setCenter(pane);
		b.setBottom(h);
		b.setRight(v);
		Scene s = new Scene(b,700,490);
		ps.setScene(s);
		ps.show();
	}
	private void oneStep() {
		if(n==0)
		{
			pp = new Point[m.trace.size()];
			for(int i=m.trace.size()-1;i>=0;i--)
			{
				pp[i] = m.trace.pop();
			}
		}
				r = new Rectangle(10,10,10,10);
				r.setFill(Color.RED);
				pane.getChildren().add(r);
				r.setLayoutX(pp[n].getX()*10);
				r.setLayoutY(pp[n].getY()*10);
				ar.add(r);
				System.out.println("( "+pp[n].getX()+" , "+pp[n].getY()+" )");
				label = new Label("( "+pp[n].getX()+" , "+pp[n].getY()+" )");
				vv.getChildren().add(label);
	}
	public void create()
	{
		maze = m.createMaze(size);
		maze0 = m.createMaze(size);
		// ָ���Թ����ڡ����
				// ���
				enterPoint = maze[1][0];
				// ������ڵ�ֵΪ0����ʾ��ͨ��
				enterPoint.setValue(0);
				// ����
				exitPoint = maze[realSize - 2][realSize - 1];
				// ���ó��ڵ�ֵΪ0����ʾ��ͨ��
				exitPoint.setValue(0);
				for(int i=0;i<maze.length;i++)
				{
					for(int j=0;j<maze[i].length;j++)
					{
						maze0[i][j].setX(maze[i][j].getX());
						maze0[i][j].setY(maze[i][j].getY());
						maze0[i][j].setValue(maze[i][j].getValue());
						maze0[i][j].setVisited(maze[i][j].isVisited());
					}
				}
	}
	
	// ����Թ�
	public void show()
	{
		for(int i = 0; i < realSize; i ++){
			
			for(int j = 0; j < realSize; j ++){
				
				if(maze[i][j].getValue()==1)
				{
					r = new Rectangle(10,10,10,10);
					r.setFill(Color.BLUE);
					pane.getChildren().add(r);
					r.setLayoutX(maze[i][j].getX()*10);
					r.setLayoutY(maze[i][j].getY()*10);
					
				}
				// ����ÿ����ķ�����
				maze[i][j].setVisited(false);
			}
		}
	}
	// ��������
	public void oneKey()
	{
		maze = m.search(maze, enterPoint, exitPoint);
		for(int i = 0; i < realSize; i ++){
			
			for(int j = 0; j < realSize; j ++){
				if(maze[i][j].getValue()==2)
				{
					r = new Rectangle(10,10,10,10);
					r.setFill(Color.RED);
					pane.getChildren().add(r);
					r.setLayoutX(maze[i][j].getX()*10);
					r.setLayoutY(maze[i][j].getY()*10);	
					ar.add(r);
				}
			}
		}
		
	}
	public void preparition()
	{
		// ����ʼλ����Ϊ��ͼ�ϵ� (enterPoint.getX(),enterPoint.getY()) ������λ��ѹ��ջ�� ��
				start_point = now_point = new Point(enterPoint.getX(), enterPoint.getY());
				finalPath = new MazeStack<Point>();
				finalPath.push(now_point);
				// ������Ϸ���յ�λ�á�
				final_point = new Point(exitPoint.getX(),exitPoint.getY());
				animation = new Timeline(
						new KeyFrame(Duration.millis(80),e-> {
							if (!isStarting) {
								isStarting = true;
								// ��(enterPoint.getX(),enterPoint.getY()) �������
								walk(start_point.getX(), start_point.getY());
							}
						}));
				animation.setCycleCount(Timeline.INDEFINITE);
				animation.play();
				allPath = new ArrayList<Point>();
	}
	public void walk(int i, int j) {
		// ��¼��ǰλ�á�
		allPath.add(new Point(i, j));
		
		// ����ǰλ�����յ㡣
		if (i == final_point.getX() && j == final_point.getY()) {
			result();
		} else {
			maze[i][j].setValue(PASS_BY);	// ��ʶ��ǰλ���Ѿ����߹���
			if (maze[i][j + 1].getValue() == 0) {
				// ����ǰλ�õĶ�����Ȼ��������,�����ߡ�
				finalPath.push(new Point(i, j + 1));
				walk(i, j + 1);
				this.allPath.add(new Point(i, j));
			}
			if (maze[i + 1][j].getValue() == 0) {
				// ����ǰλ�õ��ϲ���Ȼ��������,�������ߡ�
				finalPath.push(new Point(i + 1, j));
				walk(i + 1, j);
				this.allPath.add(new Point(i, j));
			}
			if (maze[i][j - 1].getValue() == 0) {
				// ����ǰλ�õ�������Ȼ��������,�������ߡ�
				finalPath.push(new Point(i, j - 1));
				walk(i, j - 1);
				this.allPath.add(new Point(i, j));
			}
			if (maze[i - 1][j].getValue() == 0) {
				// ����ǰλ�õı�����Ȼ��������,�����ߡ�
				finalPath.push(new Point(i - 1, j));
				walk(i - 1, j);
			}
			// ����ǰλ���Աߵ��ĸ������߲�ͨ(���߹�������·),�򷵻���һ����
			if (!finalPath.isEmpty()){
				this.allPath.add(finalPath.pop());
			}
		}
	}
	public void result()
	{
		System.out.println("��ϲ��,�����ҵ�������! ��ȷ�����߲���Ϊ��");
		int k;
		for (k = 0; !finalPath.isEmpty(); k++) {
			Point temp = finalPath.pop();
			System.out.println("( "+temp.getX()+" , "+temp.getY()+" )");
		}
		System.out.println("ϵͳ����������ļ���,�ɹ��ִ����,һ����Ҫ�� "+k+" ����");
	
	}
	public void reset ()
	{
		int n =  0;
		for(int i = 0; i < realSize; i ++){
			
			for(int j = 0; j < realSize; j ++){
				if(maze[i][j].getValue()==2)
				{
					pane.getChildren().remove(ar.get(n));
					n++; 
					maze[i][j].setValue(0);
				}
			}
		}
		for(int i=0;i<n;i++)
		{
			ar.remove(n);
		}
		n = 0;
		for(int i=0;i<maze0.length;i++)
		{
			for(int j=0;j<maze0[i].length;j++)
			{
				maze[i][j].setX(maze0[i][j].getX());
				maze[i][j].setY(maze0[i][j].getY());
				maze[i][j].setValue(maze0[i][j].getValue());
				maze[i][j].setVisited(maze0[i][j].isVisited());
			}
		}
		
		show();
	}
	public void setStart()
	{
		int a = Integer.parseInt(t1.getText());
		int b = Integer.parseInt(t2.getText());
		int c = Integer.parseInt(t3.getText());
		int d = Integer.parseInt(t4.getText());
		enterPoint = maze[a][b];
		// ������ڵ�ֵΪ0����ʾ��ͨ��
		enterPoint.setValue(0);
		// ����
		exitPoint = maze[c][d];
		// ���ó��ڵ�ֵΪ0����ʾ��ͨ��
		exitPoint.setValue(0);
	}
}
