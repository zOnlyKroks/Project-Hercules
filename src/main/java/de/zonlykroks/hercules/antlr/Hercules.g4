grammar Hercules;

program: line* EOF;

line: statement | ifBlock | whileBlock | methodDecl;

statement: (assignment | functionCall) ';';

ifBlock: 'if' expression block ('else' elseIfBlock)?;

whileBlock: WHILE expression block ('else' elseIfBlock)?;

WHILE: 'while' | 'until';

elseIfBlock: block | ifBlock;

assignment: 'final'? 'swallow'? 'global'? IDENTIFIER '=' expression;

functionCall: IDENTIFIER '(' (expression (',' expression)*)? ')';

methodDecl: 'def' IDENTIFIER '('? (IDENTIFIER (',' IDENTIFIER)*)? ')'? block;

expression
    :   constant                                #constantExpression
    |   IDENTIFIER                              #identifierExpression
    |   functionCall                            #functionCallExpression
    |   '(' expression ')'                      #parenthesizedExpression
    |   '!' expression                          #notExpression
    |   expression multOp expression            #multOpExpression
    |   expression addOp expression             #addOpExpression
    |   expression compareOp expression         #compareOpExpression
    |   expression boolOp expression            #boolOpExpression
    ;

multOp: '*' | '/' | '%';
addOp: '+' | '-';
compareOp: '==' | '!=' | '>' | '<' | '>=' | '<=';
boolOp: BOOL_OPERATOR;

BOOL_OPERATOR: 'and' | 'or' | 'xor';

constant: INTEGER | FLOAT | STRING | BOOL | NULL;

INTEGER: [0-9]+;
FLOAT: [0-9]+ '.' [0-9]+;
STRING: ('"' ~'"'* '"') | ('"' ~'\''* '\'');
BOOL: 'true' | 'false';
NULL: 'null' | 'NULL';

block: '{' line* '}';

WS: [ \t\r\n]+ -> skip;
IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;

