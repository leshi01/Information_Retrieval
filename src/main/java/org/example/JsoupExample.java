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
            Document document = Jsoup.connect("https://www.instagram.com/direct/t/109546087101389/").get();


            // Extract elements by tag name
            Elements links = document.getElementsByClass("-div xe8uvvx xdj266r x11i5rnm xat24cr x1mh8g0r xexx8yu x4uap5 x18d9i69 xkhd6sd x1h91t0o xkh2ocl x78zum5 xdt5ytf x13a6bvl x1eb86dx xhj03cu");

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
