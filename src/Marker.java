import java.util.Date;


public class Marker {
    //brands: expo,shuttle art, arteza, u brands, bic
    public String brand;
    public String color;
    public int number;
    public double timeLeft;
    public long milliPurchased;
    public Date expectedDeath;
    public boolean capState;
    public double capTime;
    public double useTime;
    public double lowestPrice;
    public Date datePurchased;

    public Marker(String brand, String color) {
        this.brand = brand;
        this.color = color;
        milliPurchased = System.currentTimeMillis();
        datePurchased = new Date();
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public Date getDate() {
        return datePurchased;
    }

    public long getMilliPurchased() {
        return milliPurchased;
    }

    public float getLifeLength() {
        return (63113852 - (getMilliPurchased() - System.currentTimeMillis())/1000) / 63113852;
    }
}