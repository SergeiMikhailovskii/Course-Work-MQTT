package com.company;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.swing.*;
import java.util.Date;

public class Main{
    private static String sTopic;
    private static MqttClient mqttClient;
    private static String sUsername;
    private static Frame frame = new Frame();
    private static boolean isConnected = false;

    public static void main(String[] args){

        frame.getSubscribe().addActionListener(e -> subscribe());

        frame.getPublish().addActionListener(e -> publish());

        frame.getUnsubscribe().addActionListener(e -> unSubscribe());

        frame.getClearMessageField().addActionListener(e -> clearMessageField());

        frame.getConnectAndDisconnect().addActionListener(e -> {
            if (!isConnected){
                frame.getConnectAndDisconnect().setText("Disconnect");
                isConnected = true;
                connect();
            }
            else{
                frame.getConnectAndDisconnect().setText("Connect");
                isConnected = false;
                disconnect();
            }
        });
    }

    private static void connect(){
        int iPort;
        String sIp = frame.getBrokerAddressValue();
        sUsername = frame.getUsernameValue();
        try {
            String broker = "tcp://"; //bridge and host
            iPort = frame.getPortValue();
            broker+=sIp+":"+iPort;
            mqttClient = new MqttClient(broker, sUsername, new MemoryPersistence());  //URI, ClientId, Persistence
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            mqttClient.connect();
            JOptionPane.showMessageDialog(null, "Connected", "Connected", JOptionPane.INFORMATION_MESSAGE);
        }catch (NumberFormatException exc){
            JOptionPane.showMessageDialog(null, "Wrong port format", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (MqttException e1) {
            JOptionPane.showMessageDialog(null, "Can't connect on this port!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void subscribe(){
        sTopic = frame.getTopicValue();
        try {
            if (mqttClient.isConnected()) {
                mqttClient.subscribe(sTopic);
                MqttCallback callback = new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable throwable) {
                        JOptionPane.showMessageDialog(null, "Connection lost because: "+throwable, "Error", JOptionPane.WARNING_MESSAGE);
                        isConnected = false;
                        frame.getConnectAndDisconnect().setText("Connect");
//                        System.exit(1);
                        while (!mqttClient.isConnected()){
                            connect();
                        }
                    }

                    @Override
                    public void messageArrived(String s, MqttMessage mqttMessage) {
                        Date date = new Date();
                        String sDate = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                        frame.getTextArea().append(String.valueOf(mqttMessage)+"\t\t\t"+sDate+'\n');
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                    }
                };
                mqttClient.setCallback(callback);
            }
        } catch (MqttException e1) {
            e1.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Subscribed", "Subscribed", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void publish(){
        String sMessage = sUsername+": "+frame.getMessageValue();
        MqttMessage message = new MqttMessage(sMessage.getBytes());
        int iQos = frame.getQosValue();
        message.setQos(iQos);
        try {
            if (mqttClient.isConnected()) {
                mqttClient.publish(sTopic, message);
                frame.getMessage().setText("");
            }
        } catch (MqttException e1) {
            e1.printStackTrace();
        }
    }

    private static void disconnect(){
        try {
            mqttClient.disconnect();
        } catch (MqttException e1) {
            e1.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Disconnected", "Disconnected", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void unSubscribe(){
        try {
            mqttClient.unsubscribe(sTopic);
        } catch (MqttException e1) {
            e1.printStackTrace();
        }
    }

    private static void clearMessageField(){
        frame.getTextArea().setText("");
    }

}