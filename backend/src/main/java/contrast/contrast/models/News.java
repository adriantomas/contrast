package contrast.contrast.models;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "news")
public class News {
    @Id
    @Field
    private String id;

    @Field
    private String newspaper;

    @Field
    private String headline;

    @Field
    private LocalDateTime date;

    @Field
    private String link;

    @Field
    private String descriptionPlain;

    @Field
    private String descriptionRaw;

    @Field
    private Set<String> categories;

    public News(String newspaper, String headline, LocalDateTime date, String link, String descriptionRaw,
            String descriptionPlain, Set<String> categories) {
        this.id = UUID.nameUUIDFromBytes(link.getBytes(StandardCharsets.UTF_8)).toString();
        this.newspaper = newspaper;
        this.headline = headline;
        this.date = date;
        this.link = link;
        this.descriptionRaw = descriptionRaw;
        this.descriptionPlain = descriptionPlain;
        if (categories == null)
            this.categories = new HashSet<String>();
        else
            this.categories = categories;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNewspaper(String newspaper) {
        this.newspaper = newspaper;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setDate(LocalDateTime date) {
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

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public String getId() {
        return this.id;
    }

    public String getNewspaper() {
        return this.newspaper;
    }

    public String getHeadline() {
        return this.headline;
    }

    public LocalDateTime getDate() {
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

    public Set<String> getCategories() {
        return this.categories;
    }

    @Override
    public String toString() {
        return "NOTICIA: [PERIODICO: " + this.newspaper + ", TITULAR: " + this.headline + ", FECHA: " + this.date
                + ", ENLACE: " + this.link + ", DESCRIPCION: " + this.descriptionPlain + ", CATEGORIAS: "
                + String.join(", ", this.categories) + "]";
    }
}