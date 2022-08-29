package engine.entitycomponent.defaultcomponents;

import engine.entitycomponent.ComponentSuper;

import java.util.List;

public class Sprite extends ComponentSuper {
    String path ="";
    public Sprite(String path, String name) {
        this.path = path;
        this.name = name;
    }
    public Sprite(String path, String name, List<? extends ComponentSuper> components) {
        this.path = path;
        this.components = components;
        this.name = name;
    }
    public Sprite(String path, List<? extends ComponentSuper> components) {
        this.path = path;
        this.components = components;
    }
    public Sprite(String name) {
        this.name = name;
    }
    public Sprite(List<? extends ComponentSuper> components) {
        this.components = components;
    }
    public Sprite(ComponentBuilder<Sprite> builder) {
        this.path = builder.path;
        this.name = builder.name;
        this.components = builder.components;
    }

    public static class ComponentBuilder<T extends ComponentSuper> extends ComponentSuper.ComponentBuilder<T> {
        final String path;
        String name;
        private List<ComponentSuper> components;

        public ComponentBuilder(String path) {
            this.path = path;
        }

        public ComponentBuilder<T> name(String name) {
            this.name = name;
            return this;
        }

        public ComponentBuilder<T> components(List<? extends ComponentSuper> comps) {
            this.components = (List<ComponentSuper>) comps;
            return this;
        }
        public ComponentBuilder<T> addComponents(List<? extends ComponentSuper> comps) {
            this.components.addAll(comps);
            return this;
        }

        public ComponentBuilder<T> addComponent(ComponentSuper comp) {
            this.components.add(comp);
            return this;
        }

        @Override
        public boolean validateComponentObject(ComponentSuper comp) {
            return true;
        }

        @Override
        public T build() {
            Sprite sprite = new Sprite((ComponentBuilder<Sprite>)this);
            if(validateComponentObject(sprite)) return null;
            return (T)sprite;
        }
    }



    @Override
    public <T extends ComponentSuper> void addComponent() {

    }
}
