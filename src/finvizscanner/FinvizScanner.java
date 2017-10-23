/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finvizscanner;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Scan finviz web page with filters set to scan stock with volume > 500k and 
 * average relative volume > 1.5
 * @author atlantis
 */
public class FinvizScanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException  {
    
    // Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        //String url = args[0];
//        String url = "https://biz.yahoo.com/p/";
        String url = "http://www.finviz.com/screener.ashx?v=111&f=sh_curvol_o500,sh_relvol_o1.5&o=-change";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");
//        Elements conameu = doc.select("conameu");
        for (Element link : links) {
             if (link.attr("abs:href").contains("quote")) {
                // print("a <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
                 print("- (%s)", trim(link.text(), 35));
           }
        }

        url = "http://eoddata.com/splits.aspx";
        print("Fetching %s...", url);

        Document doceodata = Jsoup.connect(url).get();
        Elements linkseodata = doceodata.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");
//        Elements conameu = doc.select("conameu");
        for (Element link : linkseodata) {
             if (link.attr("abs:href").contains("stock")) {
                // print("a <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
                 print("- (%s)", trim(link.text(), 35));
           }
        }

        
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    
}
