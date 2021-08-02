package com.segfault.aigisbot;

import com.segfault.aigisbot.config.ConfigFile;
import com.segfault.aigisbot.config.ConfigValues;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {

    public static void main(String[] args) {

        ConfigFile.loadConfig();
        ConfigValues.loadValues();

        JDABuilder jdaBuilder = JDABuilder.createDefault(ConfigValues.BOT_TOKEN);

        jdaBuilder.setStatus(OnlineStatus.IDLE);
        jdaBuilder.setActivity(Activity.playing("SMT and Persona"));

        jdaBuilder.addEventListeners(new Main());

        try {
            jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] arguments = event.getMessage().getContentRaw().split(" ");
        if (arguments[0].equals("!setrole")) { // !setrole <user> <role>
            Member member = event.getMessage().getMentionedMembers().get(0);
            if (member != null) {
                Role role = event.getGuild().getRoleById(arguments[2]);
                if (role != null) {
                    event.getGuild().addRoleToMember(member, role).queue();
                    event.getChannel().sendMessage("Applied the role: " + role.getAsMention() + " to the user: " + member.getUser()).queue();
                }
            }
        }
    }
}
