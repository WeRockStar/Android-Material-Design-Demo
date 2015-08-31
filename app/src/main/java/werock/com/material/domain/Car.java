package werock.com.material.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kotchaphan Muangsan on 28/8/2558.
 */
public class Car implements Parcelable {

    private String models;
    private String brand;
    private int image;
    public String description;
    public int category;
    public String tel;

    public Car() {
    }

    public Car(String models, String brand, int image) {
        this.models = models;
        this.brand = brand;
        this.image = image;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return this.category;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Car(Parcel parcel) {
        setModels(parcel.readString());
        setBrand(parcel.readString());
        setDescription(parcel.readString());
        setCategory(parcel.readInt());
        setTel(parcel.readString());
        setImage(parcel.readInt());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getModels());
        dest.writeString(getBrand());
        dest.writeString(getDescription());
        dest.writeInt(getCategory());
        dest.writeString(getTel());
        dest.writeInt(getImage());
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel source) {
            return new Car(source);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}
