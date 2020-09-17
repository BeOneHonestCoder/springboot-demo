package com.net.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.ChainBase;

public class CommandChain extends ChainBase {
    public static CommandChain create() {
        return new CommandChain();
    }

    public CommandChain add(Command command) {
        addCommand(command);
        return this;
    }
}
