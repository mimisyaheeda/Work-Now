package mmu.edu.my.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateService extends AppCompatActivity {

    EditText etName, etNumber, etLocation, etServiceTitle, etServiceDesc, etDateTime, etSuggestedPrice;
    Button bCreateServ;
    FirebaseFirestore fStore;
    //FirebaseAuth fAuth;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);

        etName = (EditText) findViewById(R.id.etName);
        etNumber = (EditText) findViewById(R.id.etNumber);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etServiceTitle = (EditText) findViewById(R.id.etServiceTitle);
        etServiceDesc = (EditText) findViewById(R.id.etServiceDesc);
        etDateTime = (EditText) findViewById(R.id.etDateTime);
        etSuggestedPrice = (EditText) findViewById(R.id.etSuggestedPrice);
        //fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        bCreateServ = (Button) findViewById(R.id.bcreateServ);

        bCreateServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String number = etNumber.getText().toString();
                String location = etLocation.getText().toString();
                String title = etServiceTitle.getText().toString();
                String desc = etServiceDesc.getText().toString();
                String date = etDateTime.getText().toString();
                String price = etSuggestedPrice.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    etName.setError("Name is required.");
                    return;
                }
                if (TextUtils.isEmpty(number)) {
                    etNumber.setError("Phone number is required.");
                    return;
                }
                if (TextUtils.isEmpty(location)) {
                    etLocation.setError("Location is required.");
                    return;
                }
                if (TextUtils.isEmpty(title)) {
                    etServiceTitle.setError("Service title is required.");
                    return;
                }
                if (TextUtils.isEmpty(desc)) {
                    etServiceDesc.setError("Service description is required.");
                    return;
                }
                if (TextUtils.isEmpty(date)) {
                    etDateTime.setError("Date is required.");
                    return;
                }
                if (TextUtils.isEmpty(price)) {
                    etSuggestedPrice.setError("Price is required.");
                    return;
                }

                Map<String, String> data = new HashMap<>();
                data.put("name", name);
                data.put("number", number);
                data.put("location", location);
                data.put("title", title);
                data.put("description", desc);
                data.put("date", date);
                data.put("price", price);

                fStore.collection("UserInfo").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CreateService.this, "Service Created", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();
                        Toast.makeText(CreateService.this, "Error: " + error, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }


}