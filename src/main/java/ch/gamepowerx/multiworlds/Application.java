package ch.gamepowerx.multiworlds;

import javax.swing.*;
import java.awt.*;

public class Application {
    public static void main(String[] args) {
        if(!GraphicsEnvironment.isHeadless())
            JOptionPane.showMessageDialog(null, "This is a Minecraft Plugin, not a Java Application! You have to upload the plugin to the server and then restart the server to use this plugin!", "MultiWorlds", JOptionPane.ERROR_MESSAGE);
        System.out.println("ERROR: This is a Minecraft Plugin, not a Java Application! You have to upload the plugin to the server and then restart the server to use this plugin!");
    }
}