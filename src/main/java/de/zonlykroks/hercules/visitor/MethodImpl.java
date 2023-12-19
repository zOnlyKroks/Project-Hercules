package de.zonlykroks.hercules.visitor;

import de.zonlykroks.hercules.antlr.HerculesParser;

import java.util.List;

public record MethodImpl(List<String> args, HerculesParser.BlockContext ctx, int argLength) {
}
