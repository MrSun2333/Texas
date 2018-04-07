import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Compiler extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int IDENTIFIER = 0;// 标识符
	private static final int CONSTANT = 1;// 常数
	private static final int KEYWORD = 2;// 关键字
	private static final int DELIMETER = 3;// 界符
//	private static final int ANNOTATION = 2;// 注释
//	private static final int CHAR = 3;// 文字常数
	private static final int OPERATOR = 4;// 运算符
	private static final int ERROR = 5;

	private JButton btnOpen;
	private JButton btnSave;

	private JButton btnAnother;
	private JButton btnExit;
	private JButton btnHand;
	private JButton btnAuto;

	private static JIMSendTextPane sourcePane;
	private JTextPane workPane;
	private JTextPane errorPane;
	private JTextPane emblemPane;
	
	// JAVA中的关键字
	private static String keyword[] = { "short", "int", "char", "long", "float", "double", "if", "else", "break",
			"continue", "switch", "case", "default", "while", "void", "static", "public", "private", "protected",
			"return", "null", "final", "for", "extends", "class", "implements", "interface", "enum", "main" };
	// JAVA中的界符
	private static char[] delimeter = {'/','/','*','[',']','{','}','(',')','&','|','!'};
	
	// JAVA中的运算符
	private static char[] operator = {'+','-','*','/','<','=','>','%'};
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compiler frame = new Compiler();
					frame.setTitle("Compiler");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Compiler() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1091, 707);

		JMenuBar jMenuBar = new JMenuBar();

		JMenu menu = new JMenu("文件");
		menu.setBackground(Color.WHITE);
		menu.setFont(new Font("宋体", Font.PLAIN, 12));
		menu.setForeground(Color.DARK_GRAY);
		menu.setHorizontalAlignment(SwingConstants.LEFT);
		JMenu menu1 = new JMenu("词法分析");
		menu1.setBackground(Color.WHITE);
		menu1.setFont(new Font("宋体", Font.PLAIN, 12));
		menu1.setForeground(Color.DARK_GRAY);
		JMenu menu2 = new JMenu("语法分析");
		menu2.setFont(new Font("宋体", Font.PLAIN, 12));
		menu2.setForeground(Color.DARK_GRAY);
		menu2.setHorizontalAlignment(SwingConstants.LEFT);
		JMenu menu3 = new JMenu("中间代码生成");
		menu3.setFont(new Font("宋体", Font.PLAIN, 12));
		menu3.setForeground(Color.DARK_GRAY);
		menu3.setHorizontalAlignment(SwingConstants.LEFT);
		JMenu menu4 = new JMenu("目标代码生成");
		menu4.setFont(new Font("宋体", Font.PLAIN, 12));
		menu4.setForeground(Color.DARK_GRAY);
		menu4.setHorizontalAlignment(SwingConstants.LEFT);
		JMenu menu5 = new JMenu("帮助");
		menu5.setFont(new Font("宋体", Font.PLAIN, 12));
		menu5.setForeground(Color.DARK_GRAY);
		menu5.setHorizontalAlignment(SwingConstants.LEFT);

		setJMenuBar(jMenuBar);

		btnHand = new JButton("手工生成过程演示 (M)");
		btnHand.setBackground(Color.WHITE);
		btnHand.setForeground(Color.DARK_GRAY);
		btnHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		menu1.add(btnHand);

		btnAuto = new JButton("自动生成过程演示 (A)");
		btnAuto.setBackground(Color.WHITE);
		btnAuto.setForeground(Color.DARK_GRAY);
		btnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count = 0;// 记录错误的个数
				char c = ' ';
				String str = sourcePane.getText();
//				System.out.println(str);
				workPane.setText(str);
				sort(c);
				scanner();
				errorPane.setText("共有 "+ count +" 个错误");

			}
		});
		menu1.add(btnAuto);
		jMenuBar.add(menu);
		jMenuBar.add(menu1);
		jMenuBar.add(menu2);
		jMenuBar.add(menu3);
		jMenuBar.add(menu4);
		jMenuBar.add(menu5);

		btnOpen = new JButton("打开 (O)");
		btnOpen.setForeground(Color.DARK_GRAY);
		btnOpen.setBackground(Color.WHITE);
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jf = new JFileChooser("E:\\compiler");
				jf.setDialogTitle("选择一个源代码文件打开");
				jf.showDialog(null, null);
				File file = jf.getSelectedFile();
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String str = new String("");

					while ((str = reader.readLine()) != null) {
						sourcePane.setText(str);
						sourcePane.replaceSelection(str);
					}

					reader.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		menu.add(btnOpen);
		;
		btnSave = new JButton("保存 (S)");
		btnSave.setForeground(Color.DARK_GRAY);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Object[] options = { "确定", "取消" };
				int response = JOptionPane.showOptionDialog(new JFrame(), "确认保存？", "保存", JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				String str = sourcePane.getText();
				try {
					if (response == 0)
						writeToFile(str);
					else if (response == 1) {
						// do nothing
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnSave.setBackground(Color.WHITE);
		menu.add(btnSave);

		btnAnother = new JButton("另存 (A)");
		btnAnother.setForeground(Color.DARK_GRAY);
		btnAnother.setBackground(Color.WHITE);
		btnAnother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser("E:\\compiler"); // 创建文件选择对话框
				int result = chooser.showSaveDialog(new JFrame());
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fout = new FileWriter(chooser.getSelectedFile()); // 向磁盘中写文件
						fout.write(sourcePane.getText() + "\r\n");
						fout.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		menu.add(btnAnother);

		btnExit = new JButton("退出 (E)");
		btnExit.setForeground(Color.DARK_GRAY);
		btnExit.setBackground(Color.WHITE);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		menu.add(btnExit);

		JLabel lblSourceCode = new JLabel("Source");
		lblSourceCode.setBounds(11, 8, 71, 28);

		sourcePane = new JIMSendTextPane();
		sourcePane.setBounds(11, 46, 492, 248);
		sourcePane.setFont(new Font("宋体", Font.PLAIN, 15));

		workPane = new JIMSendTextPane();
		workPane.setBounds(525, 46, 540, 248);
		workPane.setFont(new Font("宋体", Font.PLAIN, 15));
		workPane.setEditable(false);

		JLabel lblWorkList = new JLabel("Work List");
		lblWorkList.setBounds(525, 8, 71, 28);

		JLabel lblError = new JLabel("Error");
		lblError.setBounds(10, 316, 71, 28);

		errorPane = new JIMSendTextPane();
		errorPane.setBounds(10, 354, 492, 263);
		errorPane.setFont(new Font("宋体", Font.PLAIN, 15));
		errorPane.setEditable(false);

		JLabel lblEmblem = new JLabel("Emblems");
		lblEmblem.setBounds(525, 316, 71, 28);

		emblemPane = new JIMSendTextPane();
		emblemPane.setBounds(525, 354, 540, 263);
		emblemPane.setFont(new Font("宋体", Font.PLAIN, 15));
		emblemPane.setEditable(false);
		getContentPane().setLayout(null);
		getContentPane().add(lblSourceCode);
		getContentPane().add(lblWorkList);
		getContentPane().add(sourcePane);
		getContentPane().add(workPane);
		getContentPane().add(lblError);
		getContentPane().add(lblEmblem);
		getContentPane().add(errorPane);
		getContentPane().add(emblemPane);
	}

	// 将面板内容写入文件中
	public static void writeToFile(String str) throws IOException {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		File file1 = new File("E:\\compiler", "back");
		if (file1.mkdirs()) {
			System.out.println("文件已存在，创建失败 ！");
		} else {
			System.out.println("文件不存在，创建成功 ！ ");
		}
//		System.out.println(str + "null");

		FileWriter fout = new FileWriter("E:\\compiler\\" + year + month + day + hour + minute + second + ".txt"); // 向磁盘中写文件
		fout.write(sourcePane.getText() + "\r\n");
		fout.close();
	}

	// 词法分析的主函数sacnner
	private static void scanner() {
//		int count = 0;// 行计数器
		if (true) {
//			count++;
//			System.out.println(count);
		}
	}

	// 分类函数sort()
	private static int sort(char c) {
		if (isLetter(c))// 标识符
			return IDENTIFIER;
		else if (isDigit(c))// 常数
			return CONSTANT;
//		else if (isKeyword(c))// 关键字
//			return KEYWORD;
		else if (isOperator(c))// 操作符
			return OPERATOR;
		else if (isDelimeter(c))// 界符
			return DELIMETER;
		else
			return ERROR;// 错误
	}
	
//	// 是否关键字
//	private static boolean isKeyword(char c) {
//		for(String str : keyword) {
//			if(str.equals(str))
//				return true;
//		}
//		return false;
//	}

	// 是否操作符
	private static boolean isOperator(char c) {
		for(char ch : operator) {
			if(ch == c)
				return true;
		}
		return false;
	}

	// 是否字母
	public static boolean isLetter(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
			return true;
		return false;
	}

	// 是否数字
	public static boolean isDigit(char c) {
		if (c >= 0 && c <= 9) {
			return true;
		}
		return false;
	}

	// 是否界符
	public static boolean isDelimeter(char c) {
		for(char ch : delimeter) {
			if(c == ch)
				return true;
		}
		return false;
	}
}
