package top.bing6749;


import com.snake.GamePanel;
import table.User;
import top.bing6749.plane.MyGameFrame;
import top.bing6749.plane.MyMainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;

/**
 * @author MaRui
 */
public class GameHill extends JFrame {
    /**
     * ���
     */
    private JFrame jFrame;
    /**
     * ������
     */
    private JPanel topPanel;
    /**
     * ������
     */
    private JPanel bottomPanel;
    /**
     * ��С����ť
     */
    private JButton min;
    /**
     * �رհ�ť
     */
    private JButton exit;

    private JLabel logo;
    private JLabel game2048;
    private JLabel game20481;
    private JLabel plane;
    private JLabel plane1;
    private JLabel man;
    private JLabel man1;
    private JLabel snake;
    private JLabel snake1;
    private JLabel gobang;
    private JLabel gobang1;
    private JLabel username;
    private JLabel jkxt;    //����ϵͳ
    private JLabel jkxt2;    //����ϵͳ


    public void init(User user) {
        // ��������
        this.setTitle("��Ϸ����");
        // ���ô���ͼ��
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/imgs/��Ϸ.jpg"));
        // �˳�ʱ�ر���Դ
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ���ô��ڳߴ�
        this.setSize(1024, 768);
        // ���ô�����Ļ����
        this.setLocationRelativeTo(null);
        // ���ô��ڲ��ܸı��С
        this.setResizable(false);
        this.setLayout(null);


        // ������
        topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1050, 200);
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(0, 0, 0));
        // ������
        bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 200, 1050, 600);
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(new Color(225, 225, 255));
        // ��С����ť
        min = new JButton(new ImageIcon("src/images/m3.png"));
        min.setBounds(900, 10, 50, 50);
        min.setBorder(null);
        min.setRolloverIcon(new ImageIcon("src/images/m2.png"));
        // �رհ�ť
        exit = new JButton(new ImageIcon("src/images/3.png"));
        exit.setBounds(960, 10, 50, 50);
        exit.setBorder(null);
        exit.setRolloverIcon(new ImageIcon("src/images/2.png"));
        // gameLogo
        logo = new JLabel(new ImageIcon("src/images/logo1.jpg"));
        logo.setBounds(340, 23, 297, 161);
        //username
        username = new JLabel("�𾴵��û�:" + user.getUsername());
        username.setForeground(Color.BLUE);
        username.setFont(new Font("΢���ź�", Font.BOLD, 20));
        username.setBounds(50, 90, 500, 40);
//        System.out.println(user.getUsername());
        //2048
        game2048 = new JLabel(new ImageIcon("src/images/2048.jpg"));
        game2048.setBounds(140, 113, 100, 100);
        //20481
        game20481 = new JLabel(new ImageIcon("src/images/20481.png"));
        game20481.setBounds(140, 113, 100, 100);
        game20481.setVisible(false);
        //plane
        plane = new JLabel(new ImageIcon("src/images/plane.jpg"));
        plane.setBounds(440, 113, 100, 100);
        //plane1
        plane1 = new JLabel(new ImageIcon("src/images/plane1.png"));
        plane1.setBounds(440, 113, 100, 100);
        plane1.setVisible(false);
        //man
        man = new JLabel(new ImageIcon("src/images/man.png"));
        man.setBounds(740, 113, 100, 100);
        //man1
        man1 = new JLabel(new ImageIcon("src/images/man1.png"));
        man1.setBounds(740, 113, 100, 100);
        man1.setVisible(false);
        //snake
        snake = new JLabel(new ImageIcon("src/images/snake.jpg"));
        snake.setBounds(140, 363, 100, 100);
        //snake1
        snake1 = new JLabel(new ImageIcon("src/images/snake1.png"));
        snake1.setBounds(140, 363, 100, 100);
        snake1.setVisible(false);
        //gobang
        gobang = new JLabel(new ImageIcon("src/images/gobang.png"));
        gobang.setBounds(440, 363, 100, 100);
        //gobang1
        gobang1 = new JLabel(new ImageIcon("src/images/gobang1.png"));
        gobang1.setBounds(440, 363, 100, 100);
        gobang1.setVisible(false);

        //����ϵͳ
        jkxt = new JLabel("���Ʋ�����Ϸ���ܾ�������Ϸ��ע�����ұ�����������ƭ�ϵ���");
        jkxt.setForeground(Color.WHITE);
        jkxt.setFont(new Font("΢���ź�", Font.BOLD, 11));
        jkxt.setBounds(710, 492, 800, 80);
        jkxt2 = new JLabel("�ʶ���Ϸ���ԣ�������Ϸ����������ʱ�䣬���ܽ������");
        jkxt2.setForeground(Color.WHITE);
        jkxt2.setFont(new Font("΢���ź�", Font.BOLD, 11));
        jkxt2.setBounds(710, 510, 800, 80);

        //1ͼƬ�ļ���

        //������
        topPanel.add(exit);
        topPanel.add(min);
        topPanel.add(logo);
        topPanel.add(username);
        this.add(topPanel);

        bottomPanel.add(game2048);
        bottomPanel.add(game20481);
        bottomPanel.add(plane);
        bottomPanel.add(plane1);
        bottomPanel.add(man);
        bottomPanel.add(man1);
        bottomPanel.add(snake);
        bottomPanel.add(snake1);
        bottomPanel.add(gobang);
        bottomPanel.add(gobang1);
        bottomPanel.add(jkxt);
        bottomPanel.add(jkxt2);
        this.add(bottomPanel);


        this.setUndecorated(true); // ȥ�����ڵ�װ��

        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//����ָ���Ĵ���װ�η��

        {
            /**
             * ��С��
             */
            min.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setExtendedState(JFrame.ICONIFIED);
                }
            });
            /**
             * �ر�
             */
            exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

        }

        // �����Ƿ�ɼ�
        this.setVisible(true);
        Lister();
    }


    /**
     * ����������
     */

    public void Lister() {
        {
            /**
             * ����ƶ���plane    label��
             */
            game2048.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    game2048.setVisible(false);
                    game20481.setVisible(true);
                    System.out.println("�����");
                }
            });

            /**
             * ����Ƴ�plane1   label
             */
            game20481.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    game2048.setVisible(true);
                    game20481.setVisible(false);

                    System.out.println("�뿪");
                }

            });
            //2048�������귽��
            game2048.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                        JFrame f = new JFrame();
                        f.setTitle("game2048");
                        f.add(new Game2048(), BorderLayout.CENTER);
                        f.pack();
                        f.setLocationRelativeTo(null);  //���ڽ�������Ļ������
                        f.setVisible(true);
                        f.setResizable(false);      //�˴��岻�����û�������С
                }
            });
            game20481.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                        System.out.println(Thread.currentThread().getName());
                        JFrame f = new JFrame();
                        f.setTitle("game2048");
                        f.add(new Game2048(), BorderLayout.CENTER);
                        f.pack();
                        f.setLocationRelativeTo(null);  //���ڽ�������Ļ������
                        f.setVisible(true);
                        f.setResizable(false);      //�˴��岻�����û�������С
                }
            });
        }

        {
            /**
             * ����ƶ���plane    label��
             */
            plane.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    plane.setVisible(false);
                    plane1.setVisible(true);
                    System.out.println("�����");
                }
            });

            /**
             * ����Ƴ�plane1   label
             */
            plane1.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    plane.setVisible(true);
                    plane1.setVisible(false);

                    System.out.println("�뿪");
                }

            });
            plane1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SwingWorker swingWorker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            new GameWin().launch();
                            return null;
                        }
                    };
                    swingWorker.execute();
                }
            });

            plane.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SwingWorker swingWorker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            new GameWin().launch();
                            return null;
                        }
                    };
                    swingWorker.execute();
                }
            });
        }
        {
            /**
             * ����ƶ���man    label��
             */
            man.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    man.setVisible(false);
                    man1.setVisible(true);
                    System.out.println("�����");
                }
            });

            /**
             * ����Ƴ�man1   label
             */
            man1.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    man.setVisible(true);
                    man1.setVisible(false);

                    System.out.println("�뿪");
                }

            });
            man1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    MyGameFrame frame = new MyGameFrame();
                    //������Ϸ��ĳ�ʼ����
                    frame.launchFrame();

                }
            });

            man.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    MyGameFrame frame = new MyGameFrame();
                    //������Ϸ��ĳ�ʼ����
                    frame.launchFrame();
                }
            });
        }
        {
            /**
             * ����ƶ���snake    label��
             */
            snake.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    snake.setVisible(false);
                    snake1.setVisible(true);
                    System.out.println("�����");
                }
            });

            /**
             * ����Ƴ�snake1   label
             */
            snake1.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    snake.setVisible(true);
                    snake1.setVisible(false);

                    System.out.println("�뿪");
                }

            });
            snake.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JFrame frame = new JFrame("̰����");

                    // �����Ϸ���
                    frame.add(new GamePanel());

                    frame.setVisible(true);
                    frame.setResizable(false); // ��ֹ������С
                    frame.setBounds(10, 10, 900, 720);
                }
            });

            snake1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JFrame frame = new JFrame("̰����");

                    // �����Ϸ���
                    frame.add(new GamePanel());

                    frame.setVisible(true);
                    frame.setResizable(false); // ��ֹ������С
                    frame.setBounds(10, 10, 900, 720);
                }
            });
        }
        {
            /**
             * ����ƶ���gobang    label��
             */
            gobang.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    gobang.setVisible(false);
                    gobang1.setVisible(true);
                    System.out.println("�����");
                }
            });

            /**
             * ����Ƴ�gobang1   label
             */
            gobang1.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    gobang.setVisible(true);
                    gobang1.setVisible(false);

                    System.out.println("�뿪");
                }

            });
            gobang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    StartChessJFrame f = new StartChessJFrame();//���������
                    f.setVisible(true);//��ʾ�����
                }
            });

            gobang1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    StartChessJFrame f = new StartChessJFrame();//���������
                    f.setVisible(true);//��ʾ�����
                }
            });
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameHill().init(new User("123", "123"));
        });
    }
}
