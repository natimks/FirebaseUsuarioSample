package br.ifsc.edu.firebaseauladdm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastrarPessoaActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pessoa);

        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void cadastrarUsuario(View view) {
        EditText nomeEd = findViewById(R.id.nomeEd);
        String nome = nomeEd.getText().toString();

        EditText sexoEd = findViewById(R.id.sexoEd);
        String sexo = sexoEd.getText().toString();

        EditText cpfEd = findViewById(R.id.cpfEd);
        String cpf = cpfEd.getText().toString();

        Pessoa p = new Pessoa(nome, cpf, sexo);

        databaseReference.child("pessoas").push().setValue(p).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Inserido com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Falha no cadastro do usuário", Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}
