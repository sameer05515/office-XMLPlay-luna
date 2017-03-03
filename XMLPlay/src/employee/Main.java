package employee;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
 
public class Main {
    public static void main(String[] args) {
 
        try {
            FileInputStream file = new FileInputStream(new File("src/xml/employees.xml"));
                 
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
             
            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
             
            Document xmlDocument = builder.parse(file);
 
            XPath xPath =  XPathFactory.newInstance().newXPath();
 
            System.out.println("*************************");
            String expression = "/Employees/Employee[@emplid='3333']/email";
            System.out.println(expression);
            String email = xPath.compile(expression).evaluate(xmlDocument);
            System.out.println(email);
 
            System.out.println("*************************");
            expression = "/Employees/Employee/firstname";
            System.out.println(expression);
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
 
            System.out.println("*************************");
            expression = "/Employees/Employee[@type='admin']/firstname";
            System.out.println(expression);
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
 
            System.out.println("*************************");
            expression = "/Employees/Employee[@emplid='2222']";
            System.out.println(expression);
            Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
            if(null != node) {
                nodeList = node.getChildNodes();
                for (int i = 0;null!=nodeList && i < nodeList.getLength(); i++) {
                    Node nod = nodeList.item(i);
                    if(nod.getNodeType() == Node.ELEMENT_NODE)
                        System.out.println(nodeList.item(i).getNodeName() + " : " + nod.getFirstChild().getNodeValue()); 
                }
            }
             
            System.out.println("*************************");
 
            expression = "/Employees/Employee[age>40]/firstname";
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            System.out.println(expression);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
         
            System.out.println("*************************");
            expression = "/Employees/Employee[1]/firstname";
            System.out.println(expression);
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
            System.out.println("*************************");
            expression = "/Employees/Employee[position() <= 2]/firstname";
            System.out.println(expression);
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
 
            System.out.println("*************************");
            expression = "/Employees/Employee[last()]/firstname";
            System.out.println(expression);
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
 
            System.out.println("*************************");
            expression = "/Employees/Employee[last()]/life-achievements";
            System.out.println(expression);
            String lifeAchievement = (String) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.STRING);
            System.out.println("lifeAchievement == "+lifeAchievement.trim());
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                System.out.println(nodeList.item(i).getFirstChild().getTextContent()); 
//            }
 
            System.out.println("*************************");
            //expression = "/Employees/Employee[last()]/life-achievements";
            //System.out.println(expression);
           // String lifeAchievement = (String) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.STRING);
//            System.out.println("lifeAchievement == "+lifeAchievement.trim());
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                System.out.println(nodeList.item(i).getFirstChild().getTextContent()); 
//            }
            XPathExpression xExpress = xPath.compile("/Employees/Employee");
            NodeList nl = (NodeList)xExpress.evaluate(xmlDocument, XPathConstants.NODESET);
            for (int index = 0; index < nl.getLength(); index++) {

                Node albumNode = nl.item(index);
                // Find the title node that is a child of the albumNode
                String lifeachievementNode = (String) xPath.compile("life-achievements").evaluate(albumNode, XPathConstants.STRING);
                String emailNode = (String) xPath.compile("email").evaluate(albumNode, XPathConstants.STRING);
                System.out.println("lifeachievementNode == "+lifeachievementNode.trim()+" email "+emailNode.trim());
                

            }
 
            System.out.println("*************************");
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }       
    }
}