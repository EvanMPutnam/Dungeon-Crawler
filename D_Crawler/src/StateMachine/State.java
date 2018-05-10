package StateMachine;

import asciiPanel.AsciiPanel;

/**
 * Created by evanputnam on 8/19/17.
 */
public interface State {

    static final int LEFT_KEY = 37;
    static final int RIGHT_KEY = 39;
    static final int UP_KEY = 38;
    static final int DOWN_KEY = 40;
    static final int ENTER_KEY = 10;
    static final int ESCAPE_KEY = 27;
    static final int I_KEY = 73;

    void processCommand(int com);
    void updateScreen(AsciiPanel panel);
    void updateScreen();

}
