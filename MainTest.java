package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;


public class MainTest {

	//멤버변수 생성
	JFrame f;
	JLabel title, sumCount, sumPrice, total;
	JTextArea taMenu, taPrice;
	JButton pay, cancel;
	
	//한식 판넬
	Korean panelK;
	
	
	
	MainTest(){
		//객체 생성
		f = new JFrame("kosmo 휴게소");
		
		//제목
		 title = new JLabel("KOSMO 휴게소");
	      title.setFont(new Font(null, Font.BOLD, 55)); // 라벨 글씨체 , 크기 수정
	      title.setHorizontalAlignment(JLabel.CENTER); // 라벨 가운데 정렬
	      title.setForeground(Color.DARK_GRAY);
		//주문내역 부분
		taMenu = new JTextArea();
		taPrice = new JTextArea();
		
		//총 합계 부분
		total = new JLabel("TOTAL : ");
		sumCount = new JLabel("");
		sumPrice = new JLabel("");
		
		//결제 및 전체취소 버튼
		pay = new JButton("결제");
		cancel = new JButton("전체취소");
		
		//Panel
		panelK = new Korean(this); //한식
	}
	
	
	//Main Layout
	void addLayout() {
		f.setLayout(new BorderLayout());
		f.add(title, BorderLayout.NORTH);
		
		//TabbedPane 생성
		JTabbedPane tab = new JTabbedPane();
		tab.addTab("한식", panelK);
		f.add(tab,BorderLayout.CENTER);
		
		//주문내역 Panel 생성
		JPanel pList = new JPanel(new GridLayout(1,2));
		pList.add(taMenu);
		pList.add(taPrice);
		
		//총 합계 Panel 생성
		JPanel pSum = new JPanel(new GridLayout(1,3));
		pSum.add(total);
		pSum.add(sumCount);
		pSum.add(sumPrice);

		//버튼 Panel 생성
		JPanel pButton = new JPanel(new GridLayout(1,2));
		pButton.add(cancel);
		pButton.add(pay);

		

		//아래쪽에 놓을 Panel 생성
		JPanel pSouth = new JPanel(new GridLayout(2,1));	
		pSouth.add(pSum);	//총 합계 Panel 붙임
		pSouth.add(pButton);
		
		JPanel pInfo = new JPanel(new GridLayout(2,1));
		pInfo.add(pList);
		pInfo.add(pSouth,BorderLayout.SOUTH);
		
		f.add(pInfo,BorderLayout.EAST);			//전체 창의 아래쪽에 Panel 붙임

		//화면 출력
		f.setBounds(100, 100, 1300, 800);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void eventProc() {
		
	}//end of eventProc
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainTest test = new MainTest();
		test.addLayout();
	}

}
