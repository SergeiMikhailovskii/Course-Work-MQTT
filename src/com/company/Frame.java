package com.company;

import javax.swing.*;
import java.util.Objects;

class Frame extends JFrame {

    private JTextField port = new JTextField(4);
    private JTextField brokerAddress = new JTextField(10);
    private JTextField topic = new JTextField(10);

    JTextField getMessage() {
        return message;
    }

    private JTextField message = new JTextField(20);
    private JTextField username = new JTextField(10);

    private final String[] items = {"0","1","2"};
    private JComboBox<String> qos = new JComboBox<>(items);

    JTextArea getTextArea() {
        return textArea;
    }

    private JTextArea textArea = new JTextArea();

    JButton getSubscribe() {
        return subscribe;
    }

    JButton getUnsubscribe() {
        return unsubscribe;
    }

    JButton getPublish() {
        return publish;
    }

    private JButton subscribe = new JButton("Subscribe");
    private JButton unsubscribe = new JButton("Unsubscribe");
    private JButton publish = new JButton("Publish");

    JButton getConnectAndDisconnect() {
        return connectAndDisconnect;
    }

    private JButton connectAndDisconnect = new JButton("Connect");

    JButton getClearMessageField() {
        return clearMessageField;
    }

    private JButton clearMessageField = new JButton("Clear message field");

    int getPortValue() {
        return Integer.parseInt(port.getText());
    }

    String getBrokerAddressValue() {
        return brokerAddress.getText();
    }

    String getTopicValue() {
        return topic.getText();
    }

    String getMessageValue() {
        return message.getText();
    }

    String getUsernameValue() {
        return username.getText();
    }

    int getQosValue() {
        return Integer.parseInt((String) Objects.requireNonNull(qos.getSelectedItem()));
    }


    Frame(){
        super("MQTT client");
        JPanel jPanel = new JPanel();
        getContentPane().add(jPanel);
        jPanel.setLayout(null);

        JLabel portText = new JLabel("Port:");
        portText.setLocation(60,30);
        portText.setSize(50,20);
        jPanel.add(portText);

        port.setLocation(60,60);
        port.setSize(50,20);
        port.setText("1883");
        jPanel.add(port);

        JLabel brokerAddressText = new JLabel("Broker ip address:");
        brokerAddressText.setLocation(150,30);
        brokerAddressText.setSize(120,20);
        jPanel.add(brokerAddressText);

        brokerAddress.setLocation(150,60);
        brokerAddress.setSize(120,20);
        brokerAddress.setText("127.0.0.1");
        jPanel.add(brokerAddress);


        connectAndDisconnect.setLocation(300,60);
        connectAndDisconnect.setSize(100,20);
        jPanel.add(connectAndDisconnect);

        JLabel topicText = new JLabel("Topic:");
        topicText.setLocation(60,110);
        topicText.setSize(80,20);
        jPanel.add(topicText);

        topic.setLocation(60,140);
        topic.setSize(80,20);
        topic.setText("111111");
        jPanel.add(topic);

        JLabel usernameText = new JLabel("Username:");
        usernameText.setLocation(160,110);
        usernameText.setSize(100,20);
        jPanel.add(usernameText);

        username.setLocation(160,140);
        username.setSize(100,20);
        username.setText("Sergei");
        jPanel.add(username);

        JLabel qosText = new JLabel("Qos:");
        qosText.setLocation(280,110);
        qosText.setSize(50,20);
        jPanel.add(qosText);

        qos.setLocation(280,140);
        qos.setSize(50,20);
        jPanel.add(qos);

        subscribe.setLocation(350,110);
        subscribe.setSize(120,20);
        jPanel.add(subscribe);

        unsubscribe.setLocation(350,140);
        unsubscribe.setSize(120,20);
        jPanel.add(unsubscribe);

        JLabel messageText = new JLabel("Message:");
        messageText.setLocation(60,190);
        messageText.setSize(80,20);
        jPanel.add(messageText);

        message.setLocation(150,190);
        message.setSize(200,20);
        jPanel.add(message);

        publish.setLocation(370,190);
        publish.setSize(150,20);
        jPanel.add(publish);

        clearMessageField.setLocation(370,220);
        clearMessageField.setSize(150,20);
        jPanel.add(clearMessageField);

        textArea.setLocation(60,250);
        textArea.setSize(500,200);
        jPanel.add(textArea);

        jPanel.revalidate();
        setBounds(750,250,600,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
