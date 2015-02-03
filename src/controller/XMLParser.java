package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;



public class XMLParser {
	private static ArrayList<ArrayList<String>> colorGrid;
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();	

		DocumentBuilder builder = factory.newDocumentBuilder();
		Document dom = builder.parse("test.xml");
		dom.normalize();
		parseDocument(dom);




	}
	private static void parseDocument(Document dom){
		//Root
		colorGrid = new ArrayList();
		Element root = dom.getDocumentElement();
		clean(root);
		String name = root.getElementsByTagName("name").item(0).getTextContent();
		int size = Integer.parseInt(root.getElementsByTagName("size").item(0).getTextContent());
		NodeList grid = root.getElementsByTagName("grid").item(0).getChildNodes();
		for(int i=0; i<grid.getLength();i++){
			NodeList row = grid.item(i).getChildNodes();
			ArrayList<String> rowgrid = new ArrayList();
			for(int j=0; j<row.getLength(); j++){
				rowgrid.add(row.item(j).getTextContent());
			}
			colorGrid.add(rowgrid);
		}

		System.out.println();
	}
	public static void clean(Node node)
	{
		NodeList childNodes = node.getChildNodes();
		for (int n = 0; n < childNodes.getLength(); n++)
		{
			Node child = childNodes.item(n);
			short nodeType = child.getNodeType();
			if (nodeType == Node.ELEMENT_NODE)
				clean(child);
			else if (nodeType == Node.TEXT_NODE)
			{
				String trimmedNodeVal = child.getNodeValue().trim();
				if (trimmedNodeVal.length() == 0)
					node.removeChild(child);
				else
					child.setNodeValue(trimmedNodeVal);
			}
			else if (nodeType == Node.COMMENT_NODE)
				node.removeChild(child);
		}
	}

}
