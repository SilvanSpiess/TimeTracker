package me.SIL.TimeTracker.listener;
import me.SIL.TimeTracker.TimeTracker;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandListener implements TabExecutor {
    private TimeTracker plugin;

    public CommandListener(TimeTracker plugin){
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("timetracker")) {
            if (args.length == 3) {
                if (args[0].equals("moderate") && commandSender.hasPermission("TIMETRACKER.moderate")) {
                    if(commandSender instanceof ConsoleCommandSender) {
                        commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                        return true;
                    }
                    else if(args[1].equals("play")) {
                        switch (args[2]) {
                            case "personalsound":
                                commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.AQUA + "Playing personal sound");
                                ((Player) commandSender).playSound((Player) commandSender, Sound.valueOf(this.plugin.getTimeConfig().getConfigParams().get("PersonalSound")), 
                                                                                           Float.valueOf(this.plugin.getTimeConfig().getConfigParams().get("PersonalVolume")),
                                                                                           Float.valueOf(this.plugin.getTimeConfig().getConfigParams().get("PersonalPitch")));
                                return true;
                            case "globalsound":
                                commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.AQUA + "Playing global sound");
                                ((Player) commandSender).playSound((Player) commandSender, Sound.valueOf(this.plugin.getTimeConfig().getConfigParams().get("GlobalSound")), 
                                                                                           Float.valueOf(this.plugin.getTimeConfig().getConfigParams().get("GlobalVolume")),
                                                                                           Float.valueOf(this.plugin.getTimeConfig().getConfigParams().get("GlobalPitch")));
                                return true;
                        }
                    }                    
                }
            }
            if (args.length == 1) {
                switch (args[0]) {
                    case "info":
                        if (commandSender instanceof ConsoleCommandSender) {
                            commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                            return true;
                        }
                        commandSender.sendMessage(ChatColor.DARK_AQUA + "Plugin to track your playtime on " + 
                                                  ChatColor.LIGHT_PURPLE + this.plugin.getTimeConfig().getConfigParams().get("Server"));
                        if(this.plugin.checker.getDisabled()) {
                            commandSender.sendMessage(ChatColor.AQUA + "Serverwide messages and sounds: " + ChatColor.RED + " off");
                        }
                        else if(this.plugin.checker.getMuted()) {
                            commandSender.sendMessage(ChatColor.AQUA + "Serverwide sounds are " + ChatColor.RED + "off");
                        }
                        else {
                            commandSender.sendMessage(ChatColor.AQUA + "Serverwide messages and sounds are " + ChatColor.GREEN + " on");
                        }
                        if(this.plugin.getTimeConfig().getDisabledPlayers().containsValue(((Entity) commandSender).getUniqueId().toString())) {
                            commandSender.sendMessage(ChatColor.AQUA + "Your messages and sounds are " + ChatColor.RED + " off");
                            return true;
                        }
                        else if(this.plugin.getTimeConfig().getMutedPlayers().containsValue(((Entity) commandSender).getUniqueId().toString())) {
                            commandSender.sendMessage(ChatColor.AQUA + "Your sounds are " + ChatColor.RED + "off");
                            return true;
                        }
                        else {
                            commandSender.sendMessage(ChatColor.AQUA + "Your messages and sounds are " + ChatColor.GREEN + " on");
                            return true;
                        }
                        
                    case "disable":
                        if (commandSender instanceof ConsoleCommandSender) {
                            commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                            return true;
                        }
                        if (commandSender instanceof Player) {
                            this.plugin.getTimeConfig().addUniquePlayer(this.plugin.getTimeConfig().getDisabledPlayers(), (Player) commandSender);
                            commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.YELLOW + "Messages and sounds have been disabled");
                            return true;
                        }
                    case "enable":
                        if (commandSender instanceof ConsoleCommandSender) {
                            commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                            return true;
                        }
                        if (commandSender instanceof Player) {
                            this.plugin.getTimeConfig().removeUniquePlayer(this.plugin.getTimeConfig().getDisabledPlayers(), (Player) commandSender);
                            commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.DARK_GREEN + "Messages and sounds have been enabled");
                            return true;
                        }
                    case "mute":
                        if (commandSender instanceof ConsoleCommandSender) {
                            commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                            return true;
                        }
                        if (commandSender instanceof Player) {
                            this.plugin.getTimeConfig().addUniquePlayer(this.plugin.getTimeConfig().getMutedPlayers(), (Player) commandSender);
                            commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.YELLOW + "Sounds have been muted");
                            return true;
                        }
                    case "unmute":
                        if (commandSender instanceof ConsoleCommandSender) {
                            commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                            return true;
                        }
                        if (commandSender instanceof Player) {
                            this.plugin.getTimeConfig().removeUniquePlayer(this.plugin.getTimeConfig().getMutedPlayers(), (Player) commandSender);
                            commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.DARK_GREEN + "Sounds have been unmuted");
                            return true;
                        }

                }
            }
            if (args.length == 2) {
                switch (args[0]) {
                    case "moderate":
                        switch (args[1]) {
                            case "info":
                                if (commandSender instanceof ConsoleCommandSender || !commandSender.hasPermission("TIMETRACKER.moderate")) {
                                    commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                                    return true;
                                }
                                commandSender.sendMessage(ChatColor.DARK_AQUA + "Plugin to track your playtime on " + 
                                                        ChatColor.LIGHT_PURPLE + this.plugin.getTimeConfig().getConfigParams().get("Server"));
                                commandSender.sendMessage(ChatColor.AQUA + "Personal sound: " + ChatColor.DARK_PURPLE + this.plugin.getTimeConfig().getConfigParams().get("PersonalSound") + "\n" +
                                                        ChatColor.AQUA + "Personal volume: " + ChatColor.GREEN + this.plugin.getTimeConfig().getConfigParams().get("PersonalVolume") + 
                                                        ChatColor.AQUA + ", Personal pitch: " + ChatColor.GREEN + this.plugin.getTimeConfig().getConfigParams().get("PersonalPitch"));
                                commandSender.sendMessage(ChatColor.AQUA + "Global sound: " + ChatColor.DARK_PURPLE + this.plugin.getTimeConfig().getConfigParams().get("GlobalSound") + "\n" + 
                                                        ChatColor.AQUA + "Global volume: " + ChatColor.GREEN + this.plugin.getTimeConfig().getConfigParams().get("GlobalVolume") + 
                                                        ChatColor.AQUA + ", Global pitch: " + ChatColor.GREEN + this.plugin.getTimeConfig().getConfigParams().get("GlobalPitch"));
                                if (this.plugin.checker.getDisabled()) {
                                    commandSender.sendMessage(ChatColor.AQUA + "Messages and sounds: " + ChatColor.RED + " off");
                                    return true;
                                }
                                else if (this.plugin.checker.getMuted()) {
                                    commandSender.sendMessage(ChatColor.AQUA + "Sounds: " + ChatColor.RED + "off");
                                    return true;
                                }
                                else {
                                    commandSender.sendMessage(ChatColor.AQUA + "Messages and sounds: " + ChatColor.GREEN + " on");
                                    return true;
                                }
                            case "disable":
                                if (!commandSender.hasPermission("TIMETRACKER.moderate")) {
                                    commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");                                    
                                    return true;
                                }
                                this.plugin.checker.setDisabled(true);
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    player.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.YELLOW + "Serverwide messages and sounds have been disabled");
                                }
                                return true;
                            case "enable":
                                if (!commandSender.hasPermission("TIMETRACKER.moderate")) {
                                    commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                                    return true;
                                }
                                this.plugin.checker.setDisabled(false);
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    player.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.DARK_GREEN + "Serverwide messages and sounds have been enabled");
                                }
                                return true;
                            case "mute":
                                if (!commandSender.hasPermission("TIMETRACKER.moderate")) {
                                    commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                                    return true;
                                }
                                this.plugin.checker.setMuted(true);
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    player.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.YELLOW + "Serverwide sounds have been muted");
                                }
                                return true;
                            case "unmute":
                                if (!commandSender.hasPermission("TIMETRACKER.moderate")) {
                                    commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                                    return true;
                                }
                                this.plugin.checker.setMuted(false);
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    player.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.DARK_GREEN + "Serverwide sounds have been unmuted");
                                }
                                return true;
                            case "play":
                                if (!commandSender.hasPermission("TIMETRACKER.moderate")) {
                                    commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                                    return true;
                                }
                                if(this.plugin.getTimeConfig().reloadPlugin())
                                    commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.DARK_GREEN + "Plugin has been reloaded");
                                return true;
                            case "reload":
                                if (!commandSender.hasPermission("TIMETRACKER.moderate")) {
                                    commandSender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
                                    return true;
                                }
                                if(this.plugin.getTimeConfig().reloadPlugin())
                                    commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "[TimeTracker] " + ChatColor.DARK_GREEN + "Plugin has been reloaded");
                                return true;
                        }
                        break;
                }
            }
        }
        return false;        
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (alias.equalsIgnoreCase("timetracker")) {
            List<String> hints = new ArrayList<>();
            if (args.length == 0) {
                hints.add("info");
                if (this.plugin.getTimeConfig().getDisabledPlayers().containsValue(((Entity) commandSender).getUniqueId().toString())) {
                    hints.add("enable");
                } else {
                    hints.add("disable");
                }
                if (this.plugin.getTimeConfig().getMutedPlayers().containsValue(((Entity) commandSender).getUniqueId().toString())) {
                    hints.add("unmute");
                } else {
                    hints.add("mute");
                }
                if (commandSender.hasPermission("TIMETRACKER.moderate")) {
                    hints.add("moderate");
                }
            }
            if (args.length == 1) {
                if (args[0].length() == 0) {
                    hints.add("info");
                    if (this.plugin.getTimeConfig().getDisabledPlayers().containsValue(((Entity) commandSender).getUniqueId().toString())) {
                        hints.add("enable");
                    } else {
                        hints.add("disable");
                    }
                    if (this.plugin.getTimeConfig().getMutedPlayers().containsValue(((Entity) commandSender).getUniqueId().toString())) {
                        hints.add("unmute");
                    } else {
                        hints.add("mute");
                    }
                    if (commandSender.hasPermission("TIMETRACKER.moderate")) {
                        hints.add("moderate");
                    }
                } else {
                    if ("moderate".startsWith(args[0]) && commandSender.hasPermission("TIMETRACKER.moderate")) {
                        if (args[0].equals("moderate")) {
                            hints.add("info");
                            if(this.plugin.checker.getDisabled()) 
                                hints.add("enable");
                            else
                                hints.add("disable");
                            if(this.plugin.checker.getMuted())
                                hints.add("unmute");
                            else
                                hints.add("mute");
                            hints.add("play");
                            hints.add("reload");
                        } else
                            hints.add("moderate");
                    } else if ("info".startsWith(args[0])) {
                        hints.add("info");
                    }
                }
            }
            if (args.length == 2) {
                if (args[0].equals("moderate") && commandSender.hasPermission("TIMETRACKER.moderate")) {
                    if (args[1].length() == 0) {
                        hints.add("info");
                        if(this.plugin.checker.getDisabled()) 
                            hints.add("enable");
                        else
                            hints.add("disable");
                        if(this.plugin.checker.getMuted())
                            hints.add("unmute");
                        else
                            hints.add("mute");
                        hints.add("play");
                        hints.add("reload");
                    } else {
                        if ("info".startsWith(args[1])) {
                            if(!args[1].equals("info")) {
                                hints.add("info");
                            }
                        }
                        if ("disable".startsWith(args[1]) && !this.plugin.checker.getDisabled()) {
                            if (!args[1].equals("disable")) {
                                hints.add("disable");
                            }
                        }
                        if ("enable".startsWith(args[1]) && this.plugin.checker.getDisabled()) {
                            if (!args[1].equals("enable")) {
                                hints.add("enable");
                            }
                        }
                        if ("mute".startsWith(args[1]) && !this.plugin.checker.getMuted()) {
                            if (!args[1].equals("mute")) {
                                hints.add("mute");
                            }
                        }
                        if ("unmute".startsWith(args[1]) && this.plugin.checker.getMuted()) {
                            if (!args[1].equals("unmute")) {
                                hints.add("unmute");
                            }
                        }                        
                        if ("reload".startsWith(args[1])) {
                            if (!args[1].equals("reload")) {
                                hints.add("reload");
                            }
                        }
                    }
                }
            }
            if (args.length == 3) {
                if (args[0].equals("moderate") && commandSender.hasPermission("TIMETRACKER.moderate")) {
                    if (args[1].equals("play")) {
                        hints.add("personalsound");
                        hints.add("globalsound");
                    }
                }
            }
            return hints;
        }
        
        return null;
    }
}
