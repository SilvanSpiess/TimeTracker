package me.SIL.TimeTracker;

import me.SIL.TimeTracker.config.TimeConfig;
import me.SIL.TimeTracker.listener.CommandListener;
import me.SIL.TimeTracker.listener.PlayerCheck;

import java.util.Date;
import java.util.Timer;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeTracker extends JavaPlugin {
    private TimeConfig config;
    private Logger logger;
    private Server server;
    private Timer scheduler;
    public PlayerCheck checker;
    public TimeConfig getTimeConfig() {return config;}

    @Override
    public void onEnable() {
        logger = getLogger();
        server = getServer();
        config = new TimeConfig(this);
        logger.info("TimeTracker running");
        scheduler = new Timer(true);
        checker = new PlayerCheck(server, this);
        scheduler.schedule(checker, new Date(), 10000);
        getCommand("TimeTracker").setExecutor(new CommandListener(this));
    }

    @Override
    public void onDisable() {
        logger.info("Disabling TimeTracker Plugin");
        if(scheduler != null)
            scheduler.cancel();        
    }
}
