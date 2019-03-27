package contrast.contrast.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import contrast.contrast.model.News;
import contrast.contrast.utils.NewsPairComparator;

public class NewsCompare {


    private List<NewsPair> pairsOfNews;
    
    public NewsCompare () {
        pairsOfNews = new ArrayList<NewsPair>();
    }

    

    public void compare (Iterable<News> stories) {
        for (News storyA : stories) {
            for (News storyB : stories) {
                if (!storyA.equals(storyB)){
                    NewsPair pairNews = new NewsPair(storyA, storyB);
                    boolean alreadyIn = false;
                    for (NewsPair pair : pairsOfNews) {
                        if (pair.equalsNews(pairNews)) {
                            alreadyIn = true;
                            break;
                        }
                    }
                    if (!alreadyIn && pairNews.getTagsMatches() > 0) {
                        pairsOfNews.add(pairNews);    
                    }                          
                }              
            }
        }

        if (pairsOfNews.size() != 0) {
            Collections.sort(pairsOfNews, new NewsPairComparator());
            List<Theme> themes = new ArrayList<Theme>();
            Theme firstTheme = new Theme(pairsOfNews.get(0).getSimilarTags(), pairsOfNews.get(0).getFirstNews().getNews(), pairsOfNews.get(0).getSecondNews().getNews());
            themes.add(firstTheme);
            pairsOfNews.remove(0);

            List<NewsPair> newsRelated = new ArrayList<NewsPair>();
            for (News story : stories) {
                if (!firstTheme.getNewsList().contains(story)) {
                    NewsAndTags newsTags = new NewsAndTags(story);
                    Set<String> similarTags = new HashSet<String>(firstTheme.getTags());
                    similarTags.retainAll(newsTags.getTags());
                    if (similarTags.size() > 0) {
                        NewsPair pair = new NewsPair(story, similarTags);
                        newsRelated.add(pair);
                    }
                }                     
            }
            Collections.sort(newsRelated, new NewsPairComparator());
            System.out.println("TEMA");
            System.out.println("ETIQUETAS: " + String.join(",", firstTheme.getTags()));
            System.out.println("TITULARES: ");
            for (News story : firstTheme.getNewsList()) {
                System.out.println(story.getHeadline());
            }
            System.out.println("---------------------------");
            for (NewsPair pair : newsRelated) {
                System.out.println("NOTICIA:");
                System.out.println(pair.getFirstNews().getNews().getHeadline());
                System.out.println("TAGS IGUALES: " + pair.getTagsMatches() + " ::: " + String.join(",", pair.getSimilarTags()));
                System.out.println("******************************");
            }
        }
    }

    /*private Entry<Set<String>, Set<News>> checkExistanceInThemes (Set<String> tags) {
        for (Map.Entry<Set<String>, Set<News>> topic : themes.entrySet()) { //Comprueba si ese tema ya existe
            Set<String> relatedTags = topic.getKey();
            Set<String> tagsRetained = new HashSet<String>(relatedTags);

            if(tagsRetained.retainAll(tags)) { 
                return topic;
            }
        }
        return null;
    }
    Set<News> relatedNews = topic.getValue();
    relatedNews.add(storyA);
    relatedNews.add(storyB);
    relatedTags.addAll(similarTags);

    themes.remove(relatedTags);
    themes.putIfAbsent(relatedTags, relatedNews);

    public List<String> compareNews (Iterable<News> stories) {
        
        for (News storyA : stories) { //Construye etiquetas a partir de categories y nerTags
            Set<String> tagsA = buildTagsSet(storyA);
            for (News storyB : stories) {
                if (!storyA.equals(storyB)) {
                    Set<String> tagsB = buildTagsSet(storyB);
    
                    Set<String> similarTags = new HashSet<String>(tagsA);
                    Set<String> totalTags = new HashSet<String>(tagsA);
                    Set<String> differentTags = new HashSet<String>(tagsA);
                    totalTags.addAll(tagsB);
                    similarTags.retainAll(tagsB);
                    differentTags.removeAll(tagsB);
                    
                    if (!similarTags.isEmpty()) { //Si encuentra dos noticias similares
                        System.out.println("ETIQUETAS SIMILARES: " + String.join(", ", similarTags) + " *** NOTICIA 1: " + storyA.getHeadline() + " --- NOTICIA 2: " + storyB.getHeadline());
                        System.out.println("SIM: " + similarTags.size() + ". DIF: " + differentTags.size() + ". TOT: " + totalTags.size() + ". SIM/TOT: " + (double)similarTags.size()/totalTags.size());
                        System.out.println("*************************************************");
                        boolean added = false;
                        
                        Entry<Set<String>, Set<News>> entry = this.checkExistanceInThemes(tagsA);
                        if
                        if (!added) { //Si el tema no existe lo crea y anyade
                            Set<News> similarNews = new HashSet<News>();
                            similarNews.add(storyA);
                            similarNews.add(storyB);
                            themes.putIfAbsent(similarTags, similarNews);
                        }
                    }
                    else { //Noticias sueltas            
                        Set<String> randomTopic = new HashSet<String>();
                        randomTopic.add("RANDOM");
                        if (themes.containsKey(randomTopic)) {
                            Set<News> randomNews = themes.get(randomTopic);
                            randomNews.add(storyA);
                            randomNews.add(storyB);
                            themes.put(randomTopic, randomNews);
                        }
                        else {
                            Set<News> randomNews = new HashSet<News>();
                            randomNews.add(storyA);
                            randomNews.add(storyB);
                            themes.put(randomTopic, randomNews);
                        }
                        
                    }
                }
            }
        }
    
        List<String> ret = new ArrayList<String>(); //RESULTADOS
        for (Map.Entry<Set<String>, Set<News>> topic : themes.entrySet()) { 
            Set<String> tags = topic.getKey();
            Set<News> relatedNews = topic.getValue();
            String print = new String();
            print = "TEMA: " + String.join(", ", tags) + " ------ NOTICIAS: ";
            for (News news : relatedNews) {
                print += news.getHeadline() + "  **********  ";
            }
            ret.add(print);
        }
        return ret;
    }*/
}

