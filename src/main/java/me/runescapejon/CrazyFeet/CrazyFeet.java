package me.runescapejon.CrazyFeet;

import java.util.*;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import me.runescapejon.CrazyFeet.Commands.CrazyFireCommands;
import me.runescapejon.CrazyFeet.Commands.CrazyHeartCommands;
import me.runescapejon.CrazyFeet.Commands.CrazyMagicCommands;
import me.runescapejon.CrazyFeet.Commands.CrazyNoteCommands;
import me.runescapejon.CrazyFeet.Commands.CrazyPearlCommands;
import me.runescapejon.CrazyFeet.Commands.CrazySmokeCommands;
import me.runescapejon.CrazyFeet.Commands.CrazyWitchCommands;
import me.runescapejon.CrazyFeet.Commands.Util.CrazyCheckCommands;
import me.runescapejon.CrazyFeet.Commands.Util.CrazyDisableCmds;
import me.runescapejon.CrazyFeet.Commands.Util.CrazyFeetAdminCmd;
import me.runescapejon.CrazyFeet.Commands.Util.CrazyFeetCommands;
import me.runescapejon.CrazyFeet.Listeners.CrazyFeetListener;
import me.runescapejon.CrazyFeet.Commands.Util.Files.CrazyAutoFireFile;

@Plugin(id = "crazyfeetsponge", name = "CrazyFeetSponge", authors = {"runescapejon"}, description = "CrazyFeet Ported over to Sponge", version = "1.5")
public class CrazyFeet {
	public static ArrayList<Player> crazyFireHead;
	public static ArrayList<Player> crazyHeartHead;
	public static ArrayList<Player> crazyMagicHead;
	public static ArrayList<Player> crazyNoteHead;
	public static ArrayList<Player> crazyWitchHead;
	public static ArrayList<Player> crazySmokeHead;
	public static ArrayList<Player> crazyPearlHead;
	public static ArrayList<Player> crazyFire;
	public static ArrayList<Player> crazySmoke;
	public static ArrayList<Player> crazyMagic;
	public static ArrayList<Player> crazyPearl;
	public static ArrayList<Player> crazynote;
	public static ArrayList<Player> crazyWitch;
	public static ArrayList<Player> crazyHeart;
	public static CrazyAutoFireFile aFirePlayer;

	HashMap<List<String>, CommandSpec> subcommands = new HashMap<>();
	//private File autoFirePlayers;
	//public static CrazyAutoSmokeFile aSmokeP;
	//public static CrazyAutoPearlFile aPearlP;
	//public static CrazyAutoMagicFile aMagicP;
    //public static CrazyAutonoteFile anoteP;
	//public static CrazyAutoWitchFile aWitchP;
	//public static CrazyAutoHeartFile aHeartP;

	
	
	//rewriting this
	/**
	String folder = this.getDataFolder().getAbsolutePath();
	(new File(folder)).mkdir();
	*/
	
	
	@Listener
	public void onServerStart(GameStartedServerEvent event) {
		crazyFire = new ArrayList<>();
		crazynote = new ArrayList<>();
		crazyMagic = new ArrayList<>();
		crazyHeart = new ArrayList<>();
		crazySmoke = new ArrayList<>();
		crazyPearl = new ArrayList<>();
		crazyWitch = new ArrayList<>();
	}

	@Listener
	public void onGameInitlization(GameInitializationEvent event) {
		//aFirePlayer.loadAutoFirePlayers();
	//	autoFirePlayers = new File(folder+File.separator+"AutoFirePlayers.txt");
		//aFirePlayer = new CrazyAutoFireFile(autoFirePlayers);
		// CrazyFeet Register
		subcommands.put(Collections.singletonList("admin"), CommandSpec.builder()
				.description(Text.of("crazyfeet admin"))
				.permission("crazyfeet.admin")
				.executor(new CrazyFeetAdminCmd())
				.build());
		
		CommandSpec CrazyFeetSpec = CommandSpec.builder()
				.description(Text.of("crazyfeet"))
				.permission("crazyfeet.crazyfeet")
				.executor(new CrazyFeetCommands()).children(subcommands).build();
		Sponge.getCommandManager().register(this, CrazyFeetSpec, "crazyfeet");

		//CrazyFeetListener Registering Here
		CrazyFeetListener listener = new CrazyFeetListener();
		Sponge.getEventManager().registerListeners(this, listener);
		
		//CrazyFeetJoinListener Registering Here
		//CrazyFeetJoinListener joinlistener = new CrazyFeetJoinListener();
		//Sponge.getEventManager().registerListeners(this, joinlistener);

		// CrazyFire <PlayerName> - Register and <PlayerName> is Optional
		CommandSpec CrazyFireSpec = CommandSpec.builder()
				.description(Text.of("crazyfire to enable/disable magic particles"))
				.permission("crazyfeet.crazyfire")
				.arguments(GenericArguments.firstParsing(GenericArguments.flags().buildWith(GenericArguments.firstParsing(GenericArguments.optional(GenericArguments.player(Text.of("target"))), GenericArguments.optional(GenericArguments.string(Text.of("targets")))))))
				.executor(new CrazyFireCommands())
				.build();
		Sponge.getCommandManager().register(this, CrazyFireSpec, "crazyfire");
		
		// Crazynote <PlayerName> - Register and <PlayerName> is Optional
		CommandSpec CrazyNoteSpec = CommandSpec.builder()
				.description(Text.of("crazymagic to enable/disable note particles"))
				.permission("crazyfeet.crazynote")
				.arguments(GenericArguments.firstParsing(GenericArguments.flags().buildWith(GenericArguments.firstParsing(GenericArguments.optional(GenericArguments.player(Text.of("target"))), GenericArguments.optional(GenericArguments.string(Text.of("targets")))))))
				.executor(new CrazyNoteCommands())
				.build();
		Sponge.getCommandManager().register(this, CrazyNoteSpec, "crazynote");

		// Crazymagic <PlayerName> - Register and <PlayerName> is Optional
		CommandSpec CrazyMagicSpec = CommandSpec.builder()
				.description(Text.of("crazymagic to enable/disable magic particles"))
				.permission("crazyfeet.crazymagic")
				.arguments(GenericArguments.firstParsing(GenericArguments.flags().buildWith(GenericArguments.firstParsing(GenericArguments.optional(GenericArguments.player(Text.of("target"))), GenericArguments.optional(GenericArguments.string(Text.of("targets")))))))
				.executor(new CrazyMagicCommands())
				.build();
		Sponge.getCommandManager().register(this, CrazyMagicSpec, "crazymagic");
	
		// Crazyheart <PlayerName> - Register and <PlayerName> is Optional
		CommandSpec CrazyHeartSpec = CommandSpec.builder()
				.description(Text.of("crazymagic to enable/disable heart particles"))
				.permission("crazyfeet.crazyheart")
				.arguments(GenericArguments.firstParsing(GenericArguments.flags().buildWith(GenericArguments.firstParsing(GenericArguments.optional(GenericArguments.player(Text.of("target"))), GenericArguments.optional(GenericArguments.string(Text.of("targets")))))))
				.executor(new CrazyHeartCommands()).build();
		Sponge.getCommandManager().register(this, CrazyHeartSpec, "crazyheart");
		
		// CrazySmoke <PlayerName> - Register and <PlayerName> is Optional
		CommandSpec CrazySmokeSpec = CommandSpec.builder()
				.description(Text.of("crazymagic to enable/disable Smoke particles"))
				.permission("crazyfeet.crazysmoke")
				.arguments(GenericArguments.firstParsing(GenericArguments.flags().buildWith(GenericArguments.firstParsing(GenericArguments.optional(GenericArguments.player(Text.of("target"))), GenericArguments.optional(GenericArguments.string(Text.of("targets")))))))
				.executor(new CrazySmokeCommands())
				.build();
		Sponge.getCommandManager().register(this, CrazySmokeSpec, "crazysmoke");
		
		// CrazyPearl <PlayerName> - Register and <PlayerName> is Optional
		CommandSpec CrazypearlSpec = CommandSpec.builder()
				.description(Text.of("crazymagic to enable/disable Pearl particles"))
				.permission("crazyfeet.crazypearl")
				.arguments(GenericArguments.firstParsing(GenericArguments.flags().buildWith(GenericArguments.firstParsing(GenericArguments.optional(GenericArguments.player(Text.of("target"))), GenericArguments.optional(GenericArguments.string(Text.of("targets")))))))
				.executor(new CrazyPearlCommands())
				.build();
		Sponge.getCommandManager().register(this, CrazypearlSpec, "crazypearl");
		
		// CrazyWitch <PlayerName> - Register and <PlayerName> is Optional
		CommandSpec CrazyWitchSpec = CommandSpec.builder()
				.description(Text.of("crazymagic to enable/disable Witch particles"))
				.permission("crazyfeet.crazywitch")
				.arguments(GenericArguments.firstParsing(GenericArguments.flags().buildWith(GenericArguments.firstParsing(GenericArguments.optional(GenericArguments.player(Text.of("target"))), GenericArguments.optional(GenericArguments.string(Text.of("targets")))))))
				.executor(new CrazyWitchCommands()).build();
		Sponge.getCommandManager().register(this, CrazyWitchSpec, "crazywitch");
		/*
		// Crazyautofeet Register
		CommandSpec CrazyautofeetSpec = CommandSpec.builder().description(Text.of("Displays a help menu for Crazy-Auto"))
				.permission("crazyfeet.autofeet").executor(new CrazyAutoFeetCommands()).build();
		Sponge.getCommandManager().register(this, CrazyautofeetSpec, "crazyautofeet");
		*/
		//crazycheck <PlayerName> - Register and <PlayerName> is Optional
		CommandSpec CrazyCheckSpec = CommandSpec.builder()
				.description(Text.of("crazycheck your particles status - helpful to see what enabled"))
				.permission("crazyfeet.crazycheck")
				.arguments(GenericArguments.firstParsing(GenericArguments.flags().buildWith(GenericArguments.firstParsing(GenericArguments.optional(GenericArguments.player(Text.of("target"))), GenericArguments.optional(GenericArguments.string(Text.of("targets")))))))
				.executor(new CrazyCheckCommands())
				.build();
		Sponge.getCommandManager().register(this, CrazyCheckSpec, "crazycheck");
	
		//crazydisable <PlayerName> - Register and <PlayerName> is Optional
		CommandSpec CrazyDisableSpec = CommandSpec.builder()
				.description(Text.of("crazydisable disable your/other particles all of them"))
				.permission("crazyfeet.crazydisable")
				.arguments(GenericArguments.firstParsing(GenericArguments.flags().buildWith(GenericArguments.firstParsing(GenericArguments.optional(GenericArguments.player(Text.of("target"))), GenericArguments.optional(GenericArguments.string(Text.of("targets")))))))
				.executor(new CrazyDisableCmds())
				.build();
		Sponge.getCommandManager().register(this, CrazyDisableSpec, "crazydisable");
		
		/*
		//crazyautofire <PlayerName> - Register and <PlayerName> is Optional
		CommandSpec CrazyAutoFireSpec = CommandSpec.builder().description(Text.of("crazyautofire enable/disable auto fire when you join")) 
				.permission("CrazyFeet.crazyautofire").arguments(GenericArguments.firstParsing(GenericArguments.flags()
						.buildWith(GenericArguments.firstParsing(GenericArguments.optional(GenericArguments.player(Text.of("target"))), GenericArguments.optional(GenericArguments.string(Text.of("targets"))))))).executor(new CrazyAutoFire()).build();
		Sponge.getCommandManager().register(this, CrazyAutoFireSpec, "crazyautofire");
	*/
	}
	
    public CrazyAutoFireFile getAFirePlayers() {
		return aFirePlayer;
	}
}
