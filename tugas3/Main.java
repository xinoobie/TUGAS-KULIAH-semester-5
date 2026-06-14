import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Menu menu = new Menu();
        Pesanan pesanan = new Pesanan();

        menu.muatMenuDariFile("menu.txt");

        int pilihan;

        do {

            System.out.println("\n=== RESTORAN ===");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Tampilkan Menu");
            System.out.println("3. Pesan");
            System.out.println("4. Tampilkan Struk");
            System.out.println("5. Simpan Menu");
            System.out.println("6. Simpan Struk");
            System.out.println("0. Keluar");
            System.out.print("Pilih : ");

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                case 1:

                    System.out.println("\n1. Makanan");
                    System.out.println("2. Minuman");
                    System.out.println("3. Diskon");

                    int jenis = input.nextInt();
                    input.nextLine();

                    if (jenis == 1) {

                        System.out.print("Nama : ");
                        String nama = input.nextLine();

                        System.out.print("Harga : ");
                        double harga =
                                input.nextDouble();
                        input.nextLine();

                        System.out.print("Jenis : ");
                        String jm =
                                input.nextLine();

                        menu.tambahMenu(
                                new Makanan(
                                        nama,
                                        harga,
                                        jm));
                    }

                    else if (jenis == 2) {

                        System.out.print("Nama : ");
                        String nama = input.nextLine();

                        System.out.print("Harga : ");
                        double harga =
                                input.nextDouble();
                        input.nextLine();

                        System.out.print("Jenis : ");
                        String jm =
                                input.nextLine();

                        menu.tambahMenu(
                                new Minuman(
                                        nama,
                                        harga,
                                        jm));
                    }

                    else if (jenis == 3) {

                        System.out.print(
                                "Nama Diskon : ");
                        String nama =
                                input.nextLine();

                        System.out.print(
                                "Persentase : ");

                        double d =
                                input.nextDouble();

                        menu.tambahMenu(
                                new Diskon(
                                        nama,
                                        d));
                    }

                    break;

                case 2:

                    menu.tampilkanMenu();
                    break;

                case 3:

                    try {

                        menu.tampilkanMenu();

                        System.out.print(
                                "Nomor menu : ");

                        int nomor =
                                input.nextInt();

                        pesanan.tambahPesanan(
                                menu.getItem(
                                        nomor - 1));

                    } catch (Exception e) {

                        System.out.println(
                                e.getMessage());
                    }

                    break;

                case 4:

                    pesanan.tampilkanStruk();
                    break;

                case 5:

                    menu.simpanMenuKeFile(
                            "menu.txt");
                    break;

                case 6:

                    pesanan.simpanStruk(
                            "struk.txt");
                    break;

                case 0:

                    System.out.println(
                            "Terima kasih.");
                    break;

                default:

                    System.out.println(
                            "Pilihan tidak ada.");
            }

        } while (pilihan != 0);

        input.close();
    }
}