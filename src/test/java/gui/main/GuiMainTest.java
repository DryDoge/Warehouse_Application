package gui.main;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class GuiMainTest {

    @Test
    public void getLogr() {
        Logger newLogger = GuiMain.getLogr();
        Logger log =   Logger.getLogger(GuiMain.class.getName());
        assertEquals(newLogger, log);
    }

    @Test
    public void initializedLogr(){
        Logger newLogger =  GuiMain.getLogr();
        assertNotNull(newLogger);
    }

}