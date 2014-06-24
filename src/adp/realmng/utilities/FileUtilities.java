package adp.realmng.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Properties;


/**
 * 
 * retrieves config file from JVM properties and
 * from resource stored into the classpath as XML properties files
 *  
 * @author Alessandro Mucci
 *  
 */
public class FileUtilities {
	
	// Class attributes
		//private static Logger sLogger = Logger.getLogger(FileConfig.class);
		private static HashMap<String, FileUtilities> sConfigFiles = new HashMap<String, FileUtilities>();
		
		
		// TODO instead of this let's use enum???
		private static final String PACKAGE_RESOURCE_XML = "PACKAGE_RESOURCE_XML";
		private static final String JVM_PROPERTY = "JVM_PROPERTY";
		
		
		// Instance attributes
		private String gIdentifier;
		private String gPath;
		private Properties gInstanceProperties;
		private String gTypeOfResource;
		
		
		
		/**
		 * factory building a FileConfig from a file passed as property
		 * to the JVM
		 * 
		 * @param pIdentifier property name
		 * @param pPath path identified by pIdentifier
		 * @return
		 */
		public static FileUtilities factoryConfigFileFromJVMProperties(String pIdentifier, String pPath)
		{
			if (sConfigFiles.get(pIdentifier) != null)
			{
				throw new IllegalArgumentException("Identifier <" + pIdentifier + "> is already in use.");
			}
			
			sConfigFiles.put(pIdentifier, new FileUtilities(pIdentifier, pPath, JVM_PROPERTY));
			
			return sConfigFiles.get(pIdentifier);
		}
		
		/**
		 * factory building a FileConfig from passed resource
		 * to the JVM
		 * 
		 * @param pResource resource name (and path)
		 * @return
		 */
		public static FileUtilities factoryConfigFileFromPackageResource(String pIdentifier, String pResourcePath)
		{
			if (sConfigFiles.get(pIdentifier) != null)
			{
				throw new IllegalArgumentException("Identifier <" + pIdentifier + "> is already in use.");
			}
			
			sConfigFiles.put(pIdentifier, new FileUtilities(pIdentifier, pResourcePath, PACKAGE_RESOURCE_XML));
			
			return sConfigFiles.get(pIdentifier);
		}	
		
		
		synchronized private static FileUtilities getFileConfig(String pIdentifier) 
		{
			return sConfigFiles.get(pIdentifier);
		}
		
		// Instance methods
		private FileUtilities(String pIdentifier, String pPath, String pTypeOfResource)
		{
			if (pIdentifier == null)
			{
				throw new IllegalArgumentException("pIdentifier is null (and is not supposed to be...)");
			}
			
			if (pPath == null)
			{
				throw new IllegalArgumentException("pPath is null (and is not supposed to be...)");
			}		
			
			if (   pTypeOfResource.equals(JVM_PROPERTY) == false 
				&& pTypeOfResource.equals(PACKAGE_RESOURCE_XML) == false)
			{
				throw new IllegalArgumentException("pTypeOfResource has to be in (" + JVM_PROPERTY + ", " + PACKAGE_RESOURCE_XML + ")");
			}
			
			gIdentifier = pIdentifier;
			gPath = pPath;
			gTypeOfResource = pTypeOfResource;
		}	
		
		// GETTERs
		public String getPath()
		{
			return gPath;
		}
		
		public String getIdentifier()
		{
			return gIdentifier;
		}
		
		public String getTypeOfResource()
		{
			return gTypeOfResource;
		}
		
		
		/**
		 * used to get a property represented as String
		 * 
		 * @return String representing the passed key
		 * 
		 * @throws InvalidPropertiesFormatException
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public String getPropertyString(String pKey) throws InvalidPropertiesFormatException, FileNotFoundException, IOException
		{
			lazyInit();
			
			if (gInstanceProperties.getProperty(pKey) == null)
			{
				throw new NullPointerException("key <" + pKey + "> doesn't exist");		}
			
			return gInstanceProperties.getProperty(pKey).trim();
		}


		/**
		 * 
		 * used to get a property represented as boolean
		 * 
		 * @param pKey key to be returned
		 * @return boolean representing the passed key
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public boolean getPropertyBoolean(String pKey) throws FileNotFoundException, IOException
		{
			lazyInit();
			String lBoolStr = gInstanceProperties.getProperty(pKey).trim().toLowerCase();
			if (lBoolStr.equals("true") == true || lBoolStr.equals("false") == true)
			{
				return Boolean.parseBoolean(lBoolStr);
			}
			
			throw new IllegalStateException("it was supposed to be 'true' or 'false': "+pKey);
		}	
		

		/**
		 * 
		 * used to get a property represented as integer
		 * 
		 * @param pKey key to be returned
		 * @return int representing the passed key
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public int getPropertyInteger(String pKey) throws FileNotFoundException, IOException
		{
			lazyInit();
			String lIntStr = gInstanceProperties.getProperty(pKey).trim();
			int lReturnValue;
			try
			{
				lReturnValue =  Integer.parseInt(lIntStr);
			}
			catch (NumberFormatException nfe) 
			{	
				throw new IllegalStateException("it was supposed to be an integer: "+pKey);
			}
			return lReturnValue;
		}	
		
		
		/**
		 * 
		 * used to get a property represented as double
		 * 
		 * @param pKey key to be returned
		 * @return double representing the passed key
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public double getPropertyDouble(String pKey) throws FileNotFoundException, IOException
		{
			lazyInit();
			String lDoubleStr = gInstanceProperties.getProperty(pKey).trim();
			double lReturnValue;
			try
			{
				lReturnValue =  Double.parseDouble(lDoubleStr);
			}
			catch (NumberFormatException nfe) 
			{	
				
				throw new IllegalStateException("it was supposed to be a double': "+pKey);
				
			}
			return lReturnValue;
		}	
		
		
		/**
		 * @return
		 * @throws InvalidPropertiesFormatException
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public Enumeration<Object> getPropertyKeys() throws InvalidPropertiesFormatException, FileNotFoundException, IOException
		{
			lazyInit();
			return gInstanceProperties.keys();
		}
		
		/**
		 * list of Keys in case it's needed in original insert sequence
		 * 
		 * @return
		 * @throws InvalidPropertiesFormatException
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
//		public Iterator<Object> getOriginalSortingPropertyKeysIterator() throws InvalidPropertiesFormatException, FileNotFoundException, IOException
//		{
//			lazyInit();
//			return gInstanceProperties.getOriginalSortingKeysIterator();
//		}	
		
		
		private void lazyInit() throws FileNotFoundException, IOException
		{
			// lazy Initialization for properties 
			if (gInstanceProperties == null)
			{
				if (gTypeOfResource.equals(JVM_PROPERTY) == true)
				{
					gInstanceProperties = new Properties();
					gInstanceProperties.load(new FileInputStream(gPath));				
				}
				else if (gTypeOfResource.equals(PACKAGE_RESOURCE_XML) == true)
				{
					gInstanceProperties = new Properties();
					gInstanceProperties.loadFromXML(FileUtilities.class.getClassLoader().getResourceAsStream(gPath));				
				}
				else
				{
					throw new IllegalStateException("pTypeOfResource has to be in (" + JVM_PROPERTY + ", " + PACKAGE_RESOURCE_XML + ")");
				}
			}	
		}
		
		
		

		/**
		 * force reloading of config file
		 */
		public void reloadConfiguration()
		{
			// setting gProperties = null at next call of getProperties
			// class will perform a new Config.xml load
			gInstanceProperties = null;
		}	

}