package contrast.contrast.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Set;

import contrast.contrast.model.News;
import contrast.contrast.utils.NewsPairComparator;

public class NewsCompare {

    private Map<Set<String>, Set<News>> themes;

    private List<NewsPair> pairsOfNews;
    
    public NewsCompare () {
        themes = new HashMap<Set<String>, Set<News>>();
        pairsOfNews = new ArrayList<NewsPair>();
    }

    

    public void compare (Iterable<News> stories) {
        for (News storyA : stories) {
            for (News storyB : stories) {
                pairsOfNews.add(new NewsPair(storyA, storyB));
            }
        }
        Collections.sort(pairsOfNews, new NewsPairComparator());
        for (NewsPair pairNews : pairsOfNews) {
            System.out.println("PAREJA DE NOTICIAS");
            System.out.println("TITULAR 1: " + pairNews.getFirstNews().getNews().getHeadline());
            System.out.println("TITULAR 2: " + pairNews.getSecondNews().getNews().getHeadline());
            System.out.println("ETIQUETAS EN COMUN: " + pairNews.getTagsMatches() + " ::: " + String.join(",", pairNews.getSimilarTags()));
            System.out.println("******************************");
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

