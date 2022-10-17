import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PayedFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PayedFrame(Customer customer, String payType, JTabbedPane tabbedPane) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton okayButton = new JButton("Okay");
		okayButton.setBounds(93, 111, 89, 23);
		contentPane.add(okayButton);
		okayButton.addActionListener(new ActionListener() { //logs user out to start again 
			public void actionPerformed(ActionEvent e) {
				dispose();
				tabbedPane.setVisible(true);
				try {
					tabbedPane.removeTabAt(1);
				}
				catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		JLabel amountLabel = new JLabel("£" + Double.toString(customer.total_amount()) + " paid using " + payType);
		amountLabel.setBounds(10, 27, 264, 31);
		contentPane.add(amountLabel);
		
		JLabel addressLabel = new JLabel("Delivery Address is" + customer.getAddress().toString());
		addressLabel.setBounds(10, 52, 264, 35);
		contentPane.add(addressLabel);
	}

}
