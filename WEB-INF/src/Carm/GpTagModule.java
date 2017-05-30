package Carm;

import java.util.ArrayList;
import java.util.List;

//文字列からタグを抽出する汎用モジュール
public class GpTagModule {

	public List<String> getTag(String target, char tag) { // 引数に対象文字列とタグ記号を指定
		List<String> tags = new ArrayList<String>(); // 抽出したタグを格納するリスト
		List<Integer> tagStartPositions = new ArrayList<Integer>(); // タグ開始位置のリスト
		List<Integer> tagEndPositions = new ArrayList<Integer>(); // タグ終了位置のリスト
		boolean tagEndSerchMode = false; // true:タグ開始位置探索状態 false:タグ終了位置探索状態

		for (int i = 0; i < target.length(); i++) {
			if (!tagEndSerchMode) {
				// タグ開始文字を発見した場合
				if (target.charAt(i) == tag) {
					tagStartPositions.add(i);
					// タグ終了位置探索状態に切替
					tagEndSerchMode = true;
				}
			} else {
				if (target.charAt(i) == ' ' || target.charAt(i) == '　') {
					// 半角か全角のスペースを発見した場合
					tagEndPositions.add(i);
					// タグ開始位置探索状態に切替
					tagEndSerchMode = false;
				} else if (i == target.length() - 1) {
					tagEndPositions.add(i + 1);
				}
			}
		}

		for (int i = 0; i < tagEndPositions.size(); i++) {
			// 開始位置と終了位置からタグを切り出してリストに格納
			tags.add(target.substring(tagStartPositions.get(i),
					tagEndPositions.get(i)));
		}
		return tags;
	}
}
