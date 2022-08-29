package engine.entitycomponent.defaultcomponents;

import engine.entitycomponent.ComponentSuper;
import lombok.Getter;

public class Vec2 extends ComponentSuper {
    @Getter
    float x,y;

    public Vec2() {
        this.x = this.y = 0.0f;
    }

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vec2(Vec2.ComponentBuilder<Vec2> builder) {
        this.x = builder.x;
        this.y = builder.y;
    }

    public static class ComponentBuilder<T extends ComponentSuper> extends ComponentSuper.ComponentBuilder<T> {
        private float x,y;
        private String name ="";

        public ComponentBuilder() {
            x = y = 0.0f;
        }

        public ComponentBuilder(String name) {
            this.name = name;
            x = y = 0.0f;
        }
        public ComponentBuilder(float x, float y) {
            this.x = x; this.y = y;
        }

        public ComponentBuilder(String name, float x, float y) {
            this.x = x; this.y = y;
            this.name = name;
        }
        public ComponentBuilder(String name, Vec2 v) {
            this.x = v.getX(); this.y = v.getY();
            this.name = name;
        }
        public ComponentBuilder(Vec2 v) {
            this.x = v.getX(); this.y = v.getY();
        }

        public Vec2.ComponentBuilder<T> x(float x) {
            this.x = x;
            return this;
        }
        public Vec2.ComponentBuilder<T> y(float y) {
            this.y = y;
            return this;
        }

        public Vec2.ComponentBuilder<T> xy(float xy) {
            this.x = this.y = xy;
            return this;
        }

        public Vec2.ComponentBuilder<T> addVector(Vec2 v) {
            this.x += v.x; this.y += v.y;
            return this;
        }

        public Vec2.ComponentBuilder<T> xy(float x, float y) {
            this.x = x; this.y = y;
            return this;
        }

        public Vec2.ComponentBuilder<T> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public boolean validateComponentObject(ComponentSuper comp) {
            return true;
        }


        @Override
        public T build() {
            Vec2 v = new Vec2((Vec2.ComponentBuilder<Vec2>) this);
            if(!validateComponentObject(v)) return null;
            return (T) v;
        }
    }


    @Override
    public <T extends ComponentSuper> void addComponent() {

    }
}
