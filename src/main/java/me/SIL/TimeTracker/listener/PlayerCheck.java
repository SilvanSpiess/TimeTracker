package me.SIL.TimeTracker.listener;
import me.SIL.TimeTracker.config.TimeConfig;

import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;


public class PlayerCheck extends TimerTask {
    private Server server;
    private TimeConfig timeconfig;    

    public PlayerCheck(Server server, TimeConfig config) {
        this.server = server;
        this.timeconfig = config;
    }

    @Override
    public void run() {
        server.getOnlinePlayers().forEach(o -> {
            int playticks = o.getStatistic(Statistic.TOTAL_WORLD_TIME);
            int playtime = playticks/1200;
            //o.sendMessage("you have " + Integer.toString(playtime) + " mintues");
            if(timeconfig.getPersonalParams().contains(playtime) && (playticks - playtime*1200) <= 200) {
                o.sendMessage("§6§lCongratulations! §r§3§n" + o.getName() + " §r§2for playing §r§e§o" + Integer.toString(playtime) + " §r§2minutes on this Server!");
            }
            if(timeconfig.getGlobalParams().contains(playtime) && (playticks - playtime*1200) <= 200) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("§6§lA big applause for §r§3§n" + o.getName() + " §r§2for playing §r§e§o" + Integer.toString(playtime) + " §r§2minutes on this Server!");
                }
            }
            /*
            if(playtime == 97092) {
                o.sendMessage("§6You have §2" + Integer.toString(playtime) + " §6mintues");                
            }
            else if(playtime == 97086) {
                o.sendMessage("§6You have §2" + Integer.toString(playtime) + " §6mintues");
            }
            else if(playtime == 97088) {
                o.sendMessage("§6You have §2" + Integer.toString(playtime) + " §6mintues");
            }
            else if(playtime == 97090) {
                o.sendMessage("§6You have §2" + Integer.toString(playtime) + " §6mintues");
            }*/
        });
    }
}