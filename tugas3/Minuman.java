public class Minuman extends MenuItem {
    private String jenisMinuman;

    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }

    @Override
    public void tampilMenu() {
        System.out.println("[Minuman] " +
                getNama() +
                " | Jenis: " + jenisMinuman +
                " | Harga: Rp" + getHarga());
    }
}