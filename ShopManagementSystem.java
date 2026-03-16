package Praktikum_Pemrograman_Lanjut.LK03_FilkomMart;

public class ShopManagementSystem {
    public static void main(String[] args) {
        Product[] catalog = {
            new FoodProduct("F01", "Roti Tawar", 15000, 11, "2026-04-01"),
            new FoodProduct("F02", "Susu UHT", 20000, 5, "2026-05-10"),
            new ElectronicProduct("E01", "Mouse Wireless", 150000, 10, "1 Year"),
            new ElectronicProduct("E02", "Keyboard Mech", 600000, 3, "2 Years"),
            new ClothingProduct("C01", "Kaos Polos L", 75000, 15, "L", "Filkom-Wear"),
            new ClothingProduct("C02", "Kaos Polos S", 75000, 10, "S", "Filkom-Wear")
        };

        System.out.println("=== KATALOG PRODUK FILKOM MART ===");
        for (Product p : catalog) {
            System.out.println(p.getProductInfo());
        }

        Transaction trx1 = new Transaction("TRX-001");
        trx1.addItem(catalog[0], 2); 
        trx1.addItem(catalog[3], 1); 
        double total1 = trx1.processSale();

        Transaction trx2 = new Transaction("TRX-002");
        trx2.addItem(catalog[4], 2); 
        trx2.addItem(catalog[5], 1); 
        double total2 = trx2.processSale();

        Transaction trx3 = new Transaction("TRX-003");
        trx3.addItem(catalog[0], 1); 
        trx3.addItem(catalog[2], 1); 
        double total3 = trx3.processSale();

        System.out.println("==============================================");
        System.out.println("          LAPORAN PENJUALAN HARIAN");
        System.out.println("==============================================");
        System.out.printf("Total Pendapatan      : Rp%,15.2f\n", (total1 + total2));
        System.out.println("Status                : CLOSED");
        System.out.println("==============================================");
    }
}