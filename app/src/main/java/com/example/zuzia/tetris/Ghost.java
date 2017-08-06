package com.example.zuzia.tetris;

import android.view.View;
import android.widget.Button;

public class Ghost {

    private Tetris tetris;

    private Button blockGhostMain;//duchy
    private Button blockGhost1;
    private Button blockGhost2;
    private Button blockGhost3;

    Ghost(Tetris tetris)
    {
        this.tetris=tetris;
        View view = tetris.getView();
        blockGhostMain =(Button)view.findViewById(R.id.blockGhostMain);
        blockGhost1 =(Button)view.findViewById(R.id.blockGhost1);
        blockGhost2 =(Button)view.findViewById(R.id.blockGhost2);
        blockGhost3 =(Button)view.findViewById(R.id.blockGhost3);
    }

    public boolean fallGhostBlocks()
    {
        if(tetris.fallBlock(blockGhostMain,1) && tetris.fallBlock(blockGhost1,1) && tetris.fallBlock(blockGhost2,1) && tetris.fallBlock(blockGhost3,1))
            return true;
        return false;
    }

    private void ghostBlockMove(Button blockMain, Button block1, Button block2, Button block3)
    {
        blockGhostMain.setX(blockMain.getX());
        blockGhostMain.setY(blockMain.getY());
        blockGhost1.setX(block1.getX());
        blockGhost1.setY(block1.getY());
        blockGhost2.setX(block2.getX());
        blockGhost2.setY(block2.getY());
        blockGhost3.setX(block3.getX());
        blockGhost3.setY(block3.getY());

    }

    public void ghostBlockMoveHorizontal(Button blockMain, Button block1, Button block2, Button block3, int initialBlockDiff)
    {
        ghostBlockMove(blockMain,block1,block2,block3);
        while(fallGhostBlocks())
        {
            blockGhostMain.setY(blockGhostMain.getY()+initialBlockDiff);
            blockGhost1.setY(blockGhost1.getY()+initialBlockDiff);
            blockGhost2.setY(blockGhost2.getY()+initialBlockDiff);
            blockGhost3.setY(blockGhost3.getY()+initialBlockDiff);
        }
    }

    public void ghostBlocksInVisible()
    {
        blockGhostMain.setVisibility(blockGhostMain.INVISIBLE);
        blockGhost1.setVisibility(blockGhostMain.INVISIBLE);
        blockGhost2.setVisibility(blockGhostMain.INVISIBLE);
        blockGhost3.setVisibility(blockGhostMain.INVISIBLE);
    }

    public void ghostBlocksVisible()
    {
        blockGhostMain.setVisibility(blockGhostMain.VISIBLE);
        blockGhost1.setVisibility(blockGhostMain.VISIBLE);
        blockGhost2.setVisibility(blockGhostMain.VISIBLE);
        blockGhost3.setVisibility(blockGhostMain.VISIBLE);
    }

    public Button getBlockGhostMain()
    {
        return blockGhostMain;
    }

    public Button getBlockGhost1()
    {
        return blockGhost1;
    }

    public Button getBlockGhost2()
    {
        return blockGhost2;
    }

    public Button getBlockGhost3()
    {
        return blockGhost3;
    }
}
