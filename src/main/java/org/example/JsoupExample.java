package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class JsoupExample {
    public static void main(String[] args) {
        try {
            // Connect to a website and get the HTML
            Document document = Jsoup.connect("https://example.com/").get();


            // Extract elements by tag name
            Elements links = document.getElementsByTag("a");

            // Print each link's text and href attribute
            for (Element link : links) {
                System.out.println("Text: " + link.text());
                System.out.println("Href: " + link.attr("href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
