package test;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DOMDemo {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document xml = db.parse("src/xml/input.xml");

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        String health = (String) xpath.evaluate("/savedGame/player/stats/health", xml, XPathConstants.STRING);
        System.out.println(health);
        
        Node health1 = (Node) xpath.evaluate("/savedGame/player/items/item/amount", xml, XPathConstants.NODE);
        System.out.println(health1);
    }

}