package br.usp.icmc.healthpal.healthpal.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.provider.BaseColumns._ID;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.MEDIC;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.TABLE_NAME;

@Dao
public interface MedicineDao {
    @Insert
    void insert(Medicine medicine);
    @Insert
    void insertAll(Medicine ... medicines);
    @Update
    void update(Medicine medicine);
    @Delete
    void delete(Medicine medicine);

    @Query("SELECT * FROM " + TABLE_NAME)
    List<Medicine> getAll();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE " + _ID + " = :medicineId")
    Medicine getById(long medicineId);

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE " + MEDIC + " = :medicId")
    Medicine getByMedicId(long medicId);
}
