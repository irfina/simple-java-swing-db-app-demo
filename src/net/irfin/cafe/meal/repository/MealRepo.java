/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.irfin.cafe.meal.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import net.irfin.cafe.meal.entity.Meal;
import net.irfin.cafe.mealcategory.entity.MealCategory;

/**
 *
 * @author Hansen, Irfin
 */
public class MealRepo {
    
    private final java.sql.Connection dbCon;

    public MealRepo(Connection c) {
        dbCon = c;
    }
    
    /**
     * Get all Meal records from database.
     * 
     * @return
     * @throws SQLException 
     */
    public List<Meal> getAll() throws SQLException {
        var hasil = new LinkedList<Meal>();
        
        var sql =
            """
            SELECT M.id, M.name, M.notes, MC.id, MC.name
            FROM meal M INNER JOIN meal_category MC ON (M.meal_ctgr_id=MC.id)
            ORDER BY MC.name, M.name
            """;
        
        // Alternatif selain query join di atas:
        // Yaitu, kita utamakan query Meal saja, tdk perlu join ke MealCategory
        // lalu utk mendapatkan data MealCategory, saat kita looping (iterate) tiap hasil
        // query Meal, kita panggil lagi repo MealCategory utk query by ID.
        // Scr logika sangat simpel, tapi scr performance buruk dan sering disebut sebagai
        // N+1 query.
        
        // eksekusi query
        try (var stmt = dbCon.createStatement()) {
            var rs = stmt.executeQuery(sql);
            
            // iterasi hasil query
            while (rs.next()) {
                var m = new Meal();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
                m.setNotes(rs.getString(3));
                
                var ctgr = new MealCategory(rs.getInt(4), rs.getString(5));
                m.setCategory(ctgr);
                
                hasil.add(m);
            }
            
            return hasil;
        }
    }
    
    /**
     * Create a new Meal record in database.
     * 
     * @param m
     * @return
     * @throws SQLException
     */
    public Meal create(Meal m) throws SQLException {
        
        // insert ke tabel data dari objek m. Jika proses insert berhasil
        // maka field 'id' akan diberi nilai otomatis (auto-incr) oleh DB.
        // Nilai 'id' tsb kita assign ke objek m dan di-return.
        
        var sql = "INSERT INTO meal (name, notes, meal_ctgr_id) VALUES (?, ?, ?) RETURNING id";
        try (var ps = dbCon.prepareStatement(sql)) {
            ps.setString(1, m.getName());
            ps.setString(2, m.getNotes());
            ps.setInt(3, m.getCategory().getId());
            
            var rs = ps.executeQuery();
            if (rs.next())
                m.setId(rs.getInt(1));
            else
                throw new SQLException("Creating meal failed, no rows affected.");
        }
        
        return m;
    }
    
    /**
     * Update an existing Meal record in database.
     * 
     * @param existingId
     * @param newEntity
     * @return
     * @throws SQLException 
     */
    public Meal update(Meal newEntity) throws SQLException {
        var sql = "UPDATE meal SET name=?, notes=?, meal_ctgr_id=? WHERE id=?";
        try (var stmt = dbCon.prepareStatement(sql)) {
            stmt.setString(1, newEntity.getName());
            stmt.setString(2, newEntity.getNotes());
            stmt.setInt(3, newEntity.getCategory().getId());
            stmt.setInt(4, newEntity.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Updating meal failed, no rows affected.");
            }
        }
        
        return newEntity;
    }
    
    /**
     * Get a Meal by its ID.
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    public Optional<Meal> getById(int id) throws SQLException {
        var sql =
            """
            SELECT M.id, M.name, M.notes, MC.id, MC.name
            FROM meal M INNER JOIN meal_category MC ON (M.meal_ctgr_id=MC.id)
            WHERE M.id=
            """ + id;

        try (var stmt = dbCon.createStatement()) {
            var rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                var m = new Meal();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
                m.setNotes(rs.getString(3));
                
                var ctgr = new MealCategory(rs.getInt(4), rs.getString(5));
                m.setCategory(ctgr);
                
                return Optional.of(m);
            }
            else {
                return Optional.empty();
            }
        }
    }
}
