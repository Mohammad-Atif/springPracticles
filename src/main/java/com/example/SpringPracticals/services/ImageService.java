package com.example.SpringPracticals.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.SpringPracticals.aspects.Retryable;
import com.example.SpringPracticals.entities.response.ImageResponse;
import com.sun.jdi.VMOutOfMemoryException;

@Component
public class ImageService {

  private final static String S3_BUCKET_NAME = "image-bucket";
  private final static String S3_PATH = "images/";

  @Autowired
  private S3Util s3Util;

  @Autowired
  private ImageUtil imageUtil;

  @Retryable(maxRetries = 3, delay = 1000, retryExceptions = {VMOutOfMemoryException.class})
  public ImageResponse getCompressedImage(String s3Path, String imageType) {
    try {
      File file = s3Util.downloadFile(s3Path);
      // suppose this is the function which sometimes throws memory exception due to heavy memory consumption
      File compressedFile = imageUtil.compressImage(file, imageType);
      s3Util.uploadFile(S3_BUCKET_NAME, S3_PATH + compressedFile.getName(), compressedFile);
      return ImageResponse.builder()
          .s3Path(s3Path + compressedFile.getName())
          .imageType(imageType)
          .build();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
