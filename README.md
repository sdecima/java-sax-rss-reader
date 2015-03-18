# Basic Java RSS Reader using Java SAX

Just a basic implementation of SAX's DefaultHandler to parse a RSS 2 feed.

Doesn't depends on any external library, it only uses SAX (which is included in JavaSE since Java 5 and also on Android).

### Usage example

```java
ArrayListRSSFeedStore feedStore = new ArrayListRSSFeedStore();

RSSFeedReader.read("http://some.url.com/feed/", feedStore);

ArrayList<RSSItem> list = feedStore.getList();

for (RSSItem rssItem : list) {
  System.out.println("guid: " + rssItem.getGuid());
  System.out.println("title: " + rssItem.getTitle());
  System.out.println("description: " + rssItem.getDescription());
  System.out.println("link: " + rssItem.getLink());
  System.out.println("");
}
```

> *Disclaimer:* this is a very basic proof of concept implementation and will not parse all variations of RSS 2 feeds.
