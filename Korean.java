package project;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import b_info.PersonVO;

public class Korean extends JPanel{
	
	//객체 생성
	JPanel p = new JPanel();
	
	JButton[] kBtn = new JButton[9];							//메뉴 버튼 구성
	JLabel[] kMenu = new JLabel[9];								//메뉴 입력 Label
	
	String[] menuName = {"김치찌개","된장찌개","제육볶음","비빔밥",
			"갈비탕","설렁탕","라면","동태찌개","쫄면"};	      //메뉴 이름 배열
	
	String[] price = {"7000원","7000원","7000원","7000원","8000원","7500원",
			"4000원","8000원","7000원"};						//메뉴 가격 배열

	MainTest parent;

	
	
	public Korean(MainTest mainTest) {
		parent = mainTest;
		//메뉴 버튼 생성
		for(int i=0;i<kBtn.length;i++) {
			String imgSrc = "src\\project\\kor_imgs\\"+String.valueOf(i)+".PNG";	//이미지 주소
			
			String name = menuName[i] +" / "+ price[i];				// 메뉴이름 + 가격을 동시에 나타냄
			
			ImageIcon icon = new ImageIcon(imgSrc);					
			Image resizeImage = imageResize(icon, 200, 200);	 // 이미지 크기 조절
			kBtn[i] = new JButton(name, new ImageIcon(resizeImage));// 버튼에 메뉴와 아이콘 삽입
			kBtn[i].setBackground(new Color(255,255,255));		// 버튼 배경, 폰트 변경
			kBtn[i].setFont(new Font(null,Font.BOLD, 15));
			kBtn[i].setHorizontalTextPosition(JButton.CENTER);		// 수직으로 정렬
			kBtn[i].setVerticalTextPosition(JButton.BOTTOM);		// 글씨를 아래쪽에 놓음
		}//for
		
		
		addLayout();
		eventProc();

	}//end of Korean
	
	void addLayout() {
		setLayout(new GridLayout(3, 3));	// 3행 3열 그리드
		
		setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));	//여백
		
		for(int i=0;i<kBtn.length;i++) {			   // 그리드에 맞춰 버튼 추가
			add(kBtn[i]);
		}//for
		
	}//addLayout()
	
	 void eventProc() {
	 	
		 //메뉴 버튼을 눌렀을 때
	 	for(int i=0;i<kBtn.length;i++) {
	 		kBtn[i].addActionListener(new ActionListener(){
	 			public void actionPerformed(ActionEvent e) {
	 				// 이벤트가 발생한 컴포넌트의 참조를 얻어옴.
	 				JButton eBtn = (JButton)e.getSource();
	 				String[] strArr = eBtn.getText().split(" / ");
	 				System.out.println(strArr[0] + "/////" + strArr[1]);
	 				inputData(strArr); //VO에 입력값 넘김
	 				
	 				clearTA();			
	 				showUp();			//텍스트 에어리어에 주문 내용 출력
				}//end of actionPerformed
			});//end of addActionListener
	 	}//for
	 	
	 	//전체취소 버튼을 눌렀을 때
	 	parent.cancel.addActionListener(new ActionListener(){
	 		public void actionPerformed(ActionEvent e) {
	 			parent.list.clear();
	 			clearTA();
	 		}//end of actionPerformed
	 	});//end of addActionListener
	 	
	 }//eventProc
	
	//버튼에 들어갈 아이콘 이미지 크기 변환 메소드
	Image imageResize(ImageIcon icon, int x, int y){
		Image image = icon.getImage();//아이콘에서 이미지를 불러옴
		Image resizeImage = image.getScaledInstance(x,y,java.awt.Image.SCALE_SMOOTH); //이미지 화질을 최대한 보존하면서 크기 조정
		return resizeImage;//크기 조정된 이미지 반환
	}//imageResize
	
	//PayVO에 데이터 입력 메소드
	public void inputData(String[] strArr) {
		PayVO p = new PayVO();	
		p.setMenu(strArr[0]);			//메뉴 이름 저장
		p.setPrice(strArr[1]);			//금액 저장
		
		parent.list.add(p);				//PayVO에 입력내용 저장
	}//inputData
	
	//TextArea 초기화 메소드
	public void clearTA() {
		//각 TextArea의 내용을 비움
		parent.taMenu.setText(null);
		parent.taPrice.setText(null);
		
		//최종 금액, 개수 초기화
		parent.sum = 0;
		parent.sumCount.setText(String.valueOf(parent.list.size())+"개");
		parent.sumPrice.setText(String.valueOf(parent.sum)+"원");
	}//clearTA
	
	//주문내역 출력 메소드
	public void showUp() {
		String input = null;
		for(PayVO vo : parent.list) {
			input = vo.toString();
			String[] arr = input.split("/");			//"[메뉴명]/[금액]"을 [메뉴]와 [금액]으로 나눔
			parent.taMenu.append(arr[0]+"\n");	// 메뉴 텍스트에어리어에 추가
			parent.taPrice.append(arr[2]+"\n");	// 가격 텍스트에어리어에 추가
			calSum(arr[2]);								// 최종 갯수/가격 설정
			
			//최종 갯수/가격 출력			
			parent.sumCount.setText(String.valueOf(parent.list.size())+"개");
			parent.sumPrice.setText(String.valueOf(parent.sum)+"원");

		}//for
	}//showUp

	
	//총합계 계산 메소드
	void calSum(String price) {
		int index = price.indexOf("원");
		// 가격 String배열의 숫자만 계산하여 sum에 저장
		if(index ==-1)											//"원"이 없을 경우
		{parent.sum += Integer.parseInt(price);	
		return;
		} else														//"원"이 있을 경우
		{parent.sum += Integer.parseInt(price.substring(0, index));
		}
	}//calSum

}//end of Korean
