package com.nttyplc.cliente_service;

public class ApiResponse<T> {
  private boolean success;
  private int code;
  private String message;
  private T data;

  public ApiResponse(boolean success, int code, String message, T data) {
      this.success = success;
      this.code = code;
      this.message = message;
      this.data = data;
  }

  // Getters y setters (o usa lombok @Data para generar)
  public boolean isSuccess() { return success; }
  public int getCode() { return code; }
  public String getMessage() { return message; }
  public T getData() { return data; }

  public void setSuccess(boolean success) { this.success = success; }
  public void setCode(int code) { this.code = code; }
  public void setMessage(String message) { this.message = message; }
  public void setData(T data) { this.data = data; }
}
