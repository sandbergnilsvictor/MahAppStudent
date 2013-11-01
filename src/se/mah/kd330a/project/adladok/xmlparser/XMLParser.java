package se.mah.kd330a.project.adladok.xmlparser;


import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParser {
		
	/**
	 * Getting XML DOM element
	 * @param XML string
	 * */
	public Document getDomElement(String xml){
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(xml));
		        doc = db.parse(is); 
			} catch (Exception e) {
				//Log.e("Error: ", e.getMessage());//
				return null;
			}
	        return doc;
	}
	
	/** Getting node value
	  * @param elem element
	  */
	 public final String getElementValue( Node elem ) {
	     Node child;
	     if( elem != null){
	         if (elem.hasChildNodes()){
	             for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
	                 if( child.getNodeType() == Node.TEXT_NODE  ){
	                    return child.getNodeValue();
	                 }
	             }
	         }
	     }
	     return "";
	 }
	 
	 /**
	  * Getting node value
	  * @param Element node
	  * @param key string
	  * */
	 public String getValue(Element item, String str) {		
			NodeList n = item.getElementsByTagName(str);		
			return this.getElementValue(n.item(0));
		}
}
