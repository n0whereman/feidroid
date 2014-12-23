package sk.stuba.fei.feidroid.analysis.simpleanalyzer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class StringArrayParser extends DefaultHandler {
	private SAXParserFactory factory;
	private SAXParser parser;
	private List<String> stringList;
	private String iteratorValue;
	private final String ITEM = "item";

	public StringArrayParser() {
		factory = SAXParserFactory.newInstance();
		try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	public List<String> parse(String filename) {
		stringList = new ArrayList<String>();

		try {
			ClassLoader loader = this.getClass().getClassLoader();
			InputStream is = loader.getResourceAsStream(filename);
			parser.parse(is, this);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringList;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
	    throws SAXException {
		if (ITEM.equals(qName)) {
			stringList.add(iteratorValue);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		iteratorValue = String.copyValueOf(ch, start, length).trim();
	}

}
