package com.programming.download;

import com.programming.DownloadManager;
import com.programming.model.FileInfo;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DownloadThread extends Thread{

    private FileInfo fileInfo;
    DownloadManager manager;
    public DownloadThread(FileInfo fileInfo, DownloadManager manager){
        this.fileInfo = fileInfo;
        this.manager = manager;
    }

    @Override
    public void run() {
        this.fileInfo.setStatus("DOWNLOADING");
        this.manager.updateUI(this.fileInfo);


        //download logic.
        try {
            Files.copy(new URL(this.fileInfo.getUrl()).openStream(), Paths.get(this.fileInfo.getPath()), StandardCopyOption.REPLACE_EXISTING);
            this.fileInfo.setStatus("DONE");
            this.fileInfo.setPer("100");
        } catch (IOException e) {
            this.fileInfo.setStatus("FAILED");
            System.out.println("Downloading Error");
            e.printStackTrace();
        }
        this.manager.updateUI(this.fileInfo);
    }
}
