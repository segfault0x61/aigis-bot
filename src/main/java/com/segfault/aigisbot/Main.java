package com.segfault.aigisbot;

import com.segfault.aigisbot.config.ConfigFile;
import com.segfault.aigisbot.config.ConfigValues;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.Timer;

public class Main extends ListenerAdapter {

    private static JDA jda;
    private static JDABuilder jdaBuilder;

    public static void main(String[] args) {

        ConfigFile.loadConfig();
        ConfigValues.loadValues();

        jdaBuilder = JDABuilder.createDefault(ConfigValues.BOT_TOKEN);

        jdaBuilder.setStatus(OnlineStatus.IDLE);
        jdaBuilder.setActivity(Activity.playing("SMT and Persona"));

        jdaBuilder.addEventListeners(new Main());
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        setDescription();
    }

    private static void setDescription() {
        Description description = new Description();
        Timer timer = new Timer();
        timer.schedule(description, 0, 5000);
    }

    public static JDA getJda() {
        return jda;
    }
}
