package com.cocos.basewebview.mainprocess;

import android.content.Context;
import android.util.Log;

import com.cocos.basewebview.CommandCallBack;
import com.cocos.basewebview.command.Command;
import com.cocos.basewebview.utils.AidlError;
import com.cocos.basewebview.utils.WebConstants;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;


public class CommandsManager {
    private static final String TAG = "CommandsManager";
    private static CommandsManager instance;

    private HashMap<String, Command> commands;

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public CommandsManager() {
        commands = new HashMap<>();
        ServiceLoader<Command> load = ServiceLoader.load(Command.class);
        Iterator<Command> iterator = load.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            Log.i("command", "command  name = " + command.name() + "   " + command);
            if (!commands.containsKey(command.name())) {
                commands.put(command.name(), command);
                Log.i("command", "commands size " + commands.size() );
            }
        }
    }

    public void registerCommand(Command command) {
        commands.put(command.name(), command);
    }

    public static CommandsManager getInstance() {
        if (instance == null) {
            synchronized (CommandsManager.class) {
                instance = new CommandsManager();
            }
        }
        Log.d(TAG, instance + "");
        return instance;
    }

    /**
     * 非UI Command 的执行
     */
    public void execMainProcessCommand(Context context, String action, Map params, CommandCallBack resultBack) {
        if (getCommands().get(action) != null) {
            getCommands().get(action).exec(context, params, resultBack);
        } else {
            AidlError aidlError = new AidlError(WebConstants.ERRORCODE.NO_METHOD, WebConstants.ERRORMESSAGE.NO_METHOD);
            resultBack.onResult(WebConstants.FAILED, action, aidlError);
        }
    }

    /**
     * CommandsManager handled by Webview itself, these command does not require aidl.
     */
    public void execWebViewProcessCommand(Context context, String action, Map params, CommandCallBack resultBack) {
        if (getCommands().get(action) != null) {
            getCommands().get(action).exec(context, params, resultBack);
        }
    }

    public boolean isWebviewProcessCommand(String action) {
        return getCommands().get(action) != null;
    }
}
