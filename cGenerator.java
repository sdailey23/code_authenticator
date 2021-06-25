package countdownauthenticator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author dailey
 */
public class cGenerator {
    
    private String platform;
    private String code;
    
    private Boolean authenticationAvailable;
    
    protected cGenerator(String platform) {
        this.platform = platform;
        code = generateCode();
    }
    
    protected cGenerator() { // No platform input
        this("UNKNOWN");
    }
    
    public String getCode() {
        return code;
    }
    
    public void setAvailability(Boolean authenticationAvailable) {
        this.authenticationAvailable = authenticationAvailable;
    }
    
    public Boolean isAvailable() {
        return authenticationAvailable != null && authenticationAvailable == true;
    }
    
    public String getDisplayText() {
        if (isAvailable()) { 
            return "Authentication code for:" + "\n" + platform;
        } else {
            return "Authenticator" + "\nnot available";
        }
    }
    
    protected String generateCode() {
        setAvailability(false);
        return  "XXXX_XXXX_XXXX";
    }

}
