import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class EntryPoint {
    //handle to the window
    private long window;

    public void run() throws NullPointerException{
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        init();
        loop();

        //free window callbacks and destroy window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        //Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // initialize glfw
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // configure glfw
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        //create window
        window = glfwCreateWindow(800, 600, "glfw test", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create GLFW window");

        //setup key callback
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // <--- will be handled in rendering loop
        });

        //get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            //get the window size passed into glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            //get the resolution of primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            //center the window
            assert vidmode != null;
            glfwSetWindowPos(
                    window,
                    (vidmode.width() -  pWidth.get(0)) / 2,
                    (vidmode.height() - pWidth.get(0)) / 2
            );
        } //the stack frame is popped automatically
        // Make OpenGL Context current
        glfwMakeContextCurrent(window);
        //Enable v-sync
        glfwSwapInterval(1);

        //show the window
        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 1.0f, 0.8f);

        while( !glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); //clear the frame buffer
            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new EntryPoint().run();
    }
}
