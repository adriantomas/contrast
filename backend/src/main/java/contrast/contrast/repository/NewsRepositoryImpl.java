package contrast.contrast.repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;

import contrast.contrast.model.News;

public class NewsRepositoryImpl implements NewsRepositoryCustom {
    private SolrOperations operations;
    private SolrTemplate solrTemplate;

    @Override
    public Page<News> findByHeadlineOrCategoriesOrDescriptionPlainAndFacetOnNewspaper(String fragment, Pageable page) {      
        String innerQuery = new SolrQuery()
        .setFacet(true)
        .addDateRangeFacet("date", Date.from(LocalDateTime.now().minusYears(10).toInstant(ZoneOffset.UTC)) , Calendar.getInstance().getTime(), "%2B1DAY")
        .addFacetField("newspaper", "categories")
        .setQuery("headline:*?0* OR categories:*?0* OR descriptionPlain:*?0*")
        .toQueryString();
        Query query = new SimpleQuery(new SimpleStringCriteria(innerQuery)).setPageRequest(page);
        return solrTemplate.queryForPage("News",query, News.class);
    }

    @Autowired
    public void setOperations(SolrOperations operations) {
        this.operations = operations;
    }
}