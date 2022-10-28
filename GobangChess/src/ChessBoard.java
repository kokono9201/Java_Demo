import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

/**
 * @author jiuzhe
 * @create 2022-10-28-21:13
 * chessboard
 */
public class ChessBoard extends JPanel implements MouseListener {

    //set the chessboard
    public static final int MARGIN=30;
    public static final int GRID_SPAN=35;
    public static final int ROWS=15;
    public static final int COLS=15;

    //init the chess
    Chess[] chessList = new Chess[(ROWS+1)*(COLS+1)];
    //first player
    boolean isBlack = true;
    //gameOver judgement
    boolean gameOver = false;
    //total chess
    int chessCount;
    //coordinate
    int xIndex, yIndex;

    //init chessboard
    Image img;
    Color colorTemp;
    public ChessBoard(){
        img=Toolkit.getDefaultToolkit().getImage("board.jpg");
        addMouseListener(this);
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //transfer mouse coordinate into chessboard coordinate
                int x1=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
                int y1=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
                //out of range or gameOver
                if(x1<0||x1>ROWS||y1<0||y1>ROWS||gameOver||findChess(x1,y1)){
                    //disable
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }else{
                    //pass
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

            }
        });
    }

    //paint the chessboard
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        //img size
        int imgWidth = img.getWidth(this);
        int imgHeight = img.getHeight(this);
        //window size
        int FWidth = getWidth();
        int FHeight = getHeight();

        int x = (FWidth-imgWidth)/2;
        int y = (FHeight-imgHeight)/2;
        g.drawImage(img,x,y,null);


        //draw the rows
        for(int i=0;i<=ROWS;i++){//画横线
            g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN);
        }
        //draw cols
        for(int i=0;i<=COLS;i++){//画竖线
            g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);

        }

        //draw chess
        for (int i = 0; i < chessCount; i++) {
            int xPos = chessList[i].getX()*GRID_SPAN+MARGIN;
            int yPos = chessList[i].getY()*GRID_SPAN+MARGIN;
            g.setColor(chessList[i].getColor());
            colorTemp=chessList[i].getColor();
            if(colorTemp==Color.black){
                RadialGradientPaint paint = new RadialGradientPaint(
                        xPos-Chess.DIAMETER/2+25, yPos-Chess.DIAMETER/2+10, 20, new float[]{0f, 1f}, new Color[]{Color.WHITE, Color.BLACK}
                );
                ((Graphics2D) g).setPaint(paint);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
            }else if(colorTemp==Color.white){
                RadialGradientPaint paint = new RadialGradientPaint(xPos-Chess.DIAMETER/2+25, yPos-Chess.DIAMETER/2+10, 70, new float[]{0f, 1f}
                        , new Color[]{Color.WHITE, Color.BLACK});
                ((Graphics2D) g).setPaint(paint);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
            }

            //mark the red frame of last chess
            Ellipse2D e = new Ellipse2D.Float(xPos-Chess.DIAMETER/2, yPos-Chess.DIAMETER/2, 34, 35);
            ((Graphics2D) g).fill(e);

            //if the last one
            if(i==chessCount-1){
                g.setColor(Color.red);
                g.drawRect(xPos-Chess.DIAMETER/2, yPos-Chess.DIAMETER/2,
                        34, 35);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        if(gameOver){
            return;
        }

        String colorName=isBlack?"Black":"White";

        xIndex=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
        yIndex=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;

        if(xIndex<0||xIndex>ROWS||yIndex<0||yIndex>COLS){
            return;
        }

        if(findChess(xIndex,yIndex)){
            return;
        }

        Chess ch=new Chess(xIndex,yIndex,isBlack?Color.black:Color.white);
        chessList[chessCount++]=ch;
        repaint();

        if(isWin()){
            String msg=String.format("Congratulation，%s Win！", colorName);
            JOptionPane.showMessageDialog(this, msg);
            gameOver=true;
        }
        isBlack=!isBlack;
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //check if the position has chess
    private boolean findChess(int x1, int y1) {
        for(Chess c:chessList){
            if(c!=null&&c.getX()==x1&&c.getY()==y1){
                return true;
            }
        }return false;
    }

    //check if win
    private boolean isWin() {
        int continueCount=1;

        for(int x=xIndex-1;x>=0;x--){
            Color c=isBlack?Color.black:Color.white;
            if(getChess(x,yIndex,c)!=null){
                continueCount++;
            }else{
                break;
            }
        }
        for(int x=xIndex+1;x<=COLS;x++){
            Color c=isBlack?Color.black:Color.white;
            if(getChess(x,yIndex,c)!=null){
                continueCount++;
            }else{
                break;
            }
        }
        if(continueCount>=5){
            return true;
        }else {
            continueCount = 1;
        }

        for(int y=yIndex-1;y>=0;y--){
            Color c=isBlack?Color.black:Color.white;
            if(getChess(xIndex,y,c)!=null){
                continueCount++;
            }else {
                break;
            }
        }

        for(int y=yIndex+1;y<=ROWS;y++){
            Color c=isBlack?Color.black:Color.white;
            if(getChess(xIndex,y,c)!=null){
                continueCount++;
            }
            else{
                break;
            }

        }
        if(continueCount>=5){
            return true;
        }
        else{
            continueCount=1;
        }

        for(int x=xIndex+1,y=yIndex-1;y>=0&&x<=COLS;x++,y--){
            Color c=isBlack?Color.black:Color.white;
            if(getChess(x,y,c)!=null){
                continueCount++;
            }
            else{
                break;
            }
        }

        for(int x=xIndex-1,y=yIndex+1;x>=0&&y<=ROWS;x--,y++){
            Color c=isBlack?Color.black:Color.white;
            if(getChess(x,y,c)!=null){
                continueCount++;
            }
            else{
                break;
            }
        }
        if(continueCount>=5)
            return true;
        else continueCount=1;



        for(int x=xIndex-1,y=yIndex-1;x>=0&&y>=0;x--,y--){
            Color c=isBlack?Color.black:Color.white;
            if(getChess(x,y,c)!=null) {
                continueCount++;
            }
            else {
                break;
            }
        }

        for(int x=xIndex+1,y=yIndex+1;x<=COLS&&y<=ROWS;x++,y++){
            Color c=isBlack?Color.black:Color.white;
            if(getChess(x,y,c)!=null){
                continueCount++;
            }
            else {
                break;
            }
        }
        if(continueCount>=5) {
            return true;
        }
        else {
            continueCount=1;
        }

        return false;

    }

    private Chess getChess(int xIndex,int yIndex,Color color){
        for(Chess p:chessList){
            if(p!=null&&p.getX()==xIndex&&p.getY()==yIndex
                    &&p.getColor()==color)
                return p;
        }
        return null;
    }

    public void restartGame(){
        //clear all the paint and param
        for(int i=0;i<chessList.length;i++){
            chessList[i]=null;
        }

        isBlack=true;
        gameOver=false;
        chessCount =0;
        repaint();
    }

    public void goBack(){
        if(chessCount==0)
            return ;
        chessList[chessCount-1]=null;
        chessCount--;
        if(chessCount>0){
            xIndex=chessList[chessCount-1].getX();
            yIndex=chessList[chessCount-1].getY();
        }
        isBlack=!isBlack;
        repaint();
    }

    public Dimension getPreferredSize(){
        return new Dimension(MARGIN*2+GRID_SPAN*COLS,MARGIN*2
                +GRID_SPAN*ROWS);
    }







}
