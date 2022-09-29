package kiosk_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel_Chi extends JPanel {

	JPanel panel;
	String chimenu[] = { "짜장면", "짬뽕", "굴짬뽕", "탕수육", "팔보채", "누룽지탕", "칠리새우", "꿔바로우", "멘보샤" };
	JButton[] menu = new JButton[chimenu.length];	 // menu 변수에 chimenu 해당 값 각 인덱스 방에 저장
	String price[] = { "7000원", "9000원", "11000원", "17000원", "28000원", "32000원", "21000원", "18000원", "22000원" };
	JButton[] pricebtn = new JButton[price.length];	 // pricebtn 변수에 price 해당 값 각 인덱스 방에 저장
	String name;	// menu + price 배열로 인덱스 0번방 값으로 합쳐져 프레임에 출력 될 수 있게하는 변수

	MainTest parent; // null
	int count;

	
	public Panel_Chi(MainTest project) {	// Panel_Chi 에 Project 주소값이 저장 되어있음
		parent = project;
		setBackground(Color.WHITE); 		// 각 메뉴 버튼 색상 white 지정
		initial();							
		eventProc();						
		
	}//end of Panel_Chi

	public void addLayout() {
		initial();
		eventProc();
	}//end of addLayout()

	public void initial() {

		ImageIcon image[] = new ImageIcon[9];	
		// image 9개의 배열 방에 값 직접 대입
		image[0] = new ImageIcon("src\\project\\button_image\\1.jpg");
		image[1] = new ImageIcon("src\\project\\button_image\\2.jpg");
		image[2] = new ImageIcon("src\\project\\button_image\\3.jpg");
		image[3] = new ImageIcon("src\\project\\button_image\\4.jpg");
		image[4] = new ImageIcon("src\\project\\button_image\\5.jpg");
		image[5] = new ImageIcon("src\\project\\button_image\\6.jpg");
		image[6] = new ImageIcon("src\\project\\button_image\\7.jpg");
		image[7] = new ImageIcon("src\\project\\button_image\\8.jpg");
		image[8] = new ImageIcon("src\\project\\button_image\\9.jpg");

		setLayout(new GridLayout(3, 3));
		for (int i = 0; i < 9; i++) { 							// fmenu 판넬 (3열 3행 지정)
	name = chimenu[i] + "/" + price[i];							// 메뉴명 + 가격 name 변수에 저장
			
			menu[i] = new JButton(name, image[i]); 				// 이미지 + 메뉴 이름 = menu[i]
			menu[i].setHorizontalTextPosition(JButton.CENTER);	// 가운데 정렬
			menu[i].setVerticalTextPosition(JButton.BOTTOM);	// 메뉴명 & 가격 하단 지정
			menu[i].setFont(new Font(null, Font.BOLD, 15));		// 메뉴명 & 가격 폰트/폰트 크기 지정
			menu[i].setBackground(Color.white);					// 버튼 컬러 지정
			add(menu[i]);										// 판넬에 버튼추가
		}//for

	}//end of initial()

	
	
	
	public void addchiLayout() {
		// 중식 메뉴 탭

	}//end of addchiLayout

	public void eventProc() {
		
		// 메뉴명 클릭시 메뉴탭에 출력되는 이벤트
		for (int i = 0; i < menu.length; i++) { // 배열로 선언했기 때문에 반드시 for문
			menu[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {		// ==> ActionEvent menu[i] 라는 JButton 객체(발생한 이벤트)를 받음 
					// 이벤트가 발생한 컴포넌트의 참조를 얻어옴
					// num[0] = 메뉴명
					// num[1] = 가격
					JButton menubtn = (JButton) e.getSource(); 		// ==> getSource를 통해 형변환하여 menubtn에 대입
					String[] num = menubtn.getText().split("/");	// ==> 대입 된 값을 구분자 "/" 로 나눠 num 문자열 배열에 저장
					
					inputData(num);
					clearTA();				
					showUp();
					
				}//end of actionPerformed
			
			});//end of addActionListener
		}
	}//end of eventProc


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
}