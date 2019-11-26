package br.ifsc.edu.firebaseauladdm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // configurando realtime
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

//        Pessoa p = new Pessoa("Odair da Silva", "000.000.000-00", "m");
//
//        databaseReference.child("pessoas").push().setValue(p);
//
//        p = new Pessoa("Natalia Maria", "000.000.000-00", "f");
//        databaseReference.child("pessoas").push().setValue(p);
//
//        p = new Pessoa("Isabela Samira", "000.000.171-00", "f");
//        databaseReference.child("pessoas").push().setValue(p);
        databaseReference.child("pessoas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pessoa p = dataSnapshot.getValue(Pessoa.class);
                Log.d("DatabasePessoa", p.nome);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            Log.d("FirebaseAuth", mUser.getEmail());
        } else {
            Log.d("FirebaseAuth", "Usuário não autenticado");
            finish();
        }

    }

    public void autenticar(View view) {
        EditText loginEd, senhaEd;
        loginEd = findViewById(R.id.email);
        senhaEd = findViewById(R.id.senha);
        String login = loginEd.getText().toString();
        String senha = senhaEd.getText().toString();
        mAuth.signInWithEmailAndPassword(login, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //podemos verificar aqui se o cadastro foi concluido com sucessso
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Logado com sucesso!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent();

                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao efetuar login!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void cadastrar(View view) {
        EditText loginEd, senhaEd;
        loginEd = findViewById(R.id.email);
        senhaEd = findViewById(R.id.senha);
        String login = loginEd.getText().toString();
        String senha = senhaEd.getText().toString();

        mAuth.createUserWithEmailAndPassword(login, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //podemos verificar aqui se o cadastro foi concluido com sucessso
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Cadastro concluido com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao criar cadastro!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
