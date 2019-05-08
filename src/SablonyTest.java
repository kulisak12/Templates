import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;


public class SablonyTest {
	
	@Test
	public void noVar() {
		Scanner input = new Scanner("Some text without vars.");
		Variables vars = new Variables(new String[0]);
		assertEquals("Some text without vars.",
				Sablony.performReplacement(input, vars));
	}
	
	@Test
	public void multipleLines() {
		Scanner input = new Scanner("Some text\n"
				+ "without vars.");
		Variables vars = new Variables(new String[0]);
		assertEquals("Some text\n"
				+ "without vars.",
				Sablony.performReplacement(input, vars));
	}
	
	@Test
	public void oneVar() {
		Scanner input = new Scanner("Welcome {{ name }} to the stage!");
		Variables vars = new Variables(new String[] {"--var=name=Alice"});
		assertEquals("Welcome Alice to the stage!",
				Sablony.performReplacement(input, vars));
	}
	
	@Test
	public void multipleVars() {
		Scanner input = new Scanner("My name is {{ me }}.\n"
				+ "And you are {{ you }}, right?");
		Variables vars = new Variables(new String[] {"--var=me=Alice", "--var=you=Bob"});
		assertEquals("My name is Alice.\n"
				+ "And you are Bob, right?",
				Sablony.performReplacement(input, vars));
	}
	
	@Test
	public void twoVarsOneLine() {
		Scanner input = new Scanner("How much is {{ divisor }} divided by {{ divident }}?");
		Variables vars = new Variables(new String[] {"--var=divisor=20", "--var=divident=5"});
		assertEquals("How much is 20 divided by 5?",
				Sablony.performReplacement(input, vars));
	}
	
	@Test
	public void numberInVarName() {
		Scanner input = new Scanner("Welcome {{ person123 }} to the stage!");
		Variables vars = new Variables(new String[] {"--var=person123=yourboy2001"});
		assertEquals("Welcome yourboy2001 to the stage!",
				Sablony.performReplacement(input, vars));
	}
	
	@Test
	public void spaceInVarName() {
		Scanner input = new Scanner("Welcome {{ contestant name }} to the stage!");
		Variables vars = new Variables(new String[] {"--var=contestant name=Alice Honey"});
		assertEquals("Welcome Alice Honey to the stage!",
				Sablony.performReplacement(input, vars));
	}

}
