package de.zonlykroks.hercules.visitor;

import de.zonlykroks.hercules.antlr.HerculesParser;

import java.util.List;

public record MethodImpl(List<String> args, HerculesParser.BlockContext ctx, int argLength) {

    public MethodImpl(List<String> args, HerculesParser.BlockContext ctx, int argLength) {
        this.args = args;
        this.ctx = ctx;
        this.argLength = argLength;
    }

}
