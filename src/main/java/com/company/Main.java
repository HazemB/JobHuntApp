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
    userAgent.doc.apply("engineer");            //apply form input (starting at first editable field)
    userAgent.doc.apply("Canada");
    userAgent.doc.submit("Find Jobs");         //click submit button labelled "Google Search"

    Elements links = userAgent.doc.findEvery("<h2 class=jobtitle>").findEvery("<a>");  //find search result links

    for(Element link : links) {
        System.out.println(link.getAt("href"));            //print results

    }

    try {
        userAgent.doc.nextPageLinkExists();

    } catch (IndexOutOfBoundsException e){
            System.out.println(e);
    }



    System.out.println(links.size());
}
}

