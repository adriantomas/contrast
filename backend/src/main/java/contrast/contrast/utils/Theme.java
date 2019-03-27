package contrast.contrast.utils;

import java.util.HashSet;
import java.util.Set;

import contrast.contrast.model.News;

public class Theme {
    private Set<String> tags;
    private Set<News> newsList;

    public Theme (Set<String> tags, News storyA, News storyB) {
        this.tags = tags;
        newsList = new HashSet<News>();
        newsList.add(storyA);
        newsList.add(storyB);
    }

    public void addNews (News story) {
        newsList.add(story);
    }

    public void setTags (Set<String> tags) {
        this.tags = tags;
    }

    public void setNewsList (Set<News> newsList) {
        this.newsList = newsList;
    }

    public Set<String> getTags () {
        return this.tags;
    }

    public Set<News> getNewsList () {
        return this.newsList;
    }

}