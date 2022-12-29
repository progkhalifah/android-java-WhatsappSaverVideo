package com.progkhalifah.appwhatsappsavervideo.adapter;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.progkhalifah.appwhatsappsavervideo.R;
import com.progkhalifah.appwhatsappsavervideo.model.Constant;
import com.progkhalifah.appwhatsappsavervideo.model.WhatsappStatusmodel;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WhatsappAdapter extends RecyclerView.Adapter<WhatsappAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Object> filesList;

    public WhatsappAdapter(Context context, ArrayList<Object> filesList) {
        this.context = context;
        this.filesList = filesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.whatsapp_item_layout, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final WhatsappStatusmodel files = (WhatsappStatusmodel) filesList.get(position);

        if (files.getUri().toString().endsWith(".mp4")) {
            holder.playIcon.setVisibility(View.VISIBLE);
        } else {
            holder.playIcon.setVisibility(View.INVISIBLE);
        }

        Glide.with(context)
                .load(files.getUri())
                .into(holder.saveImage);

        holder.downloadID.setOnClickListener(v -> {
            checkFolder();
            final String path = ((WhatsappStatusmodel) filesList.get(position)).getPath();
            final File file = new File(path);

            String destPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.SAVE_FOLDER_NAME;
            File destFile = new File(destPath);

            try {
                FileUtils.copyFileToDirectory(file, destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            MediaScannerConnection.scanFile(
                    context,
                    new String[]{destPath + files.getFilename()},
                    new String[]{"*/*"},
                    new MediaScannerConnection.MediaScannerConnectionClient() {
                        @Override
                        public void onMediaScannerConnected() {

                        }

                        @Override
                        public void onScanCompleted(String path, Uri uri) {

                        }
                    }

            );

            Toast.makeText(context, "Saved to: "+destPath+files.getFilename(), Toast.LENGTH_SHORT).show();


        });


    }

    private void checkFolder() {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.SAVE_FOLDER_NAME;
        File dir = new File(path);

        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated)
            isDirectoryCreated = dir.mkdir();
        if (isDirectoryCreated)
            Log.d("Folder", "Already Created");


    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView playIcon, downloadID, saveImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            saveImage = itemView.findViewById(R.id.mainImageview);
            playIcon = itemView.findViewById(R.id.playButtonImage);
            downloadID = itemView.findViewById(R.id.downlodID);


        }
    }
}
