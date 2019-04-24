package contrast.contrast.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import contrast.contrast.repository.NewsRepository;
import contrast.contrast.model.News;
import contrast.contrast.utils.RSSParser;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @EventListener(ApplicationReadyEvent.class)
    @PostMapping("/populate")
    public void populateSolr() throws IllegalArgumentException {
        RSSParser parser = new RSSParser();
        newsRepository.saveAll(parser.parseFeeds());
    }

    @DeleteMapping("/dropSolr")
    public void dropSolr() {
        newsRepository.deleteAll();
    }

    @GetMapping("/content/{newspapers}/{initialDate}/{finalDate}/{tags}/{fragment}/{page}")
    public FacetPage<News> getNewsByAllParams(@PathVariable List<String> newspapers, @PathVariable String initialDate, @PathVariable String finalDate, @PathVariable List<String> tags, @PathVariable String fragment, @PathVariable int page) {
        String tagsQuery;
        if (tags.get(0).equals("*")) {
            tagsQuery = "*";
        }
        else {
            tagsQuery = "(" + '"' + StringUtils.join(tags, '"' + " AND " + '"') + '"' + ')';
            try {
                tagsQuery = java.net.URLDecoder.decode(tagsQuery, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {}
        }

        String newspapersQuery;
        if (newspapers.get(0).equals("*")) {
            newspapersQuery = "*";
        }
        else {
            newspapersQuery = "(" + '"' + StringUtils.join(newspapers, '"' + " OR " + '"') + '"' + ')';
            try {
                newspapersQuery = java.net.URLDecoder.decode(newspapersQuery, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {}
        }
           
        String fragmentQuery;
        if (!fragment.equals("*")) {
            try {
                fragment = java.net.URLDecoder.decode(fragment, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {}    
            fragmentQuery = "*" + '"' + fragment + '"' + "*";
        }
        else {
            fragmentQuery = fragment;
        }
        
        String dateQuery = "[" + LocalDateTime.of(LocalDate.parse(initialDate), LocalTime.MIN).toString() + ":00Z TO " + LocalDateTime.of(LocalDate.parse(finalDate), LocalTime.MAX).toString() + "Z]";
        return newsRepository.findByLotOfThings(newspapersQuery, dateQuery, tagsQuery, fragmentQuery, PageRequest.of(page, 9, new Sort(Sort.Direction.DESC, "date").and(new Sort(Sort.Direction.DESC, "headline"))));
    }

    @GetMapping(value="/content/{page}")
    public FacetPage<News> getNews(@PathVariable int page) {
        return newsRepository.findAllFacetOnNewspaperAndDateAndCategoriesOrderByDateDescOrderByHeadline(PageRequest.of(page, 9, new Sort(Sort.Direction.DESC, "date").and(new Sort(Sort.Direction.DESC, "headline"))));
    } 

    @GetMapping(value="/related/{tags}")
    public String getRelatedNews(@PathVariable List<String> tags) {
        String uri = "http://localhost:8983/solr/news/select?defType=edismax&q=categories%3A";
        String tagsQuery = uri + "(" + '"' + StringUtils.join(tags, '"' + "%20 OR %20" + '"') + '"' + ')';

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(tagsQuery, String.class);
        return result;
    }
}