
package qa;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunnableApplication implements Runnable {

    private Thread thread;
    private final String threadName;
    private final Class applicationClass;

    RunnableApplication(String name, Class applicationClass) {
        this.threadName = name;
        this.applicationClass = applicationClass;
    }

    @Override
    public void run() {
        try {
            Class[] paramTypes = new Class[1];
            paramTypes[0] = String[].class;
            Method method = this.applicationClass.getDeclaredMethod("main", paramTypes);
            method.invoke(null, (Object) new String[0]);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(RunnableApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        if (this.thread == null) {
            this.thread = new Thread(this, this.threadName);
            this.thread.start();
        }
    }

    public Thread getThread() {
        return this.thread;
    }
}
