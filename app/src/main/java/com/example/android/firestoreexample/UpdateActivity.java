package com.example.android.firestoreexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    private EditText edittextname, edittextbrand, edittextdesc, edittextqty, edittextprice;
    private Button buttonupdate,buttondelete;
    private FirebaseFirestore db;
    private Product product;
    private List<Product> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        product = (Product) getIntent().getSerializableExtra("product");


        products = new ArrayList<>();

        edittextname = findViewById(R.id.editTextName);
        edittextbrand = findViewById(R.id.editTextBrand);
        edittextdesc = findViewById(R.id.editTextDesc);
        edittextqty = findViewById(R.id.editTextQty);
        edittextprice= findViewById(R.id.editTextPrice);
        buttonupdate = findViewById(R.id.buttonUpdate);
        buttondelete = findViewById(R.id.buttonDelete);

        edittextname.setText(product.getName());
        edittextbrand.setText(product.getBrand());
        edittextdesc.setText(product.getDesc());
        edittextprice.setText(String.valueOf(product.getPrice()));
        edittextqty.setText(String.valueOf(product.getQty()));

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db  = FirebaseFirestore.getInstance();

                String name = edittextname.getText().toString().trim();
                String brand = edittextbrand.getText().toString().trim();
                String desc = edittextdesc.getText().toString().trim();
                String qty = edittextqty.getText().toString().trim();
                String price = edittextprice.getText().toString().trim();
                if(!validateErros(name,brand,desc,qty,price)){
                    Product p = new Product(name,brand,desc,Integer.parseInt(qty),Double.parseDouble(price));
                    db.collection("products").document(product.getId())
                            .set(p)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(UpdateActivity.this, "Product Updated", Toast.LENGTH_LONG).show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdateActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db  = FirebaseFirestore.getInstance();

                String name = edittextname.getText().toString().trim();
                String brand = edittextbrand.getText().toString().trim();
                String desc = edittextdesc.getText().toString().trim();
                String qty = edittextqty.getText().toString().trim();
                String price = edittextprice.getText().toString().trim();
                final Product p = new Product(name,brand,desc,Integer.parseInt(qty),Double.parseDouble(price));

                db.collection("products").document(product.getId())
                       .delete()
                       .addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {
                               Toast.makeText(UpdateActivity.this,"Product deleted successfully",Toast.LENGTH_SHORT).show();

                               edittextname.setText("");
                               edittextbrand.setText("");
                               edittextdesc.setText("");
                               edittextprice.setText("");
                               edittextqty.setText("");
                               products.remove(p);
                           }
                       })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(UpdateActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                           }
                       });

            }
        });
    }
    private boolean validateErros(String name, String brand, String desc, String qty, String price)
    {
        if(name.isEmpty())
        {
            edittextname.setError("Name required");
            edittextname.requestFocus();
            return true;
        }

        if(brand.isEmpty())
        {
            edittextbrand.setError("Name required");
            edittextbrand.requestFocus();
            return true;
        }
        if(desc.isEmpty())
        {
            edittextdesc.setError("Name required");
            edittextdesc.requestFocus();
            return true;
        }
        if(qty.isEmpty())
        {
            edittextqty.setError("Name required");
            edittextqty.requestFocus();
            return true;
        }
        if(price.isEmpty())
        {
            edittextprice.setError("Name required");
            edittextprice.requestFocus();
            return true;
        }
        return false;

    }

}
