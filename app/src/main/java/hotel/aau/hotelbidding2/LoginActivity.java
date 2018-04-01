package hotel.aau.hotelbidding2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText passwordField,emailField;
    TextView emailText,passwordText;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        passwordField = (EditText)findViewById(R.id.passwordField);
        emailField = (EditText) findViewById(R.id.emailField);
        emailText = (TextView) findViewById(R.id.emailText);
        passwordText = (TextView) findViewById(R.id.passwordText);
        myRef.setValue("test3");
        DatabaseReference test3ref = database.getReference("users").child("test3").getRef();
        test3ref.child("test3child").setValue("value3");


    }
    public void sendUser(View view){
        System.out.println(passwordField.getText()+" "+emailField.getText());
        mAuth.createUserWithEmailAndPassword(emailField.getText().toString(),passwordField.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    System.out.println("Success");
                    FirebaseUser user = mAuth.getCurrentUser();
                }
                else {
                    Log.e("create:failure", task.getException().toString());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
