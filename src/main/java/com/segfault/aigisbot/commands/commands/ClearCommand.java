package com.segfault.aigisbot.commands.commands;

import com.segfault.aigisbot.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;

import java.util.List;

public class ClearCommand implements ServerCommand {

    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {
        if (arguments.length < 2) { // !clear <amount>
            textChannel.sendMessage("\uD83D\uDCA5 Sorry, but this is not the right usage, please use - *!clear <amount>*").queue();
        } else {
            if (arguments.length == 2) {
                try {
                    try {
                        message.delete().queue();
                    } catch (InsufficientPermissionException exception) {
                        textChannel.sendMessage("\uD83D\uDCA5 Sorry, but I don't have enough permissions for this action.").queue();
                    }

                    List<Message> messageList = textChannel.getHistory().retrievePast(Integer.parseInt(arguments[1])).complete();
                    textChannel.deleteMessages(messageList).queue();

                    textChannel.sendMessage("✅ Successfully cleared " + arguments[1] + " message(s) in " + textChannel.getAsMention()).queue();
                } catch (IllegalArgumentException exception) {
                    if (exception.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
                        textChannel.sendMessage("❌ Sorry, but you can't delete more than 100 messages at once.").queue();
                    } else {
                        textChannel.sendMessage("❌ Sorry, but you can't delete messages that are older than 2 weeks.").queue();
                    }
                }
            }
        }
    }
}
