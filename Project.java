import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project
{
	static class Runner
	{
		private String name;
		private int time;
		private int category;
		public Runner next;
		
		public Runner(String _name, int _time, int _category)
		{
			this.name = _name;
			this.time = _time;
			this.category = _category;
			this.next = null;
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public int getTime()
		{
			return this.time;
		}
		
		public int getCategory()
		{
			return this.category;
		}
	}
	
	static Runner head = null;
	static Runner rear = null;
	
	public static void add_runner(String _name, int _time, int _category)
	{
		Runner ptr = new Runner(_name,_time,_category);
		if (head == null)
			head = ptr;
		else
			rear.next = ptr;
		rear = ptr;
	}
	
	public static void display()
	{
		Runner ptr = head;
		while(ptr != null)
		{
			System.out.println(ptr.getName());
			ptr = ptr.next;
		}
	}
	
	public static void find_Winner(int cat, int[] winners)
	{
		int w1 = Integer.MAX_VALUE;
		int w2 = Integer.MAX_VALUE;
		int  win1 = -1;
		int win2 = -1;
		int count = 1;
		Runner ptr = head;
		while(ptr != null)
		{
			if (ptr.getCategory() == cat)
			{
				if (ptr.getTime() < w1)
				{
					w2 = w1;
					win2 = win1;
					w1 = ptr.getTime();
					win1 = count;
				}
				else if (ptr.getTime() < w2)
				{
					w2 = ptr.getTime();
					win2 = count;
				}
			}
			ptr = ptr.next;
			count+=1;
		}
		winners[0] = win1;
		winners[1] = win2;
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Assignment_3");
		JPanel p_main = new JPanel();
		p_main.setLayout(new BoxLayout(p_main,BoxLayout.Y_AXIS));
		
		// Name Field
		JPanel p_name = new JPanel();
		p_name.setLayout(new FlowLayout(FlowLayout.CENTER));
		p_main.add(p_name);
		JLabel l_name = new JLabel("Name");
		JTextField tf_name = new JTextField();
		tf_name.setPreferredSize(new Dimension(150,35));
		p_name.add(l_name);
		p_name.add(tf_name);
		
		//Time Field
		JPanel p_time = new JPanel();
		p_time.setLayout(new FlowLayout(FlowLayout.CENTER));
		p_main.add(p_time);
		JLabel l_time = new JLabel("Time");
		JTextField tf_time = new JTextField();
		tf_time.setPreferredSize(new Dimension(150,35));
		p_time.add(l_time);
		p_time.add(tf_time);
		
		//Category Field
		JPanel p_category = new JPanel();
		p_category.setLayout(new BoxLayout(p_category,BoxLayout.Y_AXIS));
		p_main.add(p_category);
		JLabel l_category = new JLabel ("Category :");
		p_category.add(l_category);
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rb_5 = new JRadioButton("Great Delhi Run (5km)");
		JRadioButton rb_10 = new JRadioButton("Open 10K Run (10km)");
		JRadioButton rb_20 = new JRadioButton("Half Marathon (20km)");
		bg.add(rb_5);
		bg.add(rb_10);
		bg.add(rb_20);
		p_category.add(rb_5);
		p_category.add(rb_10);
		p_category.add(rb_20);
		rb_20.setSelected(true);               //Set Default Category to Half Marathon (20km)
		
		//Great Delhi Run Winners Field
		JPanel p_m5p1 = new JPanel();
		p_m5p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p_main.add(p_m5p1);
		JLabel l_m5 = new JLabel("Great Delhi Run");
		JTextField tf_m51 = new JTextField();
		JTextField tf_m52 = new JTextField();
		tf_m51.setPreferredSize(new Dimension(150,35));
		tf_m52.setPreferredSize(new Dimension(150,35));
		p_m5p1.add(l_m5);
		p_m5p1.add(tf_m51);	
		p_m5p1.add(tf_m52);
		
		//Open 10K Run Winners Field
		JPanel p_m10p1 = new JPanel();
		p_m10p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p_main.add(p_m10p1);
		JLabel l_m10 = new JLabel("Open 10K Run");
		JTextField tf_m101 = new JTextField();
		JTextField tf_m102 = new JTextField();
		tf_m101.setPreferredSize(new Dimension(150,35));
		tf_m102.setPreferredSize(new Dimension(150,35));
		p_m10p1.add(l_m10);
		p_m10p1.add(tf_m101);	
		p_m10p1.add(tf_m102);
		
		//Half Marathon Winners Field
		JPanel p_m20p1 = new JPanel();
		p_m20p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p_main.add(p_m20p1);
		JLabel l_m20 = new JLabel("Half Marathon Run");
		JTextField tf_m201 = new JTextField();
		JTextField tf_m202 = new JTextField();
		tf_m201.setPreferredSize(new Dimension(150,35));
		tf_m202.setPreferredSize(new Dimension(150,35));
		p_m20p1.add(l_m20);
		p_m20p1.add(tf_m201);	
		p_m20p1.add(tf_m202);
		
		//Button Field
				JPanel p_buttons = new JPanel();
				p_buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
				p_main.add(p_buttons);
				JButton b_next = new JButton("Next");
				JButton b_winner = new JButton("Winner");
				JButton b_cancel = new JButton("Cancel");
				b_next.addActionListener( new ActionListener()
				{
					@Override
					public void actionPerformed (ActionEvent e)
					{
						String _name = tf_name.getText();
						int _time =	Integer.parseInt(tf_time.getText());
						boolean _5 = rb_5.isSelected();
						boolean _10 = rb_10.isSelected();
						int _bg = 20;
						if (_5 == true)
							_bg = 5;
						else if (_10 == true)
							_bg = 10;
						else
							_bg = 20;
						add_runner(_name,_time,_bg);
						tf_name.setText("");			//Set name and time field to blank and set default button as Half Marathon (20km) - rb_20
						tf_time.setText("");
						rb_20.setSelected(true);
						
					}
				});
				b_winner.addActionListener( new ActionListener()
				{
					@Override
					public void actionPerformed (ActionEvent e)
					{
						/*													//Comment out when winner is also used to store last entered data
						String _name = tf_name.getText();
						int _time =	Integer.parseInt(tf_time.getText());
						boolean _5 = rb_5.isSelected();
						boolean _10 = rb_10.isSelected();
						int _bg = 20;
						if (_5 == true)
							_bg = 5;
						else if (_10 == true)
							_bg = 10;
						else
							_bg = 20;
						add_runner(_name,_time,_bg);
						tf_name.setText("");			//Set name and time field to blank and set default button as Half Marathon (20km) - rb_20
						tf_time.setText("");
						rb_20.setSelected(true);
						*/
						int Winner_5[] = new int[2];
						find_Winner(5,Winner_5);
						int Winner_10[] = new int[2];
						find_Winner(10,Winner_10);
						int Winner_20[] = new int[2];
						find_Winner(20,Winner_20);
						String w51 = new String();
						String w52 = new String();
						String w101 = new String();
						String w102 = new String();
						String w201 = new String();
						String w202 = new String();
						Runner ptr = head;
						int count = 1;
						while(ptr != null)
						{
							if (count == Winner_5[0])
								w51 = ptr.getName();
							if (count == Winner_5[1])
								w52 = ptr.getName();
							if (count == Winner_10[0])
								w101 = ptr.getName();
							if (count == Winner_10[1])
								w102 = ptr.getName();
							if (count == Winner_20[0])
								w201 = ptr.getName();
							if (count == Winner_20[1])
								w202 = ptr.getName();
							ptr = ptr.next;
							count+=1;
						}
						if (Winner_5[0] == -1)
							w51 = "";
						if (Winner_5[1] == -1)
							w52 = "";
						if (Winner_10[0] == -1)
							w101 = "";
						if (Winner_10[1] == -1)
							w102 = "";
						if (Winner_20[0] == -1)
							w201 = "";
						if (Winner_20[1] == -1)
							w202 = "";
						tf_m51.setText(w51);
						tf_m52.setText(w52);
						tf_m101.setText(w101);
						tf_m102.setText(w102);
						tf_m201.setText(w201);
						tf_m202.setText(w202);						
					}
				});
				b_cancel.addActionListener( new ActionListener()
				{
					@Override
					public void actionPerformed (ActionEvent e)
					{
						System.exit(0);
					}
				});
				p_buttons.add(b_next);
				p_buttons.add(b_winner);
				p_buttons.add(b_cancel);
				
				
		
		frame.add(p_main);
		frame.setSize(600,600);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
