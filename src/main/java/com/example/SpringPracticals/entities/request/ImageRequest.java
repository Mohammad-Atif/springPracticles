package com.example.SpringPracticals.entities.request;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
public class ImageRequest {
  private String s3Path;
  private String imageType;
}
