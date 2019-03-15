package contrast.contrast.model;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "news") //Define el documento en Solr
public class News {
    @Id
    private String id;

    @Field
    private String newspaper;

    @Field
    private String headline;

    @Field
    private Date date;

    @Field
    private String link;

    @Field
    private String descriptionPlain;

    @Field
    private String descriptionRaw;

    @Field
    private List<String> categories;

    @Field
    private List<String> nerTags;

    public News(String newspaper, String headline, Date date, String link, String descriptionRaw, String descriptionPlain, ArrayList<String> categories, ArrayList<String> nerTags) {
        this.id = UUID.nameUUIDFromBytes(link.getBytes(StandardCharsets.UTF_8)).toString();
        this.newspaper = newspaper;
        this.headline = headline;
        this.date = date;
        this.link = link;
        this.descriptionRaw = descriptionRaw;
        this.descriptionPlain = descriptionPlain;
        if (categories == null)
            this.categories = new ArrayList<String>();
        else
            this.categories = categories;
        if (nerTags == null)
            this.nerTags = new ArrayList<String>();
        else
            this.nerTags = nerTags;
    }

    public void setNewspaper(String newspaper) {
        this.newspaper = newspaper;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescriptionPlain(String descriptionPlain) {
        this.descriptionPlain = descriptionPlain.replaceAll("\\<.*?>", "");
    }

    public void setDescriptionRaw(String descriptionRaw) {
        this.descriptionRaw = descriptionRaw;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setNerTags(List<String> nerTags) {
        this.nerTags = nerTags;
    }

    public String getNewspaper() {
        return this.newspaper;
    }

    public String getHeadline() {
        return this.headline;
    }

    public Date getDate() {
        return this.date;
    }

    public String getLink() {
        return this.link;
    }

    public String getDescriptionPlain() {
        return this.descriptionPlain;
    }

    public String getDescriptionRaw() {
        return this.descriptionRaw;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public List<String> getNerTags() {
        return this.nerTags;
    }

    @Override
    public String toString() {
        return "NOTICIA: [PERIODICO: " + this.newspaper + ", TITULAR: " + this.headline + ", FECHA: " + this.date
        + ", ENLACE: " + this.link + ", DESCRIPCION: " + this.descriptionPlain + ", CATEGORIAS: " + String.join(", ", this.categories) + ", NERTAGS: " + String.join(", ", this.nerTags) + "]";
    }
}