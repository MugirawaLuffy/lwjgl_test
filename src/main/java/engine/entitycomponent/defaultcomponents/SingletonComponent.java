package engine.entitycomponent.defaultcomponents;

import engine.entitycomponent.ComponentSuper;

public class SingletonComponent<T extends ComponentSuper> {
    private SingletonComponent() {}

    private static  class  SingletonComponentHelper {
        public  static  final SingletonComponent INSTANCE = new SingletonComponent();
    }

    public static SingletonComponent getInstance() {
        return SingletonComponentHelper.INSTANCE;
    }
}
