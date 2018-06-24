package br.usp.icmc.healthpal.healthpal.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.DATABASE_NAME;

@android.arch.persistence.room.Database(
        entities = {Medicine.class, Medic.class},
        version = 1, exportSchema = false
)
public abstract class Database extends RoomDatabase {
    public abstract MedicDao medicDao();
    public abstract MedicineDao medicineDao();
    private static Database instance;
    public synchronized static Database getInstance(final Context context) {
        if (instance == null) {
            instance = buildDatabase(context);
        }
        return instance;
    }
    public static Database buildDatabase(final Context context) {
        return Room.databaseBuilder(
                context,
                Database.class,
                DATABASE_NAME
        ).build();
    }
}
