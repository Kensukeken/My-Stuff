import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GeometryDashGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture cubeTexture;
    float cubeX, cubeY;
    float gravity;
    boolean isJumping;

    @Override
    public void create () {
        batch = new SpriteBatch();
        cubeTexture = new Texture("cube.png"); // Replace with the actual cube texture file

        cubeX = 100;
        cubeY = 0;
        gravity = -0.5f;
        isJumping = false;
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update cube position based on input and gravity
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !isJumping) {
            isJumping = true;
            gravity = 10; // Adjust as needed for jump height
        }

        if (isJumping) {
            cubeY += gravity;
            gravity -= 0.5f; // Simulate gravity

            if (cubeY <= 0) {
                cubeY = 0;
                isJumping = false;
            }
        }

        batch.begin();
        batch.draw(cubeTexture, cubeX, cubeY);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        cubeTexture.dispose();
    }
}
