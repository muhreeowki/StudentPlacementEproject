package com.example.studentplacement.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentplacement.DatabaseHelper;
import com.example.studentplacement.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TPOAddPastPaperActivity extends AppCompatActivity {
    private static final int FILE_SELECT_CODE = 0;
    ActivityResultLauncher<Intent> selectFileActivityResultLauncher;
    private EditText etCompanyName, etYear;
    private Button btnSelectFile, btnUpload;
    private DatabaseHelper dbHelper;
    private Uri selectedFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo_add_past_paper);

        dbHelper = new DatabaseHelper(this);
        etCompanyName = findViewById(R.id.etCompanyName);
        etYear = findViewById(R.id.etYear);
        btnSelectFile = findViewById(R.id.btnSelectFile);
        btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setEnabled(false);

        btnSelectFile.setOnClickListener(v -> selectFile());
        btnUpload.setOnClickListener(v -> uploadPaper());


        selectFileActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK) {
                            Intent data = o.getData();
                            assert data != null;
                            selectedFileUri = data.getData();
                            btnUpload.setEnabled(true);
                        }
                    }
                }
        );
    }

    private void selectFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/*");
        selectFileActivityResultLauncher.launch(intent);
    }

    private void uploadPaper() {
        assert selectedFileUri != null;

        String companyName = etCompanyName.getText().toString();
        int year = Integer.parseInt(etYear.getText().toString());
        String fileType = getFileExtension(selectedFileUri);

        // Save file to app's private storage and get internal URI
        String internalUri = saveFileToInternalStorage(selectedFileUri);
        if (internalUri != null) {
            long result = dbHelper.addPastPaper(companyName, year, internalUri, fileType);
            if (result != -1) {
                Toast.makeText(this, "Paper uploaded successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error uploading paper", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String saveFileToInternalStorage(Uri sourceUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(sourceUri);
            String fileName = "paper_" + System.currentTimeMillis() + "." + getFileExtension(sourceUri);
            File file = new File(getFilesDir(), fileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();
            String path = file.getAbsolutePath();
            System.out.println("Past Paper File Path: " + path);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
