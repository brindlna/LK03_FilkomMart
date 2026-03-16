package Praktikum_Pemrograman_Lanjut.LK03_FilkomMart;

public abstract class Product {
    private String productId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(String productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public abstract double calculateDiscount();

    public String getProductInfo() {
        return String.format("ID: %-5s | Nama: %-20s | Harga: Rp%,10.2f | Stok: %-3d", 
                productId, name, price, stockQuantity);
    }

    public void updateStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void updateStock(int quantity, String reason) {
        this.stockQuantity += quantity;
        System.out.println("[INFO] Stok " + name + " diperbarui: " + reason);
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}

class FoodProduct extends Product {
    private String expiryDate;

    public FoodProduct(String id, String name, double price, int stock, String expiry) {
        super(id, name, price, stock);
        this.expiryDate = expiry;
    }

    @Override
    public double calculateDiscount() {
        return (getStockQuantity() > 10) ? getPrice() * 0.10 : 0;
    }
}

class ElectronicProduct extends Product {
    private String warrantyPeriod;

    public ElectronicProduct(String id, String name, double price, int stock, String warranty) {
        super(id, name, price, stock);
        this.warrantyPeriod = warranty;
    }

    @Override
    public double calculateDiscount() {
        double discount = getPrice() * 0.05; 
        if (getPrice() > 500000) discount += getPrice() * 0.02; 
        return discount;
    }
}

class ClothingProduct extends Product {
    private String size, brand;

    public ClothingProduct(String id, String name, double price, int stock, String size, String brand) {
        super(id, name, price, stock);
        this.size = size;
        this.brand = brand;
    }

    @Override
    public double calculateDiscount() {
        if (size.equalsIgnoreCase("L") || size.equalsIgnoreCase("XL")) {
            return getPrice() * 0.15;
        }
        return 0;
    }

    @Override
    public String getProductInfo() {
        return super.getProductInfo() + " | Size: " + size;
    }
}