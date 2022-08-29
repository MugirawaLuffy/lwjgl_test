package engine;

import lombok.extern.log4j.Log4j;

@Log4j
public abstract class BaseGame {
    public abstract void init();
    public abstract void start();
    public abstract void stop();


}
