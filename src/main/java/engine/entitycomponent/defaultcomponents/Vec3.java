package engine.entitycomponent.defaultcomponents;

import engine.entitycomponent.ComponentSuper;
import lombok.Getter;

public class Vec3 extends ComponentSuper {
    @Getter
    float x, y, z;

    public Vec3() {
        this.x = this.y = this.z = 0.0f;
    }

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vec3(ComponentBuilder<Vec3> builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
    }

    public static class ComponentBuilder<T extends ComponentSuper> extends ComponentSuper.ComponentBuilder<T> {
        private float x,y,z;
        private String name;

        public ComponentBuilder(String name) {
            x = y = z = 0.0f;
            this.name = name;
        }
        public ComponentBuilder(String name, float x, float y, float z) {
            this.x = x; this.y = y; this.z = z;
            this.name = name;
        }
        public ComponentBuilder(String name, Vec3 v) {
            this.x = v.getX(); this.y = v.getY(); this.z = v.getZ();
            this.name = name;
        }

        public ComponentBuilder() {
            x = y = z = 0.0f;
        }
        public ComponentBuilder(float x, float y, float z) {
            this.x = x; this.y = y; this.z = z;
        }
        public ComponentBuilder(Vec3 v) {
            this.x = v.getX(); this.y = v.getY(); this.z = v.getZ();
        }

        public ComponentBuilder<T> name(String name) {
            this.name = name;
            return this;
        }

        public ComponentBuilder<T> x(float x) {
            this.x = x;
            return this;
        }
        public ComponentBuilder<T> y(float y) {
            this.y = y;
            return this;
        }
        public ComponentBuilder<T> z(float z) {
            this.z = z;
            return this;
        }

        public ComponentBuilder<T> xy(float xy) {
            this.x = this.y = xy;
            return this;
        }

        public ComponentBuilder<T> xz(float xz) {
            this.x = this.z = xz;
            return this;
        }

        public ComponentBuilder<T> yz(float yz) {
            this.z = this.y = yz;
            return this;
        }

        public ComponentBuilder<T> xy(float x, float y) {
            this.x = x; this.y = y;
            return this;
        }

        public ComponentBuilder<T> xz(float x, float z) {
            this.x = x; this.z = z;
            return this;
        }

        public ComponentBuilder<T> yz(float y, float z) {
            this.z = z; this.y = y;
            return this;
        }

        public ComponentBuilder<T> xyz(float xyz) {
            this.z = this.y = this.x = xyz;
            return this;
        }

        public ComponentBuilder<T> xyz(float x, float y, float z) {
            this.x = x; this.y = y; this.z = z;
            return this;
        }

        public ComponentBuilder<T> xyz(Vec3 v) {
            this.x = v.getX(); this.y = v.getY(); this.z = v.getZ();
            return this;
        }

        public ComponentBuilder<T> addVec3(Vec3 v) {
            this.x += v.getX(); this.y += v.getY(); this.z += v.getZ();
            return this;
        }

        @Override
        public boolean validateComponentObject(ComponentSuper comp) {
            return true;
        }

        @Override
        public T build() {
            Vec3 v = new Vec3((Vec3.ComponentBuilder<Vec3>) this);
            if(!validateComponentObject(v) )return null;
            return (T) v;
        }
    }

    @Override
    public <T extends ComponentSuper> void addComponent() {

    }
}
