package Nathacia.uas.pendataanrs;

import android.os.Parcel;
import android.os.Parcelable;

public class Daftar implements Parcelable {
    private String id;
    private String nama;
    private String alamat;
    private String deskripsi;

    protected Daftar(Parcel in) {
        id = in.readString();
        nama = in.readString();
        alamat = in.readString();
        deskripsi = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeString(deskripsi);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Daftar> CREATOR = new Creator<Daftar>() {
        @Override
        public Daftar createFromParcel(Parcel in) {
            return new Daftar(in);
        }

        @Override
        public Daftar[] newArray(int size) {
            return new Daftar[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
