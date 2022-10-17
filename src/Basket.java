import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Basket extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Basket(Customer customer, ArrayList<Product> productList, JTabbedPane tabbedPane) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 336, 251);
		contentPane.add(scrollPane);
		
		
		
		JTable basketTable = new JTable();
		String[] basketColumnHeaders = new String[] {"Barcode", "Product details", "RRP", "Quantity in basket"};
		
		scrollPane.setViewportView(basketTable);
		DefaultTableModel basketModel = new DefaultTableModel();
		basketModel.setColumnIdentifiers(basketColumnHeaders);
		basketTable.setModel(basketModel);
		basketTable.setShowHorizontalLines(false);
		basketTable.setBounds(10, 58, 515, 313);
		
		for (int i = 0; i < customer.getShoppingBasket().size(); i++) { //inputting all items to customer table
			BasketItem item = customer.getShoppingBasket().get(i);
			String[] itemInfo = new String[4];
			itemInfo[0] = item.getProd().getBarcode();
			if (item.getProd() instanceof Mouse) {
				itemInfo[1] = ((Mouse) item.getProd()).toCustString();
			} else {
				itemInfo[1] = ((Keyboard) item.getProd()).toCustString();
			}
			itemInfo[2] = ("£" + item.getProd().getRetaPrc());
			itemInfo[3] = Integer.toString(item.getQuant());
			basketModel.addRow(itemInfo);
			String[] clearLine = new String[] {" ", " ", " ", " "};
			basketModel.addRow(clearLine);
		}
		
		
		
		JLabel basketLabel = new JLabel("Your basket");
		basketLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		basketLabel.setBounds(10, 11, 151, 27);
		contentPane.add(basketLabel);
		
		JButton basketClearButton = new JButton("Clear basket");
		basketClearButton.setBounds(379, 265, 122, 23);
		contentPane.add(basketClearButton);
		basketClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer.getShoppingBasket().clear();
				JOptionPane.showMessageDialog(null, "Shopping basket cleared.");
				tabbedPane.setVisible(true);
				dispose();
			}
		});
		
		JButton paypalButton = new JButton("Checkout with PayPal");
		paypalButton.setBounds(356, 80, 168, 23);
		contentPane.add(paypalButton);
		paypalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PayPal paypal = new PayPal(customer, productList, tabbedPane);
				paypal.setVisible(true);
			}
		});
		
		JButton cardButton = new JButton("Checkout with card");
		cardButton.setBounds(356, 46, 168, 23);
		contentPane.add(cardButton);
		
		JButton addMoreButton = new JButton("Add more items");
		addMoreButton.setBounds(356, 231, 168, 23);
		contentPane.add(addMoreButton);
		
		JLabel totalLabel = new JLabel("Total: \u00A3"+Double.toString(customer.total_amount()));
		totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalLabel.setBounds(356, 129, 168, 36);
		contentPane.add(totalLabel);
		
		addMoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				tabbedPane.setVisible(true);
			}
		});
		
		cardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CardPay card = new CardPay(customer, productList, tabbedPane);
				card.setVisible(true);
			}
		});
	}
}
