package entitycomponent.defaultcomponents;

import entitycomponent.ComponentSuper;
import lombok.Getter;

class Vec3 extends ComponentSuper {
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
}
