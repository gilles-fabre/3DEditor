package model;

import java_cup.runtime.*;

%%

/* options */
%class Lexer /* generated lexer class name */
%unicode	 /* to read from a text file; support for multibyte */
%cup		 /* cup interaction support; does include eofval and eofclose directives */
%line		 /* yyLine contains the line number for the last found token */
%column		 /* yyColumn contains the column number for the last found token */

/* user code, copied verbatim into the lexer class body */
%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

/* macros: make lexical units easier to manipulate */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = "-"?0 | "-"?[1-9][0-9]*
DoubleIntegerLiteral = "-"?[0-9][0-9]*"."[0-9][0-9]*("E""-"?[1-9][0-9]*)?

%%

/* keywords */
<YYINITIAL> "Model"           	 { return symbol(sym.MODEL); }
<YYINITIAL> "Disk"           	 { return symbol(sym.DISK); }
<YYINITIAL> "Torus"           	 { return symbol(sym.TORUS); }
<YYINITIAL> "Cone"           	 { return symbol(sym.CONE); }
<YYINITIAL> "Sphere"           	 { return symbol(sym.SPHERE); }
<YYINITIAL> "Cylinder"         	 { return symbol(sym.CYLINDER); }
<YYINITIAL> "Box"           	 { return symbol(sym.BOX); }
<YYINITIAL> "Pyramid"            { return symbol(sym.PYRAMID); }
<YYINITIAL> "Tetrahedron"      	 { return symbol(sym.TETRAHEDRON); }
<YYINITIAL> "{"           		 { return symbol(sym.BLOCK_BEGIN); }
<YYINITIAL> "}"           		 { return symbol(sym.BLOCK_END); }
<YYINITIAL> "["           		 { return symbol(sym.ARRAY_BEGIN); }
<YYINITIAL> "]"           		 { return symbol(sym.ARRAY_END); }

<YYINITIAL> "Rotation"        	 { return symbol(sym.ROTATION); }
<YYINITIAL> "TransformationMatrix"	{ return symbol(sym.TRANSFORMATION_MATRIX); }
<YYINITIAL> "Translation"      	 { return symbol(sym.TRANSLATION); }
<YYINITIAL> "Composition"      	 { return symbol(sym.COMPOSITION); }
<YYINITIAL> "Scaling"      		 { return symbol(sym.SCALING); }
<YYINITIAL> "AddChildren" 		 { return symbol(sym.ADD_CHILDREN); }
<YYINITIAL> "RemoveChildren" 	 { return symbol(sym.REMOVE_CHILDREN); }

<YYINITIAL> "," 	 			 { return symbol(sym.COMA); }
<YYINITIAL> "Polygons"  		 { return symbol(sym.POLYGONS); }
<YYINITIAL> "Point"  			 { return symbol(sym.POINT); }
<YYINITIAL> "Home"  			 { return symbol(sym.HOME); }
<YYINITIAL> "Color"  			 { return symbol(sym.COLOR); }
<YYINITIAL> "Angle"  			 { return symbol(sym.ANGLE); }
<YYINITIAL> "Radius"  			 { return symbol(sym.RADIUS); }
<YYINITIAL> "Width"  			 { return symbol(sym.WIDTH); }
<YYINITIAL> "Height"  			 { return symbol(sym.HEIGHT); }
<YYINITIAL> "Depth"  			 { return symbol(sym.DEPTH); }
<YYINITIAL> "Children"  		 { return symbol(sym.CHILDREN); }
<YYINITIAL> "Transforms"  		 { return symbol(sym.TRANSFORMS); }

<YYINITIAL> "MoveDelta"			 { return symbol(sym.MOVE_DELTA); }
<YYINITIAL> "ScaleDelta"  		 { return symbol(sym.SCALE_DELTA); }

<YYINITIAL> {
  /* identifiers */ 
  {Identifier}                   { return symbol(sym.NAME, yytext()); }
 
  /* literals */
  {DecIntegerLiteral}            { return symbol(sym.INTEGER, new Integer(yytext())); }
  {DoubleIntegerLiteral}         { return symbol(sym.DOUBLE, new Double(yytext())); }

  /* comments */
  {Comment}                      { /* ignore */ }
 
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

/* error fallback */
.|\n                             { throw new Error("Illegal character <"+
                                                    yytext()+">"); }
                                                    