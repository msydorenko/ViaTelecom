package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Holder for all commands
 *
 * @author M.Sydorenko
 */
public class CommandFactory {
    private static final Logger LOG = Logger.getLogger(CommandFactory.class);
    private static Map<String, Command> commandList = new HashMap<>();

    public CommandFactory(Map<String, Command> commandList) {
        this.commandList = commandList;
    }

    /**
     * Return command object with the given request.
     *
     * @param request
     * @return Command object
     */
    public Command getCommand(HttpServletRequest request) {
        String nameCommand = request.getParameter("command");
        if (nameCommand == null || !commandList.containsKey(nameCommand)) {
            LOG.trace("Command not found: " + nameCommand);
            return commandList.get("noCommand");
        }
        return commandList.get(nameCommand);
    }
}
