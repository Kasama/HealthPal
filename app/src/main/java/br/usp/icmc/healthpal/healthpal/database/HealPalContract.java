package br.usp.icmc.healthpal.healthpal.database;

import android.provider.BaseColumns;

public class HealPalContract {
    public static final String AUTHORITY = "br.usp.icmc.healthpal.healthpal";

    public static final String DATABASE_NAME = "healthPal.db";

    public static final class MedicineEntry implements BaseColumns {
        public static final String TABLE_NAME = "medicines";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String MEDIC = "medic";
        public static final String DOSAGE = "dosage";
        public static final String LEAFLET_LINK = "leaflet";
    }

    public static final class MedicEntry implements BaseColumns {
        public static final String TABLE_NAME = "medics";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String PHONE = "phone";
    }

    public static final class AlarmEntry implements BaseColumns {
        public static final String TABLE_NAME = "alarms";
        public static final String MEDICINE = "medicine";
        public static final String START_TIME = "start_time";
        public static final String INTERVAL = "interval";
    }
}
