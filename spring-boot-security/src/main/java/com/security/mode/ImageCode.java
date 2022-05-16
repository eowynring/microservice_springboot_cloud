package com.security.mode;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author guofei
 * @date 2022/5/16 11:00 PM
 */
public class ImageCode {

  private BufferedImage image;

  private String code;

  private LocalDateTime expireTime;

  public ImageCode(BufferedImage image, String code, int expireIn) {
    this.image = image;
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
    this.image = image;
    this.code = code;
    this.expireTime = expireTime;
  }

  public boolean isExpire() {
    return LocalDateTime.now().isAfter(expireTime);
  }

  public BufferedImage getImage() {
    return image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public LocalDateTime getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(LocalDateTime expireTime) {
    this.expireTime = expireTime;
  }

}
