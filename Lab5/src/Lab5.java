import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lab5 {
	public static void main(String[] args) {
		Frame frame = new Frame();
	}
}

class Frame extends JFrame {
	JFrame frame;
	JPanel output;
	List<JButton> btnList;
	GridLayout gridLayout;
	String[] colors = { "Black", "Yellow", "Green", "Red", "Cyan", "Gray" };

	public Frame() {
		/*
		 * nestingLayoutDemo() demonstrates nesting gridLayouts in Containers
		 */
		nestingLayoutDemo();
		/*
		 * clientPropertyDemo()
		 */
		clientPropertyDemo();
	}

	private void clientPropertyDemo() {
		// Create the frame
		frame = new JFrame("makeListener");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create an output panel (to see that the buttons can change some
		// color)
		output = new JPanel();
		/*
		 * Use setSize() if the parent has no layout manager, and
		 * setPreferredSize() if it does
		 * 
		 */
		output.setPreferredSize(new Dimension(200, 200));

		/*
		 * In our case, the frame is going to use a gridLayout
		 * 	consisting of two columns
		 */
		Container container = frame.getContentPane();
		gridLayout = new GridLayout(0, 2);
		container.setLayout(gridLayout);

		// Make some buttons and add them to the container
		for (int i = 0; i < colors.length; i++) {
			String buttonColor = colors[i % colors.length];
			JButton button = new JButton(i + ": " + buttonColor);

			/*
			 * We'll name the buttons with a string from the colors array, but
			 * we'll put an actual property down in the clientProperty map,
			 * namely the Color object corresponding to our color string
			 */
			try {
				Field field = Color.class.getField(buttonColor.toLowerCase());
				Color color = (Color) field.get(null);
				button.putClientProperty("Color", color);

			} catch (Exception e) {
				System.out.println("Something went wrong when reflecting the color");
			}

			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					/*
					 * When someone clicks this button, we cast the source back
					 * to a button and we look for the Color property in the
					 * clientProperty map
					 */

					JButton button = (JButton) e.getSource();
					Color color = (Color) button.getClientProperty("Color");
					// Change the background color of the output panel
					output.setBackground(color);
				}
			});

			container.add(button);
		}
		container.add(output);

		frame.pack();
		frame.setVisible(true);
	}

	private void nestingLayoutDemo() {
		
		/*
		 * There's a bunch of info on the BorderLayout here:
		 * https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
		 * 
		 * The GridLayout has info here:
		 * http://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
		 */
		
		// Create the frame
		frame = new JFrame("makeGridFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up a grid of buttons
		gridLayout = new GridLayout(0, 1);
		JPanel left = new JPanel(gridLayout);
		addButtonsToContainer(left, 0, 10);
		// Add the grid to the left side of the parent
		frame.add(left, BorderLayout.WEST);

		/*
		 * BEGIN NESTING PANELS
		 */
		// Nest a panel in the center of the parent
		JPanel center = new JPanel();

		// Set up a grid of buttons on the left side of the nested panel
		gridLayout = new GridLayout(0, 2);
		JPanel nestedLeft = new JPanel(gridLayout);
		addButtonsToContainer(nestedLeft, 20, 4);
		center.add(nestedLeft, BorderLayout.WEST);

		// Set up a grid of buttons on the right side of the nested panel
		gridLayout = new GridLayout(0, 3);
		JPanel nestedRight = new JPanel(gridLayout);
		addButtonsToContainer(nestedRight, 30, 9);
		center.add(nestedRight, BorderLayout.EAST);

		// Add the center panel (with all the nested panels) to the parent
		frame.add(center, BorderLayout.CENTER);
		/*
		 * END NESTING PANELS
		 */

		// Set up a grid of buttons on the bottom of the parent
		gridLayout = new GridLayout(0, 2);
		JPanel bottom = new JPanel(gridLayout);
		addButtonsToContainer(bottom, 40, 6);
		frame.add(bottom, BorderLayout.SOUTH);

		// Set up a grid of buttons on the right side of the parent
		gridLayout = new GridLayout(0, 2);
		JPanel right = new JPanel(gridLayout);
		addButtonsToContainer(right, 10, 10);
		frame.add(right, BorderLayout.EAST);

		frame.pack();
		frame.setVisible(true);
	}

	private void addButtonsToContainer(Container box, int start, int n) {
		/*
		 * This method makes 'n' buttons, starting at index 'start', and adds
		 * them to a container
		 */
		JButton button;
		btnList = new ArrayList<JButton>();
		for (int i = start; i < (n + start); i++) {
			button = new JButton(Integer.toString(i));
			box.add(button);
			btnList.add(button);
		}
	}
}