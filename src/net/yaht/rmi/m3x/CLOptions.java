package net.yaht.rmi.m3x;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class CLOptions {
	private static Options options;
	private static CommandLineParser parser;
	private CommandLine commandLine;
	
	static {
		parser = new PosixParser();
		options = new Options();
		options.addOption("m", true, "Matrix A rows number.");
		options.addOption("n", true, "Matrix A columns number. Matrix B rows number.");
		options.addOption("k", true, "Matrix B columns number.");
		options.addOption("t", "tasks", true, "Number of threads to do the multiplication.");
		options.addOption("q", "quiet", false, "Turns on silent mode.");
	}
	
	public CLOptions(String[] args) throws ParseException {
		commandLine = parser.parse(options, args);
	}
	
	public int getM() {
		return Integer.valueOf(commandLine.getOptionValue("m"));
	}
	
	public int getN() {
		return Integer.valueOf(commandLine.getOptionValue("n"));
	}
	
	public int getK() {
		return Integer.valueOf(commandLine.getOptionValue("k"));
	}
	
	public int getThreadNumber() {
		return Integer.valueOf(commandLine.getOptionValue("t"));
	}
	
	public boolean isQuiet() {
		return commandLine.hasOption("q");
	}
}
