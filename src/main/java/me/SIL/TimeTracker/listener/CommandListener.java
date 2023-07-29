package me.SIL.TimeTracker.listener;
import me.SIL.TimeTracker.config.TimeConfig;
import me.SIL.TimeTracker.TimeTracker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class CommandListener implements CommandExecutor {
    TimeConfig config;
    final TimeTracker plugin;

    public CommandListener(TimeTracker plugin, TimeConfig config){
        this.config = config;
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
        if (label.equalsIgnoreCase("colorcodes")){
            if(commandSender instanceof Player){
                commandSender.sendMessage("§bThere is no command implemented yet§r");
                return true;
            }
            else {
                commandSender.sendMessage("You must be a player!");
                return true;
            }
        }
        return false;        
    }
}
