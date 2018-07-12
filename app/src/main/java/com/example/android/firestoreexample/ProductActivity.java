package com.example.android.firestoreexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

   private  RecyclerView recyclerView;
    private List<Product> products;
    private ProgressBar progressBar;
    private ProductAdapter adapter;
    private FirebaseFirestore db;
//    Product p ;
    final private static String TAG="ProductActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);

        progressBar = findViewById(R.id.progressbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        products = new ArrayList<>();

        adapter = new ProductAdapter(this,products);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        db.collection("products").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {

                        progressBar.setVisibility(View.GONE);

                        if(!documentSnapshots.isEmpty())
                        {
                           List<DocumentSnapshot> list = documentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){



                                Product p = d.toObject(Product.class);
                                p.setId(d.getId());
                                products.add(p);





                            }

                                                                   }
                           adapter.notifyDataSetChanged();
                        }

                });
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//
//                        Toast.makeText(ProductActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
//
//                    }
//                });





    }
}
