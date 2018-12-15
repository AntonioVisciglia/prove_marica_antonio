package email.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import email.EmailSender;

public class MyPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	final int B_WIDTH = 600;
	final int B_HEIGHT = 400;
	JLabel sendLabel, objectLabel, messageLabel;
	JTextField sendTextField, objectTextField;
	JTextArea messageTextArea;

	JButton send;
	EmailSender emailSender;

	public MyPanel() {
		init();
	}

	private void init() {
		// setBackground(Color.white);

		emailSender = new EmailSender();
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setDoubleBuffered(true);

		sendLabel = new JLabel("Send to  ");
		sendLabel.setForeground(Color.black);

		objectLabel = new JLabel("Object   ");
		objectLabel.setForeground(Color.black);

		messageLabel = new JLabel("Message");
		messageLabel.setForeground(Color.black);

		sendTextField = new JTextField(45);
		objectTextField = new JTextField(45);
		messageTextArea = new JTextArea(15, 45);

		send = new JButton("Send");
		send.addActionListener(this);
		// send.setEnabled(false);

		add(sendLabel);
		add(sendTextField);
		add(objectLabel);
		add(objectTextField);
		add(messageLabel);
		add(new JScrollPane(messageTextArea));
		add(send);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(send)) {
			emailSender.sendMessage(sendTextField.getText(), objectTextField.getText(), messageTextArea.getText());
		}
	}
}