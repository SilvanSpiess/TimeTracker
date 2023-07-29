package me.SIL.TimeTracker.config;
import me.SIL.TimeTracker.TimeTracker;
import me.SIL.TimeTracker.config.TimeConfig;

import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;
import java.util.logging.Logger;

public class TimeConfig {    
    private final TimeTracker plugin;
    private final Logger logger;
    private File PersonalFile, GlobalFile;
    private LinkedList<Integer> PersonalParams;
    private LinkedList<Integer> GlobalParams;
    private LinkedList<Integer> defaultPersonalParams;
    private LinkedList<Integer> defaultGlobalParams;

    public LinkedList<Integer> getPersonalParams() {return PersonalParams;}
    public void setPersonalParams(LinkedList<Integer> personalParams) {this.PersonalParams = personalParams;}
    public LinkedList<Integer> getGlobalParams() {return GlobalParams;}
    public void setGlobalParams(LinkedList<Integer> globalParams) {this.GlobalParams = globalParams;}

    private void writeDefaultPersonalParamFiles() {
        logger.info("Writing personal default files");
        //Fill the default Personal Set
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
            for(Integer i: defaultPersonalParams) {
                    myWriter1.write(i.toString() + "\n");
            } myWriter1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    private void writeDefaultGlobalParamFiles() {
        logger.info("Writing global default files");
        //Fill the default Personal Set
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
            FileWriter myWriter2 = new FileWriter("./plugins/TimeTracker/GlobalTimes.txt");
            for(Integer j: defaultGlobalParams) {
                myWriter2.write(j.toString() + "\n");
            } myWriter2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    private void readPersonalParamFiles() {
        logger.info("Loading personal data from Files");
        PersonalParams = new LinkedList<>();        
        Scanner myReader1;
        try {
            myReader1 = new Scanner(PersonalFile);
            while (myReader1.hasNextLine()) {                
                PersonalParams.add(Integer.parseInt(myReader1.nextLine()));
            } myReader1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void readGlobalParamFiles() {
        logger.info("Loading global data from Files");
        GlobalParams = new LinkedList<>();    
        Scanner myReader2;
        try {
            myReader2 = new Scanner(GlobalFile);
            while (myReader2.hasNextLine()) {                
                GlobalParams.add(Integer.parseInt(myReader2.nextLine()));
            } myReader2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TimeConfig(TimeTracker plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
        PersonalFile = new File("./plugins/TimeTracker/PersonalTimes.txt");
        GlobalFile = new File("./plugins/TimeTracker/GlobalTimes.txt");
        if(PersonalFile.exists()) {
           readPersonalParamFiles(); 
        }
        else {
            logger.info("Setting up directory");
            File f1 = new File("./plugins/TimeTracker");
            if (!(f1.exists() && f1.isDirectory()))
                new File("./plugins/TimeTracker").mkdir();
            writeDefaultPersonalParamFiles();
        }
        if(GlobalFile.exists()) {
            readGlobalParamFiles();
        }
        else {
            logger.info("Setting up directory");
            File f2 = new File("./plugins/TimeTracker");
            if (!(f2.exists() && f2.isDirectory()))
                new File("./plugins/TimeTracker").mkdir();
            writeDefaultGlobalParamFiles();
        }   
    }
}