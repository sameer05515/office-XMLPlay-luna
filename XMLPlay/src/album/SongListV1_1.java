package album;
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

public class SongListV1_1 {

    public static void main(String[] args) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            try {
                Document doc = builder.parse(new File("src/xml/album.v1.1.xml"));

                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath = xPathFactory.newXPath();

                // Find all album tabs starting at the root level
                System.out.println("---------Find all album tabs starting at the root level-------");
                XPathExpression xExpress = xPath.compile("/album-list/album");
                NodeList nl = (NodeList)xExpress.evaluate(doc.getDocumentElement(), XPathConstants.NODESET);
                for (int index = 0; index < nl.getLength(); index++) {

                    Node albumNode = nl.item(index);
                    // Find the title node that is a child of the albumNode
                    Node titleNode = (Node) xPath.compile("title").evaluate(albumNode, XPathConstants.NODE);
                    System.out.println(titleNode.getTextContent());

                }

                // Find all albums whose title is equal to " Sample Album "
                System.out.println("---------Find all albums whose title is equal to \" Sample Album \"-----------------");
                xExpress = xPath.compile("/album-list/album[title=' Sample Album ']");
                nl = (NodeList)xExpress.evaluate(doc.getDocumentElement(), XPathConstants.NODESET);
                for (int index = 0; index < nl.getLength(); index++) {

                    Node albumNode = nl.item(index);
                    Node titleNode = (Node) xPath.compile("title").evaluate(albumNode, XPathConstants.NODE);
                    System.out.println(titleNode.getTextContent());

                }

            } catch (SAXException | IOException | XPathExpressionException ex) {
                ex.printStackTrace();
            }
        } catch (ParserConfigurationException exp) {
            exp.printStackTrace();
        }
    }

}