package com.email.writer.app;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailGenratorService {

    @Value("${gemini.api.url}")
    private String geminiApiKey;
    @Value("${gemini.api.key}")
    private String geminiUrl;
    private final WebClient webClient;
    public EmailGenratorService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public String generateEmailReply(EmailRequest emailrequest){
        //Build the prompt
        String prompt=buildPrompt(emailrequest);
        //Craft the response
        //DO request and response
        Map<String, Object> body = Map.of(
            "model", "claude-sonnet-4-20250514",
            "max_tokens", 512,
            "messages", List.of(
                Map.of(
                    "role", "user",
                    "content", prompt
                )
             )
         );

        String response = webClient.post()
            .uri(geminiUrl)
            .header("Content-Type", "application/json")
            .header("x-api-key", geminiApiKey)     
            .header("anthropic-version", "2023-06-01") // required
            .bodyValue(body)
            .retrieve()
            .bodyToMono(String.class)
            .block();


        //Return response
        return extractContentResponse(response);
    }


    public String extractContentResponse(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
    
            // Navigate: root → content (array) → first element → text
            return rootNode.path("content")
                           .get(0)
                           .path("text")
                           .asText();
        } catch (Exception e) {
            return "Error processing response: " + e.getMessage();
        }
    }
    

    public String buildPrompt(EmailRequest emailRequest){
          StringBuilder prompt=new StringBuilder();
          prompt.append("Generate a Professional email reply for the following email content.Please dont generate a Subject line.");
          if(emailRequest.getTone()!=null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a").append(emailRequest.getTone()).append(" tone.");
          }
          prompt.append("\n Original email: \n").append(emailRequest.getEmailContent());

          return prompt.toString();
    }
}
