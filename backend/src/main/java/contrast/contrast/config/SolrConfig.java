package contrast.contrast.config;

import java.net.MalformedURLException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(
    basePackages = "contrast.contrast"
    )

@ComponentScan
public class SolrConfig {

    @Value("${spring.data.solr.host}") String solrURL;

    @Bean
    public SolrClient solrClient() throws MalformedURLException, IllegalStateException {
        return new HttpSolrClient.Builder("http://" + solrURL).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}
