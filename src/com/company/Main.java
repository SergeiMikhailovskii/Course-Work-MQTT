package com.company;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements MqttCallback {
    private static String sTopic;
    private static int iQos;
    private static MqttClient mqttClient;
    private static String sUsername;
    private static Frame frame = new Frame();

    public static void main(String[] args) throws MqttException {

        frame.getConnect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                try {
                    if (mqttClient.isConnected()) {
                        mqttClient.subscribe(sTopic);
                        MqttCallback callback = new MqttCallback() {
                            @Override
                            public void connectionLost(Throwable throwable) {

                            }

                            @Override
                            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                                frame.getTextArea().append(sUsername+": "+String.valueOf(mqttMessage)+'\n');
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
                System.out.println("Subscribed");
            }
        });

        frame.getPublish().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sMessage = frame.getMessageValue();
                MqttMessage message = new MqttMessage(sMessage.getBytes());
                iQos = frame.getQosValue();
                message.setQos(iQos);
                try {
                    if (mqttClient.isConnected()) {
                        mqttClient.publish(sTopic, message);
                    }
                } catch (MqttException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Message published");
            }
        });

        frame.getDisconnect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mqttClient.disconnect();
                } catch (MqttException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Disconnected");
            }
        });

    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception{
        //frame.getTextArea().setText(String.valueOf(message));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
