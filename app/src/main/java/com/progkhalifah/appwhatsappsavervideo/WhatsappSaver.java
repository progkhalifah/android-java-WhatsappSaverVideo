package com.progkhalifah.appwhatsappsavervideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.progkhalifah.appwhatsappsavervideo.adapter.WhatsappAdapter;
import com.progkhalifah.appwhatsappsavervideo.model.Constant;
import com.progkhalifah.appwhatsappsavervideo.model.WhatsappStatusmodel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WhatsappSaver extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    WhatsappAdapter storyAdapter;
    File[] files;
    ArrayList<Object> filesList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_saver);

        initViews();

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                setUpRefreshingLayout();
            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();



    }

    private void setUpRefreshingLayout() {
        filesList.clear();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        storyAdapter = new WhatsappAdapter(WhatsappSaver.this, getData());
        recyclerView.setAdapter(storyAdapter);
        storyAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipeRececlerview);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            setUpRefreshingLayout();
            (
                    new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(WhatsappSaver.this, "Refresh!", Toast.LENGTH_SHORT).show();

                }
            },2000);

        });

    }

    private ArrayList<Object> getData() {

        WhatsappStatusmodel f;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath()+ Constant.FOLDER_NAME+"Media/.Statuses";
        File targetDirector =new File(targetPath);
        files = targetDirector.listFiles();

        for (int i =0; i<files.length; i++){
            File file = files[i];
            f = new WhatsappStatusmodel();
            f.setUri(Uri.fromFile(file));
            f.setPath(files[i].getAbsolutePath());
            f.setFilename(file.getName());

            if (!f.getUri().toString().endsWith(".nomedia")){
                filesList.add(f);

            }
        }

        return filesList;
    }
}