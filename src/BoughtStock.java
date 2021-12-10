public class BoughtStock {
    public String name;
    public double purchasePrice;
    public double share;

    BoughtStock(){}

    BoughtStock(String name, double price, double share){
        this.name = name;
        purchasePrice = price;
        this.share = share;
    }
}
