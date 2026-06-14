public class Makanan extends MenuItem {
    private String jenisMakanan;

    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }

    @Override
    public void tampilMenu() {
        System.out.println("[Makanan] " +
                getNama() +
                " | Jenis: " + jenisMakanan +
                " | Harga: Rp" + getHarga());
    }
}