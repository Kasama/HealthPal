package br.usp.icmc.healthpal.healthpal.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.provider.BaseColumns._ID;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.AlarmEntry.TABLE_NAME;

@Dao
public interface AlarmDao {
    @Insert
    void insert(Alarm medicine);
    @Insert
    void insertAll(Alarm... medicines);
    @Update
    void update(Alarm medicine);
    @Delete
    void delete(Alarm medicine);

    @Query("SELECT * FROM " + TABLE_NAME)
    List<Alarm> getAll();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE " + _ID + " = :alarmId")
    Alarm getById(long alarmId);
}
