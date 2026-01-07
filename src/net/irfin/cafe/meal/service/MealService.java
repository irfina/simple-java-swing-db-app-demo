/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.irfin.cafe.meal.service;

import java.sql.SQLException;
import java.util.Optional;

import net.irfin.cafe.app.exception.CafeAppException;
import net.irfin.cafe.app.util.DatabaseConnection;
import net.irfin.cafe.meal.entity.Meal;
import net.irfin.cafe.meal.repository.MealRepo;

/**
 * Service class for managing Meal entities.
 *
 * @author Hansen, Irfin
 */
public class MealService {

    private MealRepo repo;

    public MealService() {
        try {
            repo = new MealRepo(DatabaseConnection.getConnection());
        }
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Get a Meal by its ID.
     * 
     * @param id
     * @return
     * @throws CafeAppException
     */
    public Optional<Meal> getById(int id) throws CafeAppException {
        try {
            return repo.getById(id);
        }
        catch (SQLException e) {
            throw new CafeAppException("Gagal mendapatkan meal dengan ID " + id + ". Pesan: " + e.getMessage(), e);
        }
    }

    /**
     * Create a new Meal record in database.
     * 
     * @param entity the Meal entity to be created
     * @return the created Meal entity with generated ID
     * @throws CafeAppException if an error occurs during database operation
     */
    public Meal create(Meal entity) throws CafeAppException {
        try {
            return repo.create(entity);
        }
        catch (SQLException e) {
            throw new CafeAppException("Gagal membuat meal baru. Pesan: " + e.getMessage(), e);
        }
    }

    /**
     * Update an existing Meal record in database.
     * 
     * @param entity the Meal entity with updated values
     * @return the updated Meal entity
     * @throws CafeAppException if an error occurs during database operation
     */
    public Meal update(Meal entity) throws CafeAppException {
        try {
            return repo.update(entity);
        }
        catch (SQLException e) {
            throw new CafeAppException("Gagal mengupdate meal. Pesan: " + e.getMessage(), e);
        }
    }

}