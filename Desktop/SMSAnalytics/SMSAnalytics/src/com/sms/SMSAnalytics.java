package com.sms;
import java.util.*;
import java.util.stream.Collectors;

public class SMSAnalytics {
    public static void main(String[] args) {
        List<SMSRecord> smsRecords = new ArrayList<>();
        smsRecords.add(new SMSRecord("9870060012", "9876543210", 32, "outgoing", "have a good day"));
       smsRecords.add(new SMSRecord("8197141612", "9876543216", 54, "incoming", "happy world"));
       smsRecords.add(new SMSRecord("9876987612", "9876543210", 42, "outgoing", "have a good day"));
       smsRecords.add(new SMSRecord("9887665442", "9876543210", 12, "outgoing", "have a good day"));
       smsRecords.add(new SMSRecord("8197141614", "9876543215", 12, "incoming", "terrible world"));
       smsRecords.add(new SMSRecord("8197141632", "9876543223", 42, "incoming", "terrible world"));
              
                
        
        double averageMessageLength = calculateAverageMessageLength(smsRecords);
        System.out.println("Average Message Length: " + averageMessageLength);
        
       
        List<String> topSenders = identifyTopSenders(smsRecords);
        System.out.println("Top Senders: " + topSenders);
        
       
        String sentiment = performSentimentAnalysis(smsRecords);
        System.out.println("Sentiment Analysis: " + sentiment);
    }
    
    public static double calculateAverageMessageLength(List<SMSRecord> smsRecords) {
        double totalMessageLength = smsRecords.stream()
            .mapToInt(SMSRecord::getMessageLength)
            .sum();
        return totalMessageLength / smsRecords.size();
    }
    
    public static List<String> identifyTopSenders(List<SMSRecord> smsRecords) {
        Map<String, Long> senderMessageCounts = smsRecords.stream()
            .filter(record -> record.getMessageType().equals("outgoing"))
            .collect(Collectors.groupingBy(SMSRecord::getSenderNumber, Collectors.counting()));
        
        return senderMessageCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }
    
    public static String performSentimentAnalysis(List<SMSRecord> smsRecords) {
        int positiveCount = 0;
        int negativeCount = 0;

        List<String> positiveWords = Arrays.asList("happy", "great", "awesome");
        List<String> negativeWords = Arrays.asList("sad", "bad", "terrible");

        for (SMSRecord record : smsRecords) {
            if (record.getMessageType().equals("incoming")) {  
                String message = record.getMessageText().toLowerCase();  
                for (String word : positiveWords) {
                    if (message.contains(word)) {
                        positiveCount++;
                    }
                }
                for (String word : negativeWords) {
                    if (message.contains(word)) {
                        negativeCount++;
                    }
                }
            }
        }

        if (positiveCount > negativeCount) {
            return "positive";
        } else if (negativeCount > positiveCount) {
            return "negative";
        } else {
            return "neutral";
        }
    }

}

       
    

