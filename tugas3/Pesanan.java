import java.io.*;
import java.util.ArrayList;

public class Pesanan {

    private ArrayList<MenuItem> daftarPesanan;

    public Pesanan() {
        daftarPesanan = new ArrayList<>();
    }

    public void tambahPesanan(MenuItem item) {
        daftarPesanan.add(item);
    }

    public double hitungTotal() {

        double total = 0;
        double persenDiskon = 0;

        for (MenuItem item : daftarPesanan) {

            if (item instanceof Diskon) {
                persenDiskon =
                        ((Diskon) item).getDiskon();
            }

            else {
                total += item.getHarga();
            }
        }

        total -= total * persenDiskon / 100;

        return total;
    }

    public void tampilkanStruk() {

        System.out.println("\n===== STRUK =====");

        for (MenuItem item : daftarPesanan) {
            item.tampilMenu();
        }

        System.out.println("----------------");
        System.out.println("Total = Rp" +
                hitungTotal());
    }

    public void simpanStruk(String fileName) {

        try (PrintWriter pw =
                     new PrintWriter(new FileWriter(fileName))) {

            pw.println("===== STRUK =====");

            for (MenuItem item : daftarPesanan) {
                pw.println(item.getNama() +
                        " - Rp" + item.getHarga());
            }

            pw.println("----------------");
            pw.println("Total = Rp" +
                    hitungTotal());

            System.out.println(
                    "Struk berhasil disimpan.");

        } catch (IOException e) {

            System.out.println(
                    "Gagal menyimpan struk.");
        }
    }
}