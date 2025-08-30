package com.anish.journalApp.service;

import com.anish.journalApp.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class SentimentConsumerService {

    @Autowired
    private MailService mailService;

    @KafkaListener(topics= "weekly_sentiments", groupId = "weekly-sentiment-group")
    public void consume(SentimentData sentimentData){
       sendEmail(sentimentData);
    }

    public void sendEmail(SentimentData sentimentData){
        mailService.sendMail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
    }

}
