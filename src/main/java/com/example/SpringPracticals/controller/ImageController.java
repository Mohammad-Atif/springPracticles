package com.example.SpringPracticals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringPracticals.entities.request.ImageRequest;
import com.example.SpringPracticals.entities.response.ImageResponse;
import com.example.SpringPracticals.services.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {

  @Autowired
  private ImageService imageService;


  @PostMapping("/compressImage")
  public ImageResponse getCompressedImage(
      @RequestBody
      ImageRequest imageRequest) {
    // Compress the image
    ImageResponse compressedImage =
        imageService.getCompressedImage(imageRequest.getS3Path(), imageRequest.getImageType());
    return compressedImage;
  }
}
