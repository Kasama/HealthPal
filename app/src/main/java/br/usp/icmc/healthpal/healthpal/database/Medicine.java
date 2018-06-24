package br.usp.icmc.healthpal.healthpal.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.DESCRIPTION;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.DOSAGE;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.MEDIC;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.NAME;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.TABLE_NAME;

@Entity(
        tableName = TABLE_NAME,
        foreignKeys = @ForeignKey(
                entity = Medic.class,
                parentColumns = "_id",
                childColumns = "medic"
        )
)
public class Medicine implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long _id;
    @ColumnInfo(name = NAME)
    private String name;
    @ColumnInfo(name = DESCRIPTION)
    private String description;
    @ColumnInfo(name = DOSAGE)
    private String dosage;
    @ColumnInfo(name = MEDIC)
    private long medic;

    public Medicine(String name, String description, String dosage, long medic) {
        this.name = name;
        this.description = description;
        this.dosage = dosage;
        this.medic = medic;
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

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public long getMedic() {
        return medic;
    }

    public void setMedic(long medic) {
        this.medic = medic;
    }

    public Medicine(Parcel in) {
        _id = in.readLong();
        name = in.readString();
        description = in.readString();
        dosage = in.readString();
        medic = in.readLong();
    }

    public static final Creator<Medicine> CREATOR = new Creator<Medicine>() {
        @Override
        public Medicine createFromParcel(Parcel in) {
            return new Medicine(in);
        }

        @Override
        public Medicine[] newArray(int size) {
            return new Medicine[size];
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
        parcel.writeString(dosage);
        parcel.writeLong(medic);
    }
}