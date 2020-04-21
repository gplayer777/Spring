package com.example.Lingua.formatter;


import org.springframework.stereotype.Component;

@Component
class BasicTextFormatter implements TextFormatter {

    @Override
    public String format(String originalText){
        return originalText;
    }

}
