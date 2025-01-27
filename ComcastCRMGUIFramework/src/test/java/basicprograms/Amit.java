package basicprograms;

public class Amit {
//To see how we can pass the parameters in the main method via cmd
	public static void main(String[] args) {
		System.out.println(args.length);
		for(String var: args)
		{
			System.out.println(var);
		}
	}
}