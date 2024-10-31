package com.andt.ectool.controller.pc;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("pc01/api")
public class Pc01ControllerRest {

	/**
	 * $1: button_name_input
	 * $2: sub_dialog_name_input
	 * $3: focus_out_field_name_input
	 */
	private String skeleton_have_check = """
Nội dung xác nhận: Khi nhấn nút $1 thì thực hiện check data và điều khiển hiển thị màn hình sub 「$2」		
		
Trường hợp 1:		
INPUT:		
	チェック結果：falseの場合 Trường hợp kết quả check là false	
OUTPUT:		
	処理を終了する。kết thúc xử lý	
		
Trường hợp 2:		
INPUT:		
	チェック結果：trueの場合 Trường hợp kết quả check là true	
	$3 không nhập	
OUTPUT:		
	サブ画面 được hiển thị 	
		
Trường hợp 3:		
INPUT:		
	チェック結果：trueの場合 Trường hợp kết quả check là true	
	$3 có nhập	
	Không lấy được record nào	
OUTPUT:		
	Clear hạng mục MH	
		
Trường hợp 4:		
INPUT:		
	チェック結果：trueの場合 Trường hợp kết quả check là true	
	$3 có nhập	
	Chỉ lấy được 1 record	
OUTPUT:		
	Thiết lập hạng mục MH	
		
Trường hợp 5:		
INPUT:		
	チェック結果：trueの場合 Trường hợp kết quả check là true	
	$3 có nhập	
	Lấy được từ 2 record trở lên	
OUTPUT:		
	サブ画面 được hiển thị	
		
Trường hợp 6:		
INPUT:		
	チェック結果：trueの場合 Trường hợp kết quả check là true	
	Khi サブ画面 được khởi động	
	Đã chọn dòng ở サブ画面	
OUTPUT:		
	Thiết lập hạng mục MH	
		
Trường hợp 7:		
INPUT:		
	チェック結果：trueの場合 Trường hợp kết quả check là true	
	Khi サブ画面 được khởi động	
	Không chọn dòng ở サブ画面	
OUTPUT:		
	MH giữ nguyên trạng thái như trước khi mở MH sub	
			""";
	
	
	/**
	 * $1: button_name_input
	 * $2: sub_dialog_name_input
	 * $3: focus_out_field_name_input
	 */
	private static String skeleton = """
Nội dung xác nhận: Khi nhấn nút $1 thì điều khiển hiển thị màn hình sub 「$2」	
	
Trường hợp 1:	
INPUT	
	 Trường hợp $3 không nhập
OUTPUT:	
	サブ画面 được hiển thị 
	
Trường hợp 2:	
INPUT: 	
	$3 có nhập
	Không lấy được record nào
OUTPUT:	
	Clear hạng mục MH
	
Trường hợp 3:	
INPUT: 	
	$3 có nhập
	Chỉ lấy được 1 record
OUTPUT:	
	Thiết lập hạng mục MH
	
Trường hợp 4:	
INPUT: 	
	$3 có nhập
	Lấy được từ 2 record trở lên
OUTPUT: サブ画面 được hiển thị	
	
Trường hợp 5:	
INPUT: 	
	Khi サブ画面 được khởi động
	Đã chọn dòng ở サブ画面
OUTPUT:	
	Thiết lập hạng mục MH
	
Trường hợp 6:	
INPUT: 	
	Khi サブ画面 được khởi động
	Không chọn dòng ở サブ画面
OUTPUT:	
	MH giữ nguyên trạng thái như trước khi mở MH sub
			""";
	
	@PostMapping("generate")
	public Map<String, Object> generate(HttpServletRequest request) {
		boolean have_check = Boolean.valueOf(request.getParameter("have_check"));
		String button_name_input = request.getParameter("button_name_input_str");
		String sub_dialog_name_input = request.getParameter("sub_dialog_name_input_str");
		String focus_out_field_name_input = request.getParameter("focus_out_field_name_input_str");
		
		String skeletonTmp = skeleton;
		if (have_check) {
			skeletonTmp = skeleton_have_check;
		}
		
		skeletonTmp = skeletonTmp.replaceAll("\\$1", button_name_input)
				.replaceAll("\\$2", sub_dialog_name_input)
				.replaceAll("\\$3", focus_out_field_name_input);
		
		return Map.of("data", skeletonTmp);
	}
}
