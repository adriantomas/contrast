package contrast.contrast.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import contrast.contrast.model.News;

public interface NewsRepository extends SolrCrudRepository<News, String> {

    @Query("newspaper:*?0*")
    List<News> findByNewspaper(String newspaper);

    @Query("headline:*?0* OR categories:*?0* OR descriptionPlain:*?0*")
    Page<News> findByContent(String searchTerm, Pageable pageable);

    @Query(value= "headline:*?0* OR categories:*?0* OR descriptionPlain:*?0*")
    @Facet(fields = { "newspaper", "date", "categories" })
    FacetPage<News> findByHeadlineOrCategoriesOrDescriptionPlainAndFacetOnNewspaper(String newspaper, Pageable page);

    @Query(value= "headline:*?0* OR categories:*?0* OR descriptionPlain:*?0*")
    @Facet(fields = { "newspaper", "date", "categories" })
    FacetPage<News> findByHeadlineOrCategoriesOrDescriptionPlainAndFacetOnNewspaperAndDateAndCategories(String newspaper, LocalDateTime initialDate, LocalDateTime finalDate,  Pageable page);

  /*   {newspaper}/{initialDate}/{finalDate}/{categories}/{fragment}/{page} */

    @Query(value = "categories:?0")
    FacetPage<News> findByCategories(String categories, Pageable page);

    @Query(value= "*:*")
    @Facet(fields = { "newspaper", "date", "categories" }) 
    FacetPage<News> findAllFacetOnNewspaperAndDateAndCategories(Pageable page);

    
}