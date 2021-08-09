package com.segfault.aigisbot.commands.commands;

import com.segfault.aigisbot.commands.types.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class KickCommand implements ServerCommand {

    @Override
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message) {
        if (arguments.length == 3) { // !kick <user> <reason>
            Member target = message.getMentionedMembers().get(0);
            if (target != null) {
                String reason = arguments[2];
                if (reason != null) {
                    if (member.hasPermission(Permission.KICK_MEMBERS)) {
                        textChannel.sendMessage("The user - " + target.getUser().getName() + " was kicked for - " + reason).queue();
                        target.kick(reason).queue();
                    }
                }
            }
        } else {
            textChannel.sendMessage("The right usage is - !kick <user> <reason>").queue();
        }
    }
}
