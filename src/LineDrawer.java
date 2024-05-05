import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LineDrawer extends JFrame {
    private ArrayList<Line> lines = new ArrayList<>();
    private Line currentLine;

    public LineDrawer() {
        setTitle("Dibujar lineas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                currentLine = new Line();
                currentLine.setStart(e.getPoint());
            }
            public void mouseReleased(MouseEvent e) {
                currentLine.setEnd(e.getPoint());
                lines.add(currentLine);
                repaint();
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currentLine.setEnd(e.getPoint());
                repaint();
            }
        });
        getContentPane().add(panel);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Line line : lines) {
            g.drawLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }
        if (currentLine != null) {
            g.drawLine(currentLine.getStartX(), currentLine.getStartY(), currentLine.getEndX(), currentLine.getEndY());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LineDrawer drawer = new LineDrawer();
            drawer.setVisible(true);
        });
    }
    
    private class Line {
        private Point start;
        private Point end;

        public void setStart(Point start) {
            this.start = start;
        }

        public void setEnd(Point end) {
            this.end = end;
        }

        public int getStartX() {
            return (int) start.getX();
        }

        public int getStartY() {
            return (int) start.getY();
        }

        public int getEndX() {
            return (int) end.getX();
        }

        public int getEndY() {
            return (int) end.getY();
        }
    }
}