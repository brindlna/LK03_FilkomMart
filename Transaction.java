package Praktikum_Pemrograman_Lanjut.LK03_FilkomMart;
import java.util.ArrayList;

public class Transaction {
    private String transactionId;
    private ArrayList<Product> items = new ArrayList<>();
    private ArrayList<Double> itemDiscounts = new ArrayList<>();

    public Transaction(String transactionId) {
        this.transactionId = transactionId;
    }

    public void addItem(Product item) {
        if (item.getStockQuantity() > 0) {
            double diskonSaatIni = item.calculateDiscount();
            
            items.add(item);
            itemDiscounts.add(diskonSaatIni);
            
            item.updateStock(-1);
        } else {
            System.out.println("[Gagal] Stok " + item.getName() + " habis!");
        }
    }

    public void addItem(Product item, int quantity) {
        if (item.getStockQuantity() >= quantity) {
            double diskonSaatIni = item.calculateDiscount();
            
            for (int i = 0; i < quantity; i++) {
                items.add(item);
                itemDiscounts.add(diskonSaatIni);
            }
            
            item.updateStock(-quantity, "Pembelian " + transactionId);
        } else {
            System.out.println("[Gagal] Stok " + item.getName() + " tidak cukup!");
        }
    }

    public double processSale() {
        double totalSemua = 0;
        ArrayList<String> sudahDiproses = new ArrayList<>();

        System.out.println("\n===============================================================");
        System.out.println("                 STRUK PENJUALAN: " + transactionId);
        System.out.println("===============================================================");
        System.out.printf("%-18s %-3s %-13s %-12s %-10s\n", 
                          "Produk", "Qty", "Harga Satuan", "Tot. Diskon", "Subtotal");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < items.size(); i++) {
            Product p = items.get(i);
            
            if (!sudahDiproses.contains(p.getName())) {
                int qty = 0;
                double totalDiskonProduk = 0;

                for (int j = 0; j < items.size(); j++) {
                    if (items.get(j).getName().equals(p.getName())) {
                        qty++;
                        totalDiskonProduk += itemDiscounts.get(j);
                    }
                }

                double hargaSatuan = p.getPrice();
                double subtotal = (hargaSatuan * qty) - totalDiskonProduk;
                totalSemua += subtotal;

                System.out.printf("%-18s %-3d Rp%,-12.0f -Rp%,-10.0f Rp%,-10.0f\n", 
                    p.getName(), qty, hargaSatuan, totalDiskonProduk, subtotal);

                sudahDiproses.add(p.getName());
            }
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("TOTAL BAYAR:                                  Rp%,12.2f\n", totalSemua);
        System.out.println("===============================================================");
        System.out.println("          Terima Kasih Telah Berbelanja di Filkom Mart!        ");
        System.out.println("===============================================================\n");
        
        return totalSemua;
    }
}