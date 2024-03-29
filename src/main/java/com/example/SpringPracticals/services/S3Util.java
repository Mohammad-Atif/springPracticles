package com.example.SpringPracticals.services;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class S3Util {


  public File downloadFile(String s3Path) throws InterruptedException{
    // Download the file from S3
    return new File("tmp/image.jpg");
  }

  public void uploadFile(String bucketName, String key, File file) {
    // Upload the file to S3
  }


}
