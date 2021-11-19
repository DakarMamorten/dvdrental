package com.dvdrental.dao;

import com.dvdrental.connection.DBConnectionSingleton;
import com.dvdrental.model.Category;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements AbstractDAO<Category> {
    String data = "yyyy-MM-dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(data);
    private static final Logger LOGGER = Logger.getLogger(CategoryDAO.class);

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(data);
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Category category = new Category();
                    category.setCategoryID(resultSet.getInt(1));
                    category.setName(resultSet.getString(2));
                    category.setLocalDateTime(LocalDateTime.parse(resultSet.getString(3), formatter));
                    categories.add(category);
                }
            }
        } catch (SQLException exception) {
            LOGGER.error("Error: " + exception.getMessage());
        }
        return categories;
    }

    @Override
    public Category findById(Long id) {
        Category category = new Category();
        String sql = "SELECT * FROM category WHERE category_id = " + id;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                category.setCategoryID(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setLocalDateTime(LocalDateTime.parse(resultSet.getString(3), formatter));
            }
        } catch (SQLException exception) {
            LOGGER.error("Error: " + exception.getMessage());
        }
        return category;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM category WHERE category_id = " + id;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            LOGGER.error("Error: " + exception.getMessage());
        }
    }

    @Override
    public void save(Category entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(data);
        String formatDateTime = entity.getLocalDateTime().format(formatter);
        String sql = "INSERT INTO category(name,last_update) VALUES(" +" '" +  entity.getName() + "','"  + formatDateTime + "')";
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            LOGGER.error("Error: " + exception.getMessage());
        }
    }

}
