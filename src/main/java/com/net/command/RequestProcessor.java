package com.net.command;

import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.springframework.stereotype.Component;

@Component
public class RequestProcessor {

    public void process(String payload) throws Exception {
        Context context = new ContextBase();
        //define flowKey
        CommandChain chain = CommandChain.create().add(new EnrichCommand());
        try {
            chain.execute(context);
        } catch (Exception e) {
            //Do something
        }
    }

}
