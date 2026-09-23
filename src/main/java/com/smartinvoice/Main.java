package com.smartinvoice;

import com.formdev.flatlaf.FlatLightLaf;
import com.smartinvoice.ui.auth.LoginFrame;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        FlatLightLaf.setup();

        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}