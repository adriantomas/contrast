package contrast.contrast.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import contrast.contrast.model.News;

public interface NewsRepositoryCustom {
    Page<News> findByHeadlineOrCategoriesOrDescriptionPlainAndFacetOnNewspaper(String fragment, Pageable page);
}