package com.example.Lingua;

import com.example.Lingua.formatter.TextFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ConsoleOutputWriter {

    private TextFormatter textFormatter;

    @Autowired
    ConsoleOutputWriter(TextFormatter textFormatter){
        this.textFormatter = textFormatter;
    }

    void println(String text){
        String formattedText = textFormatter.format(text);
        System.out.println(formattedText);
    }
}
