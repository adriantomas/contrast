package contrast.contrast.utils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashSet;

import contrast.contrast.model.News;

public class NewsAndTags {
    private News story;
    private Set<String> tags;

    public NewsAndTags (News story) {
        this.story = story;
        /* this.tags = new HashSet<String>(Stream.concat(story.getCategories().stream(), story.getNerTags().stream()).collect(Collectors.toList())); */
    }

    public News getNews () {
        return story;
    }

    public void setNews (News story) {
        this.story = story;
    }

    public Set<String> getTags () {
        return tags;
    }

    public void setTags (Set<String> tags) {
        this.tags = tags;
    }
}