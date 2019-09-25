package com.example.demo;

import static org.springframework.http.HttpHeaders.USER_AGENT;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


class DemoSite {

    private static final int MAX_PAGES_TO_SEARCH = 10;
    private Set<String> pagesVisited = new HashSet<>();
    private List<String> pagesToVisit = new LinkedList<>();

    public void visit(String url)
    {
        pagesVisited.add(url);
        pagesToVisit.addAll(crawl(url));
        if (pagesVisited.size() < MAX_PAGES_TO_SEARCH && !pagesToVisit.isEmpty()) {
            visit(pagesToVisit.remove(0));
        }
    }

    private List<String> crawl(String url)
    {
        List<String> pagesToVisit = new LinkedList<>();

        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            Elements linksOnPage = htmlDocument.select("a[href]");
            for(Element link : linksOnPage) {
                pagesToVisit.add(link.absUrl("href"));
            }
        }
        catch(IOException ioe) {
            System.out.println("Error in out HTTP request " + ioe);
        }
        return pagesToVisit;
    }
}

