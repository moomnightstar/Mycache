//package snoop;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class snoop {	
	/*****����panel2~panel5******/
	static Mypanel panel2 =new Mypanel();
	static Mypanel panel3 =new Mypanel();
	static Mypanel panel4 =new Mypanel();
	static Mypanel panel5 =new Mypanel();

	/*********memory�ı���*********/
	static String[] Mem_ca={
			"Memory","","","Memory","","","Memory","",""
	};

	/*********memory�е�����*********/
	static String[][] Mem_Content ={
			{"0","","","10","","","20","",""},{"1","","","11","","","21","",""},{"2","","","12","","","22","",""},
			{"3","","","13","","","23","",""},{"4","","","14","","","24","",""},{"5","","","15","","","25","",""},
			{"6","","","16","","","26","",""},{"7","","","17","","","27","",""},{"8","","","18","","","28","",""},
			{"9","","","19","","","29","",""}
	};
	
	static JComboBox<String> Mylistmodel1_1 = new JComboBox<>(new Mylistmodel());
	static class Mylistmodel extends AbstractListModel<String> implements ComboBoxModel<String>{		
		private static final long serialVersionUID = 1L;
		String selecteditem=null;
		private String[] test={"ֱ��ӳ��","��·������","��·������"};
		public String getElementAt(int index){
			return test[index];
		}
		public int getSize(){
			return test.length;
		}
		public void setSelectedItem(Object item){
			selecteditem=(String)item;
		}
		public Object getSelectedItem( ){
			return selecteditem;
		}
		public int getIndex() {
			for (int i = 0; i < test.length; i++) {
				if (test[i].equals(getSelectedItem()))
					return i;
			}
			return 0;
		}
	}

	static class Mylistmodel2 extends AbstractListModel<String> implements ComboBoxModel<String>{		
		private static final long serialVersionUID = 1L;
		String selecteditem=null;
		private String[] test={"��","д"};
		public String getElementAt(int index){
			return test[index];
		}
		public int getSize(){
			return test.length;
		}
		public void setSelectedItem(Object item){
			selecteditem=(String)item;
		}
		public Object getSelectedItem( ){
			return selecteditem;
		}
		public int getIndex() {
			for (int i = 0; i < test.length; i++) {
				if (test[i].equals(getSelectedItem()))
					return i;
			}
			return 0;
		}
		
	}

	static class Mypanel extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		JLabel label=new JLabel("���ʵ�ַ");
		JLabel label_2=new JLabel("Process1");
		
		JTextField jtext=new JTextField("");
		JButton button=new JButton("ִ��");
		JComboBox<String> Mylistmodel = new JComboBox<>(new Mylistmodel2());
		
		
		/*********cache�еı���*********/
		String[] Cache_ca={"Cache","��/д","Ŀ���ַ"};
		/*********cache�е�����*********/
		String[][] Cache_Content = {
				{"0"," "," "},{"1"," "," "},{"2"," "," "},{"3"," "," "}
		};
		/************cache�Ĺ���ģ��***********/
		JTable table_1 = new JTable(Cache_Content,Cache_ca);
	JScrollPane scrollPane = new JScrollPane(table_1);

		/************memory�Ĺ���ģ��**********
		JTable table_2 = new JTable(Mem_Content,Mem_ca); 
		JScrollPane scrollPane2 = new JScrollPane(table_2);
		*/
		public Mypanel(){
			super();
			setSize(300, 200);
			setLayout(null);
			
			/*****���ԭ��********/
			add(jtext);
			add(label);
			add(label_2);
			add(button);
			add(Mylistmodel);
			add(scrollPane);
			//add(scrollPane2);
			
			/****����ԭ����С������********/
			label_2.setFont(new Font("",1,16));
			label_2.setBounds(10, 10, 100, 30);
			
			label.setFont(new Font("",1,16));
			label.setBounds(10, 50, 100, 30);
			
			jtext.setFont(new Font("",1,15));
			jtext.setBounds(100, 50, 50, 30);
			
			Mylistmodel.setFont(new Font("",1,15));
			Mylistmodel.setBounds(160, 50, 50, 30);
			
			scrollPane.setFont(new Font("",1,15));
			scrollPane.setBounds(10, 90, 310, 90);
			
			//scrollPane2.setFont(new Font("",1,15));
			//scrollPane2.setBounds(10, 190, 310, 180);
			
			button.setFont(new Font("",1,15));
			button.setBounds(220,50, 100, 35);
			
			/******��Ӱ�ť�¼�********/
			button.addActionListener(this);
		}
		
		public void init(){
			/******Mypanel�ĳ�ʼ��******/
			jtext.setText(null);
			Mylistmodel.setSelectedItem(null);
			for(int i=0;i<=3;i++)
				for(int j=1;j<=2;j++)
					Cache_Content[i][j]=" ";
			for(int i=0;i<=9;i++)
				for(int j=1;j<=2;j++)
					Mem_Content[i][j]=" ";
			setVisible(false);
			setVisible(true);
            for(int i=0;i<4;i++){
                    cacheState[i]=0;//cachestate��ʼ��,��Ч
            }
		}
        public int []cacheState=new int[4];//cache״̬:0 ��Ч��������������ռ

		//�����¼�, false��ȱʧ��trueдȱʧ
		public void buslisten(Mypanel process,String address,boolean busEvent){
		        int select=4;
                for(int i=0;i<4;i++){
                    if(process.Cache_Content[i][2].equals(address)) {
                        select=i;break;
                    }
                }
                if(select!=4){
                    if(process.cacheState[select]==1){ //����״̬
                        if(busEvent){//дȱʧ�������Ϊ��Ч
                            process.cacheState[select]=0;
                            process.Cache_Content[select][1]=" ";
                            process.Cache_Content[select][2]=" ";
                        }
                    }
                    if(process.cacheState[select]==2){//��ռ״̬
                        if(busEvent){//дȱʧ����д�ظÿ顡��ռ��Ϊ��Ч
                           // int addnum = Integer.valueOf(address).intValue();
                           // Mem_Content[addnum][2]="process "+Integer.toString(processId)+" д��";
                            process.cacheState[select]=0;
                            process.Cache_Content[select][1]=" ";
                            process.Cache_Content[select][2]=" ";
                        }
                        else {//��ȱʧ     д�ظÿ顡��ռ��Ϊ����
                            // int addnum = Integer.valueOf(address).intValue();
                            // Mem_Content[addnum][2]="process "+Integer.toString(processId)+" д��";
                            process.cacheState[select]=1;
                            process.Cache_Content[select][1]="д��";
                            process.Cache_Content[select][2]=address;
                        }
                    }

                }
                //���ڣ���û�иÿ�ֱ���˳�
		}
        public void exProcess(Mypanel process){
            int Method=Mylistmodel1_1.getSelectedIndex();
            int WR=process.Mylistmodel.getSelectedIndex();
            String address=process.jtext.getText();
            if(WR==-1||address==null||Method==-1){
                System.out.println("error,no wr or address or method");
            }
            else {
                int select = 4;
                boolean hit = false;
                int addnum = Integer.parseInt(address);
                    switch (Method) {
                        case 0://ֱ��ӳ��
                            select = addnum % 4;
                            if (process.Cache_Content[select][2].equals(address)) {
                                hit = true;
                            }
                            else{
                                if(process.cacheState[select]==2){ //��Ϊ��ռ״̬
                                    //д�ظÿ顡

                                    // Mem_Content[addnum][2]="process "+Integer.toString(processId)+" д��";

                                }
                            }
                            break;
                        case 1:  //2·������
                            select = addnum % 2;
                            if (process.Cache_Content[select * 2][2].equals(address)) {//����
                                select = select * 2;
                                hit = true;
                            } else if (process.Cache_Content[select * 2 + 1][2].equals(address)) {
                                select = select * 2 + 1;
                                hit = true;
                            }
                            if(hit==false) {//û������
                                if(cacheState[select*2]==0){ //����ѡ����Ч�Ŀ�
                                    select=select*2;
                                }else if(cacheState[select*2+1]==0){
                                    select=select*2+1;
                                }
                                else{ //û����Ч�Ŀ�
                                    int tmp=(int)(Math.random()*10)%2;
                                    select=select*2+tmp;
                                    if(process.cacheState[select]==2){ //��Ϊ��ռ״̬
                                        //д�ظÿ顡
                                        // int addnum = Integer.valueOf(address).intValue();
                                        // Mem_Content[addnum][2]="process "+Integer.toString(processId)+" д��";
                                    }
                                }
                            }
                            break;
                        case 2:
                            for (int i = 0; i < 4; i++) {//��·������
                                if (process.Cache_Content[i][2].equals(address)) {//����
                                    select = i;
                                    hit = true;
                                    break;
                                }
                            }
                            if(hit==false){//û������
                                select=4;

                                for (int i = 0; i < 4; i++) {//��·������
                                    if(process.cacheState[i]==0){
                                        select=i;//����ѡ��һ����Ч�Ŀ�
                                        break;
                                    }
                                }
                                if(select==4){//û����Ч�Ŀ顡���ѡ��һ����
                                    select=(int)(Math.random()*10)%4;
                                    if(process.cacheState[select]==2){ //��Ϊ��ռ״̬
                                        //д�ظÿ顡
                                        // int addnum = Integer.valueOf(address).intValue();
                                        // Mem_Content[addnum][2]="process "+Integer.toString(processId)+" д��";
                                    }
                                }
                            }
                            break;
                        default:
                            System.out.println("û������������");
                    }

                if(hit==true){ //������
                    switch (WR){
                        case 0://��cache  �����в��ı�cache״̬
                                process.Cache_Content[select][1]="��";
                                process.Cache_Content[select][2]=address;
                                break;
                        case 1://дcache
                            if( process.cacheState[select]==1){//cache��Ϊ����״̬
                                process.cacheState[select]=2;//cache״̬Ϊ��ռ
                                process.Cache_Content[select][1]="д";
                                process.Cache_Content[select][2]=address;
                                //����Ч״̬����������,��дȱʧ����
                                if(process!=panel2){buslisten(panel2,address,true);}
                                if(process!=panel3){buslisten(panel3,address,true);}
                                if(process!=panel4){buslisten(panel4,address,true);}
                                if(process!=panel5){buslisten(panel5,address,true);}
                            }
                           else{//cache��Ϊ��ռ״̬����д���в��ı��ռ״̬

                            }
                            break;
                    }
                }
                else{ //û������
                    switch (WR){
                        case 0://��cache ����cache״̬��Ϊ����
                            process.cacheState[select]=1;
                            //�Ѷ�ȱʧ����������
                            if(process!=panel2){buslisten(panel2,address,false);}
                            if(process!=panel3){buslisten(panel3,address,false);}
                            if(process!=panel4){buslisten(panel4,address,false);}
                            if(process!=panel5){buslisten(panel5,address,false);}
                            process.Cache_Content[select][1]="��";
                            process.Cache_Content[select][2]=address;
                            break;
                        case 1://дcache
                            process.cacheState[select]=2;//cache״̬Ϊ��ռ
                                //��дȱʧ����������
                            if(process!=panel2){buslisten(panel2,address,true);}
                            if(process!=panel3){buslisten(panel3,address,true);}
                            if(process!=panel4){buslisten(panel4,address,true);}
                            if(process!=panel5){buslisten(panel5,address,true);}

                            process.Cache_Content[select][1]="д";
                            process.Cache_Content[select][2]=address;
                           break;
                    }
                    }
                }
            }

		public void actionPerformed(ActionEvent e){
			/******��д�Լ��Ĵ�����*******/
				if (e.getSource() == this.button) {
						exProcess(this);
				}
				/**********��ʾˢ�º������********/
				panel2.setVisible(false);
				panel2.setVisible(true);
				panel3.setVisible(false);
				panel3.setVisible(true);
				panel4.setVisible(false);
				panel4.setVisible(true);
				panel5.setVisible(false);
				panel5.setVisible(true);

			}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame myjf = new JFrame("��cacheһ����ģ��֮������");
		myjf.setSize(1500, 600);
		myjf.setLayout(null);
		myjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container C1 = myjf.getContentPane();

        JTable table_2 = new JTable(Mem_Content,Mem_ca);
        JScrollPane scrollPane2 = new JScrollPane(table_2);

		/*****�½�panel1*****/
		JPanel panel1 = new JPanel();

		C1.add(panel2);
		C1.add(panel3);
		C1.add(panel4);
		C1.add(panel5);
		C1.add(scrollPane2);
		panel2.setBounds(10, 100, 350, 200);
		panel3.setBounds(350, 100, 350, 200);
		panel4.setBounds(700, 100, 350, 200);
		panel5.setBounds(1050, 100, 350, 200);
		scrollPane2.setBounds(200,350,1000,180);
		scrollPane2.setFont(new Font("",1,15));
		//scrollPane2.setBounds(100, 250, 310, 180);
		
		/********����ÿ��Mypanel�Ĳ�ͬ�Ĳ���************/
		panel2.label_2.setText("Process1");
		panel3.label_2.setText("Process2");
		panel4.label_2.setText("Process3");
		panel5.label_2.setText("Process4");
		panel2.table_1.getColumnModel().getColumn(0).setHeaderValue("cache1");
		panel2.Cache_ca[0]="Cache1";
		panel3.table_1.getColumnModel().getColumn(0).setHeaderValue("cache2");
		panel3.Cache_ca[0]="Cache2";
		panel4.table_1.getColumnModel().getColumn(0).setHeaderValue("cache3");
		panel4.Cache_ca[0]="Cache3";
		panel5.table_1.getColumnModel().getColumn(0).setHeaderValue("cache4");
		panel5.Cache_ca[0]="Cache4";
		
		
		//panel2.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory1");
		//panel3.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory2");
		//panel4.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory3");
		//panel5.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory4");
		
		for(int i=0;i<10;i++){
			//panel3.Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel3.Mem_Content[i][0])+10));
			//panel4.Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel3.Mem_Content[i][0])+20));
			//panel5.Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel3.Mem_Content[i][0])+30));
		}
		/********����ͷ��panel*****/
		panel1.setBounds(10, 10, 1500, 100);
		panel1.setLayout(null);
		
		JLabel label1_1=new JLabel("ִ�з�ʽ:����ִ��");
		label1_1.setFont(new Font("",1,20));
		label1_1.setBounds(15, 15, 200, 40);
		panel1.add(label1_1);
		
		//JComboBox<String> Mylistmodel1_1 = new JComboBox<>(new Mylistmodel());
		Mylistmodel1_1.setBounds(220, 15, 150, 40);
		Mylistmodel1_1.setFont(new Font("",1,20));
		panel1.add(Mylistmodel1_1);
		
		JButton button1_1=new JButton("��λ");
		button1_1.setBounds(400, 15, 70, 40);
		
		/**********��λ��ť�¼�����ʼ����***********/
		button1_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel2.init();
				panel3.init();
				panel4.init();
				panel5.init();
				Mylistmodel1_1.setSelectedItem(null);

			}
		});
		
		/*panel2.Mem_Content[1][1]="11";*/
		panel1.add(button1_1);
		C1.add(panel1);
		myjf.setVisible(true);

	}
}

