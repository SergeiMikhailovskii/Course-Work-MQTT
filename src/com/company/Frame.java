package com.company;

import javax.swing.*;

public class Frame extends JFrame {

    private JTextField port = new JTextField(4);
    private JTextField brokerAddress = new JTextField(10);
    private JTextField topic = new JTextField(10);
    private JTextField message = new JTextField(20);
    private JTextField username = new JTextField(10);
    private JTextField qos = new JTextField();
    private JTextArea textArea = new JTextArea();

    public JButton getConnect() {
        return connect;
    }

    public JButton getDisconnect() {
        return disconnect;
    }

    public JButton getSubscribe() {
        return subscribe;
    }

    public JButton getUnsubscribe() {
        return unsubscribe;
    }

    public JButton getPublish() {
        return publish;
    }

    private JButton connect = new JButton("Connect");
    private JButton disconnect = new JButton("Disconnect");
    private JButton subscribe = new JButton("Subscribe");
    private JButton unsubscribe = new JButton("Unsubscribe");
    private JButton publish = new JButton("Publish");

    public int getPortValue() {
        return Integer.parseInt(port.getText());
    }

    public String getBrokerAddressValue() {
        return brokerAddress.getText();
    }

    public String getTopicValue() {
        return topic.getText();
    }

    public String getMessageValue() {
        return message.getText();
    }

    public String getUsernameValue() {
        return username.getText();
    }

    public int getQosValue() {
        return Integer.parseInt(qos.getText());
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
        jPanel.add(port);

        JLabel brokerAddressText = new JLabel("Broker ip address:");
        brokerAddressText.setLocation(150,30);
        brokerAddressText.setSize(120,20);
        jPanel.add(brokerAddressText);

        brokerAddress.setLocation(150,60);
        brokerAddress.setSize(120,20);
        jPanel.add(brokerAddress);

        connect.setLocation(300,30);
        connect.setSize(100,20);
        jPanel.add(connect);

        disconnect.setLocation(300,60);
        disconnect.setSize(100,20);
        jPanel.add(disconnect);

        JLabel topicText = new JLabel("Topic:");
        topicText.setLocation(60,110);
        topicText.setSize(80,20);
        jPanel.add(topicText);

        topic.setLocation(60,140);
        topic.setSize(80,20);
        jPanel.add(topic);

        JLabel usernameText = new JLabel("Username:");
        usernameText.setLocation(160,110);
        usernameText.setSize(100,20);
        jPanel.add(usernameText);

        username.setLocation(160,140);
        username.setSize(100,20);
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
        publish.setSize(100,20);
        jPanel.add(publish);

        textArea.setLocation(60,250);
        textArea.setSize(400,200);
        jPanel.add(textArea);

        jPanel.revalidate();
        setBounds(750,250,500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
