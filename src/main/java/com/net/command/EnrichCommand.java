package com.net.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class EnrichCommand implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        //Do something
        return Command.CONTINUE_PROCESSING;
    }
}
