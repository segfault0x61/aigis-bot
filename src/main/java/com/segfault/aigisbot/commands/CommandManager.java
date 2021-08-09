package com.segfault.aigisbot.commands;

import com.segfault.aigisbot.commands.commands.*;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandManager extends ListenerAdapter {

    private final HelpCommand helpCommand;
    private final ClearCommand clearCommand;
    private final KickCommand kickCommand;
    private final BanCommand banCommand;
    private final UnBanCommand unBanCommand;

    public CommandManager() {
        this.helpCommand = new HelpCommand();
        this.clearCommand = new ClearCommand();
        this.kickCommand = new KickCommand();
        this.banCommand = new BanCommand();
        this.unBanCommand = new UnBanCommand();
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (!event.getMember().getUser().isBot()) {
            String[] arguments = event.getMessage().getContentRaw().split(" ");
            Guild guild = event.getGuild();
            Member member = event.getMember();
            TextChannel textChannel = event.getChannel();
            Message message = event.getMessage();

            switch (arguments[0]) {
                case "!help":
                    this.helpCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;
                case "!clear":
                    this.clearCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;
                case "!kick":
                    this.kickCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;
                case "!ban":
                    this.banCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;
                case "!unban":
                    this.unBanCommand.performCommand(arguments, guild, member, textChannel, message);
                    break;
            }
        }
    }
}
