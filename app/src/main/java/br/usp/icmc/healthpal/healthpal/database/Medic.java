package br.usp.icmc.healthpal.healthpal.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicEntry.DESCRIPTION;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicEntry.NAME;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicEntry.PHONE;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicEntry.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class Medic implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long _id;
    @ColumnInfo(name = NAME)
    private String name;
    @ColumnInfo(name = DESCRIPTION)
    private String description;
    @ColumnInfo(name = PHONE)
    private String phone;

    public Medic(String name, String description, String phone) {
        this.name = name;
        this.description = description;
        this.phone = phone;
    }

    public Medic(Parcel in) {
        _id = in.readLong();
        name = in.readString();
        description = in.readString();
        phone = in.readString();
    }

    public static final Creator<Medic> CREATOR = new Creator<Medic>() {
        @Override
        public Medic createFromParcel(Parcel in) {
            return new Medic(in);
        }

        @Override
        public Medic[] newArray(int size) {
            return new Medic[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(_id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(phone);
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
