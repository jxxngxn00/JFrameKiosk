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

	ArrayList <PayVO> list = new ArrayList<PayVO>();			//선택한 메뉴 정보 넘길 VO 생성
	MainTest parent;
	
	int sum = 0;//최종 가격
	
	
	public Korean(MainTest mainTest) {
		parent = mainTest;
		//메뉴 버튼 생성
		for(int i=0;i<kBtn.length;i++) {
			String imgSrc = "src\\project\\kor_imgs\\"+String.valueOf(i)+".PNG";	//이미지 주소
			
			String name = menuName[i] +" / "+ price[i];				// 메뉴이름 + 가격을 동시에 나타냄
			
			ImageIcon icon = new ImageIcon(imgSrc);					
			Image resizeImage = imageResize(icon, 200, 200);	 // 이미지 크기 조절
			kBtn[i] = new JButton(name, new ImageIcon(resizeImage));// 버튼에 메뉴와 아이콘 삽입
			kBtn[i].setBackground(new Color(255,255,255));
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
	 				inputData(strArr); //VO에 입력값 넘김
	 				clearTA();			
	 				showUp();			//텍스트 에어리어에 주문 내용 출력
				}//end of actionPerformed
			});//end of addActionListener
	 	}//for
	 	
	 	//전체취소 버튼을 눌렀을 때
	 	parent.cancel.addActionListener(new ActionListener(){
	 		public void actionPerformed(ActionEvent e) {
	 			list.clear();
	 			clearTA();
	 		}//end of actionPerformed
	 	});//end of addActionListener
	 	
	 }//eventProc
	
	//버튼에 들어갈 아이콘 이미지 크기 변환 메소드
	Image imageResize(ImageIcon icon, int x, int y){
		Image image = icon.getImage();//아이콘에서 이미지를 불러옴
		Image resizeImage = image.getScaledInstance(x,y,java.awt.Image.SCALE_SMOOTH); //이미지 화질을 최대한 보존하면서 크기 조정
		return resizeImage;//크기 조정된 이미지 반환
	}
	
	void inputData(String[] strArr) {
		PayVO p = new PayVO();		//PayVO에 입력내용 저장
		p.setMenu(strArr[0]);
		p.setPrice(strArr[1]);
		p.setCount(p.getCount()+1);

		list.add(p);
	}//inputData
	
	void clearTA() {
		//각 TextArea의 내용을 비움
		parent.taMenu.setText(null);
		parent.taPrice.setText(null);
		
		//최종 금액, 개수 초기화
		sum = 0;
		parent.sumCount.setText(String.valueOf(list.size())+"개");
		parent.sumPrice.setText(String.valueOf(sum)+"원");
	}
	
	//주문내역 출력 메소드
	void showUp() {
		String input = null;
		for(PayVO vo : list) {
			input = vo.toString();
			String[] arr = input.split("/");
			parent.taMenu.append(arr[0]+"\n");	// 메뉴 텍스트필드에 추가
			parent.taPrice.append(arr[2]+"\n");	// 가격 텍스트필드에 추가
			calSum(arr[2]);								// 최종 갯수/가격 설정
			
			//최종 갯수/가격 출력			
			parent.sumCount.setText(String.valueOf(list.size())+"개");
			parent.sumPrice.setText(String.valueOf(sum)+"원");

		}
	}//showUp

	
	//총합계 계산 메소드
	void calSum(String price) {
		int index = price.indexOf("원");
		sum += Integer.parseInt(price.substring(0, index));	// 가격 String배열의 숫자만 계산하여 sum에 저장
	}//calSum

}//end of Korean
