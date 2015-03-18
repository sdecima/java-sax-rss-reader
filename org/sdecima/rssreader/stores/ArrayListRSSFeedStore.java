package org.sdecima.rssreader.stores;

import org.sdecima.rssreader.RSSFeedStore;
import org.sdecima.rssreader.RSSItem;

import java.util.ArrayList;

public class ArrayListRSSFeedStore implements RSSFeedStore {

    ArrayList<RSSItem> list = new ArrayList<RSSItem>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void add(RSSItem currentItem) {
        list.add(currentItem);
    }

    public ArrayList<RSSItem> getList() {
        return list;
    }
}
