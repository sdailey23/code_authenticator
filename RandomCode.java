package countdownauthenticator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 * Handles random code generation
 * @author dailey
 */
public class RandomCode extends cGenerator {

    public RandomCode() {
        super();
    }
    
    public RandomCode(String platform) {
        super(platform);
    }
    
    @Override
    protected String generateCode() {
        return random4x4();
    }
    
    private String random4x4() {
        Random r = new Random();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String randomId = ""; 
        int ID_LENGTH = 19;
        
        for (int i = 0; i < ID_LENGTH; i++) {
            switch (i) {
                case 4: case 9: case 14:
                    randomId = randomId + "_";
                    break;
                default:
                    if (r.nextInt(2) == 1) {
                        randomId = randomId + alphabet.charAt(r.nextInt(10));
                    } else if (r.nextInt(2) == 0) {
                        randomId = randomId + r.nextInt(10);
                    } else {
                        i = i - 1;
                        continue;
                    }
            }
        }
        
        if (randomId != "")  setAvailability(true); 
        return randomId.trim();
    }
    
}
