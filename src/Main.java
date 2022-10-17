import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField barcodeInput;
	private JTextField brandInput;
	private JTextField colourInput;
	private JTextField quantInput;
	private JTextField origCostInput;
	private JTextField retPriceInput;
	private JTextField buttonsInput;
	private JTextField typeInput;
	private JTextField buttonSearch;
	private JTextField brandSearch;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	
	public Main() {
		
		ArrayList<Product> productList = new ArrayList<Product>();
		ArrayList<User> userList = new ArrayList<User>();
			
		File userFile = new File("UserAccounts.txt");
		try { //adding all users to arraylist of user objects
			Scanner fileScanner = new Scanner(userFile);
			while (fileScanner.hasNextLine()) {
				String[] details = fileScanner.nextLine().split(",");
				String userType = (details[6].trim());
				if (userType.equals("admin")) {
					Admin admin = new Admin(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(), new Address(Integer.parseInt(details[3].trim()), details[4].trim(), details[5].trim()));
					userList.add(admin);
				} else if (userType.equals("customer")) {
					ArrayList<BasketItem> shoppingBasket = new ArrayList<>();
					Customer customer = new Customer(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(), new Address(Integer.parseInt(details[3].trim()), details[4].trim(), details[5].trim()), shoppingBasket);
					userList.add(customer);
				}
			}
			fileScanner.close();
		} catch (Exception e0) {
			e0.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 984, 511);
		contentPane.add(tabbedPane);
		
		List <String> userNameList = new ArrayList<String>();
		userList.forEach(user -> userNameList.add(user.getUserName())); //for combobox
		
	
		//ADMIN TAB

		
		JPanel admTab = new JPanel();
		JButton showProdBtn = new JButton("Press to display or update products");
		showProdBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		showProdBtn.setBounds(187, 11, 289, 36);
		admTab.setLayout(null);
		
		JButton addProdBtn = new JButton("Add product");
		addProdBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addProdBtn.setBounds(762, 374, 114, 36);
		admTab.add(addProdBtn);
		admTab.add(showProdBtn);
		
		barcodeInput = new JTextField();
		barcodeInput.setBounds(855, 63, 114, 20);
		admTab.add(barcodeInput);
		barcodeInput.setColumns(10);
		
		JLabel addLabel = new JLabel("Add Product");
		addLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addLabel.setBounds(762, 15, 114, 23);
		admTab.add(addLabel);
		
		brandInput = new JTextField();
		brandInput.setBounds(855, 125, 114, 20);
		admTab.add(brandInput);
		brandInput.setColumns(10);
		
		typeInput = new JTextField();
		typeInput.setBounds(855, 94, 114, 20);
		admTab.add(typeInput);
		typeInput.setColumns(10);
		
		colourInput = new JTextField();
		colourInput.setBounds(855, 156, 114, 20);
		admTab.add(colourInput);
		colourInput.setColumns(10);
		
		quantInput = new JTextField();
		quantInput.setBounds(854, 218, 115, 20);
		admTab.add(quantInput);
		quantInput.setColumns(10);
		
		origCostInput = new JTextField();
		origCostInput.setBounds(855, 249, 114, 20);
		admTab.add(origCostInput);
		origCostInput.setColumns(10);
		
		retPriceInput = new JTextField();
		retPriceInput.setBounds(854, 280, 115, 20);
		admTab.add(retPriceInput);
		retPriceInput.setColumns(10);
		
		buttonsInput = new JTextField();
		buttonsInput.setBounds(905, 342, 64, 20);
		admTab.add(buttonsInput);
		buttonsInput.setColumns(10);
		
		JLabel layoutLabel = new JLabel("IF KEYBOARD: enter layout");
		layoutLabel.setBounds(710, 314, 148, 14);
		admTab.add(layoutLabel);
		
		JLabel buttonsLabel = new JLabel("IF MOUSE: enter button amount");
		buttonsLabel.setBounds(710, 341, 185, 22);
		admTab.add(buttonsLabel);
		
		JTable admProductTable = new JTable();
		String[] admColumnHeaders = new String[] {"Barcode","Product", "Type", "Brand", "Colour", "Connctivity", "Quantity", "Original Price", "Retail Price", "Details"};
		
		DefaultTableModel admModel = new DefaultTableModel();
		admModel.setColumnIdentifiers(admColumnHeaders);
		admProductTable.setModel(admModel);
		admProductTable.setShowHorizontalLines(false);
		admProductTable.setBounds(10, 58, 515, 313);
		
		JScrollPane admScrollPane = new JScrollPane();
		admScrollPane.setBounds(10, 58, 690, 402);
		admTab.add(admScrollPane);
		admScrollPane.setViewportView(admProductTable);
		
		JLabel origCostLabel = new JLabel("Enter original cost");
		origCostLabel.setBounds(713, 252, 117, 14);
		admTab.add(origCostLabel);
		
		JLabel retPricecLabel = new JLabel("Enter retail price");
		retPricecLabel.setBounds(717, 283, 113, 14);
		admTab.add(retPricecLabel);
		
		JLabel connecLabel = new JLabel("Enter connectivity");
		connecLabel.setBounds(707, 190, 123, 14);
		admTab.add(connecLabel);
		
		JLabel quantLabel = new JLabel("Enter quantity");
		quantLabel.setBounds(713, 221, 117, 14);
		admTab.add(quantLabel);
		
		JLabel brandLabel = new JLabel("Enter brand");
		brandLabel.setBounds(712, 128, 96, 14);
		admTab.add(brandLabel);
		
		JLabel colourLabel = new JLabel("Enter colour");
		colourLabel.setBounds(710, 159, 98, 14);
		admTab.add(colourLabel);
		
		JLabel barcodeLabel = new JLabel("Enter barcode");
		barcodeLabel.setBounds(710, 66, 98, 14);
		admTab.add(barcodeLabel);
		
		JLabel typeLabel = new JLabel("Enter type");
		typeLabel.setBounds(712, 97, 96, 14);
		admTab.add(typeLabel);
		
		JComboBox connecInput = new JComboBox();
		connecInput.setModel(new DefaultComboBoxModel(new String[] {"wireless", "wired"}));
		connecInput.setBounds(855, 185, 114, 22);
		admTab.add(connecInput);
		
		JComboBox layoutInput = new JComboBox();
		layoutInput.setModel(new DefaultComboBoxModel(new String[] {"Select layout", "UK", "US"}));
		layoutInput.setToolTipText("");
		layoutInput.setBounds(884, 311, 85, 22);
		admTab.add(layoutInput);
		
		JPanel loginTab = new JPanel();
		tabbedPane.addTab("Login", null, loginTab, null);
		loginTab.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Select Username:");
		usernameLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 24));
		usernameLabel.setBounds(195, 154, 211, 43);
		loginTab.add(usernameLabel);
		
		JComboBox usernameCB = new JComboBox(userNameList.toArray());
		usernameCB.setFont(new Font("Tahoma", Font.PLAIN, 24));
		usernameCB.setBounds(434, 160, 126, 43);
		loginTab.add(usernameCB);
		
		JLabel titleLabel = new JLabel("Computer Accessory Shop System");
		titleLabel.setForeground(Color.RED);
		titleLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 40));
		titleLabel.setBounds(145, 11, 622, 65);
		loginTab.add(titleLabel);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		loginBtn.setBounds(379, 271, 181, 76);
		loginTab.add(loginBtn);
		
		
		//CUSTOMER TAB
		
		
		JPanel custTab = new JPanel();
		custTab.setLayout(null);
		
		buttonSearch = new JTextField();
		buttonSearch.setBounds(370, 130, 86, 20);
		custTab.add(buttonSearch);
		buttonSearch.setColumns(10);
		
		JLabel buttonSearchLabel = new JLabel("Enter button amount");
		buttonSearchLabel.setBounds(235, 133, 125, 14);
		custTab.add(buttonSearchLabel);
		
		brandSearch = new JTextField();
		brandSearch.setBounds(121, 130, 86, 20);
		custTab.add(brandSearch);
		brandSearch.setColumns(10);
		
		JLabel brandSearchLabel = new JLabel("Enter brand");
		brandSearchLabel.setBounds(49, 133, 74, 14);
		custTab.add(brandSearchLabel);
		
		JButton filterBtn = new JButton("Filter results");
		filterBtn.setBounds(522, 129, 125, 23);
		custTab.add(filterBtn);
		
		
		JTable custProductTable = new JTable();
		String[] custColumnHeaders = new String[] {"Barcode", "Product details", "RRP", "Quantity in stock"};
		
		DefaultTableModel custModel = new DefaultTableModel();
		custModel.setColumnIdentifiers(custColumnHeaders);
		custProductTable.setModel(custModel);
		custProductTable.setShowHorizontalLines(false);
		custProductTable.setBounds(10, 58, 515, 313);
		
		JScrollPane custScrollPane = new JScrollPane();
		custScrollPane.setBounds(10, 185, 689, 246);
		custTab.add(custScrollPane);
		custScrollPane.setViewportView(custProductTable);
		
		JButton basketButton = new JButton("View basket and checkout");
		basketButton.setBounds(742, 298, 190, 51);
		custTab.add(basketButton);
		
		JLabel searchLabel = new JLabel("Search For Products");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		searchLabel.setBounds(325, 35, 275, 34);
		custTab.add(searchLabel);
		
		JButton addButton = new JButton("Add to basket");
		addButton.setBounds(765, 195, 120, 23);
		custTab.add(addButton);
		
		
		JLabel highlightLabel = new JLabel("Highlight desired product in the table");
		highlightLabel.setBounds(725, 133, 220, 14);
		custTab.add(highlightLabel);
		
		JLabel highlightLabel2 = new JLabel("and add to basket.");
		highlightLabel2.setBounds(768, 151, 120, 14);
		custTab.add(highlightLabel2);
		custProductTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		custProductTable.getColumnModel().getColumn(1).setPreferredWidth(220);
		custProductTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		custProductTable.getColumnModel().getColumn(3).setPreferredWidth(80);
	
		filterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //start of logic for filtering customer table results
				custModel.setRowCount(0); 
				if (brandSearch.getText().equals("") && buttonSearch.getText().equals("")) {
					for (int i = 0; i < productList.size(); i++) {
						Product product = productList.get(i);
						String[] prodInfo = new String[4];
						prodInfo[0] = product.getBarcode();
						if (product instanceof Mouse) {
							prodInfo[1] = ((Mouse) product).toCustString();
						} else {
							prodInfo[1] = ((Keyboard) product).toCustString();
						}
						prodInfo[2] = ("£" + product.getRetaPrc());
						prodInfo[3] = product.getQuant();
						custModel.addRow(prodInfo);
						String[] clearLine = new String[] {" ", " ", " ", " "};
						custModel.addRow(clearLine);
					}
					JOptionPane.showMessageDialog(null, "Error: no search filters input.");
				} else if (brandSearch.getText().equals("")) {
					custModel.setRowCount(0);
					for (int i = 0; i < productList.size(); i++) {
						Product product = productList.get(i);
						if (product instanceof Mouse) {
							if (((Mouse) product).getButtons().equals(buttonSearch.getText())) {
								String[] prodInfo = new String[4];
								prodInfo[0] = product.getBarcode();
								prodInfo[1] = ((Mouse) product).toCustString();
								prodInfo[2] = ("£" + product.getRetaPrc());
								prodInfo[3] = product.getQuant();
								custModel.addRow(prodInfo);
								String[] clearLine = new String[] {" ", " ", " ", " "};
								custModel.addRow(clearLine);
							}
						}
					}
				} else if (buttonSearch.getText().equals("")) {
					custModel.setRowCount(0);
					for (int i = 0; i < productList.size(); i++) {
						Product product = productList.get(i);
						if (product.getBrand().equals(brandSearch.getText())) {
							String[] prodInfo = new String[4];
							prodInfo[0] = product.getBarcode();
							if (product instanceof Mouse) {
								prodInfo[1] = ((Mouse) product).toCustString();
							} else {
								prodInfo[1] = ((Keyboard) product).toCustString();
							}
							prodInfo[2] = ("£" + product.getRetaPrc());
							prodInfo[3] = product.getQuant();
							custModel.addRow(prodInfo);
							String[] clearLine = new String[] {" ", " ", " ", " "};
							custModel.addRow(clearLine);
						}
					}
				} else {
					custModel.setRowCount(0);
					for (int i = 0; i < productList.size(); i++) {
						Product product = productList.get(i);
						if (product instanceof Mouse && product.getBrand().equals(brandSearch.getText())) {
							if (((Mouse) product).getButtons().equals(buttonSearch.getText())) {
								String[] prodInfo = new String[4];
								prodInfo[0] = product.getBarcode();
								prodInfo[1] = ((Mouse) product).toCustString();
								prodInfo[2] = ("£" + product.getRetaPrc());
								prodInfo[3] = product.getQuant();
								custModel.addRow(prodInfo);
								String[] clearLine = new String[] {" ", " ", " ", " "};
								custModel.addRow(clearLine);
							}
						}
					}
				} //end of logic for filtering customer table results 
			}
		});
	
		
		//LOGIN BUTTON
		
		
		loginBtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File stockFile = new File("Stock.txt");
				try { //adds all the products to an array list of product objects
					Scanner fileScanner = new Scanner(stockFile);
					productList.clear();
					while (fileScanner.hasNextLine()) {
						String[] details = fileScanner.nextLine().split(",");
						String prodType = (details[1].trim());
						if (prodType.equals("keyboard")) {
							Keyboard keyboard = new Keyboard(Integer.parseInt(details[0].trim()), details[2].trim(), details[3].trim(), details[4].trim(), details[5].trim(), Integer.parseInt(details[6].trim()), Double.parseDouble(details[7].trim()), Double.parseDouble(details[8].trim()), details[9].trim());
							productList.add(keyboard);
						} else if (prodType.equals("mouse")) {
							Mouse mouse = new Mouse(Integer.parseInt(details[0].trim()), details[2].trim(), details[3].trim(), details[4].trim(), details[5].trim(), Integer.parseInt(details[6].trim()), Double.parseDouble(details[7].trim()), Double.parseDouble(details[8].trim()), Integer.parseInt(details[9].trim()));
							productList.add(mouse);
						}
					}
					fileScanner.close();
					Collections.sort(productList, new ProductComparator());
				} catch (Exception e0) {
					e0.printStackTrace();
				}
				
				PrintWriter pw;
				try {
					pw = new PrintWriter("Stock.txt");//clears the file
					pw.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try { //rewrites the file (to avoid whitespace lines)
					Writer output = new BufferedWriter(new FileWriter(stockFile, true));
					for (Product product : productList) {
						if (product instanceof Keyboard) {
							output.append(((Keyboard) product).toAdminString()+"\n");
						} else {
							output.append(((Mouse) product).toAdminString()+"\n");
						}
					}
					output.close();
				} catch (Exception e0) {
					e0.printStackTrace();
				}
				
				String username = (String) usernameCB.getSelectedItem(); //selecting user for login
				for (User user :userList) {
					if (user.getUserName().equals(username)) {
						if (user instanceof Admin) {
							if (tabbedPane.getTabCount() == 2) {
								try {
									tabbedPane.removeTabAt(1);
								}
								catch (Exception e1){
									e1.printStackTrace();
								}
							}
							tabbedPane.addTab(user.getName()+" (admin)", null, admTab, null);
							tabbedPane.setSelectedIndex(1);
							showProdBtn.addActionListener( new ActionListener() { //displaying products for admin
								public void actionPerformed(ActionEvent e) {
									admModel.setRowCount(0);
									for (int i = 0; i < productList.size(); i++) {
										Product product = productList.get(i);
										String[] prodInfo = new String[10];
										prodInfo[0] = product.getBarcode();
										prodInfo[2] = product.getType();
										prodInfo[3] = product.getBrand();
										prodInfo[4] = product.getColour();
										prodInfo[5] = product.getConnec();
										prodInfo[6] = (product.getQuant() + " in stock");
										prodInfo[7] = ("£" + product.getOrigPrc());
										prodInfo[8] = ("£" + product.getRetaPrc());
										if (product instanceof Mouse) {
											prodInfo[9] = (((Mouse) product).getButtons() + " buttons");
											prodInfo[1] = "mouse";
										} else {
											prodInfo[9] = (((Keyboard) product).getLayout() + " layout");
											prodInfo[1] = "keyboard";
										}
										admModel.addRow(prodInfo);
									}
								}
							});
							addProdBtn.addActionListener( new ActionListener() { //adding a product for admin
								public void actionPerformed(ActionEvent e) {
									File stockFile = new File("Stock.txt");
									try {
										boolean prodExists = false;
										Writer output = new BufferedWriter(new FileWriter(stockFile, true));
										for (Product product : productList) {
											if (product.getBarcode().equals(barcodeInput.getText())) {
												prodExists = true;
											}
										}
										try {
											if (prodExists == true) { //validation for adding product
												JOptionPane.showMessageDialog(null, "Error: product with this barcode already exists.");
											} else if (Integer.parseInt(barcodeInput.getText()) < 0 || Integer.parseInt(quantInput.getText()) < 0 || Double.parseDouble(origCostInput.getText().trim()) < 0 || Double.parseDouble(retPriceInput.getText().trim()) < 0) {
												JOptionPane.showMessageDialog(null, "Error: please enter non negative values for numbers.");
											} else if (barcodeInput.getText().equals("") || typeInput.getText().equals("") || brandInput.getText().equals("") || colourInput.getText().equals("") ||  quantInput.getText().equals("") || origCostInput.getText().equals("") || retPriceInput.getText().equals("")) {
												JOptionPane.showMessageDialog(null, "Error: all fields must be filled in.");
											} else if (barcodeInput.getText().length() != 6) {
												JOptionPane.showMessageDialog(null, "Error: barcode must be 6 digits.");
											} else if (buttonsInput.getText().equals("") && layoutInput.getSelectedIndex() != 0) {
												Keyboard keyboard = new Keyboard(Integer.parseInt(barcodeInput.getText().trim()), typeInput.getText().trim(), brandInput.getText().trim(), colourInput.getText().trim(), (String) connecInput.getSelectedItem(), Integer.parseInt(quantInput.getText().trim()), Double.parseDouble(String.format("%.2f", Double.parseDouble(origCostInput.getText().trim()))), Double.parseDouble(String.format("%.2f", Double.parseDouble(retPriceInput.getText().trim()))), (String) layoutInput.getSelectedItem());
												productList.add(keyboard);
												Collections.sort(productList, new ProductComparator());
												output.append(keyboard.toAdminString()+"\n");
												JOptionPane.showMessageDialog(null, "Keyboard added.");
											} else if (layoutInput.getSelectedIndex() == 0 && buttonsInput.getText().equals("") != true) {
												if (Integer.parseInt(buttonsInput.getText()) >= 0) {
													Mouse mouse = new Mouse(Integer.parseInt(barcodeInput.getText().trim()), typeInput.getText().trim(), brandInput.getText().trim(), colourInput.getText().trim(), (String) connecInput.getSelectedItem(), Integer.parseInt(quantInput.getText().trim()), Double.parseDouble(String.format("%.2f", Double.parseDouble(origCostInput.getText().trim()))), Double.parseDouble(String.format("%.2f", Double.parseDouble(retPriceInput.getText().trim()))), Integer.parseInt(buttonsInput.getText().trim()));
													productList.add(mouse);
													Collections.sort(productList, new ProductComparator());
													output.append(mouse.toAdminString()+"\n");
													JOptionPane.showMessageDialog(null, "Mouse added.");
												} else {
													JOptionPane.showMessageDialog(null, "Error: please enter non negative value for button amount.");
												}
											} else {
												JOptionPane.showMessageDialog(null, "Error: please fill either buttons OR layout box.");
											}
											output.close();
										} catch (NumberFormatException e1) {
											JOptionPane.showMessageDialog(null, "Error: some inputs expect integers. These cannot be negative.");
										}
									} catch (Exception e0) {
										e0.printStackTrace();
									}
								}
							});
							break;
						} else {
							if (tabbedPane.getTabCount() == 2) {
								try {
									tabbedPane.removeTabAt(1);
								}
								catch (Exception e1){
									e1.printStackTrace();
								}
							}
							
							for (int i = 0; i < productList.size(); i++) { //displaying products on customer table initially
								Product product = productList.get(i);
								String[] prodInfo = new String[4];
								prodInfo[0] = product.getBarcode();
								if (product instanceof Mouse) {
									prodInfo[1] = ((Mouse) product).toCustString();
								} else {
									prodInfo[1] = ((Keyboard) product).toCustString();
								}
								prodInfo[2] = ("£" + product.getRetaPrc());
								prodInfo[3] = product.getQuant();
								custModel.addRow(prodInfo);
								String[] clearLine = new String[] {" ", " ", " ", " "};
								custModel.addRow(clearLine);
							}
							
							tabbedPane.addTab(user.getName()+" (customer)", null, custTab, null);
							tabbedPane.setSelectedIndex(1);
							addButton.addActionListener(new ActionListener() { //adding an item to basket
								public void actionPerformed(ActionEvent e) {
									if (custProductTable.getSelectionModel().isSelectionEmpty() == false) {
										int row = custProductTable.getSelectedRow();
										if (custProductTable.getValueAt(row, 0).toString() != " ") {
											String quantity = JOptionPane.showInputDialog("How many of this product would you like to add to basket?");
											String barcode = custProductTable.getValueAt(row, 0).toString();
											for (Product product : productList) {
												if (product.getBarcode().equals(barcode)) {
													if (Integer.parseInt(product.getQuant()) < Integer.parseInt(quantity)) {
														JOptionPane.showMessageDialog(null, "Error: You have selected a quantity higher than the amount of stock. Please try again.");
													} else {
														boolean added = ((Customer) user).check_basket(product, Integer.parseInt(quantity));
														if (added == true) {
															JOptionPane.showMessageDialog(null, "Added to basket");
														} else {
															JOptionPane.showMessageDialog(null, "Error: You have selected a quantity higher than the amount of stock. Please try again.");
														}
													}
												}
											}
										} else {
											JOptionPane.showMessageDialog(null, "Error: Please select a row with a product.");
										}
									} else {
										JOptionPane.showMessageDialog(null, "Error: Please select a product to add to basket.");
									}
								}	
							});
							
							basketButton.addActionListener( new ActionListener() { //creating basket frame
								public void actionPerformed(ActionEvent e) {
									Basket basket = new Basket((Customer) user, productList, tabbedPane);
									basket.setVisible(true);
									tabbedPane.setVisible(false);
								}
							});
							break;	
						}
					}
						
				}
		        
			}	
		});
	}
}


