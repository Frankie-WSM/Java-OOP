import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class CardPay extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numberInput;
	private JTextField codeInput;

	/**
	 * Create the frame.
	 */
	public CardPay(Customer customer, ArrayList<Product> productList, JTabbedPane tabbedPane) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("(In)Secure Card Payment");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		titleLabel.setBounds(57, 11, 313, 34);
		contentPane.add(titleLabel);
		
		JButton checkoutButton = new JButton("Checkout");
		checkoutButton.setBounds(287, 211, 100, 23);
		contentPane.add(checkoutButton);
		checkoutButton.addActionListener(new ActionListener() { //purchases the item and removes right amount of stock
			public void actionPerformed(ActionEvent e) { //validating card payment details
				if (numberInput.getText().equals("") || codeInput.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Error: Both a number and code must be input.");
				} else if (numberInput.getText().length() != 6 || codeInput.getText().length() != 3) {
					JOptionPane.showMessageDialog(null, "Error: Card number must be a 6 digit number and code must be a 3 digit number.");
				} else if (numberInput.getText().matches("\\d+") || codeInput.getText().matches("\\d+")) {
					dispose();
					PayedFrame payed = new PayedFrame(customer, "credit card.", tabbedPane);
					payed.setVisible(true); 
					customer.takeStock(productList);
				} else {
					JOptionPane.showMessageDialog(null, "Error: Inputs must be numbers.");
				}
			}
		});
		
		numberInput = new JTextField();
		numberInput.setBounds(57, 111, 135, 20);
		contentPane.add(numberInput);
		numberInput.setColumns(10);
		
		codeInput = new JTextField();
		codeInput.setBounds(250, 111, 86, 20);
		contentPane.add(codeInput);
		codeInput.setColumns(10);
		
		JLabel numberLabel = new JLabel("Enter 6 digit card number:");
		numberLabel.setBounds(46, 86, 146, 14);
		contentPane.add(numberLabel);
		
		JLabel codeLabel = new JLabel("Enter 3 digit security code:");
		codeLabel.setBounds(234, 86, 155, 14);
		contentPane.add(codeLabel);
		
		JButton cancelButton = new JButton("Cancel purchase");
		cancelButton.setBounds(31, 211, 146, 23);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Purchase cancelled.");
				Basket basket = new Basket(customer, productList, tabbedPane);
				basket.setVisible(true);
				dispose();
			}
		});
	}

}
