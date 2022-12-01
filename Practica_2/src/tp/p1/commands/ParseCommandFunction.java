package tp.p1.commands;

import tp.p1.exception.CommandParseException;

@FunctionalInterface
public interface ParseCommandFunction {
	public abstract Command parse(String s) throws CommandParseException;
}
