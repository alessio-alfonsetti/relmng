package adp.realmng.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import adp.realmng.utilities.FileUtilities;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class StandardInvoiceReport {

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

	/**
	 * Function to create a Standard Report 
	 */
	public void createStandardReport () {

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
	                                  connection);*/
		
		String sql = "";
		try {
			
			System.out.println("CONF.getPath(): "+CONF.getPath());
			
			sql = CONF.getPropertyString("invoices.select_for_report");
			
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
	}
}
