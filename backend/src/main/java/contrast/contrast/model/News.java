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

    //@Indexed(name = "newspaper", type = "string")
    @Field
    private String newspaper;

    //@Indexed(name = "headline", type = "string")
    @Field
    private String headline;

    //@Indexed(name = "date", type = "date")
    @Field
    private Date date;

    @Field
    private String link;

    //@Indexed(name = "descriptionPlain", type = "string")
    @Field
    private String descriptionPlain;

    //@Indexed(name = "descriptionRaw", type = "string")
    @Field
    private String descriptionRaw;

    //@Indexed(name = "categories", type = "string")
    @Field
    private List<String> categories;

    public News(String newspaper, String headline, Date date, String link, String descriptionRaw, String descriptionPlain, ArrayList<String> categories) {
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

    @Override
    public String toString() {
        return "NOTICIA: [PERIODICO: " + this.newspaper + ", TITULAR: " + this.headline + ", FECHA: " + this.date
        + ", ENLACE: " + this.link + ", DESCRIPCION: " + this.descriptionPlain + ", CATEGORIAS: " + String.join(", ", this.categories) + "]";
    }
}