package contrast.contrast.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import contrast.contrast.model.News;

public interface NewsRepository extends SolrCrudRepository<News, String> {

    @Query(value = "newspaper:?0 AND date:?1 AND categories:?2 AND (descriptionPlain:?3 OR categories:?3 OR headline:?3)")
    @Facet(fields = { "newspaper", "date", "categories" })
    FacetPage<News> findByLotOfThings(String newspapers, String date, String categories, String fragment, Pageable page);

    @Query(value= "*:*")
    @Facet(fields = { "newspaper", "date", "categories" }) 
    FacetPage<News> findAllFacetOnNewspaperAndDateAndCategoriesOrderByDateDescOrderByHeadline(Pageable page);

}