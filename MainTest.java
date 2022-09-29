package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;


public class MainTest {

	int sum=0;//총 합계 계산할 변수
	
	//멤버변수 생성
	JFrame f;
	JLabel title, sumCount, sumPrice, total, totalTitle;
	JTextArea taMenu, taPrice;
	JButton pay, cancel;
	
	//Panel
	Korean panelK;	//한식
	Panel_Chi panelC;//중식
	PanelI panelI;//양식
	KioskA panelJ;//일식
	
	ArrayList <PayVO> list = new ArrayList<PayVO>();			//선택한 메뉴 정보 넘길 VO 생성
	
	MainTest(){
		//객체 생성
		f = new JFrame("kosmo 휴게소");
		
		//제목
		title = new JLabel("KOSMO 휴게소");
		title.setFont(new Font(null, Font.BOLD, 55)); // 라벨 글씨체 , 크기 수정
		title.setHorizontalAlignment(JLabel.CENTER); // 라벨 가운데 정렬
		title.setForeground(Color.DARK_GRAY);			// 제목글씨 색 -- 짙은 회색
		
		//주문내역 부분
		taMenu = new JTextArea();
		taMenu.setFont(new Font(null, Font.BOLD, 15));	// 폰트 수정
		
		taPrice = new JTextArea();
		taPrice.setFont(new Font(null, Font.BOLD, 15));	// 폰트 수정
		
		//총 합계 부분
		total = new JLabel("TOTAL : ");
		sumCount = new JLabel("");
		sumPrice = new JLabel("");
		
		//결제 및 전체취소 버튼
		pay = new JButton("결제");
		cancel = new JButton("전체취소");
		pay.setBackground(new Color(255,255,255));							//버튼 색 설정
		cancel.setBackground(new Color(255,255,255));
		pay.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));	//여백
		cancel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));	//여백
		
		//Panel
		panelK = new Korean(this); //한식
		panelC = new Panel_Chi(this);	//중식
		panelI = new PanelI(this);	//양식
		panelJ = new KioskA(this);	//일식
	}//end of MainTest
	
	
	//Main Layout
	void addLayout() {
		f.setLayout(new BorderLayout());
		f.add(title, BorderLayout.NORTH);//제목 출력
		
		//TabbedPane 생성
		JTabbedPane tab = new JTabbedPane();
		
		//주문할 메뉴 생성
		tab.addTab("한식", panelK);
		tab.addTab("중식", panelC);
		tab.addTab("양식", panelI);
		tab.addTab("일식", panelJ);
		f.add(tab,BorderLayout.CENTER);
		
		//주문내역 Panel 생성
		JPanel pList = new JPanel(new GridLayout(1,2));
		pList.add(taMenu);//주문 메뉴 전체 내역
		pList.add(taPrice);//총 금액
		
		//총 합계 Panel 생성 -- 총 금액합계, 주문 수 삽입
		JPanel pSum = new JPanel(new GridLayout(1,3));
		pSum.add(total);
		pSum.add(sumCount);
		pSum.add(sumPrice);

		//버튼 Panel 생성 -- 결제, 전체취소 버튼 삽입
		JPanel pButton = new JPanel(new GridLayout(1,2));
		pButton.add(cancel);
		pButton.add(pay);
		

		//아래쪽에 놓을 Panel 생성
		JPanel pSouth = new JPanel(new GridLayout(2,1));	
		pSouth.add(pSum);	//총 합계 Panel 붙임
		pSouth.add(pButton);//버튼 Panel 붙임
		
		//메뉴 외의 부분 Panel 생성
		JPanel pInfo = new JPanel(new GridLayout(2,1));
		pInfo.add(pList);
		pInfo.add(pSouth,BorderLayout.SOUTH);

		pInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));	//여백
		
		f.add(pInfo,BorderLayout.EAST);			//전체 창의 아래쪽에 Panel 붙임

		//화면 출력
		f.setBounds(100, 100, 1300, 850);
		f.setResizable(false);//크기 변경 불가능
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//end of addLayout()
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainTest test = new MainTest();
		test.addLayout();
	}

}
