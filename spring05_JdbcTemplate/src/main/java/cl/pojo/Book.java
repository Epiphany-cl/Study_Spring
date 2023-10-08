package cl.pojo;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private Double price;
    private Integer sales;
    private Integer stock;
    private String imgPath = "static/img/default.jpg";


    public Book() {
    }

    public Book(Integer id, String name, String author, Double price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取
     * @return sales
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * 设置
     * @param sales
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * 获取
     * @return stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置
     * @param stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取
     * @return imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * 设置
     * @param imgPath
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String toString() {
        return "book{id = " + id + ", name = " + name + ", author = " + author + ", price = " + price + ", sales = " + sales + ", stock = " + stock + ", imgPath = " + imgPath + "}";
    }
}
