package me.SIL.TimeTracker;

import me.SIL.TimeTracker.config.TimeConfig;
import me.SIL.TimeTracker.listener.PlayerCheck;

import java.util.Date;
import java.util.Timer;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeTracker extends JavaPlugin {
    TimeConfig config;
    //Player player;
    private Logger logger;
    private Server server;
    private Timer scheduler;
    
    @Override
    public void onEnable() {
        logger = getLogger();
        server = getServer();
        config = new TimeConfig(this);
        logger.info("TimeTracker running");
        scheduler = new Timer(true);
        scheduler.schedule(new PlayerCheck(server, config), new Date(), 10000);
        //getCommand("ColorCodes").setExecutor(new CommandListener(this, config)); //maybe leave out
    }

    @Override
    public void onDisable() {
        logger.info("Disabling TimeTracker Plugin");
        scheduler.cancel();
        
    }
}
