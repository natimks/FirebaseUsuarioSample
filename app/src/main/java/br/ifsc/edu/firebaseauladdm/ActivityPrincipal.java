package br.ifsc.edu.firebaseauladdm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityPrincipal extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mAuth = FirebaseAuth.getInstance();

        // configurando realtime
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Log.d("FirebaseAuth", currentUser.getEmail());
        } else {
            Log.d("FirebaseAuth", "Usuário não cadastrado!");
            finish();
        }

        findViewById(R.id.cadastrarUsuarioBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CadastrarPessoaActivity.class);
                startActivity(i);
            }
        });

        //configurando recycler view
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        pessoas = new ArrayList<>();
        databaseReference.child("pessoas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pessoa p;
                for (DataSnapshot objSnap : dataSnapshot.getChildren()) {
                    p = objSnap.getValue(Pessoa.class);
                    p.setId(objSnap.getKey());
                    pessoas.add(p);
                }
                PessoaAdapter pessoaAdapter = new PessoaAdapter(getApplicationContext(), R.layout.linha_recycle_view, pessoas);
                recyclerView.setAdapter(pessoaAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void logOut(View view) {
        mAuth.signOut();
        finish();
    }
}
