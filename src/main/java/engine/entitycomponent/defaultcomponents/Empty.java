package engine.entitycomponent.defaultcomponents;

import engine.entitycomponent.ComponentSuper;
import lombok.Setter;

import java.util.List;

public class Empty extends ComponentSuper {
    public Empty(ComponentBuilder<Empty> builder) {
        this.components = builder.components;
        this.name = builder.name;
    }
    public Empty(String name) {
        components = null;
        this.name = name;
    }


    public static class ComponentBuilder<T extends ComponentSuper> extends ComponentSuper.ComponentBuilder<T> {
        private List<ComponentSuper> components;
        private String name = "";

        public ComponentBuilder<T> components(List<ComponentSuper> components) {
            this.components = components;
            return this;
        }

        public ComponentBuilder<T> name(String name) {
            this.name = name;
            return this;
        }

        public ComponentBuilder<T> addComponent(ComponentSuper component) {
            this.components.add(component);
            return this;
        }

        public ComponentBuilder(String name) {
            this.name = name;
            components = null;
        }
        public ComponentBuilder(String name, List<ComponentSuper> components) {
            this.components = components;
            this.name = name;
        }

        public ComponentBuilder() {
            components = null;
        }
        public ComponentBuilder(List<ComponentSuper> components) {
            this.components = components;
        }

        @Override
        public boolean validateComponentObject(ComponentSuper comp) {
            return true;
        }

        @Override
        public T build() {
            Empty empty = new Empty((ComponentBuilder<Empty>) this);
            if(!validateComponentObject(empty)) return null;
            return (T) empty;
        }
    }


    @Override
    public <T extends ComponentSuper> void addComponent() {

    }
}
