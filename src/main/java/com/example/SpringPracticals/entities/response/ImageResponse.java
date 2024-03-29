package com.example.SpringPracticals.entities.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ImageResponse {
  private String s3Path;
  private String imageType;
}
