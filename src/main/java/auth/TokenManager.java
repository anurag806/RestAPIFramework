package auth;

public class TokenManager {
    private static String token;
    private TokenManager() {

    }
    public static String getToken() {
        if (token == null || token.isEmpty()) {
            AuthService authService = new AuthService();
            token=authService.generateToken().getToken();
        }
        return token;
    }
    public  static  void cleanToken(){
        token=null;
    }
}
