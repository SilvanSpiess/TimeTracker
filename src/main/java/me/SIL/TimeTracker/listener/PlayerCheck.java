package me.SIL.TimeTracker.listener;
import me.SIL.TimeTracker.TimeTracker;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.Sound;

public class PlayerCheck extends TimerTask {
    private Server server;
    private TimeTracker plugin;
    private Boolean isMuted;
    private boolean isDisabled;
    
    public Boolean getMuted() {return isMuted;}
    public void setMuted(Boolean newMuted) {isMuted = newMuted;}
    public Boolean getDisabled() {return isDisabled;}
    public void setDisabled(Boolean newDisabled) {isDisabled = newDisabled;}

    public PlayerCheck(Server server, TimeTracker timetracker) {
        this.server = server;
        this.plugin = timetracker;
        isMuted = false;
        isDisabled = false;
    }

    @Override
    public void run() {
        server.getOnlinePlayers().forEach(o -> {
            int playticks = o.getStatistic(Statistic.TOTAL_WORLD_TIME);
            int playtime = playticks/1200;
            if(this.plugin.getTimeConfig().getPersonalParams().contains(playtime) && (playticks - playtime*1200) <= 200) {
                if(!isDisabled) {
                    o.sendMessage("§2Congratulations! §r§6§n" + o.getName() + "§r §2for playing §r§e§o" + Integer.toString(playtime) +
                                  " §r§2minutes on §r§5" + this.plugin.getTimeConfig().getConfigParams().get("Server") + "!");
                    if(!isMuted) {
                        o.playSound(o, Sound.valueOf(this.plugin.getTimeConfig().getConfigParams().get("PersonalSound")), 
                                       Float.valueOf(this.plugin.getTimeConfig().getConfigParams().get("PersonalVolume")),
                                       Float.valueOf(this.plugin.getTimeConfig().getConfigParams().get("PersonalPitch")));
                    }
                }                
            }
            if(this.plugin.getTimeConfig().getGlobalParams().contains(playtime) && (playticks - playtime*1200) <= 200) {
                for (Player player : Bukkit.getOnlinePlayers()) {                    
                    if(!isDisabled) {
                        player.sendMessage("§2A big applause for §r§6§n" + o.getName() + "§r §2for playing §r§e§o" + 
                                            Integer.toString(playtime) + " §r§2minutes on §r§5" + 
                                            this.plugin.getTimeConfig().getConfigParams().get("Server") + "!");
                        if(!isMuted) {
                            o.playSound(o, Sound.valueOf(this.plugin.getTimeConfig().getConfigParams().get("GlobalSound")), 
                                           Float.valueOf(this.plugin.getTimeConfig().getConfigParams().get("GlobalVolume")),
                                           Float.valueOf(this.plugin.getTimeConfig().getConfigParams().get("GlobalPitch")));
                        }
                    } 
                }
            }
        });
    }
}