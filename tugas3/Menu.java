import java.io.*;
import java.util.ArrayList;

public class Menu {

    private ArrayList<MenuItem> daftarMenu;

    public Menu() {
        daftarMenu = new ArrayList<>();
    }

    public void tambahMenu(MenuItem item) {
        daftarMenu.add(item);
    }

    public void tampilkanMenu() {

        if (daftarMenu.isEmpty()) {
            System.out.println("Menu masih kosong.");
            return;
        }

        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarMenu.get(i).tampilMenu();
        }
    }

    public MenuItem getItem(int index) throws Exception {

        if (index < 0 || index >= daftarMenu.size()) {
            throw new Exception("Menu tidak ditemukan!");
        }

        return daftarMenu.get(index);
    }

    public void simpanMenuKeFile(String fileName) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {

            for (MenuItem item : daftarMenu) {

                if (item instanceof Makanan) {
                    pw.println("Makanan," +
                            item.getNama() + "," +
                            item.getHarga());
                }

                else if (item instanceof Minuman) {
                    pw.println("Minuman," +
                            item.getNama() + "," +
                            item.getHarga());
                }

                else if (item instanceof Diskon) {
                    pw.println("Diskon," +
                            item.getNama() + "," +
                            ((Diskon) item).getDiskon());
                }
            }

            System.out.println("Menu berhasil disimpan.");

        } catch (IOException e) {
            System.out.println("Gagal menyimpan menu.");
        }
    }

    public void muatMenuDariFile(String fileName) {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(fileName))) {

            String baris;

            while ((baris = br.readLine()) != null) {

                String[] data = baris.split(",");

                if (data[0].equalsIgnoreCase("Makanan")) {
                    tambahMenu(new Makanan(
                            data[1],
                            Double.parseDouble(data[2]),
                            "Umum"));
                }

                else if (data[0].equalsIgnoreCase("Minuman")) {
                    tambahMenu(new Minuman(
                            data[1],
                            Double.parseDouble(data[2]),
                            "Umum"));
                }

                else if (data[0].equalsIgnoreCase("Diskon")) {
                    tambahMenu(new Diskon(
                            data[1],
                            Double.parseDouble(data[2])));
                }
            }

            System.out.println("Menu berhasil dimuat.");

        } catch (IOException e) {
            System.out.println("File menu belum ada.");
        }
    }
}