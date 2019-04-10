package contrast.contrast.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
                                LocalTime midnight = LocalTime.MIDNIGHT;
                                for (SyndEntry entry : feed.getEntries()) {
                                    Set<String> tags = new HashSet<String>();
                                    for (SyndCategory cat : entry.getCategories()) {
                                        tags.add(cat.getName());
                                    }
                                    tags.addAll(tagger.getNERTags(entry.getDescription().getValue().replaceAll("<[^>]*>", "") + " " + entry.getTitle()));  
                                    LocalDate date = entry.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                    LocalDateTime dateParsed = LocalDateTime.of(date, midnight);
                                    news.add(new News(feed.getTitle(), 
                                        entry.getTitle(), 
                                        dateParsed,
                                        entry.getLink(), 
                                        entry.getDescription().getValue(), 
                                        entry.getDescription().getValue().replaceAll("<[^>]*>", ""),  
                                        tags
                                        ));
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