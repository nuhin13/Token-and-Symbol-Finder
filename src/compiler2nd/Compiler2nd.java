/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler2nd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;
import java.util.*;
/**
 *
 * @author nuhin13
 */
public class Compiler2nd {

    /**
     * @param args the command line arguments
     */
    
    static Vector<String> Lexeme = new Vector<String>(); 
    static int position;
    
    public static void main(String[] args) {
        // TODO code application logic here
        try
            {
 			Scanner sc =new Scanner(new File("input.txt")); // input
			File outputfile = new File("output.txt");  //output 
                        
                        BufferedWriter out= new BufferedWriter(new FileWriter(outputfile));
                          
			while(sc.hasNextLine())
			{
				position = 0;
				String line = sc.nextLine();
				line = line.trim();
				int i;
				
				if(line.startsWith("/*"))
				{
					if(line.endsWith("*/"))
						continue;
					
					while(sc.hasNextLine())
					{
						line = sc.nextLine();						
						if(line.endsWith("*/"))
							break;
					}
					continue;
				}
				
				else if(line.equals("") || line.startsWith("//"))
				{
					continue;
				}
				
				else
				{
					for(i=0;i<line.length();i++)
					{
						String check = ""+line.charAt(i);
						
						// remove line comments
						if(check.equals("/"))
						{
							check = check+line.charAt(i+1);
							if(check.equals("//"))
							{
								break;
							}
						}
						
						else if(check.equals(" "))
						{
							if(position == i)
							{
								position=i+1;
								continue;
							}
							
							else
							{
								addString(i,line);
							}	
						}
						
						else if( check.equals("/") ||check.equals("*") ||check.equals("%") || check.equals("(") ||check.equals(")") ||check.equals("{") || check.equals("}") || check.equals("[") || check.equals("]") || check.equals(",") || check.equals(";"))
						{
							addString(i,line);
							String s = ""+line.charAt(i);
							Lexeme.add(s);
						}
						
						else if(check.equals("=") || check.equals("+") || check.equals("-") || check.equals("&") || check.equals("|")|| check.equals("!") || check.equals("<") || check.equals(">") )
						{
							String s = ""+ line.charAt(i+1);														
							if(s.equals("=") || s.equals("+") || s.equals("-") || s.equals("&") || s.equals("|") )
							{
								addString(i,line);
								s=""+line.charAt(i)+line.charAt(i+1);
								Lexeme.add(s);
								position = i+2;
								i = i+1;
							}
							else
							{
								addString(i,line);
								s = ""+line.charAt(i);
								Lexeme.add(s);
							}	
						}			
					}
					addString(i,line);
				}	
			}
                        
			int count=0;
			int flag=0;

			String[] Lexeme = new String[Compiler2nd.Lexeme.size()];
			Lexeme = Compiler2nd.Lexeme.toArray(Lexeme);
			
			String[] TokenName = new String[Lexeme.length];
			String[] AttributeValues = new String[Lexeme.length];
			String[][] SymbolTable = new String[Lexeme.length][2];
			
			
			for(int i=0;i<Lexeme.length;i++)
			{
				flag=0;
				
				// Syntax checking
				if(	
                                        Lexeme[i].equals("abstract") || Lexeme[i].equals("continue") || 
					Lexeme[i].equals("new")|| Lexeme[i].equals("switch")|| 
					Lexeme[i].equals("private")|| Lexeme[i].equals("public") || 
					Lexeme[i].equals("import") ||  Lexeme[i].equals("do") || 
                                        Lexeme[i].equals("int") || Lexeme[i].equals("float") || 
					Lexeme[i].equals("long")|| Lexeme[i].equals("double")|| 
					Lexeme[i].equals("if")|| Lexeme[i].equals("else") || 
					Lexeme[i].equals("else if") ||  Lexeme[i].equals("for") ||  
					Lexeme[i].equals("while") ||  Lexeme[i].equals("break") || 
					Lexeme[i].equals("return")||Lexeme[i].equals("while")||
                                        Lexeme[i].equals("double")|| Lexeme[i].equals("catch") || 
					Lexeme[i].equals("throw") ||  Lexeme[i].equals("case") ||  
					Lexeme[i].equals("exeption") ||  Lexeme[i].equals("super") || 
					Lexeme[i].equals("implement") ||Lexeme[i].equals("protected")||
                                        Lexeme[i].equals("final")||Lexeme[i].equals("finally")||
                                        Lexeme[i].equals("void")||Lexeme[i].equals("const")||
                                        Lexeme[i].equals("native")||Lexeme[i].equals("try")||
                                        Lexeme[i].equals("Extends")
				)
				{
					TokenName[i] = new String(Lexeme[i]);
					AttributeValues[i] = new String();
				}
				
                                else if(Lexeme[i].equals("==") || Lexeme[i].equals("&&") || Lexeme[i].equals("||") || Lexeme[i].equals("<=") || Lexeme[i].equals(">=") || Lexeme[i].equals("!=") || Lexeme[i].equals("<") || Lexeme[i].equals(">") )
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Logical Operator");
				}
				
				else if(isDigit(Lexeme[i]))
				{
					TokenName[i]=new String("number");
					AttributeValues[i]=new String("Constant");
				}
			
				
				else if(Lexeme[i].equals("="))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Assignment");
				}
				
				else if(Lexeme[i].equals("+"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Addition");
				}
				
				else if(Lexeme[i].equals("-"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Subtraction");
				}
				
				else if(Lexeme[i].equals("*"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Multiplication");
				}
				
				else if(Lexeme[i].equals("/"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Division");
				}
				
				else if(Lexeme[i].equals("%"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Modulus");
				}
				
				else if(Lexeme[i].equals("^"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Power");
				}
				
				else if(Lexeme[i].equals("++"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Increment");
				}
				
				else if(Lexeme[i].equals("--"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Decrement");
				}
				
				else if(Lexeme[i].equals("<<"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Left shift");
				}
				
				else if(Lexeme[i].equals(">>"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Right shift");
				}
				
				else if(Lexeme[i].equals("&"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Bit Wise And");
				}
				
				else if(Lexeme[i].equals("|"))
				{
					TokenName[i]=new String("operator");
					AttributeValues[i]=new String("Bit Wise Or");
				}
								
				else if(Lexeme[i].equals("("))
				{
					TokenName[i]=new String("special symbol");
					AttributeValues[i]=new String("Opening braces");
				}
				
				else if(Lexeme[i].equals(")"))
				{
					TokenName[i]=new String("special symbol");
					AttributeValues[i]=new String("Closing braces");
				}
				else if(Lexeme[i].equals("{"))
				{
					TokenName[i]=new String("special symbol");
					AttributeValues[i]=new String("Left curly braces");
				}
				
				else if(Lexeme[i].equals("}"))
				{
					TokenName[i]=new String("special symbol");
					AttributeValues[i]=new String("Right curly braces");
				}
				
				else if(Lexeme[i].equals("["))
				{
					TokenName[i]=new String("special symbol");
					AttributeValues[i]=new String("Opening bracket");
				}
				
				else if(Lexeme[i].equals("]"))
				{
					TokenName[i]=new String("special symbol");
					AttributeValues[i]=new String("Closing bracket");
				}
				
				else if(Lexeme[i].equals(","))
				{
					TokenName[i]=new String("special symbol");
					AttributeValues[i]=new String("Comma");
				}
				
				else if(Lexeme[i].equals(";"))
				{
					TokenName[i]=new String("special symbol");
					AttributeValues[i]=new String("Semicolon");
				}
				
				else if(Lexeme[i].equals(":"))
				{
					TokenName[i]=new String("special symbol");
					AttributeValues[i]=new String("Colon");
				}
				
				else if(Lexeme[i].equals("main"))
				{
					TokenName[i]=new String("id");
					AttributeValues[i]=""+count;
					SymbolTable[count][0]="main";
					SymbolTable[count][1]=new String();
					count++;
				}
				
				else
				{
					TokenName[i]=new String("id");
					int j;
					
					for( j=0; j< count; j++)
					{
						if(SymbolTable[j][0].equals(Lexeme[i]))
						{
							flag=1;
							AttributeValues[i] =""+j;
							break;
						}
					}
					if(flag!=1)
					{
						SymbolTable[j][0]=new String(Lexeme[i]);
						int index=i;
						count++;
						while(index>0)
						{
							if(TokenName[index].equals(Lexeme[index]))
							{
								SymbolTable[j][1]=TokenName[index];
								AttributeValues[i] =""+j;
								break;
							}
							index--;				
						}
					}			
				}
			}
			
			
			// Write into output.txt 
                        
                        out.write("\t\t\t\t\tTokens:");
			out.newLine();
                        out.newLine();
                        
                        out.write("Lexemes\t\t\tToken Name\t\t\tAttribute Value");
                        out.newLine();
                        out.write("-----------------------------------------------------------------------------------");
                        out.newLine();
                        
			for(int i=0;i<Lexeme.length;i++)
			{
				if(TokenName[i].equals("id")||TokenName[i].equals("number")){
                                    
                                    
                                        out.write(Lexeme[i]+"\t\t\t" +TokenName[i]+"\t\t\t\t"+AttributeValues[i]);
                                        out.newLine();
					
                                }else
                                        out.write(Lexeme[i]+"\t\t\t" +TokenName[i]+"\t\t\t"+AttributeValues[i]);
                                        out.newLine();
					
			}
			
			
                        out.newLine();
                        out.newLine();
                        out.newLine();
                        out.newLine();
                        out.newLine();
                        out.newLine();
                        out.newLine();
                        out.newLine();
			
                        out.write("\t\t\t\t\tSymbol Table:");
			out.newLine();
                        out.newLine();
                        
                        out.write("Symbol\t\t\tData Type\t\tToken\t\tPointer to Symbol Table Entry");
                        out.newLine();
                        out.write("-------------------------------------------------------------------------------------------------");
                        out.newLine();
                        
                        
			for(int i=0; i<count;i++)
			{
				for(int j=0;j<2;j++)
				{
                                         out.write(SymbolTable[i][j]+"\t\t\t");
                                         //out.newLine();
                                        
				}
                                //out.newLine();
                                out.write("id\t\t\t"+i);
                                out.newLine();        
				
			}
			sc.close();
                              
        out.close();
        
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        System.out.println("Please Check Output.txt File into the Project");

	}
	
	
	public static void addString( int i, String line)
	{
		String s = new String();
		for(int j=position;j<i;j++)
		{
			s = s+line.charAt(j);
		}
		if(s.length()>0)
		{
			s=s.trim();
			Lexeme.add(s);	
		}
		position = i+1;
	}
	
	
	public static boolean isDigit(String str)
	{
		boolean result = str.matches("-?\\d+(\\.\\d+)?");
		return result;
	}
        
    
}
