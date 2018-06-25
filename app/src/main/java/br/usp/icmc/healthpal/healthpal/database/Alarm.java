package br.usp.icmc.healthpal.healthpal.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.AlarmEntry.INTERVAL;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.AlarmEntry.MEDICINE;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.AlarmEntry.START_TIME;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.AlarmEntry.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class Alarm implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long _id;
    @ColumnInfo(name = MEDICINE)
    private long medicine;
    @ColumnInfo(name = START_TIME)
    private String startTime;
    @ColumnInfo(name = INTERVAL)
    private int interval;

    public Alarm(long medicine, String startTime, int interval) {
        this.medicine = medicine;
        this.startTime = startTime;
        this.interval = interval;
    }

    public Alarm(Parcel in) {
        _id = in.readLong();
        medicine = in.readLong();
        startTime = in.readString();
        interval = in.readInt();
    }

    public static final Creator<Alarm> CREATOR = new Creator<Alarm>() {
        @Override
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(_id);
        parcel.writeLong(medicine);
        parcel.writeString(startTime);
        parcel.writeInt(interval);
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getMedicine() {
        return medicine;
    }

    public void setMedicine(long medicine) {
        this.medicine = medicine;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
