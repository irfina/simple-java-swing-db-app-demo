/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.irfin.cafe.mealcategory.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import net.irfin.cafe.mealcategory.entity.MealCategory;

/**
 *
 * @author Hansen, Irfin
 */
public class MealCategoryRepo {
    
    private final java.sql.Connection dbCon;

    public MealCategoryRepo(Connection c) {
        dbCon = c;
    }
    
    /**
     * Mencari dan mengembalikan objek MealCategory berdasarkan {@code id} yang
     * dilewatkan.
     * 
     * @param id
     * @return 
     */
    public Optional<MealCategory> getById(int id) throws SQLException {
        var sql = "SELECT id, name FROM meal_category WHERE id = " + id;
        try (var stmt = dbCon.createStatement()) {
            var resultSet = stmt.executeQuery(sql);
            
            // Cek apakah resultSet mengandung hasil (ada record yg ditemukan)?
            if (resultSet.next() == false)
                return Optional.empty();
            
            var hasil = new MealCategory();
            hasil.setId(resultSet.getInt(1));
            hasil.setName(resultSet.getString(2));
            
            return Optional.of(hasil);
        }
    }
    
    /**
     * Mengembalikan semua data Meal Category yang tersimpan.
     * @return 
     */
    public List<MealCategory> getAll() throws SQLException {
        var hasil = new LinkedList<MealCategory>();
        
        var sql = "SELECT id, name FROM meal_category ORDER BY name";
        
        // eksekusi query
        try (var stmt = dbCon.createStatement()) {
            var resultSet = stmt.executeQuery(sql);
            
            // iterasi hasil query, simpan ke objek MealCategory, lalu masukkan
            // objek MealCategory ke dalam linked-list.
            while (resultSet.next()) {
                var mc = new MealCategory();
                mc.setId(resultSet.getInt(1));
                mc.setName(resultSet.getString(2));
                
                hasil.add(mc);
            }
            
            return hasil;
        }
    }
    public void create(MealCategory mc) throws SQLException {
        var sql = "INSERT INTO meal_category (\"name\") VALUES (?)";
        
        try (var ps = dbCon.prepareStatement(sql)) {
            ps.setString(1, mc.getName());
            
            ps.executeUpdate();
        }
    }

    public void update(MealCategory mc) throws SQLException {
        var sql = "UPDATE meal_category SET \"name\" = ? WHERE id = " + mc.getId();
        
        try (var ps = dbCon.prepareStatement(sql)) {
            ps.setString(1, mc.getName());
            
            ps.executeUpdate();
        }
    }
}
