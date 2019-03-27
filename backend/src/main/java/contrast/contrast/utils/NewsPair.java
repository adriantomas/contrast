package contrast.contrast.utils;

import java.util.HashSet;
import java.util.Set;

import contrast.contrast.model.News;
import contrast.contrast.utils.NewsAndTags;

public class NewsPair {

    private NewsAndTags newsTagsA;
    private NewsAndTags newsTagsB;
    private Set<String> similarTags;
    private int tagsMatches;

    public NewsPair (News news, Set<String> tags) {
        this.newsTagsA = new NewsAndTags(news);
        this.similarTags = tags;
        this.tagsMatches = tags.size();
    }

    public NewsPair (News newsA, News newsTagsB) {
        this.newsTagsA = new NewsAndTags(newsA);
        this.newsTagsB = new NewsAndTags(newsTagsB);              
        this.similarTags = new HashSet<String>(this.newsTagsA.getTags());
        this.similarTags.retainAll(this.newsTagsB.getTags());
        this.tagsMatches = similarTags.size();
        
    }

    /*@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!NewsPair.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final NewsPair other = (NewsPair) obj;
        if (this.newsA != null && this.newsTagsB != null && other.newsA != null && other.newsTagsB != null) {
            if (this.newsA.equals(other.newsA) && this.newsTagsB.equals(other.newsTagsB)) {
                return true;
            }
            else if (this.newsA.equals(other.newsTagsB) && this.newsTagsB.equals(other.newsA)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }*/

    public boolean equalsNews(NewsPair pair) {
        if (this.newsTagsA != null && this.newsTagsB != null && pair.newsTagsA != null && pair.newsTagsB != null) {
            if (this.newsTagsA.getNews().equals(pair.newsTagsA.getNews()) && this.newsTagsB.getNews().equals(pair.newsTagsB.getNews())) {
                return true;
            }
            else if (this.newsTagsA.getNews().equals(pair.newsTagsB.getNews()) && this.newsTagsB.getNews().equals(pair.newsTagsA.getNews())) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public int getTagsMatches() {
        return this.tagsMatches;
    }

    public void setTagsMatches(int tags) {
        this.tagsMatches = tags;
    }
    public Set<String> getSimilarTags() {
        return this.similarTags;
    }

    public NewsAndTags getFirstNews() {
        return this.newsTagsA;
    }
    
    public NewsAndTags getSecondNews() {
        return this.newsTagsB;
    }
}
