package com.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guofei
 * @date 2022/6/15 11:47 AM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessage {

  private String messageId = System.nanoTime() + "";

  private String action;

  private String data;

}
