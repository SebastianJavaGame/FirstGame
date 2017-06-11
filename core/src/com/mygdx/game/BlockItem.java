package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Sebastian on 2017-06-11.
 */

public class BlockItem{
    private Image image;

    public BlockItem(Image image) {
        this.image = image;
        image.addListener(new InputListener() {
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button) {
                System.out.println("Click block item");
                return false;
            }
        });
    }

    /**
     * Getters and Setters
     */
    public void setBlock(float posX, float posY, float sizeX, float sizeY){
        image.setPosition(posX, posY);
        image.setSize(sizeX, sizeY);
    }

    public void setImage(Image image){
        this.image = image;
    }

    public void setListener(InputListener inputListener){
        image.addListener(inputListener);
    }

    public Image getImage(){
        return image;
    }

    //TODO info about item
}
