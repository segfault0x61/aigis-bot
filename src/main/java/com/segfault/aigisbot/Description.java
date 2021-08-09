package com.segfault.aigisbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;

import java.util.TimerTask;

public class Description extends TimerTask {

    private int count = 0;
    private final JDA jda = Main.getJda();
    private final String[] messages = {"SMT and Persona", "Watching Anime", "Working on Social Links"};

    @Override
    public void run() {
        if (jda != null) {
            jda.getPresence().setActivity(Activity.playing(messages[count]));
            count = (count + 1) % messages.length;
        }
    }
}
