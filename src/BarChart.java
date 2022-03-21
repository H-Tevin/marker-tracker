import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.awt.Color.blue;

public class BarChart extends JPanel {

    public static final int TOP_BUFFER = 30; // where additional text is drawn
    public static final int AXIS_OFFSET = 20;

    private final ArrayList<Integer> list;
    private final Map<Integer, Integer> counts = new HashMap<>();

    private int chartwidth, chartheight, chartX, chartY;
    private Color color;

    private final String xLabel;
    private final String yLabel;
    private float height;

    public BarChart(ArrayList<Integer> list, String xl, String yl, Color color, float heightMultiplier) {
        super();
        this.list = list;
        this.color = color;
        System.out.println(heightMultiplier);
        this.height = heightMultiplier * 150;

        xLabel = xl;
        yLabel = yl;

    }

    public void paintComponent(Graphics g) {

        computeSize();

        Graphics2D g2 = (Graphics2D) g;
        drawBars(g2);
        drawAxes(g2);
    }

    private void computeSize() {

        int width = 20;
        int height = 200;

        // chart area size
        chartwidth = width - AXIS_OFFSET;
        chartheight = height - AXIS_OFFSET - TOP_BUFFER;

        // Chart origin coords
        chartX = AXIS_OFFSET;
        chartY = height - AXIS_OFFSET;

    }

    public void drawBars(Graphics2D g2) {

        Color original = g2.getColor();

        double numBars = 1;
        int barWidth = (int) (chartwidth/numBars);

        int xLeft, yTopLeft;

            xLeft = AXIS_OFFSET + 10;
            yTopLeft = 30;
            barWidth = 55;
            Rectangle rec = new Rectangle(xLeft, yTopLeft, barWidth, (int) this.height);

        g2.setColor(color);
            g2.fill(rec);

        g2.setColor(original);
    }

    private void drawAxes(Graphics2D g2) {

        int rightX = chartX + chartwidth;
        int topY = chartY - chartheight;

        g2.drawLine(chartX, chartY, rightX, chartY);

        g2.drawLine(chartX, chartY, chartX, topY);

        g2.drawString(xLabel, chartX + chartwidth/2, chartY + AXIS_OFFSET/2 +3) ;

        // draw vertical string

        Font original = g2.getFont();

        Font font = new Font(null, original.getStyle(), original.getSize());
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(-90), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g2.setFont(rotatedFont);
        g2.drawString(yLabel,AXIS_OFFSET/2+3, chartY - chartheight/2);
        g2.setFont(original);
    }
}

