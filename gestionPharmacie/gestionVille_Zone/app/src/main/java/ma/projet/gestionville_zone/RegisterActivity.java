package ma.projet.gestionville_zone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ma.projet.gestionville_zone.beans.RegisterRequest;
import ma.projet.gestionville_zone.beans.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button btnSignUp;
    EditText edUsername,edName,edPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btnSignUp = findViewById(R.id.btnRegister);
        edUsername = findViewById(R.id.tietUsername);
        edName = findViewById(R.id.edname);
        edPassword= findViewById(R.id.tiePassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edName.getText().toString())||TextUtils.isEmpty(edUsername.getText().toString())||TextUtils.isEmpty(edPassword.getText().toString())) {
                    String message = "All fields are required";
                    Toast.makeText(RegisterActivity.this,message, Toast.LENGTH_LONG).show();
                }else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setName(edName.getText().toString());
                    registerRequest.setUsername(edUsername.getText().toString());
                    registerRequest.setPassword(edPassword.getText().toString());
                    registerUser(registerRequest);
                }
            }


        });

    }

    public void registerUser(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    String message = "Successful";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                }else {
                    String message = "Unable to register user";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message =t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
}