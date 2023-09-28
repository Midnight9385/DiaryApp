package LaF;

import com.formdev.flatlaf.FlatDarculaLaf;

public class Ocean
	extends FlatDarculaLaf
{
	public static final String NAME = "Ocean";

	public static boolean setup() {
		return setup( new Ocean() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, Ocean.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
