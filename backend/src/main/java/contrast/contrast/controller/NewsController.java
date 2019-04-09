package contrast.contrast.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import contrast.contrast.repository.NewsRepository;
import contrast.contrast.model.News;
import contrast.contrast.utils.NewsCompare;
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

    @GetMapping("/comparison")
    public void compare () {
        Iterable<News> stories = newsRepository.findAll();
        NewsCompare comparer = new NewsCompare();
        comparer.compare(stories);
    }
    

    @GetMapping("/data")
    public String getSth() {
        String data = new String();
        for (News story : newsRepository.findAll()) {
            data += story.toString();
        }
        return data;
    }

    @GetMapping("/data2")
    public List<News> getSthf() {
        //return newsRepository.findAll();
        return StreamSupport.stream(newsRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    /*@GetMapping("/feedentries")
    public String getEntry() {
        RSSParser parser = new RSSParser();
        String tal = parser.fullParse().toString();
        return tal;
    }*/

    @DeleteMapping("/dropSolr")
    public void dropSolr() {
        newsRepository.deleteAll();
    }

    /*
     * @GetMapping("/newspaper/{fragment}/{page}") public List<News>
     * findNewspaperByFragment(@PathVariable String fragment, @PathVariable int
     * page) { return newsRepository.findByNewspaper(fragment, PageRequest.of(page,
     * 2)).getContent(); }
     */

    @GetMapping("/newspaper/{fragment}")
    public List<News> findNewspaperByFragment(@PathVariable String fragment) {
        return newsRepository.findByNewspaper(fragment);
    }

    @GetMapping(value="/content/{fragment}/{page}")
    public List<News> getMethodName(@PathVariable String fragment, @PathVariable int page) {
        return newsRepository.findByHeadlineOrCategoriesOrDescriptionPlainAndFacetOnNewspaper(fragment, PageRequest.of(page, 8)).getContent();
    }

    @GetMapping(value="/content2/{fragment}/{page}")
    public FacetPage<News> getMethodName2(@PathVariable String fragment, @PathVariable int page) {
        return newsRepository.findByHeadlineOrCategoriesOrDescriptionPlainAndFacetOnNewspaper(fragment, PageRequest.of(page, 8));
    }
    

    /*
     * @GetMapping("/search/{searchTerm}/{page}") public List<News>
     * findBySearchTerm(@PathVariable String searchTerm, @PathVariable int page) {
     * return newsRepository.findByContent(searchTerm, PageRequest.of(page,
     * 2)).getContent(); }
     */

    /*
     * @GetMapping("/search/{searchTerm}") public List<News>
     * findBySearchTerm(@PathVariable String searchTerm) { return
     * newsRepository.findByContent(searchTerm); }
     */
}