package softwareDevelopment2Coursework;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import grid.Grid;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {
	private SkyWarsController controller; 
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new ImageBackgroundPanel("background.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelCentre = new JPanel();
		panelCentre.setOpaque(false);
		contentPane.add(panelCentre);
		panelCentre.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("245dlu"), },
				new RowSpec[] { RowSpec.decode("50px"), RowSpec.decode("50px"), RowSpec.decode("50px"),
						RowSpec.decode("50px"), RowSpec.decode("300px"), RowSpec.decode("50px"), }));

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.newGame();
				dispose();
			}
		});
		btnNewGame.setBackground(new Color(40, 48, 58));
		panelCentre.add(btnNewGame, "1, 2, fill, fill");

		JButton btnLoadGame = new JButton("Load Game");
		panelCentre.add(btnLoadGame, "1, 4, fill, fill");

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelCentre.add(btnExit, "1, 6, fill, fill");

		JPanel panelWest = new JPanel();
		FlowLayout fl_panelWest = (FlowLayout) panelWest.getLayout();
		fl_panelWest.setHgap(200);
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setOpaque(false);

		JPanel panelEast = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelEast.getLayout();
		flowLayout.setHgap(200);
		contentPane.add(panelEast, BorderLayout.EAST);
		panelEast.setOpaque(false);

		JPanel panelNorth = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelNorth.getLayout();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setOpaque(false);

		JLabel lblTitle = new JLabel("Sky Wars");
		panelNorth.add(lblTitle);
		lblTitle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 36));
		lblTitle.setForeground(new Color(30, 144, 255));

	}

	public void setController(SkyWarsController controller) {
		this.controller = controller;
	}
}