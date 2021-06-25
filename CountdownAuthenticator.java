/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countdownauthenticator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Code authenticator reloads a random code every x seconds 
 * @author dailey
 */
public class CountdownAuthenticator {

    public static void main(String[] args) {

        final Timer timer;
        
        RandomCode code = new RandomCode("Social Media");
        
        // COMPONENTS
        
        JProgressBar progressBar = new JProgressBar();
        JButton button = new JButton("Generate Code");
        JLabel generatedCode = new JLabel("", SwingConstants.CENTER);
        JLabel preCodeText = new JLabel("", SwingConstants.CENTER);
        
        // WINDOW/PANELS
        
        JFrame window = new JFrame("Code Authenticator");
        
        //Text Panel
        JPanel content= new JPanel();  
        content.setLayout(new BorderLayout());
        content.setAlignmentX(window.getWidth()/2);
        content.add(button, BorderLayout.NORTH);
        content.add(preCodeText, BorderLayout.CENTER);
        content.add(generatedCode, BorderLayout.AFTER_LAST_LINE);
        
        //Progress Bar panel
        JPanel bar= new JPanel();  
        bar.setLayout(new FlowLayout());
        bar.add(progressBar, BorderLayout.AFTER_LAST_LINE);
        
        //Constraints
        button.setMargin(new Insets(15,0,15,0));
        Border border = generatedCode.getBorder();
        Border margin = new EmptyBorder(5,0,5,0);
        preCodeText.setBorder(new CompoundBorder(border, margin));
        
        // LISTENERS 
        ActionListener updateProgress = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int val = progressBar.getValue();
                
                progressBar.setStringPainted(true);
                progressBar.setString("expires in...");
                progressBar.setValue(++val);
                
                if (val >= 100) {
                    JOptionPane.showMessageDialog(
                            window, "CODE EXPIRED \nNew code generating");
                    preCodeText.setText(code.getDisplayText());
                    generatedCode.setText(code.generateCode());
                    progressBar.setValue(0);
                }
            }
        };
        
        timer = new Timer(120, updateProgress);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button.getText() == "Generate Code") {
                    timer.start();
                    preCodeText.setText(code.getDisplayText());
                    generatedCode.setText(code.generateCode());
                    button.setText("EXIT");
                } else if (button.getText() == "EXIT") {
                    timer.stop();
                    System.exit(0);
                }
            }
        });
        
        // WINDOW SETTERS
        
        window.getContentPane().add(content, BorderLayout.NORTH);
        window.getContentPane().add(bar, BorderLayout.SOUTH);
        
        center(window);
        window.setSize(300, 150);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    // OTHER
    
    private static void center(Window window) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - window.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - window.getHeight()) / 2);
        window.setLocation(x, y);
    }
}
