
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author developer
 */
public class ReadHTMLData {

    //private HTTPUrlConn httpURLConn;
    public static void main(String[] args) {
        HTTPUrlConn httpURLConn;
        //httpURLConn = new HTTPUrlConn("https://alvinalexander.com/blog/post/java/java-how-read-from-url-string-text");
        //httpURLConn = new HTTPUrlConn("https://play.google.com/store/apps/details?id=com.theeproduction.bdresults");
        httpURLConn = new HTTPUrlConn("https://play.google.com/store/apps/details?id=me.apphive.bestpixnormalgp");
        String htmlData;
        htmlData = httpURLConn.onRead();
        //System.out.println("DATA: " + htmlData);
        Document document = Jsoup.parse(htmlData);
        //Element link = document.select("div.expand-page").first();
        Elements detailsSectionContents = document.select("div.details-section-contents");
        //System.out.println("DATA: " + detailsSectionContents);
        Elements authorName = document.select("span.author-name");
        Elements reviewDate = document.select("span.review-date");
        for (Element element : reviewDate) {
            System.out.println(element.text());
        }
    }
}
/*
for( Element element : doc.select("img") ) // Select all 'img' tags
{
    Element divGuarantee = element.parent(); // Get parent element of 'img'
    Element divProgress = divGuarantee.parent(); // Get parent of parent etc.
}
*/
