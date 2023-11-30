package com.example.securo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.securo.ui.home.QrFragment;
import android.content.SharedPreferences;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class UploadDocsActivity extends AppCompatActivity {
    StorageReference storageReference;
    Uri image;
    Button buttonSelect;
    Button buttonApply;
    ImageView imageView;
    TextView textBack;
    ProgressBar progressBar;
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                if (result.getData() != null) {
                    image = result.getData().getData();
                    Glide.with(getApplicationContext()).load(image).into(imageView);
                }
            } else {
                Toast.makeText(UploadDocsActivity.this, "Выберите файл", Toast.LENGTH_SHORT).show();
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        FirebaseApp.initializeApp(UploadDocsActivity.this);
        storageReference = FirebaseStorage.getInstance().getReference();

        buttonSelect = findViewById(R.id.buttonUpload);
        buttonApply = findViewById(R.id.buttonApply);
        imageView = findViewById(R.id.imageViewDocs);
        textBack = findViewById(R.id.textViewBack);
        progressBar = findViewById(R.id.progressBar);

        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage(image);
            }
        });

        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadDocsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void uploadImage(Uri image) {
        QrFragment.quer = UUID.randomUUID().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("Query", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Q", QrFragment.quer);
        editor.apply();
        StorageReference reference = storageReference.child("images/" + QrFragment.quer);
        reference.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(UploadDocsActivity.this, "Успешно", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UploadDocsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadDocsActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setMax(Math.toIntExact(snapshot.getTotalByteCount()));
                progressBar.setProgress(Math.toIntExact(snapshot.getBytesTransferred()));
            }
        });
    }
}
