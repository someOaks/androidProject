package com.example.androidproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class PlayActivity extends AppCompatActivity {

    //create class reference
    VideoView vid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        vid = (VideoView)findViewById(R.id.videoView);
        playVideo();
    }

    private File getLatestFilefromDir(String dirPath){
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

    public void playVideo() {
        MediaController m = new MediaController(this);
        vid.setMediaController(m);
        final File dir = PlayActivity.this.getExternalFilesDir(null);
        String path = dir.getAbsolutePath() + "/";
        Uri u = Uri.parse(getLatestFilefromDir(path).getAbsolutePath());
        vid.setVideoURI(u);
        vid.start();
    }
}
