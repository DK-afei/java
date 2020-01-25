package a;
import javax.swing.*;
import java.awt.*;
 
/**
 * @ Author     ：heywecome
 * @ Date       ：Created in 11:05 2018/12/17
 * @ Description：绘制分型树
 * @ Modified By：
 * @Version: $version$
 */
public class a2_2 extends JFrame{
    // 先垂直绘制一根线段，然后在线段顶端向右一定倾角绘制一根线段，长度分别为原线段的k倍.
    // 再同样的，在线段左侧以固定倾角绘制一根线段，如此反复，直至线段长度小于某个较小的值。
    // 其中，线条的颜色以及长度，夹角（例如产生某个范围的随机数）都可以自行进行微调。
 
    public a2_2() {
        super("分形树");
        setBounds(200, 200, 1000, 800);
        // 表示生成的窗体大小可改变
        setResizable(true);
        // 结束窗口所在的应用程序。在窗口被关闭的时候会退出JVM
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private void drawTree(Graphics g, int x1, int y1, double angle, double length) {
        if (length < 0.5) return;
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * length*10);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * length*10);
        g.setColor( length < 1.2 ? Color.pink : Color.GRAY );
 
        g.drawLine(x1, y1, x2, y2);
 
        drawTree(g, x2, y2, angle+ 33.5, length/1.3);
        drawTree(g, x2, y2, angle- 33.5, length/1.3);
    }
 
 
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        drawTree(g, 500, 700, -90, 14);
    }
 
    public static void main(String[] args) {
        new a2_2().setVisible(true);
    }
}