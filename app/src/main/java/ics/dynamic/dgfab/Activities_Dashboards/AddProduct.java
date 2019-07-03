package ics.dynamic.dgfab.Activities_Dashboards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.myhexaville.login.R;

public class AddProduct extends AppCompatActivity {
    Toolbar toolbar_addProd;
    Spinner spinproduct ,spntx;
    Button nxpx;
    String[] FacePacks = { "Beauty and Personal Care", "Body and Skin Care", "Face Care", "Face Packs"};
    String[] Electronics = {"Gaming Consoles","Home Audio & Theater", "TV", "Laptops", "DSLR Cameras", "Gaming Consoles","Air Conditioners" ,"Refrigerators","Washing Machines","Kitchen & Home Appliances,& Cooling Appliances,"};
    String[] MensFashion = { "Clothing", "Shirts", "Jeans", "Innerwear","Watches" ,"Bags & Luggage" ,"Sunglasses","Jewellery","Wallets","Shoes","Sports Shoes","Formal Shoes" ,"Sportswear" ,"Sportswear"};
    String[] CarMotorbikeIndustrial = { "Motorbike Accessories & Parts", "Car Accessories", "Car Electronics", "Car Parts","Car & Bike Care" ,"All Car & Motorbike Products" ,"Industrial & Scientific Supplies","Jewellery","Test, Measure & Inspect","Lab & Scientific","Janitorial & Sanitation Supplies","Formal Shoes"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        spinproduct = findViewById(R.id.spinproduct);
        nxpx = findViewById(R.id.nxpx);
        spntx = findViewById(R.id.spntx);

        nxpx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinproduct.getSelectedItem().equals("Mens Wear"))
                {
//                    Intent intent = new Intent(AddProduct.this , Add_Product_Cosmetics.class);
//                    startActivity(intent);
                }
                if(spinproduct.getSelectedItem().equals("Cosmetics"))
                {
//                    Intent intent = new Intent(AddProduct.this , Add_Product_Cosmetics.class);
//                    startActivity(intent);

                }
                if(spinproduct.getSelectedItem().equals("Electronics"))
                {
//                    Intent intent = new Intent(AddProduct.this , Add_Product_Cosmetics.class);
//                    startActivity(intent);
                }
                if(spinproduct.getSelectedItem().equals("Car, Motorbike, Industrial"))
                {
//                    Intent intent = new Intent(AddProduct.this , Add_Product_Cosmetics.class);
//                    startActivity(intent);
                }
            }
        });
        spinproduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinproduct.getSelectedItem().equals("Mens Wear"))
                {
                    //Creating the ArrayAdapter instance having the country list
                    ArrayAdapter aa = new ArrayAdapter(AddProduct.this,android.R.layout.simple_spinner_item,MensFashion);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spntx.setAdapter(aa);
                }
                if(spinproduct.getSelectedItem().equals("Cosmetics"))
                {
                    //Creating the ArrayAdapter instance having the country list
                    ArrayAdapter aa = new ArrayAdapter(AddProduct.this,android.R.layout.simple_spinner_item,FacePacks);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spntx.setAdapter(aa);


                }
                if(spinproduct.getSelectedItem().equals("Electronics"))
                {
                    //Creating the ArrayAdapter instance having the country list
                    ArrayAdapter aa = new ArrayAdapter(AddProduct.this,android.R.layout.simple_spinner_item,Electronics);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spntx.setAdapter(aa);
                }
                if(spinproduct.getSelectedItem().equals("Car, Motorbike, Industrial"))
                {
                    //Creating the ArrayAdapter instance having the country list
                    ArrayAdapter aa = new ArrayAdapter(AddProduct.this,android.R.layout.simple_spinner_item,CarMotorbikeIndustrial);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spntx.setAdapter(aa);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toolbar_addProd = (Toolbar) findViewById(R.id.toolbar_addProd);
        toolbar_addProd.setNavigationIcon(R.drawable.home);
        toolbar_addProd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //   stopActivityTask();
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
