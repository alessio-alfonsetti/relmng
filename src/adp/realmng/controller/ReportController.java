package adp.realmng.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import adp.realmng.dao.InvoiceDaoImpl;

@Controller
@RequestMapping("/report/")
public class ReportController {

	/* Logger */
	Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	/* DAO configuration */
	Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");
	BeanFactory factory = new XmlBeanFactory(r);
	
	InvoiceDaoImpl invoicedao = (InvoiceDaoImpl)factory.getBean("InvoiceDao");
	
	/**
	 * Metodo per la generazione del report di una fattura.
	 * 
	 * @param invoice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pdf", method = {RequestMethod.GET, RequestMethod.POST}, params = "id")
    public ModelAndView generatePdfReport(/*@ModelAttribute("relationship-management")Invoice invoice,*/ 
    		ModelAndView modelAndView, @RequestParam("id") int id){
 
        logger.debug("--------------generate PDF report----------");
 
        System.out.println("Genero PDF Fattura 0");
        
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        
       // System.out.println("Genero PDF Fattura 1: "+invoicedao.findByInvoiceUuid(invoice.getUuid()));
        System.out.println("invoice id 0: "+id);
        /*System.out.println("invoice id: "+invoice.getUuid());
        System.out.println("invoice importo: "+invoice.getImporto());
        System.out.println("invoice iva: "+invoice.getIva());
        System.out.println("invoice importo totale: "+invoice.getImporto_totale());
        System.out.println("invoice partita iva: "+invoice.getPartita_iva());
        System.out.println("invoice data emissione: "+invoice.getData_emissione());
        System.out.println("invoice descrizione: "+invoice.getDescrizione());*/
        
        try {
        	
			List<Map<String, Object>> invoices = invoicedao.findById(id);
			
			Iterator<Map<String, Object>> iter = invoices.iterator();
			Collection<Map<String, Object>> invoicecoll = new ArrayList<Map<String, Object>>();
			
			while (iter.hasNext())
			{
				Map<String, Object> invoicemap = iter.next();
				
				System.out.println("iter next: "+invoicemap);
				
				invoicecoll.add(invoicemap);
			}

			Iterator<Map<String, Object>> itercoll = invoicecoll.iterator();
			
			while (itercoll.hasNext()) 
			{
				
				System.out.println("iter next: "+itercoll.next());
			}
			
			
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(invoicecoll);
			 
	        System.out.println("Genero PDF Fattura 2");
	        
	        parameterMap.put("dataSource", JRdataSource);
	 
	        System.out.println("Genero PDF Fattura 3");
	        
	        //pdfReport bean has ben declared in the jasper-views.xml file
	        modelAndView = new ModelAndView("pdfReport", parameterMap);
			
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Genero PDF Fattura 4");
        
        return modelAndView;
 
    }//generatePdfReport
}
