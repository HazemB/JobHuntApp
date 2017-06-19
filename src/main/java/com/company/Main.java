package com.company;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jaunt.*;

import java.net.URLEncoder;
import java.util.List;

public class Main {


    public static void main(String[] args) {
	// write your code here
      // loadIndeed();

        try {
            testJaunt();
        } catch (JauntException e) {
            e.printStackTrace();
        }

    }


    private static void testJauntJobLink() {


    }



private static void testJaunt() throws JauntException {

    UserAgent userAgent = new UserAgent();         //create new userAgent (headless browser)
    userAgent.visit("https://ca.indeed.com/?r=us");          //visit google
    userAgent.doc.apply("Hardware Engineer");            //apply form input (starting at first editable field)
    userAgent.doc.apply("Canada");
    userAgent.doc.submit("Find Jobs");         //click submit button labelled "Google Search"

    System.out.println(userAgent.doc.getUrl());
    Elements links = userAgent.doc.findEvery("<h2 class=jobtitle>").findEvery("<a>");  //find search result links

    for(Element link : links) {
        System.out.println(link.getAt("href"));            //print results
        Job newJob = new Job(link);
    }

    System.out.println("DONE");

    boolean morePages = true;
    boolean first = true;


    while (morePages){
        links = userAgent.doc.findEvery("<div class=pagination>").findEvery("<a>");
        System.out.println(links.size());
        if (links.size() < 6 & !first) morePages = false;
        String nextPageURL = (returnlastLink(links).getAt("href"));
        userAgent.visit(nextPageURL);
        links = userAgent.doc.findEvery("<h2 class=jobtitle>").findEvery("<a>");
        for(Element link : links) {
            System.out.println(link.getAt("href"));            //print results

        }


    first = false;
    }




System.exit(0);
    links = userAgent.doc.findEvery("<div class=pagination>").findEvery("<a>");
    for(Element link : links) {
        System.out.println(link.getAt("href"));            //print results
        userAgent.visit(link.getAt("href"));
    }
    System.out.println("DONE2");

   links = userAgent.doc.findEvery("<h2 class=jobtitle>").findEvery("<a>");
    for(Element link : links) {
        System.out.println(link.getAt("href"));            //print results


    }
}


    private static Element returnlastLink(Elements links){
        List<Element> listOfLinks = links.toList();

        return listOfLinks.get(listOfLinks.size()-1);
    }
}

