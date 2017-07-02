package com.example.zuzia.tetris;


import android.graphics.RectF;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Tetris extends Fragment {
    private Button blockMain;
    private Button block1;
    private Button block2;
    private Button block3;
    private int initialBlockDiff; //początkowa wartość potrzebna do ustawienia figury
    private Drawer drawer;
    public long speed;//publiczne używane są w Mainactivity
    public View view;
    public Figure figure;
    public TextView textScore;//nie są we fragmancie, są w MainActivity
    public TextView textLvl;
    public RelativeLayout layoutMainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tetris, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        view=getView();
        drawer = (Drawer) view.findViewById(R.id.drawer);
        blockMain= (Button)view.findViewById(R.id.blockMain);
        block1= (Button)view.findViewById(R.id.block1);
        block2= (Button)view.findViewById(R.id.block2);
        block3= (Button)view.findViewById(R.id.block3);
        figure = new Figure(blockMain,block1,block2,block3);
        initialBlockDiff=37;
        speed=1000;
        blockMain.setX(5*initialBlockDiff);
        blockMain.setY(0);
        figure.randomNumber();
        figure.moveFigure(initialBlockDiff);
    }


    public void moveFigureVertical(MotionEvent e1, MotionEvent e2)
    {
         //przesuwanie w osi X;
        if(e2.getX()-e1.getX()>initialBlockDiff && rightBlocks() && fallBlocks())
        {
            moveDistance(initialBlockDiff);
            e1.setLocation(e2.getX(),e2.getY());
        }
        if(e2.getX()-e1.getX()<-initialBlockDiff && leftBlocks() && fallBlocks())
        {
            moveDistance(-initialBlockDiff);
            e1.setLocation(e2.getX(),e2.getY());
        }
    }

    private void moveDistance(int distance)
    {
        //ustalenie dystansu poziomo
        blockMain.setX(blockMain.getX()+distance);
        block1.setX(block1.getX()+distance);
        block2.setX(block2.getX()+distance);
        block3.setX(block3.getX()+distance);
    }

    public boolean rightBlocks()
    {
        //czy może być po prawej
        if(rightBlock(blockMain,1) && rightBlock(block1,1) && rightBlock(block2,1) && rightBlock(block3,1))
            return true;
        return false;
    }

    private boolean rightBlock(Button block, int character)
    {
        if((drawer.isRightDrawBlock(block) || block.getX()+initialBlockDiff==initialBlockDiff*10) && character==1)
            return false;
        if((drawer.isRightDrawBlock(block) || block.getX()+initialBlockDiff>initialBlockDiff*10) && character==2)
            return false;
        return true;
    }

    public boolean leftBlocks()
    {
        if(leftBlock(blockMain,1) && leftBlock(block1,1) && leftBlock(block2,1) && leftBlock(block3,1))
            return true;
        return false;
    }

    private boolean leftBlock(Button block, int character)
    {
        if((drawer.isLeftDrawBlock(block) || block.getX()==0) && character==1)//żeby rozróżnić czy do obrotu czy do przesuwu
            return false;
        if((drawer.isLeftDrawBlock(block) || block.getX()<0) && character==2)
            return false;
        return true;
    }

    public void moveHorizontalFall()
    {
        blockMain.setY(blockMain.getY()+initialBlockDiff);
        block1.setY(block1.getY()+initialBlockDiff);
        block2.setY(block2.getY()+initialBlockDiff);
        block3.setY(block3.getY()+initialBlockDiff);
    }

    public void moveHorizontalNotFall()
    {
        sendDrawData(blockMain);
        sendDrawData(block1);
        sendDrawData(block2);
        sendDrawData(block3);
        sortByYBlocks();
        speed=1000-100*(Integer.parseInt(textLvl.getText().toString())-1);
        blockMain.setX(5*initialBlockDiff);
        blockMain.setY(0);
    }

    public boolean fallBlocks()
    {
        //sprawdza czy wszystkie mogą spadać...czyli jeżeli nie to ma się zatrzymać
        if(fallBlock(blockMain,1) && fallBlock(block1,1) && fallBlock(block2,1) && fallBlock(block3,1))
            return true;
        return false;
    }

    private boolean fallBlock(Button block, int chracter)
    {
        //sprawdza czy może spaść jeden kwadracik
        if((drawer.isAboveDrawBlock(block) || block.getY()+initialBlockDiff==initialBlockDiff*20) && chracter==1)
            return false;
        if((drawer.isAboveDrawBlock(block) || block.getY()+initialBlockDiff>initialBlockDiff*20) && chracter==2)
            return false;
        return true;
    }

    private void sendDrawData(Button block)
    {
        //wysyłanie obiektu do narysowania
        DrawerSquare drawBlock = new DrawerSquare(new RectF(block.getX(), block.getY()+initialBlockDiff-2, block.getX()+initialBlockDiff-2, block.getY()), figure.color);
        drawer.drawBlock(drawBlock);
    }

    private void sortByYBlocks()
    {
        //sportowanie przez Zyzika (żeby nie spadały te co są ma dole
        Button tab[] = new Button[4];
        tab[0]=blockMain;
        tab[1]=block1;
        tab[2]=block2;
        tab[3]=block3;

        for (int i=0; i<4; i++)
        {
            for (int j=0; j<4; j++)
            {
                if (tab[i].getY() < tab[j].getY()) {
                    Button b1=tab[i];
                    Button b2=tab[j];
                    tab[i]=b2;
                    tab[j]=b1;
                }
                if(j==3)
                {
                    Button b1=tab[i];
                    Button b2=tab[j];
                    tab[i]=b2;
                    tab[j]=b1;
                }
            }
        }

        for (int i=0; i<4; i++)
        {
            sendDrawDataDown(tab[i]);
        }
    }

    private void sendDrawDataDown(Button block)
    {
        //wysyłanie obiektu do usuwania i przesunięcia w dół i dodawania pkt.
        DrawerSquare drawBlock = new DrawerSquare(new RectF(block.getX(), block.getY()+initialBlockDiff-2, block.getX()+initialBlockDiff-2, block.getY()), figure.color);
        ColorBackground colorBackground = new ColorBackground(textLvl, drawer, layoutMainActivity);
        drawer.DrawBlockDown(drawBlock, textScore, colorBackground);
    }

    public void changeRotation()
    {
        //obrót figury
        if(figure.rotable)
        {
            int i=0;
            do {
                    rotationBlock(block1);
                    rotationBlock(block2);
                    rotationBlock(block3);
                    i++;

            }while (!isOutOfScope() && i<4);
        }
    }

    private void rotationBlock(Button block)
    {
        //zmiana pozycji block zgodnie z Zyzikowym schematem
        float X=(block.getX()-blockMain.getX())/initialBlockDiff;
        float Y=(blockMain.getY()-block.getY())/initialBlockDiff;
        float x=0;
        float y=0;
        if(X>=0 && Y>0)
        {
            x=Y;
            y=X;
            if(x<0)
                x=x*(-1);
            if(y>0)
                y=y*(-1);
        }
        if(X>0 && Y<=0)
        {
            x=Y;
            y=X;
            if(x>0)
                x=x*(-1);
            if(y>0)
                y=y*(-1);
        }
        if(X<=0 && Y<0)
        {
            x=Y;
            y=X;
            if(x>0)
                x=x*(-1);
            if(y<0)
                y=y*(-1);
        }
        if(X<0 && Y>=0)
        {
            x=Y;
            y=X;
            if(x<0)
                x=x*(-1);
            if(y<0)
                y=y*(-1);
        }

        block.setX(blockMain.getX()+x*initialBlockDiff);
        block.setY(blockMain.getY()-y*initialBlockDiff);
    }

    private boolean isOutOfScope()
    {
        //czy nie wychodzą po za
        if(rightBlocksOut() && leftBlocksOut() && fallBlocksOut())
            return true;
        return false;
    }

    private boolean rightBlocksOut()
    {
        //czy może być po prawej przeszkoda by się obróciło
        if(rightBlock(blockMain,2) && rightBlock(block1, 2) && rightBlock(block2,2) && rightBlock(block3,2))
            return true;
        return false;
    }

    private boolean leftBlocksOut()
    {
        if(leftBlock(blockMain,2) && leftBlock(block1,2) && leftBlock(block2,2) && leftBlock(block3,2))
            return true;
        return false;
    }

    public boolean fallBlocksOut()
    {
        if(fallBlock(blockMain,2) && fallBlock(block1,2) && fallBlock(block2,2) && fallBlock(block3,2))
            return true;
        return false;
    }

    public boolean speedUp(MotionEvent e1, MotionEvent e2)
    {
        if(e2.getY()-e1.getY()>25)
            return true;
        return false;
    }

}