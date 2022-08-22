package entitycomponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class ComponentSuper implements IComponent{

    List<IComponent> components = new ArrayList<IComponent>();
    public void update() {

    }

    protected <T extends ComponentSuper>void addComponent(T t) {
        this.components.add(t);
    }


}
