package contrast.contrast.utils;

import java.util.Comparator;
import contrast.contrast.utils.NewsPair;

public class NewsPairComparator implements Comparator<NewsPair> {
    public int compare (NewsPair np1, NewsPair np2) {
        return np2.getTagsMatches() - np1.getTagsMatches();
    }
}