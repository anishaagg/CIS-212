// Anisha Aggarwal	CIS 212	Assignment 4
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

// get the size, shape, color
class ShapePoint {
	public static final int SQUARE_TYPE = 1;
	private final Point _points;
	private final int shape_type;
	private Color current_color;
	private int sq_size = 10;
	
	public ShapePoint(Point p, int shapeType, Color c, int size) {
		_points = p;
		shape_type = shapeType;
		current_color = c;
		sq_size = size;
	}
	
	public Point getPoint() {
		return _points;
	}
	public int getShape() {
		return shape_type;
	}
	public Color getColor(){
		return current_color;
	}
	
	public void paint(Graphics g) {
		if (shape_type == SQUARE_TYPE) {
			g.setColor(current_color);
			g.fillRect(_points.x, _points.y, sq_size, sq_size);
		}
	}
}

// function will allow user to either click or drag
// the mouse to paint in different sizes, colors
// create an arrayList of points
class PaintPanel extends JPanel {
	private static ArrayList<ShapePoint> _points;
	private Color current_color;
	private int current_shape;
	private static int current_size = 10;
	
	public PaintPanel() {
		_points = new ArrayList<>();
		current_shape = ShapePoint.SQUARE_TYPE;
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				_points.add(new ShapePoint(event.getPoint(), current_shape, current_color, current_size));
				repaint();
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				_points.add(new ShapePoint(e.getPoint(), current_shape, current_color, current_size));
				repaint();
			}
		});
		
	}
	
	public void setCurrentColor (Color currentColor) {
		current_color = currentColor;
	}
	
	public static void setSize(int r){
		current_size = r;
	};
	
	public static void clearScreen() {
		_points.removeAll(_points);
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		for (ShapePoint p: _points) {
			p.paint(g);
		}
	}
	
}

// create buttons and a canvas to be painted on
// be able to change size, color and clear the screen
// to start over
class MainFrame extends JFrame{
	private final PaintPanel paint_panel;
	private final JPanel topPanel;
	private final JPanel bottomPanel;
	
	// create buttons with text on them
	JButton b_button = new JButton("Blue");
	JButton g_button = new JButton("Green");
	JButton r_button = new JButton("Red");
	JButton yel_button = new JButton("Yellow");
	JButton small_button = new JButton("Small");
	JButton med_button = new JButton("Medium");
	JButton large_button = new JButton("Large");
	JButton clear_button = new JButton("Clear");
	
	public MainFrame() {
		super("LET'S PAINT!!");
		setLayout(new BorderLayout());
		paint_panel = new PaintPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		add(paint_panel, BorderLayout.CENTER);
		
		// buttons for color
		topPanel.add(b_button); topPanel.add(g_button);
		topPanel.add(r_button); topPanel.add(yel_button);
		topPanel.setLayout(new GridLayout());
		// buttons for size and clear button
		bottomPanel.add(small_button); bottomPanel.add(med_button);
		bottomPanel.add(large_button); bottomPanel.add(clear_button);
		bottomPanel.setLayout(new GridLayout());
		//display
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
		
		ButtonHandler handler = new ButtonHandler();
		b_button.addActionListener(handler);
		g_button.addActionListener(handler);
		r_button.addActionListener(handler);
		yel_button.addActionListener(handler);
		small_button.addActionListener(handler);
		med_button.addActionListener(handler);
		large_button.addActionListener(handler);
		clear_button.addActionListener(handler);
	}
	// change colors, size and clear using the handler function
	private class ButtonHandler implements ActionListener {	
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == b_button) {
				paint_panel.setCurrentColor(Color.BLUE);
			} else if (e.getSource() == g_button) {
				paint_panel.setCurrentColor(Color.GREEN);
			} else if (e.getSource() == r_button) {
				paint_panel.setCurrentColor(Color.RED);
			} else if (e.getSource() == yel_button) {
				paint_panel.setCurrentColor(Color.YELLOW);
			} else if (e.getSource() == small_button) {
				paint_panel.setSize(5);
			} else if (e.getSource() == med_button) {
				paint_panel.setSize(10);
			} else if (e.getSource() == large_button) {
				paint_panel.setSize(15);
			} else if (e.getSource() == clear_button){
				paint_panel.clearScreen();
				repaint();
			}
		}

	}
}

public class main_4 {
	public static void main(String[] args) {
		// build user interface with a mix of standard/custom widgets
		// interactive paint application
		
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setVisible(true);
	}

}
