package app.ishan.jash.common;

import java.io.PrintStream;

import javax.naming.CommunicationException;

public class AppLog {
	public static final String DEBUG = "[DEBUG]";
	public static final String ERROR = "[ERROR]";
	public static final String INFO = "[INFO]";
	public static final String WARNING = "[WARNING]";
	PrintStream out;
	
	public AppLog(){
		out = System.out;
	}
	
	public AppLog(PrintStream printStream){
		if(printStream==null)
			out = System.out;
		this.out = printStream;
	}
	
	public void debug(String msg){
		out.println(DEBUG + " " + msg);
	}
	
	public void error(String msg){
		out.println(ERROR + " " + msg);
	}
	
	public void info(String msg){
		out.println(INFO + " " + msg);
	}
	
	public void warning(String msg){
		out.println(WARNING + " " + msg);
	}
	
	public void write(String msg, String level){
		if(DEBUG.equals(level))
			debug(msg);
		else if(ERROR.equals(level))
			error(msg);
		else if(INFO.equals(level))
			info(msg);
		else if(WARNING.equals(level))
			warning(msg);
	}

}
