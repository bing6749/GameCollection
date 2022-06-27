package top.bing6749.plane;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author MaRui
 * 历史记录窗口
 */
public class HistoryRecord {
    public void init(){
        JFrame jf = new JFrame("历史记录");
        jf.setSize(500, 300);
        jf.setLocationRelativeTo(null);
        JPanel panel = new JPanel();

        // 创建一个 JList 实例
        final JList<String> list = new JList<String>();

        // 设置一下首选大小
        list.setPreferredSize(new Dimension(200, 600));
        // 允许可间断的多选
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // 设置选项数据（内部将自动封装成 ListModel ）
        list.setListData(new String[]{"香蕉", "雪梨", "苹果", "荔枝","Monday", "Tuesday", "Wednesday","苹果", "荔枝","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});

        // 添加选项选中状态被改变的监听器
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // 获取所有被选中的选项索引
                int[] indices = list.getSelectedIndices();
                // 获取选项数据的 ListModel
                ListModel<String> listModel = list.getModel();
                // 输出选中的选项
                for (int index : indices) {
                    System.out.println("选中: " + index + " = " + listModel.getElementAt(index));
                }
                System.out.println();
            }
        });

        // 设置默认选中项
        list.setSelectedIndex(1);

        JScrollPane jScrollPane = new JScrollPane(list);

        jScrollPane.setBounds(200, 10, 100, 200);
        panel.setLayout(null);

        // 添加到内容面板容器
        //panel.add(list);
        panel.add(jScrollPane);


        jf.setContentPane(panel);
        jf.setVisible(true);
    }
}
