package werock.com.material.domain;

/**
 * Created by Kotchaphan Muangsan on 28/8/2558.
 */
public class Car {

    private String models;
    private String brand;
    private int image;

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
}
