public class Item {
    private String name;
    private String price;
    private String stock;

    Item(String product, String cost, String info) {
        this.name = product;
        this.price = cost;
    }

    Item(String product) {
        this.name = product;
        this.price = null;
    }

    void setname(String product) {
        this.name = product;
    }

    void setprice(String cost) {
        this.price = cost;
    }

    String getname() {
        return this.name;
    }

    String getprice() {
        return this.price;
    }

    public String toString() {
        return "\n" + this.getname() + " " + this.getprice();
    }
}
