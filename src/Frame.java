import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    private JTextArea messageToEncryptTextArea;
    private JTextArea messageToDecryptTextArea;
    private JTextArea encryptionKeyTextArea;
    private JTextArea decryptionKeyTextArea;
    private JButton encryptButton;
    private JButton decryptButton;
    private JButton copyEncryptedButton;
    private JButton copyDecryptedButton;
    private JTextArea encryptedMessageTextArea;
    private JTextArea decryptedMessageTextArea;
    private JComboBox<String> algorithmSelectionBox;

    // constructor
    public Frame() {
        // Set the title, and set the layout of the frame to be BorderLayout
        setTitle("Symmetric Encryption Algorithms - Demo");
        setLayout(new BorderLayout());

        // Create three panels: left, right, and bottom, give them padding, and set box
        // layout
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.lightGray);
        leftPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.lightGray);
        rightPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.lightGray);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(new EmptyBorder(0, 50, 50, 50));

        // components on the left panel
        JPanel messageToEncryptPanel = new JPanel();
        messageToEncryptPanel.setLayout(new BoxLayout(messageToEncryptPanel, BoxLayout.Y_AXIS));
        messageToEncryptPanel.setBackground(Color.lightGray);
        JLabel messageToEncryptLabel = new JLabel("Message To Encrypt");
        messageToEncryptLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageToEncryptLabel.setBorder(new EmptyBorder(0, 110, 5, 0));
        messageToEncryptTextArea = new JTextArea(5, 30);
        JScrollPane messageToEncryptScrollPane = new JScrollPane(messageToEncryptTextArea);
        messageToEncryptPanel.add(messageToEncryptLabel);
        messageToEncryptPanel.add(messageToEncryptScrollPane);
        leftPanel.add(messageToEncryptPanel);

        JPanel encryptionKeyPanel = new JPanel();
        encryptionKeyPanel.setLayout(new BoxLayout(encryptionKeyPanel, BoxLayout.X_AXIS));
        encryptionKeyPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
        encryptionKeyPanel.setBackground(Color.lightGray);
        JLabel encryptionKeyLabel = new JLabel("Encryption Key");
        encryptionKeyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        encryptionKeyLabel.setBorder(new EmptyBorder(0, 0, 0, 50));
        encryptionKeyTextArea = new JTextArea(3, 5);
        JScrollPane encryptionKeyScrollPane = new JScrollPane(encryptionKeyTextArea);
        encryptionKeyPanel.add(encryptionKeyLabel);
        encryptionKeyPanel.add(encryptionKeyScrollPane);
        leftPanel.add(encryptionKeyPanel);

        JPanel encryptCopyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 110, 0));
        encryptCopyPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
        encryptCopyPanel.setBackground(Color.lightGray);
        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(this);
        encryptButton.setFont(new Font("Arial", Font.BOLD, 16));
        encryptButton.setBackground(Color.blue);
        encryptButton.setForeground(Color.white);
        copyEncryptedButton = new JButton("Copy Encrypted Message");
        copyEncryptedButton.addActionListener(this);
        copyEncryptedButton.setFont(new Font("Arial", Font.BOLD, 16));
        copyEncryptedButton.setBackground(Color.red);
        copyEncryptedButton.setForeground(Color.white);
        encryptCopyPanel.add(encryptButton);
        encryptCopyPanel.add(copyEncryptedButton);
        leftPanel.add(encryptCopyPanel);

        encryptedMessageTextArea = new JTextArea(5, 20);
        JScrollPane encryptedMessageScrollPane = new JScrollPane(encryptedMessageTextArea);
        leftPanel.add(encryptedMessageScrollPane);

        // components on the right panel
        JPanel messageToDecryptPanel = new JPanel();
        messageToDecryptPanel.setLayout(new BoxLayout(messageToDecryptPanel, BoxLayout.Y_AXIS));
        messageToDecryptPanel.setBackground(Color.lightGray);
        JLabel messageToDecryptLabel = new JLabel("Message To Decrypt");
        messageToDecryptLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageToDecryptLabel.setBorder(new EmptyBorder(0, 110, 5, 0));
        messageToDecryptTextArea = new JTextArea(5, 20);
        JScrollPane messageToDecryptScrollPane = new JScrollPane(messageToDecryptTextArea);
        messageToDecryptPanel.add(messageToDecryptLabel);
        messageToDecryptPanel.add(messageToDecryptScrollPane);
        rightPanel.add(messageToDecryptPanel);

        JPanel decryptionKeyPanel = new JPanel();
        decryptionKeyPanel.setLayout(new BoxLayout(decryptionKeyPanel, BoxLayout.X_AXIS));
        decryptionKeyPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
        decryptionKeyPanel.setBackground(Color.lightGray);
        JLabel decryptionKeyLabel = new JLabel("Decryption Key");
        decryptionKeyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        decryptionKeyLabel.setBorder(new EmptyBorder(0, 0, 0, 50));
        decryptionKeyTextArea = new JTextArea(3, 5);
        JScrollPane decryptionKeyScrollPane = new JScrollPane(decryptionKeyTextArea);
        decryptionKeyPanel.add(decryptionKeyLabel);
        decryptionKeyPanel.add(decryptionKeyScrollPane);
        rightPanel.add(decryptionKeyPanel);

        JPanel decryptCopyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 110, 0));
        decryptCopyPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
        decryptCopyPanel.setBackground(Color.lightGray);
        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(this);
        decryptButton.setFont(new Font("Arial", Font.BOLD, 16));
        decryptButton.setBackground(Color.blue);
        decryptButton.setForeground(Color.white);
        copyDecryptedButton = new JButton("Copy Decrypted Message");
        copyDecryptedButton.addActionListener(this);
        copyDecryptedButton.setFont(new Font("Arial", Font.BOLD, 16));
        copyDecryptedButton.setBackground(Color.red);
        copyDecryptedButton.setForeground(Color.white);
        decryptCopyPanel.add(decryptButton);
        decryptCopyPanel.add(copyDecryptedButton);
        rightPanel.add(decryptCopyPanel);

        decryptedMessageTextArea = new JTextArea(5, 20);
        JScrollPane decryptedMessageScrollPane = new JScrollPane(decryptedMessageTextArea);
        rightPanel.add(decryptedMessageScrollPane);

        // components on the bottom panel
        JPanel algoithmSelectionPanel = new JPanel();
        algoithmSelectionPanel.setLayout(new BoxLayout(algoithmSelectionPanel, BoxLayout.X_AXIS));
        algoithmSelectionPanel.setBackground(Color.lightGray);
        JLabel algorithmSelectionLabel = new JLabel("Select encryption algorithm");
        algorithmSelectionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        algorithmSelectionLabel.setBorder(new EmptyBorder(0, 0, 5, 100));
        String[] algorithms = { "OTP", "3DES", "AES" };
        algorithmSelectionBox = new JComboBox<>(algorithms);
        algoithmSelectionPanel.add(algorithmSelectionLabel);
        algoithmSelectionPanel.add(algorithmSelectionBox);
        bottomPanel.add(algoithmSelectionPanel);

        // Add the three panels to west, east and south of the frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Display the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String algorithm = (String) algorithmSelectionBox.getSelectedItem();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        if (e.getSource() == encryptButton) {
            String message = messageToEncryptTextArea.getText();
            String key = encryptionKeyTextArea.getText();

            if (algorithm == "OTP") {
                encryptedMessageTextArea.setText(OneTimePad.encrypt(message, key));
            } else if (algorithm == "3DES") {
                TripleDesAes des3 = new TripleDesAes("DESede");
                encryptedMessageTextArea.setText(des3.encrypt(message, key));
            } else {
                TripleDesAes aes = new TripleDesAes("AES");
                encryptedMessageTextArea.setText(aes.encrypt(message, key));
            }

        } else if (e.getSource() == decryptButton) {
            String message = messageToDecryptTextArea.getText();
            String key = decryptionKeyTextArea.getText();

            if (algorithm == "OTP") {
                decryptedMessageTextArea.setText(OneTimePad.decrypt(message, key));
            } else if (algorithm == "3DES") {
                TripleDesAes des3 = new TripleDesAes("DESede");
                decryptedMessageTextArea.setText(des3.decrypt(message, key));
            } else {
                TripleDesAes aes = new TripleDesAes("AES");
                decryptedMessageTextArea.setText(aes.decrypt(message, key));
            }
        } else if (e.getSource() == copyEncryptedButton) {
            StringSelection stringSelection = new StringSelection(encryptedMessageTextArea.getText());
            clipboard.setContents(stringSelection, null);
        } else if (e.getSource() == copyDecryptedButton) {
            StringSelection stringSelection = new StringSelection(decryptedMessageTextArea.getText());
            clipboard.setContents(stringSelection, null);
        }
    }

}
