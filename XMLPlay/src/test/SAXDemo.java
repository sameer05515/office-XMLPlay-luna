package test;
import javax.xml.xpath.*;
import org.xml.sax.InputSource;

public class SAXDemo {

    public static void main(String[] args) throws Exception {
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        InputSource xml = new InputSource("src/xml/input.xml");
        String health = (String) xpath.evaluate("/savedGame/player/stats/health", xml, XPathConstants.STRING);
        System.out.println(health);
    }

}