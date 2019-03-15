package contrast.contrast.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import contrast.contrast.utils.NERTagger;

public class RSSParser {

    private String[] sources;

    public RSSParser() {
        sources = null;
        try {
            File resource = new ClassPathResource("/static/RSS.txt").getFile();
            String data = new String(Files.readAllBytes(resource.toPath()));
            sources = data.split("\\r?\\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<News> parseFeeds() {
        ArrayList<News> news = new ArrayList<News>();
        NERTagger tagger = new NERTagger();
        try {
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

                                    news.add(new News(feed.getTitle(), 
                                        entry.getTitle(), 
                                        entry.getPublishedDate(),
                                        entry.getLink(), 
                                        entry.getDescription().getValue(), 
                                        entry.getDescription().getValue().replaceAll("<[^>]*>", ""),  
                                        aux,
                                        tagger.getNERTags(entry.getDescription().getValue().replaceAll("<[^>]*>", "") + " " + entry.getTitle())));
                                }
                            }       
                    }
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }
}