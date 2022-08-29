package engine.entitycomponent;

import lombok.Getter;

import java.util.List;

public abstract class ComponentSuper implements IComponent{
    protected List<? extends ComponentSuper> components;
    @Getter
    protected String name = "";

    public static abstract class ComponentBuilder<T extends ComponentSuper> {
        public abstract  <T extends ComponentSuper> boolean validateComponentObject(T comp);
        public abstract <T extends ComponentSuper> T build();
    }
    public abstract <T extends ComponentSuper> void addComponent();

    public <T extends ComponentSuper> T getComponentByName(String name) {
        for (ComponentSuper component: components) {
            if(component.getName() == name)
                return (T)component;
        }
        return null;
    }
}
