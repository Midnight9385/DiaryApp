package util.UI.Source.model.options.themes;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class DarkPurpleLaf
	extends FlatIntelliJLaf
{
	public static final String NAME = "DarkPurpleLaf";

	public static boolean setup() {
		return setup( new DarkPurpleLaf() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, DarkPurpleLaf.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
