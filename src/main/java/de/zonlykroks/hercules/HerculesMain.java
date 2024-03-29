package de.zonlykroks.hercules;

import de.zonlykroks.hercules.antlr.HerculesLexer;
import de.zonlykroks.hercules.antlr.HerculesParser;
import de.zonlykroks.hercules.visitor.Scope;
import de.zonlykroks.hercules.visitor.SimpleVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;

public class HerculesMain {

    public static final Scope global = new Scope(null);

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        CharStream inputStream = new ANTLRInputStream(new FileInputStream(
                "C:/Users/finnr/Desktop/Steamwar Development/Project-Hercules/src/main/java/de/zonlykroks/hercules/antlr/test.hc"
        ));

        HerculesLexer lexer = new HerculesLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HerculesParser parser = new HerculesParser(tokens);

        ParseTree tree = parser.program();

        SimpleVisitor visitor = new SimpleVisitor(global);
        visitor.visit(tree);
    }

}
