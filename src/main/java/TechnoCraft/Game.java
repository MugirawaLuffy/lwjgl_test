package TechnoCraft;

import engine.BaseGame;
import engine.annotations.GameRoot;
import engine.annotations.OnInitializeUpdate;
import engine.entitycomponent.defaultcomponents.Empty;
import engine.entitycomponent.defaultcomponents.Script;
import engine.entitycomponent.defaultcomponents.Sprite;
import engine.entitycomponent.defaultcomponents.Vec3;

@GameRoot(debug = false, activateLog = true)
public class Game extends BaseGame {
    Empty rootGameComponent;

    @Override
    @OnInitializeUpdate
    public void init() {
        Sprite logoSprite = (Sprite) new Sprite.ComponentBuilder<Sprite>("textures/logo.png")
                .addComponent(new Empty.ComponentBuilder<Empty>()
                        .addComponent((Vec3) new Vec3.ComponentBuilder<Vec3>()
                                .xyz(1.0f, 3.0f, 2.0f)
                                .name("position")
                                .build()
                        )
                        .addComponent((Vec3) new Vec3.ComponentBuilder<Vec3>()
                                .xyz(90.0f, 0.0f, 0.0f)
                                .name("rotation")
                                .build()
                        )
                        .name("transform")
                        .addComponent(new Script())
                        .build()
                )
                .name("logo-sprite")
                .addComponent(new Script())
                .build();

        rootGameComponent = (Empty) new Empty.ComponentBuilder<Empty>("RootComponent")
                .addComponent(logoSprite)
                .addComponent(new Script())
                .build();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

}
