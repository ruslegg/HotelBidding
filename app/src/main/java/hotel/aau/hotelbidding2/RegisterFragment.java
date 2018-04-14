package hotel.aau.hotelbidding2;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    Calendar myCalendar = Calendar.getInstance();
    Button birthdayBtn,registerBtn,toLoginBtn;
    String birthdayDate;
    EditText firstEditText,lastEditText,passwordEditText,repeatPasswordEditText,emailEditText;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;
    DatabaseReference usersRef = database.getReference("users");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        registerBtn = (Button) view.findViewById(R.id.registerBtn);
        toLoginBtn = (Button) view.findViewById(R.id.toLoginBtn);
        birthdayBtn = (Button) view.findViewById(R.id.birthdayBtn);
        firstEditText = (EditText) view.findViewById(R.id.firstEditText);
        lastEditText = (EditText) view.findViewById(R.id.lastEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        repeatPasswordEditText = (EditText) view.findViewById(R.id.repeatPasswordEditText);
        emailEditText = (EditText) view.findViewById(R.id.emailEditText);
        toLoginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        birthdayBtn.setOnClickListener(this);
        return view;
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd/MM/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
            birthdayDate = sdf.format(myCalendar.getTime());
            birthdayBtn.setText(birthdayDate);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void toLogin(View v){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.replace(R.id.activity_main,loginFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.birthdayBtn:
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.registerBtn:
                mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(),passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()){
                            Toast.makeText(getActivity(), "Sign up successful", Toast.LENGTH_SHORT).show();
                            mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(),passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        user = mAuth.getCurrentUser();
                                        DatabaseReference currentUserRef = usersRef.child(user.getUid());
                                        currentUserRef.child("name").setValue(firstEditText.getText().toString() + " " + lastEditText.getText().toString());
                                        currentUserRef.child("email").setValue(emailEditText.getText().toString());
                                        Toast.makeText(getActivity(),"Database ref created",Toast.LENGTH_SHORT);
                                    }
                                    else{
                                        Toast.makeText(getActivity(),"Database ref not created",Toast.LENGTH_SHORT);
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(getActivity(),"Fail",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.toLoginBtn:
                getFragmentManager().beginTransaction().replace(R.id.activity_main,new LoginFragment()).commit();
                break;
            }
        }
    }

