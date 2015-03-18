package org.sdecima.rssreader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSFeedSaxHandler extends DefaultHandler {

    RSSFeedStore store;
	
    public RSSFeedSaxHandler(RSSFeedStore store) {
        this.store = store;
    }
	
	RSSItem currentItem;
	String currentElement;
	StringBuffer currentCharacters;
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if(qName.equalsIgnoreCase("item"))
			currentItem = new RSSItem();
		
		currentElement = qName;
		currentCharacters = new StringBuffer();
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(currentItem != null && currentElement != null) {
			if(
                currentElement.equalsIgnoreCase("guid") ||
				currentElement.equalsIgnoreCase("title") ||
				currentElement.equalsIgnoreCase("description") ||
				currentElement.equalsIgnoreCase("content:encoded") ||
				currentElement.equalsIgnoreCase("link") ||
				currentElement.equalsIgnoreCase("pubDate")
			)
				currentCharacters.append(ch, start, length);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equalsIgnoreCase("item")) {
			store.add(currentItem);
			currentItem = null;
		}
		
		if(currentElement != null && currentCharacters.length() > 0) {
            if(currentElement.equalsIgnoreCase("guid"))
                currentItem.setGuid(currentCharacters.toString());

			if(currentElement.equalsIgnoreCase("title"))
				currentItem.setTitle(currentCharacters.toString());
			
			if(currentElement.equalsIgnoreCase("description"))
				currentItem.setDescription(currentCharacters.toString());
			
			if(currentElement.equalsIgnoreCase("content:encoded"))
				currentItem.setContent(currentCharacters.toString());
				
			if(currentElement.equalsIgnoreCase("link"))
				currentItem.setLink(currentCharacters.toString());
			
			if(currentElement.equalsIgnoreCase("pubDate")) {
				
				SimpleDateFormat dateFormatterRssPubDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
				
				try {
					Date parse = dateFormatterRssPubDate.parse(currentCharacters.toString());
					currentItem.setPubDate(parse);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

    public RSSFeedStore getRSSFeedStore() {
        return store;
    }
}
