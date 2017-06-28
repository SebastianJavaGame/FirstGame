package com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;

import Screen.BaseMap;

/**
 * Created by Sebastian on 2017-06-14.
 */

public class Hero3D {
    public PerspectiveCamera cam;
    public ModelBatch modelBatch;
    public Model model;
    public ModelInstance instance;
    private Environment environment;
    private AnimationController controller;

    private Polygon[] rotatePolygon = new Polygon[20];
    private Polygon touchPlace;

    public void create () {
        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        UBJsonReader json = new UBJsonReader();
        G3dModelLoader modelLoader = new G3dModelLoader(json);
        model = modelLoader.loadModel(Gdx.files.getFileHandle("hero3d/knight.g3db", Files.FileType.Internal));
        instance = new ModelInstance(model);
        instance.transform.scale(0.005f, 0.005f, 0.005f);
        instance.transform.translate(0, -270, 0);

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        controller = new AnimationController(instance);
        controller.setAnimation("Armature|Idle", 1);

        initializeTabPolygon();
    }

    public void render () {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        controller.update(Gdx.graphics.getDeltaTime());

        modelBatch.begin(cam);
        modelBatch.render(instance, environment);
        modelBatch.end();
    }

    public float moveRotate(float screenX, float screenY){

        touchPlace = new Polygon(new float[]{screenX -1, screenY -1, screenX -1, screenY +1, screenX +1, screenY +1, screenX +1, screenY -1});
        for(int i = 0; i < 20; i++){
            if(Intersector.overlapConvexPolygons(touchPlace, rotatePolygon[i])) {
                instance.transform.setToRotation(Vector3.Y, (i -4) * -18);
                instance.transform.scale(0.005f, 0.005f, 0.005f);
                instance.transform.translate(0, -270, 0);
                return (i -4) * -18;
            }
        }
        return 0;
    }

    private void initializeTabPolygon() {
        rotatePolygon[0] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 440, 40, 480});
        rotatePolygon[1] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 40, 480, 120, 480});
        rotatePolygon[2] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 120, 480, 200, 480});
        rotatePolygon[3] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 200, 480, 280, 480});
        rotatePolygon[4] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 280, 480, 320, 440});
        rotatePolygon[5] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 440, 320, 360});
        rotatePolygon[6] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 360, 320, 280});
        rotatePolygon[7] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 280, 320, 200});
        rotatePolygon[8] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 200, 320, 120});
        rotatePolygon[9] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 120, 320, 40});
        rotatePolygon[10] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 344, 10, 295, -30});
        rotatePolygon[11] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 280, 0, 200, 0});
        rotatePolygon[12] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 200, 0, 120, 0});
        rotatePolygon[13] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 120, 0, 40, 0});
        rotatePolygon[14] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 40, 0, -32, 0});
        rotatePolygon[15] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 40, 0, 120});
        rotatePolygon[16] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 120, 0, 200});
        rotatePolygon[17] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 200, 0, 280});
        rotatePolygon[18] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 280, 0, 360});
        rotatePolygon[19] = new Polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 360, 0, 440});
    }

    public void setStopAnimation(){
        controller.setAnimation("Armature|Idle", 1);
    }

    public void setPlayAnimation(){
        controller.setAnimation("Walk", -1);
    }

    public void dispose () {
        modelBatch.dispose();
        model.dispose();
    }
}
