package edu.jd.xyt.lib;


import java.util.Date;

public class Library {


    public
    String getBorrow_id () {
        return borrow_id;
    }

    public
    void setBorrow_id ( String borrow_id ) {
        this.borrow_id = borrow_id;
    }

    public
    String getB_ISBN () {
        return b_ISBN;
    }

    public
    void setB_ISBN ( String b_ISBN ) {
        this.b_ISBN = b_ISBN;
    }

    public
    String getC_id () {
        return c_id;
    }

    public
    void setC_id ( String c_id ) {
        this.c_id = c_id;
    }

    public
    String getBorrow_time () {
        return borrow_time;
    }

    public
    void setBorrow_time ( String borrow_time ) {
        this.borrow_time = borrow_time;
    }

    public
    String getReturn_time () {
        return return_time;
    }

    public
    void setReturn_time ( String return_time ) {
        this.return_time = return_time;
    }

    public
    String getActual_return_time () {
        return actual_return_time;
    }

    public
    void setActual_return_time ( String actual_return_time ) {
        this.actual_return_time = actual_return_time;
    }

    private String borrow_id;
    private String b_ISBN;
    private String c_id;//
    private String borrow_time;
    private String return_time;
    private String actual_return_time;


}
