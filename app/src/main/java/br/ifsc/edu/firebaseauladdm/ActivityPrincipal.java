package br.ifsc.edu.firebaseauladdm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityPrincipal extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Log.d("FirebaseAuth", currentUser.getEmail());
        } else {
            Log.d("FirebaseAuth", "Usuário não cadastrado!");
            finish();
        }

    }

    public void logOut(View view) {
        mAuth.signOut();
        finish();
    }
}
