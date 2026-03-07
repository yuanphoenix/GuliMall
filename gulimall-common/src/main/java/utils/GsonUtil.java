package utils;

import com.google.gson.Gson;

public enum GsonUtil {
  INSTANCE;
  private final Gson gson;

  GsonUtil() {
    gson = new Gson();
  }

  public Gson getInstance() {
    return this.gson;
  }
}
