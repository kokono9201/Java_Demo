import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author jiuzhe
 * @create 2022-10-28-21:12
 * main frame and app
 */
public class Start extends JFrame {
    private ChessBoard chessBoard;
    private JPanel toolbar;
    private JButton startButton,backButton,exitButton;

    private JMenuBar menuBar;
    private JMenu sysMenu;
    private JMenuItem startMenuItem,exitMenuItem,backMenuItem;


    public Start(){
        setTitle("Gobang Chess");//设置标题
        chessBoard=new ChessBoard();


        Container contentPane=getContentPane();
        contentPane.add(chessBoard);
        chessBoard.setOpaque(true);


        //menu
        menuBar =new JMenuBar();//init menu
        sysMenu=new JMenu("System");
        //init menu
        startMenuItem=new JMenuItem("Restart");
        exitMenuItem =new JMenuItem("Exit");
        backMenuItem =new JMenuItem("GoBack");

        sysMenu.add(startMenuItem);
        sysMenu.add(exitMenuItem);
        sysMenu.add(backMenuItem);

        MyItemListener lis=new MyItemListener();

        this.startMenuItem.addActionListener(lis);
        backMenuItem.addActionListener(lis);
        exitMenuItem.addActionListener(lis);
        menuBar.add(sysMenu);
        setJMenuBar(menuBar);

        toolbar=new JPanel();
        //init button
        startButton=new JButton("Restart");
        exitButton=new JButton("Exit");
        backButton=new JButton("Goback");

        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        toolbar.add(startButton);
        toolbar.add(exitButton);
        toolbar.add(backButton);

        startButton.addActionListener(lis);
        exitButton.addActionListener(lis);
        backButton.addActionListener(lis);

        add(toolbar,BorderLayout.SOUTH);
        add(chessBoard);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

    }

    private class MyItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj=e.getSource();//获得事件源
            if(obj==Start.this.startMenuItem||obj==startButton){

                System.out.println("Restart");
                chessBoard.restartGame();
            }
            else if (obj==exitMenuItem||obj==exitButton)
                System.exit(0);
            else if (obj==backMenuItem||obj==backButton){
                System.out.println("GoBack...");
                chessBoard.goBack();
            }
        }
    }



    public static void main(String[] args){
        Start f=new Start();//创建主框架
        f.setVisible(true);//显示主框架

    }

}
