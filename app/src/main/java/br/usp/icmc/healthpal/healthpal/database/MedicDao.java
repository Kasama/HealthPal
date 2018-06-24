package br.usp.icmc.healthpal.healthpal.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.provider.BaseColumns._ID;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicEntry.TABLE_NAME;

@Dao
public interface MedicDao {
    @Insert
    void insert(Medic medicine);
    @Insert
    void insertAll(Medic ... medicines);
    @Update
    void update(Medic medicine);
    @Delete
    void delete(Medic medicine);

    @Query("SELECT * FROM " + TABLE_NAME)
    List<Medic> getAll();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE " + _ID + " = :medicId")
    Medic getById(long medicId);
}
