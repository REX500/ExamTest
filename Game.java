package AppLayer;

/**
 * Created by Filip on 25-04-2016.
 */
public class Game {
    private String name;
    private String genre;
    private String PEGI;
    private String price;
    private String platform;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPEGI() {
        return PEGI;
    }

    public void setPEGI(String PEGI) {
        this.PEGI = PEGI;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Game(String name, String genre, String PEGI, String platform, String price, int quantity){
        this.name = name;
        this.genre = genre;
        this.PEGI = PEGI;
        this.platform = platform;
        this.price = price;
        this.quantity = quantity;
    }

    public Game(){

    }

}
