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

	private static final int IDENTIFIER = 0;// ��ʶ��
	private static final int CONSTANT = 1;// ����
	private static final int KEYWORD = 2;// �ؼ���
	private static final int DELIMETER = 3;// ���
//	private static final int ANNOTATION = 2;// ע��
//	private static final int CHAR = 3;// ���ֳ���
	private static final int OPERATOR = 4;// �����
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
	
	// JAVA�еĹؼ���
	private static String keyword[] = { "short", "int", "char", "long", "float", "double", "if", "else", "break",
			"continue", "switch", "case", "default", "while", "void", "static", "public", "private", "protected",
			"return", "null", "final", "for", "extends", "class", "implements", "interface", "enum", "main" };
	// JAVA�еĽ��
	private static char[] delimeter = {'/','/','*','[',']','{','}','(',')','&','|','!'};
	
	// JAVA�е������
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

		JMenu menu = new JMenu("�ļ�");
		menu.setBackground(Color.WHITE);
		menu.setFont(new Font("����", Font.PLAIN, 12));
		menu.setForeground(Color.DARK_GRAY);
		menu.setHorizontalAlignment(SwingConstants.LEFT);
		JMenu menu1 = new JMenu("�ʷ�����");
		menu1.setBackground(Color.WHITE);
		menu1.setFont(new Font("����", Font.PLAIN, 12));
		menu1.setForeground(Color.DARK_GRAY);
		JMenu menu2 = new JMenu("�﷨����");
		menu2.setFont(new Font("����", Font.PLAIN, 12));
		menu2.setForeground(Color.DARK_GRAY);
		menu2.setHorizontalAlignment(SwingConstants.LEFT);
		JMenu menu3 = new JMenu("�м��������");
		menu3.setFont(new Font("����", Font.PLAIN, 12));
		menu3.setForeground(Color.DARK_GRAY);
		menu3.setHorizontalAlignment(SwingConstants.LEFT);
		JMenu menu4 = new JMenu("Ŀ���������");
		menu4.setFont(new Font("����", Font.PLAIN, 12));
		menu4.setForeground(Color.DARK_GRAY);
		menu4.setHorizontalAlignment(SwingConstants.LEFT);
		JMenu menu5 = new JMenu("����");
		menu5.setFont(new Font("����", Font.PLAIN, 12));
		menu5.setForeground(Color.DARK_GRAY);
		menu5.setHorizontalAlignment(SwingConstants.LEFT);

		setJMenuBar(jMenuBar);

		btnHand = new JButton("�ֹ����ɹ�����ʾ (M)");
		btnHand.setBackground(Color.WHITE);
		btnHand.setForeground(Color.DARK_GRAY);
		btnHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		menu1.add(btnHand);

		btnAuto = new JButton("�Զ����ɹ�����ʾ (A)");
		btnAuto.setBackground(Color.WHITE);
		btnAuto.setForeground(Color.DARK_GRAY);
		btnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count = 0;// ��¼����ĸ���
				char c = ' ';
				String str = sourcePane.getText();
//				System.out.println(str);
				workPane.setText(str);
				sort(c);
				scanner();
				errorPane.setText("���� "+ count +" ������");

			}
		});
		menu1.add(btnAuto);
		jMenuBar.add(menu);
		jMenuBar.add(menu1);
		jMenuBar.add(menu2);
		jMenuBar.add(menu3);
		jMenuBar.add(menu4);
		jMenuBar.add(menu5);

		btnOpen = new JButton("�� (O)");
		btnOpen.setForeground(Color.DARK_GRAY);
		btnOpen.setBackground(Color.WHITE);
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jf = new JFileChooser("E:\\compiler");
				jf.setDialogTitle("ѡ��һ��Դ�����ļ���");
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
		btnSave = new JButton("���� (S)");
		btnSave.setForeground(Color.DARK_GRAY);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Object[] options = { "ȷ��", "ȡ��" };
				int response = JOptionPane.showOptionDialog(new JFrame(), "ȷ�ϱ��棿", "����", JOptionPane.YES_OPTION,
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

		btnAnother = new JButton("��� (A)");
		btnAnother.setForeground(Color.DARK_GRAY);
		btnAnother.setBackground(Color.WHITE);
		btnAnother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser("E:\\compiler"); // �����ļ�ѡ��Ի���
				int result = chooser.showSaveDialog(new JFrame());
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fout = new FileWriter(chooser.getSelectedFile()); // �������д�ļ�
						fout.write(sourcePane.getText() + "\r\n");
						fout.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		menu.add(btnAnother);

		btnExit = new JButton("�˳� (E)");
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
		sourcePane.setFont(new Font("����", Font.PLAIN, 15));

		workPane = new JIMSendTextPane();
		workPane.setBounds(525, 46, 540, 248);
		workPane.setFont(new Font("����", Font.PLAIN, 15));
		workPane.setEditable(false);

		JLabel lblWorkList = new JLabel("Work List");
		lblWorkList.setBounds(525, 8, 71, 28);

		JLabel lblError = new JLabel("Error");
		lblError.setBounds(10, 316, 71, 28);

		errorPane = new JIMSendTextPane();
		errorPane.setBounds(10, 354, 492, 263);
		errorPane.setFont(new Font("����", Font.PLAIN, 15));
		errorPane.setEditable(false);

		JLabel lblEmblem = new JLabel("Emblems");
		lblEmblem.setBounds(525, 316, 71, 28);

		emblemPane = new JIMSendTextPane();
		emblemPane.setBounds(525, 354, 540, 263);
		emblemPane.setFont(new Font("����", Font.PLAIN, 15));
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

	// ���������д���ļ���
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
			System.out.println("�ļ��Ѵ��ڣ�����ʧ�� ��");
		} else {
			System.out.println("�ļ������ڣ������ɹ� �� ");
		}
//		System.out.println(str + "null");

		FileWriter fout = new FileWriter("E:\\compiler\\" + year + month + day + hour + minute + second + ".txt"); // �������д�ļ�
		fout.write(sourcePane.getText() + "\r\n");
		fout.close();
	}

	// �ʷ�������������sacnner
	private static void scanner() {
//		int count = 0;// �м�����
		if (true) {
//			count++;
//			System.out.println(count);
		}
	}

	// ���ຯ��sort()
	private static int sort(char c) {
		if (isLetter(c))// ��ʶ��
			return IDENTIFIER;
		else if (isDigit(c))// ����
			return CONSTANT;
//		else if (isKeyword(c))// �ؼ���
//			return KEYWORD;
		else if (isOperator(c))// ������
			return OPERATOR;
		else if (isDelimeter(c))// ���
			return DELIMETER;
		else
			return ERROR;// ����
	}
	
//	// �Ƿ�ؼ���
//	private static boolean isKeyword(char c) {
//		for(String str : keyword) {
//			if(str.equals(str))
//				return true;
//		}
//		return false;
//	}

	// �Ƿ������
	private static boolean isOperator(char c) {
		for(char ch : operator) {
			if(ch == c)
				return true;
		}
		return false;
	}

	// �Ƿ���ĸ
	public static boolean isLetter(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
			return true;
		return false;
	}

	// �Ƿ�����
	public static boolean isDigit(char c) {
		if (c >= 0 && c <= 9) {
			return true;
		}
		return false;
	}

	// �Ƿ���
	public static boolean isDelimeter(char c) {
		for(char ch : delimeter) {
			if(c == ch)
				return true;
		}
		return false;
	}
}
