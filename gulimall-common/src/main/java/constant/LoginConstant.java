package constant;

public enum LoginConstant {
  LOGIN("login");

  LoginConstant(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  private final String value;

}
