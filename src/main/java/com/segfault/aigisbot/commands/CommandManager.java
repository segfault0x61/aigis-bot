package com.segfault.aigisbot.commands;

import com.segfault.aigisbot.commands.commands.ClearCommand;
import com.segfault.aigisbot.commands.commands.HelpCommand;
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

    public CommandManager() {
        this.helpCommand = new HelpCommand();
        this.clearCommand = new ClearCommand();
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
            }
        }
    }
}
