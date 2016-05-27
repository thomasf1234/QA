
package qa;

import com.sun.javafx.stage.StageHelper;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeoutException;
import javafx.stage.Stage;
import qa.utils.StopWatch;


public class QATester extends Robot {
    
    private static int WAIT = 5;
    
    public QATester() throws AWTException {
        super();
    }
    
    public Stage getPrimaryStage() {
        return StageHelper.getStages().get(0);
    }
    
    public void open(String applicationClassName) throws ClassNotFoundException, TimeoutException, InterruptedException {
        Class applicationClass = Class.forName(applicationClassName);
        RunnableApplication runnableApplication = new RunnableApplication(applicationClassName, applicationClass);
        runnableApplication.start();
        
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        while (stopWatch.getElapsedTimeSeconds() < WAIT) {
            if (StageHelper.getStages().size() > 0) {
                return;
            }
            
            Thread.sleep(100); //avoid overwhelming CPU
        }
        
        throw new TimeoutException("timed out waiting for application to launch");
    }
}
