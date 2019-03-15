package contrast.contrast.utils;

import java.util.HashSet;
import java.util.Set;

import contrast.contrast.model.News;
import contrast.contrast.utils.NewsAndTags;

public class NewsPair {

    private NewsAndTags newsA;
    private NewsAndTags newsB;
    private Set<String> similarTags;
    private int tagsMatches;

    public NewsPair (News newsA, News newsB) {
        this.newsA = new NewsAndTags(newsA);
        this.newsB = new NewsAndTags(newsB);

        similarTags = new HashSet<String>(this.newsA.getTags());
        similarTags.retainAll(this.newsB.getTags());
        tagsMatches = similarTags.size();
    }

    public int getTagsMatches() {
        return this.tagsMatches;
    }

    public Set<String> getSimilarTags() {
        return this.similarTags;
    }

    public NewsAndTags getFirstNews() {
        return this.newsA;
    }
    
    public NewsAndTags getSecondNews() {
        return this.newsB;
    }
}
