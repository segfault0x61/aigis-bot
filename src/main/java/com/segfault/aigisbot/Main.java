package com.segfault.aigisbot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    private static JDABuilder jdaBuilder;

    public static void main(String[] args) {

        jdaBuilder = JDABuilder.createDefault("NzgzMzQ0MDU2MzEwMzAwNjkz.X8ZYAA._u44QPiHWHtyFLBTs9G6t5hhpC8");

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
        if (arguments[0].equals("!sendembed")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setTitle("Hey I am a embed for discord");
            embedBuilder.setColor(0x90c695);
            embedBuilder.setDescription("Hello you have successfully created your first embed message!\nThis is a test of new line!");
            embedBuilder.setFooter("This is just a simple footer.", event.getAuthor().getAvatarUrl());
            embedBuilder.setThumbnail(event.getAuthor().getAvatarUrl());
            embedBuilder.setImage(event.getAuthor().getAvatarUrl());

            event.getChannel().sendMessage(embedBuilder.build()).queue();
        }
    }

    @Override
    public void onGuildUpdateName(@NotNull GuildUpdateNameEvent event) {
        TextChannel textChannel = event.getGuild().getSystemChannel();
        if (textChannel != null) {
            String newName = event.getNewName();
            String oldName = event.getOldName();
            if (newName.equals("Test Discord")) {
                textChannel.sendMessage("The discord server was renamed from **" + oldName + "** to **" + newName + "**!").queue();
            }
        }
    }
}
