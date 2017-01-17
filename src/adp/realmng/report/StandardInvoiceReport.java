package adp.realmng.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import adp.realmng.model.Invoice;
import adp.realmng.utilities.FileUtilities;

public class StandardInvoiceReport {

	DefaultTableModel tableModel;
	
	SimpleJdbcTemplate template;

	DataSource dataSource;

	Connection connection;

	/**
	 * config file where are stored all sql statements
	 */
	private static final FileUtilities CONF = 	
			FileUtilities.factoryConfigFileFromPackageResource(StandardInvoiceReport.class+"", "resources/sql-queries.xml");

	public StandardInvoiceReport() {}
	
	/**
	 * 	Standard Constructor for Spring Context Initialisation
	 */
	public StandardInvoiceReport(SimpleJdbcTemplate template) {
		this.template = template;
	}

	
	private void TableModelData(Invoice invoice) {
        String[] columnNames = {"Id", "Name", "Department", "Email"};
        String[][] data = {
            {"111", "G Conger", " Orthopaedic", "jim@wheremail.com"},
            {"222", "A Date", "ENT", "adate@somemail.com"},
            {"333", "R Linz", "Paedriatics", "rlinz@heremail.com"},
            {"444", "V Sethi", "Nephrology", "vsethi@whomail.com"},
            {"555", "K Rao", "Orthopaedics", "krao@whatmail.com"},
            {"666", "V Santana", "Nephrology", "vsan@whenmail.com"},
            {"777", "J Pollock", "Nephrology", "jpol@domail.com"},
            {"888", "H David", "Nephrology", "hdavid@donemail.com"},
            {"999", "P Patel", "Nephrology", "ppatel@gomail.com"},
            {"101", "C Comer", "Nephrology", "ccomer@whymail.com"}
        };
        tableModel = new DefaultTableModel(data, columnNames);
    }
	
	
	/**
	 * Function to create a Standard Report 
	 */
	public void createStandardReport (Invoice invoice) {

		System.out.println("Stampo Report");
		
		JasperPrint jasperPrint = null;
        TableModelData(invoice);
        
        try {
            //JasperCompileManager.compileReportToFile("C:\\Users\\alfonsetti\\git\\relmng\\src\\resources\\sample-report.jrxml");
        	JasperCompileManager.compileReportToFile("resources/sample-report.jrxml");
        	//OutputStream file = new FileOutputStream(new File("/resources/sample-report.jrxml"));
        	
            //jasperPrint = JasperFillManager.fillReport("C:\\Users\\alfonsetti\\git\\relmng\\src\\resources\\sample-report.jasper", new HashMap(),
        	jasperPrint = JasperFillManager.fillReport("resources/sample-report.jasper", new HashMap(),
                    new JRTableModelDataSource(tableModel));
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
//        } catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//        	System.out.println("");
//			e.printStackTrace();
//		}
		
		
		/*
		//a new report
		JasperReportBuilder report = DynamicReports.report();
		/*report
		  .columns(
		      Columns.column("Customer Id", "id", DataTypes.integerType()),
		      Columns.column("First Name", "first_name", DataTypes.stringType()),
		      Columns.column("Last Name", "last_name", DataTypes.stringType()),
		      Columns.column("Date", "date", DataTypes.dateType()))
		  .title(//title of the report
		      Components.text("SimpleReportExample")
			  .setHorizontalAlignment(HorizontalAlignment.CENTER))
			  .pageFooter(Components.pageXofY())//show page number on the page footer
			  .setDataSource("SELECT id, first_name, last_name, date FROM customers", 
	                                  connection);* /
		
		String sql = "";
		try {
			
			System.out.println("CONF.getPath(): "+CONF.getPath());
			
			sql = CONF.getPropertyString("invoices.select_for_standard_report");
			
			System.out.println("sql: "+sql);
			
			MapSqlParameterSource parameters = new MapSqlParameterSource();
		    parameters.addValue("uuid", "000000000001");
		    parameters.addValue("partita_iva", "alessio.alfonsetti@gmail.com");
		    parameters.addValue("data_emissione", "Alessio");
		    parameters.addValue("descrizione", "Alfonsetti");
		    parameters.addValue("importo_totale", "2");
			
		    if (template ==  null)
		    {
		    	System.out.println("NULL template");
		    	return;
		    }
		    
		    List<Map<String, Object>> inserted = template.queryForList(sql, parameters);
		    
		    if (inserted ==  null)
		    {
		    	System.out.println("NULL res");
		    	return;
		    }
		    
		    Iterator<Map<String, Object>> iter = inserted.iterator();
		    
		    Collection result = null;
		    
		    while (iter.hasNext())
		    {
		    	Map<String, Object> obj = iter.next();
		    	
		    	if(!obj.isEmpty())
		    	{
		    		Set ks = obj.keySet();
		    		Iterator iterKs = ks.iterator();
		    		
		    		while (iterKs.hasNext())
		    		{
		    			result.add(obj.get(iterKs.next()));
		    		}
		    	}
		    }
		    
		    Iterator iterRs = result.iterator();
		    while(iterRs.hasNext())
		    {
		    	System.out.println("Res on the Collection: "+iterRs.next());
		    }
		    
		    report.columns(
					Columns.column("Id Fattura", "uuid", DataTypes.integerType()),
					Columns.column("Partita Iva", "partita_iva", DataTypes.stringType()),
					Columns.column("Descrizione", "descrizione", DataTypes.stringType()),
					Columns.column("Importo Totale", "importo_totale", DataTypes.stringType()),
					Columns.column("Data Emissione", "data_emissione", DataTypes.dateType()))
					.title(
							Components.text("SimpleReportExample")
							.setHorizontalAlignment(HorizontalAlignment.CENTER))
							.pageFooter(Components.pageNumber())
							.setDataSource(result)
							;
		    
		    //show the report
		    report.show();

			//export the report to a pdf file
			report.toPdf(new FileOutputStream("C:/report.pdf"));
			
		} catch (InvalidPropertiesFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DRException e) {
			e.printStackTrace();
		}
		*/
	}
	
}
