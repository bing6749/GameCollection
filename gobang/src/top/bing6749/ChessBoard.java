package top.bing6749;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
/*������-������*/
public class ChessBoard extends JPanel implements MouseListener{
    public static int MARGIN=30;//�߾�
    public static int GRID_SPAN=35;//������
    public static int ROWS=15;//��������
    public static int COLS=15;//��������
    top.bing6749.Point[] chessList=new top.bing6749.Point[(ROWS+1)*(COLS+1)];//��ʼ��ÿ������Ԫ��Ϊnull
    boolean isBack=true;//Ĭ�Ͽ�ʼ�Ǻ�������
    boolean gameOver=false;//��Ϸ�Ƿ����
    int chessCount;//��ǰ���̵����Ӹ���
    int xIndex,yIndex;//��ǰ�������ӵ�����
    public ChessBoard(){
        setBackground(Color.LIGHT_GRAY);//���ñ�����ɫΪ��ɫ
        addMouseListener(this);//����¼�������
        addMouseMotionListener(new MouseMotionListener() {//�����ڲ���

            @Override
            public void mouseMoved(MouseEvent e) {
                int x1=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
                int y1=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;//����굥��������λ��ת��Ϊ��������
                if(x1<0||x1>ROWS||y1<0||y1>COLS||gameOver||findChess(x1,y1)){//��Ϸ�Ѿ������������£����������⣬�����£�x��yλ���Ѿ������Ӵ��ڣ�������
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//���ó�Ĭ����״
                }else{
                    setCursor(new Cursor(Cursor.HAND_CURSOR));//���ó�����
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }
        });
    }
    /*����*/
    public void paintComponent(Graphics g){
        super.paintComponent(g);//������
        for(int i=0;i<=ROWS;i++){//������
            g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN);
        }
        for(int i=0;i<=COLS;i++){//��ֱ��
            g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN,MARGIN+ROWS*GRID_SPAN);
        }
        /*������*/
        for(int i=0;i<chessCount;i++){
            int xPos= (int) (chessList[i].getX()*GRID_SPAN+MARGIN);//���񽻲��x����
            int yPos= (int) (chessList[i].getY()*GRID_SPAN+MARGIN);//���񽻲��y����
            g.setColor(chessList[i].getColor());//������ɫ
            g.fillOval(xPos- top.bing6749.Point.DIAMETER/2, yPos- top.bing6749.Point.DIAMETER/2, top.bing6749.Point.DIAMETER, top.bing6749.Point.DIAMETER);
            if(i==chessCount-1){
                g.setColor(Color.red);//������һ������Ϊ��ɫ
                g.drawRect(xPos- top.bing6749.Point.DIAMETER/2, yPos- top.bing6749.Point.DIAMETER/2, top.bing6749.Point.DIAMETER, top.bing6749.Point.DIAMETER);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {//��갴��������ϰ���ʱ����
        if(gameOver)//��Ϸ�Ѿ�������������
            return ;
        String colorName=isBack ? "����" : "����";
        xIndex=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
        yIndex=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;//����굥��������λ��ת��Ϊ��������
        if(xIndex<0||xIndex>ROWS||yIndex<0||yIndex>COLS)//�������������⣬������
            return ;
        if(findChess(xIndex,yIndex))//x,yλ���Ѿ������Ӵ��ڣ�������
            return ;

        top.bing6749.Point ch= new top.bing6749.Point(xIndex,yIndex,isBack ? Color.black : Color.white);
        chessList[chessCount++]=ch;
        repaint();//֪ͨϵͳ���»���
        if(isWin()){
            String msg=String.format("��ϲ��%sӮ��~", colorName);
            JOptionPane.showMessageDialog(this, msg);
            gameOver=true;
        }
        else if(chessCount==(COLS+1)*(ROWS+1))
        {
            String msg=String.format("����൱��������~");
            JOptionPane.showMessageDialog(this,msg);
            gameOver=true;
        }
        isBack=!isBack;
    }

    @Override
    public void mouseClicked(MouseEvent e) {//��갴��������ϵ���(���²��ͷ�)ʱ����
    }

    @Override
    public void mouseReleased(MouseEvent e) {////��갴����������ͷ�ʱ����
    }

    @Override
    public void mouseEntered(MouseEvent e) {//���������ʱ����
    }

    @Override
    public void mouseExited(MouseEvent e){//����뿪���ʱ����
    }

    private boolean findChess(int x,int y){
        for(top.bing6749.Point c:chessList){
            if(c!=null&&c.getX()==x&&c.getY()==y)
                return true;
        }
        return false;
    }

    /*�ж��Ƿ�Ӯ*/
    private boolean isWin(){
        int continueCount=1;//�������ӵĸ���
        for(int x=xIndex-1;x>=0;x--){//��������Ѱ��
            Color c=isBack ? Color.black : Color.white;
            if(getChess(x,yIndex,c)!=null){
                continueCount++;
            }else
                break;
        }
        for(int x=xIndex+1;x<=ROWS;x++){//��������Ѱ��
            Color c=isBack ? Color.black : Color.white;
            if(getChess(x,yIndex,c)!=null){
                continueCount++;
            }else
                break;
        }
        if(continueCount>=5){//�жϼ�¼�����ڵ����壬����ʾ�˷���ʤ
            return true;
        }else
            continueCount=1;
        //
        for(int y=yIndex-1;y>=0;y--){//��������Ѱ��
            Color c=isBack ? Color.black : Color.white;
            if(getChess(xIndex,y,c)!=null){
                continueCount++;
            }else
                break;
        }
        for(int y=yIndex+1;y<=ROWS;y++){//��������Ѱ��
            Color c=isBack ? Color.black : Color.white;
            if(getChess(xIndex,y,c)!=null){
                continueCount++;
            }else
                break;
        }
        if(continueCount>=5){//�жϼ�¼�����ڵ����壬����ʾ�˷���ʤ
            return true;
        }else
            continueCount=1;
        //
        for(int x=xIndex+1,y=yIndex-1;y>=0&&x<=COLS;x++,y--){//����Ѱ��
            Color c=isBack ? Color.black : Color.white;
            if(getChess(x,y,c)!=null){
                continueCount++;
            }else
                break;
        }
        for(int x=xIndex-1,y=yIndex+1;y<=ROWS&&x>=0;x--,y++){//����Ѱ��
            Color c=isBack ? Color.black : Color.white;
            if(getChess(x,y,c)!=null){
                continueCount++;
            }else
                break;
        }
        if(continueCount>=5){//�жϼ�¼�����ڵ����壬����ʾ�˷���ʤ
            return true;
        }else
            continueCount=1;
        //
        for(int x=xIndex-1,y=yIndex-1;y>=0&&x>=0;x--,y--){//����Ѱ��
            Color c=isBack ? Color.black : Color.white;
            if(getChess(x,y,c)!=null){
                continueCount++;
            }else
                break;
        }
        for(int x=xIndex+1,y=yIndex+1;y<=ROWS&&x<=COLS;x++,y++){//����Ѱ��
            Color c=isBack ? Color.black : Color.white;
            if(getChess(x,y,c)!=null){
                continueCount++;
            }else
                break;
        }
        if(continueCount>=5){//�жϼ�¼�����ڵ����壬����ʾ�˷���ʤ
            return true;
        }else
            continueCount=1;
        return false;
    }
    private top.bing6749.Point getChess(int xIndex, int yIndex, Color color){
        for(Point c:chessList){
            if(c!=null&&c.getX()==xIndex&&c.getY()==yIndex&&c.getColor()==color)
                return c;
        }
        return null;
    }
    public void restartGame(){//�������
        for(int i=0;i<chessList.length;i++)
            chessList[i]=null;
        /*�ָ���Ϸ��صı���ֵ*/
        isBack=true;
        gameOver=false;//��Ϸ�Ƿ����
        chessCount=0;//��ǰ���̵����Ӹ���
        repaint();
    }
    public void goback(){
        if(chessCount==0)
            return ;
        chessList[chessCount-1]=null;
        chessCount--;
        if(chessCount>0){
            xIndex=chessList[chessCount-1].getX();
            yIndex=chessList[chessCount-1].getY();
        }
        isBack=!isBack;
        repaint();
    }
    //Dimension:����
    public Dimension getPreferredSize(){
        return new Dimension(MARGIN*2+GRID_SPAN*COLS,MARGIN*2+GRID_SPAN*ROWS);
    }

}

