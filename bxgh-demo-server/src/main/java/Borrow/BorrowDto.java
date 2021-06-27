package edu.jd.xyt.Borrow;

public class BorrowDto {
    private String isbn;
    private String b_name;
    private String c_id;
    public  String borrow_time;

    public String getBorrow_time() {
        return borrow_time;
    }
    public void setBorrow_time(String borrow_time) {
        this.borrow_time = borrow_time;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String borrow_id) {
        this.isbn=borrow_id;
    }
    public String getB_name() {
        return b_name;
    }
    public void setB_name(String b_name) {
        this.b_name = b_name;
    }
    public String getC_id() {
        return c_id;
    }
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
}
