package com.reusecompany.oslc.utils;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

/**
 * This is a helper class for serilizing Documents in XML.
 */
public class DOMToString {
			public static String printXML(Document doc) {
				OutputFormat  outFormat    = null;
				outFormat = new OutputFormat();
				outFormat.setEncoding("UTF-8");
				outFormat.setVersion("1.0");
				outFormat.setIndenting(true);
				outFormat.setIndent(6);
				return  printXML(doc, outFormat);  	 
			}

	    public static String printXML(Document doc,OutputFormat outFormat) {
	        StringWriter  strWriter    = new StringWriter();
	        XMLSerializer serializer   = new XMLSerializer(strWriter,outFormat);
	            try {
	                serializer.serialize(doc);
	                strWriter.close();
	            } catch (IOException e) {
	                return "";
	            }		        
		     return  strWriter.toString();  	 
		}
	    public static String print(Document doc){			
	    	StringWriter writer = new StringWriter();
		    try {
				TransformerFactory.newInstance().newTransformer().transform( new DOMSource(doc),  new StreamResult(writer));
			} catch (Exception e){
			}
			return writer.toString();
	    }
	}

