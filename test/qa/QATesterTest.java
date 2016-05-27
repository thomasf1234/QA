
package qa;

import java.awt.AWTException;
import java.util.concurrent.TimeoutException;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QATesterTest {
    
    private QATester qaTester;
    
    public QATesterTest() {
    }
    
    @Before
    public void setUp() throws InterruptedException, AWTException, ClassNotFoundException, TimeoutException {
       this.qaTester = new QATester();
       this.qaTester.open("qa.DemoFX");
    }

    @Test
    public void testGetPrimaryStage() throws InterruptedException {
        Stage primaryStage = this.qaTester.getPrimaryStage();
        assertEquals("JavaFX Demo", primaryStage.getTitle());
    }
    
}
