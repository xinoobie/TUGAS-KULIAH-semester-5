import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    // Array menu restoran
    static Menu[] daftarMenu = new Menu[100];

    // Array pesanan
    static String[] pesanan = new String[100];
    static int[] jumlahPesanan = new int[100];

    static int jumlahMenu = 0;
    static int totalPesanan = 0;

    public static void main(String[] args) {

        // Menu awal restoran
        tambahMenuAwal();

        int pilihan;

        do {
            System.out.println("\n===== APLIKASI RESTORAN =====");
            System.out.println("1. Pesan Menu");
            System.out.println("2. Manajemen Menu");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    menuPelanggan();
                    break;

                case 2:
                    manajemenMenu();
                    break;

                case 3:
                    System.out.println("Terima kasih.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 3);
    }

    // Tambah menu awal
    static void tambahMenuAwal() {

        daftarMenu[jumlahMenu++] = new Menu("Nasi Goreng", "Makanan", 25000);
        daftarMenu[jumlahMenu++] = new Menu("Mie Ayam", "Makanan", 20000);
        daftarMenu[jumlahMenu++] = new Menu("Sate Ayam", "Makanan", 30000);
        daftarMenu[jumlahMenu++] = new Menu("Bakso", "Makanan", 22000);

        daftarMenu[jumlahMenu++] = new Menu("Es Teh", "Minuman", 5000);
        daftarMenu[jumlahMenu++] = new Menu("Jus Alpukat", "Minuman", 15000);
        daftarMenu[jumlahMenu++] = new Menu("Kopi Hitam", "Minuman", 12000);
        daftarMenu[jumlahMenu++] = new Menu("Milkshake", "Minuman", 18000);
    }

    // Tampilkan menu
    static void tampilkanMenu() {

        System.out.println("\n===== MENU MAKANAN =====");

        for (int i = 0; i < jumlahMenu; i++) {

            if (daftarMenu[i].kategori.equalsIgnoreCase("Makanan")) {
                System.out.println((i + 1) + ". "
                        + daftarMenu[i].nama
                        + " - Rp " + daftarMenu[i].harga);
            }
        }

        System.out.println("\n===== MENU MINUMAN =====");

        for (int i = 0; i < jumlahMenu; i++) {

            if (daftarMenu[i].kategori.equalsIgnoreCase("Minuman")) {
                System.out.println((i + 1) + ". "
                        + daftarMenu[i].nama
                        + " - Rp " + daftarMenu[i].harga);
            }
        }
    }

    // Menu pelanggan
    static void menuPelanggan() {

        totalPesanan = 0;

        while (true) {

            tampilkanMenu();

            System.out.print("\nMasukkan nama menu (ketik selesai): ");
            String namaMenu = input.nextLine();

            if (namaMenu.equalsIgnoreCase("selesai")) {
                break;
            }

            int index = cariMenu(namaMenu);

            if (index == -1) {
                System.out.println("Menu tidak ditemukan!");
                continue;
            }

            System.out.print("Jumlah: ");
            int jumlah = input.nextInt();
            input.nextLine();

            pesanan[totalPesanan] = namaMenu;
            jumlahPesanan[totalPesanan] = jumlah;

            totalPesanan++;
        }

        cetakStruk();
    }

    // Cari menu
    static int cariMenu(String nama) {

        for (int i = 0; i < jumlahMenu; i++) {

            if (daftarMenu[i].nama.equalsIgnoreCase(nama)) {
                return i;
            }
        }

        return -1;
    }

    // Cetak struk
    static void cetakStruk() {

        System.out.println("\n===== STRUK PEMBELIAN =====");

        int subtotal = 0;
        int jumlahMinuman = 0;
        int minumanTermurah = Integer.MAX_VALUE;

        for (int i = 0; i < totalPesanan; i++) {

            int index = cariMenu(pesanan[i]);

            int harga = daftarMenu[index].harga;
            int total = harga * jumlahPesanan[i];

            subtotal += total;

            System.out.println(
                    pesanan[i]
                    + " x" + jumlahPesanan[i]
                    + " = Rp " + total
            );

            // Hitung promo minuman
            if (daftarMenu[index].kategori.equalsIgnoreCase("Minuman")) {

                jumlahMinuman += jumlahPesanan[i];

                if (harga < minumanTermurah) {
                    minumanTermurah = harga;
                }
            }
        }

        System.out.println("-------------------------");
        System.out.println("Subtotal : Rp " + subtotal);

        // Diskon
        int diskon = 0;

        if (subtotal > 100000) {
            diskon = subtotal * 10 / 100;
            System.out.println("Diskon 10% : Rp " + diskon);
        }

        // Promo minuman
        int promoMinuman = 0;

        if (subtotal > 50000 && jumlahMinuman >= 2) {
            promoMinuman = minumanTermurah;
            System.out.println("Promo Beli 1 Gratis 1 : Rp " + promoMinuman);
        }

        // Pajak
        int pajak = subtotal * 10 / 100;

        System.out.println("Pajak 10% : Rp " + pajak);

        // Biaya pelayanan
        int pelayanan = 20000;

        System.out.println("Biaya Pelayanan : Rp " + pelayanan);

        int totalBayar = subtotal + pajak + pelayanan - diskon - promoMinuman;

        System.out.println("-------------------------");
        System.out.println("TOTAL BAYAR : Rp " + totalBayar);
    }

    // Menu manajemen
    static void manajemenMenu() {

        int pilih;

        do {

            System.out.println("\n===== MANAJEMEN MENU =====");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");

            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {

                case 1:
                    tambahMenuBaru();
                    break;

                case 2:
                    ubahHarga();
                    break;

                case 3:
                    hapusMenu();
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilih != 4);
    }

    // Tambah menu baru
    static void tambahMenuBaru() {

        System.out.print("Nama menu: ");
        String nama = input.nextLine();

        System.out.print("Kategori: ");
        String kategori = input.nextLine();

        System.out.print("Harga: ");
        int harga = input.nextInt();
        input.nextLine();

        daftarMenu[jumlahMenu++] = new Menu(nama, kategori, harga);

        System.out.println("Menu berhasil ditambahkan!");
    }

    // Ubah harga
    static void ubahHarga() {

        tampilkanMenu();

        System.out.print("Masukkan nomor menu: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > jumlahMenu) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        System.out.print("Yakin ingin mengubah? (Ya/Tidak): ");
        String konfirmasi = input.nextLine();

        if (konfirmasi.equalsIgnoreCase("Ya")) {

            System.out.print("Harga baru: ");
            int hargaBaru = input.nextInt();
            input.nextLine();

            daftarMenu[nomor - 1].harga = hargaBaru;

            System.out.println("Harga berhasil diubah!");
        }
    }

    // Hapus menu
    static void hapusMenu() {

        tampilkanMenu();

        System.out.print("Masukkan nomor menu: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > jumlahMenu) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        System.out.print("Yakin ingin menghapus? (Ya/Tidak): ");
        String konfirmasi = input.nextLine();

        if (konfirmasi.equalsIgnoreCase("Ya")) {

            for (int i = nomor - 1; i < jumlahMenu - 1; i++) {
                daftarMenu[i] = daftarMenu[i + 1];
            }

            jumlahMenu--;

            System.out.println("Menu berhasil dihapus!");
        }
    }
}