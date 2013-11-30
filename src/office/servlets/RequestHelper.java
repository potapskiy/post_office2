package office.servlets;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import office.comands.*;

public class RequestHelper {
	
	private static RequestHelper instance = null;
	
	HashMap<String, Command> commands = new HashMap<String, Command>();

	private RequestHelper() {
		//puts commands name
		commands.put("login", new LoginCommand());
//		commands.put("addrecord", new AddRecordCommand());
//		commands.put("showhistory", new ShowHistoryCommand());
//		commands.put("monthlystatistic", new MonthlyStatisticCommand());
//		commands.put("registration", new RegistrationCommand());
	}
	
	public Command getCommand(HttpServletRequest request) {
		//get command from request
		String action = request.getParameter("command");
		//finding command by name
		Command command = commands.get(action);
		if (command == null) {
			command = new NoCommand();
			
		}
		return command;
	}
	
	//creating Singleton RequestHelper object
	public static RequestHelper getInstance() {
		if (instance == null) {
			instance = new RequestHelper();
		}
		return instance;
	}
}
