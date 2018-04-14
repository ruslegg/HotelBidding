package hotel.aau.hotelbidding2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class LoginFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = "Hotel bid";
    Button toRegisterButton,signInButton;
    EditText passwordEditText, emailEditText;
    TextView emailText, passwordText;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        signInButton = (Button) view.findViewById(R.id.signInButton);
        toRegisterButton = (Button) view.findViewById(R.id.toRegisterButton);
        mAuth = FirebaseAuth.getInstance();
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        emailEditText = (EditText) view.findViewById(R.id.emailEditText);
        toRegisterButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
                case R.id.toRegisterButton:
                    getFragmentManager().beginTransaction().replace(R.id.activity_main,new RegisterFragment()).commit();
                    break;
                case R.id.signInButton:
                    Log.d(TAG,"check 2");
                    mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString()).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "sign in successful");
                                user = mAuth.getCurrentUser();

                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getActivity(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Intent intent = new Intent(getActivity(),BidsActivity.class);
                    startActivity(intent);
                    break;
            }
        }

}