/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.irfin.cafe.mealcategory.service;

import net.irfin.cafe.mealcategory.entity.MealCategory;
import net.irfin.cafe.mealcategory.repository.MealCategoryRepo;
import net.irfin.cafe.app.util.DatabaseConnection;
import net.irfin.cafe.app.exception.CafeAppException;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Irfin, Hansen
 */
public class MealCategoryService {
    
    private MealCategoryRepo repo;

    public MealCategoryService() {
        try {
            repo = new MealCategoryRepo(DatabaseConnection.getConnection());
        }
        catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * 
     * @param namaKtgr
     * @throws CafeAppException 
     */
    public void create(String namaKtgr) throws CafeAppException {
        // create entity object
        var entity = new MealCategory();
        entity.setName(namaKtgr);

        // Panggil repo untuk nyimpan ke DB
        try {
            repo.create(entity);
        }
        catch (SQLException ex) {
            ex.printStackTrace(System.out);
            throw new CafeAppException("Error proses penyimpanan data", ex);
        }
    }
    
    public void update(int existingId, String newName) throws CafeAppException {
        // siapkan objek entity
        var entity = new MealCategory();
        entity.setName(newName);
        entity.setId(existingId);
        
        try {
            repo.update(entity);
        }
        catch (SQLException ex) {
            ex.printStackTrace(System.out);
            throw new CafeAppException("Error proses update data", ex);
        }
    }

    public List<MealCategory> getAll() throws CafeAppException {
        try {
            return repo.getAll();
        }
        catch (SQLException ex) {
            ex.printStackTrace(System.out);
            throw new CafeAppException("Error proses ambil data", ex);
        }
    }
}
