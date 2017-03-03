package category;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Category {

	public static void main(String[] args) {
		readAllCategories("");

	}

	private static void readAllCategories(String path) {

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			try {
				Document doc = builder.parse(new File("src/xml/category.xml"));
				XPathFactory xPathFactory = XPathFactory.newInstance();
				XPath xPath = xPathFactory.newXPath();
				// Find all album tabs starting at the root level
				System.out
						.println("---------Find all album tabs starting at the root level-------");
				XPathExpression xExpress = xPath.compile("/root");
				NodeList nl = (NodeList) xExpress.evaluate(
						doc.getDocumentElement(), XPathConstants.NODESET);
				for (int index = 0; index < nl.getLength(); index++) {
					Node rootNode = nl.item(index);
					// Find the title node that is a child of the category
					NodeList categoryList = (NodeList) xPath
							.compile("category").evaluate(rootNode,
									XPathConstants.NODESET);
					for (int categoryListindex = 0; categoryListindex < categoryList
							.getLength(); categoryListindex++) {
						Node categoryNode = categoryList
								.item(categoryListindex);
						String message = getCategory(xPath, categoryNode, 1);
						System.out.println(message);
					}
				}
			} catch (SAXException | IOException | XPathExpressionException ex) {
				ex.printStackTrace();
			}
		} catch (ParserConfigurationException exp) {
			exp.printStackTrace();
		}

	}

	private static String getCategory(XPath xPath, Node categoryNode, int depth)
			throws XPathExpressionException {

		String depthString = "";
		for (int i = 0; i < depth; i++) {
			depthString = depthString + "\t";
		}
		String categoryName = getCDATA(xPath, categoryNode, "name");

		String linkURL = getCDATA(xPath, categoryNode, "link-url");

		NodeList subCategoryList = getNodeList(xPath, categoryNode,
				"sub-category-list");
		String message = "";
		for (int i = 0; i < subCategoryList.getLength(); i++) {
			Node subCatListNode = subCategoryList.item(i);

			NodeList categoryList = (NodeList) xPath.compile("category")
					.evaluate(subCatListNode, XPathConstants.NODESET);
			for (int categoryListindex = 0; categoryListindex < categoryList
					.getLength(); categoryListindex++) {
				Node subCatNode = categoryList.item(categoryListindex);
				message = message + "\n" + depthString
						+ getCategory(xPath, subCatNode, depth + 1);
			}
		}
		return (depthString + "\n---------\nCategory : " + depthString
				+ "\n name : " + categoryName + depthString + "\n link-url : "
				+ linkURL + ((message != null && !message.trim()
				.equalsIgnoreCase("")) ? (depthString + "\n sub-category " + message)
				: ""))
				+ depthString + "\n###################";
	}

	private static String getCDATA(XPath xPath, Node node, String nodeChildName)
			throws XPathExpressionException {
		return ((String) xPath.compile(nodeChildName).evaluate(node,
				XPathConstants.STRING)).trim();

	}

	private static NodeList getNodeList(XPath xPath, Node parentNode,
			String nodeChildName) throws XPathExpressionException {
		NodeList categoryList = (NodeList) xPath.compile(nodeChildName)
				.evaluate(parentNode, XPathConstants.NODESET);
		return categoryList;
	}

}
