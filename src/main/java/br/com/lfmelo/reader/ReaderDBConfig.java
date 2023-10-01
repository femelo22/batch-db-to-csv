package br.com.lfmelo.reader;

import br.com.lfmelo.domain.Product;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class ReaderDBConfig {

    @Bean
    public JdbcCursorItemReader<Product> readerDB(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Product>()
                .name("readerDB")
                .dataSource(dataSource)
                .sql("select * from produtos")
                .rowMapper(rowMapperProdutos()) // OU PODEMOS USAR --> .rowMapper(new BeanPropertyRowMapper<Product>(Product.class))
                .build();
    }



    private RowMapper<Product> rowMapperProdutos() {

        return new RowMapper<Product>() {

            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();

                product.setId(rs.getInt("id"));
                product.setNome(rs.getString("nome"));
                product.setDescricao(rs.getString("descricao"));
                product.setPreco(rs.getDouble("preco"));

                return product;
            }
        };
    }
}
