package contrast.contrast.services;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class NERTagger {

    private StanfordCoreNLP pipeline;
    private List<String> selectedTags;

    public NERTagger() {
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
        this.pipeline = new StanfordCoreNLP(props);
        this.selectedTags = Arrays.asList("ORGANIZATION", "LOCATION", "PERSON"/* , "CITY", "COUNTRY" */);
    }

    public Set<String> getNERTags(String doc) {
        Set<String> nerTags = new HashSet<String>();
        CoreDocument document = new CoreDocument(doc);
        this.pipeline.annotate(document);
        for (CoreEntityMention em : document.entityMentions())
            if (selectedTags.contains(em.entityType()))
                nerTags.add(em.text());
        return nerTags;
    }
}