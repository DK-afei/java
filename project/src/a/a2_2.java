package a;
import javax.swing.*;
import java.awt.*;
 
/**
 * @ Author     ��heywecome
 * @ Date       ��Created in 11:05 2018/12/17
 * @ Description�����Ʒ�����
 * @ Modified By��
 * @Version: $version$
 */
public class a2_2 extends JFrame{
    // �ȴ�ֱ����һ���߶Σ�Ȼ�����߶ζ�������һ����ǻ���һ���߶Σ����ȷֱ�Ϊԭ�߶ε�k��.
    // ��ͬ���ģ����߶�����Թ̶���ǻ���һ���߶Σ���˷�����ֱ���߶γ���С��ĳ����С��ֵ��
    // ���У���������ɫ�Լ����ȣ��нǣ��������ĳ����Χ������������������н���΢����
 
    public a2_2() {
        super("������");
        setBounds(200, 200, 1000, 800);
        // ��ʾ���ɵĴ����С�ɸı�
        setResizable(true);
        // �����������ڵ�Ӧ�ó����ڴ��ڱ��رյ�ʱ����˳�JVM
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