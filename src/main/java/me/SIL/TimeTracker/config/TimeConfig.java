package me.SIL.TimeTracker.config;

import me.SIL.TimeTracker.TimeTracker;
import me.SIL.TimeTracker.config.TimeConfig;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.util.logging.Logger;

import org.bukkit.entity.Player;

public class TimeConfig {
    //private TimeTracker plugin;
    private Logger logger;
    
    private File PersonalFile, GlobalFile, ConfigFile, DisabledPlayersFile, MutedPlayersFile;
    private LinkedList<Integer> PersonalParams, GlobalParams, defaultPersonalParams, defaultGlobalParams;
    private Map<String, String> DisabledPlayers, MutedPlayers, ConfigParams, defaultConfigParams;

    public LinkedList<Integer> getPersonalParams() {return PersonalParams;}
    public void setPersonalParams(LinkedList<Integer> personalParams) {this.PersonalParams = personalParams;}

    public LinkedList<Integer> getGlobalParams() {return GlobalParams;}
    public void setGlobalParams(LinkedList<Integer> globalParams) {this.GlobalParams = globalParams;}

    public Map<String, String> getDisabledPlayers() {return DisabledPlayers;}
    public void setDisabledPlayers(Map<String, String> disabledPlayers) {this.DisabledPlayers = disabledPlayers;}

    public Map<String, String> getMutedPlayers() {return MutedPlayers;}
    public void setMutedPlayers(Map<String, String> mutedPlayers) {this.MutedPlayers = mutedPlayers;}

    public Map<String, String> getConfigParams() {return ConfigParams;}
    public void setConfigParams(Map<String, String> configParams) {this.ConfigParams = configParams;}

    private void writeDefaultPersonalParams() {
        logger.info("Writing personal default files");
        // Fill the default Personal Set
        defaultPersonalParams = new LinkedList<>();
        defaultPersonalParams.add(1000);
        defaultPersonalParams.add(5000);
        defaultPersonalParams.add(11111);
        defaultPersonalParams.add(22222);
        defaultPersonalParams.add(33333);
        defaultPersonalParams.add(44444);
        defaultPersonalParams.add(55555);
        defaultPersonalParams.add(66666);
        defaultPersonalParams.add(77777);
        defaultPersonalParams.add(88888);
        defaultPersonalParams.add(99999);
        defaultPersonalParams.add(12345);
        defaultPersonalParams.add(25000);
        defaultPersonalParams.add(54321);
        defaultPersonalParams.add(75000);
        defaultPersonalParams.add(123456);
        File defaultPersonalFile = new File("./plugins/TimeTracker/PersonalTimes.txt");
        try {
            defaultPersonalFile.createNewFile();
            FileWriter myWriter1 = new FileWriter("./plugins/TimeTracker/PersonalTimes.txt");
            for (Integer i : defaultPersonalParams) {
                myWriter1.write(i.toString() + "\n");
            }
            myWriter1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDefaultGlobalParams() {
        logger.info("Writing global default files");
        // Fill the default Personal Set
        defaultGlobalParams = new LinkedList<>();
        defaultGlobalParams.add(10000);
        defaultGlobalParams.add(50000);
        defaultGlobalParams.add(69420);
        defaultGlobalParams.add(100000);
        defaultGlobalParams.add(111111);
        defaultGlobalParams.add(150000);
        defaultGlobalParams.add(200000);
        defaultGlobalParams.add(222222);
        defaultGlobalParams.add(250000);
        defaultGlobalParams.add(300000);
        defaultGlobalParams.add(333333);
        defaultGlobalParams.add(350000);
        defaultGlobalParams.add(400000);
        defaultGlobalParams.add(444444);
        defaultGlobalParams.add(450000);
        defaultGlobalParams.add(500000);
        File defaultGlobalFile = new File("./plugins/TimeTracker/GlobalTimes.txt");
        try {
            defaultGlobalFile.createNewFile();
            FileWriter myWriter = new FileWriter("./plugins/TimeTracker/GlobalTimes.txt");
            for (Integer j : defaultGlobalParams) {
                myWriter.write(j.toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDefaultConfigParams() {
        logger.info("Writing default config file");
        defaultConfigParams = new HashMap<>();
        defaultConfigParams.put("Server", "Nebula");
        defaultConfigParams.put("PersonalSound", "UI_TOAST_CHALLENGE_COMPLETE");
        defaultConfigParams.put("PersonalVolume", "0.5f");
        defaultConfigParams.put("PersonalPitch", "1.0f");
        defaultConfigParams.put("GlobalSound", "UI_TOAST_CHALLENGE_COMPLETE");
        defaultConfigParams.put("GlobalVolume", "0.5f");
        defaultConfigParams.put("GlobalPitch", "1.0f");
        File ConfigFile = new File("./plugins/TimeTracker/TimeConfig.txt");
        try {
            ConfigFile.createNewFile();
            FileWriter myWriter = new FileWriter("./plugins/TimeTracker/TimeConfig.txt");
            for (String key : defaultConfigParams.keySet()) {
                myWriter.write(key + ": " + defaultConfigParams.get(key) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialisePlayers() {
        if(new File("./plugins/TimeTracker/DisabledPlayers.txt").exists()) {
            try {
                Scanner myReader = new Scanner(DisabledPlayersFile);
                while (myReader.hasNextLine()) {
                    String LineRead = myReader.nextLine();
                    if (LineRead.contains(": ")) {
                        String[] ParsedLine = LineRead.split(": ");
                        DisabledPlayers.put(ParsedLine[0], ParsedLine[1]);
                    }
                } myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {            
            try {                
                DisabledPlayersFile.createNewFile();
                FileWriter myWriter = new FileWriter("./plugins/TimeTracker/DisabledPlayers.txt");
                myWriter.write("Names: UUID\n");            
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if(new File("./plugins/TimeTracker/MutedPlayers.txt").exists()) {
            try {
                Scanner myReader = new Scanner(MutedPlayersFile);
                while (myReader.hasNextLine()) {
                    String LineRead = myReader.nextLine();
                    if (LineRead.contains(": ")) {
                        String[] ParsedLine = LineRead.split(": ");
                        MutedPlayers.put(ParsedLine[0], ParsedLine[1]);
                    }
                } myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
        } else {
            try {                
                MutedPlayersFile.createNewFile();
                FileWriter myWriter = new FileWriter("./plugins/TimeTracker/MutedPlayers.txt");
                myWriter.write("Names: UUID\n");            
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void addUniquePlayer(Map<String, String> players, Player p) {
        if(!players.containsValue(p.getUniqueId().toString())) {
            players.put(p.getName(), p.getUniqueId().toString());
        }
    }
    public void removeUniquePlayer(Map<String, String> players, Player p) {
        if(players.containsValue(p.getUniqueId().toString())) {
            players.remove(p.getName(), p.getUniqueId().toString());
        }        
    }

    public void storePlayers() {
        if(new File("./plugins/TimeTracker/DisabledPlayers.txt").exists()) {             
            try {
                new FileWriter("./plugins/TimeTracker/DisabledPlayers.txt", false).close();
                FileWriter myWriter = new FileWriter("./plugins/TimeTracker/DisabledPlayers.txt");
                myWriter.write("Names: UUID\n");
                for (String key : DisabledPlayers.keySet()) {
                    myWriter.write(key + ": " + DisabledPlayers.get(key) + "\n");
                } myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }            
        }
        else {            
            try {                
                DisabledPlayersFile.createNewFile();
                FileWriter myWriter = new FileWriter("./plugins/TimeTracker/DisabledPlayers.txt");
                myWriter.write("Names: UUID\n");            
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if(new File("./plugins/TimeTracker/MutedPlayers.txt").exists()) {             
            try {
                new FileWriter("./plugins/TimeTracker/MutedPlayers.txt", false).close();
                FileWriter myWriter = new FileWriter("./plugins/TimeTracker/MutedPlayers.txt");
                myWriter.write("Names: UUID\n");
                for (String key : MutedPlayers.keySet()) {
                    myWriter.write(key + ": " + MutedPlayers.get(key) + "\n");
                } myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }            
        }
        else {
            try {                
                MutedPlayersFile.createNewFile();
                FileWriter myWriter = new FileWriter("./plugins/TimeTracker/MutedPlayers.txt");
                myWriter.write("Names: UUID\n");            
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readPersonalParams() {
        try {
            Scanner myReader = new Scanner(PersonalFile);
            while (myReader.hasNextLine()) {
                PersonalParams.add(Integer.parseInt(myReader.nextLine()));
            } myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readGlobalParams() {
        try {
            Scanner myReader = new Scanner(GlobalFile);
            while (myReader.hasNextLine()) {
                GlobalParams.add(Integer.parseInt(myReader.nextLine()));
            } myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readConfigParams() {
        try {
            Scanner myReader = new Scanner(ConfigFile);
            while (myReader.hasNextLine()) {
                String LineRead = myReader.nextLine();
                if (LineRead.contains(": ")) {
                    String[] ParsedLine = LineRead.split(": ");
                    ConfigParams.put(ParsedLine[0], ParsedLine[1]);
                }
            } myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean reloadPlugin() {
        PersonalParams.clear();
        GlobalParams.clear();
        ConfigParams.clear();
        loadDirectory();

        DisabledPlayers.clear();
        MutedPlayers.clear();
        initialisePlayers();
        logger.info("TimeTracker Plugin reloaded successfully");
        return true;
    }

    public TimeConfig(TimeTracker plugin) {
        //this.plugin = plugin;
        logger = plugin.getLogger();
        PersonalFile = new File("./plugins/TimeTracker/PersonalTimes.txt");
        GlobalFile = new File("./plugins/TimeTracker/GlobalTimes.txt");
        ConfigFile = new File("./plugins/TimeTracker/TimeConfig.txt");        
        PersonalParams = new LinkedList<>();
        GlobalParams = new LinkedList<>();
        ConfigParams = new HashMap<>();
        loadDirectory();

        DisabledPlayersFile = new File("./plugins/TimeTracker/DisabledPlayers.txt");
        MutedPlayersFile = new File("./plugins/TimeTracker/MutedPlayers.txt");
        DisabledPlayers = new HashMap<>();  
        MutedPlayers = new HashMap<>();        
        initialisePlayers();        
    }

    private void loadDirectory() {
        if (PersonalFile.exists()) {
            readPersonalParams();
            logger.info("Personal times file loaded successfully");
        } else {
            logger.info("Setting up Personal times file");
            File f1 = new File("./plugins/TimeTracker");
            if (!(f1.exists() && f1.isDirectory()))
                new File("./plugins/TimeTracker").mkdir();
            writeDefaultPersonalParams();
            readPersonalParams();
            logger.info("Personal times file initialised successfully");
        }
        if (GlobalFile.exists()) {
            readGlobalParams();
            logger.info("GLobal times file loaded successfully");
        } else {
            logger.info("Setting up Global times file");
            File f2 = new File("./plugins/TimeTracker");
            if (!(f2.exists() && f2.isDirectory()))
                new File("./plugins/TimeTracker").mkdir();
            writeDefaultGlobalParams();
            readGlobalParams();
            logger.info("GLobal times file initialised successfully");
        }
        if (ConfigFile.exists()) {
            readConfigParams();
            logger.info("Config file loaded successfully");
        } else {
            logger.info("Setting up config file");
            File f2 = new File("./plugins/TimeTracker");
            if (!(f2.exists() && f2.isDirectory()))
                new File("./plugins/TimeTracker").mkdir();
            writeDefaultConfigParams();
            readConfigParams();
            logger.info("Config file initialised successfully");
        }
    } 
}