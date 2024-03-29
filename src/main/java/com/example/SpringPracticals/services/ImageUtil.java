package com.example.SpringPracticals.services;

import java.io.File;

import org.springframework.stereotype.Component;

import com.sun.jdi.VMOutOfMemoryException;

@Component
public class ImageUtil {


  private int numOfCalls = 0;

  public File compressImage(File imageFile, String imageType) throws VMOutOfMemoryException {
    if(numOfCalls < 2) {
      numOfCalls++;
      throw new VMOutOfMemoryException();
    }
    // Compress the image
    return imageFile;
  }
}
