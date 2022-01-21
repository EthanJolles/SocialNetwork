package com.solvd.socialNetwork.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Deprecated
public class DomParser {
    final static Logger LOGGER = LogManager.getLogger(DomParser.class);

    public static void parseDom(String fileName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("src/main/resources/" + fileName + ".xml");
            NodeList nodeList = document.getElementsByTagName(fileName);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getAttribute("id");
                    NodeList nodeList1 = element.getChildNodes();

                    for (int j = 0; j < nodeList1.getLength(); j++) {
                        Node node1 = nodeList1.item(j);

                        if (node1.getNodeType() == Node.ELEMENT_NODE) {
                            Element name = (Element) node1;
                            LOGGER.info(fileName + id + ": " + name.getTagName() + " = " + name.getTextContent());
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.info(e);
        }
    }
}
