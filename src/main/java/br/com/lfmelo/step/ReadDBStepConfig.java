package br.com.lfmelo.step;

import br.com.lfmelo.domain.Product;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadDBStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step readDBStep(
            JdbcCursorItemReader<Product> readerDB,
            FlatFileItemWriter<Product> writerCsv
    ) {
        return stepBuilderFactory
                .get("readDBStep")
                .<Product, Product>chunk(1)
                .reader(readerDB)
                .writer(writerCsv)
                .build();
    }
}
