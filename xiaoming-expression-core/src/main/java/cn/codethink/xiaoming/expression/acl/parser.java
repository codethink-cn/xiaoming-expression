
/*
 * Copyright 2023. CodeThink Technologies and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package cn.codethink.xiaoming.expression.acl;

import cn.codethink.xiaoming.expression.*;
import cn.codethink.xiaoming.expression.lang.*;
import java_cup.runtime.*;
import java.util.*;
import java.util.stream.Collectors;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"all"})
public class parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\024\000\002\002\004\000\002\002\003\000\002\002" +
    "\003\000\002\002\003\000\002\002\003\000\002\002\003" +
    "\000\002\002\003\000\002\007\003\000\002\003\003\000" +
    "\002\003\003\000\002\004\003\000\002\004\005\000\002" +
    "\004\003\000\002\006\006\000\002\011\002\000\002\011" +
    "\003\000\002\010\003\000\002\010\005\000\002\005\005" +
    "\000\002\005\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\037\000\026\004\021\006\005\010\017\013\016\014" +
    "\023\015\013\016\007\017\011\020\012\021\006\001\002" +
    "\000\014\002\ufff5\005\ufff5\007\ufff5\011\ufff5\012\ufff5\001" +
    "\002\000\030\004\021\006\005\007\ufff3\010\017\013\016" +
    "\014\023\015\013\016\007\017\011\020\012\021\006\001" +
    "\002\000\014\002\ufffb\005\ufffb\007\ufffb\011\ufffb\012\ufffb" +
    "\001\002\000\014\002\ufffe\005\ufffe\007\ufffe\011\ufffe\012" +
    "\ufffe\001\002\000\014\002\ufff8\005\ufff8\007\ufff8\011\ufff8" +
    "\012\ufff8\001\002\000\014\002\ufffd\005\ufffd\007\ufffd\011" +
    "\ufffd\012\ufffd\001\002\000\014\002\ufffc\005\ufffc\007\ufffc" +
    "\011\ufffc\012\ufffc\001\002\000\014\002\uffff\005\uffff\007" +
    "\uffff\011\uffff\012\uffff\001\002\000\004\002\037\001\002" +
    "\000\004\004\034\001\002\000\004\004\ufffa\001\002\000" +
    "\030\004\021\006\005\010\017\011\ufff3\013\016\014\023" +
    "\015\013\016\007\017\011\020\012\021\006\001\002\000" +
    "\014\002\ufff7\005\ufff7\007\ufff7\011\ufff7\012\ufff7\001\002" +
    "\000\026\004\021\006\005\010\017\013\016\014\023\015" +
    "\013\016\007\017\011\020\012\021\006\001\002\000\014" +
    "\002\ufff9\005\ufff9\007\ufff9\011\ufff9\012\ufff9\001\002\000" +
    "\014\002\000\005\000\007\000\011\000\012\000\001\002" +
    "\000\004\005\025\001\002\000\014\002\ufff6\005\ufff6\007" +
    "\ufff6\011\ufff6\012\ufff6\001\002\000\004\011\033\001\002" +
    "\000\012\005\ufff1\007\ufff1\011\ufff1\012\ufff1\001\002\000" +
    "\012\005\ufff2\007\ufff2\011\ufff2\012\031\001\002\000\026" +
    "\004\021\006\005\010\017\013\016\014\023\015\013\016" +
    "\007\017\011\020\012\021\006\001\002\000\012\005\ufff0" +
    "\007\ufff0\011\ufff0\012\ufff0\001\002\000\014\002\uffee\005" +
    "\uffee\007\uffee\011\uffee\012\uffee\001\002\000\030\004\021" +
    "\005\ufff3\006\005\010\017\013\016\014\023\015\013\016" +
    "\007\017\011\020\012\021\006\001\002\000\004\005\036" +
    "\001\002\000\014\002\ufff4\005\ufff4\007\ufff4\011\ufff4\012" +
    "\ufff4\001\002\000\004\002\001\001\002\000\004\007\041" +
    "\001\002\000\014\002\uffef\005\uffef\007\uffef\011\uffef\012" +
    "\uffef\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\037\000\016\002\017\003\013\004\021\005\007\006" +
    "\003\007\014\001\001\000\002\001\001\000\022\002\017" +
    "\003\026\004\021\005\007\006\003\007\014\010\027\011" +
    "\037\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\022\002\017\003\026\004\021\005\007\006\003\007" +
    "\014\010\027\011\025\001\001\000\002\001\001\000\016" +
    "\002\017\003\023\004\021\005\007\006\003\007\014\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\016\002\017\003\031\004\021\005\007" +
    "\006\003\007\014\001\001\000\002\001\001\000\002\001" +
    "\001\000\022\002\017\003\026\004\021\005\007\006\003" +
    "\007\014\010\027\011\034\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



  Interpreter interpreter;

  public void initialize(Interpreter interpreter) {
    this.interpreter = interpreter;
  }

  @Override
  public void report_error(String message, Object info) {
    report_fatal_error(message, info);
  }

  @Override
  public void syntax_error(Symbol cur_token) {
    this.report_error("Syntax error", cur_token);
  }

  @Override
  public void report_fatal_error(String message, Object info) {
    if (info instanceof JavaSymbol) {
      final JavaSymbol symbol = (JavaSymbol) info;
      throw new RuntimeException(message + " (at line " + symbol.getLine() + ", column " + symbol.getColumn() + ")");
    } else {
      throw new RuntimeException(message);
    }
  }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$parser$do_action_part00000000(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= expression EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		cn.codethink.xiaoming.expression.Expression start_val = (cn.codethink.xiaoming.expression.Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // literal ::= INTEGER_LITERAL 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		java.lang.Number l = (java.lang.Number)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = LiteralExpression.of(l.intValue()); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // literal ::= FLOATING_POINT_LITERAL 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		java.lang.Number l = (java.lang.Number)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = LiteralExpression.of(l.doubleValue()); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // literal ::= BOOLEAN_LITERAL 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		java.lang.Boolean l = (java.lang.Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = LiteralExpression.of(l.booleanValue()); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // literal ::= CHARACTER_LITERAL 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		java.lang.Character l = (java.lang.Character)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = LiteralExpression.of(l.charValue()); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // literal ::= STRING_LITERAL 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		java.lang.String l = (java.lang.String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = LiteralExpression.of(l); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // literal ::= NULL_LITERAL 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		 RESULT = LiteralExpression.ofNull(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // type ::= IDENTIFIER 
            {
              java.lang.String RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		java.lang.String i = (java.lang.String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = i; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("type",5, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // expression ::= primary_no_new_collection 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		cn.codethink.xiaoming.expression.Expression i = (cn.codethink.xiaoming.expression.Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = i; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // expression ::= collection_creation_expression 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		cn.codethink.xiaoming.expression.Expression i = (cn.codethink.xiaoming.expression.Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = i; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // primary_no_new_collection ::= literal 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		cn.codethink.xiaoming.expression.Expression i = (cn.codethink.xiaoming.expression.Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = i; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("primary_no_new_collection",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // primary_no_new_collection ::= LPAREN expression RPAREN 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		cn.codethink.xiaoming.expression.Expression e = (cn.codethink.xiaoming.expression.Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = e; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("primary_no_new_collection",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // primary_no_new_collection ::= method_invocation 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		cn.codethink.xiaoming.expression.Expression i = (cn.codethink.xiaoming.expression.Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = i; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("primary_no_new_collection",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // method_invocation ::= type LPAREN argument_list_opt RPAREN 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		java.lang.String t = (java.lang.String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		java.util.List l = (java.util.List)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = new InvokeExpressionImpl(interpreter.getFunctionOrFail(t, ((List<Expression>) l).stream().map(Expression::getResultClass).collect(Collectors.toList())), l); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("method_invocation",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // argument_list_opt ::= 
            {
              java.util.List RESULT =null;
		 RESULT = Collections.emptyList(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("argument_list_opt",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // argument_list_opt ::= argument_list 
            {
              java.util.List RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		java.util.List i = (java.util.List)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = i; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("argument_list_opt",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // argument_list ::= expression 
            {
              java.util.List RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		cn.codethink.xiaoming.expression.Expression e = (cn.codethink.xiaoming.expression.Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new ArrayList<>(); RESULT.add(e); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("argument_list",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // argument_list ::= argument_list COMMA expression 
            {
              java.util.List RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		java.util.List l = (java.util.List)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		cn.codethink.xiaoming.expression.Expression e = (cn.codethink.xiaoming.expression.Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = l; l.add(e); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("argument_list",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // collection_creation_expression ::= LBRACE argument_list_opt RBRACE 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		java.util.List l = (java.util.List)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = new SetExpressionImpl(l); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("collection_creation_expression",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // collection_creation_expression ::= LBRACK argument_list_opt RBRACK 
            {
              cn.codethink.xiaoming.expression.Expression RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		java.util.List l = (java.util.List)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = new ListExpressionImpl(l); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("collection_creation_expression",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
              return CUP$parser$do_action_part00000000(
                               CUP$parser$act_num,
                               CUP$parser$parser,
                               CUP$parser$stack,
                               CUP$parser$top);
    }
}

}
