package de.zonlykroks.hercules.visitor;

import de.zonlykroks.hercules.antlr.HerculesBaseVisitor;
import de.zonlykroks.hercules.antlr.HerculesParser;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public class SimpleVisitor extends HerculesBaseVisitor<Object> {

    private final Map<String, @Nullable Object> variables = new Hashtable<>();
    private final List<String> finalVariables = new ArrayList<>();
    private final List<String> swallowingVariables = new ArrayList<>();

    public SimpleVisitor() {
        variables.put("Write", (Function<Object[], Object>) objects -> {
            for (Object object : objects) {
                System.out.println(object);
            }
            return null;
        });
    }

    @Override
    public Object visitFunctionCall(HerculesParser.FunctionCallContext ctx) {
        final String name = ctx.IDENTIFIER().getText();

        final List<Object> args = new ArrayList<>();

        ctx.expression().forEach(expressionContext -> {
            args.add(visit(expressionContext));
        });

        if(variables.containsKey(name)) {
            if(variables.get(name) instanceof Function<?,?> func) {
                return ((Function<Object[],Object>) func).apply(args.toArray());
            }
        }

        return null;
    }

    @Override
    public Object visitAssignment(HerculesParser.AssignmentContext ctx) {
        final boolean isFinal = ctx.getText().contains("final");

        final String varName = ctx.IDENTIFIER().getText();

        final Object value = visit(ctx.expression());

        if(!finalVariables.contains(varName)) {
            variables.put(varName,value);

            if(isFinal) {
                finalVariables.add(varName);

                if(ctx.getText().contains("(swallow)")) {
                    swallowingVariables.add(varName);
                }
            }
        }else {
            if(!swallowingVariables.contains(varName)) {
                throw new RuntimeException("Cannot reassign final variable: " + varName);
            }
        }

        return null;
    }

    @Override
    public Object visitIfBlock(HerculesParser.IfBlockContext ctx) {
        boolean expressionEvaluatesToTrue = isTrue(visit(ctx.expression()));

        if(expressionEvaluatesToTrue) {
            visit(ctx.block());
        }else if(ctx.elseIfBlock() != null) {
            visit(ctx.elseIfBlock());
        }

        return null;
    }

    @Override
    public Object visitElseIfBlock(HerculesParser.ElseIfBlockContext ctx) {
        if(ctx.block() != null) {
            return this.visit(ctx.block());
        }

        return null;
    }

    @Override
    public Object visitIdentifierExpression(HerculesParser.IdentifierExpressionContext ctx) {
        final String varName = ctx.IDENTIFIER().getText();

        return variables.getOrDefault(varName,null);
    }

    @Override
    public Object visitAddOpExpression(HerculesParser.AddOpExpressionContext ctx) {

        final Object left = visit(ctx.expression().get(0));
        final Object right = visit(ctx.expression().get(1));

        var op = ctx.addOp().getText();

        return switch (op) {
            case "+" : yield add(left, right);
            default: throw new IllegalArgumentException("Not implemented");
        };
    }

    @Override
    public Object visitMultOpExpression(HerculesParser.MultOpExpressionContext ctx) {
        final Object left = visit(ctx.expression().get(0));
        final Object right = visit(ctx.expression().get(1));

        var op = ctx.multOp().getText();

        return switch (op) {
            case "*" : yield mult(left, right);
            case "%" : yield modulo(left, right);
            default: throw new IllegalArgumentException("Not implemented");
        };
    }

    private Object add(Object left, Object right) {
        if(left instanceof Integer leftInt && right instanceof Integer rightInt) {
            return leftInt + rightInt;
        }

        if(left instanceof Float leftFloat && right instanceof Float rightFloat) {
            return leftFloat + rightFloat;
        }

        if(left instanceof Integer leftInt && right instanceof Float rightFloat) {
            return leftInt + rightFloat;
        }

        if(left instanceof Float leftFloat && right instanceof Integer rightInt) {
            return leftFloat + rightInt;
        }

        if(left instanceof String leftString && right instanceof String rightString) {
            return leftString + rightString;
        }

        if(left instanceof  String leftString) {
            return leftString + right;
        }

        if(right instanceof  String rightString) {
            return left + rightString;
        }

        return null;
    }

    private Object modulo(Object left, Object right) {
        if(left instanceof Integer leftInt && right instanceof Integer rightInt) {
            return leftInt % rightInt;
        }

        return null;
    }

    private Object mult(Object left, Object right) {
        if(left instanceof Integer leftInt && right instanceof Integer rightInt) {
            return leftInt * rightInt;
        }

        return null;
    }

    @Override
    public Object visitConstant(HerculesParser.ConstantContext ctx) {

        if(ctx.INTEGER() != null) {
            return Integer.parseInt(ctx.INTEGER().getText());
        }

        if(ctx.FLOAT() != null) {
            return Float.parseFloat(ctx.FLOAT().getText());
        }

        if(ctx.STRING() != null) {
            return ctx.STRING().getText().substring(1, ctx.STRING().getText().length() - 1);
        }

        if(ctx.BOOL() != null) {
            return Objects.equals(ctx.BOOL().getText(), "true");
        }

        return null;
    }

    @Override
    public Object visitWhileBlock(HerculesParser.WhileBlockContext ctx) {

        Function<Object, Boolean> condition = o -> ctx.WHILE().getText().equals("while") ? isTrue(o) : isFalse(o);

        if(condition.apply(visit(ctx.expression()))) {
            do {
                visit(ctx.block());
            }while(condition.apply(visit(ctx.expression())));
        }else if(ctx.elseIfBlock() != null){
            visit(ctx.elseIfBlock());
        }

       return null;
    }

    @Override
    public Object visitCompareOpExpression(HerculesParser.CompareOpExpressionContext ctx) {

        final Object left = visit(ctx.expression().get(0));
        final Object right = visit(ctx.expression().get(1));

        var op = ctx.compareOp().getText();

        return switch (op) {
            case "==" : yield isSame(left, right);
            case "!=" : yield !isSame(left,right);
            case ">" : yield !isSame(left,right) && !isLessThan(left,right);
            case "<" : yield isLessThan(left, right);
            case ">=" : yield isSame(left,right) || !isLessThan(left,right);
            case "<=" : yield isSame(left,right) || isLessThan(left,right);
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        };
    }

    private boolean isSame(Object left, Object right) {
        if(left instanceof Integer leftInt && right instanceof Integer rightInt) {
            return leftInt.equals(rightInt);
        }

        throw new IllegalArgumentException("Could not infer variable type");
    }

    private boolean isLessThan(Object left, Object right) {
        if(left instanceof Integer leftInt && right instanceof Integer rightInt) {
            return leftInt < rightInt;
        }

        throw new IllegalArgumentException("Could not infer variable type");
    }

    private boolean isTrue(Object value) {
        if(value instanceof Boolean bool) {
            return bool;
        }
        throw new IllegalArgumentException("Could not infer boolean value!");
    }

    private boolean isFalse(Object value) {
        return !isTrue(value);
    }
}
