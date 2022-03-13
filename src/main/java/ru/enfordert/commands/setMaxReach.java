package ru.enfordert.commands;

import ru.enfordert.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class setMaxReach implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length < 1) {
            commandSender.sendMessage("§eSet right max reach value");
            return true;
        }
         else {
             if(commandSender.isOp()) {
                 Double multiplier = Double.parseDouble(strings[0]);
                 commandSender.sendMessage("§ereach value is: §6" + multiplier);
                 Main.reachMultiplier = multiplier;
             } else {
                 commandSender.sendMessage("§cYou don't have permissions to use this command");
             }
        }
        return true;
    }
}
