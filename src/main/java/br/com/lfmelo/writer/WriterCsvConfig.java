package br.com.lfmelo.writer;

import br.com.lfmelo.domain.Product;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class WriterCsvConfig {

    @Bean
    public FlatFileItemWriter<Product> writerCsv() {
        FlatFileItemWriter<Product> writer = new FlatFileItemWriter<Product>();
        writer.setResource(new FileSystemResource("C://Users/Luiz/Documents/Dev/csv_output_products.csv"));

        DelimitedLineAggregator<Product> aggregator = new DelimitedLineAggregator<>();
        BeanWrapperFieldExtractor<Product> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"id", "nome", "descricao", "preco"});
        aggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(aggregator);

        return writer;
    }
}
