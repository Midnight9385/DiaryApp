package LaF;

import com.formdev.flatlaf.FlatDarkLaf;

public class Purple
	extends FlatDarkLaf
{
	public static final String NAME = "Purple";

	public static boolean setup() {
		return setup( new Purple() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, Purple.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
