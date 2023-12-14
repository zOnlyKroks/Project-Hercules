package de.zonlykroks.hercules.antlr;// Generated from ./src/main/java/de/zonlykroks/hercules/antlr/Hercules.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HerculesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HerculesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HerculesParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(HerculesParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(HerculesParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(HerculesParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#ifBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBlock(HerculesParser.IfBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#elseIfBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseIfBlock(HerculesParser.ElseIfBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#whileBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileBlock(HerculesParser.WhileBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(HerculesParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(HerculesParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareOpExpression(HerculesParser.CompareOpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(HerculesParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(HerculesParser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(HerculesParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddOpExpression(HerculesParser.AddOpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpression(HerculesParser.FunctionCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultOpExpression(HerculesParser.MultOpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(HerculesParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolOpExpression}
	 * labeled alternative in {@link HerculesParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOpExpression(HerculesParser.BoolOpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#multOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultOp(HerculesParser.MultOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#addOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddOp(HerculesParser.AddOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#compareOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareOp(HerculesParser.CompareOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#boolOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOp(HerculesParser.BoolOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(HerculesParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link HerculesParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(HerculesParser.BlockContext ctx);
}