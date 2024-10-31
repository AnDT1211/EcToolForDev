package com.andt.ectool.controller.pc;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("pc02/api")
public class Pc02ControllerRest {

	/**
	 * $1: focus_out_field_name_input
	 */
	private static String skeleton_have_check = """
Nội dung xác nhận: Khi lost focus $1	
	
Trường hợp 1:	
INPUT: 	
	$1 = blank
OUTPUT: 	
	Clear hạng mục MH
	

Trường hợp 2:	
INPUT: 	
	$1 <> blank
	チェック結果：falseの場合 Trường hợp kết quả check là false
OUTPUT: 	
	処理を終了する。kết thúc xử lý
	

Trường hợp 3:	
INPUT: 	
	$1 <> blank
	チェック結果：trueの場合 Trường hợp kết quả check là true
	Không lấy được record nào
OUTPUT: 	
	Clear hạng mục MH
	

Trường hợp 4:	
INPUT: 	
	$1 <> blank
	チェック結果：trueの場合 Trường hợp kết quả check là true
	Chỉ lấy được 1 record
OUTPUT: 	
	Thiết lập hạng mục MH
	

Trường hợp 5:	
INPUT: 	
	$1 <> blank
	チェック結果：trueの場合 Trường hợp kết quả check là true
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
	 * $1: focus_out_field_name_input
	 */
	private static String skeleton = """
Nội dung xác nhận: Khi lost focus $1
	
Trường hợp 1:	
INPUT: 	
	$1 = blank
OUTPUT:	
	Clear hạng mục MH
	

Trường hợp 2:	
INPUT: 	
	$1 <> blank
	Không lấy được record nào
OUTPUT:	
	Clear hạng mục MH
	

Trường hợp 3:	
INPUT: 	
	$1 <> blank
	Chỉ lấy được 1 record
OUTPUT:	
	Thiết lập hạng mục MH
	

Trường hợp 4:	
INPUT: 	
	$1 <> blank
	Lấy được từ 2 record trở lên
OUTPUT:	
	サブ画面 được hiển thị
	

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
		String focus_out_field_name_input = request.getParameter("focus_out_field_name_input_str");
		
		String skeletonTmp = skeleton;
		if (have_check) {
			skeletonTmp = skeleton_have_check;
		}
		
		skeletonTmp = skeletonTmp.replaceAll("\\$1", focus_out_field_name_input);
		
		return Map.of("data", skeletonTmp);
	}
}
