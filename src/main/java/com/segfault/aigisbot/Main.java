package com.segfault.aigisbot;

import com.segfault.aigisbot.commands.CommandManager;
import com.segfault.aigisbot.config.ConfigFile;
import com.segfault.aigisbot.config.ConfigValues;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.Timer;

public class Main {

    private static JDA jda;
    private static JDABuilder jdaBuilder;

    public static void main(String[] args) {

        ConfigFile.loadConfig();
        ConfigValues.loadValues();

        jdaBuilder = JDABuilder.createDefault(ConfigValues.BOT_TOKEN);

        jdaBuilder.setStatus(OnlineStatus.IDLE);
        jdaBuilder.setActivity(Activity.playing("SMT and Persona"));

        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        setDescription();
        registerCommands();
    }

    private static void setDescription() {
        Description description = new Description();
        Timer timer = new Timer();
        timer.schedule(description, 0, 5000);
    }

    private static void registerCommands() {
        CommandManager commandManager = new CommandManager();
        jda.addEventListener(commandManager);
    }

    public static JDA getJda() {
        return jda;
    }
}
