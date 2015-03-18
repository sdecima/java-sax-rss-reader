package org.sdecima.rssreader;

public interface RSSFeedStore {
    void clear();
    void add(RSSItem currentItem);
}
