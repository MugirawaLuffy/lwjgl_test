package engine;

import engine.annotations.GameRoot;
import engine.annotations.OnFrameUpdate;
import engine.annotations.OnInitializeUpdate;
import engine.annotations.OnStopUpdate;
import engine.exceptions.AnnotationException;
import lombok.Getter;
import lombok.Setter;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.Set;
import java.util.Timer;

public class GameWrapper {
    boolean isRunning = false;

    @Getter
    @Setter
    int fps = 60;

    Reflections reflections;
    private BaseGame game;

    private void init() throws AnnotationException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        reflections = new Reflections("TechnoCraft");

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(GameRoot.class);

        if(annotated.size() != 1) {
            throw new AnnotationException("Only one class can be annotated with \"@GameRoot\"");
        }
        for (Class<?> root : annotated) {
            Constructor<?> gameConstructor = root.getConstructor();
            game = (BaseGame)gameConstructor.newInstance();
        }
        game.init();
        this.runAllAnnotatedWith(OnInitializeUpdate.class);
    }

    private void stop() throws InvocationTargetException, IllegalAccessException {
        this.runAllAnnotatedWith(OnStopUpdate.class);
        game.stop();
    }

    private void start() {
        game.start();
        this.isRunning = true;
    }

    public void run() {
        try {
            Stopwatch watch = new Stopwatch();
            init();
            start();
            while(this.isRunning) {
                watch.start();
                //GameLogic
                this.runAllAnnotatedWith(OnFrameUpdate.class);
                //GameLocic ends here
                watch.stop();
                this.idle(watch.getElapsedMilliseconds());
            }

        }catch(AnnotationException e) {

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void idle(long millis) throws InterruptedException {
        Thread.sleep((1000 / fps) - millis);
    }

    private void runAllAnnotatedWith(Class<? extends Annotation> annotation) throws InvocationTargetException, IllegalAccessException {
        if(reflections == null)
            return;

        Set<Method> methods = this.reflections.getMethodsAnnotatedWith(annotation);
        for (Method m : methods) {
            // for simplicity, invokes methods as static without parameters
            m.invoke(null);
        }
    }
}
