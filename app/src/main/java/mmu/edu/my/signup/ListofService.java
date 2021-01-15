package mmu.edu.my.signup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

public class ListofService extends AppCompatActivity {
    TextView etName, etNumber, etServiceTitle, etServiceDesc;
    Button bNewService;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    RecyclerView recyclerView;
    FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_service);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etServiceTitle = findViewById(R.id.etServiceTitle);
        etServiceDesc = findViewById(R.id.etServiceDesc);
        bNewService = (Button) findViewById(R.id.bNewService);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);

        Query query = fstore.collection("UserInfo");

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<User, UserViewHolder>(options) {
            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
                return new UserViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User model) {
                holder.txtTitle.setText(model.getTitle());
                holder.txtName.setText(model.getName());
                holder.txtDesc.setText(model.getDescription());
                holder.txtPhone.setText(model.getNumber());
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);








        bNewService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateService.class));
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), login.class));
        finish();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout root;
        public TextView txtTitle;
        public TextView txtDesc;
        public TextView txtPhone;
        public TextView txtName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtTitle = itemView.findViewById(R.id.list_title);
            txtDesc = itemView.findViewById(R.id.list_desc);
            txtPhone = itemView.findViewById(R.id.list_number);
            txtName = itemView.findViewById(R.id.list_name);
        }

        public void setTxtTitle(String string){
            txtTitle.setText(string);
        }

        public void setTxtDesc(TextView txtDesc) {
            this.txtDesc = txtDesc;
        }

        public void setTxtName(TextView txtName) {
            this.txtName = txtName;
        }

        public void setTxtPhone(TextView txtPhone) {
            this.txtPhone = txtPhone;
        }
    }


}
