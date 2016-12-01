package io.github.gusg21.kberry.GUI;

import io.github.gusg21.kberry.kBerry;
import io.github.gusg21.kberry.supp.CLS;
import io.github.gusg21.kberry.supp.TextAreaOutputStream;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class kBerryWrapperGUI extends JFrame {

	/**
	 * Default Serial
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inKB;
	private JTextField outClass;
	private JTextField txtArguments;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			kBerry.throwError("No System LookAndFeel? What does your system look and feel like, then?");
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kBerryWrapperGUI frame = new kBerryWrapperGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					kBerry.throwError("GUI execution error. Try the command line version.");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public kBerryWrapperGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(kBerryWrapperGUI.class.getResource("/io/github/gusg21/kberry/assets/icon.png")));
		setTitle("kBerry Compiler");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Compiler = new JPanel();
		tabbedPane.addTab("Compiler", null, Compiler, null);
		Compiler.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 507, 176);
		Compiler.add(scrollPane);
		
		final JTextArea Console = new JTextArea();
		Console.setLocation(0, 176);
		Console.setForeground(new Color(0, 0, 128));
		scrollPane.setViewportView(Console);
		
		PrintStream out = new PrintStream(new TextAreaOutputStream(Console));
		
		// redirect standard output stream to the TextAreaOutputStream
		System.setOut(out);

		// redirect standard error stream to the TextAreaOutputStream
		System.setErr(out);

		// now test the mechanism
		System.out.println( "kBerry GUI Initiated" );
		
		JPanel paneAbout = new JPanel();
		tabbedPane.addTab("About", null, paneAbout, null);
		paneAbout.setLayout(null);
		
		JEditorPane dtrpnAboutKberryKberry = new JEditorPane();
		dtrpnAboutKberryKberry.setFont(new Font("Dialog", Font.PLAIN, 23));
		dtrpnAboutKberryKberry.setEditable(false);
		dtrpnAboutKberryKberry.setText("About kBerry:\r\nkBerry is a Java-based compiled language made out of pure terribleness. Made as a toy compiler, the entire language is a nice thing to mess with, but completely (and utterly) pointless.");
		dtrpnAboutKberryKberry.setBounds(10, 11, 507, 241);
		paneAbout.add(dtrpnAboutKberryKberry);
		
		JLabel lblHttpgithubcomgusg = new JLabel("<html><u>http://github.com/gusg21</u><html>");
		lblHttpgithubcomgusg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				openWebpage("https://github.com/gusg21");
			}
		});
		lblHttpgithubcomgusg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHttpgithubcomgusg.setForeground(Color.BLUE);
		lblHttpgithubcomgusg.setHorizontalAlignment(SwingConstants.CENTER);
		lblHttpgithubcomgusg.setBounds(10, 287, 507, 14);
		paneAbout.add(lblHttpgithubcomgusg);
		
		inKB = new JTextField();
		inKB.setText("Input .kb file (full path)");
		inKB.setBounds(10, 5, 395, 20);
		inKB.setColumns(10);
		Compiler.add(inKB);
		
		outClass = new JTextField();
		outClass.setText("Output .java file name (no path; just name)");
		outClass.setColumns(10);
		outClass.setBounds(10, 36, 507, 20);
		Compiler.add(outClass);
		
		txtArguments = new JTextField();
		txtArguments.setText("Arguments (optional)");
		txtArguments.setColumns(10);
		txtArguments.setBounds(10, 67, 507, 20);
		Compiler.add(txtArguments);
		
		JButton btnCompile = new JButton("GO!");
		btnCompile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtArguments.getText() == "Arguments (optional)") {
					txtArguments.setText("");
				}
				CLS.clear(Console);
				kBerry.parse(inKB.getText(), outClass.getText(), txtArguments.getText()); // Line you're looking for
			}
		});
		
		btnCompile.setFont(new Font("Verdana", Font.BOLD, 41));
		btnCompile.setBounds(10, 98, 507, 59);
		Compiler.add(btnCompile);
		this.getRootPane().setDefaultButton(btnCompile);
		
		JButton btnClearConsole = new JButton("Clear Console");
		btnClearConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CLS.clear(Console);
			}
		});
		btnClearConsole.setBounds(10, 394, 507, 28);
		Compiler.add(btnClearConsole);
		btnClearConsole.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		final JButton buttonIn = new JButton("Choose...");
		buttonIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser(new File(System.getProperty("user.home")));
				FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("kBerry Files (*.kb, *.kberry)", "kb", "kberry");
				fc.setFileFilter(fileFilter);
				File selFile = null;
				fc.showOpenDialog(buttonIn);

				try {
				    selFile = fc.getSelectedFile();
				} finally {
					// ...
				}
				
				if (!(String.valueOf(selFile) == "null")) {
					inKB.setText(String.valueOf(selFile));
					String fname = selFile.getName();
					int pos = fname.lastIndexOf(".");
					if (pos > 0) {
					    fname = fname.substring(0, pos);
					}
					outClass.setText(fname.substring(0, 1).toUpperCase() + fname.substring(1));
				}
			}
		});
		buttonIn.setBounds(417, 5, 100, 20);
		Compiler.add(buttonIn);
		
		JButton btnExecute = new JButton("Open Output Directory");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().open(new File(System.getProperty("user.home") + File.separator + "kberry-c" + File.separator + outClass.getText()));
				} catch (IllegalArgumentException e) {
					kBerry.throwError("Couldn't open the directory: " + System.getProperty("user.home") + File.separator + "kberry-c" + File.separator + outClass.getText());
					e.printStackTrace();
				} catch (IOException e) {
					kBerry.throwError("Couldn't open the directory: " + System.getProperty("user.home") + File.separator + "kberry-c" + File.separator + outClass.getText());
					e.printStackTrace();
				}
			}
		});
		btnExecute.setIcon(new ImageIcon(kBerryWrapperGUI.class.getResource("/io/github/gusg21/kberry/assets/run.png")));
		btnExecute.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnExecute.setBounds(10, 355, 507, 28);
		Compiler.add(btnExecute);
		
	}

	public static void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	        kBerry.throwError("GUI: Link not found! Page Down?");
	    }
	}
}
