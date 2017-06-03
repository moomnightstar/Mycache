import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class directory {	
	/*****����panel2~panel5******/
	static Mypanel panel2 =new Mypanel();
	static Mypanel panel3 =new Mypanel();
	static Mypanel panel4 =new Mypanel();
	static Mypanel panel5 =new Mypanel();
	
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
				{"0","",""},{"1","",""},{"2","",""},{"3","",""}
		};
		/*********memory�ı���*********/
		String[] Mem_ca={
				"Memory","",""
		};
		
		/*********memory�е�����*********/
		String[][] Mem_Content ={
				{"0","",""},{"1","",""},{"2","",""},{"3","",""},{"4","",""},{"5","",""},{"6","",""},{"7","",""},
				{"8","",""},{"9","",""}
		};
		/************cache�Ĺ���ģ��***********/
		JTable table_1 = new JTable(Cache_Content,Cache_ca); 
		JScrollPane scrollPane = new JScrollPane(table_1);
		/************memory�Ĺ���ģ��***********/
		JTable table_2 = new JTable(Mem_Content,Mem_ca); 
		JScrollPane scrollPane2 = new JScrollPane(table_2);
		
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
			add(scrollPane2);
			
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
			
			scrollPane2.setFont(new Font("",1,15));
			scrollPane2.setBounds(10, 190, 310, 180);
			
			button.setFont(new Font("",1,15));
			button.setBounds(220,50, 100, 35);
			
			/******��Ӱ�ť�¼�********/
			button.addActionListener(this);
		}
		
		public void init(){
			/******Mypanel�ĳ�ʼ��******/
			jtext.setText("");
			Mylistmodel.setSelectedItem(null);
			for(int i=0;i<=3;i++)
				for(int j=1;j<=2;j++)
					Cache_Content[i][j]="";
			for(int i=0;i<=9;i++)
				for(int j=1;j<=2;j++)
					Mem_Content[i][j]="";
			setVisible(false);
			setVisible(true);
			
		}
		//cacheEvent true д�ء�false����Ч
		public void cacheState(String address,boolean cacheEvent){
			int select=4;
			for(int i=0;i<4;i++){
				if(Cache_Content[i][2].equals(address)) {
					select=i;break;
				}
			}
			if(select!=4){
				if(cacheEvent){//д��
					Cache_Content[select][1]="д��";
					//Cache_Content[select][2]="";
				}
				else{//��Ч
					Cache_Content[select][1]="";
					Cache_Content[select][2]="";
				}
			}
		}
		
		//directoryEvent true дȱʧ��false��ȱʧ
        public void directoryState(String process_id,int addnum,String address,boolean directoryEvent){
		    if(Mem_Content[addnum][1].equals("����")){
		        if(directoryEvent){
		            //��Ч
					if(Mem_Content[addnum][2].indexOf("1")!=-1){ //������������
							panel2.cacheState(address,false);
					}
					if(Mem_Content[addnum][2].indexOf("2")!=-1){ //����������2
						panel3.cacheState(address,false);
					}
					if(Mem_Content[addnum][2].indexOf("3")!=-1){ //����������3
						panel4.cacheState(address,false);
					}
					if(Mem_Content[addnum][2].indexOf("4")!=-1){ //����������4
						panel5.cacheState(address,false);
					}
                    Mem_Content[addnum][1]="��ռ";
                    Mem_Content[addnum][2]=process_id;
                }
                else{//��ȱʧ����Ȼ����
		            Mem_Content[addnum][2]+=process_id;//����´�����
                }
            }
            else if(Mem_Content[addnum][1].equals("��ռ")){
		    	 /*cache����д��*/
				if(Mem_Content[addnum][2].indexOf("1")!=-1){ //������������
					panel2.cacheState(address,true);
				}
				else if(Mem_Content[addnum][2].indexOf("2")!=-1){ //����������2
					panel3.cacheState(address,true);
				}
				else if(Mem_Content[addnum][2].indexOf("3")!=-1){ //����������3
					panel4.cacheState(address,true);
				}
				else if(Mem_Content[addnum][2].indexOf("4")!=-1){ //����������4
					panel5.cacheState(address,true);
				}
                if(directoryEvent){//дȱʧ��״̬Ϊ�µĴ�������ռ
                    Mem_Content[addnum][1]="��ռ";
                    Mem_Content[addnum][2]=process_id;
                }
                else{//��ȱʧ����״̬��Ϊ����
                    Mem_Content[addnum][1]="����";
                    Mem_Content[addnum][2]+=process_id;
                }
            }
            else {//û�л���`
                if(directoryEvent){//дȱʧ
                    Mem_Content[addnum][1]="��ռ";
                    Mem_Content[addnum][2]=process_id;
                }
                else{//��ȱʧ
                    Mem_Content[addnum][1]="����";
                    Mem_Content[addnum][2]+=process_id;
                }
            }
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
							if(process.Cache_Content[select*2][2]==""){ //����ѡ����Ч�Ŀ�
								select=select*2;
							}else if(process.Cache_Content[select*2+1][2]==""){
								select=select*2+1;
							}
							else{ //û����Ч�Ŀ�
								int tmp=(int)(Math.random()*10)%2;
								select=select*2+tmp;
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
								if(process.Cache_Content[i][2]==""){
									select=i;//����ѡ��һ����Ч�Ŀ�
									break;
								}
							}
							if(select==4){//û����Ч�Ŀ顡���ѡ��һ����
								select=(int)(Math.random()*10)%4;
							}
						}

						break;
					default:
						System.out.println("û������������");
				}
                String process_id="0";
				if(process==panel2) process_id="1";
				else if(process==panel3) process_id="2";
				else if(process==panel4) process_id="3";
				else if(process==panel5) process_id="4";
				Mypanel objProcess;
                if(addnum<10){
                    objProcess=panel2;
                }
                else if(addnum<20){
                  objProcess=panel3;addnum-=10;
                }
                else if(addnum<30){
                  objProcess=panel4;addnum-=20;
                }
                else {
                   objProcess=panel5;addnum-=30;
                }
                if(hit==true){ //������
                    switch (WR){
                        case 0://��cache  �����в��ı�cache״̬
                            process.Cache_Content[select][1]="��";
                            process.Cache_Content[select][2]=address;
                            break;
                        case 1://дcache
							//д����,�ù���״̬��cache����Ч  ��дȱʧ����
							if(objProcess.Mem_Content[addnum][1].equals("����")){
                        		objProcess.directoryState(process_id,addnum,address,true);
							}
                            process.Cache_Content[select][1]="д";
                            process.Cache_Content[select][2]=address;
                            break;
                    }
                }
                else{//û������
                	if(process.Cache_Content[select][2]!=""){//cache������Ч
						int memAdd=Integer.parseInt(process.Cache_Content[select][2]);
						Mypanel CobjProcess;
						if(memAdd<10){
							CobjProcess=panel2;
						}
						else if(memAdd<20){
							CobjProcess=panel3;memAdd-=10;
						}
						else if(addnum<30){
							CobjProcess=panel4;memAdd-=20;
						}
						else {
							CobjProcess=panel5;memAdd-=30;
						}
                		if(objProcess.Mem_Content[memAdd][1].equals("��ռ")){
                			//cacheд��mem
							CobjProcess.Mem_Content[memAdd][1]="";
							CobjProcess.Mem_Content[memAdd][2]="";
						}
						else{//memΪ����
							if(objProcess.Mem_Content[memAdd][2].length()==1){
								CobjProcess.Mem_Content[memAdd][1]="";
								CobjProcess.Mem_Content[memAdd][2]="";
							}
							else{
								CobjProcess.Mem_Content[memAdd][2]=CobjProcess.Mem_Content[memAdd][2].replace(process_id,"");
							}

						}
					}
					switch (WR){
						case 0://  ��ȱʧ
							objProcess.directoryState(process_id,addnum,address,false);
							process.Cache_Content[select][1]="��";
							process.Cache_Content[select][2]=address;
							break;
						case 1://дcache
							//дȱʧ
							objProcess.directoryState(process_id,addnum,address,true);
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
		JFrame myjf = new JFrame("��cacheһ����ģ��֮Ŀ¼��");
		myjf.setSize(1500, 600);
		myjf.setLayout(null);
		myjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container C1 = myjf.getContentPane();
		
		/*****�½�panel1*****/
		JPanel panel1 = new JPanel();

		C1.add(panel2);
		C1.add(panel3);
		C1.add(panel4);
		C1.add(panel5);
		panel2.setBounds(10, 100, 350, 400);
		panel3.setBounds(350, 100, 350, 400);
		panel4.setBounds(700, 100, 350, 400);
		panel5.setBounds(1050, 100, 350, 400);
		
		
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
		
		
		panel2.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory1");
		panel3.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory2");
		panel4.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory3");
		panel5.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory4");
		
		for(int i=0;i<10;i++){
			panel3.Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel2.Mem_Content[i][0])+10));
			panel4.Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel2.Mem_Content[i][0])+20));
			panel5.Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel2.Mem_Content[i][0])+30));
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

