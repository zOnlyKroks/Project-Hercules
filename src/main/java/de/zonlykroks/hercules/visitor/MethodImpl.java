package de.zonlykroks.hercules.visitor;

import de.zonlykroks.hercules.antlr.HerculesParser;

import java.util.List;

public record MethodImpl(List<Object> args, HerculesParser.BlockContext ctx) {

    public MethodImpl(List<Object> args, HerculesParser.BlockContext ctx) {
        this.args = args;
        this.ctx = ctx;
    }

}
