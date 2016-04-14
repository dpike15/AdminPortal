package adminPortal;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Landing {
	final DefaultCategoryDataset datasetTotal = new DefaultCategoryDataset();
	final DefaultCategoryDataset datasetAverages = new DefaultCategoryDataset();
	final DefaultCategoryDataset datasetLabor = new DefaultCategoryDataset();
	final DefaultCategoryDataset datasetPass = new DefaultCategoryDataset();

	private JFrame frmAdminPortal;

	/**
	 * Launch the application.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	

	/**
	 * Create the application.
	 */
	public Landing(String path,String username,String password) {
		initialize(path,username,password);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String path,String username,String password) {
		frmAdminPortal = new JFrame();
		frmAdminPortal.setTitle("ADMIN PORTAL");
		frmAdminPortal.setVisible(true);
		frmAdminPortal.setBounds(100, 100, 600, 500);
		frmAdminPortal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminPortal.getContentPane().setLayout(null);

		JPanel panel2 = new JPanel();

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 584, 68);
		frmAdminPortal.getContentPane().add(panel_1);
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setLayout(null);

		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAdminPortal.dispose();
				AdminLogin frame = new AdminLogin();
			}
		});
		btnLogout.setBounds(495, 0, 89, 23);
		panel_1.add(btnLogout);
		btnLogout.setBackground(UIManager.getColor("Button.background"));
		btnLogout.setForeground(UIManager.getColor("Button.foreground"));

		JLabel lblFischerInternationalAdmin = new JLabel(
				"FISCHER INTERNATIONAL ADMIN PORTAL");
		lblFischerInternationalAdmin.setBounds(0, 0, 584, 68);
		panel_1.add(lblFischerInternationalAdmin);
		lblFischerInternationalAdmin
				.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblFischerInternationalAdmin
				.setHorizontalAlignment(SwingConstants.LEFT);
		lblFischerInternationalAdmin.setIcon(new ImageIcon(
				"C:\\Users\\DF\\Desktop\\banner_logo_fischer09.jpg"));
		lblFischerInternationalAdmin.setFont(new Font(
				"Copperplate Gothic Bold", Font.PLAIN, 14));
		lblFischerInternationalAdmin.setBackground(new Color(72, 209, 204));
		lblFischerInternationalAdmin.setForeground(new Color(240, 248, 255));

		final JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 66, 584, 396);
		frmAdminPortal.getContentPane().add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane, "name_203856514910501");

		JPanel panel_3 = new JPanel();

		tabbedPane.addTab("Database Totals", null, panel_3, null);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Cost Breakdown", null, panel_4, null);

		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Labor Hours", null, panel_5, null);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Modules Breakdown", null, panel_6, null);

		createDataset(path,username,password);

		JFreeChart barChart = ChartFactory.createBarChart3D("Database Totals",
				"Table", "Total", datasetTotal, PlotOrientation.VERTICAL, true,
				true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		panel_3.add(chartPanel);

		JFreeChart barChartAvg = ChartFactory.createBarChart3D(
				"Cost Breakdown", "Range", "Cost", datasetAverages,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanelAvg = new ChartPanel(barChartAvg);
		chartPanelAvg.setPreferredSize(new java.awt.Dimension(560, 367));
		panel_4.add(chartPanelAvg);

		JFreeChart barChartlabor = ChartFactory.createBarChart3D(
				"Labor Breakdown", "Range", "Hours", datasetLabor,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanelLabor = new ChartPanel(barChartlabor);
		chartPanelLabor.setPreferredSize(new java.awt.Dimension(560, 367));
		panel_5.add(chartPanelLabor);

		JFreeChart barChartPass = ChartFactory.createBarChart3D(
				"Modules Breakdown", "Modules", "Total", datasetPass,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanelPass = new ChartPanel(barChartPass);
		chartPanelPass.setPreferredSize(new java.awt.Dimension(560, 367));
		panel_6.add(chartPanelPass);
	}

	private void createDataset(String path,String username,String password) {
		final String users = "USERS";
		final String quotes = "QUOTES";
		final String hours = "HOURS";
		final String price = "Average Cost";
		final String minPrice = "Minimum Cost";
		final String maxPrice = "Maximum Cost";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			try {
				Connection conn = DriverManager
						.getConnection(
								"jdbc:mysql://"+path+"?autoReconnect=true&useSSL-false",
								username, // user
								// name
								password);

				Statement statement = conn.createStatement();
				String userCount = "select count(*)Total from users;";
				String quoteCount = "select count(*)Total from quotes;";

				ResultSet userResult = statement.executeQuery(userCount);
				userResult.next();

				int numUsers = userResult.getInt("Total");

				ResultSet quoteResult = statement.executeQuery(quoteCount);
				quoteResult.next();

				int numQuotes = quoteResult.getInt("Total");

				datasetTotal.addValue(numUsers, users, "Total");
				datasetTotal.addValue(numQuotes, quotes, "Total");

				String avgHours = "select avg(totalAllHours)AvgHours from quotes;";
				String avgRate= "select avg(servicesHourlyRate)Rate from quotes;";
				String minHours = "select min(totalAllHours)MinHours from quotes";
				String maxHours = "select max(totalAllHours)MaxHours from quotes";

				ResultSet avgHoursResult = statement.executeQuery(avgHours);
				avgHoursResult.next();
				int numAvgHours = (int) avgHoursResult.getDouble("AvgHours");

				ResultSet avgRateResult = statement.executeQuery(avgRate);
				avgRateResult.next();
				int numAvgRate = avgRateResult.getInt("Rate");

				ResultSet minHoursResult = statement.executeQuery(minHours);
				minHoursResult.next();
				int minHour = (int) minHoursResult.getInt("MinHours");
				ResultSet maxHoursResult = statement.executeQuery(maxHours);
				maxHoursResult.next();
				int maxHour = (int) maxHoursResult.getInt("MaxHours");

				datasetAverages.addValue(minHour * numAvgRate, minPrice, "Min");
				datasetAverages.addValue(numAvgHours* numAvgRate, price, "Avg");
				datasetAverages.addValue(maxHour * numAvgRate, maxPrice, "Max");

				String avgServiceRate = "select avg(servicesHourlyRate)Rate from quotes;";
				String minServiceRate = "select min(servicesHourlyRate)Rate from quotes";
				String maxServiceRate = "select max(servicesHourlyRate)Rate from quotes";

				ResultSet avgServiceRateResult = statement.executeQuery(avgServiceRate);
				avgServiceRateResult.next();
				int avgRate_1 = avgServiceRateResult.getInt("Rate");
				
				ResultSet minServiceRateResult = statement.executeQuery(minServiceRate);
				minServiceRateResult.next();
				int minRate = minServiceRateResult.getInt("Rate");
				
				ResultSet maxServiceRateResult = statement.executeQuery(maxServiceRate);
				maxServiceRateResult.next();
				int maxRate = maxServiceRateResult.getInt("Rate");

				datasetLabor.addValue(minRate, "Minimimum Labor Hours", "Min");
				datasetLabor.addValue(avgRate_1, "Average Labor Hours", "Avg");
				datasetLabor.addValue(maxRate, "Maximum Labor Hours", "Max");

				String pass = "select count(*) from quotes where modulesPasswordManagement=\'YES\';";
				String fed = "select count(*) from quotes where modulesFederation=\'YES\';";
				String prov = "select count(*) from quotes where modulesProvisioning=\'YES\';";
				String hpam = "select count(*) from quotes where modulesHPAM=\'YES\';";

				ResultSet pr = statement.executeQuery(pass);
				pr.next();
				int numPass = pr.getInt("count(*)");

				ResultSet fr = statement.executeQuery(fed);
				fr.next();
				int numFed = fr.getInt("count(*)");

				ResultSet provr = statement.executeQuery(prov);
				provr.next();
				int numProv = provr.getInt("count(*)");

				ResultSet hPam = statement.executeQuery(hpam);
				hPam.next();
				int numHpam = hPam.getInt("count(*)");

				datasetPass
						.addValue(numPass, "Password Management", "Included");
				datasetPass.addValue(numQuotes - numPass, "Password Management",
						"Not Included");

				datasetPass.addValue(numFed, "Federation", "Included");
				datasetPass.addValue(numQuotes- numFed, "Federation",
						"Not Included");

				datasetPass.addValue(numProv, "Provisioning", "Included");
				datasetPass.addValue(numQuotes - numProv, "Provisioning",
						"Not Included");

				datasetPass.addValue(numHpam, "HPAM", "Included");
				datasetPass.addValue(numQuotes - numHpam, "HPAM", "Not Included");

				conn.close();

			} catch (SQLException e1) {

			}
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}
}
