package de.uni_saarland.studymechanics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.uni_saarland.studymechanics.entity.User;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener{
    // Firebase dependencies
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Firebase dependencies
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    // User is signed in, display home page
                    Intent homePage = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(homePage);
                }
                else {
                    // User is signed out, display login form
                    LoginFragment loginFragment = LoginFragment.newInstance("login", "fragment");
                    getSupportFragmentManager().beginTransaction().add(R.id.content, loginFragment).commit();
                }
            }
        };
    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // If sign in fails, display a message to the user.
                // If sign in succeeds, the auth state listener will be notified and logic
                // to handled signed in user can be done in the listener.
                if (!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Failed to create user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Failed to sign in user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    public void onLoginFragmentSignIn(String email, String password) {
        signIn(email, password);
    }

    @Override
    public void onLoginFragmentRegister() {
        RegisterFragment registerFragment = RegisterFragment.newInstance("register", "fragment");
        getSupportFragmentManager().beginTransaction().replace(R.id.content, registerFragment).commit();
    }

    @Override
    public void onRegisterFragmentRegister(User user) {
        createAccount(user.getEmail(), user.getPassword());
        Intent homePage = new Intent(this, HomeActivity.class);
        startActivity(homePage);
    }
}

