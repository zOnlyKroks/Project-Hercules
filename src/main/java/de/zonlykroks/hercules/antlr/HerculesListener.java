package de.zonlykroks.hercules.antlr;// Generated from ./src/main/java/de/zonlykroks/hercules/antlr/Hercules.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HerculesParser}.
 */
public interface HerculesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HerculesParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(HerculesParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(HerculesParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(HerculesParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(HerculesParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(HerculesParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(HerculesParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void enterIfBlock(HerculesParser.IfBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void exitIfBlock(HerculesParser.IfBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#whileBlock}.
	 * @param ctx the parse tree
	 */
	void enterWhileBlock(HerculesParser.WhileBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#whileBlock}.
	 * @param ctx the parse tree
	 */
	void exitWhileBlock(HerculesParser.WhileBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#elseIfBlock}.
	 * @param ctx the parse tree
	 */
	void enterElseIfBlock(HerculesParser.ElseIfBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#elseIfBlock}.
	 * @param ctx the parse tree
	 */
	void exitElseIfBlock(HerculesParser.ElseIfBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(HerculesParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(HerculesParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(HerculesParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(HerculesParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compareOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompareOpExpression(HerculesParser.CompareOpExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compareOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompareOpExpression(HerculesParser.CompareOpExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(HerculesParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(HerculesParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(HerculesParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(HerculesParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(HerculesParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(HerculesParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddOpExpression(HerculesParser.AddOpExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddOpExpression(HerculesParser.AddOpExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(HerculesParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(HerculesParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultOpExpression(HerculesParser.MultOpExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultOpExpression(HerculesParser.MultOpExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(HerculesParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(HerculesParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolOpExpression(HerculesParser.BoolOpExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolOpExpression(HerculesParser.BoolOpExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#multOp}.
	 * @param ctx the parse tree
	 */
	void enterMultOp(HerculesParser.MultOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#multOp}.
	 * @param ctx the parse tree
	 */
	void exitMultOp(HerculesParser.MultOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#addOp}.
	 * @param ctx the parse tree
	 */
	void enterAddOp(HerculesParser.AddOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#addOp}.
	 * @param ctx the parse tree
	 */
	void exitAddOp(HerculesParser.AddOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#compareOp}.
	 * @param ctx the parse tree
	 */
	void enterCompareOp(HerculesParser.CompareOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#compareOp}.
	 * @param ctx the parse tree
	 */
	void exitCompareOp(HerculesParser.CompareOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void enterBoolOp(HerculesParser.BoolOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void exitBoolOp(HerculesParser.BoolOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(HerculesParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(HerculesParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link HerculesParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(HerculesParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HerculesParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(HerculesParser.BlockContext ctx);
}