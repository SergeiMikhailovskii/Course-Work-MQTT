package com.company;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static String sTopic;
    private static int iQos;
    private static MqttClient mqttClient;
    private static final String broker = "tcp://broker.mqttdashboard.com:1883";

    public static void main(String[] args) throws MqttException {
        Frame frame = new Frame();
        mqttClient = new MqttClient(broker,MqttClient.generateClientId(), new MemoryPersistence());  //URI, ClientId, Persistence
        frame.getConnect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int iPort;
                String sIp = frame.getBrokerAddressValue();
                String sUsername = frame.getUsernameValue();
                try {
                    iPort = frame.getPortValue();
                    MqttConnectOptions connectOptions = new MqttConnectOptions();
                    connectOptions.setCleanSession(true);
                    System.out.println("Connecting to broker: "+broker);
                    mqttClient.connect();
                    System.out.println("Connected");
                }catch (NumberFormatException exc){
                    System.out.println("Wrong port format");
                } catch (MqttException e1) {
                    e1.printStackTrace();
                }

            }
        });

        frame.getSubscribe().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sTopic = frame.getTopicValue();
            }
        });

        frame.getPublish().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sMessage = frame.getMessageValue();
                MqttMessage message = new MqttMessage(sMessage.getBytes());
                iQos = frame.getQosValue();
                message.setQos(iQos);
                mqttClient.publish(topic,message);
                System.out.println("Message published");
            }
        });

        frame.getDisconnect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mqttClient.disconnect();
                System.out.println("Disconnected");
            }
        });

    }
}
