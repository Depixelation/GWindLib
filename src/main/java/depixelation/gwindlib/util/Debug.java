package depixelation.gwindlib.util;

import depixelation.gwindlib.GlobalWindLib;
import depixelation.gwindlib.config.Constants;

public class Debug {
    public static void send(String msg){
        if(Constants.DEBUG) GlobalWindLib.LOGGER.info(msg);
    }
}
