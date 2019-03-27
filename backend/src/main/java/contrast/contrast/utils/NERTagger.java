package contrast.contrast.utils;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class NERTagger {

    private StanfordCoreNLP pipeline;
    private List<String> selectedTags;

    public NERTagger()  {
        Properties props = new Properties();
        try {
            props.load(IOUtils.readerFromString("StanfordCoreNLP-spanish.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        props.put("annotators", "tokenize,ssplit,pos,lemma,ner");
        props.setProperty("ner.useSUTime", "false");
        props.setProperty("ner.applyNumericClassifiers", "false");
        props.setProperty("ner.applyFineGrained", "false");
        pipeline = new StanfordCoreNLP(props);
        selectedTags = Arrays.asList("ORGANIZATION", "LOCATION", "PERSON"/*, "CITY", "COUNTRY"*/);
    }

    public ArrayList<String> getNERTags (String doc) {
        Set<String> nerTagsNotDup = new HashSet<String>();
        CoreDocument document = new CoreDocument(doc);
        pipeline.annotate(document);
        for (CoreEntityMention em : document.entityMentions()) 
            if (selectedTags.contains(em.entityType()))
                nerTagsNotDup.add(em.text());
        return new ArrayList<String>(nerTagsNotDup);
    }
}
