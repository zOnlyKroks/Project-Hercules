package de.zonlykroks.hercules.visitor;

import de.zonlykroks.hercules.antlr.HerculesBaseVisitor;
import de.zonlykroks.hercules.antlr.HerculesParser;

import java.util.*;
import java.util.function.Function;

public class SimpleVisitor extends HerculesBaseVisitor<Object> {

    private final Scope scope;

    public SimpleVisitor(Scope scope) {
        this.scope = scope;

        scope.addVariable("Write", (Function<Object[], Object>) objects -> {
            for (Object object : objects) {
                System.out.println(object);
            }
            return null;
        });

        scope.addFinalVariable("Write");

        scope.addVariable("exit", (Function<Object[], Object>) objects -> {
            System.exit(-1);
            return null;
        });

        scope.addFinalVariable("exit");
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object visitFunctionCall(HerculesParser.FunctionCallContext ctx) {
        Scope functionScope = new Scope(this.scope);
        SimpleVisitor visitor = new SimpleVisitor(functionScope);

        final String name = ctx.IDENTIFIER().getText();

        if(scope.isVariableVisible(name)) {
            if(scope.getVariable(name) instanceof Function<?,?>) {

                final List<Object> args = new ArrayList<>();

                ctx.expression().forEach(expressionContext -> {
                    Object arg = visit(expressionContext);
                    args.add(arg);
                });

                return ((Function<Object[], Object>) scope.getVariable(name)).apply(args.toArray());
            }
        }

        if(scope.isFunctionVisible(name)) {
            MethodImpl impl = scope.getMethod(name);

            if(ctx.expression().size() != impl.argLength()) {
                throw new RuntimeException("Method " + name + " has a arg length mismatch, provided: " + ctx.expression().size() + " , needed: " + ctx.expression().size());
            }

            for(int i = 0; i < ctx.expression().size(); i++) {
                visitor.scope.addVariable(impl.args().get(i), visit(ctx.expression(i)));
            }

            return visitor.visit(impl.ctx());
        }

        throw new RuntimeException("Method " + name + " does not exist, neither as built-in or self declared, check order!");
    }

    @Override
    public Object visitMethodDecl(HerculesParser.MethodDeclContext ctx) {
        final String methodName = ctx.IDENTIFIER(0).getText();

        final List<String> args = new ArrayList<>();

        for(int i = 1; i < ctx.IDENTIFIER().size(); i++) {
            args.add(ctx.IDENTIFIER(i).getText());
        }

        scope.addMethod(methodName, new MethodImpl(args, ctx.block(), args.size()));

        return null;
    }

    @Override
    public Object visitAssignment(HerculesParser.AssignmentContext ctx) {
        final boolean isFinal = ctx.getText().contains("final");

        final String varName = ctx.IDENTIFIER().getText();

        final Object value = visit(ctx.expression());

        if(!scope.isFinalVariable(varName)) {
            scope.addVariable(varName,value);

            if(isFinal) {
                scope.addFinalVariable(varName);

                if(ctx.getText().contains("(swallow)")) {
                    scope.addSwallowingVariable(varName);
                }
            }
        }else {
            if(!scope.containsSwallowingVariable(varName)) {
                throw new RuntimeException("Cannot reassign final variable: " + varName);
            }
        }

        return null;
    }

    @Override
    public Object visitIfBlock(HerculesParser.IfBlockContext ctx) {
        Scope ifBlockScope = new Scope(this.scope);
        SimpleVisitor visitor = new SimpleVisitor(ifBlockScope);

        boolean expressionEvaluatesToTrue = isTrue(visit(ctx.expression()));

        if(expressionEvaluatesToTrue) {
            return visitor.visit(ctx.block());
        }else if(ctx.elseIfBlock() != null) {
            return visitor.visit(ctx.elseIfBlock());
        }

        return null;
    }

    @Override
    public Object visitElseIfBlock(HerculesParser.ElseIfBlockContext ctx) {
        Scope elseIfBlockScope = new Scope(this.scope);
        SimpleVisitor visitor = new SimpleVisitor(elseIfBlockScope);


        if(ctx.block() != null) {
            return visitor.visit(ctx.block());
        }

        return null;
    }

    @Override
    public Object visitIdentifierExpression(HerculesParser.IdentifierExpressionContext ctx) {
        final String varName = ctx.IDENTIFIER().getText();

        if(scope.isVariableVisible(varName)) {
            return scope.getVariable(varName);
        }

        return null;
    }

    @Override
    public Object visitAddOpExpression(HerculesParser.AddOpExpressionContext ctx) {

        final Object left = visit(ctx.expression().get(0));
        final Object right = visit(ctx.expression().get(1));

        final String op = ctx.addOp().getText();

        return switch (op) {
            case "+" : yield add(left, right);
            case "-" : yield sub(left, right);
            default: throw new IllegalArgumentException("Not implemented");
        };
    }

    @Override
    public Object visitMultOpExpression(HerculesParser.MultOpExpressionContext ctx) {
        final Object left = visit(ctx.expression().get(0));
        final Object right = visit(ctx.expression().get(1));

        var op = ctx.multOp().getText();

        return switch (op) {
            case "*" : yield multi(left, right);
            case "/" : yield div(left, right);
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

    private Object div(Object left, Object right) {
        if (left instanceof Integer leftInt && right instanceof Integer rightInt) {
            return leftInt / rightInt;
        }

        if (left instanceof Float leftFloat && right instanceof Float rightFloat) {
            return leftFloat / rightFloat;
        }

        if (left instanceof Integer leftInt && right instanceof Float rightFloat) {
            return leftInt / rightFloat;
        }

        if (left instanceof Float leftFloat && right instanceof Integer rightInt) {
            return leftFloat / rightInt;
        }

        return null;
    }

    private Object sub(Object left, Object right) {
        if (left instanceof Integer leftInt && right instanceof Integer rightInt) {
            return leftInt - rightInt;
        }

        if (left instanceof Float leftFloat && right instanceof Float rightFloat) {
            return leftFloat - rightFloat;
        }

        if (left instanceof Integer leftInt && right instanceof Float rightFloat) {
            return leftInt - rightFloat;
        }

        if (left instanceof Float leftFloat && right instanceof Integer rightInt) {
            return leftFloat - rightInt;
        }

        return null;
    }

    private Object modulo(Object left, Object right) {
        if(left instanceof Integer leftInt && right instanceof Integer rightInt) {
            return leftInt % rightInt;
        }

        return null;
    }

    private Object multi(Object left, Object right) {
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
        Scope whileBlockScope = new Scope(this.scope);
        SimpleVisitor visitor = new SimpleVisitor(whileBlockScope);

        Function<Object, Boolean> condition = o -> ctx.WHILE().getText().equals("while") ? isTrue(o) : isFalse(o);

        if(condition.apply(visit(ctx.expression()))) {
            do {
                visitor.visit(ctx.block());
            }while(condition.apply(visitor.visit(ctx.expression())));
        }else if(ctx.elseIfBlock() != null){
            return visitor.visit(ctx.elseIfBlock());
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

    @Override
    public Object visitBoolOpExpression(HerculesParser.BoolOpExpressionContext ctx) {
        final Object left = visit(ctx.expression(0));
        final Object right = visit(ctx.expression(1));

        var op = ctx.boolOp().getText();

        return switch(op) {
            case "and": yield boolAnd(left, right);
            case "or": yield boolOr(left,right);
            case "xor": yield boolXor(left, right);
            default:
                throw new IllegalArgumentException("Unexpected value: " + op);
        };
    }

    private Object boolAnd(Object left, Object right) {
        if(left instanceof Boolean leftBool && right instanceof Boolean rightBool) {
            return leftBool && rightBool;
        }

        throw new RuntimeException("Left or right not boolean, left: " + left + " , right: " + right);
    }

    private Object boolOr(Object left, Object right) {
        if(left instanceof Boolean leftBool && right instanceof Boolean rightBool) {
            return leftBool || rightBool;
        }

        throw new RuntimeException("Left or right not boolean, left: " + left + " , right: " + right);
    }

    private Object boolXor(Object left, Object right) {
        if(left instanceof Boolean leftBool && right instanceof Boolean rightBool) {
            return leftBool ^ rightBool;
        }

        throw new RuntimeException("Left or right not boolean, left: " + left + " , right: " + right);
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
