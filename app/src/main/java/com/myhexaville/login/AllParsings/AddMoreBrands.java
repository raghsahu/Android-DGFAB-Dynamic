package com.myhexaville.login.AllParsings;

import android.widget.EditText;
import android.widget.ImageView;

public class AddMoreBrands  {
    public EditText Brandname;
    public ImageView Addmore;
    public ImageView removeless;

    public AddMoreBrands(EditText brandname, ImageView addmore, ImageView removeless) {
        Brandname = brandname;
        Addmore = addmore;
        this.removeless = removeless;
    }

    public EditText getBrandname() {
        return Brandname;
    }

    public void setBrandname(EditText brandname) {
        Brandname = brandname;
    }

    public ImageView getAddmore() {
        return Addmore;
    }

    public void setAddmore(ImageView addmore) {
        Addmore = addmore;
    }

    public ImageView getRemoveless() {
        return removeless;
    }

    public void setRemoveless(ImageView removeless) {
        this.removeless = removeless;
    }
}
