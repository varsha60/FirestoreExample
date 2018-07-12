package com.example.android.firestoreexample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private EditText edittextname, edittextbrand, edittextdesc, edittextqty, edittextprice;
    private Button buttonsave, buttonview;
   private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittextname = findViewById(R.id.editTextName);
        edittextbrand = findViewById(R.id.editTextBrand);
        edittextdesc = findViewById(R.id.editTextDesc);
        edittextqty = findViewById(R.id.editTextQty);
        edittextprice= findViewById(R.id.editTextPrice);

        buttonsave = findViewById(R.id.buttonSave);
        buttonview = findViewById(R.id.buttonView);




        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              db  = FirebaseFirestore.getInstance();

                 String name = edittextname.getText().toString().trim();
                 String brand = edittextbrand.getText().toString().trim();
                 String desc = edittextdesc.getText().toString().trim();
                 String qty = edittextqty.getText().toString().trim();
                 String price = edittextprice.getText().toString().trim();
             if(!validateErros(name,brand,desc,qty,price))
             {
                 CollectionReference dbproducts = db.collection("products");
                 Product product = new Product(name,brand,desc,Integer.parseInt(qty),Double.parseDouble(price));
                 dbproducts.add(product)
                         .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                     @Override
                     public void onSuccess(DocumentReference documentReference) {

                         Toast.makeText(MainActivity.this,"Added successfully",Toast.LENGTH_SHORT).show();
                         edittextname.setText("");
                         edittextbrand.setText("");
                         edittextdesc.setText("");
                         edittextprice.setText("");
                         edittextqty.setText("");

                     }
                 })
                         .addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {

                                 Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                             }
                         });




             }


            }
        });

        buttonview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductActivity.class);
                startActivity(intent);
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
