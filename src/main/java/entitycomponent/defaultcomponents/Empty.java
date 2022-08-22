package entitycomponent.defaultcomponents;

import entitycomponent.ComponentSuper;

public class Empty extends ComponentSuper {

    public Empty() {
        this.addComponent(new Vec3());
    }
}
