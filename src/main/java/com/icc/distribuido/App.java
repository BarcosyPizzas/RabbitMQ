package com.icc.distribuido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

// Entry point of computo paralelo y distribuido final project(En ingles para sonar pro)
@SpringBootApplication
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run(App.class, args);
    
        String queueName = System.getenv("QUEUE_NAME") != null
            ? System.getenv("QUEUE_NAME")
            : "cetys_test";
        System.out.println("hola mundo");
    
        if (args.length == 0 || Arrays.asList(args).contains("receiver")) {
            Receiver receiver = new Receiver(queueName);
            receiver.receiveMessage();
        }
        if (args.length == 0 || Arrays.asList(args).contains("listener")) {
            Receiver receiver = new Receiver(queueName);
            receiver.subscribeMessage();
        }
        if (args.length == 0 || Arrays.asList(args).contains("sender")) {
            Sender sender = new Sender(queueName);
            sender.sendMessage("otro mensaje");
        }
        if (args.length == 0 || Arrays.asList(args).contains("publisher")) {
            Sender sender = new Sender(queueName);
            sender.publishMessage("publicar mensaje");
        }
    }
}

