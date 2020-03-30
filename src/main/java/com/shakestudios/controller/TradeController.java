package com.shakestudios.controller;

import com.shakestudios.Answers;
import com.shakestudios.Merchant;
import com.shakestudios.knowledge.PlanetSymbols;
import com.shakestudios.knowledge.Symbols;
import com.shakestudios.questions.QuestionTypes;
import io.swagger.models.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Stream;

@RestController
public class TradeController {

    @GetMapping("/api/{question}/{questTypes}")
    public Response GetTradeeQuestion (@PathVariable("question") String question)
    {
        PlanetSymbols planetSymbols = new PlanetSymbols();
        Symbols symbols = new Symbols(planetSymbols.getPlanetSymbolMap());
        Merchant merchant =  new Merchant(symbols);
        Answers answer = merchant.trade(question);
        Map.Entry<String,String>  val = answer.getQuestionAnswerMap().entrySet().stream().findFirst().get();
        return new Response().description(val.getKey() + " : " +   val.getValue());
    }


}
