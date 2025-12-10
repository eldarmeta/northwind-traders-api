package com.pluralsight.northwind.data;

import com.pluralsight.northwind.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAll() {
        String sql = """
            SELECT ProductID, ProductName, CategoryID, UnitPrice
            FROM Products
        """;

        return jdbcTemplate.query(sql, productRowMapper);
    }

    @Override
    public Product getById(int id) {
        String sql = """
            SELECT ProductID, ProductName, CategoryID, UnitPrice
            FROM Products
            WHERE ProductID = ?
        """;

        return jdbcTemplate.queryForObject(sql, productRowMapper, id);
    }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) ->
            new Product(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getInt("CategoryID"),
                    rs.getDouble("UnitPrice")
            );
}
