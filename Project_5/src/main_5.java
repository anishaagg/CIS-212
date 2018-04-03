import java.io.IOException;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

// class that reads in phone # and name are stored
class PhoneList {
	private String phone_num;
	private String names;
	
	//initialize PhoneList
	public PhoneList (String number, String name) {
		this.phone_num = number;
		this.names = name;
	}
	public PhoneList(PhoneList pl){
		this.phone_num = pl.getNumber();
		this.names = pl.getName();
	}
	//get phone number
	public String getNumber(){
		return phone_num;
	}
	//get name
	public String getName(){
		return names;
	}
	// set phone list
	public void setNumber(String number) {
		phone_num = number;
	}
	public void setName(String name) {
		names = name;
	}
	public void setPhoneList(PhoneList setP) {
		names = setP.getName();
		phone_num = setP.getNumber();
	}
	@Override
	public String toString() {
		return String.format("%s %s", phone_num, names);
	}
}

//implement selection sorting method (alphabetically by name)
class SelectionSort implements Runnable{
	private ArrayList<PhoneList> phone_list;
	private JLabel s_label;
	// constructor implementation
	public SelectionSort (ArrayList<PhoneList> plist, JLabel slabel) {
		phone_list = plist;
		s_label = slabel;
	}
	// implement run method created by Runnable
	// calculate and print run time
	public void run() {
		double start_time = System.nanoTime();
		s_label.setText("Please wait...");
		ArrayList <PhoneList> sorted = main_5.selectionSort(phone_list);
		if (main_5.isSorted(sorted) == true) {
			double end_time = System.nanoTime();
			double elapsed_time = (end_time - start_time)/1000000;
			s_label.setText("Elapsed Time: " + elapsed_time + " milliseconds");
/*			// debug print statement
			System.out.println("After threaded selection sort:");
			for (PhoneList p : sorted) {
				System.out.println(p);
			}*/
		} else {
			s_label.setText("Error... Selection Sort Failed");
		}
		return;
	}
}

//implement merge sorting method (alphabetically by name)
class MergeSort implements Runnable{
	private ArrayList<PhoneList> phone_list;
	private JLabel m_label;
	// constructor implementation
	public MergeSort (ArrayList<PhoneList> plist, JLabel mlabel) {
		phone_list = plist;
		m_label = mlabel;
	}
	// implement run method created by Runnable
	// calculate and print run time
	public void run() {
		long start_time = System.nanoTime();
		m_label.setText("Please wait...");
		ArrayList <PhoneList> sorted = main_5.mergeSort(phone_list);
		if (main_5.isSorted(sorted) == true) {
			long end_time = System.nanoTime();
			long elapsed_time = (end_time - start_time)/1000000;
			m_label.setText("Elapsed Time: " + elapsed_time + "milliseconds");
/*			// debug print statement
			System.out.println("After threaded merge sort:");
			for (PhoneList p : sorted) {
				System.out.println(p);
			}*/
		} else {
			m_label.setText("Error... Merge Sort Failed");
		}
		return;
	}
}

// implement GUI with 2 sorting buttons
class MainFrame extends JFrame {
	private final JPanel top_panel;
	private final JPanel bottom_panel;
	//private ArrayList <PhoneList> phone_list;
	private JButton sel_btn = new JButton("Selection Sort");
	private JButton mer_btn = new JButton("Merge Sort");
	private JLabel s_label = new JLabel("");
	private JLabel m_label = new JLabel("");
	private static final boolean threadedS = true;
	private static final boolean threadedM = true;
	
	public MainFrame() {
		super("LET'S SORT!!");
		
		//add buttons to panels
		top_panel = new JPanel(); bottom_panel = new JPanel();
		top_panel.add(sel_btn);
		top_panel.add(s_label);
		bottom_panel.add(mer_btn);
		bottom_panel.add(m_label);
		
		//create layout
		top_panel.setLayout(new GridLayout(1,2));
		add(top_panel, BorderLayout.NORTH);
		bottom_panel.setLayout(new GridLayout(1,2));
		add(bottom_panel, BorderLayout.SOUTH);
		
		//add actionlisteners to buttons
		ButtonHandler handler = new ButtonHandler();
		sel_btn.addActionListener(handler);
		mer_btn.addActionListener(handler);
	}
	
	private class ButtonHandler implements ActionListener {	
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == sel_btn) {
				// extra credit thread for selection sort
				if (threadedS) {
					SelectionSort s_sort = new SelectionSort(main_5.readFile(), s_label);
					Thread t = new Thread(s_sort);
					t.start();
				} else {
					double start_time = System.nanoTime();
					ArrayList <PhoneList> sorted = main_5.selectionSort(main_5.readFile());
					// debug print statement
					/*System.out.println("After selection sort:");
					for (PhoneList p : sorted) {
						System.out.println(p);
					}*/
					
					if (main_5.isSorted(sorted) == true) {
						double end_time = System.nanoTime();
						double elapsed_time = (end_time - start_time)/1000000;
						s_label.setText("Elapsed Time: " + elapsed_time + " milliseconds");
					} else {
						s_label.setText("Error... Selection Sort Failed");
					}
				}
			} if (e.getSource() == mer_btn) {
				// extra credit thread for selection sort
				if (threadedM) {
					MergeSort m_sort = new MergeSort(main_5.readFile(), m_label);
					Thread t = new Thread(m_sort);
					t.start();
				} else {
					double start_time = System.nanoTime();
					ArrayList <PhoneList> sorted = main_5.mergeSort(main_5.readFile());
					// debug print statement
/*					System.out.println("After merge sort:");
					for (PhoneList pl : sorted) {
						System.out.println(pl);
					}*/
					
					if (main_5.isSorted(sorted) == true) {
						double end_time = System.nanoTime();
						double elapsed_time = (end_time - start_time)/1000000;
						m_label.setText("Elapsed Time: " + elapsed_time + " milliseconds");
					} else {
						m_label.setText("Error... Merge Sort Failed");
					}
				}
			}
		}
	}
}

public class main_5 {
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,200);
		frame.setVisible(true);
	}
	
	// opens file, reads record from file, and closes the file
	public static ArrayList<PhoneList> readFile() {
		Scanner input = null;
		//open file
		try {
			input = new Scanner(Paths.get("phonebook.txt"));
		} catch (IOException ioException) {
			System.err.println("Error opening file. Terminating.");
			System.exit(1);
		}
		
		//read file
		ArrayList<PhoneList> phoneList = new ArrayList<PhoneList>();
		try {
			//System.out.println("Before Sorting:");
			while(input.hasNextLine()) {
				//System.out.println(input.nextInt() + input.next() + input.next());
				PhoneList p = new PhoneList("", "");
				String s = input.nextLine();
				String [] line_array = s.split(" ",2);
				p.setNumber(line_array[0]);
				p.setName(line_array[1]);
				phoneList.add(p); 
				/* debug print statement
				System.out.println(p);*/
			}
		} catch (NoSuchElementException elementException) {
			System.err.println("File improperly formed. Terminating...");
		} catch (IllegalStateException stateException) {
			System.err.println("File improperly formed. Terminating.");
		}
		
		//close file
		input.close();
		
		return phoneList;
	}
	
	// selection sort method
	public static ArrayList<PhoneList> selectionSort(ArrayList<PhoneList> in_array) {
		ArrayList<PhoneList> out_array = new ArrayList<PhoneList>();
		for (PhoneList entry : in_array) {
			out_array.add(new PhoneList(entry));
		}
		int out_size = out_array.size();
		for (int i=0; i < out_size - 1; i++) {
			int min_index = i;
			PhoneList min_value = out_array.get(i);
			for (int j= (i + 1); j < out_size; j++) {
				if (out_array.get(j).getName().compareTo(min_value.getName()) < 0) {
					min_index = j;
					min_value = out_array.get(j);
				}
			}
			if (i != min_index) {
				PhoneList temp = new PhoneList(out_array.get(i));
				out_array.get(i).setPhoneList(out_array.get(min_index));
				out_array.get(min_index).setPhoneList(temp);
			}
		}
		return out_array;
	}
	// merge sort method
	public static ArrayList<PhoneList> mergeSort(ArrayList<PhoneList> in_array) {
		int in_size = in_array.size();
		// recursion set up ending case here 
		if (in_size==1) {
			return in_array;
		}
		// cut in_array into 2 arrays
		int mid_point = in_size/2;
		ArrayList<PhoneList> first_array = new ArrayList<PhoneList>();
		ArrayList<PhoneList> second_array = new ArrayList<PhoneList>();
		for (int i = 0; i < mid_point; i++) {
			first_array.add(new PhoneList(in_array.get(i)));
		}
		for (int i = mid_point; i < in_size; i++) {
			second_array.add(new PhoneList(in_array.get(i)));
		}
		// sort the cut arrays 
		first_array = mergeSort(first_array);
		second_array = mergeSort(second_array);
		//combine 2 arrays into a new array
		ArrayList<PhoneList> out_array = new ArrayList<PhoneList>();
		int size1 = first_array.size();
		int size2 = second_array.size();
		int i = 0; int j = 0;
		while (i != size1 || j != size2) {
			//the following 2 if statements are to address remaining end elements of cut arrays
			// when one of them is completed 
			if (i==size1) {
				out_array.add(second_array.get(j));
				j++;
				continue;
			}
			if (j==size2) {
				out_array.add(first_array.get(i));
				i++;
				continue;
			}
			// here we compare the individual elements of first and second cut arrays to merge into a bigger array 
			if (first_array.get(i).getName().compareTo(second_array.get(j).getName()) < 0) {
				out_array.add(first_array.get(i));
				i++;
			} else {
				out_array.add(second_array.get(j));
				j++;
			}
		}
		return out_array;
	}
	// check if the the arraylist is sorted or not
	public static boolean isSorted(ArrayList<PhoneList> sorted) {
		int array_size = sorted.size();
		if (array_size==0 || array_size == 1) {
			return true;
		}
		for (int i = 1; i < array_size; i++){
			if (sorted.get(i-1).getName().compareTo(sorted.get(i).getName()) <= 0) {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
}
