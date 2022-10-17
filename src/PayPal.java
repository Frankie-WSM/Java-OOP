import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayPal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailInput;

	/**
	 * Create the frame.
	 */
	public PayPal(Customer customer, ArrayList<Product> productList, JTabbedPane tabbedPane) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton checkoutButton = new JButton("Checkout");
		checkoutButton.setBounds(281, 206, 89, 23);
		contentPane.add(checkoutButton);
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //purchases the item and removes right amount of stock
				if (emailInput.getText().equals("") || emailInput.getText().contains(""+'@') != true) {
					JOptionPane.showMessageDialog(null, "Error: Please enter an email address with '@' character.");
				} else {	
					dispose();
					PayedFrame payed = new PayedFrame(customer, "PayPal.", tabbedPane);
					payed.setVisible(true);
					customer.takeStock(productList);
				}
			}
		});
		
		emailInput = new JTextField();
		emailInput.setBounds(111, 118, 195, 20);
		contentPane.add(emailInput);
		emailInput.setColumns(10);
		
		JLabel paypalLabel = new JLabel("Please enter PayPal email:");
		paypalLabel.setBounds(111, 93, 156, 14);
		contentPane.add(paypalLabel);
		
		JLabel titleLabel = new JLabel("PayPal");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		titleLabel.setBounds(164, 11, 89, 40);
		contentPane.add(titleLabel);
		
		JButton cancelButton = new JButton("Cancel purchase");
		cancelButton.setBounds(46, 206, 142, 23);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() { //cancels purchase and returns to basket
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Purchase cancelled.");
				Basket basket = new Basket(customer, productList, tabbedPane);
				basket.setVisible(true);
				dispose();
			}
		});
	}
}
