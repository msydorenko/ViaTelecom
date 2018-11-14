package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Holder for all commands
 *
 * @author M.Sydorenko
 *
 */
public class CommandFactory {
    private static CommandFactory instance;
    private static final Logger log = Logger.getLogger(CommandFactory.class);
    private static Map<String, Command> commandList = new HashMap<>();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    static {
        commandList.put("login", new LoginCommand());
        commandList.put("main", new MainCommand());
        commandList.put("addTariff", new AddTariffCommand());
        commandList.put("tariffList", new TariffListCommand());
        commandList.put("sort", new SortCommand());
        commandList.put("editTariff", new EditTariffCommand());
        commandList.put("deleteTariff", new DeleteTariffCommand());
        commandList.put("openEditTariffPage", new OpenEditTariffPageCommand());
        commandList.put("userSubscribeTariff", new UserSubscribeTariffCommand());
        commandList.put("userUnsubscribeTariff", new UserUnsubscribeTariffCommand());
        commandList.put("locale", new I18NCommand());
        commandList.put("download", new DownloadCommand());
        commandList.put("recharge", new RechargeCommand());
        commandList.put("usersList", new UsersListCommand());
        commandList.put("changeUserStatus", new ChangeUserStatusCommand());


        commandList.put("logout", new LogoutCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String nameCommand = request.getParameter("command");
        Command command = null;
        if (nameCommand != null || !nameCommand.equals("")) {
            command = commandList.get(nameCommand);
        }
        return command;
    }
}
