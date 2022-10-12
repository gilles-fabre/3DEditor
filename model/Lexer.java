/* The following code was generated by JFlex 1.4.1 on 8/3/07 11:30 AM */

package model;

import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 8/3/07 11:30 AM from the specification file
 * <tt>I:/Sources & Tools Backup/Applications/Java/3DEngine/3DEditor/model/ModelFile.jflex</tt>
 */
class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\7\1\3\1\2\1\0\1\3\1\1\16\7\4\0\1\3\3\0"+
    "\1\6\5\0\1\5\1\0\1\57\1\10\1\13\1\4\1\11\11\12"+
    "\7\0\1\55\1\37\1\31\1\22\1\14\2\6\1\60\4\6\1\15"+
    "\2\6\1\41\1\6\1\51\1\33\1\26\2\6\1\61\3\6\1\47"+
    "\1\0\1\50\1\0\1\6\1\0\1\42\1\6\1\53\1\17\1\20"+
    "\1\52\1\54\1\35\1\23\1\6\1\25\1\21\1\43\1\32\1\16"+
    "\1\34\1\6\1\27\1\24\1\44\1\30\1\56\1\6\1\40\1\36"+
    "\1\6\1\45\1\0\1\46\1\0\41\7\2\0\4\6\4\0\1\6"+
    "\2\0\1\7\7\0\1\6\4\0\1\6\5\0\27\6\1\0\37\6"+
    "\1\0\u013f\6\31\0\162\6\4\0\14\6\16\0\5\6\11\0\1\6"+
    "\21\0\130\7\5\0\23\7\12\0\1\6\13\0\1\6\1\0\3\6"+
    "\1\0\1\6\1\0\24\6\1\0\54\6\1\0\46\6\1\0\5\6"+
    "\4\0\202\6\1\0\4\7\3\0\105\6\1\0\46\6\2\0\2\6"+
    "\6\0\20\6\41\0\46\6\2\0\1\6\7\0\47\6\11\0\21\7"+
    "\1\0\27\7\1\0\3\7\1\0\1\7\1\0\2\7\1\0\1\7"+
    "\13\0\33\6\5\0\3\6\15\0\4\7\14\0\6\7\13\0\32\6"+
    "\5\0\13\6\16\7\7\0\12\7\4\0\2\6\1\7\143\6\1\0"+
    "\1\6\10\7\1\0\6\7\2\6\2\7\1\0\4\7\2\6\12\7"+
    "\3\6\2\0\1\6\17\0\1\7\1\6\1\7\36\6\33\7\2\0"+
    "\3\6\60\0\46\6\13\7\1\6\u014f\0\3\7\66\6\2\0\1\7"+
    "\1\6\20\7\2\0\1\6\4\7\3\0\12\6\2\7\2\0\12\7"+
    "\21\0\3\7\1\0\10\6\2\0\2\6\2\0\26\6\1\0\7\6"+
    "\1\0\1\6\3\0\4\6\2\0\1\7\1\6\7\7\2\0\2\7"+
    "\2\0\3\7\11\0\1\7\4\0\2\6\1\0\3\6\2\7\2\0"+
    "\12\7\4\6\15\0\3\7\1\0\6\6\4\0\2\6\2\0\26\6"+
    "\1\0\7\6\1\0\2\6\1\0\2\6\1\0\2\6\2\0\1\7"+
    "\1\0\5\7\4\0\2\7\2\0\3\7\13\0\4\6\1\0\1\6"+
    "\7\0\14\7\3\6\14\0\3\7\1\0\11\6\1\0\3\6\1\0"+
    "\26\6\1\0\7\6\1\0\2\6\1\0\5\6\2\0\1\7\1\6"+
    "\10\7\1\0\3\7\1\0\3\7\2\0\1\6\17\0\2\6\2\7"+
    "\2\0\12\7\1\0\1\6\17\0\3\7\1\0\10\6\2\0\2\6"+
    "\2\0\26\6\1\0\7\6\1\0\2\6\1\0\5\6\2\0\1\7"+
    "\1\6\6\7\3\0\2\7\2\0\3\7\10\0\2\7\4\0\2\6"+
    "\1\0\3\6\4\0\12\7\1\0\1\6\20\0\1\7\1\6\1\0"+
    "\6\6\3\0\3\6\1\0\4\6\3\0\2\6\1\0\1\6\1\0"+
    "\2\6\3\0\2\6\3\0\3\6\3\0\10\6\1\0\3\6\4\0"+
    "\5\7\3\0\3\7\1\0\4\7\11\0\1\7\17\0\11\7\11\0"+
    "\1\6\7\0\3\7\1\0\10\6\1\0\3\6\1\0\27\6\1\0"+
    "\12\6\1\0\5\6\4\0\7\7\1\0\3\7\1\0\4\7\7\0"+
    "\2\7\11\0\2\6\4\0\12\7\22\0\2\7\1\0\10\6\1\0"+
    "\3\6\1\0\27\6\1\0\12\6\1\0\5\6\2\0\1\7\1\6"+
    "\7\7\1\0\3\7\1\0\4\7\7\0\2\7\7\0\1\6\1\0"+
    "\2\6\4\0\12\7\22\0\2\7\1\0\10\6\1\0\3\6\1\0"+
    "\27\6\1\0\20\6\4\0\6\7\2\0\3\7\1\0\4\7\11\0"+
    "\1\7\10\0\2\6\4\0\12\7\22\0\2\7\1\0\22\6\3\0"+
    "\30\6\1\0\11\6\1\0\1\6\2\0\7\6\3\0\1\7\4\0"+
    "\6\7\1\0\1\7\1\0\10\7\22\0\2\7\15\0\60\6\1\7"+
    "\2\6\7\7\4\0\10\6\10\7\1\0\12\7\47\0\2\6\1\0"+
    "\1\6\2\0\2\6\1\0\1\6\2\0\1\6\6\0\4\6\1\0"+
    "\7\6\1\0\3\6\1\0\1\6\1\0\1\6\2\0\2\6\1\0"+
    "\4\6\1\7\2\6\6\7\1\0\2\7\1\6\2\0\5\6\1\0"+
    "\1\6\1\0\6\7\2\0\12\7\2\0\2\6\42\0\1\6\27\0"+
    "\2\7\6\0\12\7\13\0\1\7\1\0\1\7\1\0\1\7\4\0"+
    "\2\7\10\6\1\0\42\6\6\0\24\7\1\0\2\7\4\6\4\0"+
    "\10\7\1\0\44\7\11\0\1\7\71\0\42\6\1\0\5\6\1\0"+
    "\2\6\1\0\7\7\3\0\4\7\6\0\12\7\6\0\6\6\4\7"+
    "\106\0\46\6\12\0\51\6\7\0\132\6\5\0\104\6\5\0\122\6"+
    "\6\0\7\6\1\0\77\6\1\0\1\6\1\0\4\6\2\0\7\6"+
    "\1\0\1\6\1\0\4\6\2\0\47\6\1\0\1\6\1\0\4\6"+
    "\2\0\37\6\1\0\1\6\1\0\4\6\2\0\7\6\1\0\1\6"+
    "\1\0\4\6\2\0\7\6\1\0\7\6\1\0\27\6\1\0\37\6"+
    "\1\0\1\6\1\0\4\6\2\0\7\6\1\0\47\6\1\0\23\6"+
    "\16\0\11\7\56\0\125\6\14\0\u026c\6\2\0\10\6\12\0\32\6"+
    "\5\0\113\6\3\0\3\6\17\0\15\6\1\0\4\6\3\7\13\0"+
    "\22\6\3\7\13\0\22\6\2\7\14\0\15\6\1\0\3\6\1\0"+
    "\2\7\14\0\64\6\40\7\3\0\1\6\3\0\2\6\1\7\2\0"+
    "\12\7\41\0\3\7\2\0\12\7\6\0\130\6\10\0\51\6\1\7"+
    "\126\0\35\6\3\0\14\7\4\0\14\7\12\0\12\7\36\6\2\0"+
    "\5\6\u038b\0\154\6\224\0\234\6\4\0\132\6\6\0\26\6\2\0"+
    "\6\6\2\0\46\6\2\0\6\6\2\0\10\6\1\0\1\6\1\0"+
    "\1\6\1\0\1\6\1\0\37\6\2\0\65\6\1\0\7\6\1\0"+
    "\1\6\3\0\3\6\1\0\7\6\3\0\4\6\2\0\6\6\4\0"+
    "\15\6\5\0\3\6\1\0\7\6\17\0\4\7\32\0\5\7\20\0"+
    "\2\6\23\0\1\6\13\0\4\7\6\0\6\7\1\0\1\6\15\0"+
    "\1\6\40\0\22\6\36\0\15\7\4\0\1\7\3\0\6\7\27\0"+
    "\1\6\4\0\1\6\2\0\12\6\1\0\1\6\3\0\5\6\6\0"+
    "\1\6\1\0\1\6\1\0\1\6\1\0\4\6\1\0\3\6\1\0"+
    "\7\6\3\0\3\6\5\0\5\6\26\0\44\6\u0e81\0\3\6\31\0"+
    "\11\6\6\7\1\0\5\6\2\0\5\6\4\0\126\6\2\0\2\7"+
    "\2\0\3\6\1\0\137\6\5\0\50\6\4\0\136\6\21\0\30\6"+
    "\70\0\20\6\u0200\0\u19b6\6\112\0\u51a6\6\132\0\u048d\6\u0773\0\u2ba4\6"+
    "\u215c\0\u012e\6\2\0\73\6\225\0\7\6\14\0\5\6\5\0\1\6"+
    "\1\7\12\6\1\0\15\6\1\0\5\6\1\0\1\6\1\0\2\6"+
    "\1\0\2\6\1\0\154\6\41\0\u016b\6\22\0\100\6\2\0\66\6"+
    "\50\0\15\6\3\0\20\7\20\0\4\7\17\0\2\6\30\0\3\6"+
    "\31\0\1\6\6\0\5\6\1\0\207\6\2\0\1\7\4\0\1\6"+
    "\13\0\12\7\7\0\32\6\4\0\1\6\1\0\32\6\12\0\132\6"+
    "\3\0\6\6\2\0\6\6\2\0\6\6\2\0\3\6\3\0\2\6"+
    "\3\0\2\6\22\0\3\7\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\1\1\1\3\1\1\2\4\7\3"+
    "\1\5\1\6\1\7\1\10\2\3\1\11\2\3\4\0"+
    "\26\3\1\0\1\12\16\3\1\13\13\3\2\0\3\3"+
    "\1\14\4\3\1\15\15\3\1\16\2\3\1\0\1\12"+
    "\1\17\1\3\1\20\1\21\2\3\1\22\7\3\1\23"+
    "\5\3\1\24\1\3\1\25\7\3\1\26\6\3\1\27"+
    "\1\3\1\30\10\3\1\31\1\3\1\32\10\3\1\33"+
    "\1\34\1\3\1\35\1\36\2\3\1\37\11\3\1\40"+
    "\2\3\1\41\2\3\1\42\1\43\1\3\1\44\1\3"+
    "\1\45\5\3\1\46\5\3\1\47";

  private static int [] zzUnpackAction() {
    int [] result = new int[208];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\62\0\144\0\62\0\226\0\310\0\372\0\u012c"+
    "\0\u015e\0\u0190\0\u01c2\0\u01f4\0\u0226\0\u0258\0\u028a\0\u02bc"+
    "\0\62\0\62\0\62\0\62\0\u02ee\0\u0320\0\62\0\u0352"+
    "\0\u0384\0\u03b6\0\u03e8\0\u012c\0\u041a\0\u044c\0\u047e\0\u04b0"+
    "\0\u04e2\0\u0514\0\u0546\0\u0578\0\u05aa\0\u05dc\0\u060e\0\u0640"+
    "\0\u0672\0\u06a4\0\u06d6\0\u0708\0\u073a\0\u076c\0\u079e\0\u07d0"+
    "\0\u0802\0\u0834\0\u0866\0\u0898\0\u08ca\0\u08fc\0\u092e\0\u0960"+
    "\0\u0992\0\u09c4\0\u09f6\0\u0a28\0\u0a5a\0\u0a8c\0\u0abe\0\u0af0"+
    "\0\u0b22\0\u0b54\0\u0b86\0\310\0\u0bb8\0\u0bea\0\u0c1c\0\u0c4e"+
    "\0\u0c80\0\u0cb2\0\u0ce4\0\u0d16\0\u0d48\0\u0d7a\0\u0dac\0\u0dde"+
    "\0\u0e10\0\u0e42\0\u0e74\0\u0ea6\0\310\0\u0ed8\0\u0f0a\0\u0f3c"+
    "\0\u0f6e\0\310\0\u0fa0\0\u0fd2\0\u1004\0\u1036\0\u1068\0\u109a"+
    "\0\u10cc\0\u10fe\0\u1130\0\u1162\0\u1194\0\u11c6\0\u11f8\0\310"+
    "\0\u122a\0\u125c\0\u128e\0\u12c0\0\310\0\u12f2\0\310\0\310"+
    "\0\u1324\0\u1356\0\310\0\u1388\0\u13ba\0\u13ec\0\u141e\0\u1450"+
    "\0\u1482\0\u14b4\0\310\0\u14e6\0\u1518\0\u154a\0\u157c\0\u15ae"+
    "\0\310\0\u15e0\0\310\0\u1612\0\u1644\0\u1676\0\u16a8\0\u16da"+
    "\0\u170c\0\u173e\0\310\0\u1770\0\u17a2\0\u17d4\0\u1806\0\u1838"+
    "\0\u186a\0\310\0\u189c\0\310\0\u18ce\0\u1900\0\u1932\0\u1964"+
    "\0\u1996\0\u19c8\0\u19fa\0\u1a2c\0\310\0\u1a5e\0\310\0\u1a90"+
    "\0\u1ac2\0\u1af4\0\u1b26\0\u1b58\0\u1b8a\0\u1bbc\0\u1bee\0\310"+
    "\0\310\0\u1c20\0\310\0\310\0\u1c52\0\u1c84\0\310\0\u1cb6"+
    "\0\u1ce8\0\u1d1a\0\u1d4c\0\u1d7e\0\u1db0\0\u1de2\0\u1e14\0\u1e46"+
    "\0\310\0\u1e78\0\u1eaa\0\310\0\u1edc\0\u1f0e\0\310\0\310"+
    "\0\u1f40\0\310\0\u1f72\0\310\0\u1fa4\0\u1fd6\0\u2008\0\u203a"+
    "\0\u206c\0\310\0\u209e\0\u20d0\0\u2102\0\u2134\0\u2166\0\310";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[208];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\2\4\1\5\1\2\1\6\1\2\1\7"+
    "\1\10\1\11\1\2\1\6\1\12\4\6\1\13\3\6"+
    "\1\14\2\6\1\15\1\6\1\16\3\6\1\17\1\6"+
    "\1\20\3\6\1\21\1\22\1\23\1\24\1\25\3\6"+
    "\1\26\1\6\1\27\1\30\1\31\64\0\1\4\63\0"+
    "\1\32\1\33\62\0\2\6\1\0\2\6\1\0\31\6"+
    "\4\0\6\6\1\0\2\6\11\0\1\10\1\11\60\0"+
    "\2\34\1\35\57\0\2\11\1\35\54\0\2\6\1\0"+
    "\2\6\1\0\2\6\1\36\26\6\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\4\6\1\37"+
    "\2\6\1\40\21\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\2\6\1\41\1\6\1\42"+
    "\6\6\1\43\15\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\2\6\1\44\16\6\1\45"+
    "\1\46\6\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\20\6\1\47\10\6\4\0\2\6"+
    "\1\50\3\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\2\6\1\51\26\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\2\6\1\52\17\6"+
    "\1\53\6\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\2\6\1\54\1\6\1\55\21\6"+
    "\1\56\2\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\3\6\1\57\12\6\1\60\12\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\2\6\1\61\1\6\1\62\24\6\4\0\6\6"+
    "\1\0\2\6\6\0\2\6\1\0\2\6\1\0\7\6"+
    "\1\63\21\6\4\0\6\6\1\0\2\6\1\32\1\3"+
    "\1\4\57\32\62\64\11\0\2\65\55\0\2\6\1\0"+
    "\2\6\1\0\3\6\1\66\25\6\4\0\5\6\1\67"+
    "\1\0\2\6\6\0\2\6\1\0\2\6\1\0\20\6"+
    "\1\70\10\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\10\6\1\71\20\6\4\0\6\6"+
    "\1\0\2\6\6\0\2\6\1\0\2\6\1\0\13\6"+
    "\1\72\15\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\30\6\1\73\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\26\6\1\74"+
    "\2\6\4\0\6\6\1\0\2\6\6\0\2\6\1\0"+
    "\2\6\1\0\5\6\1\75\10\6\1\76\10\6\1\77"+
    "\1\6\4\0\6\6\1\0\2\6\6\0\2\6\1\0"+
    "\2\6\1\0\7\6\1\100\21\6\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\5\6\1\101"+
    "\23\6\4\0\6\6\1\0\2\6\6\0\2\6\1\0"+
    "\2\6\1\0\21\6\1\102\7\6\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\26\6\1\103"+
    "\2\6\4\0\6\6\1\0\2\6\6\0\2\6\1\0"+
    "\2\6\1\0\24\6\1\104\4\6\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\5\6\1\105"+
    "\1\6\1\106\21\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\13\6\1\107\15\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\30\6\1\110\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\27\6\1\111\1\6\4\0\6\6"+
    "\1\0\2\6\6\0\2\6\1\0\2\6\1\0\3\6"+
    "\1\112\25\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\3\6\1\113\25\6\4\0\6\6"+
    "\1\0\2\6\6\0\2\6\1\0\2\6\1\0\31\6"+
    "\4\0\3\6\1\114\2\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\27\6\1\115\1\6\4\0\6\6"+
    "\1\0\2\6\6\0\2\6\1\0\2\6\1\0\7\6"+
    "\1\116\21\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\3\6\1\117\25\6\4\0\6\6"+
    "\1\0\2\6\5\64\1\120\54\64\11\0\2\65\1\0"+
    "\1\121\53\0\2\6\1\0\2\6\1\0\4\6\1\122"+
    "\24\6\4\0\6\6\1\0\2\6\6\0\2\6\1\0"+
    "\2\6\1\0\4\6\1\123\24\6\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\30\6\1\124"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\11\6\1\125\17\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\14\6\1\126\14\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\13\6\1\127\15\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\16\6\1\130\12\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\2\6\1\131\26\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\4\6\1\132\24\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\20\6\1\133\10\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\5\6\1\134\23\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\7\6\1\135\21\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\4\6\1\136\24\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\5\6\1\137\23\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\22\6\1\140\6\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\16\6\1\141\12\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\26\6\1\142\2\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\26\6\1\143\2\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\2\6\1\144\26\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\7\6\1\145\21\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\15\6\1\146\13\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\5\6\1\147\23\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\4\6\1\150\24\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\31\6\4\0\3\6\1\151\2\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\30\6\1\152\4\0"+
    "\6\6\1\0\2\6\4\64\1\4\1\120\54\64\10\0"+
    "\1\153\1\0\1\154\55\0\2\6\1\0\2\6\1\0"+
    "\5\6\1\155\23\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\6\6\1\156\22\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\21\6\1\157\7\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\10\6\1\160\20\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\26\6\1\161\2\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\10\6\1\162\20\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\13\6\1\163\15\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\2\6\1\164\26\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\3\6\1\165\25\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\16\6\1\166\12\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\13\6\1\167\15\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\4\6\1\170\2\6\1\171"+
    "\21\6\4\0\6\6\1\0\2\6\6\0\2\6\1\0"+
    "\2\6\1\0\31\6\4\0\3\6\1\172\2\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\30\6\1\173"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\27\6\1\174\1\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\30\6\1\175\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\31\6\4\0\5\6\1\176\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\14\6\1\177\14\6\4\0\6\6"+
    "\1\0\2\6\6\0\2\6\1\0\2\6\1\0\21\6"+
    "\1\200\7\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\4\6\1\201\24\6\4\0\6\6"+
    "\1\0\2\6\6\0\2\6\1\0\2\6\1\0\21\6"+
    "\1\202\7\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\21\6\1\203\7\6\4\0\6\6"+
    "\1\0\2\6\12\0\1\154\60\0\2\154\55\0\2\6"+
    "\1\0\2\6\1\0\4\6\1\204\24\6\4\0\6\6"+
    "\1\0\2\6\6\0\2\6\1\0\2\6\1\0\21\6"+
    "\1\205\7\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\5\6\1\206\23\6\4\0\1\6"+
    "\1\207\4\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\10\6\1\210\20\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\13\6\1\211\15\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\3\6\1\212\25\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\4\6\1\213\24\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\6\6\1\214\22\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\16\6\1\215\12\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\2\6\1\216\26\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\7\6\1\217\21\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\7\6\1\220\21\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\4\6\1\221\24\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\10\6\1\222\20\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\7\6\1\223\21\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\30\6\1\224\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\5\6\1\225\23\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\4\6\1\226\24\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\26\6\1\227\2\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\2\6\1\230\26\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\7\6\1\231\21\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\4\6\1\232\24\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\4\6\1\233\24\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\4\6\1\234\24\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\31\6\4\0\3\6\1\235"+
    "\2\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\16\6\1\236\12\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\3\6\1\237\25\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\2\6\1\240\26\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\15\6\1\241\13\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\5\6\1\242\23\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\30\6\1\243\4\0\6\6"+
    "\1\0\2\6\6\0\2\6\1\0\2\6\1\0\3\6"+
    "\1\244\25\6\4\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\0\2\6\1\0\30\6\1\245\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\13\6\1\246"+
    "\15\6\4\0\6\6\1\0\2\6\6\0\2\6\1\0"+
    "\2\6\1\0\30\6\1\247\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\16\6\1\250\12\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\13\6\1\251\15\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\5\6\1\252\23\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\10\6\1\253\20\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\16\6\1\254\12\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\21\6\1\255\7\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\3\6\1\256\25\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\26\6\1\257\2\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\13\6\1\260\15\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\7\6\1\261\21\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\27\6\1\262\1\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\7\6\1\263\21\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\30\6\1\264\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\7\6\1\265\21\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\13\6\1\266\15\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\2\6\1\267\26\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\2\6\1\270\26\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\10\6\1\271\15\6\1\272\2\6\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\2\6\1\273"+
    "\26\6\4\0\6\6\1\0\2\6\6\0\2\6\1\0"+
    "\2\6\1\0\26\6\1\274\2\6\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\5\6\1\275"+
    "\23\6\4\0\6\6\1\0\2\6\6\0\2\6\1\0"+
    "\2\6\1\0\4\6\1\276\24\6\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\16\6\1\277"+
    "\12\6\4\0\6\6\1\0\2\6\6\0\2\6\1\0"+
    "\2\6\1\0\16\6\1\300\12\6\4\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\0\2\6\1\0\30\6\1\301"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\16\6\1\302\12\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\3\6\1\303\25\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\16\6\1\304\12\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\7\6\1\305\21\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\13\6\1\306\15\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\2\6\1\307\26\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\4\6\1\310\24\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\16\6\1\311\12\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\16\6\1\312\12\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\1\6\1\313\27\6"+
    "\4\0\6\6\1\0\2\6\6\0\2\6\1\0\2\6"+
    "\1\0\26\6\1\314\2\6\4\0\6\6\1\0\2\6"+
    "\6\0\2\6\1\0\2\6\1\0\30\6\1\315\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\13\6\1\316\15\6\4\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\0\2\6\1\0\7\6\1\317\21\6\4\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\0\2\6\1\0"+
    "\24\6\1\320\4\6\4\0\6\6\1\0\2\6";

  private static int [] zzUnpackTrans() {
    int [] result = new int[8600];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\1\11\14\1\4\11\2\1\1\11"+
    "\2\1\4\0\26\1\1\0\33\1\2\0\31\1\1\0"+
    "\145\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[208];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 1770) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 6: 
          { return symbol(sym.BLOCK_END);
          }
        case 40: break;
        case 2: 
          { /* ignore */
          }
        case 41: break;
        case 16: 
          { return symbol(sym.DEPTH);
          }
        case 42: break;
        case 32: 
          { return symbol(sym.TRANSFORMS);
          }
        case 43: break;
        case 39: 
          { return symbol(sym.TRANSFORMATION_MATRIX);
          }
        case 44: break;
        case 38: 
          { return symbol(sym.REMOVE_CHILDREN);
          }
        case 45: break;
        case 23: 
          { return symbol(sym.RADIUS);
          }
        case 46: break;
        case 18: 
          { return symbol(sym.COLOR);
          }
        case 47: break;
        case 11: 
          { return symbol(sym.BOX);
          }
        case 48: break;
        case 12: 
          { return symbol(sym.DISK);
          }
        case 49: break;
        case 25: 
          { return symbol(sym.SCALING);
          }
        case 50: break;
        case 20: 
          { return symbol(sym.ANGLE);
          }
        case 51: break;
        case 26: 
          { return symbol(sym.PYRAMID);
          }
        case 52: break;
        case 10: 
          { return symbol(sym.DOUBLE, new Double(yytext()));
          }
        case 53: break;
        case 7: 
          { return symbol(sym.ARRAY_BEGIN);
          }
        case 54: break;
        case 19: 
          { return symbol(sym.POINT);
          }
        case 55: break;
        case 30: 
          { return symbol(sym.ROTATION);
          }
        case 56: break;
        case 37: 
          { return symbol(sym.ADD_CHILDREN);
          }
        case 57: break;
        case 21: 
          { return symbol(sym.WIDTH);
          }
        case 58: break;
        case 1: 
          { throw new Error("Illegal character <"+
                                                    yytext()+">");
          }
        case 59: break;
        case 36: 
          { return symbol(sym.COMPOSITION);
          }
        case 60: break;
        case 35: 
          { return symbol(sym.TRANSLATION);
          }
        case 61: break;
        case 28: 
          { return symbol(sym.CYLINDER);
          }
        case 62: break;
        case 27: 
          { return symbol(sym.CHILDREN);
          }
        case 63: break;
        case 13: 
          { return symbol(sym.CONE);
          }
        case 64: break;
        case 9: 
          { return symbol(sym.COMA);
          }
        case 65: break;
        case 22: 
          { return symbol(sym.SPHERE);
          }
        case 66: break;
        case 14: 
          { return symbol(sym.HOME);
          }
        case 67: break;
        case 4: 
          { return symbol(sym.INTEGER, new Integer(yytext()));
          }
        case 68: break;
        case 33: 
          { return symbol(sym.SCALE_DELTA);
          }
        case 69: break;
        case 17: 
          { return symbol(sym.TORUS);
          }
        case 70: break;
        case 31: 
          { return symbol(sym.MOVE_DELTA);
          }
        case 71: break;
        case 15: 
          { return symbol(sym.MODEL);
          }
        case 72: break;
        case 3: 
          { return symbol(sym.NAME, yytext());
          }
        case 73: break;
        case 5: 
          { return symbol(sym.BLOCK_BEGIN);
          }
        case 74: break;
        case 34: 
          { return symbol(sym.TETRAHEDRON);
          }
        case 75: break;
        case 24: 
          { return symbol(sym.HEIGHT);
          }
        case 76: break;
        case 29: 
          { return symbol(sym.POLYGONS);
          }
        case 77: break;
        case 8: 
          { return symbol(sym.ARRAY_END);
          }
        case 78: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}