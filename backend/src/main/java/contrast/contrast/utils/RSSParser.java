package contrast.contrast.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;


import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.io.ClassPathResource;

import contrast.contrast.model.News;

public class RSSParser {

    private ArrayList<News> news;

    public RSSParser() {
        news = new ArrayList<News>();
    }

    public ArrayList<SyndEntry> fullParse() {
        ArrayList<SyndEntry> ret = new ArrayList<SyndEntry>();
        try {
            String[] sources = null;
            try {
                File resource = new ClassPathResource("/static/RSS.txt").getFile();
                String data = new String(Files.readAllBytes(resource.toPath()));
                sources = data.split("\\r?\\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (sources != null) {
                for (String rss : sources) {
                    try (XmlReader reader = new XmlReader(new URL(rss))) {
                        SyndFeed feed = new SyndFeedInput().build(reader);
                        for (SyndEntry entry : feed.getEntries()) {
                            ret.add(entry);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void parseFeeds() {
        try {
            String[] sources = null;
            try {
                File resource = new ClassPathResource("/static/RSS.txt").getFile();
                String data = new String(Files.readAllBytes(resource.toPath()));
                sources = data.split("\\r?\\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (sources != null) {
                for (String rss : sources) {
                    try (CloseableHttpClient client = HttpClients.createMinimal()){
                        HttpUriRequest request = new HttpGet(rss);
                        try (CloseableHttpResponse response = client.execute(request);
                            InputStream stream = response.getEntity().getContent()) {
                                SyndFeed feed = new SyndFeedInput().build(new XmlReader(stream));
                                for (SyndEntry entry : feed.getEntries()) {
                                    ArrayList<String> aux = new ArrayList<String>();
                                    for (SyndCategory cat : entry.getCategories()) {
                                        aux.add(cat.getName());
                                    }
                                    this.news.add(new News(feed.getTitle(), entry.getTitle(), entry.getPublishedDate(),
                                            entry.getLink(), entry.getDescription().getValue(), entry.getDescription().getValue().replaceAll("\\<.*?>/", ""),  aux));
                                } //String.join(", ", aux)
                            }       
                    }
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<News> getParsedFeeds() {
        return news;
    }

    public void printParsedFeeds() {
        for (News story : this.news) {
            System.out.println(story.toString());
        }
    }
}