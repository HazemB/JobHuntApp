package com.company;

import com.jaunt.*;
/**
 * Created by Developer on 2017-06-16.
 */
public class Job {


    private String title;
    private String source;
    private String url;
    UserAgent userAgent;
    Elements links;
    public Job(Element jobLink) throws NotFound {
        userAgent = new UserAgent();
        try {
            userAgent.visit(jobLink.getAt("href"));
        } catch (ResponseException e) {
            e.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }

        links = userAgent.doc.findEvery("<b class=jobtitle>");

        for(Element link : links) {
            System.out.println(link.innerText());            //print results

        }
        System.exit(0);
    }



}
